package com.bytedance.ttgame.module.rn.modules;

import com.bytedance.react.framework.modules.BridgeLogUtil;
import com.bytedance.sdk.account.platform.base.i;
import com.bytedance.ttgame.module.rn.utils.RNHttpRequestUtil;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import java.util.HashMap;
import java.util.Iterator;
import javax.annotation.Nonnull;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class RNHttpRequestModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "RNHttpRequestModule";
    private static final String TAG = "RNHttpRequestModule";

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "RNHttpRequestModule";
    }

    public RNHttpRequestModule(@Nonnull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void sendRequestWithTimeout(String str, String str2, ReadableMap readableMap, ReadableMap readableMap2, int i2, ReadableMap readableMap3, final Promise promise) {
        HashMap hashMap;
        JSONObject jSONObject;
        HashMap hashMap2;
        final String str3 = "url:" + str + "&type:" + str2 + "&data:" + readableMap + "&header:" + readableMap2 + "&isJson:" + i2;
        BridgeLogUtil.reportBegin(getName() + "-getRequest", str3);
        if (readableMap != null) {
            hashMap = new HashMap();
            jSONObject = new JSONObject(readableMap.toHashMap());
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    hashMap.put(next, jSONObject.get(next).toString());
                } catch (JSONException unused) {
                }
            }
        } else {
            hashMap = null;
            jSONObject = null;
        }
        if (readableMap2 != null) {
            hashMap2 = new HashMap();
            JSONObject jSONObject2 = new JSONObject(readableMap2.toHashMap());
            Iterator<String> keys2 = jSONObject2.keys();
            while (keys2.hasNext()) {
                String next2 = keys2.next();
                try {
                    hashMap2.put(next2, jSONObject2.get(next2).toString());
                } catch (JSONException unused2) {
                }
            }
        } else {
            hashMap2 = null;
        }
        HashMap<String, Object> hashMap3 = new HashMap<>();
        if (readableMap3 != null) {
            hashMap3 = readableMap3.toHashMap();
        }
        RNHttpRequestUtil.sendRequest(str, str2, hashMap, hashMap2, jSONObject == null ? "" : jSONObject.toString(), i2, hashMap3, new RNHttpRequestUtil.HttpRequestListener() { // from class: com.bytedance.ttgame.module.rn.modules.RNHttpRequestModule.1
            @Override // com.bytedance.ttgame.module.rn.utils.RNHttpRequestUtil.HttpRequestListener
            public void onSuccess(Object obj) {
                promise.resolve(obj);
                BridgeLogUtil.reportEnd(RNHttpRequestModule.this.getName() + "-getRequest", str3);
            }

            @Override // com.bytedance.ttgame.module.rn.utils.RNHttpRequestUtil.HttpRequestListener
            public void onFail(String str4) {
                promise.reject(i.a.ERROR_CODE_UNKNOW, str4);
                BridgeLogUtil.reportError(RNHttpRequestModule.this.getName() + "-getRequest", str3, str4);
            }

            @Override // com.bytedance.ttgame.module.rn.utils.RNHttpRequestUtil.HttpRequestListener
            public void onError(Exception exc) {
                promise.reject(i.a.ERROR_CODE_NOT_SUPPORT, exc);
                BridgeLogUtil.reportError(RNHttpRequestModule.this.getName() + "-getRequest", str3, exc == null ? "" : exc.getMessage());
            }
        });
    }

    @ReactMethod
    public void sendRequest(String str, String str2, ReadableMap readableMap, ReadableMap readableMap2, int i2, final Promise promise) {
        HashMap hashMap;
        JSONObject jSONObject;
        HashMap hashMap2;
        final String str3 = "url:" + str + "&type:" + str2 + "&data:" + readableMap + "&header:" + readableMap2 + "&isJson:" + i2;
        BridgeLogUtil.reportBegin(getName() + "-getRequest", str3);
        if (readableMap != null) {
            hashMap = new HashMap();
            jSONObject = new JSONObject(readableMap.toHashMap());
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    hashMap.put(next, jSONObject.get(next).toString());
                } catch (JSONException unused) {
                }
            }
        } else {
            hashMap = null;
            jSONObject = null;
        }
        if (readableMap2 != null) {
            hashMap2 = new HashMap();
            JSONObject jSONObject2 = new JSONObject(readableMap2.toHashMap());
            Iterator<String> keys2 = jSONObject2.keys();
            while (keys2.hasNext()) {
                String next2 = keys2.next();
                try {
                    hashMap2.put(next2, jSONObject2.get(next2).toString());
                } catch (JSONException unused2) {
                }
            }
        } else {
            hashMap2 = null;
        }
        RNHttpRequestUtil.sendRequest(str, str2, hashMap, hashMap2, jSONObject == null ? "" : jSONObject.toString(), i2, new RNHttpRequestUtil.HttpRequestListener() { // from class: com.bytedance.ttgame.module.rn.modules.RNHttpRequestModule.2
            @Override // com.bytedance.ttgame.module.rn.utils.RNHttpRequestUtil.HttpRequestListener
            public void onSuccess(Object obj) {
                promise.resolve(obj);
                BridgeLogUtil.reportEnd(RNHttpRequestModule.this.getName() + "-getRequest", str3);
            }

            @Override // com.bytedance.ttgame.module.rn.utils.RNHttpRequestUtil.HttpRequestListener
            public void onFail(String str4) {
                promise.reject(i.a.ERROR_CODE_UNKNOW, str4);
                BridgeLogUtil.reportError(RNHttpRequestModule.this.getName() + "-getRequest", str3, str4);
            }

            @Override // com.bytedance.ttgame.module.rn.utils.RNHttpRequestUtil.HttpRequestListener
            public void onError(Exception exc) {
                promise.reject(i.a.ERROR_CODE_NOT_SUPPORT, exc);
                BridgeLogUtil.reportError(RNHttpRequestModule.this.getName() + "-getRequest", str3, exc == null ? "" : exc.getMessage());
            }
        });
    }
}
