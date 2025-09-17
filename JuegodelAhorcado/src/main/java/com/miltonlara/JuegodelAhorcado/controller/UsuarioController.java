package com.miltonlara.JuegodelAhorcado.controller;

import com.miltonlara.JuegodelAhorcado.model.Usuario;
import com.miltonlara.JuegodelAhorcado.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable("id") Integer idUsuario) {
        return usuarioService.getUsuarioById(idUsuario);
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable("id") Integer idUsuario, @RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(idUsuario, usuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable("id") Integer idUsuario) {
        usuarioService.deleteUsuario(idUsuario);
    }
}
