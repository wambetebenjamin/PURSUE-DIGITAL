package com.facebook.yoga;
/* loaded from: classes8.dex */
public enum YogaDirection {
    INHERIT(0),
    LTR(1),
    RTL(2);
    
    private final int mIntValue;

    YogaDirection(int i2) {
        this.mIntValue = i2;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaDirection fromInt(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return RTL;
                }
                throw new IllegalArgumentException("Unknown enum value: " + i2);
            }
            return LTR;
        }
        return INHERIT;
    }
}
