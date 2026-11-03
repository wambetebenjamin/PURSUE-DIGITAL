package com.bytedance.pipo.game.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
/* compiled from: PipoOrderResponse.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\t\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R \u0010\u0013\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR \u0010\u0016\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\r\"\u0004\b\u0018\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/bytedance/pipo/game/api/PipoOrderResponse;", "", "()V", "code", "", "getCode", "()Ljava/lang/Integer;", "setCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "encryptMessage", "", "getEncryptMessage", "()Ljava/lang/String;", "setEncryptMessage", "(Ljava/lang/String;)V", "isSuccess", "", "()Z", "msg", "getMsg", "setMsg", "payInfo", "getPayInfo", "setPayInfo", "library_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class m {
    @SerializedName("code")

    /* renamed from: a  reason: collision with root package name */
    private Integer f10124a;
    @SerializedName("message")

    /* renamed from: b  reason: collision with root package name */
    private String f10125b;
    @SerializedName("encrypt_ret_msg")

    /* renamed from: c  reason: collision with root package name */
    private String f10126c;
    @SerializedName("payInfo")

    /* renamed from: d  reason: collision with root package name */
    private String f10127d;

    public final Integer a() {
        return this.f10124a;
    }

    public final void a(Integer num) {
        this.f10124a = num;
    }

    public final void a(String str) {
        this.f10125b = str;
    }

    public final String b() {
        return this.f10125b;
    }

    public final void b(String str) {
        this.f10126c = str;
    }

    public final String c() {
        return this.f10126c;
    }

    public final void c(String str) {
        this.f10127d = str;
    }

    public final String d() {
        return this.f10127d;
    }

    public final boolean e() {
        Integer num = this.f10124a;
        return num != null && num.intValue() == 0;
    }
}
