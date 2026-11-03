package com.bytedance.ttgame.module.account.api;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes3.dex */
public interface IAccountErrorHandleService extends IModuleApi {

    /* loaded from: classes3.dex */
    public interface CancelError {
        GSDKError createGSDKError(String str);

        GSDKError createGSDKError(String str, String str2);
    }

    /* loaded from: classes3.dex */
    public interface ClientError {
        GSDKError createGSDKError(int i2, String str);

        GSDKError createGSDKError(String str);
    }

    /* loaded from: classes3.dex */
    public interface CloseError {
        GSDKError createGSDKError();
    }

    /* loaded from: classes3.dex */
    public interface NetworkError {
        GSDKError createGSDKError();

        GSDKError createGSDKError(Throwable th);

        int getNetworkErrorCode(Throwable th);

        String getNetworkErrorGMsg(Throwable th);
    }

    /* loaded from: classes3.dex */
    public interface SharkError {
        GSDKError createGSDKError(int i2, String str);
    }

    /* loaded from: classes3.dex */
    public interface Success {
        GSDKError createGSDKError();
    }

    GSDKError convertError(int i2, String str);

    GSDKError convertOSDKError(int i2, String str);

    CancelError getCancelError();

    ClientError getClientError();

    CloseError getCloseError();

    NetworkError getNetworkError();

    SharkError getSharkError();

    Success getSuccess();
}
