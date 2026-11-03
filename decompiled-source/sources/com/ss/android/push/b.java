package com.ss.android.push;
/* compiled from: ImmutableTriple.java */
/* loaded from: classes9.dex */
public final class b<L, M, R> extends e<L, M, R> {

    /* renamed from: a  reason: collision with root package name */
    private static final long f18460a = 1;
    public final L left;
    public final M middle;
    public final R right;

    public static <L, M, R> b<L, M, R> of(L l, M m, R r) {
        return new b<>(l, m, r);
    }

    public b(L l, M m, R r) {
        this.left = l;
        this.middle = m;
        this.right = r;
    }

    @Override // com.ss.android.push.e
    public L getLeft() {
        return this.left;
    }

    @Override // com.ss.android.push.e
    public M getMiddle() {
        return this.middle;
    }

    @Override // com.ss.android.push.e
    public R getRight() {
        return this.right;
    }
}
