package com.bytedance.pipo.game.impl.model;

import android.text.TextUtils;
import com.android.billingclient.api.Purchase;
import com.bytedance.pipo.game.impl.util.l;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PayPurchase.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private String f10340a;

    /* renamed from: b  reason: collision with root package name */
    private final String f10341b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f10342c;

    /* renamed from: d  reason: collision with root package name */
    private JSONObject f10343d;

    /* renamed from: e  reason: collision with root package name */
    private String f10344e;

    /* renamed from: f  reason: collision with root package name */
    private String f10345f;

    /* renamed from: g  reason: collision with root package name */
    private Purchase f10346g;

    /* compiled from: PayPurchase.java */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface a {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10347a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10348b = 1;

        /* renamed from: c  reason: collision with root package name */
        public static final int f10349c = 2;
    }

    public Purchase p() {
        return this.f10346g;
    }

    public void a(Purchase purchase) {
        this.f10346g = purchase;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        com.bytedance.pipo.game.impl.util.e.a(jSONObject, "mOriginalJson", this.f10340a);
        com.bytedance.pipo.game.impl.util.e.a(jSONObject, "mSignature", this.f10341b);
        com.bytedance.pipo.game.impl.util.e.a(jSONObject, "isSubscription", this.f10342c);
        com.bytedance.pipo.game.impl.util.e.a(jSONObject, "mParsedJson", this.f10343d);
        return jSONObject;
    }

    private c(String str, String str2) {
        String str3;
        String str4;
        this.f10340a = "";
        this.f10342c = false;
        this.f10343d = null;
        this.f10341b = str2;
        try {
            this.f10344e = "";
            JSONObject jSONObject = new JSONObject(str);
            this.f10343d = jSONObject;
            this.f10345f = jSONObject.optString("obfuscatedAccountId");
            String optString = this.f10343d.optString("obfuscatedProfileId");
            String[] split = optString.split(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            if (split.length == 3) {
                str3 = split[0];
                this.f10344e = split[1];
                str4 = split[2];
            } else {
                if (split.length == 2) {
                    str3 = split[0];
                    this.f10344e = split[1];
                } else {
                    JSONObject a2 = a(this.f10343d.optString("obfuscatedAccountId"), optString);
                    if (a2 != null) {
                        String optString2 = a2.optString("merchant_id");
                        String optString3 = a2.optString("uid");
                        String optString4 = a2.optString(com.bytedance.pipo.game.impl.net.a.aJ);
                        if (TextUtils.isEmpty(optString2) || TextUtils.isEmpty(optString3) || TextUtils.isEmpty(optString4)) {
                            str3 = "";
                            str4 = str3;
                        } else {
                            this.f10345f = optString4;
                            str3 = optString2;
                            str4 = optString3;
                        }
                    } else {
                        str3 = split[0];
                    }
                }
                str4 = "";
            }
            this.f10343d.remove("obfuscatedProfileId");
            this.f10343d.remove("obfuscatedAccountId");
            if (TextUtils.isEmpty(this.f10345f) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str4)) {
                this.f10343d.put("developerPayload", "");
            } else {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("orderId", this.f10345f);
                jSONObject2.put("roleId", str3);
                jSONObject2.put(gsdk.impl.account.toutiao.f.v, str4);
                jSONObject2.put("serverId", this.f10344e);
                this.f10343d.put("developerPayload", jSONObject2.toString());
            }
            this.f10340a = this.f10343d.toString();
            com.bytedance.pipo.game.impl.util.d.a(com.bytedance.pipo.game.impl.f.f10331a, "PayPurchase: mSelfOrderId is :" + this.f10345f + " roleId is " + str3 + " userId is " + str4 + " serverId is " + this.f10344e);
        } catch (JSONException unused) {
            com.bytedance.pipo.game.impl.util.d.c(com.bytedance.pipo.game.impl.f.f10331a, "PayPurchase: mOriginalJson parse json error:" + this.f10340a);
        }
    }

    public static JSONObject a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            String[] split = l.b(str).split("-");
            String[] split2 = l.b(str2).split("-");
            if (split.length != 0 && split2.length != 0) {
                jSONObject.put(com.bytedance.pipo.game.impl.net.a.aJ, split2[0]);
                jSONObject.put(com.bytedance.pipo.game.impl.net.a.aK, split2[1]);
                jSONObject.put("app_id", split[0]);
                jSONObject.put("merchant_id", split[1]);
                jSONObject.put("uid", split[2]);
                return jSONObject;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public c(Purchase purchase, boolean z) {
        this(purchase.getOriginalJson(), purchase.getSignature());
        this.f10346g = purchase;
        this.f10342c = z;
    }

    public boolean b() {
        return this.f10342c;
    }

    public String c() {
        return this.f10343d.optString("orderId");
    }

    public String d() {
        return this.f10345f;
    }

    public String e() {
        return this.f10343d.optString("packageName");
    }

    public String f() {
        return this.f10343d.optString("productId");
    }

    public long g() {
        return this.f10343d.optLong("purchaseTime");
    }

    public String h() {
        JSONObject jSONObject = this.f10343d;
        return jSONObject.optString("token", jSONObject.optString("purchaseToken"));
    }

    public String i() {
        return this.f10343d.optString("developerPayload");
    }

    public int j() {
        return this.f10343d.optInt("purchaseState", 1) != 4 ? 1 : 2;
    }

    public int k() {
        JSONObject jSONObject = this.f10343d;
        if (jSONObject == null) {
            return -1;
        }
        return jSONObject.optInt("purchaseState", -1);
    }

    public boolean l() {
        return this.f10343d.optBoolean("acknowledged", true);
    }

    public String m() {
        return this.f10340a;
    }

    public String n() {
        return this.f10341b;
    }

    public String toString() {
        return this.f10340a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            return TextUtils.equals(this.f10340a, cVar.m()) && TextUtils.equals(this.f10341b, cVar.n());
        }
        return false;
    }

    public int hashCode() {
        return this.f10340a.hashCode();
    }

    public String o() {
        return this.f10344e;
    }
}
