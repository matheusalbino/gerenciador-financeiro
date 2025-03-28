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
    /*    CategoriaController categoriaController = new CategoriaController();
        UsuarioController usuarioController = new UsuarioController();
        TransacaoController transacaoController = new TransacaoController();
        ResumoFinanceiroController resumoFinanceiroController = new ResumoFinanceiroController();

        // cadastrando
        try {
            // cadastrando usuario
            usuarioController.cadastrarUsuario(1, "primeiro", "senha_user");
            // cadastrando categoria
            categoriaController.cadastrarCategoria(1, 1, "Mercado", "Compras no mercado");
            // cadastrando transação
            transacaoController.cadastrarTransacao(1, 333,
                    "11/11/2024", "Carnes para o churrasco", TipoTransacao.DESPESA);
        } catch(Exception e){
            System.out.println("Falha no cadastro: " + e.getMessage());
        }

        // buscando categoria
        System.out.println(
                "\nTeste busca categoria: \nCategoria: "
                        + categoriaController.buscarCategoria(1, 1).getNome()
                        + "\nDesc: "
                        + categoriaController.buscarCategoria(1, 1).getDescricao()
        );

        // buscando transações de um usuário
        System.out.println("\nTeste busca transações: ");

        List<Transacao> transacoes = transacaoController.listarTransacoesDoUsuario(1);
        for (Transacao transacao : transacoes){
            System.out.println(
                    transacao.getId() +
                            " - " + transacao.getDescricao() +
                            " - " + transacao.getValor() +
                            " - " + transacao.getTipoTransacao().getText()
            );
        }

        // todo testar features:

        // buscando transações com filtro
        List<Transacao> listaFiltrada = transacaoController.listarTransacoesComFiltro(1, null, null, null, null);
        System.out.println("\nTeste busca transações filtradas: ");
        for (Transacao transacao : listaFiltrada){
            System.out.println(transacao.getDescricao());
        }

        // editando categoria
        categoriaController.editarCategoria(1, 1, "nova", "descrição editada");
        System.out.println("\nEditando categoria: ");
        System.out.println(categoriaController.buscarCategoria(1, 1).getNome());
        System.out.println(categoriaController.buscarCategoria(1, 1).getDescricao() + "\n");

        // teste resumo financeiro
        System.out.println("\nGerando resumo financeiro:");
        ResumoFinanceiro resumoFinanceiro = resumoFinanceiroController.gerarResumo(1);
        System.out.println(resumoFinanceiro.getTotalCreditos() + " " + resumoFinanceiro.getTotalDebitos() + " " + resumoFinanceiro.getSaldoTotal());
        // teste gerar resumo com filtro por período
        System.out.println("\nGerando resumo financeiro por período:");
        // todo data do mesmo dia não está entrando no filtro
        ResumoFinanceiro resumoFiltrado = resumoFinanceiroController.gerarResumoFiltrado(1, new Date(), null);
        System.out.println(resumoFiltrado.getTotalCreditos() + " " + resumoFiltrado.getTotalDebitos() + " " + resumoFiltrado.getSaldoTotal());

        // teste login usuário


        // deletando transação
        try {
            transacaoController.removerTransacao(1);
            System.out.println("\nTransação deletada");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        // deletando categoria
        try{
            categoriaController.removerCategoria(1, 1);
            System.out.println("Categoria deletada");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        UsuarioService usuarioService = new UsuarioServiceImpl();

        usuarioController.cadastrarUsuario(2, "brau", "kkk");
        Usuario usuario = usuarioController.validarLogin("brau","kkk");

        if (usuario != null) {
            usuarioService.login(usuario);
        }

        System.out.println(usuarioService.getUsuarioLogado().getLogin());
    */}
}