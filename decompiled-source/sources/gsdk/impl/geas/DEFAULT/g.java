package gsdk.impl.geas.DEFAULT;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: StrategyResponse.kt */
/* loaded from: classes10.dex */
public final class g {
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private int f20358a;
    @SerializedName("name")

    /* renamed from: b  reason: collision with root package name */
    private String f20359b;
    @SerializedName("begin_timestamp")

    /* renamed from: c  reason: collision with root package name */
    private String f20360c;
    @SerializedName("end_timestamp")

    /* renamed from: d  reason: collision with root package name */
    private String f20361d;
    @SerializedName("cmd")

    /* renamed from: e  reason: collision with root package name */
    private String f20362e;
    @SerializedName("send_interval")

    /* renamed from: f  reason: collision with root package name */
    private int f20363f;
    @SerializedName("send_count")

    /* renamed from: g  reason: collision with root package name */
    private int f20364g;
    @SerializedName("test_interval")

    /* renamed from: h  reason: collision with root package name */
    private long f20365h;
    @SerializedName("wait_time")

    /* renamed from: i  reason: collision with root package name */
    private long f20366i;
    @SerializedName("random_delay_time")

    /* renamed from: j  reason: collision with root package name */
    private int f20367j;
    @SerializedName("system_info_interval")
    private int k;
    @SerializedName("fps_interval")
    private int l;
    @SerializedName("send_packet_size")
    private int m;

    public g() {
        this(0, null, null, null, null, 0, 0, 0L, 0L, 0, 0, 0, 0, 8191, null);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            return this.f20358a == gVar.f20358a && Intrinsics.areEqual(this.f20359b, gVar.f20359b) && Intrinsics.areEqual(this.f20360c, gVar.f20360c) && Intrinsics.areEqual(this.f20361d, gVar.f20361d) && Intrinsics.areEqual(this.f20362e, gVar.f20362e) && this.f20363f == gVar.f20363f && this.f20364g == gVar.f20364g && this.f20365h == gVar.f20365h && this.f20366i == gVar.f20366i && this.f20367j == gVar.f20367j && this.k == gVar.k && this.l == gVar.l && this.m == gVar.m;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.f20358a) * 31;
        String str = this.f20359b;
        int hashCode2 = (((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.f20360c.hashCode()) * 31) + this.f20361d.hashCode()) * 31;
        String str2 = this.f20362e;
        return ((((((((((((((((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.f20363f)) * 31) + Integer.hashCode(this.f20364g)) * 31) + Long.hashCode(this.f20365h)) * 31) + Long.hashCode(this.f20366i)) * 31) + Integer.hashCode(this.f20367j)) * 31) + Integer.hashCode(this.k)) * 31) + Integer.hashCode(this.l)) * 31) + Integer.hashCode(this.m);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Strategy(id=").append(this.f20358a).append(", name=").append(this.f20359b).append(", beginTimestamp=").append(this.f20360c).append(", endTimestamp=").append(this.f20361d).append(", cmd=").append(this.f20362e).append(", sendInterval=").append(this.f20363f).append(", sendCount=").append(this.f20364g).append(", testInterval=").append(this.f20365h).append(", waitTime=").append(this.f20366i).append(", randomDelayTime=").append(this.f20367j).append(", systemInfoInterval=").append(this.k).append(", fpsInterval=");
        sb.append(this.l).append(", sendPacketSize=").append(this.m).append(')');
        return sb.toString();
    }

    public g(int i2, String str, String beginTimestamp, String endTimestamp, String str2, int i3, int i4, long j2, long j3, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(beginTimestamp, "beginTimestamp");
        Intrinsics.checkNotNullParameter(endTimestamp, "endTimestamp");
        this.f20358a = i2;
        this.f20359b = str;
        this.f20360c = beginTimestamp;
        this.f20361d = endTimestamp;
        this.f20362e = str2;
        this.f20363f = i3;
        this.f20364g = i4;
        this.f20365h = j2;
        this.f20366i = j3;
        this.f20367j = i5;
        this.k = i6;
        this.l = i7;
        this.m = i8;
    }

    public /* synthetic */ g(int i2, String str, String str2, String str3, String str4, int i3, int i4, long j2, long j3, int i5, int i6, int i7, int i8, int i9, DefaultConstructorMarker defaultConstructorMarker) {
        this((i9 & 1) != 0 ? 0 : i2, (i9 & 2) != 0 ? "" : str, (i9 & 4) != 0 ? "0" : str2, (i9 & 8) == 0 ? str3 : "0", (i9 & 16) == 0 ? str4 : "", (i9 & 32) != 0 ? 0 : i3, (i9 & 64) != 0 ? 0 : i4, (i9 & 128) != 0 ? 0L : j2, (i9 & 256) == 0 ? j3 : 0L, (i9 & 512) != 0 ? 0 : i5, (i9 & 1024) != 0 ? 5000 : i6, (i9 & 2048) != 0 ? 1000 : i7, (i9 & 4096) == 0 ? i8 : 0);
    }

    public final int a() {
        return this.f20358a;
    }

    public final String b() {
        return this.f20360c;
    }

    public final String c() {
        return this.f20361d;
    }

    public final String d() {
        return this.f20362e;
    }

    public final int e() {
        return this.f20363f;
    }

    public final int f() {
        return this.f20364g;
    }

    public final long g() {
        return this.f20365h;
    }

    public final long h() {
        return this.f20366i;
    }

    public final int i() {
        return this.k;
    }

    public final int j() {
        return this.l;
    }

    public final int k() {
        return this.m;
    }
}
