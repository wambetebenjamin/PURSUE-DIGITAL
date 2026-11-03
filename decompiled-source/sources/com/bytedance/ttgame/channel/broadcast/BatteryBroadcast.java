package com.bytedance.ttgame.channel.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.bytedance.ttgame.rocketapi.callback.StateChangedCallback;
/* loaded from: classes3.dex */
public class BatteryBroadcast extends BroadcastReceiver {
    private StateChangedCallback mStateChangedCallback;

    public BatteryBroadcast(StateChangedCallback stateChangedCallback) {
        this.mStateChangedCallback = stateChangedCallback;
    }

    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        return intentFilter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        StateChangedCallback stateChangedCallback;
        String action = intent.getAction();
        if ("android.intent.action.ACTION_POWER_CONNECTED".equals(action)) {
            StateChangedCallback stateChangedCallback2 = this.mStateChangedCallback;
            if (stateChangedCallback2 != null) {
                stateChangedCallback2.stateChanged(true);
            }
        } else if (!"android.intent.action.ACTION_POWER_DISCONNECTED".equals(action) || (stateChangedCallback = this.mStateChangedCallback) == null) {
        } else {
            stateChangedCallback.stateChanged(false);
        }
    }
}
