package com.bytedance.ttgame.module.appsflyer.bridge;

import android.util.Log;
import com.bytedance.ttgame.framework.gbridge.GBridgeManager;
import com.bytedance.ttgame.framework.gbridge.IApplicationProvider;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeMethod;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeParam;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import com.bytedance.ttgame.framework.gbridge.spi.ServiceManager;
import com.bytedance.ttgame.module.appsflyer.api.AppsFlyerDataListener;
import com.bytedance.ttgame.module.appsflyer.api.AppsFlyerDeepLinkCallback;
import com.bytedance.ttgame.module.appsflyer.api.IAppsFlyerService;
import java.util.Map;
import org.json.JSONObject;
import timber.log.Timber;
/* loaded from: classes4.dex */
public class AppsFlyerModule implements BaseModule {
    public static final String GetAppsFlyerUID = "getAppsFlyerUID";
    public static final String REQUEST_APPSFLYER_CONVERSION_DATA_RESULT = "requestAppsFlyerConversionDataResult";
    public static final String REQUEST_APPSFLYER_DATA_ERROR = "requestAppsFlyerDataError";
    public static final String REQUEST_APPSFLYER_DATA_RESULT = "requestAppsFlyerDataResult";
    public static final String SETCUSTOMID = "setCustomId";
    public static final String START_APPSFLYER_SDK = "startAppsFlyerSDK";
    private static final String TAG = "AppsflyerModule";
    private static boolean isAppsFlyerModuleRegisted = false;
    private static JSONObject mConversionData;
    private static JSONObject mData;
    private static String mTunnel;
    private IApplicationProvider app;

    public AppsFlyerModule(String str, IApplicationProvider iApplicationProvider) {
        mTunnel = str;
        this.app = iApplicationProvider;
        isAppsFlyerModuleRegisted = true;
        setAFDeepLinkDataCallback();
        setAFDataCallback();
        if (mData != null) {
            Timber.tag(TAG).v(REQUEST_APPSFLYER_DATA_RESULT, new Object[0]);
            JSONObject jSONObject = new JSONObject();
            BaseModule.add(jSONObject, "data", mData);
            GBridgeManager.registerEvent(mTunnel, REQUEST_APPSFLYER_DATA_RESULT);
            GBridgeManager.sendEvent(mTunnel, REQUEST_APPSFLYER_DATA_RESULT, jSONObject);
            mData = null;
        }
        if (mConversionData != null) {
            Timber.tag(TAG).v(REQUEST_APPSFLYER_CONVERSION_DATA_RESULT, new Object[0]);
            JSONObject jSONObject2 = new JSONObject();
            BaseModule.add(jSONObject2, "data", mConversionData);
            GBridgeManager.registerEvent(mTunnel, REQUEST_APPSFLYER_CONVERSION_DATA_RESULT);
            GBridgeManager.sendEvent(mTunnel, REQUEST_APPSFLYER_CONVERSION_DATA_RESULT, jSONObject2);
            mConversionData = null;
        }
    }

    @Override // com.bytedance.ttgame.framework.gbridge.IBridgeModule
    public String getBridgeTunnel() {
        return mTunnel;
    }

    public static void setAFDeepLinkDataCallback() {
        Log.i(TAG, "regsiter appsflyer deeplink event callback");
        ((IAppsFlyerService) ServiceManager.get().getService(IAppsFlyerService.class)).setAFDeepLinkDataCallback(new AppsFlyerDeepLinkCallback() { // from class: com.bytedance.ttgame.module.appsflyer.bridge.AppsFlyerModule.1
            @Override // com.bytedance.ttgame.module.appsflyer.api.AppsFlyerDeepLinkCallback
            public void onAppsFlyerDeepLinking(Map<String, String> map) {
                Log.d(AppsFlyerModule.TAG, "[GSDK-AFBridge-onAppsFlyerDeepLinking]");
                Timber.tag(AppsFlyerModule.TAG).v("map=" + map, new Object[0]);
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                if (map != null) {
                    BaseModule.add(jSONObject2, "link", map.get("link"));
                    BaseModule.add(jSONObject2, "deep_link_value", map.get("deep_link_value"));
                }
                if (!AppsFlyerModule.isAppsFlyerModuleRegisted) {
                    JSONObject unused = AppsFlyerModule.mData = jSONObject2;
                    return;
                }
                Timber.tag(AppsFlyerModule.TAG).v(AppsFlyerModule.REQUEST_APPSFLYER_DATA_RESULT, new Object[0]);
                BaseModule.add(jSONObject, "data", jSONObject2);
                Log.d(AppsFlyerModule.TAG, "[GSDK-AFBridge-onAppsFlyerDeepLinking Send]");
                GBridgeManager.registerEvent(AppsFlyerModule.mTunnel, AppsFlyerModule.REQUEST_APPSFLYER_DATA_RESULT);
                GBridgeManager.sendEvent(AppsFlyerModule.mTunnel, AppsFlyerModule.REQUEST_APPSFLYER_DATA_RESULT, jSONObject);
            }
        });
    }

