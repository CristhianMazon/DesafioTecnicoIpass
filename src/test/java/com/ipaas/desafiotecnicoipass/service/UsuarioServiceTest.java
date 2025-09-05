package com.ipaas.desafiotecnicoipass.service;

import com.ipaas.desafiotecnicoipass.dto.UsuarioRequest;
import com.ipaas.desafiotecnicoipass.exception.EmailJaExisteException;
import com.ipaas.desafiotecnicoipass.exception.RecursoNaoEncontradoException;
import com.ipaas.desafiotecnicoipass.model.Usuario;
import com.ipaas.desafiotecnicoipass.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private UsuarioRequest usuarioRequest;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(UUID.randomUUID());
        usuario.setNome("João Silva");
        usuario.setEmail("joao.silva@email.com");

        usuarioRequest = new UsuarioRequest();
        usuarioRequest.setNome("João Silva");
        usuarioRequest.setEmail("joao.silva@email.com");
    }

    @Test
    void criarUsuarioComSucesso() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario novoUsuario = usuarioService.criarUsuario(usuarioRequest);

        assertNotNull(novoUsuario);
        assertEquals(usuario.getNome(), novoUsuario.getNome());
        assertEquals(usuario.getEmail(), novoUsuario.getEmail());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void criarUsuarioComEmailExistenteLancaExcecao() {
        when(usuarioRepository.findByEmail(anyString())).thenReturn(Optional.of(usuario));

        assertThrows(EmailJaExisteException.class, () -> {
            usuarioService.criarUsuario(usuarioRequest);
        });
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void buscarUsuarioExistentePorId() {
        when(usuarioRepository.findById(any(UUID.class))).thenReturn(Optional.of(usuario));

        Usuario usuarioEncontrado = usuarioService.buscarPorId(usuario.getId());

        assertNotNull(usuarioEncontrado);
        assertEquals(usuario.getId(), usuarioEncontrado.getId());
        verify(usuarioRepository, times(1)).findById(usuario.getId());
    }

    @Test
    void buscarUsuarioInexistentePorIdLancaExcecao() {
        when(usuarioRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> {
            usuarioService.buscarPorId(UUID.randomUUID());
        });
        verify(usuarioRepository, times(1)).findById(any(UUID.class));
    }
}