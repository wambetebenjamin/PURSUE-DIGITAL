package com.google.common.collect;

import java.util.ListIterator;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public abstract class UnmodifiableListIterator<E> extends UnmodifiableIterator<E> implements ListIterator<E> {
    @Override // java.util.ListIterator
    @Deprecated
    public final void add(@ParametricNullness E e2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    @Deprecated
    public final void set(@ParametricNullness E e2) {
        throw new UnsupportedOperationException();
    }
}
