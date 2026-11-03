package com.bytedance.ttgame.channel.adapter.v4;

import com.bytedance.apm.constant.l;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\bR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/channel/adapter/v4/V4Response;", "", "code", "", "msg", "", l.KEY_LOG_ID, "data", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()I", "setCode", "(I)V", "getData", "()Ljava/lang/String;", "setData", "(Ljava/lang/String;)V", "getLog_id", "setLog_id", "getMsg", "setMsg", "pay_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class V4Response {
    @SerializedName("code")
    private int code;
    @SerializedName("data")
    private String data;
    @SerializedName(l.KEY_LOG_ID)
    private String log_id;
    @SerializedName("message")
    private String msg;

    public V4Response(int i2, String msg, String log_id, String str) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        Intrinsics.checkNotNullParameter(log_id, "log_id");
        this.code = i2;
        this.msg = msg;
        this.log_id = log_id;
        this.data = str;
    }

    public /* synthetic */ V4Response(int i2, String str, String str2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, str, str2, (i3 & 8) != 0 ? null : str3);
    }

    public final int getCode() {
        return this.code;
    }

    public final void setCode(int i2) {
        this.code = i2;
    }

    public final String getMsg() {
        return this.msg;
    }

    public final void setMsg(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.msg = str;
    }

    public final String getLog_id() {
        return this.log_id;
    }

    public final void setLog_id(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.log_id = str;
    }

    public final String getData() {
        return this.data;
    }

    public final void setData(String str) {
        this.data = str;
    }
}
