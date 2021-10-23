package com.dictionary.lib.check.impl.step;

import com.dictionary.lib.check.Check;
import com.dictionary.lib.check.CheckInfo;
import com.dictionary.lib.data.PlayerData;
import com.dictionary.lib.data.exempt.ExemptType;
import com.dictionary.lib.event.impl.MoveEvent;

@CheckInfo(name = "Step (Height)")
public class StepHeight extends Check {
    public StepHeight(PlayerData data) {
        super(data);
    }

    @Override
    public void onMove(MoveEvent e) {
        double deltaY = e.getTo().getY() - e.getFrom().getY();

        if (deltaY > 0.6 && e.isOnGround() && !data.getExemptData().get(ExemptType.TELEPORT))
            fail("deltaY=" + deltaY);
    }
}
