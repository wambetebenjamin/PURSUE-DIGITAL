package com.bytedance.ttgame.sdk.module.utils;
/* loaded from: classes8.dex */
public class AntiShakeUtil {
    private static final int DEFAULT_DISTANCE_TIME = 500;
    private static long mLastClickTime;

    public static boolean isFastDoubleClick() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - mLastClickTime < 500) {
            mLastClickTime = currentTimeMillis;
            return true;
        }
        mLastClickTime = currentTimeMillis;
        return false;
    }
}
