package com.bytedance.ttgame.module.dynamic.api;

import android.app.Activity;
import android.view.Window;
/* loaded from: classes5.dex */
public interface IDynamicWindow {
    void close();

    Window getAndroidWindow();

    String getParentWindowCallbackID();

    String getRNUrl();

    String getWindowToken();

    boolean hide();

    boolean isShow();

    boolean onBackPress();

    void sentMessageToRN(String str, String str2);

    boolean show();

    void showWindow(Activity activity);
}
