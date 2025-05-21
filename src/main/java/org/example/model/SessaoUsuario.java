package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sessao_usuario")
public class SessaoUsuario {

    @Id
    @Column(name = "id")
    private int id = 0;

    @Column(name = "idUsuario")
    private Integer idUsuario;


    public SessaoUsuario(int id, Integer idUsuario){
        this.id = id;
        this.idUsuario = idUsuario;
    }

    public SessaoUsuario(){ }

    public int getId() {
        return id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }

}
