package com.bytedance.ttgame.rocketapi.account;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public class ReleaseResult {
    public GSDKError gsdkError;
    public boolean needLogout;

    public String toString() {
        return "ReleaseResult{gsdkError=" + this.gsdkError + ", needLogout=" + this.needLogout + '}';
    }
}
