package com.ipaas.desafiotecnicoipass.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class TarefaRequest {

    @NotBlank(message = "O título da tarefa é obrigatório")
    private String titulo;

    private String descricao;

    @NotNull(message = "O ID do usuário é obrigatório")
    private UUID usuarioId;

    // Construtor, Getters e Setters
    // ...
}