package com.google.android.play.core.assetpacks.internal;

import android.os.IBinder;
import java.util.List;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class v extends p {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ IBinder f16836a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ y f16837b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(y yVar, IBinder iBinder) {
        this.f16837b = yVar;
        this.f16836a = iBinder;
    }

    @Override // com.google.android.play.core.assetpacks.internal.p
    public final void a() {
        List<Runnable> list;
        List list2;
        this.f16837b.f16839a.n = e.b(this.f16836a);
        z.q(this.f16837b.f16839a);
        this.f16837b.f16839a.f16847h = false;
        list = this.f16837b.f16839a.f16844e;
        for (Runnable runnable : list) {
            runnable.run();
        }
        list2 = this.f16837b.f16839a.f16844e;
        list2.clear();
    }
}
