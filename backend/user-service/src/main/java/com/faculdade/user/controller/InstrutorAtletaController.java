package com.faculdade.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.user.dto.InstrutorAtletaRequest;
import com.faculdade.user.entity.InstrutorAtleta;
import com.faculdade.user.service.InstrutorAtletaService;

@RestController
@RequestMapping("/instrutores-atletas")
public class InstrutorAtletaController {

    @Autowired
    private InstrutorAtletaService service;

    @PostMapping
    public InstrutorAtleta vincular(
            @RequestBody InstrutorAtletaRequest request) {

        return service.vincular(request);

    }

    @GetMapping("/instrutor/{id}")
    public List<InstrutorAtleta> listarPorInstrutor(
            @PathVariable Long id) {

        return service.listarPorInstrutor(id);

    }

}