package com.proyectoPrueba.gestionUsuario.advice;

import com.proyectoPrueba.gestionUsuario.exceptions.NoUsuariosEcontradosException;
import com.proyectoPrueba.gestionUsuario.exceptions.UsuarioInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>handlevalidatorException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()){
            errors.put(error.getField(),error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors); // Devuelve un HTTP 400 con los errores
    }

    @ExceptionHandler(NoUsuariosEcontradosException.class)
    public ResponseEntity<String> handleNoUsuarioEcontradosException (NoUsuariosEcontradosException ex){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIlegalArgumentoException(IllegalAccessException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(UsuarioInvalidoException.class)
    public ResponseEntity<String> handleUsuarioInvalidoException(UsuarioInvalidoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
