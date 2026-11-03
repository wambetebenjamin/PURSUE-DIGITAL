package com.ss.android.message;

import android.content.Context;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: IPushLifeAdapter.java */
/* loaded from: classes9.dex */
public interface b extends c {

    /* compiled from: IPushLifeAdapter.java */
    /* loaded from: classes9.dex */
    public interface a {
        void onEvent(Context context, String str, String str2, String str3, long j2, long j3, JSONObject jSONObject);

        void onEventV3(String str, JSONObject jSONObject);
    }

    void a();

    void handleAppLogUpdate(Context context, Map<String, String> map);

    void initOnApplication(Context context, com.ss.android.pushmanager.b bVar);

    void onSetSettingsData(Context context, JSONObject jSONObject);

    void setPushDepend(a aVar);
}
