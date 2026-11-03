package com.facebook.react.bridge;

import android.view.View;
/* loaded from: classes8.dex */
public interface UIManager extends JSIModule, PerformanceCounter {
    <T extends View> int addRootView(T t, WritableMap writableMap, String str);

    void dispatchCommand(int i2, int i3, ReadableArray readableArray);

    void dispatchCommand(int i2, String str, ReadableArray readableArray);

    void sendAccessibilityEvent(int i2, int i3);

    void setAllowImmediateUIOperationExecution(boolean z);

    void synchronouslyUpdateViewOnUIThread(int i2, ReadableMap readableMap);

    void updateRootLayoutSpecs(int i2, int i3, int i4);
}
