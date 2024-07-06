package com.shinhan.heehee.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.shinhan.heehee.exception.ProductNotFoundException;
import com.shinhan.heehee.exception.UserNotFoundException;
import com.shinhan.heehee.exception.AucListZeroException;

@ControllerAdvice
public class ExceptionHandlerController {

	Logger logger = LoggerFactory.getLogger("ExceptionHandlerController.class");

	
	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ProductNotFoundException.class)
    public String product404(HttpServletRequest request, ProductNotFoundException ex) {
        logger.warn("=====제품정보가 존재하지 않습니다.======");
        logger.warn("요청 URL: " + request.getRequestURL());
        logger.warn(ex.getMessage());
        return "error/404error";
    }
	/*
	@ExceptionHandler(ProductNotFoundException.class)
    public String product404(HttpServletRequest request, ProductNotFoundException ex) {
        logger.warn("=====제품정보가 존재하지 않습니다.======");
        logger.warn("요청 URL: " + request.getRequestURL());
        logger.warn(ex.getMessage());
        return "error/404error";
    }*/
	
	@ExceptionHandler(Exception.class)
	public String errorProcess500(HttpServletRequest request,Exception ex) {
		logger.warn("=====500에러입니다.======");
		logger.warn(ex.getClass().getPackageName());
		logger.warn(ex.getClass().getSimpleName());
		logger.warn(ex.getMessage());
		ex.printStackTrace();
		return "error/500error";
	}
	
	@ExceptionHandler(AucListZeroException.class)
	public void AucListZeroException(HttpServletRequest request,AucListZeroException ex) {
		logger.warn("=====현재 진행중인 경매가 없습니다.======");
	}
}
