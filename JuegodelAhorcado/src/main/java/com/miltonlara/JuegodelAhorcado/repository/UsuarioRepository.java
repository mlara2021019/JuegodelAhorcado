package com.miltonlara.JuegodelAhorcado.repository;

import com.miltonlara.JuegodelAhorcado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Método para verificar si un usuario ya existe por nombre de usuario
    boolean existsByUsuario(String usuario);

    // Método para verificar si un usuario existe con otro ID diferente (útil para actualizaciones)
    boolean existsByUsuarioAndIdUsuarioNot(String usuario, Integer idUsuario);
}
