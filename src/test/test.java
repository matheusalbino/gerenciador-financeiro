package test;

import controller.CategoriaController;
import controller.ResumoFinanceiroController;
import controller.TransacaoController;
import controller.UsuarioController;
import model.ResumoFinanceiro;
import model.Transacao;
import model.Usuario;
import model.enums.TipoTransacao;
import service.UsuarioService;
import service.UsuarioServiceImpl;

import java.util.Date;
import java.util.List;

public class test {
    public void testarFuncionalidades(){
        CategoriaController categoriaController = new CategoriaController();
        UsuarioController usuarioController = new UsuarioController();
        TransacaoController transacaoController = new TransacaoController();
        ResumoFinanceiroController resumoFinanceiroController = new ResumoFinanceiroController();

        // cadastrando
        try {
            // cadastrando usuario
            usuarioController.cadastrarUsuario("primeiro", "senha_user");
            usuarioController.Login("primeiro", "senha_user");
            // cadastrando categoria
            categoriaController.cadastrarCategoria("Mercado", "Compras no mercado");
            // cadastrando transação
            transacaoController.cadastrarTransacao("Mercado", "333",
                    "11/11/2024", "Carnes para o churrasco", "DESPESA");
        } catch(Exception e){
            System.out.println("Falha no cadastro: " + e.getMessage());
        }
        System.out.println(usuarioController.buscarUsuarioPorUsername("primeiro").getLogin().toUpperCase());
        System.out.println("cadastrados, buscar categoria.");
        // buscando categoria
        System.out.println(
                "\nTeste busca categoria: \nCategoria: "
                        + categoriaController.buscarCategoria("Mercado").getNome()
                        + "\nDesc: "
                        + categoriaController.buscarCategoria("Mercado").getDescricao()
        );

        // buscando transações de um usuário
        System.out.println("\nTeste busca transações: ");

        List<Transacao> transacoes = transacaoController.listarTransacoesDoUsuario();
        for (Transacao transacao : transacoes){
            System.out.println(
                    transacao.getId() +
                            " - " + transacao.getDescricao() +
                            " - " + transacao.getValor() +
                            " - " + transacao.getTipoTransacao().getText()
            );
        }

        // todo testar features:

        // todo buscando transações com filtro
        /*
        List<Transacao> listaFiltrada = transacaoController.listarTransacoesComFiltro(null, null, null, "DESPESA");
        System.out.println("\nTeste busca transações filtradas: ");
        for (Transacao transacao : listaFiltrada){
            System.out.println(transacao.getDescricao());
        }*/

        // editando categoria
        System.out.println("\nEditando categoria: ");
        categoriaController.editarCategoria("Mercado", "nova", "descrição editada");
        System.out.println(categoriaController.buscarCategoria("nova").getNome().toUpperCase());
        System.out.println(categoriaController.buscarCategoria("nova").getDescricao());

        // teste resumo financeiro
        System.out.println("\nGerando resumo financeiro:");
        ResumoFinanceiro resumoFinanceiro = resumoFinanceiroController.gerarResumoFinanceiro();
        System.out.println(resumoFinanceiro.getTotalCreditos() + " " + resumoFinanceiro.getTotalDebitos() + " " + resumoFinanceiro.getSaldoTotal());


        // teste gerar resumo com filtro por período
        //System.out.println("\nGerando resumo financeiro por período:");
        // todo data do mesmo dia não está entrando no filtro
        //ResumoFinanceiro resumoFiltrado = resumoFinanceiroController.gerarResumoFiltrado(null, null);
        //System.out.println(resumoFiltrado.getTotalCreditos() + " " + resumoFiltrado.getTotalDebitos() + " " + resumoFiltrado.getSaldoTotal());


        // deletando transação
        try {
            transacaoController.removerTransacao("1");
            System.out.println("\nTransação deletada");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        // deletando categoria
        System.out.println("Testando deletar:");
        try{
            categoriaController.removerCategoria("nova");
            System.out.println("Categoria deletada");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}