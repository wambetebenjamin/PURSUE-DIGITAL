package com.bytedance.push.settings;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.NativeProtocol;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class SettingsManager {
    public static boolean IS_PUSH_DEMO;
    public static boolean allowReadSettingsOnMain;
    public static boolean allowReadSettingsOnSmp;
    public static q eventService;
    private static final l mSettingsCache;
    private static final j sLocalSettingsCache;
    private static final com.bytedance.push.settings.storage.h sStorageFactory;

    static {
        com.bytedance.push.settings.storage.h hVar = new com.bytedance.push.settings.storage.h();
        sStorageFactory = hVar;
        sLocalSettingsCache = new j(hVar);
        mSettingsCache = new l();
        allowReadSettingsOnSmp = false;
        allowReadSettingsOnMain = false;
        IS_PUSH_DEMO = false;
    }

    public static <T> T obtain(Context context, Class<T> cls) {
        StackTraceElement[] stackTrace;
        String curProcessName = com.bytedance.push.settings.utils.a.getCurProcessName(context);
        if (!allowReadSettingsOnMain && TextUtils.equals(curProcessName, context.getPackageName()) && Looper.myLooper() == Looper.getMainLooper()) {
            if (IS_PUSH_DEMO) {
                throw new RuntimeException("not allow read settings on main process before start!!");
            }
            com.bytedance.push.settings.logger.b.a().b("not allow read settings on main process before start:" + Log.getStackTraceString(new Throwable()));
        } else if (!allowReadSettingsOnSmp && !TextUtils.isEmpty(curProcessName) && curProcessName.endsWith(":smp")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(NativeProtocol.BRIDGE_ARG_ERROR_TYPE, 2);
                JSONArray jSONArray = new JSONArray();
                for (StackTraceElement stackTraceElement : new Throwable().getStackTrace()) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("class_name", stackTraceElement.getClassName());
                    jSONObject2.put("file_name", stackTraceElement.getFileName());
                    jSONObject2.put("method_name", stackTraceElement.getMethodName());
                    jSONObject2.put("line_number", stackTraceElement.getLineNumber());
                    jSONArray.put(jSONObject2);
                }
                jSONObject.put("stack_trace", jSONArray.toString());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            q qVar = eventService;
            if (qVar != null) {
                qVar.a("alliance_process_isolation_error", jSONObject);
            }
            throw new RuntimeException("read settings on smp before allowReadSettings is true,it will make process isolation failed !!");
        }
        if (ISettings.class.isAssignableFrom(cls)) {
            return (T) mSettingsCache.obtain(context, cls);
        }
        if (ILocalSettings.class.isAssignableFrom(cls)) {
            return (T) sLocalSettingsCache.a(context, cls);
        }
        throw new IllegalArgumentException("tClass must be child of ISettings or ILocalSettings");
    }

    public static n getStorageFactory() {
        return sStorageFactory;
    }
}
