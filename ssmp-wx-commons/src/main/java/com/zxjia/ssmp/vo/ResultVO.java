package com.zxjia.ssmp.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class ResultVO<T> implements Serializable {

    /**
     * 请求响应code，200为成功 其他为失败
     */
    //@ApiModelProperty(value = "请求响应code，200为成功 其他为失败", name = "code")
    private Integer code;
    /**
     * 响应异常码详细信息
     */
    //@ApiModelProperty(value = "响应异常码详细信息", name = "msg")
    private String msg;
    //@ApiModelProperty(value = "需要返回的数据", name = "data")
    private T data;

    private String cosDomain = "https://shop-admin-1300029934.cos.ap-beijing.myqcloud.com";

    public ResultVO() {
        this.code = BaseResponseCode.SUCCESS.getCode();
        this.msg = BaseResponseCode.SUCCESS.getMsg();
        this.data = null;
    }

    public ResultVO(Integer code, String message) {
        this.code = code;
        this.msg = message;
        this.data = null;
    }

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public static String getImages() {
        return success().getCosDomain();
    }

    public static <T> ResultVO success() {
        return new ResultVO();
    }

    public static <T> ResultVO success(T data, String message) {
        return new ResultVO(BaseResponseCode.SUCCESS.getCode(), message, data);
    }

    public static <T> ResultVO success(T data) {
        return new ResultVO(BaseResponseCode.SUCCESS.getCode(), BaseResponseCode.SUCCESS.getMsg(), data);
    }

    public static <T> ResultVO success(String message) {
        return new ResultVO(BaseResponseCode.SUCCESS.getCode(), message);
    }

    public static <T> ResultVO systemError(String message) {
        return new ResultVO(BaseResponseCode.OPERATION_ERRO.getCode(), message);
    }

    enum BaseResponseCode implements ResponseCodeInterface {
        SUCCESS(200, "操作成功"),
        OPERATION_ERRO(404, "操作失败");

        /**
         * 错误码
         */
        private final int code;
        /**
         * 错误消息
         */
        private final String msg;

        BaseResponseCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }

    }

    interface ResponseCodeInterface {
        /**
         * 获取code
         *
         * @return code
         */
        int getCode();

        /**
         * 获取信息
         *
         * @return msg
         */
        String getMsg();
    }
}