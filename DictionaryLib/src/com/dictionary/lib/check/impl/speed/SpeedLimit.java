package com.dictionary.lib.check.impl.speed;

import com.dictionary.lib.check.Check;
import com.dictionary.lib.check.CheckInfo;
import com.dictionary.lib.data.PlayerData;
import com.dictionary.lib.data.exempt.ExemptType;
import com.dictionary.lib.event.impl.MoveEvent;
import org.bukkit.Bukkit;

@CheckInfo(name = "Speed (Limit)")
public class SpeedLimit extends Check {
    public SpeedLimit(PlayerData data) {
        super(data);
    }

    @Override
    public void onMove(MoveEvent e) {
        double deltaXZ = Math.hypot(e.getTo().getX() - e.getFrom().getX(), e.getTo().getZ() - e.getFrom().getZ());

        if (deltaXZ > 0.63 && !data.getExemptData().get(ExemptType.TELEPORT))
            fail("deltaXZ=" + deltaXZ);
    }
}
