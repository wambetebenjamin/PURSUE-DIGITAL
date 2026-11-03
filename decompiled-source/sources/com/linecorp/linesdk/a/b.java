package com.linecorp.linesdk.a;

import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final long f17265a;

    /* renamed from: b  reason: collision with root package name */
    public final List<String> f17266b;

    /* renamed from: c  reason: collision with root package name */
    private final String f17267c;

    public b(String str, long j2, List<String> list) {
        this.f17267c = str;
        this.f17265a = j2;
        this.f17266b = Collections.unmodifiableList(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (this.f17267c.equals(bVar.f17267c) && this.f17265a == bVar.f17265a) {
            return this.f17266b.equals(bVar.f17266b);
        }
        return false;
    }

    public final int hashCode() {
        long j2 = this.f17265a;
        return (((Integer.valueOf(this.f17267c).intValue() * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.f17266b.hashCode();
    }

    public final String toString() {
        return "AccessTokenVerificationResult{expiresInMillis=" + this.f17265a + ", channelId=" + this.f17267c + ", permissions=" + this.f17266b + '}';
    }
}
