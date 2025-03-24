package service;

import dao.CategoriaDAO;
import dao.TransacaoDAO;
import dao.UsuarioDAO;
import model.Filtro;
import model.Transacao;
import model.enums.TipoTransacao;

import java.util.Date;
import java.util.List;

public class TransacaoServiceImpl implements TransacaoService{
    private TransacaoDAO transacaoDAO;
    private UsuarioDAO usuarioDAO;
    private CategoriaDAO categoriaDAO;
    private TipoTransacao tipoTransacao;


    @Override
    public boolean adicionarTransacao(Transacao transacao){
        if (transacao.getValor() <= 0){
            throw new IllegalArgumentException("Valor da transação deve ser positivo");
        }
        if (transacao.getCategoria() == null){
            throw new IllegalArgumentException("Escolha uma categoria válida");
        }
        if (transacao.getData().compareTo(new Date()) <= 0){
            throw new IllegalArgumentException("Usuário não pode lançar transações futuras");
        }
        if (transacao.getDescricao() == null){
            throw new IllegalArgumentException("Insira uma descrição");
        }

        transacaoDAO.adicionarTransacao(transacao);
        return true;
    }

    @Override
    public boolean removerTransacaoPorId(int idTransacao){
        Transacao transacao = transacaoDAO.buscarTransacaoPorId(idTransacao);
        if ( transacao != null){
            transacaoDAO.removerTransacao(transacao);
            return true;
        }
        return false;
    }

    @Override
    public List<Transacao> buscarTransacoesPorFiltro(Filtro filtro){
        // testar o filtro antes de mandar a buscaDAO
        if (usuarioDAO.buscarUsuarioPorId(filtro.getUserID()) == null){
            throw new IllegalArgumentException("Codigo de usuario não encontrado");
        }

        if (filtro.getDataInicio().after(filtro.getDataFinal())){
            throw new IllegalArgumentException("Data de inicio depois da data final");
        }

        //verificar se existe essa categoria e verificar se existe esse tipo de transacao

        if (categoriaDAO.buscarCategoriaPorId(filtro.getCategoria().getId(), filtro.getUserID()) == null){
            throw new IllegalArgumentException("Categoria inválida");
        }

        boolean flag = false;
        for (TipoTransacao tipo : TipoTransacao.values()){
            if (filtro.getTipoTransacao().equals(tipo)){
                break;
            }
        }

        if(flag) {
            throw new IllegalArgumentException("Tipo de transação não encontrado");
        }


        return transacaoDAO.buscarTransacoesComFiltro(filtro);
    }


    // checar se o id existe para busca
    @Override
    public List<Transacao> buscarTransacoesPorIdUsuario(int idUsuario){
        if (usuarioDAO.buscarUsuarioPorId(idUsuario) == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        return transacaoDAO.buscarTransacoesDeUsuario(idUsuario);
    }

    public Transacao buscarTransacaoPorID(int idTransacao){
        Transacao transacao =  transacaoDAO.buscarTransacaoPorId(idTransacao);
        if(transacao == null){
            throw new IllegalArgumentException("Transação não encontrada");
        }
        return transacao;
    }

}
