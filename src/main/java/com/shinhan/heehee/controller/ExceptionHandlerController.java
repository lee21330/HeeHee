package com.shinhan.heehee.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.shinhan.heehee.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

	Logger logger = LoggerFactory.getLogger("ExceptionHandlerController.class");
	
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

	@ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(HttpServletRequest request, NoHandlerFoundException ex) {
        logger.warn("=====404 에러입니다.======");
        logger.warn("요청 URL: " + request.getRequestURL());
        logger.warn(ex.getMessage());
        return "error/404error";
    }

    @ExceptionHandler(Exception.class)
    public String handle500(HttpServletRequest request, Exception ex) {
        logger.warn("=====500 에러입니다.======");
        logger.warn(ex.getClass().getPackageName());
        logger.warn(ex.getClass().getSimpleName());
        logger.warn(ex.getMessage());
        ex.printStackTrace();
        return "error/500error";
    }
	
}
