package com.bytedance.push.interfaze;

import org.json.JSONObject;
/* compiled from: ISDKMonitor.java */
/* loaded from: classes2.dex */
public interface q extends com.ss.android.ug.bus.a {
    void ensureNotReachHere(Throwable th);

    void monitorEvent(String str, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3);

    void monitorStatusAndDuration(String str, int i2, JSONObject jSONObject, JSONObject jSONObject2);

    void onUserActive();
}
