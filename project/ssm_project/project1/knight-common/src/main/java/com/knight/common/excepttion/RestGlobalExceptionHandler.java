package com.knight.common.excepttion;

import com.knight.common.result.BaseServerResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(annotations = {RestController.class})
@Log4j2
public class RestGlobalExceptionHandler {
    public static Logger getLog() {
        return log;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseServerResponse defaultErrorHandler(HttpServletRequest request,Exception e) throws Exception{
        log.error("Exception ",e);
        return BaseServerResponse.createByErrorMessage("系统内部错误");
    }
}
