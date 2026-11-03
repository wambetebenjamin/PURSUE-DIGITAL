package com.linecorp.linesdk.a;
/* loaded from: classes9.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    public final String f17271a;

    /* renamed from: b  reason: collision with root package name */
    public final long f17272b;

    /* renamed from: c  reason: collision with root package name */
    public final long f17273c;

    /* renamed from: d  reason: collision with root package name */
    public final String f17274d;

    public d(String str, long j2, long j3, String str2) {
        this.f17271a = str;
        this.f17272b = j2;
        this.f17273c = j3;
        this.f17274d = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.f17272b == dVar.f17272b && this.f17273c == dVar.f17273c && this.f17271a.equals(dVar.f17271a)) {
            return this.f17274d.equals(dVar.f17274d);
        }
        return false;
    }

    public final int hashCode() {
        long j2 = this.f17272b;
        long j3 = this.f17273c;
        return (((((this.f17271a.hashCode() * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + this.f17274d.hashCode();
    }

    public final String toString() {
        return "InternalAccessToken{accessToken='#####', expiresInMillis=" + this.f17272b + ", issuedClientTimeMillis=" + this.f17273c + ", refreshToken='" + this.f17274d + "'}";
    }
}
