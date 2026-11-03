package com.bytedance.ttgame.module.account.toutiao.account.api;

import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
@InternalApi
/* loaded from: classes4.dex */
public interface ITTAccountInternalApi extends IModuleApi {
    TTUserInfo getTTUserInfo();

    void initAuthService(Context context);

    void initSpManager(Context context);

    void setTTUserInfo(TTUserInfo tTUserInfo);
}
