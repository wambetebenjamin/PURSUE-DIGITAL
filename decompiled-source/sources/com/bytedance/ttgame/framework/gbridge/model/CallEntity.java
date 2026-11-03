package com.bytedance.ttgame.framework.gbridge.model;

import com.bytedance.ttgame.framework.gbridge.GBridgeCallback;
/* loaded from: classes3.dex */
public class CallEntity {
    private long addTime;
    private String callName;
    private GBridgeCallback callback;
    private long timeout;

    public String getCallName() {
        return this.callName;
    }

    public void setCallName(String str) {
        this.callName = str;
    }

    public long getAddTime() {
        return this.addTime;
    }

    public void setAddTime(long j2) {
        this.addTime = j2;
    }

    public GBridgeCallback getCallback() {
        return this.callback;
    }

    public void setCallback(GBridgeCallback gBridgeCallback) {
        this.callback = gBridgeCallback;
    }

    public long getTimeout() {
        return this.timeout;
    }

    public void setTimeout(long j2) {
        this.timeout = j2;
    }
}
