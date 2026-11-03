package com.facebook.yoga;
/* loaded from: classes8.dex */
public enum YogaOverflow {
    VISIBLE(0),
    HIDDEN(1),
    SCROLL(2);
    
    private final int mIntValue;

    YogaOverflow(int i2) {
        this.mIntValue = i2;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaOverflow fromInt(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return SCROLL;
                }
                throw new IllegalArgumentException("Unknown enum value: " + i2);
            }
            return HIDDEN;
        }
        return VISIBLE;
    }
}
