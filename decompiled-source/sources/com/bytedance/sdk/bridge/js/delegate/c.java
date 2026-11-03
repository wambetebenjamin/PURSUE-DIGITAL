package com.bytedance.sdk.bridge.js.delegate;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: JsBridgeRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u001a\u0010\u000e\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\t\"\u0004\b\u0018\u0010\u000bR\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lcom/bytedance/sdk/bridge/js/delegate/JsBridgeRequest;", "", "msg", "Lorg/json/JSONObject;", "bridgeName", "", "(Lorg/json/JSONObject;Ljava/lang/String;)V", "callbackId", "getCallbackId", "()Ljava/lang/String;", "setCallbackId", "(Ljava/lang/String;)V", "currentUrl", "getCurrentUrl", "function", "getFunction", "setFunction", "params", "getParams", "()Lorg/json/JSONObject;", "setParams", "(Lorg/json/JSONObject;)V", "type", "getType", "setType", "version", "", "getVersion", "()I", "setVersion", "(I)V", "js-bridge_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private String f13229a;

    /* renamed from: b  reason: collision with root package name */
    private String f13230b;

    /* renamed from: c  reason: collision with root package name */
    private String f13231c;

    /* renamed from: d  reason: collision with root package name */
    private int f13232d;

    /* renamed from: e  reason: collision with root package name */
    private JSONObject f13233e;

    /* renamed from: f  reason: collision with root package name */
    private final String f13234f;

    public c(JSONObject msg, String bridgeName) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Intrinsics.checkParameterIsNotNull(bridgeName, "bridgeName");
        this.f13232d = msg.optInt("JSSDK", 0);
        String optString = msg.optString("__msg_type");
        Intrinsics.checkExpressionValueIsNotNull(optString, "msg.optString(\"__msg_type\")");
        this.f13229a = optString;
        String optString2 = msg.optString("__callback_id", "");
        Intrinsics.checkExpressionValueIsNotNull(optString2, "msg.optString(\"__callback_id\",\"\")");
        this.f13230b = optString2;
        this.f13231c = bridgeName;
        this.f13233e = msg.optJSONObject("params");
        String optString3 = msg.optString("currentUrl", "");
        Intrinsics.checkExpressionValueIsNotNull(optString3, "msg.optString(\"currentUrl\",\"\")");
        this.f13234f = optString3;
    }

    public final String getType() {
        return this.f13229a;
    }

    public final void setType(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.f13229a = str;
    }

    public final String getCallbackId() {
        return this.f13230b;
    }

    public final void setCallbackId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.f13230b = str;
    }

    public final String getFunction() {
        return this.f13231c;
    }

    public final void setFunction(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.f13231c = str;
    }

    public final int getVersion() {
        return this.f13232d;
    }

    public final void setVersion(int i2) {
        this.f13232d = i2;
    }

    public final JSONObject getParams() {
        return this.f13233e;
    }

    public final void setParams(JSONObject jSONObject) {
        this.f13233e = jSONObject;
    }

    public final String getCurrentUrl() {
        return this.f13234f;
    }
}
