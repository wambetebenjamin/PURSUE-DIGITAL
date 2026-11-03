package com.bytedance.ttgame.module.webview.aidl.service.mainproaidl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import gsdk.impl.webview.DEFAULT.as;
import timber.log.Timber;
/* loaded from: classes7.dex */
public class MainProAidlService extends Service {

    /* renamed from: b  reason: collision with root package name */
    private static final String f14681b = "MainProAidlService";

    /* renamed from: a  reason: collision with root package name */
    private Context f14682a;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f14682a = this;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Timber.tag(f14681b).d("onBind MainProAidlService", new Object[0]);
        return new as.a(this.f14682a);
    }
}
