package com.tiki.client.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AllExceptionAdvice {


    @ExceptionHandler(Exception.class)
    public String errorException(Model model, Exception e){
        log.warn(e.getMessage());

        model.addAttribute("error","로그인하여야 이용할 수 있는 기능입니다.");
        return "member/login";
    }


}
