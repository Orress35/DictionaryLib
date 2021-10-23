package com.dictionary.lib;

import com.comphenix.protocol.ProtocolLibrary;
import com.dictionary.lib.data.PlayerDataManager;
import com.dictionary.lib.event.CheatEventListener;
import com.dictionary.lib.event.PacketListener;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class DictionaryAPI {
    private final Plugin plugin;
    private final PlayerDataManager playerDataManager;

    private final List<CheatEventListener> listeners = new ArrayList<>();

    private Class<?>[] checks;

    public DictionaryAPI(Plugin plugin) {
        this.plugin = plugin;
        this.playerDataManager = new PlayerDataManager(this);
    }

    public void start() {
        Bukkit.getPluginManager().registerEvents(new PacketListener(this, plugin), plugin);
    }

    public void stop() {
        ProtocolLibrary.getProtocolManager().removePacketListeners(plugin);
        HandlerList.unregisterAll(plugin);
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }

    public void setChecks(Class<?>... checks) {
        this.checks = checks;
    }

    public Class<?>[] getChecks() {
        return checks;
    }

    public List<CheatEventListener> getListeners() {
        return listeners;
    }

    public void addListener(CheatEventListener listener) {
        listeners.add(listener);
    }
}
