package service;

public interface ResumoFinanceiroService {
    // calcular o total atual, total de crédito, total de débito, totais com filtro de data
    // calcular totais vindo de uma lista

    // calcular totais do usuario
    // somar todas as transações tipo crédito
    public double totalEntradas(int idUsuario);

    // somar todos os débitos
    public double totalDespesas(int idUsuario);

    // calcular quanto sobra do total
    public double saldoAtual(int idUsuario);
}
