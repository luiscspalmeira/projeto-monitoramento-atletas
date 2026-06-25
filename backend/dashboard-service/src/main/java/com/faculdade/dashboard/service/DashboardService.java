package com.faculdade.dashboard.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.faculdade.dashboard.dto.AtividadeDTO;
import com.faculdade.dashboard.dto.DashboardDTO;
import com.faculdade.dashboard.dto.ModalidadesDTO;

@Service
public class DashboardService {

    private final RestClient restClient =
            RestClient.create("http://localhost:8081");

    private List<AtividadeDTO> buscarAtividades(Long atletaId) {

        return restClient.get()
                .uri("/activities/atleta/" + atletaId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public DashboardDTO resumoSemanal(Long atletaId) {

        List<AtividadeDTO> atividades =
                buscarAtividades(atletaId);

        LocalDate hoje = LocalDate.now();
        LocalDate inicioSemana = hoje.minusDays(7);

        DashboardDTO dto = new DashboardDTO();

        double distancia = 0;
        int tempo = 0;
        int quantidade = 0;

        for (AtividadeDTO atividade : atividades) {

            if (!atividade.getDataAtividade()
                    .isBefore(inicioSemana)) {

                distancia += atividade.getDistancia();
                tempo += atividade.getDuracaoMinutos();
                quantidade++;
            }
        }

        dto.setAtletaId(atletaId);
        dto.setDistanciaTotal(distancia);
        dto.setTempoTotal(tempo);
        dto.setQuantidadeAtividades(quantidade);

        return dto;
    }

    public DashboardDTO resumoMensal(Long atletaId) {

        List<AtividadeDTO> atividades =
                buscarAtividades(atletaId);

        LocalDate hoje = LocalDate.now();
        LocalDate inicioMes = hoje.minusDays(30);

        DashboardDTO dto = new DashboardDTO();

        double distancia = 0;
        int tempo = 0;
        int quantidade = 0;

        for (AtividadeDTO atividade : atividades) {

            if (!atividade.getDataAtividade()
                    .isBefore(inicioMes)) {

                distancia += atividade.getDistancia();
                tempo += atividade.getDuracaoMinutos();
                quantidade++;
            }
        }

        dto.setAtletaId(atletaId);
        dto.setDistanciaTotal(distancia);
        dto.setTempoTotal(tempo);
        dto.setQuantidadeAtividades(quantidade);

        return dto;
    }

    public ModalidadesDTO resumoModalidades(Long atletaId) {

        List<AtividadeDTO> atividades =
                buscarAtividades(atletaId);

        ModalidadesDTO dto = new ModalidadesDTO();

        double corrida = 0;
        double bicicleta = 0;
        double natacao = 0;

        int tempoTotal = 0;
        int quantidade = 0;

        for (AtividadeDTO atividade : atividades) {

            String modalidade =
                    atividade.getModalidade();

            if ("CORRIDA".equalsIgnoreCase(modalidade)) {
                corrida += atividade.getDistancia();
            }

            if ("BICICLETA".equalsIgnoreCase(modalidade)) {
                bicicleta += atividade.getDistancia();
            }

            if ("NATACAO".equalsIgnoreCase(modalidade)) {
                natacao += atividade.getDistancia();
            }

            tempoTotal += atividade.getDuracaoMinutos();
            quantidade++;
        }

        dto.setCorrida(corrida);
        dto.setBicicleta(bicicleta);
        dto.setNatacao(natacao);
        dto.setTempoTotal(tempoTotal);
        dto.setQuantidadeAtividades(quantidade);

        return dto;
    }
}