package com.dictionary;

import com.dictionary.lib.DictionaryAPI;
import com.dictionary.lib.check.impl.StepA;
import com.dictionary.lib.event.CheatEventListener;
import com.dictionary.lib.event.impl.CheatEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class DictionaryAC extends JavaPlugin implements CheatEventListener {
    private DictionaryAPI dictionary;

    @Override
    public void onEnable() {
        dictionary = new DictionaryAPI(this);
        dictionary.start();

        dictionary.setChecks(
                StepA.class
        );

        dictionary.addListener(this);
    }

    @Override
    public void onDisable() {
        dictionary.stop();
    }

    @Override
    public void onPlayerCheat(CheatEvent e) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&8[&9Dictionary&8] &9" + e.getPlayer().getName() + "&7 failed &9" + e.getCheck().getName() + " &8[&7VL: &9" + (int) e.getViolations() + "&8]"));
    }
}
