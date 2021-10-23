package com.dictionary.lib.check.impl.fly;

import com.dictionary.lib.check.Check;
import com.dictionary.lib.check.CheckInfo;
import com.dictionary.lib.data.PlayerData;
import com.dictionary.lib.event.impl.MoveEvent;

@CheckInfo(name = "Fly (Stable)")
public class FlyStable extends Check {
    public FlyStable(PlayerData data) {
        super(data);
    }

    double deltaY, lastDeltaY, lastLastDeltaY;
    int airTicks;

    @Override
    public void onMove(MoveEvent e) {
        lastLastDeltaY = lastDeltaY;
        lastDeltaY = deltaY;
        deltaY = e.getTo().getY() - e.getFrom().getY();

        airTicks = e.isOnGround() ? 0 : Math.min(airTicks + 1, 100);

        if (deltaY == lastDeltaY && lastDeltaY == lastLastDeltaY && airTicks > 2)
            fail("deltaY=" + deltaY);
    }
}
