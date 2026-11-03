package com.bytedance.react.framework.modules;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import javax.annotation.Nonnull;
@ReactModule(name = AuthApi.AUTH_MODULE_NAME)
/* loaded from: classes2.dex */
public class AuthApi extends ReactContextBaseJavaModule {
    public static final String AUTH_MODULE_NAME = "AuthApi";

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return AUTH_MODULE_NAME;
    }

    public AuthApi(@Nonnull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getDouYinAuthCode(String str, Callback callback) {
        BridgeLogUtil.reportBegin(getName() + "-getDouYinAuthCode", str);
        BridgeLogUtil.reportEnd(getName() + "-getDouYinAuthCode", str);
    }
}
