package com.bytedance.push.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
/* loaded from: classes2.dex */
public class PushProcessService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        return 2;
    }
}
