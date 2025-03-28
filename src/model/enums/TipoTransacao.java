package model.enums;

public enum TipoTransacao {
    RECEITA,
    DESPESA;

    public String getText() {
        return this.name();
    }

    public static TipoTransacao getTrasancao(String tipo){
        for (TipoTransacao tipoTransacao : TipoTransacao.values()){
            if (tipoTransacao.getText() == tipo){
                return tipoTransacao;
            }
        }
        throw new IllegalArgumentException("Tipo de transação não encontrado");
    }

}
