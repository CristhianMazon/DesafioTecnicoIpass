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

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }
}