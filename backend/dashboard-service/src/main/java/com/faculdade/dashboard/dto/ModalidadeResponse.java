package com.faculdade.dashboard.dto;

public class ModalidadeResponse {

    private String modalidade;

    private Integer quantidade;

    public ModalidadeResponse() {
    }

    public ModalidadeResponse(String modalidade, Integer quantidade) {
        this.modalidade = modalidade;
        this.quantidade = quantidade;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}