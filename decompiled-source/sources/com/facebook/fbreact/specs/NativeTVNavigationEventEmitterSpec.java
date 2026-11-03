package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes8.dex */
public abstract class NativeTVNavigationEventEmitterSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @ReactMethod
    public abstract void addListener(String str);

    @ReactMethod
    public abstract void removeListeners(double d2);

    public NativeTVNavigationEventEmitterSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
