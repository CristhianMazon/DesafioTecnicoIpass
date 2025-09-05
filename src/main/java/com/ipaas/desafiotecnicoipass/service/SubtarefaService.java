package com.ipaas.desafiotecnicoipass.service;

import com.ipaas.desafiotecnicoipass.dto.SubtarefaRequest;
import com.ipaas.desafiotecnicoipass.dto.SubtarefaStatusRequest;
import com.ipaas.desafiotecnicoipass.exception.RecursoNaoEncontradoException;
import com.ipaas.desafiotecnicoipass.model.Subtarefa;
import com.ipaas.desafiotecnicoipass.model.StatusTarefa;
import com.ipaas.desafiotecnicoipass.model.Tarefa;
import com.ipaas.desafiotecnicoipass.repository.SubtarefaRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SubtarefaService {

    private final SubtarefaRepository subtarefaRepository;
    private final TarefaService tarefaService;

    public SubtarefaService(SubtarefaRepository subtarefaRepository, TarefaService tarefaService) {
        this.subtarefaRepository = subtarefaRepository;
        this.tarefaService = tarefaService;
    }

    public Subtarefa criarSubtarefa(UUID tarefaId, SubtarefaRequest subtarefaRequest) {
        Tarefa tarefa = tarefaService.buscarPorId(tarefaId);

        Subtarefa subtarefa = new Subtarefa();
        subtarefa.setTitulo(subtarefaRequest.getTitulo());
        subtarefa.setDescricao(subtarefaRequest.getDescricao());
        subtarefa.setTarefa(tarefa);
        subtarefa.setStatus(StatusTarefa.PENDENTE);
        subtarefa.setDataCriacao(LocalDateTime.now());

        return subtarefaRepository.save(subtarefa);
    }
    
    public List<Subtarefa> listarSubtarefas(UUID tarefaId) {
        return subtarefaRepository.findByTarefaId(tarefaId);
    }

    public Subtarefa atualizarStatusSubtarefa(UUID id, SubtarefaStatusRequest statusRequest) {
        Subtarefa subtarefa = subtarefaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Subtarefa não encontrada com ID: " + id));
        
        if (statusRequest.getStatus() == StatusTarefa.CONCLUIDA) {
            subtarefa.setDataConclusao(LocalDateTime.now());
        } else {
            subtarefa.setDataConclusao(null);
        }
        
        subtarefa.setStatus(statusRequest.getStatus());

        return subtarefaRepository.save(subtarefa);
    }
}