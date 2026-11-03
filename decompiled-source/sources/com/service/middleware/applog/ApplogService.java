package com.service.middleware.applog;

import android.content.Context;
import com.bytedance.news.common.service.manager.IService;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public interface ApplogService extends IService {
    void onEvent(Context context, String str);

    void onEvent(Context context, String str, String str2);

    void onEvent(Context context, String str, String str2, String str3, long j2, long j3);

    void onEvent(Context context, String str, String str2, String str3, long j2, long j3, JSONObject jSONObject);

    void onEvent(Context context, String str, String str2, String str3, long j2, long j3, boolean z);

    void onEvent(Context context, String str, String str2, String str3, long j2, long j3, boolean z, JSONObject jSONObject);

    void registerHeaderCustomCallback(b bVar);
}
