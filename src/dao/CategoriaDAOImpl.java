package dao;

import model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {
    private static CategoriaDAOImpl instancia;
    private static List<Categoria> categorias;

    private CategoriaDAOImpl(){
        categorias = new ArrayList<>();
    }

    public static synchronized CategoriaDAOImpl getInstance(){
        if (instancia == null){
            instancia = new CategoriaDAOImpl();
        }
        return instancia;
    }

    @Override
    public void adicionarCategoria(Categoria categoria){
        categorias.add(categoria);
    }

    @Override
    public boolean removerCategoria(int id){
        for (Categoria categoria : categorias){
            if (categoria.getId() == id){
                categorias.remove(categoria);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Categoria> buscarCategoriasDeUsuario(int userid){
        List<Categoria> categoriasDoUsuario = new ArrayList<>();
        for (Categoria categoria : categorias){
            if (categoria.getUserid() == userid){
                categoriasDoUsuario.add(categoria);
            }
        }

        return categoriasDoUsuario;
    }

}
