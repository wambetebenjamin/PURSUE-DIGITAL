package com.bytedance.push.settings;

import android.content.Context;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public interface ISettings {
    void registerValChanged(Context context, String str, String str2, b bVar);

    void unregisterValChanged(b bVar);

    void updateSettings(Context context, JSONObject jSONObject);
}
