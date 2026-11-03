package com.facebook.react.bridge;

import com.facebook.react.bridge.JSIModule;
/* loaded from: classes8.dex */
public interface JSIModuleSpec<T extends JSIModule> {
    JSIModuleProvider<T> getJSIModuleProvider();

    JSIModuleType getJSIModuleType();
}
