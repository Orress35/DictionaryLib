package com.dictionary.lib.data.exempt;

import java.util.ArrayList;
import java.util.List;

public class ExemptData {
    private final List<ExemptType> exempts = new ArrayList<>();

    public void set(ExemptType type, boolean value) {
        if (value)
            exempts.remove(type);
        else if (!exempts.contains(type))
            exempts.add(type);
    }

    public boolean get(ExemptType... types) {
        for (ExemptType type: types) {
            if (exempts.contains(type))
                return true;
        }

        return false;
    }
}
