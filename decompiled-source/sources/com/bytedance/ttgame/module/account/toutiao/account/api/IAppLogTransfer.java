package com.bytedance.ttgame.module.account.toutiao.account.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
@InternalApi
/* loaded from: classes4.dex */
public interface IAppLogTransfer extends IModuleApi {
    void sendSwitchLoginBegin();

    void sendSwitchLoginCheckResult(boolean z);
}
