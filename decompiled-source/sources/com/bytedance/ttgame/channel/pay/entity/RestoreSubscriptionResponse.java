package com.bytedance.ttgame.channel.pay.entity;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: RocketGoodsResponse.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\u0005H\u0016R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/bytedance/ttgame/channel/pay/entity/RestoreSubscriptionResponse;", "Lcom/bytedance/ttgame/channel/pay/entity/BaseResponse;", "code", "", "msg", "", "encryptMessage", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getEncryptMessage", "()Ljava/lang/String;", "setEncryptMessage", "(Ljava/lang/String;)V", "toString", "pay_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RestoreSubscriptionResponse extends BaseResponse {
    @SerializedName("encrypt_ret_msg")
    private String encryptMessage;

    public /* synthetic */ RestoreSubscriptionResponse(Integer num, String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str, (i2 & 4) != 0 ? null : str2);
    }

    public RestoreSubscriptionResponse(Integer num, String str, String str2) {
        super(num, str);
        this.encryptMessage = str2;
    }

    public final String getEncryptMessage() {
        return this.encryptMessage;
    }

    public final void setEncryptMessage(String str) {
        this.encryptMessage = str;
    }

    public String toString() {
        return "RocketGoods(code=" + getCode() + ", msg=" + getMsg() + ", data=" + this.encryptMessage + ')';
    }
}
