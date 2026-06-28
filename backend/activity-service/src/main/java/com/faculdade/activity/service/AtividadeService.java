package com.faculdade.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faculdade.activity.dto.ActivityRequest;
import com.faculdade.activity.dto.ActivityResponse;
import com.faculdade.activity.entity.Atividade;
import com.faculdade.activity.repository.AtividadeRepository;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository repository;

    public ActivityResponse salvar(ActivityRequest request) {

        Atividade atividade = new Atividade();

        atividade.setAtletaId(request.getAtletaId());

        atividade.setModalidade(request.getModalidade());

        atividade.setDistancia(request.getDistancia());

        atividade.setDuracaoMinutos(request.getTempo());

        atividade.setDataAtividade(request.getData());

        atividade = repository.save(atividade);

        ActivityResponse response = new ActivityResponse();

        response.setId(atividade.getId());

        response.setAtletaId(atividade.getAtletaId());

        response.setModalidade(atividade.getModalidade());

        response.setDistancia(atividade.getDistancia());

        response.setTempo(atividade.getDuracaoMinutos());

        response.setData(atividade.getDataAtividade());

        return response;

    }

    public List<Atividade> listar() {
        return repository.findAll();
    }

    public List<Atividade> buscarPorAtleta(Long atletaId) {
        return repository.findByAtletaId(atletaId);
    }
}