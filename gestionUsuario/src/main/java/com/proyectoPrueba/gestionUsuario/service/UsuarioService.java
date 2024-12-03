package com.proyectoPrueba.gestionUsuario.service;

import com.proyectoPrueba.gestionUsuario.entity.Usuario;
import com.proyectoPrueba.gestionUsuario.exceptions.NoUsuariosEcontradosException;
import com.proyectoPrueba.gestionUsuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private UsuarioValidator usuarioValidator;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository,UsuarioValidator usuarioValidator){
        this.usuarioRepository = usuarioRepository;
        this.usuarioValidator = usuarioValidator;
    }

    public List<Usuario>findAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public List<Usuario> findByEdadlessThan(int age){
        List <Usuario> usuarios = usuarioRepository.findByAgeLessThan(age);
        if (usuarios.isEmpty()){
            throw new NoUsuariosEcontradosException("No se encontraron usuarios con la edad menor a :"+age+" en la base de datos.");
        }
        return usuarios;
    }


    public List<Usuario>cargarMultiplesUsuario(List<Usuario>usuarios){
        return usuarioRepository.saveAll(usuarios);
    }

    public Usuario findUsuarioById (long id ){

        return  usuarioRepository.findById(id).orElseThrow(()-> new NoUsuariosEcontradosException("No se econtro el usuario con ID: "+id));
    }

    public Usuario saveUsuario(Usuario usuario){
        usuarioValidator.validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario (long id, Usuario usuario){
        Usuario usuarioExistente = findUsuarioById(id);
        usuarioExistente.setName(usuario.getName());
        usuarioExistente.setAge(usuario.getAge());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioValidator.validarUsuario(usuarioExistente);
        return usuarioRepository.save(usuarioExistente);

    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public String deleteAllUsuarios(){
        usuarioRepository.deleteAll();
        return "Todos los usuarios han sido eliminados de la base de datos.";
    }

    }

