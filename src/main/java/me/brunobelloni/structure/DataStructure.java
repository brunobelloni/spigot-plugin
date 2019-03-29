package me.brunobelloni.structure;

import java.util.Iterator;
import java.util.TreeSet;
import me.brunobelloni.types.Gamer;
import org.bukkit.entity.Player;

public class DataStructure {

    private TreeSet<Gamer> gamers = new TreeSet<>();

    public DataStructure add(Gamer gamer) {
        this.gamers.add(gamer);
        return this;
    }

    public DataStructure remove(Gamer gamer) {
        this.gamers.remove(gamer);
        return this;
    }

    public DataStructure clear() {
        this.gamers.clear();
        return this;
    }

    public void list() {
        Iterator<Gamer> iterator = this.gamers.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }

    public void listAbilitys() {
        Iterator<Gamer> iterator = this.gamers.iterator();
        while (iterator.hasNext()) {
            Gamer g = iterator.next();
            System.out.print(g.getPlayer().getName() + " tem o kit " + g.getAbility());
        }
    }

    public Gamer search(Player key) {
        Gamer gamer = new Gamer(key);
        return search(this.gamers, gamer);
    }

    private Gamer search(TreeSet treeset, Gamer key) {
        Gamer ceil = (Gamer) treeset.ceiling(key);
        Gamer floor = (Gamer) treeset.floor(key);
        return ceil == floor ? ceil : null;
    }
}
