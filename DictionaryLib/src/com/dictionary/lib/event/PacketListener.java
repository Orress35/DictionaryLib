package com.dictionary.lib.event;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.dictionary.lib.DictionaryAPI;
import com.dictionary.lib.check.Check;
import com.dictionary.lib.data.PlayerData;
import com.dictionary.lib.event.impl.MoveEvent;
import com.dictionary.lib.util.Loc;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class PacketListener implements Listener {
    private final DictionaryAPI dictionary;

    public PacketListener(DictionaryAPI dictionary, Plugin plugin) {
        this.dictionary = dictionary;
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin,
                PacketType.Play.Client.FLYING,
                PacketType.Play.Client.POSITION,
                PacketType.Play.Client.LOOK,
                PacketType.Play.Client.POSITION_LOOK
        ) {
            @Override
            public void onPacketReceiving(PacketEvent e) {
                if (e.isPlayerTemporary())
                    return;

                PlayerData data = dictionary.getPlayerDataManager().get(e.getPlayer());
                PacketType type = e.getPacketType();

                if (type == PacketType.Play.Client.FLYING || type == PacketType.Play.Client.POSITION
                        || type == PacketType.Play.Client.LOOK || type == PacketType.Play.Client.POSITION_LOOK) {
                    Loc from = data.getLastLocation();
                    Loc to   = data.getLocation();

                    from.set(to.getX(), to.getY(), to.getZ(), to.getYaw(), to.getPitch());

                    if (type == PacketType.Play.Client.POSITION) {
                        double x = e.getPacket().getDoubles().read(0);
                        double y = e.getPacket().getDoubles().read(1);
                        double z = e.getPacket().getDoubles().read(2);
                        to.set(x, y, z);
                    } else if (type == PacketType.Play.Client.LOOK) {
                        float yaw = e.getPacket().getFloat().read(0);
                        float pitch = e.getPacket().getFloat().read(1);
                        to.set(yaw, pitch);
                    } else if (type == PacketType.Play.Client.POSITION_LOOK) {
                        double x = e.getPacket().getDoubles().read(0);
                        double y = e.getPacket().getDoubles().read(1);
                        double z = e.getPacket().getDoubles().read(2);
                        float yaw = e.getPacket().getFloat().read(0);
                        float pitch = e.getPacket().getFloat().read(1);
                        to.set(x, y, z, yaw, pitch);
                    }

                    MoveEvent event = new MoveEvent(to, from, e.getPacket().getBooleans().read(0));
                    for (Check check: data.getChecks())
                        check.onMove(event);
                }
            }
        });
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        dictionary.getPlayerDataManager().add(e.getPlayer());
        dictionary.getPlayerDataManager().get(e.getPlayer()).registerChecks(dictionary.getChecks());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        dictionary.getPlayerDataManager().remove(e.getPlayer());
    }
}
