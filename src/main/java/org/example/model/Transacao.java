package org.example.model;

import org.example.model.enums.TipoTransacao;

import java.util.Date;

public class Transacao {
    private int id;
    private int userid;
    private Categoria categoria;
    private double valor;
    private Date data;
    private String descricao;
    private TipoTransacao tipo;

    public Transacao(int id, int userid, Categoria categoria, double valor, Date data, String descricao, TipoTransacao tipo){
        this.id = id;
        this.userid = userid;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    // Getters e setters
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getUserId() {
        return userid;
    }
    public void setUserId(int id){
        this.userid = id;
    }

    public Categoria getCategoria(){
        return categoria;
    }
    public void setCategoria(Categoria categoria){
        this.categoria = categoria;
    }

    public double getValor(){
        return valor;
    }
    public void setValor(double valor){
        this.valor = valor;
    }

    public Date getData(){
        return data;
    }
    public void setData(Date data){
        this.data = data;
    }

    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }
}
