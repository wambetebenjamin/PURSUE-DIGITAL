package com.bytedance.sdk.account.impl;
/* compiled from: AbsControllerApiCall.java */
/* loaded from: classes2.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    protected com.bytedance.sdk.account.api.pool.a f12374a;

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(com.bytedance.sdk.account.api.pool.a aVar) {
        this.f12374a = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        com.bytedance.sdk.account.api.pool.a aVar = this.f12374a;
        if (aVar != null) {
            aVar.cancel();
        }
    }
}
