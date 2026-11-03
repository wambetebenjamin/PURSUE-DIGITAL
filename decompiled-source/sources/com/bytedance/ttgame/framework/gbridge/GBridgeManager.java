package com.bytedance.ttgame.framework.gbridge;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.bytedance.ttgame.framework.gbridge.plugin.SdkLog;
import com.bytedance.ttgame.framework.gbridge.utils.LogUtil;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class GBridgeManager {
    public static final String TUNNEL_CPP = "tunnel_cpp";
    public static final String TUNNEL_ENDER = "tunnel_ender";
    public static final String TUNNEL_RN = "tunnel_rn";
    public static final String TUNNEL_UNITY = "tunnel_unity";
    private static Handler sMainHandler = new Handler(Looper.getMainLooper());

    private GBridgeManager() {
    }

    public static void initTunnel(String str, IEngineCall iEngineCall) {
        GBridge.getInstance(str).setEngineCall(iEngineCall);
    }

    public static void registerModule(IBridgeModule iBridgeModule) {
        if (iBridgeModule == null) {
            LogUtil.i("registered module can't be null.");
            return;
        }
        try {
            GBridge.getInstance(iBridgeModule.getBridgeTunnel()).registerModule(iBridgeModule);
        } catch (Exception e2) {
            LogUtil.e("registerModule exception: " + iBridgeModule.getClass().getName(), e2);
            e2.printStackTrace();
        }
    }

    public static void registerEvent(String str, String str2) {
        GBridge.getInstance(str).registerEvent(str2);
    }

    public static void sendEvent(String str, String str2, JSONObject jSONObject) {
        GBridge.getInstance(str).sendEvent(str2, jSONObject);
    }

    public static void sendEvent(String str, String str2, Map<String, Object> map) {
        GBridge.getInstance(str).sendEvent(str2, map);
    }

    public static JSONObject handleJSONFromEngine(String str, JSONObject jSONObject, boolean z) {
        return GBridge.getInstance(str).handleJSONFromEngine(jSONObject, z);
    }

    public static void handleMsgFromEngine(String str, String str2) {
        GBridge.getInstance(str).handleMsgFromEngine(str2);
    }

    public static void handleMsgFromEngineMainThread(final String str, final String str2) {
        sMainHandler.post(new Runnable() { // from class: com.bytedance.ttgame.framework.gbridge.GBridgeManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GBridgeManager.handleMsgFromEngine(str, str2);
            }
        });
    }

    public static String handleMsgFromEngineSync(String str, String str2) {
        return GBridge.getInstance(str).handleMsgFromEngineSync(str2);
    }

    public static void sendLocalEvent(String str, String str2, Map<String, Object> map) {
        GBridge.getInstance(str).sendLocalEvent(str2, map);
    }

    public static void setCallbackTimeout(String str, long j2) {
        GBridge.getInstance(str).setCallbackTimeout(j2);
    }

    public static void setCallbackTimeout(long j2) {
        for (GBridge gBridge : GBridge.getAllGBridge()) {
            gBridge.setCallbackTimeout(j2);
        }
    }

    public static void callEngineMethod(String str, String str2) {
        GBridge.getInstance(str).call(str2);
    }

    public static void callEngineMethod(String str, String str2, Map<String, Object> map) {
        GBridge.getInstance(str).call(str2, map);
    }

    public static void callEngineMethod(String str, String str2, Map<String, Object> map, GBridgeCallback gBridgeCallback) {
        GBridge.getInstance(str).call(str2, map, gBridgeCallback);
    }

    public static void callEngineMethod(String str, Map<String, Object> map, GBridgeCallback gBridgeCallback) {
        List<GBridge> allGBridge = GBridge.getAllGBridge();
        int size = allGBridge.size();
        if (!allGBridge.isEmpty()) {
            if (size != 1 || !"tunnel_rn".equals(allGBridge.get(0).getTunnel())) {
                for (int i2 = 0; i2 < size; i2++) {
                    GBridge gBridge = allGBridge.get(i2);
                    if (gBridge != null && !"tunnel_rn".equals(gBridge.getTunnel())) {
                        gBridge.call(str, map, gBridgeCallback);
                    }
                }
                return;
            }
        }
        gBridgeCallback.onFailed(105, "no engine tunnel registered");
    }

    public static void listen(String str, String str2, GBridgeCallback gBridgeCallback) {
        GBridge.getInstance(str).listen(str2, gBridgeCallback);
    }

    public static void unListen(String str, String str2) {
        GBridge.getInstance(str).unListen(str2);
    }

    public static IBridgeModule getBridgeInstance(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (IBridgeModule) Class.forName(str).getConstructor(String.class).newInstance(str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            SdkLog.e("getBridgeInstance", e2.getMessage());
            return null;
        }
    }

    public static IBridgeModule getBridgeInstance(String str, String str2, IApplicationProvider iApplicationProvider) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return (IBridgeModule) Class.forName(str).getConstructor(String.class, IApplicationProvider.class).newInstance(str2, iApplicationProvider);
        } catch (Exception e2) {
            e2.printStackTrace();
            SdkLog.e("getBridgeInstance", e2.getMessage());
            return null;
        }
    }
}
