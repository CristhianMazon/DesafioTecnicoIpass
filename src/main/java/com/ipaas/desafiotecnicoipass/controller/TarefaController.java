package com.ipaas.desafiotecnicoipass.controller;

import com.ipaas.desafiotecnicoipaas.dto.TarefaRequest;
import com.ipaas.desafiotecnicoipaas.dto.TarefaStatusRequest;
import com.ipaas.desafiotecnicoipaas.model.StatusTarefa;
import com.ipaas.desafiotecnicoipaas.model.Tarefa;
import com.ipaas.desafiotecnicoipaas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    // Injeção de dependência do serviço
    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@Valid @RequestBody TarefaRequest tarefaRequest) {
        Tarefa novaTarefa = tarefaService.criarTarefa(tarefaRequest);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas(@RequestParam(required = false) StatusTarefa status) {
        List<Tarefa> tarefas = tarefaService.listarTarefasPorStatus(status);
        return ResponseEntity.ok(tarefas);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Tarefa> atualizarStatusTarefa(@PathVariable UUID id, @Valid @RequestBody TarefaStatusRequest statusRequest) {
        Tarefa tarefaAtualizada = tarefaService.atualizarStatusTarefa(id, statusRequest);
        return ResponseEntity.ok(tarefaAtualizada);
    }
}