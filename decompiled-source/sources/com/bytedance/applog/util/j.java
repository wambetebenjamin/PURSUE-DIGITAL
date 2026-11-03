package com.bytedance.applog.util;

import android.content.Context;
import java.util.List;
import org.json.JSONArray;
/* compiled from: IEventsSender.java */
/* loaded from: classes.dex */
public interface j {
    boolean isEnable();

    void loginEtWithScheme(String str, Context context);

    void putEvent(String str, JSONArray jSONArray);

    void setEnable(boolean z, Context context);

    void setEventVerifyInterval(long j2);

    void setEventVerifyUrl(String str);

    void setSpecialKeys(List<String> list);
}
