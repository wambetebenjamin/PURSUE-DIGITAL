package com.ss.android.push;

import java.io.Serializable;
/* compiled from: Triple.java */
/* loaded from: classes9.dex */
public abstract class e<L, M, R> implements Serializable, Comparable<e<L, M, R>> {

    /* renamed from: a  reason: collision with root package name */
    private static final long f18465a = 1;

    public abstract L getLeft();

    public abstract M getMiddle();

    public abstract R getRight();

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((e) ((e) obj));
    }

    public static <L, M, R> e<L, M, R> of(L l, M m, R r) {
        return new b(l, m, r);
    }

    public int compareTo(e<L, M, R> eVar) {
        int compareTo = ((Comparable) getLeft()).compareTo(eVar.getLeft());
        if (compareTo != 0) {
            return compareTo;
        }
        int compareTo2 = ((Comparable) getMiddle()).compareTo(eVar.getMiddle());
        return compareTo2 != 0 ? compareTo2 : ((Comparable) getRight()).compareTo(eVar.getRight());
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            return a(getLeft(), eVar.getLeft()) && a(getMiddle(), eVar.getMiddle()) && a(getRight(), eVar.getRight());
        }
        return false;
    }

    private boolean a(Object obj, Object obj2) {
        return (obj == null || obj2 == null || (obj != obj2 && !obj.equals(obj2))) ? false : true;
    }

    public int hashCode() {
        return ((getLeft() == null ? 0 : getLeft().hashCode()) ^ (getMiddle() == null ? 0 : getMiddle().hashCode())) ^ (getRight() != null ? getRight().hashCode() : 0);
    }

    public String toString() {
        return new StringBuilder().append('(').append(getLeft()).append(',').append(getMiddle()).append(',').append(getRight()).append(')').toString();
    }

    public String toString(String str) {
        return String.format(str, getLeft(), getMiddle(), getRight());
    }
}
