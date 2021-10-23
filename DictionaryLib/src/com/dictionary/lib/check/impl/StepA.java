package com.dictionary.lib.check.impl;

import com.dictionary.lib.check.Check;
import com.dictionary.lib.check.CheckInfo;
import com.dictionary.lib.data.PlayerData;
import com.dictionary.lib.event.impl.MoveEvent;

@CheckInfo(name = "Step (A)")
public class StepA extends Check {
    public StepA(PlayerData data) {
        super(data);
    }

    @Override
    public void onMove(MoveEvent e) {
        double deltaY = e.getTo().getY() - e.getFrom().getY();

        if (deltaY > 0.6 && e.isOnGround())
            fail("deltaY=" + deltaY);
    }
}
