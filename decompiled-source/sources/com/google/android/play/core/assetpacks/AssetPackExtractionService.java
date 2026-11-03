package com.google.android.play.core.assetpacks;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public class AssetPackExtractionService extends Service {

    /* renamed from: a  reason: collision with root package name */
    b f16402a;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.f16402a;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        d.a(getApplicationContext()).b(this);
    }
}
