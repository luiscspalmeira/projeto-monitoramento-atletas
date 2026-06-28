package com.faculdade.dashboard.dto;

public class DashboardMensalResponse {

    private Long atletaId;

    private Integer quantidadeTreinos;

    private Double distanciaTotal;

    private Integer tempoTotal;

    public DashboardMensalResponse() {
    }

    public Long getAtletaId() {
        return atletaId;
    }

    public void setAtletaId(Long atletaId) {
        this.atletaId = atletaId;
    }

    public Integer getQuantidadeTreinos() {
        return quantidadeTreinos;
    }

    public void setQuantidadeTreinos(Integer quantidadeTreinos) {
        this.quantidadeTreinos = quantidadeTreinos;
    }

    public Double getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(Double distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public Integer getTempoTotal() {
        return tempoTotal;
    }

    public void setTempoTotal(Integer tempoTotal) {
        this.tempoTotal = tempoTotal;
    }

}