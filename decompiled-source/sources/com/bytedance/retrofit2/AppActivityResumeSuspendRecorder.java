package com.bytedance.retrofit2;
/* loaded from: classes2.dex */
public class AppActivityResumeSuspendRecorder {
    private static volatile long sAppActivityResumeState = 0;
    private static volatile boolean sAppActivityResumed = false;

    public static void OnAppActiviyResume() {
        if (sAppActivityResumed) {
            return;
        }
        sAppActivityResumed = true;
        sAppActivityResumeState++;
    }

    public static void OnAppActivitySuspend() {
        if (sAppActivityResumed) {
            sAppActivityResumed = false;
            sAppActivityResumeState++;
        }
    }

    public static long GetAppActivityResumeState() {
        return sAppActivityResumeState;
    }
}
