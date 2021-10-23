package com.dictionary.lib.event.impl;

import com.dictionary.lib.event.Event;
import com.dictionary.lib.util.Loc;

public class MoveEvent extends Event {
    private final Loc to, from;
    private final boolean onGround;

    public MoveEvent(Loc to, Loc from, boolean onGround) {
        this.to = to;
        this.from = from;
        this.onGround = onGround;
    }

    public Loc getTo() {
        return to;
    }

    public Loc getFrom() {
        return from;
    }

    public boolean isOnGround() {
        return onGround;
    }
}
