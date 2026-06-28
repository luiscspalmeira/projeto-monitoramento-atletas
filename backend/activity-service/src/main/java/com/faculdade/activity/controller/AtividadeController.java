package com.faculdade.activity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.activity.dto.ActivityRequest;
import com.faculdade.activity.dto.ActivityResponse;
import com.faculdade.activity.service.AtividadeService;

@RestController
@RequestMapping("/activities")
public class AtividadeController {

    @Autowired
    private AtividadeService service;

    @PostMapping
    public ActivityResponse salvar(
            @RequestBody ActivityRequest request) {

        return service.salvar(request);
    }

    @GetMapping
    public List<ActivityResponse> listar() {

        return service.listar();
    }

    @GetMapping("/atleta/{id}")
    public List<ActivityResponse> buscarPorAtleta(
            @PathVariable Long id) {

        return service.buscarPorAtleta(id);
    }

}