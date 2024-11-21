package com.proyectoPrueba.gestionUsuario.advice;

import com.proyectoPrueba.gestionUsuario.exceptions.NoUsuariosEcontradosException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoUsuariosEcontradosException.class)
    public ResponseEntity<String> handleNoUsuarioEcontradosException (NoUsuariosEcontradosException ex){
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }
}
