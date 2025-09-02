package com.ipaas.desafiotecnicoipass.dto;

import com.ipaas.desafiotecnicoipaas.model.StatusTarefa;
import jakarta.validation.constraints.NotNull;

public class TarefaStatusRequest {

    @NotNull(message = "O status é obrigatório")
    private StatusTarefa status;

    // Construtor, Getters e Setters
    // ...
}