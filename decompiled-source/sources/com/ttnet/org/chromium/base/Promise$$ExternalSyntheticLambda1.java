package com.ttnet.org.chromium.base;
/* compiled from: D8$$SyntheticClass */
/* loaded from: classes9.dex */
public final /* synthetic */ class Promise$$ExternalSyntheticLambda1 implements Callback {
    public final /* synthetic */ Promise f$0;

    @Override // com.ttnet.org.chromium.base.Callback
    public final void onResult(Object obj) {
        this.f$0.reject((Exception) obj);
    }
}
