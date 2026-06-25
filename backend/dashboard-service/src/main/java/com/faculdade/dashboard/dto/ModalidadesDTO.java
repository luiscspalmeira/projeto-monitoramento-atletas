package com.faculdade.dashboard.dto;

public class ModalidadesDTO {

    private Double corrida;

    private Double bicicleta;

    private Double natacao;

    private Integer tempoTotal;

    private Integer quantidadeAtividades;

    public ModalidadesDTO() {
    }

    public Double getCorrida() {
        return corrida;
    }

    public void setCorrida(Double corrida) {
        this.corrida = corrida;
    }

    public Double getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(Double bicicleta) {
        this.bicicleta = bicicleta;
    }

    public Double getNatacao() {
        return natacao;
    }

    public void setNatacao(Double natacao) {
        this.natacao = natacao;
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