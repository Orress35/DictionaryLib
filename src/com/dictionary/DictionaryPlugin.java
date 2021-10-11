package com.dictionary;

import org.bukkit.plugin.java.JavaPlugin;

public class DictionaryPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        Dictionary.INSTANCE.start(this);
    }

    @Override
    public void onDisable() {
        Dictionary.INSTANCE.stop(this);
    }
}
