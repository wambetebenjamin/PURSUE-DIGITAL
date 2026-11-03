package com.facebook.react.modules.core;

import com.facebook.react.bridge.WritableArray;
/* loaded from: classes8.dex */
public interface JavaScriptTimerManager {
    void callIdleCallbacks(double d2);

    void callTimers(WritableArray writableArray);

    void emitTimeDriftWarning(String str);
}
