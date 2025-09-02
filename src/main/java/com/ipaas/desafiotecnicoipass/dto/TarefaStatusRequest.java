package com.ipaas.desafiotecnicoipass.dto;

import com.ipaas.desafiotecnicoipass.model.StatusTarefa;
import jakarta.validation.constraints.NotNull;

public class TarefaStatusRequest {

    @NotNull(message = "O status é obrigatório")
    private StatusTarefa status;

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }
}