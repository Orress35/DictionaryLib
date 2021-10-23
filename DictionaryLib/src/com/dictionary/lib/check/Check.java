package com.dictionary.lib.check;

import com.dictionary.lib.data.PlayerData;
import com.dictionary.lib.event.CheatEventListener;
import com.dictionary.lib.event.impl.CheatEvent;
import com.dictionary.lib.event.impl.MoveEvent;

public class Check {
    private final String name;
    protected final PlayerData data;

    protected double vl;

    public Check(PlayerData data) {
        this.data = data;

        CheckInfo info = this.getClass().getAnnotation(CheckInfo.class);
        this.name = info.name();
    }

    protected void fail(String info) {
        vl++;

        for (CheatEventListener listener: data.getDictionary().getListeners())
            listener.onPlayerCheat(new CheatEvent(data.getPlayer(), this, info, vl));
    }

    public void onMove(MoveEvent e) {}

    public String getName() {
        return name;
    }

    public void resetViolations() {
        vl = 0;
    }

    public void setViolations(double val) {
        vl = val;
    }

    public void removeViolations(double val) {
        vl = Math.max(vl - val, 0);
    }

    public double getViolations() {
        return vl;
    }
}
