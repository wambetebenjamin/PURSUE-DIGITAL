package com.bytedance.ttgame.sdk.module.account.api;

import android.app.Activity;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessLoginInfo;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessLoginResult;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessUnlinkInfo;
/* loaded from: classes3.dex */
public interface ITTSeamlessLoginService extends IModuleApi {
    void getSeamlessLoginAccounts(Activity activity, IAccountCallback<SeamlessLoginResult> iAccountCallback);

    boolean isSeamlessLoginEnabled();

    void linkSeamlessAccount(Activity activity, IAccountCallback<GSDKError> iAccountCallback);

    void seamlessLogin(SeamlessLoginInfo seamlessLoginInfo, IAccountCallback<TTUserInfoResult> iAccountCallback);

    void unlinkSeamlessAccount(Activity activity, SeamlessUnlinkInfo seamlessUnlinkInfo, IAccountCallback<GSDKError> iAccountCallback);
}
