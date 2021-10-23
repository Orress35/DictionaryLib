package com.dictionary.lib.data;

import com.dictionary.lib.DictionaryAPI;
import com.dictionary.lib.check.Check;
import com.dictionary.lib.util.Loc;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {
    private final Player player;
    private final DictionaryAPI dictionary;
    private final List<Check> checks = new ArrayList<>();

    private final Loc location = new Loc(0,0,0,0,0), lastLocation = new Loc(0,0,0,0,0);

    public PlayerData(Player player, DictionaryAPI dictionary) {
        this.player = player;
        this.dictionary = dictionary;
    }

    public Player getPlayer() {
        return player;
    }

    public void registerChecks(Class<?>... classes) {
        try {
            for (Class<?> clazz: classes)
                checks.add((Check) clazz.getDeclaredConstructors()[0].newInstance(this));
        } catch (Exception ignored) { }
    }

    public List<Check> getChecks() {
        return checks;
    }

    public DictionaryAPI getDictionary() {
        return dictionary;
    }

    public Loc getLocation() {
        return location;
    }

    public Loc getLastLocation() {
        return lastLocation;
    }
}
