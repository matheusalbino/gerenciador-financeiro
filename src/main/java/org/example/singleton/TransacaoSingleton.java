package org.example.singleton;

import org.example.model.Transacao;
import java.util.ArrayList;
import java.util.List;

public class TransacaoSingleton {
    private static TransacaoSingleton instancia = new TransacaoSingleton();
    private final List<Transacao> transacoes;

    private TransacaoSingleton() {
        transacoes = new ArrayList<>();
    }

    public static synchronized TransacaoSingleton getInstance() {

        if (instancia == null) {
            instancia = new TransacaoSingleton();
        }

        return instancia;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}
