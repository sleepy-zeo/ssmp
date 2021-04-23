function initMoney() {
    var index = mlayer.loading({
        title: '加载中'
    });
    var member = localStorage.getItem("member");
    var productId = CoreUtil.getQueryVariable("productId");
    if (!productId) {
        productId = null;
    }
    CoreUtil.sendPost("/api/order/bill", {"memberId": JSON.parse(member).id, "productId": productId}, function (res) {
        initAddress(res.data.memberAddressVo)
        initProduct(res.data.cartListVos);

        $("#totalMoney").html("￥" + res.data.totalMoney);
        $("#productMoney").html("￥" + res.data.totalMoney);
        $("#orderMoney").val(res.data.totalMoney);
        if (res.data.memberAddressVo != null) {
            $("#addressId").val(res.data.memberAddressVo.id);
        }
        mlayer.close(index);
    });
}

$(function () {
    var member = localStorage.getItem("member");
    var memberId = JSON.parse(member).id;
    var mobile = JSON.parse(member).mobile;

    $(".settlement").click(function () {
        var orderMoney = $("#orderMoney").val();
        var addressId = $("#addressId").val();
        CoreUtil.sendPost("/api/order/createOrder", {
            "memberId": memberId,
            "orderMoney": orderMoney,
            "mobile": mobile,
            "addressId": addressId
        }, function (res) {
            location.href = "my-indent-all.html?memberId=" + memberId + "&status=all";
        });
    });
    $(".aui-address-box-default").click(function () {
        location.href = "receiving-adress-list.html?memberId=" + memberId;
    });

    initMoney();
})

function initAddress(memberAddressVo) {
    var address = "";
    if (memberAddressVo == null) {
        address +=
            "<li>" +
            "<strong></strong>" +
            "</li>" +
            "<li>" +
            "<i class='aui-icon aui-icon-address'></i>" +
            "还没有地址，请去创建" +
            "</li>";
    } else {
        address +=
            "<li>" +
            "<strong>" + memberAddressVo.realName + "</strong>" +
            "<span class='aui-tag' id='aui-home'>家里</span>" +
            "<span class='aui-tag aui-tag-default' id='aui-default'>默认</span>" +
            "</li>" +
            "<li>" +
            "<i class='aui-icon aui-icon-address'></i>" +
            "" + memberAddressVo.address + "" +
            "</li>";
    }
    $(".aui-address-box-default ul").html(address);
}

function initProduct(cartListVos) {
    if (cartListVos.length > 0) {
        var v = "";
        for (var cart of cartListVos) {
            v += "<a href='javascript:;' class='aui-list-product-fl-item'> " +
                "<div class='aui-list-product-fl-img'> " +
                "<img src='" + cart.productVo.imgUrl + "'> " +
                "</div>" +
                "<div class='aui-list-product-fl-text'> " +
                "<h3 class='aui-list-product-fl-title'>" + cart.productVo.productName + "</h3>" +
                "<div class='aui-list-product-fl-mes'>" +
                "<div>" +
                "<span class='aui-list-product-item-price'>" +
                "<em>¥</em>" + cart.productVo.mallMobilePrice + "" +
                "</span>" +

                "</div>" +

                "</div>" +
                "<div class='aui-list-product-fl-bag'>" +
                "<span><img src='themes/img/icon/bag1.png' alt=''></span>" +
                "<span><img src='themes/img/icon/bag2.png' alt=''></span>" +
                "</div>" +
                "</div>" +
                "</a>";
        }
        $(".aui-list-product-float-item").html(v)
    }
}
