package com.datacorp.sistemainventario.controller;

import com.datacorp.sistemainventario.dto.ErrorResponse;
import com.datacorp.sistemainventario.exceptions.MiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(MiException.class)
    public ResponseEntity<ErrorResponse> handleMiException(MiException ex){
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(),"CATEGORY_ALREADY_EXISTS_V2");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
