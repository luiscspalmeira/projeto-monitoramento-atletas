package com.faculdade.gateway.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.gateway.service.GatewayService;

@RestController
@RequestMapping("/activities")
public class ActivityGatewayController {

    private final GatewayService gatewayService;

    public ActivityGatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String listarAtividades() {

        return gatewayService.get(
                "http://localhost:8082/activities");

    }

    @GetMapping("/atleta/{id}")
    public String buscarPorAtleta(
            @org.springframework.web.bind.annotation.PathVariable Long id) {

        return gatewayService.get(
                "http://localhost:8082/activities/atleta/" + id);

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String cadastrarAtividade(
            @RequestBody String json) {

        return gatewayService.post(
                "http://localhost:8082/activities",
                json);

    }

}