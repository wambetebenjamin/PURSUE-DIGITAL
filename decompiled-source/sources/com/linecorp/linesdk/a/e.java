package com.linecorp.linesdk.a;

import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
public final class e {

    /* renamed from: a  reason: collision with root package name */
    public final d f17275a;

    /* renamed from: b  reason: collision with root package name */
    public final List<String> f17276b;

    public e(d dVar, List<String> list) {
        this.f17275a = dVar;
        this.f17276b = Collections.unmodifiableList(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        if (this.f17275a.equals(eVar.f17275a)) {
            return this.f17276b.equals(eVar.f17276b);
        }
        return false;
    }

    public final int hashCode() {
        return (this.f17275a.hashCode() * 31) + this.f17276b.hashCode();
    }

    public final String toString() {
        return "IssueAccessTokenResult{accessToken=" + ((Object) "#####") + ", permissions=" + this.f17276b + '}';
    }
}
