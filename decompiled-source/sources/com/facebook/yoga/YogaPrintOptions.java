package com.facebook.yoga;
/* loaded from: classes8.dex */
public enum YogaPrintOptions {
    LAYOUT(1),
    STYLE(2),
    CHILDREN(4);
    
    private final int mIntValue;

    YogaPrintOptions(int i2) {
        this.mIntValue = i2;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaPrintOptions fromInt(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 == 4) {
                    return CHILDREN;
                }
                throw new IllegalArgumentException("Unknown enum value: " + i2);
            }
            return STYLE;
        }
        return LAYOUT;
    }
}
