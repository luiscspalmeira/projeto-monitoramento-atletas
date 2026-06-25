package com.faculdade.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faculdade.activity.entity.Atividade;
import com.faculdade.activity.repository.AtividadeRepository;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository repository;

    public Atividade salvar(Atividade atividade) {
        return repository.save(atividade);
    }

    public List<Atividade> listar() {
        return repository.findAll();
    }

    public List<Atividade> buscarPorAtleta(Long atletaId) {
        return repository.findByAtletaId(atletaId);
    }
}