package com.proyectoPrueba.gestionUsuario.repository;

import com.proyectoPrueba.gestionUsuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario , Long  > {
}
