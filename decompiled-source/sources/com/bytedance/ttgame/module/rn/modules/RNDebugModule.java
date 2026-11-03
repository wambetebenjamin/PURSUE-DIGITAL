package com.bytedance.ttgame.module.rn.modules;

import com.bytedance.ttgame.module.rn.utils.RNDebugUtil;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import javax.annotation.Nonnull;
/* loaded from: classes6.dex */
public class RNDebugModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "RNDebugModule";

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return MODULE_NAME;
    }

    public RNDebugModule(@Nonnull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void setRNDebug(boolean z, Promise promise) {
        RNDebugUtil.setRNDebug(getReactApplicationContext(), z);
        if (promise != null) {
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void getRNDebug(Promise promise) {
        if (promise != null) {
            promise.resolve(Boolean.valueOf(RNDebugUtil.getRNDebug(getReactApplicationContext())));
        }
    }
}
