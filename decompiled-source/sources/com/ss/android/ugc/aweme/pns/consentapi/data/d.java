package com.ss.android.ugc.aweme.pns.consentapi.data;

import com.google.gson.annotations.SerializedName;
import com.ss.android.ugc.aweme.pns.consentapi.IPNSConsentService;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ConsentStatusRecord.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u001e\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentapi/data/LightConsentRecord;", "", "id", "", "flow", "platform", "createdAt", "", "status", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "getCreatedAt", "()J", "getFlow", "()Ljava/lang/String;", "getId", "getPlatform", "getStatus", "setStatus", "(Ljava/lang/String;)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "pns_consent_sdk_api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes9.dex */
public final class d {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private final String f18698a;
    @SerializedName("business_flow")

    /* renamed from: b  reason: collision with root package name */
    private final String f18699b;
    @SerializedName("platform")

    /* renamed from: c  reason: collision with root package name */
    private final String f18700c;
    @SerializedName("created_at")

    /* renamed from: d  reason: collision with root package name */
    private final long f18701d;
    @SerializedName("status")

    /* renamed from: e  reason: collision with root package name */
    private String f18702e;

    public d() {
        this(null, null, null, 0L, null, 31, null);
    }

    public static /* synthetic */ d a(d dVar, String str, String str2, String str3, long j2, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = dVar.f18698a;
        }
        if ((i2 & 2) != 0) {
            str2 = dVar.f18699b;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = dVar.f18700c;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            j2 = dVar.f18701d;
        }
        long j3 = j2;
        if ((i2 & 16) != 0) {
            str4 = dVar.f18702e;
        }
        return dVar.a(str, str5, str6, j3, str4);
    }

    public final d a(String id, String flow, String platform, long j2, String status) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(flow, "flow");
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(status, "status");
        return new d(id, flow, platform, j2, status);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof d) {
            d dVar = (d) obj;
            return Intrinsics.areEqual(this.f18698a, dVar.f18698a) && Intrinsics.areEqual(this.f18699b, dVar.f18699b) && Intrinsics.areEqual(this.f18700c, dVar.f18700c) && this.f18701d == dVar.f18701d && Intrinsics.areEqual(this.f18702e, dVar.f18702e);
        }
        return false;
    }

    public final String f() {
        return this.f18698a;
    }

    public final String g() {
        return this.f18699b;
    }

    public final String h() {
        return this.f18700c;
    }

    public int hashCode() {
        return (((((((this.f18698a.hashCode() * 31) + this.f18699b.hashCode()) * 31) + this.f18700c.hashCode()) * 31) + Long.hashCode(this.f18701d)) * 31) + this.f18702e.hashCode();
    }

    public final long i() {
        return this.f18701d;
    }

    public final String j() {
        return this.f18702e;
    }

    public String toString() {
        return "LightConsentRecord(id=" + this.f18698a + ", flow=" + this.f18699b + ", platform=" + this.f18700c + ", createdAt=" + this.f18701d + ", status=" + this.f18702e + ')';
    }

    public d(String id, String flow, String platform, long j2, String status) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(flow, "flow");
        Intrinsics.checkNotNullParameter(platform, "platform");
        Intrinsics.checkNotNullParameter(status, "status");
        this.f18698a = id;
        this.f18699b = flow;
        this.f18700c = platform;
        this.f18701d = j2;
        this.f18702e = status;
    }

    public final String a() {
        return this.f18698a;
    }

    public final String b() {
        return this.f18699b;
    }

    public final String c() {
        return this.f18700c;
    }

    public final long d() {
        return this.f18701d;
    }

    public /* synthetic */ d(String str, String str2, String str3, long j2, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) == 0 ? str2 : "", (i2 & 4) != 0 ? "ANDROID" : str3, (i2 & 8) != 0 ? 0L : j2, (i2 & 16) != 0 ? IPNSConsentService.a.CONSENT_UNDECIDED.toString() : str4);
    }

    public final void a(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f18702e = str;
    }

    public final String e() {
        return this.f18702e;
    }
}
