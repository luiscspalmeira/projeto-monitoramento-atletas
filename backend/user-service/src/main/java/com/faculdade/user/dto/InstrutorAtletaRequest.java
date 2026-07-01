package com.faculdade.user.dto;

public class InstrutorAtletaRequest {

    private Long instrutorId;

    private Long atletaId;

    public InstrutorAtletaRequest() {
    }

    public Long getInstrutorId() {
        return instrutorId;
    }

    public void setInstrutorId(Long instrutorId) {
        this.instrutorId = instrutorId;
    }

    public Long getAtletaId() {
        return atletaId;
    }

    public void setAtletaId(Long atletaId) {
        this.atletaId = atletaId;
    }

}