package com.bytedance.bdinstall.callback.event;

import com.bytedance.bdinstall.ax;
import org.json.JSONObject;
/* compiled from: HeaderChangeEvent.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final JSONObject f3787a;

    public a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        ax.a(jSONObject2, jSONObject);
        this.f3787a = jSONObject2;
    }

    public JSONObject a() {
        return this.f3787a;
    }
}
