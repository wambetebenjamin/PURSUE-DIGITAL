package gsdk.impl.geas.DEFAULT;

import com.google.gson.annotations.SerializedName;
import gsdk.impl.account.toutiao.da;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: StrategyResponse.kt */
/* loaded from: classes10.dex */
public final class f {
    @SerializedName("region_id")

    /* renamed from: a  reason: collision with root package name */
    private int f20355a;
    @SerializedName("ip")

    /* renamed from: b  reason: collision with root package name */
    private String f20356b;
    @SerializedName(da.bc)

    /* renamed from: c  reason: collision with root package name */
    private int f20357c;

    public f() {
        this(0, null, 0, 7, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            return this.f20355a == fVar.f20355a && Intrinsics.areEqual(this.f20356b, fVar.f20356b) && this.f20357c == fVar.f20357c;
        }
        return false;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.f20355a) * 31) + this.f20356b.hashCode()) * 31) + Integer.hashCode(this.f20357c);
    }

    public String toString() {
        return "SpeedSvr(regionId=" + this.f20355a + ", ip=" + this.f20356b + ", port=" + this.f20357c + ')';
    }

    public f(int i2, String ip, int i3) {
        Intrinsics.checkNotNullParameter(ip, "ip");
        this.f20355a = i2;
        this.f20356b = ip;
        this.f20357c = i3;
    }

    public /* synthetic */ f(int i2, String str, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i2, (i4 & 2) != 0 ? "" : str, (i4 & 4) != 0 ? 0 : i3);
    }

    public final int a() {
        return this.f20355a;
    }

    public final String b() {
        return this.f20356b;
    }

    public final int c() {
        return this.f20357c;
    }
}
