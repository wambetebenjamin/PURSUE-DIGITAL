package com.bytedance.ttgame.rocketapi.pay;

import com.bytedance.ttgame.base.GSDKError;
/* loaded from: classes7.dex */
public class RocketPayResult {
    @Deprecated
    private String channelCode;
    @Deprecated
    private String channelMsg;
    private String gameOrderId = "";
    private String gameProductId;
    public GSDKError gsdkError;
    @Deprecated
    private String mOriginCode;
    private String outSideOrderId;

    public RocketPayResult() {
    }

    private RocketPayResult(int i2, String str) {
        this.gsdkError = new GSDKError(i2, str);
    }

    public String getGameOrderId() {
        return this.gameOrderId;
    }

    @Deprecated
    public String getChannelCode() {
        return this.channelCode;
    }

    @Deprecated
    public String getChannelMsg() {
        return this.channelMsg;
    }

    public void setGameOrderId(String str) {
        this.gameOrderId = str;
    }

    public String getGameProductId() {
        return this.gameProductId;
    }

    public void setGameProductId(String str) {
        this.gameProductId = str;
    }

    @Deprecated
    public void setChannelCode(String str) {
        this.channelCode = str;
    }

    @Deprecated
    public void setChannelMsg(String str) {
        this.channelMsg = str;
    }

    public String getOutSideOrderId() {
        return this.outSideOrderId;
    }

    public void setOutSideOrderId(String str) {
        this.outSideOrderId = str;
    }

    public static RocketPayResult newSuccessPayResult() {
        return new RocketPayResult(0, "pay success.");
    }

    public static RocketPayResult newErrorResult(int i2, String str, int i3, String str2) {
        RocketPayResult rocketPayResult = new RocketPayResult();
        if (str.isEmpty()) {
            rocketPayResult.gsdkError = new GSDKError(i2, str);
        } else {
            rocketPayResult.gsdkError = new GSDKError(i2, str, i3, str2);
        }
        return rocketPayResult;
    }

    public static RocketPayResult newErrorResult(int i2, String str) {
        return newErrorResult(i2, str, i2, null);
    }

    public static RocketPayResult newRocketErrorPayResult(int i2, String str) {
        RocketPayResult rocketPayResult = new RocketPayResult();
        rocketPayResult.mOriginCode = String.valueOf(i2);
        if (i2 >= 0) {
            i2 = -i2;
        }
        rocketPayResult.gsdkError = new GSDKError(i2, str);
        return rocketPayResult;
    }

    public static RocketPayResult newChannelErrorPayResult(int i2, String str) {
        RocketPayResult rocketPayResult = new RocketPayResult();
        rocketPayResult.gsdkError = new GSDKError(Math.abs(i2), str);
        rocketPayResult.mOriginCode = String.valueOf(i2);
        return rocketPayResult;
    }

    public static RocketPayResult newChannelErrorPayResult(int i2, String str, int i3, String str2) {
        RocketPayResult rocketPayResult = new RocketPayResult();
        rocketPayResult.gsdkError = new GSDKError(Math.abs(i2), str, i3, str2);
        rocketPayResult.mOriginCode = String.valueOf(i2);
        return rocketPayResult;
    }

    public String toString() {
        return "RocketPayResult{errorInfo= " + this.gsdkError + ", gameOrderId='" + this.gameOrderId + "', gameProductId='" + this.gameProductId + "'}";
    }
}
