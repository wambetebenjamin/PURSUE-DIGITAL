package com.bytedance.common.process.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.bytedance.push.utils.h;
import com.ss.android.message.a;
import com.ss.android.push_common_lib.b;
import java.util.List;
/* loaded from: classes2.dex */
public class BaseCrossProcessService extends Service {
    public static final String EXTRA_KEY_IS_FROM_ON_BIND = "is_from_on_bind";
    public static final String EXTRA_KEY_PROCESS = "process";
    private final String TAG = "BaseCrossProcessService";
    private Context mContext = this;
    private final b.a mBinder = new b.a() { // from class: com.bytedance.common.process.service.BaseCrossProcessService.1
        @Override // com.ss.android.push_common_lib.b
        public String invoke(String str, String str2, List list) {
            h.d("BaseCrossProcessService", com.ss.android.message.util.b.getCurProcess(BaseCrossProcessService.this.mContext) + " process method " + str + "is called");
            return com.bytedance.common.process.cross.b.getInstance().a(com.bytedance.common.model.b.parseProcess(str2), str, list);
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        com.bytedance.common.model.b curProcess = com.ss.android.message.util.b.getCurProcess(this.mContext);
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String string = extras.getString("process");
            if (!extras.getBoolean(EXTRA_KEY_IS_FROM_ON_BIND)) {
                h.d("BaseCrossProcessService", curProcess + " process service is called by " + string);
                a.initApp(getApplication());
                com.bytedance.common.process.cross.b.getInstance().onServiceBind(string);
            }
        }
        return this.mBinder;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        super.onStartCommand(intent, i2, i3);
        return 2;
    }
}
