package com.bytedance.pipo.game.impl.net.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ProductsResponse.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\u0005H\u0016R \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/bytedance/pipo/game/impl/net/api/ProductResponse;", "Lcom/bytedance/pipo/game/impl/net/api/BaseResponse;", "code", "", "msg", "", "encryptMessage", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getEncryptMessage", "()Ljava/lang/String;", "setEncryptMessage", "(Ljava/lang/String;)V", "toString", "library_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class d extends a {
    @SerializedName("encrypt_ret_msg")

    /* renamed from: a  reason: collision with root package name */
    private String f10457a;

    public /* synthetic */ d(Integer num, String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str, (i2 & 4) != 0 ? null : str2);
    }

    public d(Integer num, String str, String str2) {
        super(num, str);
        this.f10457a = str2;
    }

    public final void b(String str) {
        this.f10457a = str;
    }

    public final String d() {
        return this.f10457a;
    }

    public String toString() {
        return "RocketGoods(code=" + a() + ", msg=" + b() + ", data=" + this.f10457a + ')';
    }
}
