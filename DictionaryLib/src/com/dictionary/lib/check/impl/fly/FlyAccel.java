package com.dictionary.lib.check.impl.fly;

import com.dictionary.lib.check.Check;
import com.dictionary.lib.check.CheckInfo;
import com.dictionary.lib.data.PlayerData;
import com.dictionary.lib.data.exempt.ExemptType;
import com.dictionary.lib.event.impl.MoveEvent;

@CheckInfo(name = "Fly (Accel)")
public class FlyAccel extends Check {
    public FlyAccel(PlayerData data) {
        super(data);
    }

    double deltaY, lastDeltaY;
    int airTicks;

    @Override
    public void onMove(MoveEvent e) {
        lastDeltaY = deltaY;
        deltaY = e.getTo().getY() - e.getFrom().getY();

        airTicks = e.isOnGround() ? 0 : Math.min(airTicks + 1, 100);

        double accelY = deltaY - lastDeltaY;

        if (accelY >= 0 && airTicks > 2 && !data.getExemptData().get(ExemptType.TELEPORT))
            fail("accelY=" + accelY);
    }
}
