package com.bytedance.sdk.bridge.auth.privilege;

import com.bytedance.sdk.bridge.auth.privilege.b;
import org.json.JSONObject;
/* compiled from: AbsAuthenticateModule.java */
/* loaded from: classes2.dex */
public abstract class a<T, B extends b> extends com.bytedance.sdk.bridge.a {

    /* renamed from: a  reason: collision with root package name */
    protected c<T, B> f13111a;

    public abstract void config(com.bytedance.sdk.bridge.model.d dVar, JSONObject jSONObject);

    public a(c<T, B> cVar) {
        this.f13111a = cVar;
    }
}
