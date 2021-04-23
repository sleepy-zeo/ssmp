/*工具类*/
var CoreUtil = (function () {
    var coreUtil = {};

    /*GET*/
    coreUtil.sendGet = function (url, params, ft) {
        this.sendAJAX(url, params, ft, "GET")
    }

    /*POST*/
    coreUtil.sendPost = function (url, params, ft) {
        this.sendAJAX(url, JSON.stringify(params), ft, "POST")
    }
    /*PUT*/
    coreUtil.sendPut = function (url, params, ft) {
        this.sendAJAX(url, JSON.stringify(params), ft, "PUT")
    }
    /*DELETE*/
    coreUtil.sendDelete = function (url, params, ft) {
        this.sendAJAX(url, JSON.stringify(params), ft, "DELETE")
    }

    /*ajax*/
    coreUtil.sendAJAX = function (url, params, ft, method) {
        $.ajax({
            url: url,
            cache: false,
            async: true,
            data: params,
            type: method,
            dataType: "json",
            contentType: "application/json",
            success: function (res) {
                if (res.code == 200) {
                    if (ft != null && ft != undefined) {
                        ft(res);
                    }
                } else {
                    mlayer.alert({
                        title: '消息',
                        content: res.msg,
                        btn: 'OK'
                    });
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                mlayer.alert({
                    title: '消息',
                    content: textStatus,
                    btn: 'OK'
                });
            }
        })
    }

    //判断字符是否为空的方法
    coreUtil.isEmpty = function (obj, message) {
        if (typeof obj == "undefined" || obj == null || obj == "") {
            mlayer.alert({
                title: '消息',
                content: message,
                btn: 'OK'
            });
            return true;
        } else {
            return false;
        }
    }

    coreUtil.getQueryVariable = function (obj) {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            if (pair[0] == obj) {
                return pair[1];
            }
        }
        return (false);
    }

    //字典数据回显
    coreUtil.selectDictLabel = function (datas, value) {
        datas = JSON.parse(datas);
        var label = "";
        $.each(datas, function (index, dict) {
            if (dict.value == ('' + value)) {
                label = dict.label;
                return false;
            }
        });
        //匹配不到，返回未知
        if (CoreUtil.isEmpty(label)) {
            return "未知";
        }
        return label;
    }


    return coreUtil;
})(CoreUtil, window);
