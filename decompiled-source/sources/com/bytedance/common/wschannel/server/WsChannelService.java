package com.bytedance.common.wschannel.server;

import android.content.Intent;
import android.os.Message;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.common.wschannel.app.AbsMessengerService;
/* loaded from: classes2.dex */
public class WsChannelService extends AbsMessengerService {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getClientKey(com.bytedance.common.wschannel.app.b bVar) {
        if (bVar == null) {
            return Integer.MIN_VALUE;
        }
        return bVar.a();
    }

    @Override // com.bytedance.common.wschannel.app.AbsMessengerService, android.app.Service
    public void onCreate() {
        if (Logger.debug()) {
            Logger.d("WsChannelService", "onCreate");
        }
        super.onCreate();
    }

    @Override // com.bytedance.common.wschannel.app.AbsMessengerService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, int i2, int i3) {
        if (Logger.debug()) {
            Logger.d("WsChannelService", "onStartCommand");
        }
        com.bytedance.common.utility.concurrent.e.submitRunnable(new Runnable() { // from class: com.bytedance.common.wschannel.server.WsChannelService.1
            @Override // java.lang.Runnable
            public void run() {
                WsChannelService.this.handleIntent(intent);
            }
        });
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if (WsConstants.APP_STATE_ACTION.equals(action)) {
            int intExtra = intent.getIntExtra(WsConstants.KEY_APP_STATE, -1);
            Message message = new Message();
            message.what = 2;
            message.arg1 = intExtra;
            handleMsg(message);
        } else if (WsConstants.NETWORK_STATE_ACTION.equals(action)) {
            int intExtra2 = intent.getIntExtra(WsConstants.KEY_NETWORK_STATE, -1);
            Message message2 = new Message();
            message2.what = 3;
            message2.arg1 = intExtra2;
            handleMsg(message2);
        } else if (WsConstants.MEM_TRIM_ACTION.equals(action)) {
            int intExtra3 = intent.getIntExtra(WsConstants.KEY_MEM_TRIM, -1);
            com.bytedance.common.wschannel.h e2 = com.bytedance.common.wschannel.n.e();
            if (e2 != null) {
                e2.a(intExtra3);
            }
        }
    }

    @Override // com.bytedance.common.wschannel.app.AbsMessengerService, com.bytedance.common.utility.collection.WeakHandler.IHandler
    public void handleMsg(Message message) {
        super.handleMsg(message);
        if (message == null) {
            return;
        }
        if (Logger.debug()) {
            Logger.d("WsChannelService", "handleMsg msg.what = " + message.what);
        }
        k.a(this).handleMsg(message);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        final int f5894a;

        /* renamed from: b  reason: collision with root package name */
        final byte[] f5895b;

        /* renamed from: c  reason: collision with root package name */
        final long f5896c;

        /* renamed from: d  reason: collision with root package name */
        final com.bytedance.common.wschannel.model.i f5897d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(int i2, byte[] bArr, long j2) {
            this.f5894a = i2;
            this.f5895b = bArr;
            this.f5896c = j2;
            this.f5897d = null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(int i2, com.bytedance.common.wschannel.model.i iVar, long j2) {
            this.f5894a = i2;
            this.f5897d = iVar;
            this.f5896c = j2;
            this.f5895b = null;
        }
    }
}
