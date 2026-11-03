package com.google.android.play.core.assetpacks.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class y implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ z f16839a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ y(z zVar, x xVar) {
        this.f16839a = zVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        z.f(this.f16839a).d("ServiceConnectionImpl.onServiceConnected(%s)", componentName);
        this.f16839a.c().post(new v(this, iBinder));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        z.f(this.f16839a).d("ServiceConnectionImpl.onServiceDisconnected(%s)", componentName);
        this.f16839a.c().post(new w(this));
    }
}
