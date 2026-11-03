package com.bytedance.ttgame.module.account.toutiao.account.api;

import com.bytedance.ttgame.base.GSDKError;
import kotlin.Metadata;
/* compiled from: IAccountAgeGateCallback.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J(\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/bytedance/ttgame/module/account/toutiao/account/api/IAccountAgeGateCallback;", "", "onFail", "", "gsdkError", "Lcom/bytedance/ttgame/base/GSDKError;", "onSuccess", "currentTimestamp", "", "windowType", "", "limitAge", "isInCoolingPeriod", "", "account_api_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface IAccountAgeGateCallback {
    void onFail(GSDKError gSDKError);

    void onSuccess(long j2, int i2, int i3, boolean z);
}
