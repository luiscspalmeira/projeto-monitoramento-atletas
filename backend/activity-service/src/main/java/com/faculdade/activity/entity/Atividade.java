package com.faculdade.activity.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "atividades")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long atletaId;

    private String modalidade;

    private Double distancia;

    private Integer duracaoMinutos;

    private LocalDate dataAtividade;

    public Atividade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getAtletaId(){
        return atletaId;
    }

    public void setAtletaId(Long atletaId){
        this.atletaId = atletaId;
    }

    public String getModalidade(){
        return modalidade;
    }

    public void setModalidade(String modalidade){
        this.modalidade = modalidade;
    }

    public Double getDistancia(){
        return distancia;
    }

    public void setDistancia(Double distancia){
        this.distancia = distancia;
    }

    public Integer getDuracaoMinutos(){
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(Integer duracaoMinutos){
        this.duracaoMinutos = duracaoMinutos;
    }

    public LocalDate getDataAtividade(){
        return dataAtividade;
    }

    public void setDataAtividade(LocalDate dataAtividade){
        this.dataAtividade = dataAtividade;
    }
}