    public static void setAFDataCallback() {
        Log.i(TAG, "regsiter appsflyer conversion-data event callback");
        ((IAppsFlyerService) ServiceManager.get().getService(IAppsFlyerService.class)).setAFDataCallback(new AppsFlyerDataListener() { // from class: com.bytedance.ttgame.module.appsflyer.bridge.AppsFlyerModule.2
            @Override // com.bytedance.ttgame.module.appsflyer.api.AppsFlyerDataListener
            public void onConversionDataSuccess(Map<String, Object> map) {
                Log.d(AppsFlyerModule.TAG, "[GSDK-AFBridge-onConversionDataSuccess]");
                Timber.tag(AppsFlyerModule.TAG).v("onConversionDataSuccess, map=" + map, new Object[0]);
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                if (map != null) {
                    for (String str : map.keySet()) {
                        BaseModule.add(jSONObject2, str, map.get(str));
                    }
                }
                if (!AppsFlyerModule.isAppsFlyerModuleRegisted) {
                    JSONObject unused = AppsFlyerModule.mConversionData = jSONObject2;
                    return;
                }
                Timber.tag(AppsFlyerModule.TAG).v(AppsFlyerModule.REQUEST_APPSFLYER_CONVERSION_DATA_RESULT, new Object[0]);
                BaseModule.add(jSONObject, "data", jSONObject2);
                BaseModule.add(jSONObject, "code", 0);
                BaseModule.add(jSONObject, "message", "success");
                Log.d(AppsFlyerModule.TAG, "[GSDK-AFBridge-onConversionDataSuccess Send]");
                GBridgeManager.registerEvent(AppsFlyerModule.mTunnel, AppsFlyerModule.REQUEST_APPSFLYER_CONVERSION_DATA_RESULT);
                GBridgeManager.sendEvent(AppsFlyerModule.mTunnel, AppsFlyerModule.REQUEST_APPSFLYER_CONVERSION_DATA_RESULT, jSONObject);
            }

            @Override // com.bytedance.ttgame.module.appsflyer.api.AppsFlyerDataListener
            public void onConversionDataFail(String str) {
                if (AppsFlyerModule.isAppsFlyerModuleRegisted) {
                    Timber.tag(AppsFlyerModule.TAG).e("onConversionDataFail, errMsg: " + str, new Object[0]);
                    JSONObject jSONObject = new JSONObject();
                    BaseModule.add(jSONObject, "code", -1);
                    BaseModule.add(jSONObject, "message", str);
                    GBridgeManager.registerEvent(AppsFlyerModule.mTunnel, AppsFlyerModule.REQUEST_APPSFLYER_CONVERSION_DATA_RESULT);
                    GBridgeManager.sendEvent(AppsFlyerModule.mTunnel, AppsFlyerModule.REQUEST_APPSFLYER_CONVERSION_DATA_RESULT, jSONObject);
                }
            }

            @Override // com.bytedance.ttgame.module.appsflyer.api.AppsFlyerDataListener
            public void onAttributionFailure(String str) {
                if (AppsFlyerModule.isAppsFlyerModuleRegisted) {
                    Timber.tag(AppsFlyerModule.TAG).e("onAttributionFailure, errMsg: " + str, new Object[0]);
                    JSONObject jSONObject = new JSONObject();
                    BaseModule.add(jSONObject, "code", -1);
                    BaseModule.add(jSONObject, "message", str);
                    GBridgeManager.registerEvent(AppsFlyerModule.mTunnel, AppsFlyerModule.REQUEST_APPSFLYER_DATA_ERROR);
                    GBridgeManager.sendEvent(AppsFlyerModule.mTunnel, AppsFlyerModule.REQUEST_APPSFLYER_DATA_ERROR, jSONObject);
                }
            }
        });
    }

    @GBridgeMethod(callName = GetAppsFlyerUID, sync = true)
    public String getAppsFlyerUID() {
        return ((IAppsFlyerService) ServiceManager.get().getService(IAppsFlyerService.class)).getAppsFlyerUID(this.app.getApplication());
    }

    @GBridgeMethod(callName = SETCUSTOMID)
    public void setCustomId(@GBridgeParam("customId") String str) {
        ((IAppsFlyerService) ServiceManager.get().getService(IAppsFlyerService.class)).setCustomId(str);
    }

    @GBridgeMethod(callName = START_APPSFLYER_SDK)
    public void startAfSdk() {
        ((IAppsFlyerService) ServiceManager.get().getService(IAppsFlyerService.class)).startAppsflyer(this.app.getCurrentActivity());
    }
}
