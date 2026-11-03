package com.bytedance.sdk.account.platform.app.auth;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.base.e;
import com.bytedance.sdk.account.platform.base.f;
/* compiled from: AppAuthServiceIniter.java */
/* loaded from: classes2.dex */
public class b implements com.bytedance.sdk.account.platform.base.c<com.bytedance.sdk.account.platform.api.b>, f<com.bytedance.sdk.account.platform.api.b> {

    /* renamed from: a  reason: collision with root package name */
    private String f12785a;

    /* renamed from: b  reason: collision with root package name */
    private String f12786b;

    /* renamed from: c  reason: collision with root package name */
    private String f12787c;

    /* renamed from: d  reason: collision with root package name */
    private String f12788d;

    public b(String str, String str2, String str3) {
        this.f12785a = str;
        this.f12786b = str2;
        this.f12788d = str3;
    }

    public b(String str, String str2) {
        this.f12787c = str;
        this.f12788d = str2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.bytedance.sdk.account.platform.base.c
    public com.bytedance.sdk.account.platform.api.b createService(Context context) {
        return a(context);
    }

    @Override // com.bytedance.sdk.account.platform.base.f
    public void init(Context context) {
        e.registerService(com.bytedance.sdk.account.platform.api.b.class, a(context));
    }

    private com.bytedance.sdk.account.platform.api.b a(Context context) {
        if (context == null) {
            return null;
        }
        if (!TextUtils.isEmpty(this.f12785a) && !TextUtils.isEmpty(this.f12786b)) {
            return new a(context.getApplicationContext(), this.f12785a, this.f12786b, this.f12788d);
        }
        return new a(context.getApplicationContext(), this.f12787c, this.f12788d);
    }
}
