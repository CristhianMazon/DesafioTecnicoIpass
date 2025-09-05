package com.ipaas.desafiotecnicoipass.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class SubtarefaRequest {

    @NotBlank(message = "O título da subtarefa é obrigatório")
    private String titulo;

    private String descricao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}