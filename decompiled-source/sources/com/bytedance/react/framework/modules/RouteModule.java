package com.bytedance.react.framework.modules;

import android.content.Intent;
import android.os.Bundle;
import com.bytedance.react.framework.GReactNativeActivity;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.react.framework.core.BRNManager;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import javax.annotation.Nonnull;
@ReactModule(name = "RouteModule")
/* loaded from: classes2.dex */
public class RouteModule extends ReactContextBaseJavaModule {
    public static final String MODULE_NAME = "RouteModule";
    private static final String TAG = "RouteModule";

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "RouteModule";
    }

    public RouteModule(@Nonnull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void push(String str, Callback callback) {
        BridgeLogUtil.reportBegin(getName() + "-push", str);
        if (str == null || str.isEmpty()) {
            return;
        }
        if (str.startsWith("rn") || str.startsWith("http") || str.startsWith("https")) {
            Bundle parseScheme = parseScheme(str);
            if (parseScheme == null || parseScheme.isEmpty()) {
                return;
            }
            if (parseScheme.getString("pageType").equals("rn")) {
                Intent intent = new Intent(getReactApplicationContext(), GReactNativeActivity.class);
                intent.putExtra("rctModuleName", parseScheme);
                getCurrentActivity().startActivity(intent);
            } else {
                parseScheme.getString("pageType").equals("h5");
            }
        } else {
            BRNManager.newInstance().parserRoute(str);
        }
        BridgeLogUtil.reportEnd(getName() + "-push", str);
    }

    @ReactMethod
    public void back(Callback callback) {
        BridgeLogUtil.reportBegin(getName() + "-back", "");
        getCurrentActivity().finish();
        BridgeLogUtil.reportEnd(getName() + "-back", "");
    }

    private Bundle parseScheme(String str) {
        String str2;
        if (str == null && str.isEmpty()) {
            return null;
        }
        Bundle bundle = new Bundle();
        if (str.startsWith("rn://")) {
            bundle.putString("pageType", "rn");
        } else if (str.startsWith("native://")) {
            bundle.putString("pageType", "native");
        } else if (!str.startsWith("http") && !str.startsWith("https")) {
            return null;
        } else {
            bundle.putString("pageType", "h5");
        }
        String[] split = str.split("//");
        if (str.contains("?")) {
            String[] split2 = split[1].split("\\?");
            str2 = split2[0];
            Bundle bundle2 = new Bundle();
            if (str.contains("&")) {
                for (String str3 : split2[1].split("&")) {
                    String[] split3 = str3.split("=");
                    bundle2.putString(split3[0], split3[1]);
                }
            } else {
                String[] split4 = split2[1].split("=");
                bundle2.putString(split4[0], split4[1]);
            }
            bundle.putBundle("params", bundle2);
        } else {
            str2 = split[1];
        }
        bundle.putString(RNConfig.MODULE_NAME, str2);
        return bundle;
    }
}
