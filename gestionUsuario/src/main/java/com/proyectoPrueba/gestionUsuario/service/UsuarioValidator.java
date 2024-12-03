package com.proyectoPrueba.gestionUsuario.service;

import com.proyectoPrueba.gestionUsuario.entity.Usuario;
import com.proyectoPrueba.gestionUsuario.exceptions.NoUsuariosEcontradosException;
import com.proyectoPrueba.gestionUsuario.exceptions.UsuarioInvalidoException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
public class UsuarioValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    public void validarUsuario(Usuario usuario) {

        if (usuario.getName() == null || usuario.getName().isEmpty()) {
            throw new UsuarioInvalidoException("El nombre no debe estar vacio.");


        }

        if (usuario.getAge()<18 || usuario.getAge()>100){
            throw new IllegalArgumentException("La edad debe estar entre 18 y 100.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty() ){
            throw  new UsuarioInvalidoException("El Email del Usuario no debe estar vacio");
        }

        if (!EMAIL_PATTERN.matcher(usuario.getEmail()).matches()){
            throw  new UsuarioInvalidoException("El mail ingresado no tiene un formato válido.  ");
        }
    }

    public  void busquedaUsuario(Usuario usuario){
        if (usuario.getId() == null){
           throw  new NoUsuariosEcontradosException("El usuario con el ID especificado no existe.");
        }

    }



}
