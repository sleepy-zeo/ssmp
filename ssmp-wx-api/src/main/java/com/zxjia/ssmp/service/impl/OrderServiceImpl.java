package com.zxjia.ssmp.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.zxjia.ssmp.dto.*;
import com.zxjia.ssmp.entity.Orders;
import com.zxjia.ssmp.entity.OrdersProduct;
import com.zxjia.ssmp.exception.BusinessException;
import com.zxjia.ssmp.feign.OrderApiService;
import com.zxjia.ssmp.feign.UserApiService;
import com.zxjia.ssmp.service.CartService;
import com.zxjia.ssmp.service.OrderService;
import com.zxjia.ssmp.service.ProductService;
import com.zxjia.ssmp.utils.ObjectUtil;
import com.zxjia.ssmp.vo.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderApiService orderApiService;
    @Autowired
    private UserApiService userApiService;
    @Autowired
    private ProductService productService;

    @Override
    public OrderVo bill(OrderRequest orderRequest) {
        CartListVo cartListVo = cartService.getCart(ObjectUtil.initObject(new CartRequest())
                .init(v -> v.setMemberId(orderRequest.getMemberId()))
                .init(v -> v.setProductId(orderRequest.getProductId())).getObject());

        if (CollectionUtil.isEmpty(cartListVo.getCart())) {
            throw new BusinessException("请求数据失败");
        }
        List<ProductVo> productVos = cartListVo.getCart().stream().map(CartVo::getProductVo).collect(Collectors.toList());
        BigDecimal totalMoney = productVos.stream().map(ProductVo::getMallMobilePrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        List<MemberAddressVo> memberAddressVoList = userApiService.getMemberAddress(ObjectUtil.initObject(new MemberAddressRequest())
                .init(v -> v.setMemberId(orderRequest.getMemberId())).getObject());

        MemberAddressVo memberAddressVo = memberAddressVoList.stream().filter(e -> e.getIsDefault().equals("0")).findFirst().orElse(null);

        //目前没有做折扣这块 就都取商品金额
        OrderVo orderVo = new OrderVo();
        orderVo.setTotalMoney(totalMoney);
        orderVo.setCartListVos(cartListVo.getCart());
        orderVo.setMemberAddressVo(memberAddressVo);
        return orderVo;
    }

    @Override
    @Transactional
    public OrderVo createOrder(OrderRequest orderRequest) {
        MemberVo memberVo = userApiService.getMemberByMobile(ObjectUtil.initObject(new MemberRequest())
                .init(v -> v.setMobile(orderRequest.getMobile())).getObject());
        if (memberVo == null) {
            throw new BusinessException("用户信息不存在");
        }
        List<MemberAddressVo> memberAddressVoList = userApiService.getMemberAddress(ObjectUtil.initObject(new MemberAddressRequest())
                .init(v -> v.setMemberId(orderRequest.getMemberId())).getObject());
        if (memberAddressVoList.size() == 0) {
            throw new BusinessException("您还没有创建收货地址");
        } else if (memberAddressVoList.stream().filter(e -> e.getIsDefault().equals("0")).findFirst().get() == null) {
            throw new BusinessException("您还没有默认的收获地址");
        }

        MemberAddressVo memberAddressVo = userApiService.getMemberAddressInfo(ObjectUtil.initObject(new MemberAddressRequest())
                .init(v -> v.setId(Integer.parseInt(orderRequest.getAddressId()))).getObject());
        orderRequest.setOrderStatus(OrderRequest.STATUS_1);
        orderRequest.setCreateTime(new Date());
        orderRequest.setSource(OrderRequest.SOURCE_1);
        orderRequest.setRealName(memberAddressVo.getRealName());
        orderRequest.setAddress(memberAddressVo.getAddress());

        CartListVo cartListVo = cartService.getCart(ObjectUtil.initObject(new CartRequest())
                .init(v -> v.setMemberId(orderRequest.getMemberId()))
                .init(v -> v.setProductId(orderRequest.getProductId())).getObject());

        orderRequest.setCart(cartListVo.getCart());

        //1创建订单
        OrderVo orderVo = orderApiService.createOrder(orderRequest);
        //2清除购物车
        cartService.deleteCart(ObjectUtil.initObject(new CartRequest())
                .init(v -> v.setMemberId(orderRequest.getMemberId()))
                .init(v -> v.setProductId(orderRequest.getProductId())).getObject());
        //3减库存
        //productService.updateProduct(ObjectUtil.initObject(new ProductRequest())
        //       .init(v -> v.setId()))

        return orderVo;
    }

    @Override
    public OrderListVo orders(OrderRequest orderRequest) {
        OrderListVo orderListVo = orderApiService.orders(orderRequest);

        if (ObjectUtils.isNotEmpty(orderListVo)) {
            List<Orders> orderListVos = orderListVo.getOrdersList();
            for (Orders order : orderListVos) {
                order.setCreateDate(DateUtil.format(order.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                switch (order.getOrderStatus()) {
                    case Orders.ORDER_STATUS_1:
                        order.setPayStatus("待付款");
                        break;
                    case Orders.ORDER_STATUS_2:
                        order.setPayStatus("待发货");
                        break;
                    case Orders.ORDER_STATUS_3:
                        order.setPayStatus("待收货");
                        break;
                    case Orders.ORDER_STATUS_4:
                        order.setPayStatus("待评价");
                        break;
                    case Orders.ORDER_STATUS_8:
                        order.setPayStatus("已取消");
                        break;
                }
                List<OrdersProduct> ordersProductList = order.getOrdersProducts();
                List<Integer> productIds = ordersProductList.stream().map(OrdersProduct::getProductId).collect(Collectors.toList());
                List<ProductVo> productVoList = productService.getProductByIds(ObjectUtil.initObject(new ProductRequest())
                        .init(v -> v.setProductIds(productIds)).getObject());

                Map<Integer, String> map = productVoList.stream().collect(Collectors.toMap(ProductVo::getId, ProductVo::getImgUrl));
                ordersProductList.stream().forEach(ordersProduct -> {
                    ordersProduct.setImgUrl(map.get(ordersProduct.getProductId()));
                });
            }
        }
        return orderListVo;
    }

    @Override
    public boolean updateOrder(OrderRequest orderRequest) {
        OrdersVo ordersVo = orderApiService.getOrder(orderRequest);
        if (ObjectUtils.isEmpty(ordersVo)) {
            throw new BusinessException("订单号不存在");
        }
        orderRequest.setId(ordersVo.getId());
        return orderApiService.updateOrder(orderRequest);
    }

    @Override
    public boolean createOrderComments(OrderCommentRequest request) {
        return orderApiService.createOrderComments(request);
    }
}
