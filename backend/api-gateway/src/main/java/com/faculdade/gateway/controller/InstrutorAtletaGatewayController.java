package com.faculdade.gateway.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.gateway.service.GatewayService;

@RestController
@RequestMapping("/instrutores-atletas")
public class InstrutorAtletaGatewayController {

    private final GatewayService gatewayService;

    public InstrutorAtletaGatewayController(
            GatewayService gatewayService) {

        this.gatewayService = gatewayService;

    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String vincular(
            @RequestBody String json) {

        return gatewayService.post(

                "http://localhost:8081/instrutores-atletas",

                json);

    }

    @GetMapping(
            value = "/instrutor/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String listarPorInstrutor(
            @PathVariable Long id) {

        return gatewayService.get(

                "http://localhost:8081/instrutores-atletas/instrutor/" + id);

    }

}