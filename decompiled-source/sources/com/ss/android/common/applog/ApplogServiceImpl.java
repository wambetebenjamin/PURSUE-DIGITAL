package com.ss.android.common.applog;

import android.content.Context;
import android.util.Log;
import com.service.middleware.applog.ApplogService;
import com.service.middleware.applog.b;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class ApplogServiceImpl implements ApplogService {
    public static final String TAG = "ApplogServiceImpl";
    public static volatile b cachedHeaderCustomTimelyCallback = null;
    public static volatile boolean isServicePrepared = false;

    public static void handleCachedData() {
        if (isServicePrepared && cachedHeaderCustomTimelyCallback != null) {
            AppLog.registerHeaderCustomCallback(cachedHeaderCustomTimelyCallback);
        }
    }

    @Override // com.service.middleware.applog.ApplogService
    public void onEvent(Context context, String str, String str2, String str3, long j2, long j3, JSONObject jSONObject) {
        if (isServicePrepared) {
            TeaAgent.onEvent(context, str, str2, str3, j2, j3, jSONObject);
        } else {
            Log.e(TAG, "ApplogService not prepared!");
        }
    }

    @Override // com.service.middleware.applog.ApplogService
    public void onEvent(Context context, String str, String str2, String str3, long j2, long j3, boolean z) {
        if (isServicePrepared) {
            TeaAgent.onEvent(context, str, str2, str3, j2, j3, z);
        } else {
            Log.e(TAG, "ApplogService not prepared!");
        }
    }

    @Override // com.service.middleware.applog.ApplogService
    public void onEvent(Context context, String str, String str2, String str3, long j2, long j3) {
        if (isServicePrepared) {
            TeaAgent.onEvent(context, str, str2, str3, j2, j3);
        } else {
            Log.e(TAG, "ApplogService not prepared!");
        }
    }

    @Override // com.service.middleware.applog.ApplogService
    public void onEvent(Context context, String str, String str2) {
        if (isServicePrepared) {
            TeaAgent.onEvent(context, str, str2);
        } else {
            Log.e(TAG, "ApplogService not prepared!");
        }
    }

    @Override // com.service.middleware.applog.ApplogService
    public void onEvent(Context context, String str) {
        if (isServicePrepared) {
            TeaAgent.onEvent(context, str);
        } else {
            Log.e(TAG, "ApplogService not prepared!");
        }
    }

    @Override // com.service.middleware.applog.ApplogService
    public void onEvent(Context context, String str, String str2, String str3, long j2, long j3, boolean z, JSONObject jSONObject) {
        if (isServicePrepared) {
            TeaAgent.onEvent(context, str, str2, str3, j2, j3, z, jSONObject);
        } else {
            Log.e(TAG, "ApplogService not prepared!");
        }
    }

    @Override // com.service.middleware.applog.ApplogService
    public void registerHeaderCustomCallback(b bVar) {
        if (isServicePrepared) {
            AppLog.registerHeaderCustomCallback(bVar);
        } else {
            cachedHeaderCustomTimelyCallback = bVar;
        }
    }
}
