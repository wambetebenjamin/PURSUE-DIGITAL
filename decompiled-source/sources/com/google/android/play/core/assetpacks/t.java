package com.google.android.play.core.assetpacks;

import android.content.Context;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class t implements com.google.android.play.core.assetpacks.internal.as {

    /* renamed from: a  reason: collision with root package name */
    private final com.google.android.play.core.assetpacks.internal.as f16880a;

    /* renamed from: b  reason: collision with root package name */
    private final com.google.android.play.core.assetpacks.internal.as f16881b;

    /* renamed from: c  reason: collision with root package name */
    private final com.google.android.play.core.assetpacks.internal.as f16882c;

    public t(com.google.android.play.core.assetpacks.internal.as asVar, com.google.android.play.core.assetpacks.internal.as asVar2, com.google.android.play.core.assetpacks.internal.as asVar3) {
        this.f16880a = asVar;
        this.f16881b = asVar2;
        this.f16882c = asVar3;
    }

    @Override // com.google.android.play.core.assetpacks.internal.as
    public final /* bridge */ /* synthetic */ Object a() {
        y yVar;
        Context b2 = ((u) this.f16880a).b();
        com.google.android.play.core.assetpacks.internal.aq c2 = com.google.android.play.core.assetpacks.internal.aq.c(this.f16881b);
        com.google.android.play.core.assetpacks.internal.aq c3 = com.google.android.play.core.assetpacks.internal.aq.c(this.f16882c);
        if (p.b(b2) == null) {
            yVar = (y) c2.a();
        } else {
            yVar = (y) c3.a();
        }
        com.google.android.play.core.assetpacks.internal.ar.a(yVar);
        return yVar;
    }
}
