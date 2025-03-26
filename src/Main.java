import controller.CategoriaController;
import controller.TransacaoController;
import controller.UsuarioController;
import model.Transacao;
import model.enums.TipoTransacao;

import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        // System.out.println("Hello, World!");

        CategoriaController categoriaController = new CategoriaController();
        UsuarioController usuarioController = new UsuarioController();
        TransacaoController transacaoController = new TransacaoController();

        try {
            usuarioController.cadastrarUsuario(1, "primeiro", "senha_user");
            categoriaController.cadastrarCategoria(1, 1, "Mercado", "Compras no mercado");
            transacaoController.cadastrarTransacao(1, 1, 1, 333, util.CurrentDate.getCurrentDate(),
                    "Carnes para o churrasco", TipoTransacao.DESPESA);
        }catch(Exception e){
            System.out.println("Falha no cadastro: " + e.getMessage());
        }

        System.out.println(
                "Teste busca categoria: \nCategoria: "
                + categoriaController.buscarCategoria(1, 1).getNome()
                + "\nDesc: "
                + categoriaController.buscarCategoria(1, 1).getDescricao()
        );

        System.out.println("\nTeste listar transações: ");

        List<Transacao> transacoes = transacaoController.listarTransacoesDoUsuario(1);
        for (Transacao transacao : transacoes){
            System.out.println(
                    transacao.getId() +
                    " - " + transacao.getDescricao() +
                    " - " + transacao.getValor() +
                    " - " + transacao.getTipoTransacao().getText()
            );
        }

        System.out.println();
    }
}
