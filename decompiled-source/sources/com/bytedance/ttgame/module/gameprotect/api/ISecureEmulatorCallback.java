package com.bytedance.ttgame.module.gameprotect.api;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes6.dex */
public interface ISecureEmulatorCallback {
    void OnFailed(GSDKError gSDKError);

    void OnSuccess(boolean z, String str);
}
