/*分享弹层*/
$(".gdetails-hshare").click(function () {
    $(".gdetails-layer-bg").show();
    $(".gd-share-layer").show();
    $("body").addClass("gdetails-ovrerHide");
    $(".delivery-layer").hide();
});
$(".gd-share-layer-box1").click(function () {
    $(".gdetails-layer-bg").hide();
    $(".gd-share-layer").hide();
    $("body").removeClass("gdetails-ovrerHide");
});
$(".gdetails-layer-bg").click(function () {
    $(".gdetails-layer-bg").hide();
    $(".gd-share-layer").hide();
    $("body").removeClass("gdetails-ovrerHide");
});
/*加入购物车*/
$(function () {

    $(".addcar").click(function (event) {
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
        var productId = $("#productId").val();
        CoreUtil.sendPost("/api/cart/addCart", {
            "memberMobile": JSON.parse(member).mobile,
            "productId": productId,
            "number": 1
        }, function (res) {
            if (res.code == 200) {
                $(".add-num").show();
                var addNumText = parseInt($(".add-num").text());
                addNumText++;
                $(".add-num").text(addNumText);
            } else {
                mlayer.error({
                    title: res.msg,
                    time: 3
                });
            }
        })
    });
    $(".buynow").click(function (event) {
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
        var productId = $("#productId").val();
        CoreUtil.sendPost("/api/cart/addCart", {
            "memberMobile": JSON.parse(member).mobile,
            "productId": productId,
            "number": 1
        }, function (res) {
            if (res.code == 200) {
                location.href = "order.html?productId=" + productId;
            } else {
                mlayer.error({
                    title: res.msg,
                    time: 3
                });
            }
        })
    });
    initBanner();


    // 初始化购物车数据
    var member = localStorage.getItem("member");
    if (member != null) {
        CoreUtil.sendPost("/api/cart/getCart", {"memberId": JSON.parse(member).id}, function (res) {
            if (res.data.cart.length > 0) {
                var number = 0;
                for (var cart of res.data.cart) {
                    number = cart.number + number;
                }
                $(".add-num").text(number);
            }
        });
    } else {
        var addNumText = parseInt($(".add-num").text());
        if (addNumText == 0) {
            $(".add-num").hide();
        }
    }

});

function initBanner() {
    var url = window.location.search; //获取url中"?"符后的字串
    var productId = url.substr(url.indexOf("=") + 1);
    var index = mlayer.loading({
        title: '加载中'
    });
    CoreUtil.sendGet("/api/product/productDetails", {"productId": productId}, function (res) {
        if (res.code == 200) {
            var bd = "";
            for (var imvos of res.data.productImageVos) {
                bd += "<li><a href='javascript:void(0)'><img src='" + imvos.imgUrl + "' /></a></li>";
            }
            $(".banner ul").html(bd);
            TouchSlide({slideCell: "#slideBox", autoPage: true, titCell: ".hd ul", mainCell: ".bd ul"});
            $("#description").attr("src", res.data.description)
            $("#productId").val(res.data.id);
            $("#mallMobilePrice").text(res.data.mallMobilePrice);
            if (null != res.data.originalPrice) {
                $("#originalPrice").text(res.data.originalPrice);
            }
            $("#productName").text(res.data.productName);
        } else {
            mlayer.error({
                title: res.msg,
                time: 3
            });
        }
        mlayer.close(index);
    });


}
