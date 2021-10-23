package com.dictionary.lib.event.impl;

import com.dictionary.lib.check.Check;
import com.dictionary.lib.event.Event;
import org.bukkit.entity.Player;

public class CheatEvent extends Event {
    private final Player player;
    private final Check check;
    private final String info;
    private final double violations;

    public CheatEvent(Player player, Check check, String info, double violations) {
        this.player = player;
        this.check = check;
        this.info = info;
        this.violations = violations;
    }

    public Player getPlayer() {
        return player;
    }

    public Check getCheck() {
        return check;
    }

    public String getInfo() {
        return info;
    }

    public double getViolations() {
        return violations;
    }
}
