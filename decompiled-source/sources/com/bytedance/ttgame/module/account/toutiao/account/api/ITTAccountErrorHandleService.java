package com.bytedance.ttgame.module.account.toutiao.account.api;

import com.bytedance.sdk.account.api.call.b;
import com.bytedance.sdk.account.api.call.h;
import com.bytedance.sdk.account.platform.base.d;
import com.bytedance.ttgame.base.BaseResponse;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.LogoutDeviceResponse;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.TTSwitchAccountResponse;
import com.bytedance.ttgame.module.account.toutiao.account.pojo.UserInfoResponse;
/* loaded from: classes4.dex */
public interface ITTAccountErrorHandleService extends IModuleApi {

    /* loaded from: classes4.dex */
    public interface ClientError {
        UserInfoResponse createUserInfoResponse(String str);
    }

    /* loaded from: classes4.dex */
    public interface CloseError {
        UserInfoResponse createUserInfoResponse();
    }

    /* loaded from: classes4.dex */
    public interface NetworkError {
        UserInfoResponse createUserInfoResponse();

        UserInfoResponse createUserInfoResponse(String str, Throwable th, String str2);

        UserInfoResponse createUserInfoResponse(Throwable th, String str);
    }

    GSDKError convertError(UserInfoResponse userInfoResponse);

    GSDKError convertPassportError(b bVar);

    GSDKError convertPassportError(h hVar);

    GSDKError convertPassportError(h hVar, String str);

    GSDKError convertPassportError(d dVar);

    GSDKError convertPassportError(TTSwitchAccountResponse tTSwitchAccountResponse);

    UserInfoResponse convertPassportErrorToUserInfoResponse(b bVar);

    UserInfoResponse convertPassportErrorToUserInfoResponse(h hVar);

    UserInfoResponse convertPassportErrorToUserInfoResponse(d dVar);

    GSDKError convertServerError(BaseResponse baseResponse);

    GSDKError convertServerError(LogoutDeviceResponse logoutDeviceResponse);

    ClientError getClientError();

    CloseError getCloseError();

    NetworkError getNetworkError();

    boolean isCustomPassportError(int i2);

    boolean isNumeric(String str);
}
