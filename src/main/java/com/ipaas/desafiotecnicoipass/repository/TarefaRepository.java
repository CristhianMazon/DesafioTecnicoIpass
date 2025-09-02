package com.ipaas.desafiotecnicoipass.repository;

import com.ipaas.desafiotecnicoipass.model.StatusTarefa;
import com.ipaas.desafiotecnicoipass.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {
    List<Tarefa> findByStatus(StatusTarefa status);
}