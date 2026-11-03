package com.bytedance.ttgame.module.firebase.bridge;

import android.os.Bundle;
import com.bytedance.ttgame.framework.gbridge.IApplicationProvider;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeMethod;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeParam;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import com.bytedance.ttgame.framework.gbridge.plugin.SdkLog;
import com.bytedance.ttgame.framework.gbridge.spi.ServiceManager;
import com.bytedance.ttgame.module.firebase.api.IFirebaseService;
import java.util.Iterator;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class FirebaseModule implements BaseModule {
    private static final String GET_REMOTE_CONFIG_BOOLEAN_VALUE = "getFirebaseRemoteConfigBooleanValueForKey";
    private static final String GET_REMOTE_CONFIG_DOUBLE_VALUE = "getFirebaseRemoteConfigDoubleValueForKey";
    private static final String GET_REMOTE_CONFIG_LONG_VALUE = "getFirebaseRemoteConfigLongValueForKey";
    private static final String GET_REMOTE_CONFIG_STRING_VALUE = "getFirebaseRemoteConfigStringValueForKey";
    private static final String SEND_ANALYTICS_EVENT = "sendAnalyticsEvent";
    private static final String SEND_PAY_ANALYTICS_EVENT = "sendPayAnalyticsEvent";
    private static final String SET_FIREBASE_REMOTE_CONFIG_DEFAULTS = "setFirebaseRemoteConfigDefaults";
    private static final String SET_FIREBASE_USER_ID = "setFirebaseUserId";
    private static final String SET_FIREBASE_USER_PROPERTY = "setFirebaseUserProperty";
    private static final String TAG = "FirebaseModule";
    private IApplicationProvider app;
    private String mTunnel;

    public FirebaseModule(String str, IApplicationProvider iApplicationProvider) {
        this.mTunnel = str;
        this.app = iApplicationProvider;
    }

    @GBridgeMethod(callName = SEND_ANALYTICS_EVENT)
    public void sendAnalyticsEvent(@GBridgeParam("event") String str, @GBridgeParam("params") JSONObject jSONObject) {
        SdkLog.v(TAG, "event = " + str + " & params =" + jSONObject.toString());
        Bundle bundle = new Bundle();
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next.equals("value")) {
                    bundle.putFloat(next, (float) jSONObject.getDouble(next));
                } else {
                    bundle.putString(next, jSONObject.getString(next));
                }
            }
            SdkLog.v(TAG, "bundle = " + bundle.toString());
            ((IFirebaseService) ServiceManager.get().getService(IFirebaseService.class)).sendAnalyticsEvent(this.app.getApplication(), str, bundle);
        } catch (Exception e2) {
            SdkLog.w(TAG, e2.toString());
        }
    }

    @GBridgeMethod(callName = SET_FIREBASE_USER_ID)
    public void setFirebaseUserId(@GBridgeParam("userId") String str) {
        ((IFirebaseService) ServiceManager.get().getService(IFirebaseService.class)).setFirebaseUserId(this.app.getApplication(), str);
    }

    @GBridgeMethod(callName = SEND_PAY_ANALYTICS_EVENT)
    public void sendPayAnalyticsEvent(@GBridgeParam("event") String str, @GBridgeParam("value") double d2, @GBridgeParam("currency") String str2) {
        SdkLog.v(TAG, "event = " + str + " & value =" + d2 + " & currency = " + str2);
        ((IFirebaseService) ServiceManager.get().getService(IFirebaseService.class)).sendPayAnalyticsEvent(this.app.getApplication(), str, (float) d2, str2);
    }

    @GBridgeMethod(callName = SET_FIREBASE_REMOTE_CONFIG_DEFAULTS)
    public void setFirebaseRemoteConfigDefaults(@GBridgeParam("defaultConfig") JSONObject jSONObject) {
        SdkLog.v(TAG, "params=" + jSONObject);
        ((IFirebaseService) ServiceManager.get().getService(IFirebaseService.class)).setFirebaseRemoteConfigDefaults(jSONObject);
    }

    @GBridgeMethod(callName = GET_REMOTE_CONFIG_STRING_VALUE, sync = true)
    public String getFirebaseRemoteConfigStringValueForKey(@GBridgeParam("key") String str) {
        SdkLog.v(TAG, "key=" + str);
        return ((IFirebaseService) ServiceManager.get().getService(IFirebaseService.class)).getFirebaseRemoteConfigStringValueForKey(str);
    }

    @GBridgeMethod(callName = GET_REMOTE_CONFIG_BOOLEAN_VALUE, sync = true)
    public boolean getFirebaseRemoteConfigBooleanValueForKey(@GBridgeParam("key") String str) {
        SdkLog.v(TAG, "key=" + str);
        return ((IFirebaseService) ServiceManager.get().getService(IFirebaseService.class)).getFirebaseRemoteConfigBooleanValueForKey(str);
    }

    @GBridgeMethod(callName = GET_REMOTE_CONFIG_DOUBLE_VALUE, sync = true)
    public double getFirebaseRemoteConfigDoubleValueForKey(@GBridgeParam("key") String str) {
        SdkLog.v(TAG, "key=" + str);
        return ((IFirebaseService) ServiceManager.get().getService(IFirebaseService.class)).getFirebaseRemoteConfigDoubleValueForKey(str);
    }

    @GBridgeMethod(callName = GET_REMOTE_CONFIG_LONG_VALUE, sync = true)
    public long getFirebaseRemoteConfigLongValueForKey(@GBridgeParam("key") String str) {
        SdkLog.v(TAG, "key=" + str);
        return ((IFirebaseService) ServiceManager.get().getService(IFirebaseService.class)).getFirebaseRemoteConfigLongValueForKey(str);
    }

    @Override // com.bytedance.ttgame.framework.gbridge.IBridgeModule
    public String getBridgeTunnel() {
        return this.mTunnel;
    }

    @GBridgeMethod(callName = SET_FIREBASE_USER_PROPERTY)
    public void setFirebaseProperty(@GBridgeParam("propertyName") String str, @GBridgeParam("propertyValue") String str2) {
        ((IFirebaseService) ServiceManager.get().getService(IFirebaseService.class)).setUserProperty(this.app.getApplication(), str, str2);
    }
}
