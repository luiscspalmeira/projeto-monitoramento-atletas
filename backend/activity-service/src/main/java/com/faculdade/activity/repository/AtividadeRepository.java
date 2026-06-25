package com.faculdade.activity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.activity.entity.Atividade;

public interface AtividadeRepository
        extends JpaRepository<Atividade, Long> {

    List<Atividade> findByAtletaId(Long atletaId);
}