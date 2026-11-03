package com.bytedance.bdturing.net.models;

import java.util.Arrays;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: NativeResponse.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003J5\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u0005H\u0016J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/bytedance/bdturing/net/models/NativeResponse;", "", "byteArray", "", "responseCode", "", "responseMessage", "", "errorMessage", "([BILjava/lang/String;Ljava/lang/String;)V", "getByteArray", "()[B", "getErrorMessage", "()Ljava/lang/String;", "getResponseCode", "()I", "getResponseMessage", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "setting_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f4289a;

    /* renamed from: b  reason: collision with root package name */
    private final int f4290b;

    /* renamed from: c  reason: collision with root package name */
    private final String f4291c;

    /* renamed from: d  reason: collision with root package name */
    private final String f4292d;

    public static /* synthetic */ b a(b bVar, byte[] bArr, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            bArr = bVar.f4289a;
        }
        if ((i3 & 2) != 0) {
            i2 = bVar.f4290b;
        }
        if ((i3 & 4) != 0) {
            str = bVar.f4291c;
        }
        if ((i3 & 8) != 0) {
            str2 = bVar.f4292d;
        }
        return bVar.a(bArr, i2, str, str2);
    }

    public final b a(byte[] byteArray, int i2, String str, String str2) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        return new b(byteArray, i2, str, str2);
    }

    public final byte[] e() {
        return this.f4289a;
    }

    public final int f() {
        return this.f4290b;
    }

    public final String g() {
        return this.f4291c;
    }

    public final String h() {
        return this.f4292d;
    }

    public String toString() {
        return "NativeResponse(byteArray=" + Arrays.toString(this.f4289a) + ", responseCode=" + this.f4290b + ", responseMessage=" + ((Object) this.f4291c) + ", errorMessage=" + ((Object) this.f4292d) + ')';
    }

    public b(byte[] byteArray, int i2, String str, String str2) {
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        this.f4289a = byteArray;
        this.f4290b = i2;
        this.f4291c = str;
        this.f4292d = str2;
    }

    public /* synthetic */ b(byte[] bArr, int i2, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, (i3 & 2) != 0 ? -1 : i2, (i3 & 4) != 0 ? "" : str, (i3 & 8) != 0 ? "" : str2);
    }

    public final byte[] a() {
        return this.f4289a;
    }

    public final int b() {
        return this.f4290b;
    }

    public final String c() {
        return this.f4291c;
    }

    public final String d() {
        return this.f4292d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(getClass(), obj == null ? null : obj.getClass())) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type com.bytedance.bdturing.net.models.NativeResponse");
            b bVar = (b) obj;
            if (Arrays.equals(this.f4289a, bVar.f4289a) && this.f4290b == bVar.f4290b) {
                return Intrinsics.areEqual(this.f4291c, bVar.f4291c);
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((Arrays.hashCode(this.f4289a) * 31) + Integer.hashCode(this.f4290b)) * 31;
        String str = this.f4291c;
        return hashCode + (str != null ? str.hashCode() : 0);
    }
}
