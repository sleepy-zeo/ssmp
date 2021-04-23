$(function () {

    initClassify();

    // 获取window的引用:
    var $window = $(window);
    // 获取包含data-src属性的img，并以jQuery对象存入数组:
    var lazyImgs = $.map($('.classify-box1 img').get(), function (i) {
        return $(i);
    });
    // 定义事件函数:
    var onScroll = function () {
        // 获取页面滚动的高度:  scrollTop()获取匹配元素相对滚动条顶部的偏移。
        var wtop = $window.scrollTop();//页面滚动的高度就是窗口顶部与文档顶部之间的距离，也就是滚动条滚动的距离
        // 判断是否还有未加载的img:
        if (lazyImgs.length > 0) {
            // 获取可视区域高度:
            var wheight = $window.height();
            // 存放待删除的索引:
            var loadedIndex = [];
            // 循环处理数组的每个img元素:
            $.each(lazyImgs, function ($i, index) {
                // 判断是否在可视范围内:
                if ($i.offset().top - wtop < wheight) {  //$.offset().top获取匹配元素距离文本文档顶的距离。
                    // 设置src属性:
                    $i.attr('src', $i.attr('data-src'));
                    // 添加到待删除数组:
                    loadedIndex.unshift(index);//从大到小排序，保证下边删除操作能顺利进行
                }
            });
            // 删除已处理的对象:
            $.each(loadedIndex, function (index) {
                lazyImgs.splice(index, 1);
            });
        }
    };
    // 绑定事件:
    $window.scroll(onScroll);
    // 手动触发一次:
    onScroll();

    $(".shop-cart-add").click(function () {
        var multi = 0;
        var vall = $(this).prev().val();
        vall++;
        $(this).prev().val(vall);
        TotalPrice();
    });
    $(".shop-cart-subtract").click(function () {
        var multi = 0;
        var vall = $(this).next().val();
        vall--;
        if (vall <= 0) {
            vall = 0;
        }
        $(this).next().val(vall);
        TotalPrice();
    });
});

function tab(index, catId) {
    switab('.classify-text1', '.content2', 'pitch-on2', '', index, catId);
}

function switab(tab, con, tab_c_css, tab_n_css, no, catId) {
    $(tab).each(function (i) {
        if (i == no) {
            $(this).addClass(tab_c_css);
        } else {
            $(this).removeClass(tab_c_css);
            $(this).addClass(tab_n_css);
        }
    })
    var index = mlayer.loading({
        title: '加载中'
    });
    $.ajax({
        type: "post",
        url: "/api/product/getProductByCateId",
        data: {"catId": catId},
        dataType: "json",
        success: function (data) {
            var html = "";
            $.each(data.data, function (commentIndex, comment) {
                html += "<div class='classify-box1'>";
                html += "<a href='goods-details.html?productId=" + comment['id'] + "' class='classify-box1-img1'><img src='" + comment['imgUrl'] + "' src='/images/loading.gif' alt=''></a>";
                html += "<div class='classify-box2'>";
                html += "<a href='goods-details.html?productId=" + comment['id'] + "' class='classify-box2-text1'>" + comment['productName'] + "</a>";
                html += "<span class='classify-box2-text2'>" + comment['mallMobilePrice'] + "</span>";
                html += "<div class='shop-cart-box3'>";
                html += "</div>";
                html += "</div>";
                html += "</div>";
            });
            $('#productContaia').html(html);
            mlayer.close(index);
        }
    });

}

function initClassify() {
    $.ajax({
        type: "post",
        url: "/api/product/getProductCate",
        dataType: "json",
        success: function (data) {
            var html = "<div class='classify-perch'></div>";
            $.each(data.data, function (commentIndex, comment) {
                if (commentIndex == 0) {
                    tab(commentIndex, comment['catId']);
                    html += "<span class='classify-text1 pitch-on2' onclick='tab(" + commentIndex + "," + comment['catId'] + ")'>" + comment['catName'] + "</span>";
                } else {
                    html += "<span class='classify-text1' onclick='tab(" + commentIndex + "," + comment['catId'] + ")'>" + comment['catName'] + "</span>";
                }
            });
            html += " <div class='classify-perch2'></div>";
            $('#initClassify').html(html);
        }
    });
}
