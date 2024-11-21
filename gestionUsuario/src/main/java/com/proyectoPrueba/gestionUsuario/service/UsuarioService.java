package com.proyectoPrueba.gestionUsuario.service;

import com.proyectoPrueba.gestionUsuario.entity.Usuario;
import com.proyectoPrueba.gestionUsuario.exceptions.NoUsuariosEcontradosException;
import com.proyectoPrueba.gestionUsuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario>findAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public List<Usuario>finbyEdadlessThan (int age){
        List <Usuario> usuarios = usuarioRepository.findByAgeLessThan(age);
        if (usuarios.isEmpty()){
            throw new NoUsuariosEcontradosException("No se encontraron usuarios con la edad menor a :"+age+" en la base de datos.");
        }
        return usuarios;
    }


    public List<Usuario>cargarMultiplesUsuario(List<Usuario>usuarios){
        return usuarioRepository.saveAll(usuarios);
    }

    public Optional<Usuario>findUsuarioById (Long id ){
        return  usuarioRepository.findById(id);
    }
    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public String deleteAllUsuarios(){
        usuarioRepository.deleteAll();
        return "Todos los usuarios han sido eliminados de la base de datos.";
    }

    }

