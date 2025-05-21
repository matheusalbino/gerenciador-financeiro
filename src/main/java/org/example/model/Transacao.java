package org.example.model;

import jakarta.persistence.*;
import org.example.model.enums.TipoTransacao;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Entity
@Table(name = "Transacao")
public class Transacao {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "userid")
    private int userid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @Column(name = "valor", columnDefinition = "TEXT")
    private double valor;

    @JdbcTypeCode(SqlTypes.DATE)
    @Column(name = "data")
    private Date data;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "tipo", columnDefinition = "TEXT")
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

    public Transacao() { }


    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getUserId() {
        return userid;
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

    public Date getData(){
        return data;
    }

    public String getDescricao(){
        return descricao;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

}
