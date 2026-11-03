package com.bytedance.ttgame.main.internal.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.bytedance.ttgame.main.internal.NetStateManager;
import com.bytedance.ttgame.rocketapi.callback.NetStateCallback;
import com.bytedance.ttgame.sdk.module.utils.NetworkChangeManager;
/* loaded from: classes3.dex */
public class NetStateBroadcast extends BroadcastReceiver {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final NetStateCallback netStateCallback;

    public NetStateBroadcast(NetStateCallback netStateCallback) {
        this.netStateCallback = netStateCallback;
    }

    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkChangeManager.ACTION);
        return intentFilter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        if (NetworkChangeManager.ACTION.equals(intent.getAction())) {
            this.handler.postDelayed(new Runnable() { // from class: com.bytedance.ttgame.main.internal.device.NetStateBroadcast.1
                @Override // java.lang.Runnable
                public void run() {
                    NetStateBroadcast.this.netStateCallback.stateChanged(NetStateManager.INSTANCE.getCurrentNetState(context));
                }
            }, 500L);
        }
    }
}
