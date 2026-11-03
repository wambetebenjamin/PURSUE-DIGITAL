package com.facebook.yoga;
/* loaded from: classes8.dex */
public enum YogaDisplay {
    FLEX(0),
    NONE(1);
    
    private final int mIntValue;

    YogaDisplay(int i2) {
        this.mIntValue = i2;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaDisplay fromInt(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return NONE;
            }
            throw new IllegalArgumentException("Unknown enum value: " + i2);
        }
        return FLEX;
    }
}
