package me.brunobelloni.enums;

public enum Cooldown {

    THOR_COOLDOWN(10l);

    private long cooldown;

    private Cooldown(long cooldown) {
        this.cooldown = cooldown * 20l;
    }

    public long getCooldown() {
        return cooldown;
    }
}
