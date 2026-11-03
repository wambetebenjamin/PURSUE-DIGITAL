package com.bytedance.ttgame.module.gameprotect.api;
/* loaded from: classes6.dex */
public interface PopCheckCodeCallback {
    public static final int CLOSE_BY_CLICK_BUTTON = 0;
    public static final int CLOSE_BY_CLICK_SCREEN = 1;
    public static final int CLOSE_BY_ESCAPE = 2;

    default void dialogOnClose(int i2) {
    }

    default void dialogOnError(String str) {
    }

    default void dialogOnReady() {
    }

    default void dialogOnResult(boolean z, String str) {
    }
}
