package com.ss.android.ugc.aweme.pns.consentapi.data;

import com.google.gson.annotations.SerializedName;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ConsentStatusRecord.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentapi/data/BaseResponse;", "Ljava/io/Serializable;", "statusCode", "", "statusMsg", "", "(ILjava/lang/String;)V", "getStatusCode", "()I", "getStatusMsg", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "pns_consent_sdk_api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes9.dex */
public final class a implements Serializable {
    @SerializedName(MonitorConstants.STATUS_CODE)

    /* renamed from: a  reason: collision with root package name */
    private final int f18683a;
    @SerializedName("status_msg")

    /* renamed from: b  reason: collision with root package name */
    private final String f18684b;

    public a() {
        this(0, null, 3, null);
    }

    public static /* synthetic */ a a(a aVar, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = aVar.f18683a;
        }
        if ((i3 & 2) != 0) {
            str = aVar.f18684b;
        }
        return aVar.a(i2, str);
    }

    public final a a(int i2, String str) {
        return new a(i2, str);
    }

    public final int c() {
        return this.f18683a;
    }

    public final String d() {
        return this.f18684b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            a aVar = (a) obj;
            return this.f18683a == aVar.f18683a && Intrinsics.areEqual(this.f18684b, aVar.f18684b);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.f18683a) * 31;
        String str = this.f18684b;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "BaseResponse(statusCode=" + this.f18683a + ", statusMsg=" + ((Object) this.f18684b) + ')';
    }

    public a(int i2, String str) {
        this.f18683a = i2;
        this.f18684b = str;
    }

    public /* synthetic */ a(int i2, String str, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, (i3 & 2) != 0 ? "" : str);
    }

    public final int a() {
        return this.f18683a;
    }

    public final String b() {
        return this.f18684b;
    }
}
