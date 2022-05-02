package com.ibm.academia.restapi.ruleta.excepcion.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ibm.academia.restapi.ruleta.excepcion.BadRequestException;
import com.ibm.academia.restapi.ruleta.excepcion.NotFoundExcepcion;



@ControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler(NotFoundExcepcion.class)
    public ResponseEntity<String> notFoundHandler(NotFoundExcepcion e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestHandler(BadRequestException e)
    {
    	return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

  


}