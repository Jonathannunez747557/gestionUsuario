package com.proyectoPrueba.gestionUsuario.controller;

import com.proyectoPrueba.gestionUsuario.entity.Usuario;
import com.proyectoPrueba.gestionUsuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Usuario usuario = usuarioService.findUsuarioById(id);
        return ResponseEntity.ok(usuario);

    }

    @GetMapping("/menor-que/{age}")
    public ResponseEntity <List<Usuario>>finByAgeLessThan (@PathVariable int age){
        List <Usuario> usuariosByAge = usuarioService.findByEdadlessThan(age);
        if (usuariosByAge.isEmpty()){
           return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuariosByAge);
    }

    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario){
        return usuarioService.saveUsuario(usuario);
    }


    @PostMapping ("/cargar-multiples")
    public List<Usuario> cargaMultiplesUsuaarios(@RequestBody List<Usuario> usuarios){
        return usuarioService.cargarMultiplesUsuario(usuarios);
    }
    @PutMapping ("/{id}")
    public ResponseEntity<Usuario>updateUsuario(@PathVariable Long id , @RequestBody Usuario usuario ){
        Usuario updateUsuario = usuarioService.updateUsuario(id, usuario);
        return ResponseEntity.ok(updateUsuario);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario>deleteUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/eliminar-todos")
    public ResponseEntity<String>DeleteAllUsuarios (){
        return ResponseEntity.ok(usuarioService.deleteAllUsuarios());
    }

}
