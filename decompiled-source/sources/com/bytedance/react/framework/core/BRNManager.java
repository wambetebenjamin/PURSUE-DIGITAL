package com.bytedance.react.framework.core;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.react.framework.acitive.BRNActiveManager;
import com.bytedance.react.framework.acitive.ResultCallback;
import com.bytedance.react.framework.model.ShareEventCallback;
import com.bytedance.ttgame.engine.network.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class BRNManager {
    private static BRNManager mBRNAPIManager = null;
    private static BRNActiveManager mBRNActiveManager = null;
    private static ExecutorServiceInterface mBRNExecutorService = null;
    private static BRNExtendCallBack mBRNExtendCallBack = null;
    private static LoggerHelper mBRNLogger = null;
    private static String mSavedDeviceLevel = "";

    /* loaded from: classes2.dex */
    public interface BRNExtendCallBack {
        boolean astcSupport();

        <T> T createRetrofit(Class<T> cls);

        boolean downloadPackage(List<String> list);

        String encrypt(String str);

        String getAccessToken();

        Context getAppContext();

        String getAppID();

        String getAppLanguage();

        Application getApplication();

        Context getApplicatonContext();

        int getBundleMode();

        String getChannel();

        String getChannelOp();

        Bundle getCommonDeviceInfo();

        HashMap getCommonInfo();

        Map<String, Object> getCommonParams();

        Bundle getCommonRiskInfo();

        String getDefinedPackageName();

        String getDeviceLevel();

        int[] getFPSflag();

        String getGlobalVersion();

        Map<String, Object> getGumihoConstantParams();

        String getGumihoHost();

        String getGumihoVersion();

        String getInstallId();

        int getMemoryLevel();

        List<HashMap<String, Object>> getPrefetchData();

        List<Map<String, Object>> getPrefetchImage();

        String getRegisterCountry();

        String getSDKVersion();

        String getServerDeviceId();

        String getServerDeviceIdAgent();

        int getServerRegion();

        String[] getVersionCode(String str, String str2);

        void handleError(Exception exc, Bundle bundle);

        void handleShareResultOnActivityResult(int i2, int i3, Intent intent);

        boolean isBoe();

        boolean isCnFlavor();

        boolean isDebug();

        int isEmulator();

        boolean isLogin();

        boolean isSanbox();

        void logMemoryUsage();

        void logSceneEnd();

        void logSceneEvent(String str, String str2);

        void logSceneStart(Map<String, Object> map);

        void onShareStart(Activity activity, Map<String, Object> map, ShareEventCallback shareEventCallback);

        void parserRoute(String str);

        void pay(Activity activity, HashMap<String, Object> hashMap, PayResultListener payResultListener);

        void postReactException(Exception exc, HashMap hashMap);

        void reportMonitor(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

        <T> b requestNetwork(boolean z, boolean z2, String str, Map<String, Object> map, Map<String, String> map2, Map<String, Object> map3, Class<T> cls, Object obj, Map<String, String> map4, String str2, boolean z3, boolean z4);

        <T> b requestNetworkWithTimeout(boolean z, boolean z2, String str, Map<String, Object> map, Map<String, String> map2, Map<String, Object> map3, Class<T> cls, Object obj, Map<String, String> map4, String str2, boolean z3, boolean z4, HashMap hashMap);

        void sendLog(String str, JSONObject jSONObject);

        void sendLog(boolean z, String str, JSONObject jSONObject);

        void sendMessageToGame(String str, Map map, String str2);

        void sendMonitorCommonLog(JSONObject jSONObject);

        boolean showWebView(Context context, String str, String str2, String str3, boolean z);
    }

    /* loaded from: classes2.dex */
    public interface ExecutorServiceInterface {
        ExecutorService getBackGroundExecutor();

        ExecutorService getDefaultExecutor();

        ExecutorService getIdleExecutor();

        ExecutorService getMainExecutor();

        ScheduledExecutorService getScheduledExecutor();

        ExecutorService getSerialExecutor();
    }

    /* loaded from: classes2.dex */
    public interface LoggerHelper {
        void d(String str, String str2);

        void e(String str, String str2);

        void i(String str, String str2);
    }

    public static BRNManager newInstance() {
        if (mBRNAPIManager == null) {
            mBRNAPIManager = new BRNManager();
        }
        return mBRNAPIManager;
    }

    public void setLogger(LoggerHelper loggerHelper) {
        mBRNLogger = loggerHelper;
    }

    public void setExecutorServiceInterface(ExecutorServiceInterface executorServiceInterface) {
        mBRNExecutorService = executorServiceInterface;
    }

    public LoggerHelper getLogger() {
        return mBRNLogger;
    }

    public ExecutorServiceInterface getExecutorServiceInterface() {
        return mBRNExecutorService;
    }

    public void setActiveActivtManager(BRNActiveManager bRNActiveManager) {
        mBRNActiveManager = bRNActiveManager;
    }

    public void getCommonBundle(String str, ResultCallback resultCallback) {
        BRNActiveManager bRNActiveManager = mBRNActiveManager;
        if (bRNActiveManager != null) {
            bRNActiveManager.getCommonBundle(str, resultCallback);
        }
    }

    public void getActivityBundle(String str, ResultCallback resultCallback) {
        BRNActiveManager bRNActiveManager = mBRNActiveManager;
        if (bRNActiveManager != null) {
            bRNActiveManager.getActivityBundle(str, resultCallback);
        }
    }

    public void getActivityBundleV2(String str, ResultCallback resultCallback) {
        BRNActiveManager bRNActiveManager = mBRNActiveManager;
        if (bRNActiveManager != null) {
            bRNActiveManager.getActivityBundleV2(str, resultCallback);
        }
    }

    public void setExtendCallBack(BRNExtendCallBack bRNExtendCallBack) {
        mBRNExtendCallBack = bRNExtendCallBack;
    }

    public Bundle getCommonRiskInfo() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getCommonRiskInfo();
        }
        return new Bundle();
    }

    public String[] getVersionCode(String str, String str2) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getVersionCode(str, str2);
        }
        return null;
    }

    public int[] getFPSflag() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getFPSflag() : new int[]{0, 0};
    }

    public String getDefinedPackageName() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getDefinedPackageName() : "";
    }

    public String getGumihoHost() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getGumihoHost() : "";
    }

    public Map<String, Object> getGumihoConstantParams() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getGumihoConstantParams();
        }
        return new HashMap();
    }

    public String getChannel() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getChannel() : "";
    }

    public String getChannelOp() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getChannelOp() : "";
    }

    public String getAccessToken() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getAccessToken() : "";
    }

    public boolean isLogin() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.isLogin();
        }
        return false;
    }

    public HashMap getCommonInfo() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getCommonInfo();
        }
        return new HashMap();
    }

    public List<Map<String, Object>> getPrefetchImage() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getPrefetchImage();
        }
        return new ArrayList();
    }

    public List<HashMap<String, Object>> getPrefetchData() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getPrefetchData();
        }
        return new ArrayList();
    }

    public String getServerID() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            String str = (String) bRNExtendCallBack.getCommonInfo().get("ServerID");
            return !TextUtils.isEmpty(str) ? str : "";
        }
        return "";
    }

    public String getRoleID() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            String str = (String) bRNExtendCallBack.getCommonInfo().get("RoleID");
            return !TextUtils.isEmpty(str) ? str : "";
        }
        return "";
    }

    public String getServerName() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            String str = (String) bRNExtendCallBack.getCommonInfo().get("ServerName");
            return !TextUtils.isEmpty(str) ? str : "";
        }
        return "";
    }

    public String getRoleName() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            String str = (String) bRNExtendCallBack.getCommonInfo().get("RoleName");
            return !TextUtils.isEmpty(str) ? str : "";
        }
        return "";
    }

    public String getHostOpenID() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            String str = (String) bRNExtendCallBack.getCommonInfo().get("HostOpenID");
            return !TextUtils.isEmpty(str) ? str : "";
        }
        return "";
    }

    public String getHostRoleID() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            String str = (String) bRNExtendCallBack.getCommonInfo().get("HostRoleID");
            return !TextUtils.isEmpty(str) ? str : "";
        }
        return "";
    }

    public void sendMessageToGame(String str, Map map, String str2) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.sendMessageToGame(str, map, str2);
        }
    }

    public String getGumihoVersion() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getGumihoVersion() : "";
    }

    public int getBundleMode() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getBundleMode();
        }
        return 1;
    }

    public void postReactException(Exception exc, HashMap hashMap) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.postReactException(exc, hashMap);
        }
    }

    public void sendLog(String str, JSONObject jSONObject) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.sendLog(str, jSONObject);
        }
    }

    public void sendLog(boolean z, String str, JSONObject jSONObject) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.sendLog(z, str, jSONObject);
        }
    }

    public <T> T createRetrofit(Class<T> cls) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return (T) bRNExtendCallBack.createRetrofit(cls);
        }
        return null;
    }

    public <T> b requestNetwork(boolean z, boolean z2, String str, Map<String, Object> map, Map<String, String> map2, Map<String, Object> map3, Class<T> cls, Object obj, Map<String, String> map4, String str2, boolean z3, boolean z4) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.requestNetwork(z, z2, str, map, map2, map3, cls, obj, map4, str2, z3, z4);
        }
        return null;
    }

    public <T> b requestNetworkWithTimeout(boolean z, boolean z2, String str, Map<String, Object> map, Map<String, String> map2, Map<String, Object> map3, Class<T> cls, Object obj, Map<String, String> map4, String str2, boolean z3, boolean z4, HashMap hashMap) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.requestNetworkWithTimeout(z, z2, str, map, map2, map3, cls, obj, map4, str2, z3, z4, hashMap);
        }
        return null;
    }

    public String encrypt(String str) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.encrypt(str);
        }
        return null;
    }

    public String getAppID() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getAppID() : "";
    }

    public Application getApplication() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getApplication();
        }
        return null;
    }

    public void parserRoute(String str) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.parserRoute(str);
        }
    }

    public boolean downloadPackage(List<String> list) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.downloadPackage(list);
        }
        return false;
    }

    public void handleShareResultOnActivityResult(int i2, int i3, Intent intent) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.handleShareResultOnActivityResult(i2, i3, intent);
        }
    }

    public void sendMonitorCommonLog(JSONObject jSONObject) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.sendMonitorCommonLog(jSONObject);
        }
    }

    public String getServerDeviceId() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getServerDeviceId() : "";
    }

    public String getServerDeviceIdAgent() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getServerDeviceIdAgent() : "";
    }

    public int getMemoryLevel() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getMemoryLevel();
        }
        return 0;
    }

    public void reportMonitor(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.reportMonitor(str, jSONObject, jSONObject2, jSONObject3);
        }
    }

    public String getSDKVersion() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getSDKVersion() : "";
    }

    public boolean showWebView(Context context, String str, String str2, String str3, boolean z) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.showWebView(context, str, str2, str3, z);
        }
        return false;
    }

    public String getGlobalVersion() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getGlobalVersion() : "";
    }

    public Context getApplicatonContext() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getApplicatonContext();
        }
        return null;
    }

    public boolean isDebug() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.isDebug();
        }
        return false;
    }

    public Context getAppContext() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getAppContext();
        }
        return null;
    }

    public boolean isCnFlavor() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.isCnFlavor();
        }
        return false;
    }

    public boolean isSanbox() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.isSanbox();
        }
        return false;
    }

    public boolean isBoe() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.isBoe();
        }
        return false;
    }

    public void handleError(Exception exc, Bundle bundle) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.handleError(exc, bundle);
        }
    }

    public int isEmulator() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.isEmulator();
        }
        return -1;
    }

    public Map<String, Object> getCommonParams() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getCommonParams();
        }
        return new HashMap();
    }

    public void logSceneEvent(String str, String str2) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.logSceneEvent(str, str2);
        }
    }

    public void logMemoryUsage() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.logMemoryUsage();
        }
    }

    public void logSceneStart(Map<String, Object> map) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.logSceneStart(map);
        }
    }

    public void logSceneEnd() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.logSceneEnd();
        }
    }

    public Bundle getCommonDeviceInfo() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getCommonDeviceInfo();
        }
        return new Bundle();
    }

    public String getDeviceLevel() {
        Context applicatonContext;
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            String deviceLevel = bRNExtendCallBack.getDeviceLevel();
            if (TextUtils.isEmpty(deviceLevel)) {
                if (TextUtils.isEmpty(mSavedDeviceLevel) && (applicatonContext = mBRNExtendCallBack.getApplicatonContext()) != null) {
                    mSavedDeviceLevel = applicatonContext.getSharedPreferences("ReactKVConfig", 0).getString("device_level", "default");
                }
                return TextUtils.isEmpty(mSavedDeviceLevel) ? "default" : mSavedDeviceLevel;
            }
            return deviceLevel;
        }
        return "default";
    }

    public void startShare(Activity activity, Map<String, Object> map, ShareEventCallback shareEventCallback) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.onShareStart(activity, map, shareEventCallback);
        }
    }

    public int getServerRegion() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.getServerRegion();
        }
        return -1;
    }

    public String getAppLanguage() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getAppLanguage() : "en";
    }

    public String getRegisterCountry() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getRegisterCountry() : "";
    }

    public void pay(Activity activity, HashMap<String, Object> hashMap, PayResultListener payResultListener) {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            bRNExtendCallBack.pay(activity, hashMap, payResultListener);
        }
    }

    public String getInstallId() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        return bRNExtendCallBack != null ? bRNExtendCallBack.getInstallId() : "";
    }

    public boolean astcSupport() {
        BRNExtendCallBack bRNExtendCallBack = mBRNExtendCallBack;
        if (bRNExtendCallBack != null) {
            return bRNExtendCallBack.astcSupport();
        }
        return false;
    }
}
