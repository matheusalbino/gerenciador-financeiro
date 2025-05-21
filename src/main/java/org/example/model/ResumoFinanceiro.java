package org.example.model;


public class ResumoFinanceiro {
    private final double saldoTotal;
    private final double totalDebitos;
    private final double totalCreditos;

    public ResumoFinanceiro(int userID, double saldoTotal, double totalCreditos, double totalDebitos){
        this.saldoTotal = saldoTotal;
        this.totalCreditos = totalCreditos;
        this.totalDebitos = totalDebitos;
    }


    public double getSaldoTotal() {
        return saldoTotal;
    }

    public double getTotalDebitos() {
        return totalDebitos;
    }

    public double getTotalCreditos() {
        return totalCreditos;
    }

}
