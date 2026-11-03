package com.bytedance.ttgame.module.gameprotect.bridge;

import android.util.Base64;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.channel.account.ErrorConstants;
import com.bytedance.ttgame.framework.gbridge.GBridgeContext;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeMethod;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeParam;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import com.bytedance.ttgame.framework.gbridge.plugin.SdkLog;
import com.bytedance.ttgame.framework.gbridge.spi.ServiceManager;
import com.bytedance.ttgame.module.gameprotect.api.ISecureFrameCaptureCallBack;
import com.bytedance.ttgame.module.gameprotect.api.ISecureService;
import com.bytedance.ttgame.module.gameprotect.api.common.SecureErrorCode;
import java.util.HashMap;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class SecureModule implements BaseModule {
    private String mTunnel;

    public SecureModule(String str) {
        this.mTunnel = str;
    }

    @GBridgeMethod(callName = "readPacketText", sync = true)
    public String readPacketText() {
        return new String(Base64.encode(((ISecureService) ServiceManager.get().getService(ISecureService.class)).getGPPacketText(), 0));
    }

    @GBridgeMethod(callName = "writePacketText", sync = true)
    public JSONObject writePacketText(@GBridgeParam("data") String str) {
        GSDKError uploadPacketText = ((ISecureService) ServiceManager.get().getService(ISecureService.class)).uploadPacketText(Base64.decode(str, 0));
        SdkLog.i(BaseModule.TAG, "writePacketText result:" + uploadPacketText);
        JSONObject jSONObject = new JSONObject();
        BaseModule.add(jSONObject, "code", uploadPacketText.getCode());
        BaseModule.add(jSONObject, "message", uploadPacketText.getMessage());
        BaseModule.add(jSONObject, ErrorConstants.EXTRA_ERROR_CODE, uploadPacketText.getExtraErrorCode());
        BaseModule.add(jSONObject, ErrorConstants.EXTRA_ERROR_MESSAGE, uploadPacketText.getExtraErrorMessage());
        BaseModule.add(jSONObject, ErrorConstants.ADDITIONAL_INFO, uploadPacketText.getAdditionalInfo());
        return jSONObject;
    }

    @GBridgeMethod(callName = "setPriority")
    public void setPriority(@GBridgeParam("data") int i2) {
        SdkLog.i(BaseModule.TAG, "setPriority---data=" + i2);
        ((ISecureService) ServiceManager.get().getService(ISecureService.class)).setPriority(i2);
    }

    @GBridgeMethod(callName = "setUserInfo")
    public void setUserInfo(@GBridgeParam("userType") int i2, @GBridgeParam("roleId") String str, @GBridgeParam("serverId") String str2) {
        SdkLog.i(BaseModule.TAG, "setUserInfo---userType=" + i2 + "---roleId=" + str + "---serverId=" + str2);
        ((ISecureService) ServiceManager.get().getService(ISecureService.class)).setUserInfo(i2, str, str2);
    }

    @GBridgeMethod(callName = "isFrameCaptureWithTimeout")
    public void isFrameCaptureWithTimeout(@GBridgeParam final GBridgeContext gBridgeContext, @GBridgeParam("timeout") long j2) {
        SdkLog.i(BaseModule.TAG, "start to call isFrameCaptureWithTimeout, timeout = " + j2);
        ISecureService iSecureService = (ISecureService) ServiceManager.get().getService(ISecureService.class);
        ISecureFrameCaptureCallBack iSecureFrameCaptureCallBack = new ISecureFrameCaptureCallBack() { // from class: com.bytedance.ttgame.module.gameprotect.bridge.SecureModule.1
            @Override // com.bytedance.ttgame.module.gameprotect.api.ISecureFrameCaptureCallBack
            public void onSuccess(boolean z, String str) {
                HashMap hashMap = new HashMap();
                hashMap.put("code", 0);
                hashMap.put("message", "success");
                hashMap.put("result", Boolean.valueOf(z));
                hashMap.put("detail", str);
                gBridgeContext.callBackResult(hashMap);
                SdkLog.i(BaseModule.TAG, "isFrameCaptureWithTimeout onSuccess callback, result: " + z + " detail: " + str);
            }

            @Override // com.bytedance.ttgame.module.gameprotect.api.ISecureFrameCaptureCallBack
            public void onFailure(String str) {
                HashMap hashMap = new HashMap();
                hashMap.put("code", Integer.valueOf((int) SecureErrorCode.IS_FRAME_CAPTURE_FAILED));
                hashMap.put("message", str);
                hashMap.put("errorMsg", str);
                gBridgeContext.callBackResult(hashMap);
                SdkLog.i(BaseModule.TAG, "isFrameCaptureWithTimeout onFailure callback, errorMsg: " + str);
            }
        };
        if (j2 <= 0) {
            j2 = 0;
        }
        iSecureService.isFrameCapture(iSecureFrameCaptureCallBack, j2);
    }

    @GBridgeMethod(callName = "isFrameCapture")
    public void isFrameCapture(@GBridgeParam final GBridgeContext gBridgeContext) {
        SdkLog.i(BaseModule.TAG, "start to call isFrameCapture");
        ((ISecureService) ServiceManager.get().getService(ISecureService.class)).isFrameCapture(new ISecureFrameCaptureCallBack() { // from class: com.bytedance.ttgame.module.gameprotect.bridge.SecureModule.2
            @Override // com.bytedance.ttgame.module.gameprotect.api.ISecureFrameCaptureCallBack
            public void onSuccess(boolean z, String str) {
                HashMap hashMap = new HashMap();
                hashMap.put("code", 0);
                hashMap.put("message", "success");
                hashMap.put("result", Boolean.valueOf(z));
                hashMap.put("detail", str);
                gBridgeContext.callBackResult(hashMap);
                SdkLog.i(BaseModule.TAG, "isFrameCapture onSuccess callback");
            }

            @Override // com.bytedance.ttgame.module.gameprotect.api.ISecureFrameCaptureCallBack
            public void onFailure(String str) {
                HashMap hashMap = new HashMap();
                hashMap.put("code", Integer.valueOf((int) SecureErrorCode.IS_FRAME_CAPTURE_FAILED));
                hashMap.put("message", str);
                hashMap.put("errorMsg", str);
                gBridgeContext.callBackResult(hashMap);
                SdkLog.i(BaseModule.TAG, "isFrameCapture onFailure callback");
            }
        });
    }

    @Override // com.bytedance.ttgame.framework.gbridge.IBridgeModule
    public String getBridgeTunnel() {
        return this.mTunnel;
    }
}
