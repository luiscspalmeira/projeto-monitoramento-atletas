package com.faculdade.dashboard.dto;

public class DashboardDTO {

    private Long atletaId;

    private Double distanciaTotal;

    private Integer tempoTotal;

    private Integer quantidadeAtividades;

    public DashboardDTO() {
    }

    public Long getAtletaId() {
        return atletaId;
    }

    public void setAtletaId(Long atletaId) {
        this.atletaId = atletaId;
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

    public Integer getQuantidadeAtividades() {
        return quantidadeAtividades;
    }

    public void setQuantidadeAtividades(Integer quantidadeAtividades) {
        this.quantidadeAtividades = quantidadeAtividades;
    }
}