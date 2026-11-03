package com.facebook.yoga;
/* loaded from: classes8.dex */
public enum YogaExperimentalFeature {
    WEB_FLEX_BASIS(0);
    
    private final int mIntValue;

    YogaExperimentalFeature(int i2) {
        this.mIntValue = i2;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaExperimentalFeature fromInt(int i2) {
        if (i2 == 0) {
            return WEB_FLEX_BASIS;
        }
        throw new IllegalArgumentException("Unknown enum value: " + i2);
    }
}
