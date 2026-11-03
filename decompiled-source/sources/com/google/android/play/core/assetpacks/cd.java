package com.google.android.play.core.assetpacks;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class cd {

    /* renamed from: a  reason: collision with root package name */
    private p f16567a;

    private cd() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ cd(cc ccVar) {
    }

    public final cd b(p pVar) {
        this.f16567a = pVar;
        return this;
    }

    public final a a() {
        p pVar = this.f16567a;
        if (pVar != null) {
            return new cb(pVar, null);
        }
        throw new IllegalStateException(String.valueOf(p.class.getCanonicalName()).concat(" must be set"));
    }
}
