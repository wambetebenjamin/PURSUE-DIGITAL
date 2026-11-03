package gsdk.impl.geas.DEFAULT;
/* compiled from: Udp.kt */
/* loaded from: classes10.dex */
public final class c {

    /* renamed from: a  reason: collision with root package name */
    private long f20330a;

    /* renamed from: b  reason: collision with root package name */
    private long f20331b;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            return this.f20330a == cVar.f20330a && this.f20331b == cVar.f20331b;
        }
        return false;
    }

    public int hashCode() {
        return (Long.hashCode(this.f20330a) * 31) + Long.hashCode(this.f20331b);
    }

    public String toString() {
        return "TimeModel(start=" + this.f20330a + ", end=" + this.f20331b + ')';
    }

    public c(long j2, long j3) {
        this.f20330a = j2;
        this.f20331b = j3;
    }

    public final long a() {
        return this.f20330a;
    }

    public final void a(long j2) {
        this.f20331b = j2;
    }

    public final long b() {
        return this.f20331b;
    }
}
