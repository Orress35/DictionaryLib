package com.dictionary.lib.check.impl.speed;

import com.dictionary.lib.check.Check;
import com.dictionary.lib.check.CheckInfo;
import com.dictionary.lib.data.PlayerData;
import com.dictionary.lib.data.exempt.ExemptType;
import com.dictionary.lib.event.impl.MoveEvent;

@CheckInfo(name = "Speed (Friction)")
public class SpeedFriction extends Check {
    public SpeedFriction(PlayerData data) {
        super(data);
    }

    double lastDeltaXZ, deltaXZ;
    int airTicks;

    @Override
    public void onMove(MoveEvent e) {
        lastDeltaXZ = deltaXZ;
        deltaXZ = Math.hypot(e.getTo().getX() - e.getFrom().getX(), e.getTo().getZ() - e.getFrom().getZ());

        airTicks = e.isOnGround() ? 0 : Math.min(airTicks + 1, 100);

        if (deltaXZ > lastDeltaXZ * 0.91f + 0.026f && airTicks > 1 && !data.getExemptData().get(ExemptType.TELEPORT))
            fail("deltaXZ=" + deltaXZ);
    }
}
