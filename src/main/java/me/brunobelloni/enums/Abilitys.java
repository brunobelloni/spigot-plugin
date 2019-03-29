package me.brunobelloni.enums;

public enum Abilitys {

    NONE("Nenhum"),
    PVP("Pvp");

    private String name;

    private Abilitys(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
