package org.example.model.enums;

import java.util.Arrays;

public enum TipoTransacao {
    RECEITA,
    DESPESA;

    public String getText() {
        return this.name();
    }

    public static TipoTransacao getTrasancao(String tipo){
        for (TipoTransacao tipoTransacao : Arrays.stream(TipoTransacao.values()).toList()){
            System.out.println(tipoTransacao.getText().toUpperCase() + " - " + tipo.toUpperCase());
            if (tipoTransacao.getText().equalsIgnoreCase(tipo)){
                return tipoTransacao;
            }
        }
        //throw new IllegalArgumentException("Tipo de transação não encontrado");
        return null;
    }

}
