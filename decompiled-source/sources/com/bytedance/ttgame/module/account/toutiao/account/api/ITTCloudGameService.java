package com.bytedance.ttgame.module.account.toutiao.account.api;

import android.app.Activity;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.bytedance.ttgame.rocketapi.account.UserInfoResult;
/* loaded from: classes4.dex */
public interface ITTCloudGameService extends IModuleApi {
    void cloudGameLogin(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback);

    default void cloudGameLoginV2(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback) {
    }

    boolean isCloudRuntime();
}
