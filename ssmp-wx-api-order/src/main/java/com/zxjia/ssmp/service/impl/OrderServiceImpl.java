package com.zxjia.ssmp.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxjia.ssmp.dto.OrderCommentRequest;
import com.zxjia.ssmp.dto.OrderRequest;
import com.zxjia.ssmp.entity.OrderComment;
import com.zxjia.ssmp.entity.Orders;
import com.zxjia.ssmp.entity.OrdersProduct;
import com.zxjia.ssmp.mapper.OrderCommentMapper;
import com.zxjia.ssmp.mapper.OrderMapper;
import com.zxjia.ssmp.mapper.OrderProductMapper;
import com.zxjia.ssmp.service.OrderService;
import com.zxjia.ssmp.utils.ObjectUtil;
import com.zxjia.ssmp.vo.OrderListVo;
import com.zxjia.ssmp.vo.OrderVo;
import com.zxjia.ssmp.vo.OrdersVo;
import com.zxjia.ssmp.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Autowired
    OrderProductMapper orderProductMapper;
    @Autowired
    OrderCommentMapper orderCommentMapper;

    @Override
    public OrderVo createOrder(OrderRequest request) {
        Orders orders = new Orders();
        BeanUtils.copyProperties(request, orders);

        orders.setOrderSn(RandomUtil.randomNumbers(16));
        orders.setPaySn(RandomUtil.randomNumbers(20));

        this.save(orders);

        request.getCart().stream().forEach(e -> {
            ProductVo product = e.getProductVo();
            orderProductMapper.insert(ObjectUtil.initObject(new OrdersProduct())
                    .init(v -> v.setOrderSn(orders.getOrderSn()))
                    .init(v -> v.setProductId(e.getProductId()))
                    .init(v -> v.setProductMoney(product.getMallMobilePrice().multiply(new BigDecimal(e.getNumber()))))
                    .init(v -> v.setProductName(product.getProductName()))
                    .init(v -> v.setProductNumber(e.getNumber()))
                    .init(v -> v.setProductPrice(product.getMallMobilePrice())).getObject());
        });

        return new OrderVo();
    }

    @Override
    public OrderListVo orders(OrderRequest orderRequest) {
        LambdaQueryWrapper<Orders> orders = new QueryWrapper<Orders>().lambda();
        if (orderRequest.getOrderSn() != null) {
            orders.eq(Orders::getOrderSn, orderRequest.getOrderSn());
        }
        if (StrUtil.isNotEmpty(orderRequest.getOrderStatus())) {
            orders.eq(Orders::getOrderStatus, orderRequest.getOrderStatus());
        }
        if (orderRequest.getMemberId() != null) {
            orders.eq(Orders::getMemberId, orderRequest.getMemberId());
        }
        orders.orderByDesc(Orders::getCreateTime);
        List<Orders> ordersList = this.list(orders);

        ordersList.stream().forEach(e -> {
            List<OrdersProduct> ordersProductList = orderProductMapper.selectList(new QueryWrapper<OrdersProduct>().lambda().eq(OrdersProduct::getOrderSn, e.getOrderSn()));
            e.setOrdersProducts(ordersProductList);
        });

        OrderListVo orderVo = new OrderListVo();
        orderVo.setOrdersList(ordersList);
        return orderVo;
    }

    @Override
    public boolean updateOrder(OrderRequest orderRequest) {
        Orders orders = new Orders();
        BeanUtils.copyProperties(orderRequest, orders);

        return this.updateById(orders);
    }

    @Override
    public OrdersVo getOrder(OrderRequest orderRequest) {
        OrdersVo ordersVo = new OrdersVo();

        LambdaQueryWrapper<Orders> orders = new QueryWrapper<Orders>().lambda();
        if (orderRequest.getOrderSn() != null) {
            orders.eq(Orders::getOrderSn, orderRequest.getOrderSn());
        }
        if (orderRequest.getId() != null) {
            orders.eq(Orders::getMemberId, orderRequest.getId());
        }
        Orders orders1 = this.getOne(orders);
        BeanUtils.copyProperties(orders1, ordersVo);
        return ordersVo;
    }

    @Override
    public boolean createOrderComments(OrderCommentRequest request) {
        OrderComment orderComment = new OrderComment();
        BeanUtils.copyProperties(request, orderComment);
        // TODO: orderCommentMapper.insert(orderComment);
        return true;
    }
}
