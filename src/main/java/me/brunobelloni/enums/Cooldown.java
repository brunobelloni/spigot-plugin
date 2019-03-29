package me.brunobelloni.enums;

public enum Cooldown {

    THOR_COOLDOWN(5);
    
    int cooldown;

    private Cooldown(int cooldown) {
        this.cooldown = cooldown * 20;
    }

}
