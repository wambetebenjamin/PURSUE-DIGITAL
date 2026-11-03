package com.bytedance.ttgame.module.push.api;

import android.app.Activity;
import kotlin.Metadata;
/* compiled from: IPushCallback.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/module/push/api/IActivityAwarePushCallback;", "Lcom/bytedance/ttgame/module/push/api/IPushCallback;", "onReceivedPush", "", "activity", "Landroid/app/Activity;", "pushInfo", "Lcom/bytedance/ttgame/module/push/api/PushInfo;", "push_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface IActivityAwarePushCallback extends IPushCallback {
    void onReceivedPush(Activity activity, PushInfo pushInfo);
}
