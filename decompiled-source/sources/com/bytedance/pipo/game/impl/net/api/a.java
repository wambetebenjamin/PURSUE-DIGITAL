package com.bytedance.pipo.game.impl.net.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
/* compiled from: ProductsResponse.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/bytedance/pipo/game/impl/net/api/BaseResponse;", "", "code", "", "msg", "", "(Ljava/lang/Integer;Ljava/lang/String;)V", "getCode", "()Ljava/lang/Integer;", "setCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "isSuccess", "", "()Z", "getMsg", "()Ljava/lang/String;", "setMsg", "(Ljava/lang/String;)V", "library_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public class a {
    @SerializedName("code")

    /* renamed from: a  reason: collision with root package name */
    private Integer f10453a;
    @SerializedName("message")

    /* renamed from: b  reason: collision with root package name */
    private String f10454b;

    public a(Integer num, String str) {
        this.f10453a = num;
        this.f10454b = str;
    }

    public final Integer a() {
        return this.f10453a;
    }

    public final void a(Integer num) {
        this.f10453a = num;
    }

    public final void a(String str) {
        this.f10454b = str;
    }

    public final String b() {
        return this.f10454b;
    }

    public final boolean c() {
        Integer num = this.f10453a;
        return num != null && num.intValue() == 0;
    }
}
