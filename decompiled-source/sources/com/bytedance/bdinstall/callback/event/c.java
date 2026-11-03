package com.bytedance.bdinstall.callback.event;

import android.text.TextUtils;
import com.bytedance.bdinstall.am;
import com.bytedance.bdinstall.u;
import org.json.JSONObject;
/* compiled from: Monitor.java */
/* loaded from: classes.dex */
public abstract class c {
    protected abstract JSONObject a();

    protected abstract void b();

    public void a(am amVar, String str) {
        JSONObject a2 = a();
        if (amVar == null || amVar.G() == null) {
            return;
        }
        amVar.G().a(str, a2);
        if (!TextUtils.isEmpty(str)) {
            u.a("EventTracking  " + str + amVar.a() + ", " + a2);
        }
        b();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        u.a("EventTracking  " + str + amVar.a() + " reset.");
    }
}
