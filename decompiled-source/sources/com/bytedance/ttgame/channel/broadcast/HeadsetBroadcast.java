package com.bytedance.ttgame.channel.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.bytedance.ttgame.rocketapi.callback.StateChangedCallback;
/* loaded from: classes3.dex */
public class HeadsetBroadcast extends BroadcastReceiver {
    private StateChangedCallback mStateChangedCallback;

    public HeadsetBroadcast(StateChangedCallback stateChangedCallback) {
        this.mStateChangedCallback = stateChangedCallback;
    }

    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        return intentFilter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        StateChangedCallback stateChangedCallback;
        StateChangedCallback stateChangedCallback2;
        String action = intent.getAction();
        if ("android.intent.action.HEADSET_PLUG".equals(action)) {
            int intExtra = intent.getIntExtra("state", -1);
            if (intExtra == 0) {
                StateChangedCallback stateChangedCallback3 = this.mStateChangedCallback;
                if (stateChangedCallback3 != null) {
                    stateChangedCallback3.stateChanged(false);
                }
            } else if (intExtra != 1 || (stateChangedCallback2 = this.mStateChangedCallback) == null) {
            } else {
                stateChangedCallback2.stateChanged(true);
            }
        } else if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
            int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
            if (intExtra2 == 0) {
                StateChangedCallback stateChangedCallback4 = this.mStateChangedCallback;
                if (stateChangedCallback4 != null) {
                    stateChangedCallback4.stateChanged(false);
                }
            } else if (intExtra2 != 2 || (stateChangedCallback = this.mStateChangedCallback) == null) {
            } else {
                stateChangedCallback.stateChanged(true);
            }
        }
    }
}
