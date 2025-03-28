package model;

import model.enums.TipoTransacao;

import java.util.Date;

public class Filtro {
    private Integer userID;
    private Date dataInicio = new Date();
    private Date dataFinal = null;
    private Categoria categoria;
    private TipoTransacao tipoTransacao = null;

    public Filtro(Integer userID, Date dataInicio, Date dataFinal, Categoria categoria, TipoTransacao tipoTransacao) {
        this.userID = userID;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.categoria = categoria;
        this.tipoTransacao = tipoTransacao;
    }



    // Métodos getters e setters (opcional para acesso)

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }


    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
