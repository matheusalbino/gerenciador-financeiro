package model;

import model.enums.TipoTransacao;

import java.text.ParseException;
import java.util.Date;

public class Filtro {
    private Integer userID;
    private Date dataInicio;// = util.ValidarEntrada.formatarData("01/01/1888");
    private Date dataFinal = new Date();
    private Categoria categoria;
    private TipoTransacao tipoTransacao = null;

    public Filtro(Integer userID, Date dataInicio, Date dataFinal, Categoria categoria, TipoTransacao tipoTransacao) {
        this.userID = userID; // Valor padrão para userID
        this.dataInicio = dataInicio != null ? dataInicio : new Date(0); // Valor padrão: 01/01/1970
        this.dataFinal = dataFinal != null ? dataFinal : new Date(); // Valor padrão: data atual
        this.categoria = categoria != null ? categoria : new Categoria(); // Assumindo um construtor vazio na classe Categoria
        this.tipoTransacao = tipoTransacao != null ? tipoTransacao : TipoTransacao.DESPESA; // Valor padrão, exemplo
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
