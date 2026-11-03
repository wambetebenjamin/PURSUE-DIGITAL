package com.bytedance.pipo.game.impl.net.entity;

import org.json.JSONObject;
/* compiled from: SubscriptionOrderStateResponseEntity.java */
/* loaded from: classes2.dex */
public class d extends c {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10469a = 1;

    /* renamed from: d  reason: collision with root package name */
    public static final int f10470d = 2;

    /* renamed from: e  reason: collision with root package name */
    public static final int f10471e = 3;

    /* renamed from: f  reason: collision with root package name */
    public static final int f10472f = 4;

    /* renamed from: g  reason: collision with root package name */
    public static final int f10473g = 5;

    /* renamed from: h  reason: collision with root package name */
    public static final int f10474h = 6;

    /* renamed from: i  reason: collision with root package name */
    public static final int f10475i = 7;

    /* renamed from: j  reason: collision with root package name */
    public a f10476j;

    /* compiled from: SubscriptionOrderStateResponseEntity.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public b f10477a;
    }

    /* compiled from: SubscriptionOrderStateResponseEntity.java */
    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public int f10478a;
    }

    public static d a(String str) {
        JSONObject optJSONObject;
        d dVar = new d();
        a aVar = new a();
        b bVar = new b();
        dVar.f10476j = aVar;
        aVar.f10477a = bVar;
        try {
            JSONObject jSONObject = new JSONObject(str);
            dVar.f10467b = jSONObject.optInt("error_code");
            dVar.f10468c = jSONObject.optString("message");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("data");
            if (optJSONObject2 != null && (optJSONObject = optJSONObject2.optJSONObject("SubsInfo")) != null) {
                bVar.f10478a = optJSONObject.optInt("Status");
            }
        } catch (Throwable unused) {
        }
        return dVar;
    }
}
