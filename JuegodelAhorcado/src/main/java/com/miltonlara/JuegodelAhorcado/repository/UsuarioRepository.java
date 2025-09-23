package com.miltonlara.JuegodelAhorcado.repository;

import com.miltonlara.JuegodelAhorcado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //para ver si un usuario ya existe por nombre de usuario
    boolean existsByUsuario(String usuario);

    //para ver si un usuario existe con otro ID diferente es para actualizaciones
    boolean existsByUsuarioAndIdUsuarioNot(String usuario, Integer idUsuario);
}
