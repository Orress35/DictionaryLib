package com.dictionary.lib.event;

import com.dictionary.lib.event.impl.CheatEvent;

public interface CheatEventListener {
    void onPlayerCheat(CheatEvent e);
}
