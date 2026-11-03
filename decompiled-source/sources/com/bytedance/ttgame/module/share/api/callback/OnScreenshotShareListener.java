package com.bytedance.ttgame.module.share.api.callback;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.share.api.entity.TTShareResult;
import com.bytedance.ttgame.module.share.api.utils.ConstantKt;
import kotlin.Metadata;
/* compiled from: OnScreenshotShareListener.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/bytedance/ttgame/module/share/api/callback/OnScreenshotShareListener;", "", "onGetScreenshotFailedEvent", "", "error", "Lcom/bytedance/ttgame/base/GSDKError;", "onPanelShareResultEvent", "result", "Lcom/bytedance/ttgame/module/share/api/entity/TTShareResult;", "onPassThroughImageEvent", ConstantKt.IMAGE_PATH, "", "share_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface OnScreenshotShareListener {
    void onGetScreenshotFailedEvent(GSDKError gSDKError);

    void onPanelShareResultEvent(TTShareResult tTShareResult);

    void onPassThroughImageEvent(String str);
}
