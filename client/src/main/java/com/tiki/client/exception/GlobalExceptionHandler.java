package com.tiki.client.exception;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class)
    public String handleException(Exception e, HttpServletResponse response)throws Exception {
        Map<String,String> map =new HashMap<>();
        map.put("message",e.getMessage());
        return "/login";
    }
}
