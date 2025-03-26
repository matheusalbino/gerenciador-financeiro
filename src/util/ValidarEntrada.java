package util;

import java.lang.reflect.Array;
import java.util.Optional;

public final class ValidarEntrada {

    private ValidarEntrada() {}

    public static boolean validateDouble(String numero) {
        try {
            Double.parseDouble(numero);

            return true;
        } catch (NumberFormatException n) {
            return false;
        }
    }

    public static void validarNaoNuloOuVazio(Object objeto, String mensagem) {
        if (objeto == null) {
            throw new IllegalArgumentException(mensagem);
        }

        if (objeto instanceof Integer) {
            validarIntNuloOuMenorIgualZero((Integer) objeto, mensagem);

        } else if (objeto instanceof Double) {
            validarDoubleNuloOuMenorIgualZero((Double) objeto, mensagem);
        }
    }

    // Métodos de validação específicos para cada tipo

    public static void validarIntNuloOuMenorIgualZero(Integer numero, String mensagem) {
        if (numero == null || numero <= 0) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    public static void validarDoubleNuloOuMenorIgualZero(Double numero, String mensagem) {
        if (numero == null || numero <= 0.0) {
            throw new IllegalArgumentException(mensagem);
        }
    }


}
