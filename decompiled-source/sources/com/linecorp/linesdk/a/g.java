package com.linecorp.linesdk.a;
/* loaded from: classes9.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    public final String f17279a;

    /* renamed from: b  reason: collision with root package name */
    public final long f17280b;

    /* renamed from: c  reason: collision with root package name */
    public final String f17281c;

    public g(String str, long j2, String str2) {
        this.f17279a = str;
        this.f17280b = j2;
        this.f17281c = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            g gVar = (g) obj;
            if (this.f17280b != gVar.f17280b || !this.f17279a.equals(gVar.f17279a)) {
                return false;
            }
            String str = this.f17281c;
            String str2 = gVar.f17281c;
            if (str != null) {
                return str.equals(str2);
            }
            if (str2 == null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j2 = this.f17280b;
        int hashCode = ((this.f17279a.hashCode() * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        String str = this.f17281c;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public final String toString() {
        return "RefreshTokenResult{accessToken='#####', expiresInMillis=" + this.f17280b + ", refreshToken='#####'}";
    }
}
