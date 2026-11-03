package com.bytedance.react.framework.core;

import android.app.Activity;
import android.view.Window;
/* loaded from: classes2.dex */
public interface IBRNWindow {
    void close();

    Window getAndroidWindow();

    String getInGameId();

    String getParentWindowCallbackID();

    String getRNUrl();

    int getType();

    String getWindowToken();

    boolean hide();

    boolean isShow();

    boolean onBackPress();

    void sentMessageToRN(String str, String str2);

    boolean show();

    void showWindow(Activity activity);
}
