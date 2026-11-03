package com.bytedance.ttgame.framework.gbridge.utils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.bytedance.ttgame.framework.gbridge.GBridge;
import com.bytedance.ttgame.framework.gbridge.IEngineCall;
import com.bytedance.ttgame.framework.gbridge.IEngineCall2;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class EngineUtil {
    private static Handler sEngineMainHandler;

    public static native boolean hasCallEngineFuncPtr();

    public static native void nativeCallEngine(String str);

    public static void callEngineBridge(final String str, final JSONObject jSONObject) {
        Handler handler = sEngineMainHandler;
        if (handler == null) {
            realCallEngineBridge(str, jSONObject);
            return;
        }
        try {
            handler.post(new Runnable() { // from class: com.bytedance.ttgame.framework.gbridge.utils.EngineUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    EngineUtil.realCallEngineBridge(str, jSONObject);
                }
            });
        } catch (Exception e2) {
            LogUtil.e("callEngineBridge", e2);
        }
    }

    public static void realCallEngineBridge(String str, JSONObject jSONObject) {
        if ("tunnel_cpp".equals(str)) {
            if (!hasCallEngineFuncPtr()) {
                LogUtil.i("tunnel_cpp: can not found CallEngineFuncPtr");
                return;
            } else {
                nativeCallEngine(jSONObject.toString());
                return;
            }
        }
        IEngineCall currentEngineCall = GBridge.getCurrentEngineCall(str);
        if (currentEngineCall == null) {
            LogUtil.d("callEngineBridge, must set engine call");
            return;
        }
        try {
            if (currentEngineCall instanceof IEngineCall2) {
                ((IEngineCall2) currentEngineCall).callEngine(jSONObject);
            } else {
                currentEngineCall.callEngine(jSONObject.toString());
            }
        } catch (Throwable th) {
            LogUtil.e("realCallEngineBridge", th);
        }
    }

    public static void prepareEngineMainHandler() {
        Looper myLooper;
        if (sEngineMainHandler == null) {
            synchronized (EngineUtil.class) {
                if (sEngineMainHandler == null && (myLooper = Looper.myLooper()) != null) {
                    sEngineMainHandler = new Handler(myLooper);
                    Log.d("GBridge", "construct sEngineMainHandler, threadId:" + Thread.currentThread().getId() + ", threadName:" + Thread.currentThread().getName());
                }
            }
        }
    }
}
