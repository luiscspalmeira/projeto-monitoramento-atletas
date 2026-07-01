package com.faculdade.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faculdade.user.dto.InstrutorAtletaRequest;
import com.faculdade.user.entity.InstrutorAtleta;
import com.faculdade.user.repository.InstrutorAtletaRepository;

@Service
public class InstrutorAtletaService {

    @Autowired
    private InstrutorAtletaRepository repository;

    public InstrutorAtleta vincular(
            InstrutorAtletaRequest request) {

        InstrutorAtleta relacao =
                new InstrutorAtleta();

        relacao.setInstrutorId(
                request.getInstrutorId());

        relacao.setAtletaId(
                request.getAtletaId());

        return repository.save(relacao);

    }

    public List<InstrutorAtleta> listarPorInstrutor(
            Long instrutorId) {

        return repository.findByInstrutorId(
                instrutorId);

    }

}