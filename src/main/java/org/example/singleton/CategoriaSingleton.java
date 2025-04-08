package org.example.singleton;

import org.example.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaSingleton {
    private static CategoriaSingleton instancia = new CategoriaSingleton();
    private final List<Categoria> categorias;

    private CategoriaSingleton() {
        categorias = new ArrayList<>();
    }

    public static synchronized CategoriaSingleton getInstance() {
        if (instancia == null) {
            instancia = new CategoriaSingleton();
        }
        return instancia;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }
}
