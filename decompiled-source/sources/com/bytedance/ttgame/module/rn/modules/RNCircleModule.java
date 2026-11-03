package com.bytedance.ttgame.module.rn.modules;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import javax.annotation.Nonnull;
/* loaded from: classes6.dex */
public class RNCircleModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "RNCircleModule";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return MODULE_NAME;
    }

    public RNCircleModule(@Nonnull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
