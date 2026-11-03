package com.bytedance.react.framework.core;
/* loaded from: classes2.dex */
public class WindowStateManager {
    private static final String EVENT_NAME = "WINDOW_STATE_EVENT";

    /* loaded from: classes2.dex */
    public enum WindowState {
        RESUME,
        PAUSE,
        PAGE_RESUME,
        PAGE_PAUSE
    }

    public static void sendStateToRN(String str, WindowState windowState) {
        if (windowState != null && windowState.toString().equals(WindowState.RESUME.toString())) {
            BRNWindowManager.sendEventToRN(EVENT_NAME, WindowState.PAGE_RESUME.toString(), str);
            BRNWindowManager.sendEventToRN(EVENT_NAME, WindowState.RESUME.toString(), str);
        } else if (windowState == null || !windowState.toString().equals(WindowState.PAUSE.toString())) {
        } else {
            BRNWindowManager.sendEventToRN(EVENT_NAME, WindowState.PAGE_PAUSE.toString(), str);
            BRNWindowManager.sendEventToRN(EVENT_NAME, WindowState.PAUSE.toString(), str);
        }
    }
}
