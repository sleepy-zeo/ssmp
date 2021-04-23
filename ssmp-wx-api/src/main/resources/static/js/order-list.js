$(function () {

    $(".zjzz-buylist-mid").on("click", "#calcOrder", function () {
        var orderSn = $(this).attr("orderSn");
        var data = {"orderSn": orderSn, "orderStatus": "8"}
        CoreUtil.sendPost("/api/order/updateOrder", data, function (res) {
            window.location.reload();
        });
    });

    initOrder()
})

function initOrder() {
    var index = mlayer.loading({
        title: '加载中'
    });
    var member = localStorage.getItem("member");
    if (member == null) {
        mlayer.alert({
            title: '消息',
            content: '请先去登录',
            btn: ['不了', 'OK'],
            yes: function (index) {
                location.href = "login.html";
            }
        });
        return;
    }
    var memberId = JSON.parse(member).id;
    var mobile = JSON.parse(member).mobile;
    var status = CoreUtil.getQueryVariable("status");
    var data = {};
    if (status == "all") {
        data = {"memberId": memberId}
    } else if (status == "daifukuan") {
        data = {"memberId": memberId, "orderStatus": "1"}
    } else if (status == "daifahuo") {
        data = {"memberId": memberId, "orderStatus": "2"}
    } else if (status == "daishouhuo") {
        data = {"memberId": memberId, "orderStatus": "3"}
    } else if (status == "pingjia") {
        data = {"memberId": memberId, "orderStatus": "4"}
    }
    CoreUtil.sendPost("/api/order/orders", data, function (res) {
        $("#order-list").after(initList(res));
        mlayer.close(index);
    });

}

function initList(res) {
    var content = "";
    for (var order of res.data.ordersList) {
        content += '<div class="zjzz-buylist-goods1">' +
            '<div class="zjzz-buylist-gtime">' +
            '<span class="zjzz-buylist-gtime1">' + order.createDate + '</span>' +
            '<span class="zjzz-buylist-gtime2">' + order.payStatus + '</span>' +
            '</div>';
        for (var orderProduct of order.ordersProducts) {
            content += '<div class="zjzz-buylist-det">' +
                '<img src="' + orderProduct.imgUrl + '"/>' +
                '<div class="zjzz-buylist-gdetail"> ' +
                '<span class="zjzz-buylist-gtit1">' + orderProduct.productName + '</span>' +
                '<span class="zjzz-buylist-gmoney">' +
                '<i class="zjzz-buylist-gm1">￥' + orderProduct.productPrice + '</i>' +
                '<i class="zjzz-buylist-gm2">x' + orderProduct.productNumber + '</i>' +
                '</span>' +
                '</div>' +
                '</div>';
        }
        content +=
            '<span class="zjzz-buylist-goodsm">' +
            '<i class="zjzz-buylist-gm3">共1件</i>' +
            '<i>应付总额：<i class="zjzz-buylist-gm4">￥' + order.orderMoney + '</i></i>' +
            '</span>' +
            '<div class="zjzz-buylist-btn">';
        if (order.orderStatus == 1) {
            content +=
                '<a class="zjzz-buylist-btn3">去付款</a>' +
                '<a class="zjzz-buylist-btn1" id="calcOrder" orderSn = "' + order.orderSn + '">取消订单</a>';
        }
        if (order.orderStatus == 2) {
            content +=
                '<a class="zjzz-buylist-btn3">催发货</a>' +
                '<a href="order-tracking.html" class="zjzz-buylist-btn1">查看物流</a>' +
                '<a class="zjzz-buylist-btn1">退款</a>';
        }
        if (order.orderStatus == 3) {
            content +=
                '<a class="zjzz-buylist-btn3">确认收货</a>' +
                '<a href="order-tracking.html" class="zjzz-buylist-btn1">查看物流</a>';
        }
        content += "</div>";
        content += "</div>";
    }
    return content;
}