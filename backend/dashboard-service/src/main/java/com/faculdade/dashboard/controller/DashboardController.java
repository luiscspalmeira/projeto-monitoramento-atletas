package com.faculdade.dashboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.dashboard.dto.DashboardMensalResponse;
import com.faculdade.dashboard.dto.DashboardSemanalResponse;
import com.faculdade.dashboard.dto.ModalidadeResponse;
import com.faculdade.dashboard.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/semanal/{atletaId}")
    public DashboardSemanalResponse semanal(
            @PathVariable Long atletaId) {

        return service.resumoSemanal(atletaId);

    }

    @GetMapping("/mensal/{atletaId}")
    public DashboardMensalResponse mensal(
            @PathVariable Long atletaId) {

        return service.resumoMensal(atletaId);

    }

    @GetMapping("/modalidades/{atletaId}")
    public List<ModalidadeResponse> modalidades(
            @PathVariable Long atletaId) {

        return service.resumoModalidades(atletaId);

    }

}