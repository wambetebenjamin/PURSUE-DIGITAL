package com.bytedance.ttgame.framework.gbridge.model;

import org.json.JSONObject;
/* loaded from: classes3.dex */
public class BridgeMsg {
    public static final String CALLBACK_ID = "callback_id";
    public static final String MSG_CODE = "code";
    public static final String MSG_DATA = "data";
    public static final String MSG_FAIL_MSG = "failMsg";
    public static final String MSG_ID = "msg_id";
    public static final String MSG_NATIVE_SDK_VER = "native_sdk_ver";
    public static final String MSG_PARAM = "param";
    public static final String MSG_SOURCE = "source";
    public static final String MSG_TARGET = "target";
    public static final String MSG_TYPE = "type";
    public static final String MSG_UNITY_SDK_VER = "unity_sdk_ver";
    public static final String MSG_VALUE = "value";
    public static final int SOURCE_ENGINE = 1;
    public static final int SOURCE_NATIVE = 2;
    public static final int TYPE_CALL = 0;
    public static final int TYPE_EVENT = 3;
    public static final int TYPE_LISTEN = 1;
    public static final int TYPE_UNLISTEN = 2;
    private long callbackId;
    private int code;
    private JSONObject data;
    private String failMsg;
    private long msgId;
    private int nativeSdkVer;
    private JSONObject param;
    private int source;
    private String target;
    private int type;
    private int unitySdkVer;

    public long getMsgId() {
        return this.msgId;
    }

    public void setMsgId(long j2) {
        this.msgId = j2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public long getCallbackId() {
        return this.callbackId;
    }

    public void setCallbackId(long j2) {
        this.callbackId = j2;
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public JSONObject getData() {
        return this.data;
    }

    public void setData(JSONObject jSONObject) {
        this.data = jSONObject;
    }

    public JSONObject getParam() {
        return this.param;
    }

    public void setParam(JSONObject jSONObject) {
        this.param = jSONObject;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public String getFailMsg() {
        return this.failMsg;
    }

    public void setFailMsg(String str) {
        this.failMsg = str;
    }

    public int getSource() {
        return this.source;
    }

    public void setSource(int i2) {
        this.source = i2;
    }

    public int getUnitySdkVer() {
        return this.unitySdkVer;
    }

    public void setUnitySdkVer(int i2) {
        this.unitySdkVer = i2;
    }

    public int getNativeSdkVer() {
        return this.nativeSdkVer;
    }

    public void setNativeSdkVer(int i2) {
        this.nativeSdkVer = i2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("BridgeMsg{");
        sb.append("msgId=").append(this.msgId);
        sb.append(", type=").append(this.type);
        sb.append(", callbackId=").append(this.callbackId);
        sb.append(", target='").append(this.target).append('\'');
        sb.append(", source=").append(this.source);
        sb.append(", data=").append(this.data);
        sb.append(", param=").append(this.param);
        sb.append(", code=").append(this.code);
        sb.append(", failMsg='").append(this.failMsg).append('\'');
        sb.append(", unitySdkVer=").append(this.unitySdkVer);
        sb.append(", nativeSdkVer=").append(this.nativeSdkVer);
        sb.append('}');
        return sb.toString();
    }
}
