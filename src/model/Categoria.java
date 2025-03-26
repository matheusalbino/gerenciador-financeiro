package model;

import model.enums.TipoCategoria;

public class Categoria {
    private int id;
    private int userid;
    private String nome;
    private String descricao;

    public Categoria(int id, int userid, String nome, String descricao){
        this.id = id;
        this.userid = userid;
        this.nome = nome;
        this.descricao = descricao;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid(){
        return userid;
    }

    public void setUserid(int userid){
        this.userid = userid;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
