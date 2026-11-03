package com.bytedance.sdk.open.tiktok.common.handler;

import android.content.Intent;
import com.bytedance.sdk.open.tiktok.common.model.BaseReq;
import com.bytedance.sdk.open.tiktok.common.model.BaseResp;
/* loaded from: classes3.dex */
public interface IApiEventHandler {
    void onErrorIntent(Intent intent);

    void onReq(BaseReq baseReq);

    void onResp(BaseResp baseResp);
}
