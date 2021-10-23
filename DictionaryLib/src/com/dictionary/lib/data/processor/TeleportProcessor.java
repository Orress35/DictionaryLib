package com.dictionary.lib.data.processor;

import com.dictionary.lib.data.exempt.ExemptData;
import com.dictionary.lib.data.exempt.ExemptType;
import com.dictionary.lib.event.impl.MoveEvent;
import com.dictionary.lib.event.impl.TeleportEvent;
import com.dictionary.lib.util.Loc;

import java.util.ArrayList;
import java.util.List;

public class TeleportProcessor {
    private final ExemptData data;
    private int teleportTicks;
    private final Loc lastTeleport = new Loc(0,0,0,0,0);
    private final List<Loc> teleports = new ArrayList<>();

    public TeleportProcessor(ExemptData data) {
        this.data = data;
    }

    public void onMove(MoveEvent e) {
        if (teleportTicks < 5)
            teleportTicks++;

        if (e.getTo().distance(lastTeleport) > 3)
            teleportTicks = 5;

        for (Loc teleport: teleports) {
            if (e.getTo().distance(teleport) == 0) {
                teleportTicks = 0;
                teleports.remove(teleport);
                lastTeleport.set(teleport.getX(), teleport.getY(), teleport.getZ(), teleport.getYaw(), teleport.getPitch());
                break;
            }
        }

        data.set(ExemptType.TELEPORT, teleportTicks < 5);
    }

    public void onTeleport(TeleportEvent e) {
        teleports.add(e.getLocation());
    }
}
