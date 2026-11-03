package com.bytedance.ttgame.main.internal;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes3.dex */
public class HeadsetPluggedResult {
    public GSDKError gsdkError;
    public boolean isPlugged;

    public HeadsetPluggedResult(boolean z, GSDKError gSDKError) {
        this.isPlugged = z;
        this.gsdkError = gSDKError;
    }
}
