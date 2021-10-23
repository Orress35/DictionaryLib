package com.dictionary.lib.data;

import com.dictionary.lib.DictionaryAPI;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {
    private final DictionaryAPI dictionary;
    private final Map<UUID, PlayerData> dataMap = new HashMap<>();

    public PlayerDataManager(DictionaryAPI dictionary) {
        this.dictionary = dictionary;
    }

    public void add(Player player) {
        dataMap.put(player.getUniqueId(), new PlayerData(player, dictionary));
    }

    public PlayerData get(Player player) {
        return dataMap.getOrDefault(player.getUniqueId(), null);
    }

    public void remove(Player player) {
        dataMap.remove(player.getUniqueId());
    }
}
