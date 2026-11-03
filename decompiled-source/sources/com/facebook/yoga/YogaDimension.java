package com.facebook.yoga;
/* loaded from: classes8.dex */
public enum YogaDimension {
    WIDTH(0),
    HEIGHT(1);
    
    private final int mIntValue;

    YogaDimension(int i2) {
        this.mIntValue = i2;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaDimension fromInt(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return HEIGHT;
            }
            throw new IllegalArgumentException("Unknown enum value: " + i2);
        }
        return WIDTH;
    }
}
