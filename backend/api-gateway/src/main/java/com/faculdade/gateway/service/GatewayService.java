package com.faculdade.gateway.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class GatewayService {

    private final RestClient restClient;

    public GatewayService() {

        this.restClient = RestClient.create();

    }

    public String get(String url) {

        return restClient
                .get()
                .uri(url)
                .retrieve()
                .body(String.class);

    }

    public String post(String url, String json) {

        return restClient
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .body(json)
                .retrieve()
                .body(String.class);

    }

}