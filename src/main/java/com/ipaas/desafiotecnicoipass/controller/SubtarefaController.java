package com.ipaas.desafiotecnicoipass.controller;

import com.ipaas.desafiotecnicoipass.dto.SubtarefaRequest;
import com.ipaas.desafiotecnicoipass.dto.SubtarefaStatusRequest;
import com.ipaas.desafiotecnicoipass.model.Subtarefa;
import com.ipaas.desafiotecnicoipass.service.SubtarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
public class SubtarefaController {

    private final SubtarefaService subtarefaService;

    public SubtarefaController(SubtarefaService subtarefaService) {
        this.subtarefaService = subtarefaService;
    }

    @PostMapping("/tarefas/{tarefaId}/subtarefas")
    public ResponseEntity<Subtarefa> criarSubtarefa(
            @PathVariable UUID tarefaId,
            @Valid @RequestBody SubtarefaRequest subtarefaRequest) {
        Subtarefa novaSubtarefa = subtarefaService.criarSubtarefa(tarefaId, subtarefaRequest);
        return new ResponseEntity<>(novaSubtarefa, HttpStatus.CREATED);
    }

    @GetMapping("/tarefas/{tarefaId}/subtarefas")
    public ResponseEntity<List<Subtarefa>> listarSubtarefas(@PathVariable UUID tarefaId) {
        List<Subtarefa> subtarefas = subtarefaService.listarSubtarefas(tarefaId);
        return ResponseEntity.ok(subtarefas);
    }

    @PatchMapping("/subtarefas/{id}/status")
    public ResponseEntity<Subtarefa> atualizarStatusSubtarefa(
            @PathVariable UUID id,
            @Valid @RequestBody SubtarefaStatusRequest statusRequest) {
        Subtarefa subtarefaAtualizada = subtarefaService.atualizarStatusSubtarefa(id, statusRequest);
        return ResponseEntity.ok(subtarefaAtualizada);
    }
}