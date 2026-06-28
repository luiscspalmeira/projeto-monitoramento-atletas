package com.faculdade.gateway.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faculdade.gateway.service.GatewayService;

@RestController

@RequestMapping("/users")

public class UserGatewayController {

    private final GatewayService gatewayService;

    public UserGatewayController(GatewayService gatewayService) {

        this.gatewayService = gatewayService;

    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)

    public String listarUsuarios() {

        return gatewayService.get(

                "http://localhost:8081/users");

    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public String cadastrarUsuario(

            @RequestBody String json) {

        return gatewayService.post(

                "http://localhost:8081/users",

                json);

    }

}