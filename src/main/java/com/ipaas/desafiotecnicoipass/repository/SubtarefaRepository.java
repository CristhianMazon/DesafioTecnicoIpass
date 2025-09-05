package com.ipaas.desafiotecnicoipass.repository;

import com.ipaas.desafiotecnicoipass.model.Subtarefa;
import com.ipaas.desafiotecnicoipass.model.StatusTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SubtarefaRepository extends JpaRepository<Subtarefa, UUID> {
    List<Subtarefa> findByTarefaId(UUID tarefaId);
    List<Subtarefa> findByTarefaIdAndStatus(UUID tarefaId, StatusTarefa status);
}