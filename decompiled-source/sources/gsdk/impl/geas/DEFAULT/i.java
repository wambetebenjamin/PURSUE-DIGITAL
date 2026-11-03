package gsdk.impl.geas.DEFAULT;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: StrategyResponse.kt */
/* loaded from: classes10.dex */
public final class i {
    @SerializedName("status")

    /* renamed from: a  reason: collision with root package name */
    private int f20370a;
    @SerializedName("msg")

    /* renamed from: b  reason: collision with root package name */
    private String f20371b;
    @SerializedName("data")

    /* renamed from: c  reason: collision with root package name */
    private h f20372c;

    public i() {
        this(0, null, null, 7, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof i) {
            i iVar = (i) obj;
            return this.f20370a == iVar.f20370a && Intrinsics.areEqual(this.f20371b, iVar.f20371b) && Intrinsics.areEqual(this.f20372c, iVar.f20372c);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.f20370a) * 31;
        String str = this.f20371b;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        h hVar = this.f20372c;
        return hashCode2 + (hVar != null ? hVar.hashCode() : 0);
    }

    public String toString() {
        return "StrategyResponse(code=" + this.f20370a + ", message=" + this.f20371b + ", data=" + this.f20372c + ')';
    }

    public i(int i2, String str, h hVar) {
        this.f20370a = i2;
        this.f20371b = str;
        this.f20372c = hVar;
    }

    public /* synthetic */ i(int i2, String str, h hVar, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, (i3 & 2) != 0 ? "" : str, (i3 & 4) != 0 ? null : hVar);
    }

    public final int a() {
        return this.f20370a;
    }

    public final String b() {
        return this.f20371b;
    }

    public final h c() {
        return this.f20372c;
    }
}
