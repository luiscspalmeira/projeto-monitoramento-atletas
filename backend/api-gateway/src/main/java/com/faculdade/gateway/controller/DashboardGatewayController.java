package com.faculdade.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/dashboard")
public class DashboardGatewayController {

    private final RestClient restClient;

    public DashboardGatewayController(
            @Value("${dashboard.service.url}") String url) {

        this.restClient = RestClient.builder()
                .baseUrl(url)
                .build();
    }

    @GetMapping("/semanal/{id}")
    public Object semanal(@PathVariable Long id) {

        return restClient.get()
                .uri("/dashboard/semanal/{id}", id)
                .retrieve()
                .body(Object.class);

    }

    @GetMapping("/mensal/{id}")
    public Object mensal(@PathVariable Long id) {

        return restClient.get()
                .uri("/dashboard/mensal/{id}", id)
                .retrieve()
                .body(Object.class);

    }

    @GetMapping("/modalidades/{id}")
    public Object modalidades(@PathVariable Long id) {

        return restClient.get()
                .uri("/dashboard/modalidades/{id}", id)
                .retrieve()
                .body(Object.class);

    }

}