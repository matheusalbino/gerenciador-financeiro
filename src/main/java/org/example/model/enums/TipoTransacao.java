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
            if (tipoTransacao.getText().equalsIgnoreCase(tipo)){
                return tipoTransacao;
            }
        }
        return null;
    }

}
