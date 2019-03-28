package me.brunobelloni.enums;

public enum Skills {
    
    PVP("Pvp");
    
    private String name;
    
    
    private Skills(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
