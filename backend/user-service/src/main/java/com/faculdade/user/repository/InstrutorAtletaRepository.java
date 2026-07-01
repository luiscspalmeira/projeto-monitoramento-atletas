package com.faculdade.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.faculdade.user.entity.InstrutorAtleta;

public interface InstrutorAtletaRepository
        extends JpaRepository<InstrutorAtleta, Long> {

    List<InstrutorAtleta> findByInstrutorId(Long instrutorId);

    List<InstrutorAtleta> findByAtletaId(Long atletaId);

}