package com.ipaas.desafiotecnicoipass.controller;

import com.ipaas.desafiotecnicoipass.dto.UsuarioRequest;
import com.ipaas.desafiotecnicoipass.model.Usuario;
import com.ipaas.desafiotecnicoipass.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuarioRequest);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable UUID id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }
}