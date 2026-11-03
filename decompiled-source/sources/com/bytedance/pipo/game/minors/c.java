package com.bytedance.pipo.game.minors;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: MinorsLimitResponse.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\u0006\u0010\u0018\u001a\u00020\u0015J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/bytedance/pipo/game/minors/MinorsLimitResponse;", "", "code", "", "message", "", "data", "encryptMessage", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()I", "getData", "()Ljava/lang/String;", "getEncryptMessage", "getMessage", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "isSuccess", "toString", "library_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class c {
    @SerializedName("code")

    /* renamed from: a  reason: collision with root package name */
    private final int f10569a;
    @SerializedName("message")

    /* renamed from: b  reason: collision with root package name */
    private final String f10570b;
    @SerializedName("data")

    /* renamed from: c  reason: collision with root package name */
    private final String f10571c;
    @SerializedName("encrypt_ret_msg")

    /* renamed from: d  reason: collision with root package name */
    private final String f10572d;

    public static /* synthetic */ c a(c cVar, int i2, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = cVar.f10569a;
        }
        if ((i3 & 2) != 0) {
            str = cVar.f10570b;
        }
        if ((i3 & 4) != 0) {
            str2 = cVar.f10571c;
        }
        if ((i3 & 8) != 0) {
            str3 = cVar.f10572d;
        }
        return cVar.a(i2, str, str2, str3);
    }

    public final c a(int i2, String message, String data, String encryptMessage) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(encryptMessage, "encryptMessage");
        return new c(i2, message, data, encryptMessage);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            return this.f10569a == cVar.f10569a && Intrinsics.areEqual(this.f10570b, cVar.f10570b) && Intrinsics.areEqual(this.f10571c, cVar.f10571c) && Intrinsics.areEqual(this.f10572d, cVar.f10572d);
        }
        return false;
    }

    public final int f() {
        return this.f10569a;
    }

    public final String g() {
        return this.f10570b;
    }

    public final String h() {
        return this.f10571c;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.f10569a) * 31) + this.f10570b.hashCode()) * 31) + this.f10571c.hashCode()) * 31) + this.f10572d.hashCode();
    }

    public final String i() {
        return this.f10572d;
    }

    public String toString() {
        return "MinorsLimitResponse(code=" + this.f10569a + ", message=" + this.f10570b + ", data=" + this.f10571c + ", encryptMessage=" + this.f10572d + ')';
    }

    public c(int i2, String message, String data, String encryptMessage) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(encryptMessage, "encryptMessage");
        this.f10569a = i2;
        this.f10570b = message;
        this.f10571c = data;
        this.f10572d = encryptMessage;
    }

    public final int a() {
        return this.f10569a;
    }

    public final String b() {
        return this.f10570b;
    }

    public final String c() {
        return this.f10571c;
    }

    public final String d() {
        return this.f10572d;
    }

    public final boolean e() {
        return this.f10569a == 0 && (Intrinsics.areEqual(this.f10570b, "success") || Intrinsics.areEqual("Success", this.f10570b));
    }
}
