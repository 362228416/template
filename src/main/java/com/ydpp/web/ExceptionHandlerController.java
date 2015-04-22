package com.ydpp.web;

import com.ydpp.exception.ACException;
import com.ydpp.utils.JsonUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * 全局异常处理
 * Created by 16 on 2015/4/20.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * 无权限访问异常处理
     * @param runtimeException
     * @return
     */
    @ExceptionHandler(ACException.class)
    public String runtimeExceptionHandler(ACException runtimeException, PrintWriter out) {
        int err = runtimeException.getCode();
        switch (err) {
            case 401:
                return "redirect:/login";
        }
        Map model = new TreeMap();
        model.put("success", false);
        model.put("msg", runtimeException.getMessage());
        out.write(JsonUtils.toJson(model));
        return null;
    }


}
