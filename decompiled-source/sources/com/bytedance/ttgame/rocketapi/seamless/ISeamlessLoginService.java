package com.bytedance.ttgame.rocketapi.seamless;

import android.app.Activity;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.bytedance.ttgame.rocketapi.account.UserInfoResult;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessLoginInfo;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessLoginResult;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessUnlinkInfo;
/* loaded from: classes7.dex */
public interface ISeamlessLoginService extends IModuleApi {
    void getSeamlessLoginAccounts(Activity activity, IAccountCallback<SeamlessLoginResult> iAccountCallback);

    boolean isSeamlessLoginEnabled();

    void linkSeamlessAccount(Activity activity, IAccountCallback<GSDKError> iAccountCallback);

    void seamlessLogin(SeamlessLoginInfo seamlessLoginInfo, IAccountCallback<UserInfoResult> iAccountCallback);

    void unlinkSeamlessAccount(Activity activity, SeamlessUnlinkInfo seamlessUnlinkInfo, IAccountCallback<GSDKError> iAccountCallback);
}
