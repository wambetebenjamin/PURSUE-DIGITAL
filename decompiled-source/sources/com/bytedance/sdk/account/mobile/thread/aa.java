package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.common.utility.o;
import com.bytedance.sdk.account.execute.a;
import com.bytedance.sdk.account.impl.n;
import com.bytedance.sdk.account.monitor.a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: SetpassThread.java */
/* loaded from: classes2.dex */
public class aa extends n<com.bytedance.sdk.account.api.call.f<a>> {

    /* renamed from: d  reason: collision with root package name */
    private a f12605d;

    public aa(Context context, com.bytedance.sdk.account.execute.a aVar, a aVar2, com.bytedance.sdk.account.mobile.thread.call.w wVar) {
        super(context, aVar, wVar);
        this.f12605d = aVar2;
    }

    public static aa setPassword(Context context, String str, String str2, com.bytedance.sdk.account.mobile.thread.call.w wVar) {
        a aVar = new a(str, str2);
        return new aa(context, new a.C0188a().url(com.bytedance.sdk.account.j.getSetPass()).parameters(a(aVar, (Map<String, String>) null)).post(), aVar, wVar);
    }

    public static aa setPassword(Context context, String str, String str2, Map<String, String> map, com.bytedance.sdk.account.mobile.thread.call.w wVar) {
        a aVar = new a(str, str2);
        return new aa(context, new a.C0188a().url(com.bytedance.sdk.account.j.getSetPass()).parameters(a(aVar, map)).post(), aVar, wVar);
    }

    protected static Map<String, String> a(a aVar, Map<String, String> map) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(aVar.f12607b)) {
            hashMap.put("captcha", aVar.f12607b);
        }
        hashMap.put("password", o.encryptWithXor(aVar.f12606a));
        hashMap.put("mix_mode", "1");
        if (map != null) {
            hashMap.putAll(map);
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.n
    /* renamed from: a */
    public com.bytedance.sdk.account.api.call.f<a> b(boolean z, com.bytedance.sdk.account.execute.b bVar) {
        return new com.bytedance.sdk.account.api.call.f<>(z, 10002, this.f12605d);
    }

    @Override // com.bytedance.sdk.account.impl.n
    protected void a(JSONObject jSONObject, JSONObject jSONObject2) {
        com.bytedance.sdk.account.impl.c.mobileError(this.f12605d, jSONObject);
        this.f12605d.jsonResult = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.n
    protected void b(JSONObject jSONObject, JSONObject jSONObject2) {
        this.f12605d.jsonResult = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.n
    public void onSendEvent(com.bytedance.sdk.account.api.call.f<a> fVar) {
        com.bytedance.sdk.account.monitor.b.onEvent(a.c.SET_PASSWORD, "mobile", null, fVar, this.f12471c);
    }

    /* compiled from: SetpassThread.java */
    /* loaded from: classes2.dex */
    public static class a extends com.bytedance.sdk.account.mobile.query.m {

        /* renamed from: a  reason: collision with root package name */
        String f12606a;

        /* renamed from: b  reason: collision with root package name */
        String f12607b;

        public a(String str, String str2) {
            super(13);
            this.f12606a = str;
            this.f12607b = str2;
        }
    }
}
