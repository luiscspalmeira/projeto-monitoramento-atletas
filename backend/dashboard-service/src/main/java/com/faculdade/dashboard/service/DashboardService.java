package com.faculdade.dashboard.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.faculdade.dashboard.client.ActivityClient;
import com.faculdade.dashboard.dto.ActivityResponse;
import com.faculdade.dashboard.dto.DashboardMensalResponse;
import com.faculdade.dashboard.dto.DashboardSemanalResponse;
import com.faculdade.dashboard.dto.ModalidadeResponse;

@Service
public class DashboardService {

    private final ActivityClient activityClient;

    public DashboardService(ActivityClient activityClient) {
        this.activityClient = activityClient;
    }

    private List<ActivityResponse> buscarAtividades(Long atletaId) {

        return activityClient.buscarPorAtleta(atletaId);

    }

    /*
     * ===========================
     * DASHBOARD SEMANAL
     * ===========================
     */

    public DashboardSemanalResponse resumoSemanal(Long atletaId) {

        List<ActivityResponse> atividades = buscarAtividades(atletaId);

        LocalDate hoje = LocalDate.now();

        LocalDate inicioSemana = hoje.minusDays(7);

        DashboardSemanalResponse response = new DashboardSemanalResponse();

        response.setAtletaId(atletaId);

        int quantidade = 0;

        double distancia = 0;

        int tempo = 0;

        for (ActivityResponse atividade : atividades) {

            if (atividade.getData() == null)
                continue;

            if (!atividade.getData().isBefore(inicioSemana)) {

                quantidade++;

                if (atividade.getDistancia() != null)
                    distancia += atividade.getDistancia();

                if (atividade.getTempo() != null)
                    tempo += atividade.getTempo();

            }

        }

        response.setQuantidadeTreinos(quantidade);
        response.setDistanciaTotal(distancia);
        response.setTempoTotal(tempo);

        return response;

    }

    /*
     * ===========================
     * DASHBOARD MENSAL
     * ===========================
     */

    public DashboardMensalResponse resumoMensal(Long atletaId) {

        List<ActivityResponse> atividades = buscarAtividades(atletaId);

        LocalDate hoje = LocalDate.now();

        LocalDate inicioMes = hoje.minusDays(30);

        DashboardMensalResponse response = new DashboardMensalResponse();

        response.setAtletaId(atletaId);

        int quantidade = 0;

        double distancia = 0;

        int tempo = 0;

        for (ActivityResponse atividade : atividades) {

            if (atividade.getData() == null)
                continue;

            if (!atividade.getData().isBefore(inicioMes)) {

                quantidade++;

                if (atividade.getDistancia() != null)
                    distancia += atividade.getDistancia();

                if (atividade.getTempo() != null)
                    tempo += atividade.getTempo();

            }

        }

        response.setQuantidadeTreinos(quantidade);
        response.setDistanciaTotal(distancia);
        response.setTempoTotal(tempo);

        return response;

    }

    /*
     * ===========================
     * MODALIDADES
     * ===========================
     */

    public List<ModalidadeResponse> resumoModalidades(Long atletaId) {

        List<ActivityResponse> atividades = buscarAtividades(atletaId);

        int corrida = 0;
        int bicicleta = 0;
        int natacao = 0;

        for (ActivityResponse atividade : atividades) {

            if (atividade.getModalidade() == null)
                continue;

            switch (atividade.getModalidade().toUpperCase()) {

                case "CORRIDA":
                    corrida++;
                    break;

                case "BICICLETA":
                    bicicleta++;
                    break;

                case "NATACAO":
                    natacao++;
                    break;

                case "NATAÇÃO":
                    natacao++;
                    break;
            }

        }

        List<ModalidadeResponse> lista = new ArrayList<>();

        lista.add(new ModalidadeResponse("CORRIDA", corrida));
        lista.add(new ModalidadeResponse("BICICLETA", bicicleta));
        lista.add(new ModalidadeResponse("NATACAO", natacao));

        return lista;

    }

}