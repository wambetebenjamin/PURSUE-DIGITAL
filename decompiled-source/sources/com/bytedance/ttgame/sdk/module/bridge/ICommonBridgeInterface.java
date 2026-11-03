package com.bytedance.ttgame.sdk.module.bridge;

import android.app.Activity;
import android.content.Intent;
import java.util.HashMap;
/* loaded from: classes8.dex */
public abstract class ICommonBridgeInterface {
    private static final String TAG = "ICommonBridgeInterface";
    IActivityInterface mActivityInterface;
    IBridgeEvent mBridgeEvent = null;
    private boolean isDestory = false;

    public abstract String getName();

    public abstract boolean onActivityResult(Activity activity, int i2, int i3, Intent intent);

    public abstract void onBridgeCalled(String str, HashMap hashMap, IResultCallback iResultCallback);

    public void setActivityInterface(IActivityInterface iActivityInterface) {
        this.mActivityInterface = iActivityInterface;
    }

    public Activity getCurrentActivity() {
        IActivityInterface iActivityInterface = this.mActivityInterface;
        if (iActivityInterface != null) {
            return iActivityInterface.getCurActivity();
        }
        return null;
    }

    public void sendMessage(String str, HashMap hashMap) {
        IBridgeEvent iBridgeEvent = this.mBridgeEvent;
        if (iBridgeEvent != null) {
            iBridgeEvent.sendMessage(str, hashMap);
        }
    }

    public void setEventHandle(IBridgeEvent iBridgeEvent) {
        this.mBridgeEvent = iBridgeEvent;
    }

    public void setDestroyFlag(boolean z) {
        this.isDestory = z;
    }

    public boolean isDestroy() {
        return this.isDestory;
    }
}
