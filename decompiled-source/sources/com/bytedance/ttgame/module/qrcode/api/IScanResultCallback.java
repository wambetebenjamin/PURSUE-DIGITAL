package com.bytedance.ttgame.module.qrcode.api;

import com.bytedance.ttgame.base.GSDKError;
import java.io.Serializable;
/* loaded from: classes6.dex */
public interface IScanResultCallback extends Serializable {
    void onFailed(GSDKError gSDKError);

    void onSuccess(String str);
}
