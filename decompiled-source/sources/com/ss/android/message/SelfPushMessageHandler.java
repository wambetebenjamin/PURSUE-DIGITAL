package com.ss.android.message;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.ss.android.pushmanager.app.a;
@Deprecated
/* loaded from: classes9.dex */
public class SelfPushMessageHandler extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        int onStartCommand = super.onStartCommand(intent, i2, i3);
        if (((a.b) com.ss.android.ug.bus.b.getService(a.b.class)).needControlFlares()) {
            return 2;
        }
        return onStartCommand;
    }
}
