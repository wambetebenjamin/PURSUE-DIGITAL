package com.bytedance.ug.sdk.share.impl.share;

import android.content.Context;
/* compiled from: AbstractShare.java */
/* loaded from: classes8.dex */
public abstract class a implements com.bytedance.ug.sdk.share.impl.share.api.a {

    /* renamed from: a  reason: collision with root package name */
    protected Context f15911a;

    /* renamed from: b  reason: collision with root package name */
    protected com.bytedance.ug.sdk.share.api.entity.h f15912b;

    @Override // com.bytedance.ug.sdk.share.impl.share.api.a
    public boolean canShare(com.bytedance.ug.sdk.share.api.entity.h hVar) {
        return true;
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.a
    public String getPackageName() {
        return null;
    }

    @Override // com.bytedance.ug.sdk.share.impl.share.api.a
    public boolean isInstalled() {
        return true;
    }

    public a(Context context) {
        this.f15911a = context;
    }
}
