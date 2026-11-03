package com.bytedance.ttgame.module.push.api;

import com.brentvatne.react.ReactVideoView;
import com.bytedance.ttgame.base.GSDKError;
import kotlin.Metadata;
/* compiled from: IPushCallback.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/module/push/api/ILocalPushCallback;", "", "onFail", "", "gsdkError", "Lcom/bytedance/ttgame/base/GSDKError;", "onSuccess", ReactVideoView.EVENT_PROP_METADATA_IDENTIFIER, "", "push_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface ILocalPushCallback {
    void onFail(GSDKError gSDKError);

    void onSuccess(String str);
}
