package org.example.model;

import java.util.Date;

public class ResumoFinanceiro {
    private int userID;
    private double saldoTotal;
    private double totalDebitos;
    private double totalCreditos;
    private Date dataInicio;
    private Date dataFinal;

    public ResumoFinanceiro(int userID, double saldoTotal, double totalCreditos, double totalDebitos){
        this.userID = userID;
        this.saldoTotal = saldoTotal;
        this.totalCreditos = totalCreditos;
        this.totalDebitos = totalDebitos;
    }

    public ResumoFinanceiro(int userID, double saldoTotal, double totalCreditos,
                            double totalDebitos, Date dataInicio, Date dataFinal)
    {
        this.userID = userID;
        this.saldoTotal = saldoTotal;
        this.totalCreditos = totalCreditos;
        this.totalDebitos = totalDebitos;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public double getTotalDebitos() {
        return totalDebitos;
    }

    public void setTotalDebitos(double totalDebitos) {
        this.totalDebitos = totalDebitos;
    }

    public double getTotalCreditos() {
        return totalCreditos;
    }

    public void setTotalCreditos(double totalCreditos) {
        this.totalCreditos = totalCreditos;
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
}
