package com.google.android.play.core.assetpacks.internal;

import android.content.Context;
import android.content.ServiceConnection;
import android.os.IInterface;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class t extends p {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ z f16835a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public t(z zVar) {
        this.f16835a = zVar;
    }

    @Override // com.google.android.play.core.assetpacks.internal.p
    public final void a() {
        Object obj;
        AtomicInteger atomicInteger;
        IInterface iInterface;
        o oVar;
        Context context;
        ServiceConnection serviceConnection;
        AtomicInteger atomicInteger2;
        o oVar2;
        obj = this.f16835a.f16846g;
        synchronized (obj) {
            atomicInteger = this.f16835a.l;
            if (atomicInteger.get() > 0) {
                atomicInteger2 = this.f16835a.l;
                if (atomicInteger2.decrementAndGet() > 0) {
                    oVar2 = this.f16835a.f16842c;
                    oVar2.d("Leaving the connection open for other ongoing calls.", new Object[0]);
                    return;
                }
            }
            z zVar = this.f16835a;
            iInterface = zVar.n;
            if (iInterface != null) {
                oVar = zVar.f16842c;
                oVar.d("Unbind from service.", new Object[0]);
                z zVar2 = this.f16835a;
                context = zVar2.f16841b;
                serviceConnection = zVar2.m;
                context.unbindService(serviceConnection);
                this.f16835a.f16847h = false;
                this.f16835a.n = null;
                this.f16835a.m = null;
            }
            this.f16835a.w();
        }
    }
}
