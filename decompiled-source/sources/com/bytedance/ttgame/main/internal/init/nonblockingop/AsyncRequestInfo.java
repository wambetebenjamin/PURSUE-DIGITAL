package com.bytedance.ttgame.main.internal.init.nonblockingop;

import com.bytedance.ttgame.rocketapi.callback.GSDKDeviceInfoUpdateCallback;
/* loaded from: classes3.dex */
public class AsyncRequestInfo {
    private final GSDKDeviceInfoUpdateCallback callback;
    private final long startTimeStamp;
    private final long timeout;

    public AsyncRequestInfo(long j2, long j3, GSDKDeviceInfoUpdateCallback gSDKDeviceInfoUpdateCallback) {
        this.timeout = j3;
        this.startTimeStamp = j2;
        this.callback = gSDKDeviceInfoUpdateCallback;
    }

    public GSDKDeviceInfoUpdateCallback getCallback() {
        return this.callback;
    }

    public long getStartTimeStamp() {
        return this.startTimeStamp;
    }

    public long getTimeout() {
        return this.timeout;
    }
}
