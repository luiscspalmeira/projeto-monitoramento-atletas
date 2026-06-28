package com.faculdade.dashboard.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.faculdade.dashboard.dto.ActivityResponse;

@Service
public class ActivityClient {

    private final RestClient restClient;

    public ActivityClient() {

        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();

    }

    public List<ActivityResponse> buscarPorAtleta(Long atletaId) {

        return restClient
                .get()
                .uri("/activities/atleta/{id}", atletaId)
                .retrieve()
                .body(new ParameterizedTypeReference<List<ActivityResponse>>() {
                });

    }

}