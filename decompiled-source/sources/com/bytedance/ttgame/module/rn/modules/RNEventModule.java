package com.bytedance.ttgame.module.rn.modules;

import com.bytedance.react.framework.core.BRNManager;
import com.bytedance.sdk.account.platform.base.i;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import javax.annotation.Nonnull;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class RNEventModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "RNEventModule";
    private static final String TAG = "RNEventModule";

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "RNEventModule";
    }

    public RNEventModule(@Nonnull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void trackEvent(String str, ReadableMap readableMap, Promise promise) {
        if (readableMap == null) {
            promise.reject(i.a.ERROR_CODE_UNKNOW, "params is null");
        }
        try {
            BRNManager.newInstance().sendLog(false, str, new JSONObject(readableMap.toHashMap()));
            promise.resolve(true);
        } catch (Exception unused) {
            promise.reject(i.a.ERROR_CODE_UNKNOW, "trackEvent fail");
        }
    }

    @ReactMethod
    public void monitorEvent(String str, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, Promise promise) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        if (readableMap != null) {
            jSONObject = new JSONObject(readableMap.toHashMap());
        } else {
            jSONObject = new JSONObject();
        }
        if (readableMap2 != null) {
            jSONObject2 = new JSONObject(readableMap2.toHashMap());
        } else {
            jSONObject2 = new JSONObject();
        }
        if (readableMap3 != null) {
            jSONObject3 = new JSONObject(readableMap3.toHashMap());
        } else {
            jSONObject3 = new JSONObject();
        }
        try {
            BRNManager.newInstance().reportMonitor(str, jSONObject, jSONObject2, jSONObject3);
            promise.resolve(true);
        } catch (Exception unused) {
            promise.reject(i.a.ERROR_CODE_UNKNOW, "monitorEvent fail");
        }
    }
}
