package model;

import model.enums.TipoCategoria;
import model.enums.TipoTransacao;

import java.util.Date;

public class Filtro {
    private int userID;
    private Date dataInicio;
    private Date dataFinal;
    private Categoria categoria;
    private TipoTransacao tipoTransacao;

    public Filtro(int userid, Date dataInicio, Date dataFinal, Categoria categoria, TipoTransacao tipoTransacao){
        this.userID = userid;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
        this.categoria = categoria;
        this.tipoTransacao = tipoTransacao;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}
