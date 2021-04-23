package com.zxjia.ssmp.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <pre>
 * 出错页面控制器
 * Created by Binary Wang on 2018/8/25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/404")
    @ResponseBody
    public String error404() {
        return HttpStatus.NOT_FOUND.getReasonPhrase();
    }

    @RequestMapping("/405")
    @ResponseBody
    public String error405() {
        return HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase();
    }

    @RequestMapping("/500")
    @ResponseBody
    public String error500() {
        return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    }

}
