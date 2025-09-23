package com.miltonlara.JuegodelAhorcado.service;

import com.miltonlara.JuegodelAhorcado.model.Usuario;
import com.miltonlara.JuegodelAhorcado.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImplements implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImplements(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        validarUsuario(usuario);

        if (usuarioRepository.existsByUsuario(usuario.getUsuario())) {
            throw new RuntimeException("El usuario ya existe");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(Integer idUsuario, Usuario usuario) {
        validarUsuario(usuario);

        Usuario existingUsuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + idUsuario));

        if (usuarioRepository.existsByUsuarioAndIdUsuarioNot(usuario.getUsuario(), idUsuario)) {
            throw new RuntimeException("El usuario ya está en uso por otra cuenta");
        }

        existingUsuario.setUsuario(usuario.getUsuario());
        existingUsuario.setContrasena(usuario.getContrasena());

        return usuarioRepository.save(existingUsuario);
    }

    @Override
    public void deleteUsuario(Integer idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException("El usuario no existe");
        }
        usuarioRepository.deleteById(idUsuario);
    }

    // para que los campos no estenb vacios
    private void validarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new RuntimeException("El usuario no puede estar vacío");
        }
        if (usuario.getUsuario() == null || usuario.getUsuario().trim().isEmpty()) {
            throw new RuntimeException("El campo 'usuario' no puede estar vacío");
        }
        if (usuario.getContrasena() == null || usuario.getContrasena().trim().isEmpty()) {
            throw new RuntimeException("La contraseña no puede estar vacía");
        }
    }
}
