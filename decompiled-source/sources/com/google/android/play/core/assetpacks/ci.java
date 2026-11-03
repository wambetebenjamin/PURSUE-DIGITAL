package com.google.android.play.core.assetpacks;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class ci implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    private final com.google.android.play.core.assetpacks.internal.o f16591a = new com.google.android.play.core.assetpacks.internal.o("ExtractionForegroundServiceConnection");

    /* renamed from: b  reason: collision with root package name */
    private final List f16592b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    private final Context f16593c;

    /* renamed from: d  reason: collision with root package name */
    private ExtractionForegroundService f16594d;

    /* renamed from: e  reason: collision with root package name */
    private Notification f16595e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ci(Context context) {
        this.f16593c = context;
    }

    private final void d() {
        ArrayList arrayList;
        synchronized (this.f16592b) {
            arrayList = new ArrayList(this.f16592b);
            this.f16592b.clear();
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            try {
                ((com.google.android.play.core.assetpacks.internal.k) arrayList.get(i2)).e(new Bundle(), new Bundle());
            } catch (RemoteException unused) {
                this.f16591a.b("Could not resolve Play Store service state update callback.", new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(Notification notification) {
        this.f16595e = notification;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b() {
        this.f16591a.a("Stopping foreground installation service.", new Object[0]);
        this.f16593c.unbindService(this);
        ExtractionForegroundService extractionForegroundService = this.f16594d;
        if (extractionForegroundService != null) {
            extractionForegroundService.a();
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(com.google.android.play.core.assetpacks.internal.k kVar) {
        synchronized (this.f16592b) {
            this.f16592b.add(kVar);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f16591a.a("Starting foreground installation service.", new Object[0]);
        ExtractionForegroundService extractionForegroundService = ((ch) iBinder).f16590a;
        this.f16594d = extractionForegroundService;
        extractionForegroundService.startForeground(-1883842196, this.f16595e);
        d();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
