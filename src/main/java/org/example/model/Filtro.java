package org.example.model;

import java.util.Date;

public class Filtro {
    private final Integer userID;
    private final Date dataInicio;
    private final Date dataFinal;
    private String categoria;
    private final String tipoTransacao;

    public Filtro(Integer userID, Date dataInicio, Date dataFinal, String categoria, String tipoTransacao) {
        this.userID = userID;
        this.dataInicio = dataInicio != null ? dataInicio : new Date(0); // Valor padrão: 01/01/1970
        this.dataFinal = dataFinal != null ? dataFinal : new Date(); // Valor padrão: data atual
        this.categoria = categoria;
        this.tipoTransacao = tipoTransacao;
    }


    public Integer getUserID() {
        return userID;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
