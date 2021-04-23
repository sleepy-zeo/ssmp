$(function () {

    $(".shop-cart-listbox1").on("click", ".shop-cart-add", function () {
        var index = mlayer.loading({
            title: '加载中'
        });
        var number = $(this).siblings(".shop-cart-numer").val(); //得到商品的数量
        var productId = $(this).attr("productId");
        var id = $(this).attr("id");

        if (number >= 50) {
            mlayer.alert({
                title: '消息',
                content: "单件商品最大购买数量不能超过50件",
                btn: 'OK'
            });
            mlayer.close(index);
            return;
        }

        CoreUtil.sendPost("/api/cart/updateCart", {
            "memberId": JSON.parse(member).id,
            "productId": productId,
            "number": ++number,
            "id": id
        }, function (res) {
        });
        var vall = $(this).prev().val();
        vall++;
        $(this).prev().val(vall);
        TotalPrice();
        mlayer.close(index);
    });
    $(".shop-cart-listbox1").on("click", ".shop-cart-subtract", function () {
        var index = mlayer.loading({
            title: '加载中'
        });
        var number = $(this).siblings(".shop-cart-numer").val(); //得到商品的数量
        var productId = $(this).attr("productId");
        var id = $(this).attr("id");
        if (number == 1) {
            mlayer.alert({
                title: '消息',
                content: "商品购买数量不能小于1",
                btn: 'OK'
            });
            mlayer.close(index);
            return;
        }
        CoreUtil.sendPost("/api/cart/updateCart", {
            "memberId": JSON.parse(member).id,
            "productId": productId,
            "number": --number,
            "id": id
        }, function (res) {
        });
        var vall = $(this).next().val();
        vall--;
        if (vall <= 0) {
            vall = 1;
        }
        $(this).next().val(vall);
        TotalPrice();
        mlayer.close(index);
    });

    $(".btn1").click(function () {
        var $btn2 = $(this).parent(".shop-cart-box2").siblings(".index-goods").children(".shop-cart-check2").children(".btn2");
        if ($(this).is(':checked')) {
            $btn2.prop("checked", this.checked);
            TotalPrice();
        } else {
            $btn2.removeAttr("checked");
            TotalPrice();
        }
    });


    $(".shop-cart-listbox1").on("click", ".btn2", function () {
        var goods = $(this).closest(".shop-cart-listbox1").find(".btn2"); //获取本店铺的所有商品
        var goodsC = $(this).closest(".shop-cart-listbox1").find(".btn2:checked"); //获取本店铺所有被选中的商品
        var Shops = $(this).closest(".shop-cart-listbox1").find(".btn1"); //获取本店铺的全选按钮
        if (goods.length == goodsC.length) { //如果选中的商品等于所有商品
            Shops.prop('checked', true); //店铺全选按钮被选中
            TotalPrice();
        } else { //如果选中的商品不等于所有商品
            Shops.prop('checked', false); //店铺全选按钮不被选中
            TotalPrice();
        }
    });

    $("#ckAll").click(function () {
        $("input[name='sub2']").prop("checked", this.checked);
        TotalPrice();
    });
    $("input[name='sub2']").click(function () {
        var $subs = $("input[name='sub2']");
        $("#ckAll").prop("checked", $subs.length == $subs.filter(":checked").length ? true : false);
        TotalPrice();
    });

    $("#bill").click(function () {
        if ($("#ckAll").prop("checked") == false) {
            mlayer.alert({
                title: '消息',
                content: "必须全选所有商品才能结算",
                btn: 'OK'
            });
            return;
        }
        var total = $("#AllTotal").text();
        if (total <= 0) {
            mlayer.alert({
                title: '消息',
                content: "您还没有选择购买的商品",
                btn: 'OK'
            });
            return;
        }
        location.href = "order.html";
    });

    $(".shop-cart-htext1").click(function () {
        $(".scart-total-text2").toggleClass("hide");
        $(".scart-total-text3").toggleClass("hide");
        $(".scart-total-text4").toggleClass("hide");
        $(".delete").toggleClass("hide");
        TotalPrice();
    });

    $(".delete").click(function () {
        if ($("#ckAll").is(':checked')) {
            $(".btn2:checked").parent(".shop-cart-check2").parent(".index-goods").remove();
            TotalPrice();
        }
        // if ($(".btn1").is(':checked')) {
        //     $(".btn1:checked").closest(".shop-cart-listbox1").remove();
        //     TotalPrice();
        // }
        // if ($(".btn2").is(':checked')) {
        //     $(".btn2:checked").parent(".shop-cart-check2").parent(".index-goods").remove();
        //     TotalPrice();
        // }
    });

    function TotalPrice() {
        var allprice = 0; //总价
        $(".shop-cart-listbox1").each(function () { //循环每个店铺
            var oprice = 0; //店铺总价
            $(this).find(".btn2").each(function () { //循环店铺里面的商品
                if ($(this).is(":checked")) { //如果该商品被选中
                    var num = $(this).parents(".index-goods").find(".shop-cart-numer").val(); //得到商品的数量
                    var price = parseFloat($(this).parents(".index-goods").find(".priceJs").text()); //得到商品的单价
                    var total = price * num; //计算单个商品的总价
                    oprice += total; //计算该店铺的总价
                }
                $(this).closest(".shop-cart-listbox1").find(".ShopTotal").text(oprice.toFixed(2)); //显示被选中商品的店铺总价
            });
            var oneprice = parseFloat($(this).find(".ShopTotal").text()); //得到每个店铺的总价
            allprice += oneprice; //计算所有店铺的总价
        });
        $("#AllTotal").text(allprice.toFixed(2)); //输出全部总价
    }

    var member = localStorage.getItem("member");
    if (member == null) {
        mlayer.login({
            btn: ['我再看看', '立即登陆'],
            yes: function (index) {
                location.href = "/login.html"
                mlayer.close(index);
            }
        });
    } else {

        var index = mlayer.loading({
            title: '加载中'
        });
        CoreUtil.sendPost("/api/cart/getCart", {"memberId": JSON.parse(member).id}, function (res) {
            if (res.code == 200) {
                var v = "";
                if (res.data.cart.length > 0) {
                    for (var cart of res.data.cart) {
                        v +=
                            "<div class='index-goods'>" +
                            "<span class='shop-cart-check2'><input type='checkbox' name='sub2' class='shopcart-input1 btn2'></span>" +
                            "<span class='index-goods-img'><a href='goods-details.html?productId=" + cart.productId + "'><img src='" + cart.productVo.imgUrl + "'></a></span>" +
                            "<div class='index-goods-textbox'>" +
                            "<span class='index-goods-text1'>" + cart.productVo.productName + "</span>" +
                            "<div class='index-goods-text2'>￥<i class='priceJs'>" + cart.productVo.mallMobilePrice + "</i></div>" +
                            "<div class='shop-cart-box3'>" +
                            "<span class='shop-cart-subtract' productId=" + cart.productId + " id=" + cart.id + "></span>" +
                            "<input type='tel' size='4' value='" + cart.number + "' class='shop-cart-numer' >" +
                            "<span class='shop-cart-add' productId=" + cart.productId + " id=" + cart.id + "></span>" +
                            "</div>" +
                            "</div>" +
                            "</div>";
                    }
                    $(".shop-cart-box2").after(v)
                }

            } else {
                mlayer.error({
                    title: res.msg,
                    time: 3
                });
            }
            mlayer.close(index);
        });
    }

});