package com.bytedance.bdinstall.storage;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.bdinstall.ax;
import com.bytedance.bdinstall.util.j;
import com.bytedance.bdinstall.util.p;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.UUID;
/* compiled from: AbsDeviceParamsProvider.java */
/* loaded from: classes.dex */
public abstract class a implements com.bytedance.bdinstall.service.a {

    /* renamed from: i  reason: collision with root package name */
    private static final String f3977i = "_local";

    /* renamed from: b  reason: collision with root package name */
    protected final Context f3978b;

    /* renamed from: c  reason: collision with root package name */
    protected final i f3979c;

    /* renamed from: d  reason: collision with root package name */
    protected c f3980d;

    /* renamed from: e  reason: collision with root package name */
    protected b f3981e;

    /* renamed from: f  reason: collision with root package name */
    protected String f3982f;

    /* renamed from: g  reason: collision with root package name */
    protected String f3983g;

    /* renamed from: h  reason: collision with root package name */
    protected final String f3984h;

    /* renamed from: j  reason: collision with root package name */
    private volatile String f3985j;

    private String b(boolean z) {
        return z ? f3977i : "";
    }

    protected void a(i iVar, c cVar) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(Context context, i iVar) {
        this.f3984h = b(iVar.c());
        Context applicationContext = context.getApplicationContext();
        this.f3978b = applicationContext;
        this.f3979c = iVar;
        this.f3980d = new h(applicationContext, iVar.b(), iVar.h());
        if (!com.bytedance.bdinstall.migrate.a.b(context, iVar.h())) {
            b bVar = new b(context, iVar.f(), iVar.h());
            this.f3981e = bVar;
            this.f3980d.a(bVar);
            b bVar2 = this.f3981e;
            if ((!p.F() || !p.a(context)) && iVar.g()) {
                a(iVar, bVar2);
            }
        }
        a(iVar.e());
    }

    @Override // com.bytedance.bdinstall.service.a
    public String a(boolean z) {
        if (!TextUtils.isEmpty(this.f3982f)) {
            return this.f3982f;
        }
        String a2 = com.bytedance.bdinstall.util.h.a(this.f3978b);
        SharedPreferences a3 = j.a(this.f3978b, this.f3979c.h());
        String string = a3.getString("openudid", null);
        try {
            if (!ax.a(a2) || com.bytedance.bdinstall.service.a.f3972a.equals(a2)) {
                if (!ax.a(string)) {
                    string = new BigInteger(80, new SecureRandom()).toString(16);
                    if (string.charAt(0) == '-') {
                        string = string.substring(1);
                    }
                    int length = 13 - string.length();
                    if (length > 0) {
                        StringBuilder sb = new StringBuilder();
                        while (length > 0) {
                            sb.append('F');
                            length--;
                        }
                        sb.append(string);
                        a2 = sb.toString();
                    }
                }
                a2 = string;
            }
        } catch (Exception unused) {
        }
        SharedPreferences.Editor edit = a3.edit();
        edit.putString("openudid", a2);
        edit.apply();
        if (!TextUtils.isEmpty(a2)) {
            a2 = a2 + this.f3984h;
        }
        if (!TextUtils.isEmpty(a2)) {
            this.f3982f = a2;
        }
        return a2;
    }

    @Override // com.bytedance.bdinstall.service.a
    public String a() {
        if (!TextUtils.isEmpty(this.f3983g)) {
            return this.f3983g;
        }
        try {
            SharedPreferences a2 = j.a(this.f3978b, this.f3979c.h());
            String string = a2.getString("clientudid", null);
            if (!ax.a(string)) {
                string = UUID.randomUUID().toString();
                SharedPreferences.Editor edit = a2.edit();
                edit.putString("clientudid", string);
                edit.apply();
            }
            if (!TextUtils.isEmpty(string)) {
                string = string + this.f3984h;
            }
            this.f3983g = string;
            return string;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    @Override // com.bytedance.bdinstall.service.a
    public String b() {
        if (!TextUtils.isEmpty(this.f3985j)) {
            return this.f3985j;
        }
        this.f3985j = this.f3980d.b("", "");
        return this.f3985j;
    }

    @Override // com.bytedance.bdinstall.service.a
    public void a(String str) {
        if (!ax.c(str) || ax.a(str, this.f3985j)) {
            return;
        }
        this.f3985j = this.f3980d.b(str, this.f3985j);
    }

    @Override // com.bytedance.bdinstall.service.a
    public void a(Account account) {
        b bVar = this.f3981e;
        if (bVar != null) {
            bVar.a(this.f3979c.h().b(), account);
        }
    }

    public void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f3985j = null;
        String str2 = "clear_key_prefix" + str;
        SharedPreferences b2 = this.f3979c.b();
        if (b2.getBoolean(str2, false)) {
            return;
        }
        SharedPreferences.Editor edit = b2.edit();
        edit.putBoolean(str2, true);
        if (b2.contains("device_id")) {
            edit.remove("device_id");
        }
        if (b2.contains("install_id")) {
            edit.remove("install_id");
        }
        edit.apply();
        this.f3980d.a(Collections.singletonList("device_id"));
    }

    public b d() {
        return this.f3981e;
    }
}
