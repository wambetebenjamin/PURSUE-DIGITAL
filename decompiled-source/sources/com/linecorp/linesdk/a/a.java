package com.linecorp.linesdk.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.base.i;
/* loaded from: classes9.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private final Context f17240a;

    /* renamed from: b  reason: collision with root package name */
    private final String f17241b;

    /* renamed from: c  reason: collision with root package name */
    private final com.linecorp.a.a.a.b f17242c;

    public a(Context context, String str) {
        this(context.getApplicationContext(), str, c.a());
    }

    private a(Context context, String str, com.linecorp.a.a.a.b bVar) {
        this.f17240a = context;
        this.f17241b = "com.linecorp.linesdk.accesstoken." + str;
        this.f17242c = bVar;
    }

    public final void a() {
        this.f17240a.getSharedPreferences(this.f17241b, 0).edit().clear().apply();
    }

    public final void a(d dVar) {
        this.f17240a.getSharedPreferences(this.f17241b, 0).edit().putString(i.CT_KEY_ACCESS_TOKEN, a(dVar.f17271a)).putString("expiresIn", a(dVar.f17272b)).putString("issuedClientTime", a(dVar.f17273c)).putString("refreshToken", a(dVar.f17274d)).apply();
    }

    public final d b() {
        SharedPreferences sharedPreferences = this.f17240a.getSharedPreferences(this.f17241b, 0);
        try {
            String b2 = b(sharedPreferences.getString(i.CT_KEY_ACCESS_TOKEN, null));
            long c2 = c(sharedPreferences.getString("expiresIn", null));
            long c3 = c(sharedPreferences.getString("issuedClientTime", null));
            if (TextUtils.isEmpty(b2) || c2 == -1 || c3 == -1) {
                return null;
            }
            String b3 = b(sharedPreferences.getString("refreshToken", null));
            if (b3 == null) {
                b3 = "";
            }
            return new d(b2, c2, c3, b3);
        } catch (com.linecorp.a.a.a.a unused) {
            a();
            return null;
        }
    }

    private String a(String str) {
        return this.f17242c.a(this.f17240a, str);
    }

    private String a(long j2) {
        return this.f17242c.a(this.f17240a, String.valueOf(j2));
    }

    private String b(String str) {
        if (str == null) {
            return null;
        }
        return this.f17242c.b(this.f17240a, str);
    }

    private long c(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.valueOf(this.f17242c.b(this.f17240a, str)).longValue();
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }
}
