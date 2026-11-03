package com.bytedance.ttgame.main.internal;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes3.dex */
public class EmulatorResult {
    public GSDKError gsdkError;
    public boolean isEmulator;

    public EmulatorResult(GSDKError gSDKError, boolean z) {
        this.gsdkError = gSDKError;
        this.isEmulator = z;
    }
}
