package com.bytedance.applog;

import org.json.JSONObject;
/* compiled from: ISessionObserver.java */
/* loaded from: classes.dex */
public interface q {
    void onSessionBatchEvent(long j2, String str, JSONObject jSONObject);

    void onSessionStart(long j2, String str);

    void onSessionTerminate(long j2, String str, JSONObject jSONObject);
}
