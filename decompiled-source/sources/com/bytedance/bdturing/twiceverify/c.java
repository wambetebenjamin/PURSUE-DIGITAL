package com.bytedance.bdturing.twiceverify;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.bytedance.bdturing.twiceverify.b;
import com.bytedance.bdturing.verify.request.h;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Charsets;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: TwiceVerifyManager.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4359a = "/passport/safe/verify_auth_ticket/";

    /* renamed from: b  reason: collision with root package name */
    private static volatile c f4360b;

    /* renamed from: c  reason: collision with root package name */
    private b f4361c;

    /* renamed from: d  reason: collision with root package name */
    private a f4362d;

    /* renamed from: e  reason: collision with root package name */
    private com.bytedance.bdturing.twiceverify.a f4363e;

    /* renamed from: f  reason: collision with root package name */
    private com.bytedance.bdturing.verify.request.a f4364f;

    /* renamed from: g  reason: collision with root package name */
    private b.a f4365g = new b.a() { // from class: com.bytedance.bdturing.twiceverify.c.1
        @Override // com.bytedance.bdturing.twiceverify.b.a
        public void a() {
            c.this.f4362d.a();
            c.this.g();
        }

        @Override // com.bytedance.bdturing.twiceverify.b.a
        public void a(String str, String str2, Map<String, String> map) {
            c.this.a(c.this.c().b() + c.f4359a, c.this.a(str, str2, map));
            c.this.g();
        }

        @Override // com.bytedance.bdturing.twiceverify.b.a
        public void a(int i2, String str) {
            c.this.f4362d.a(i2, str);
            c.this.g();
        }
    };

    /* compiled from: TwiceVerifyManager.java */
    /* loaded from: classes.dex */
    public interface a {
        void a();

        void a(int i2, String str);
    }

    public static c a() {
        if (f4360b == null) {
            synchronized (c.class) {
                f4360b = new c();
            }
        }
        return f4360b;
    }

    public void a(b bVar) {
        this.f4361c = bVar;
    }

    public void a(com.bytedance.bdturing.twiceverify.a aVar) {
        this.f4363e = aVar;
    }

    public void a(com.bytedance.bdturing.verify.request.a aVar, HashMap<String, String> hashMap, a aVar2) {
        this.f4362d = aVar2;
        if (aVar instanceof h) {
            c().a(hashMap, this.f4365g);
            return;
        }
        this.f4364f = aVar;
        Intent intent = new Intent();
        Activity a2 = aVar.a();
        intent.setClass(a2, TwiceVerifyWebActivity.class);
        a2.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        try {
            byte[] bArr = new byte[0];
            if (com.bytedance.bdturing.a.getInstance().getConfig() != null) {
                bArr = com.bytedance.bdturing.a.getInstance().getConfig().h().a(str, null, str2.getBytes()).a();
            }
            JSONObject jSONObject = new JSONObject(new String(bArr, Charsets.UTF_8));
            String optString = jSONObject.optString("message");
            if (jSONObject.has("data") && !TextUtils.isEmpty(optString)) {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                if ("success".equals(optString)) {
                    int optInt = optJSONObject.optInt("check_result");
                    if (optInt == 1) {
                        this.f4362d.a();
                        return;
                    } else {
                        this.f4362d.a(optInt, "verify error");
                        return;
                    }
                }
                int optInt2 = optJSONObject.has("error_code") ? optJSONObject.optInt("error_code") : -2;
                if (optJSONObject.has("description")) {
                    optString = optJSONObject.optString("description");
                }
                this.f4362d.a(optInt2, optString);
                return;
            }
            this.f4362d.a(-2, "network error");
        } catch (JSONException e2) {
            this.f4362d.a(-2, "JSON error");
            e2.printStackTrace();
        } catch (Exception e3) {
            this.f4362d.a(-2, "network error");
            e3.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2, Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("auth_ticket", str);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("scene", str2);
            }
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public boolean b() {
        return this.f4361c != null;
    }

    public b c() {
        return this.f4361c;
    }

    public com.bytedance.bdturing.twiceverify.a d() {
        return this.f4363e;
    }

    public a e() {
        return this.f4362d;
    }

    public com.bytedance.bdturing.verify.request.a f() {
        return this.f4364f;
    }

    public void g() {
        this.f4362d = null;
        this.f4364f = null;
    }
}
