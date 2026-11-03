package com.google.android.play.core.assetpacks.internal;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class ap implements as {

    /* renamed from: a  reason: collision with root package name */
    private as f16815a;

    public static void b(as asVar, as asVar2) {
        ap apVar = (ap) asVar;
        if (apVar.f16815a != null) {
            throw new IllegalStateException();
        }
        apVar.f16815a = asVar2;
    }

    @Override // com.google.android.play.core.assetpacks.internal.as
    public final Object a() {
        as asVar = this.f16815a;
        if (asVar == null) {
            throw new IllegalStateException();
        }
        return asVar.a();
    }
}
