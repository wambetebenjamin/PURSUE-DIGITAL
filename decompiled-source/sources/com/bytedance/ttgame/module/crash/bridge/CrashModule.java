package com.bytedance.ttgame.module.crash.bridge;

import com.bytedance.ttgame.framework.gbridge.IApplicationProvider;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeMethod;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeParam;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import com.bytedance.ttgame.framework.gbridge.plugin.SdkLog;
import com.bytedance.ttgame.framework.gbridge.spi.ServiceManager;
import com.bytedance.ttgame.module.crash.api.ICrashService;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes5.dex */
public class CrashModule implements BaseModule {
    private IApplicationProvider mApplication;
    private String mTunnel;

    public CrashModule(String str) {
        this.mTunnel = str;
    }

    public CrashModule(String str, IApplicationProvider iApplicationProvider) {
        this.mTunnel = str;
        this.mApplication = iApplicationProvider;
    }

    @GBridgeMethod(callName = "requestGMAddTags")
    public void addTags(@GBridgeParam("data") JSONObject jSONObject) {
        SdkLog.i(BaseModule.TAG, "addTags");
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.optString(next));
        }
        ((ICrashService) ServiceManager.get().getService(ICrashService.class)).addTags(hashMap);
    }

    @GBridgeMethod(callName = "requestGMAddTagsByJson")
    public void addTagsByJson(@GBridgeParam("data") String str) {
        SdkLog.i(BaseModule.TAG, "addTagsByJson");
        try {
            JSONObject jSONObject = new JSONObject(str);
            HashMap hashMap = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.optString(next));
            }
            ((ICrashService) ServiceManager.get().getService(ICrashService.class)).addTags(hashMap);
        } catch (Throwable th) {
            SdkLog.e(BaseModule.TAG, "Fail to add tags by json. " + th.toString());
        }
    }

    @GBridgeMethod(callName = "requestGMAddCustomInfo")
    public void addCustomParams(@GBridgeParam("data") JSONObject jSONObject) {
        SdkLog.i(BaseModule.TAG, "addCustomParams");
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.optString(next));
        }
        ((ICrashService) ServiceManager.get().getService(ICrashService.class)).addCustomParams(hashMap);
    }

    @GBridgeMethod(callName = "requestGMAddCustomInfoByJson")
    public void addCustomParamsByJson(@GBridgeParam("data") String str) {
        SdkLog.i(BaseModule.TAG, "addCustomParams");
        try {
            JSONObject jSONObject = new JSONObject(str);
            HashMap hashMap = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.optString(next));
            }
            ((ICrashService) ServiceManager.get().getService(ICrashService.class)).addCustomParams(hashMap);
        } catch (Throwable th) {
            SdkLog.e(BaseModule.TAG, "Fail to add custom info by json. " + th.toString());
        }
    }

    public static HashMap<String, String> toMap(JSONObject jSONObject) throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof String) {
                hashMap.put(next, (String) obj);
            }
        }
        return hashMap;
    }

    @GBridgeMethod(callName = "requestGMLogException")
    public void reportCrash(@GBridgeParam("reason") String str, @GBridgeParam("name") String str2, @GBridgeParam("stack") String str3, @GBridgeParam(optional = true, value = "filters") JSONObject jSONObject, @GBridgeParam(optional = true, value = "params") JSONObject jSONObject2) {
        SdkLog.i(BaseModule.TAG, "reportCrash");
        ICrashService iCrashService = (ICrashService) ServiceManager.get().getService(ICrashService.class);
        if (jSONObject == null && jSONObject2 == null) {
            iCrashService.reportCrash(str, str2, str3);
            return;
        }
        try {
            iCrashService.reportCrash(str2, str, str3, (jSONObject == null || jSONObject.length() <= 0) ? new HashMap<>() : toMap(jSONObject), (jSONObject2 == null || jSONObject2.length() <= 0) ? new HashMap<>() : toMap(jSONObject2));
        } catch (Exception e2) {
            SdkLog.e(BaseModule.TAG, "Fail to report crash with filter. " + e2.getMessage());
        }
    }

    @Override // com.bytedance.ttgame.framework.gbridge.IBridgeModule
    public String getBridgeTunnel() {
        return this.mTunnel;
    }
}
