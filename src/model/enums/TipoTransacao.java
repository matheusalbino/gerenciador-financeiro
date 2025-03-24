package model.enums;

public enum TipoTransacao {
    RECEITA,
    DESPESA;

    public String getText() {
        return this.name(); // name() retrieves the enum's name as a string
    }
}
