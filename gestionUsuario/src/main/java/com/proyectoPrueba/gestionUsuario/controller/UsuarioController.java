package com.proyectoPrueba.gestionUsuario.controller;

import com.proyectoPrueba.gestionUsuario.entity.Usuario;
import com.proyectoPrueba.gestionUsuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
 @RequestMapping("api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> findAllUsuarios(){
        return usuarioService.findAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario>findUsuarioById(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioService.findUsuarioById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(( ) -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario){
        return usuarioService.saveUsuario(usuario);
    }
    @PutMapping ("/{id}")
    public ResponseEntity<Usuario>updateUsuario(@PathVariable Long id , @RequestBody Usuario usuario ){

        Optional<Usuario> existingUsuario = usuarioService.findUsuarioById(id); //verifica si el usuario po ID existe antes de actualizarlo
        if(existingUsuario.isPresent()){
            usuario.setId(id);
            Usuario updateUsuario = usuarioService.saveUsuario(usuario);
            return ResponseEntity.ok(updateUsuario);//Restorna un 200 ok con el usuario actualizado
        }else {
            return ResponseEntity.notFound().build();//Retorna un estado 404 Not Found si el usuario no existe.
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario>deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
