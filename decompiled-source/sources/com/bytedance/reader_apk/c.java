package com.bytedance.reader_apk;
/* compiled from: Pair.java */
/* loaded from: classes2.dex */
public final class c<A, B> {

    /* renamed from: a  reason: collision with root package name */
    private final A f12093a;

    /* renamed from: b  reason: collision with root package name */
    private final B f12094b;

    private c(A a2, B b2) {
        this.f12093a = a2;
        this.f12094b = b2;
    }

    public static <A, B> c<A, B> of(A a2, B b2) {
        return new c<>(a2, b2);
    }

    public A getFirst() {
        return this.f12093a;
    }

    public B getSecond() {
        return this.f12094b;
    }

    public int hashCode() {
        A a2 = this.f12093a;
        int hashCode = ((a2 == null ? 0 : a2.hashCode()) + 31) * 31;
        B b2 = this.f12094b;
        return hashCode + (b2 != null ? b2.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            c cVar = (c) obj;
            A a2 = this.f12093a;
            if (a2 == null) {
                if (cVar.f12093a != null) {
                    return false;
                }
            } else if (!a2.equals(cVar.f12093a)) {
                return false;
            }
            B b2 = this.f12094b;
            if (b2 == null) {
                if (cVar.f12094b != null) {
                    return false;
                }
            } else if (!b2.equals(cVar.f12094b)) {
                return false;
            }
            return true;
        }
        return false;
    }
}
