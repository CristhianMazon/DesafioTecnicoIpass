package com.ipaas.desafiotecnicoipass.service;

import com.ipaas.desafiotecnicoipaas.dto.TarefaRequest;
import com.ipaas.desafiotecnicoipaas.dto.TarefaStatusRequest;
import com.ipaas.desafiotecnicoipaas.exception.RecursoNaoEncontradoException;
import com.ipaas.desafiotecnicoipaas.model.StatusTarefa;
import com.ipaas.desafiotecnicoipaas.model.Tarefa;
import com.ipaas.desafiotecnicoipaas.model.Usuario;
import com.ipaas.desafiotecnicoipaas.repository.TarefaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final UsuarioService usuarioService;

    public TarefaService(TarefaRepository tarefaRepository, UsuarioService usuarioService) {
        this.tarefaRepository = tarefaRepository;
        this.usuarioService = usuarioService;
    }

    public Tarefa criarTarefa(TarefaRequest tarefaRequest) {
        Usuario usuario = usuarioService.buscarPorId(tarefaRequest.getUsuarioId());

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(tarefaRequest.getTitulo());
        tarefa.setDescricao(tarefaRequest.getDescricao());
        tarefa.setUsuario(usuario);
        tarefa.setStatus(StatusTarefa.PENDENTE);
        tarefa.setDataCriacao(LocalDateTime.now());

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefasPorStatus(StatusTarefa status) {
        if (status == null) {
            return tarefaRepository.findAll();
        }
        return tarefaRepository.findByStatus(status);
    }

    public Tarefa atualizarStatusTarefa(UUID id, TarefaStatusRequest statusRequest) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tarefa não encontrada com ID: " + id));

        if (statusRequest.getStatus() == StatusTarefa.CONCLUIDA) {
            tarefa.setDataConclusao(LocalDateTime.now());
        } else {
            tarefa.setDataConclusao(null);
        }

        tarefa.setStatus(statusRequest.getStatus());

        return tarefaRepository.save(tarefa);
    }
}