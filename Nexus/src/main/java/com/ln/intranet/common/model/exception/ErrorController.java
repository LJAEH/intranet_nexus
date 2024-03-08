package com.ln.intranet.common.model.exception;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class ErrorController {
    
    @RequestMapping("/handle404")
    public String hand404leError(HttpServletRequest request, HttpServletResponse response) {
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
    
    @RequestMapping("/handle500")
    public String handle500Error(HttpServletRequest request, HttpServletResponse response) {
        String referer = request.getHeader("referer");
        return "redirect:" + referer;
    }
}
