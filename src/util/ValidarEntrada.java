package util;

import java.lang.reflect.Array;
import java.util.Optional;

public final class ValidarEntrada {

    private ValidarEntrada() {}

    public static void validarNaoNuloOuVazio(Object objeto, String mensagem) {
        if (objeto == null) {
            throw new IllegalArgumentException(mensagem);
        }

        if (objeto instanceof CharSequence) {
            validarCharSequence((CharSequence) objeto, mensagem);

        } else if (objeto instanceof Integer) {
            validarIntNuloOuMenorIgualZero((Integer) objeto, mensagem);

        } else if (objeto instanceof Double) {
            validarDoubleNuloOuMenorIgualZero((Double) objeto, mensagem);

        } else if (objeto instanceof Optional) {
            validarOptional((Optional<?>) objeto, mensagem);

        } else if (objeto.getClass().isArray()) {
            validarArray(objeto, mensagem);

        }
    }

    // Métodos de validação específicos para cada tipo
    private static void validarCharSequence(CharSequence texto, String mensagem) {
        if (texto.isEmpty() || texto.toString().trim().isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
    }

    private static void validarArray(Object array, String mensagem) {
        if (Array.getLength(array) == 0) {
            throw new IllegalArgumentException(mensagem);
        }
    }


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

    private static void validarOptional(Optional<?> optional, String mensagem) {
        if (optional.isEmpty()) {
            throw new IllegalArgumentException(mensagem);
        }
    }
}
