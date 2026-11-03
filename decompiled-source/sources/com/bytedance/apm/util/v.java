package com.bytedance.apm.util;
/* compiled from: Pair.java */
/* loaded from: classes.dex */
public class v<F, S> {
    public F first;
    public S second;

    public v(F f2, S s) {
        this.first = f2;
        this.second = s;
    }

    public boolean equals(Object obj) {
        if (obj instanceof v) {
            v vVar = (v) obj;
            return u.equals(vVar.first, this.first) && u.equals(vVar.second, this.second);
        }
        return false;
    }

    public int hashCode() {
        F f2 = this.first;
        int hashCode = f2 == null ? 0 : f2.hashCode();
        S s = this.second;
        return hashCode ^ (s != null ? s.hashCode() : 0);
    }

    public String toString() {
        return "Pair{" + String.valueOf(this.first) + " " + this.second + "}";
    }

    public static <A, B> v<A, B> create(A a2, B b2) {
        return new v<>(a2, b2);
    }
}
