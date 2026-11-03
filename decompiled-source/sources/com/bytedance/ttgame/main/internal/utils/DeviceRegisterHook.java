package com.bytedance.ttgame.main.internal.utils;
/* loaded from: classes3.dex */
public class DeviceRegisterHook {
    private static final String TAG = "DeviceRegisterHook";
    private static volatile boolean sIsEnableHookMethod = false;

    public static void updateHookConfig(boolean z) {
        sIsEnableHookMethod = z;
    }
}
