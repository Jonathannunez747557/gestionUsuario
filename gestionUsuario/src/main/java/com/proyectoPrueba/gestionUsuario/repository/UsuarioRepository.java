package com.proyectoPrueba.gestionUsuario.repository;

import com.proyectoPrueba.gestionUsuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository  extends JpaRepository<Usuario , Long  > {

    List<Usuario> findByAgeLessThan(int age);
}
