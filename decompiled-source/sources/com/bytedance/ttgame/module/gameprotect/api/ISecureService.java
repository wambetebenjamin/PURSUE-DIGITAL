package com.bytedance.ttgame.module.gameprotect.api;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes6.dex */
public interface ISecureService extends IModuleApi {
    boolean canShowVisitor();

    long emulatorJudgmentWaitTime();

    String getEmulatorType();

    byte[] getGPPacketData();

    byte[] getGPPacketText();

    int getIsEmulatorResult();

    String getPayEnv();

    void isEmulator(ISecureEmulatorCallback iSecureEmulatorCallback);

    void isEmulatorBackUp(ISecureEmulatorCallback iSecureEmulatorCallback);

    void isFrameCapture(ISecureFrameCaptureCallBack iSecureFrameCaptureCallBack);

    void isFrameCapture(ISecureFrameCaptureCallBack iSecureFrameCaptureCallBack, long j2);

    void reportRiskInfo(String str);

    void setParams(String str, String str2);

    void setPriority(int i2);

    void setUserInfo(int i2, String str, String str2);

    GSDKError uploadPacketData(byte[] bArr);

    GSDKError uploadPacketText(byte[] bArr);
}
