package com.ss.android.ugc.aweme.pns.consentapi.data;

import com.google.gson.annotations.SerializedName;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ConsentStatusRecord.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/ss/android/ugc/aweme/pns/consentapi/data/FetchConsentResponse;", "Ljava/io/Serializable;", "statusCode", "", "statusMsg", "", "entities", "", "Lcom/ss/android/ugc/aweme/pns/consentapi/data/ConsentStatusRecord;", "(ILjava/lang/String;Ljava/util/List;)V", "getEntities", "()Ljava/util/List;", "getStatusCode", "()I", "getStatusMsg", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "pns_consent_sdk_api_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes9.dex */
public final class c implements Serializable {
    @SerializedName(MonitorConstants.STATUS_CODE)

    /* renamed from: a  reason: collision with root package name */
    private final int f18695a;
    @SerializedName("status_msg")

    /* renamed from: b  reason: collision with root package name */
    private final String f18696b;
    @SerializedName("entities")

    /* renamed from: c  reason: collision with root package name */
    private final List<b> f18697c;

    public c() {
        this(0, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ c a(c cVar, int i2, String str, List list, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = cVar.f18695a;
        }
        if ((i3 & 2) != 0) {
            str = cVar.f18696b;
        }
        if ((i3 & 4) != 0) {
            list = cVar.f18697c;
        }
        return cVar.a(i2, str, list);
    }

    public final c a(int i2, String str, List<b> list) {
        return new c(i2, str, list);
    }

    public final int d() {
        return this.f18695a;
    }

    public final String e() {
        return this.f18696b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            return this.f18695a == cVar.f18695a && Intrinsics.areEqual(this.f18696b, cVar.f18696b) && Intrinsics.areEqual(this.f18697c, cVar.f18697c);
        }
        return false;
    }

    public final List<b> f() {
        return this.f18697c;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.f18695a) * 31;
        String str = this.f18696b;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        List<b> list = this.f18697c;
        return hashCode2 + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "FetchConsentResponse(statusCode=" + this.f18695a + ", statusMsg=" + ((Object) this.f18696b) + ", entities=" + this.f18697c + ')';
    }

    public c(int i2, String str, List<b> list) {
        this.f18695a = i2;
        this.f18696b = str;
        this.f18697c = list;
    }

    public /* synthetic */ c(int i2, String str, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, (i3 & 2) != 0 ? "" : str, (i3 & 4) != 0 ? null : list);
    }

    public final int a() {
        return this.f18695a;
    }

    public final String b() {
        return this.f18696b;
    }

    public final List<b> c() {
        return this.f18697c;
    }
}
