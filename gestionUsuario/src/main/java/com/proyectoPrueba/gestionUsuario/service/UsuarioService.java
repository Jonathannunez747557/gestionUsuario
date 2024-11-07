package com.proyectoPrueba.gestionUsuario.service;

import com.proyectoPrueba.gestionUsuario.entity.Usuario;
import com.proyectoPrueba.gestionUsuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Usuario>findUsuarioById (Long id ){
        return  usuarioRepository.findById(id);
    }
    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    }

