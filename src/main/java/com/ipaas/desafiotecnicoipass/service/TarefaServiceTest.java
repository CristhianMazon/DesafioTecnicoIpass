package com.ipaas.desafiotecnicoipass.service;

import com.ipaas.desafiotecnicoipass.dto.TarefaRequest;
import com.ipaas.desafiotecnicoipass.dto.TarefaStatusRequest;
import com.ipaas.desafiotecnicoipass.exception.RecursoNaoEncontradoException;
import com.ipaas.desafiotecnicoipass.model.StatusTarefa;
import com.ipaas.desafiotecnicoipass.model.Subtarefa;
import com.ipaas.desafiotecnicoipass.model.Tarefa;
import com.ipaas.desafiotecnicoipass.model.Usuario;
import com.ipaas.desafiotecnicoipass.repository.SubtarefaRepository;
import com.ipaas.desafiotecnicoipass.repository.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private SubtarefaRepository subtarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    private Tarefa tarefa;
    private Usuario usuario;
    private TarefaRequest tarefaRequest;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(UUID.randomUUID());

        tarefa = new Tarefa();
        tarefa.setId(UUID.randomUUID());
        tarefa.setTitulo("Fazer compras");
        tarefa.setUsuario(usuario);
        tarefa.setStatus(StatusTarefa.PENDENTE);
        tarefa.setDataCriacao(LocalDateTime.now());

        tarefaRequest = new TarefaRequest();
        tarefaRequest.setTitulo("Fazer compras");
        tarefaRequest.setUsuarioId(usuario.getId());
    }

    @Test
    void criarTarefaComSucesso() {
        when(usuarioService.buscarPorId(any(UUID.class))).thenReturn(usuario);
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        Tarefa novaTarefa = tarefaService.criarTarefa(tarefaRequest);

        assertNotNull(novaTarefa);
        assertEquals(tarefa.getTitulo(), novaTarefa.getTitulo());
        assertEquals(StatusTarefa.PENDENTE, novaTarefa.getStatus());
        assertNotNull(novaTarefa.getDataCriacao());
        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void atualizarStatusTarefaParaConcluidaComSubtarefasPendentesLancaExcecao() {
        TarefaStatusRequest statusRequest = new TarefaStatusRequest();
        statusRequest.setStatus(StatusTarefa.CONCLUIDA);

        Subtarefa subtarefaPendente = new Subtarefa();
        subtarefaPendente.setStatus(StatusTarefa.PENDENTE);
        when(tarefaRepository.findById(tarefa.getId())).thenReturn(Optional.of(tarefa));
        when(subtarefaRepository.findByTarefaId(tarefa.getId())).thenReturn(List.of(subtarefaPendente));

        assertThrows(IllegalStateException.class, () -> {
            tarefaService.atualizarStatusTarefa(tarefa.getId(), statusRequest);
        });
        verify(tarefaRepository, never()).save(any(Tarefa.class));
    }

    @Test
    void atualizarStatusTarefaParaConcluidaSemSubtarefasLancaExcecao() {
        TarefaStatusRequest statusRequest = new TarefaStatusRequest();
        statusRequest.setStatus(StatusTarefa.CONCLUIDA);

        when(tarefaRepository.findById(tarefa.getId())).thenReturn(Optional.of(tarefa));
        when(subtarefaRepository.findByTarefaId(tarefa.getId())).thenReturn(Collections.emptyList());

        assertThrows(IllegalStateException.class, () -> {
            tarefaService.atualizarStatusTarefa(tarefa.getId(), statusRequest);
        });
        verify(tarefaRepository, never()).save(any(Tarefa.class));
    }


    @Test
    void buscarTarefaInexistenteLancaExcecao() {
        when(tarefaRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> {
            tarefaService.buscarPorId(UUID.randomUUID());
        });
    }
}