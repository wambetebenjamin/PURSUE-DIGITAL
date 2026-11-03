package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
/* loaded from: classes8.dex */
public interface RCTEventEmitter extends JavaScriptModule {
    void receiveEvent(int i2, String str, WritableMap writableMap);

    void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2);
}
