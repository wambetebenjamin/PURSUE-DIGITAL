package com.google.android.play.core.assetpacks.internal;

import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class s extends p {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f16832a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ p f16833b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ z f16834c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(z zVar, TaskCompletionSource taskCompletionSource, TaskCompletionSource taskCompletionSource2, p pVar) {
        super(taskCompletionSource);
        this.f16834c = zVar;
        this.f16832a = taskCompletionSource2;
        this.f16833b = pVar;
    }

    @Override // com.google.android.play.core.assetpacks.internal.p
    public final void a() {
        Object obj;
        AtomicInteger atomicInteger;
        o oVar;
        obj = this.f16834c.f16846g;
        synchronized (obj) {
            z.n(this.f16834c, this.f16832a);
            atomicInteger = this.f16834c.l;
            if (atomicInteger.getAndIncrement() > 0) {
                oVar = this.f16834c.f16842c;
                oVar.d("Already connected to the service.", new Object[0]);
            }
            z.p(this.f16834c, this.f16833b);
        }
    }
}
