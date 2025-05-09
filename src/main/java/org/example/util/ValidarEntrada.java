package org.example.util;

import org.example.model.enums.TipoTransacao;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public final class ValidarEntrada {

    private ValidarEntrada() {}

    public static double validateDouble(String numero) {
        try{
            return Double.parseDouble(numero);
        }catch (Exception e){
            throw new IllegalArgumentException("Valor invalido");
        }
    }

    public static Integer validateInteger(String numero){
        return Integer.parseInt(numero);
    }

    public static void validarInt(Integer numero, String mensagem) {
        if (numero < 0) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public static void validarDouble(Double numero, String mensagem) {
        if (numero <= 0.0) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public static void validarStringNuloOuVazia(String texto, String mensagem) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
    }
    public static void validarStringVazia(String texto, String mensagem) {
        if (texto.isBlank()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public static Date formatarData(String data) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.parse(data);
        }catch (Exception e){
            return null;
        }
    }


}
