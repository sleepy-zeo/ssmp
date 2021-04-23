package com.zxjia.ssmp.exception;

import com.zxjia.ssmp.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultVO handleServiceException(BusinessException e) {
        return ResultVO.systemError(e.getMessage());
    }

}
