package com.ipaas.desafiotecnicoipass.service;

import com.ipaas.desafiotecnicoipaas.dto.UsuarioRequest;
import com.ipaas.desafiotecnicoipaas.exception.EmailJaExisteException;
import com.ipaas.desafiotecnicoipaas.exception.RecursoNaoEncontradoException;
import com.ipaas.desafiotecnicoipaas.model.Usuario;
import com.ipaas.desafiotecnicoipaas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criarUsuario(UsuarioRequest usuarioRequest) {
        usuarioRepository.findByEmail(usuarioRequest.getEmail())
            .ifPresent(u -> {
                throw new EmailJaExisteException("O e-mail " + u.getEmail() + " já está em uso.");
            });

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setEmail(usuarioRequest.getEmail());

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(UUID id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com ID: " + id));
    }
}