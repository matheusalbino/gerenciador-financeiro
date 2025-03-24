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
    public void removerCategoria(Categoria categoria){ categorias.remove(categoria); }

    @Override
    public void editarCategoria(Categoria categoria){
        Categoria categoriaAEditar = buscarCategoriaPorId(categoria.getId(), categoria.getUserid());
        categoriaAEditar.setNome(categoria.getNome());
        categoriaAEditar.setDescricao(categoria.getDescricao());
        categoriaAEditar.setTipo(categoria.getTipo());
    }

    @Override
    public List<Categoria> listarCategoriasDeUsuario(int userid){
        List<Categoria> categoriasDoUsuario = new ArrayList<>();
        for (Categoria categoria : categorias){
            if (categoria.getUserid() == userid){
                categoriasDoUsuario.add(categoria);
            }
        }

        return categoriasDoUsuario;
    }

    @Override
    public Categoria buscarCategoriaPorId(int idCategoria, int idUsuario) {
        List<Categoria> categoriasDoUsuario = listarCategoriasDeUsuario(idUsuario);
        for (Categoria categoria : categoriasDoUsuario){
            if(categoria.getId() == idCategoria){
                return categoria;
            }
        }
        return null;
    }
}
