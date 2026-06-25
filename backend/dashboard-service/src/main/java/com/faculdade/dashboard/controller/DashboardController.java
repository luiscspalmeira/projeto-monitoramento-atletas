package com.faculdade.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.dashboard.dto.DashboardDTO;
import com.faculdade.dashboard.dto.ModalidadesDTO;
import com.faculdade.dashboard.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping("/semanal/{atletaId}")
    public DashboardDTO semanal(
            @PathVariable Long atletaId) {

        return service.resumoSemanal(atletaId);
    }

    @GetMapping("/mensal/{atletaId}")
    public DashboardDTO mensal(
            @PathVariable Long atletaId) {

        return service.resumoMensal(atletaId);
    }

    @GetMapping("/modalidades/{atletaId}")
    public ModalidadesDTO modalidades(
            @PathVariable Long atletaId) {

        return service.resumoModalidades(atletaId);
    }
}