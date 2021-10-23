package com.dictionary.lib.event.impl;

import com.dictionary.lib.event.Event;
import com.dictionary.lib.util.Loc;

public class TeleportEvent extends Event {
    private final Loc location;

    public TeleportEvent(Loc location) {
        this.location = location;
    }

    public Loc getLocation() {
        return location;
    }
}
