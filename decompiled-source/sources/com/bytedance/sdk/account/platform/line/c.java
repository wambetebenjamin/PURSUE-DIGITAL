package com.bytedance.sdk.account.platform.line;

import android.content.Context;
import com.bytedance.sdk.account.platform.api.k;
import com.bytedance.sdk.account.platform.base.e;
import com.bytedance.sdk.account.platform.base.f;
/* compiled from: LineServiceIniter.java */
/* loaded from: classes2.dex */
public class c implements com.bytedance.sdk.account.platform.base.c<k>, f<k> {

    /* renamed from: a  reason: collision with root package name */
    String f12861a;

    public c(String str) {
        this.f12861a = str;
    }

    @Override // com.bytedance.sdk.account.platform.base.c
    public k createService(Context context) {
        return new b(this.f12861a);
    }

    @Override // com.bytedance.sdk.account.platform.base.f
    public void init(Context context) {
        e.registerService(k.class, new b(this.f12861a));
    }
}
