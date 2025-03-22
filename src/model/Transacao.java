package model;

public class Transacao {
    private int id;
    private int userid;
    private Categoria categoria;
    private double valor;
    private String data;
    private String descricao;

    public Transacao(int id, int userid, Categoria categoria, double valor, String data, String descricao){
        this.id = id;
        this.userid = userid;
        this.categoria = categoria;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
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

    public String getData(){
        return data;
    }
    public void setData(String data){
        this.data = data;
    }

    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
