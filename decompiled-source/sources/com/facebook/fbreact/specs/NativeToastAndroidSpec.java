package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public abstract class NativeToastAndroidSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    protected abstract Map<String, Object> getTypedExportedConstants();

    @ReactMethod
    public abstract void show(String str, double d2);

    @ReactMethod
    public abstract void showWithGravity(String str, double d2, double d3);

    @ReactMethod
    public abstract void showWithGravityAndOffset(String str, double d2, double d3, double d4, double d5);

    public NativeToastAndroidSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public final Map<String, Object> getConstants() {
        return getTypedExportedConstants();
    }
}
