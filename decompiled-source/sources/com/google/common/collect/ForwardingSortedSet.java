package com.google.common.collect;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public abstract class ForwardingSortedSet<E> extends ForwardingSet<E> implements SortedSet<E> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract SortedSet<E> delegate();

    @Override // java.util.SortedSet
    @CheckForNull
    public Comparator<? super E> comparator() {
        return delegate().comparator();
    }

    @Override // java.util.SortedSet
    @ParametricNullness
    public E first() {
        return delegate().first();
    }

    @Override // java.util.SortedSet
    public SortedSet<E> headSet(@ParametricNullness E e2) {
        return delegate().headSet(e2);
    }

    @Override // java.util.SortedSet
    @ParametricNullness
    public E last() {
        return delegate().last();
    }

    @Override // java.util.SortedSet
    public SortedSet<E> subSet(@ParametricNullness E e2, @ParametricNullness E e3) {
        return delegate().subSet(e2, e3);
    }

    @Override // java.util.SortedSet
    public SortedSet<E> tailSet(@ParametricNullness E e2) {
        return delegate().tailSet(e2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingCollection
    protected boolean standardContains(@CheckForNull Object obj) {
        try {
            return ForwardingSortedMap.unsafeCompare(comparator(), tailSet(obj).first(), obj) == 0;
        } catch (ClassCastException | NullPointerException | NoSuchElementException unused) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingCollection
    protected boolean standardRemove(@CheckForNull Object obj) {
        try {
            Iterator<E> it = tailSet(obj).iterator();
            if (it.hasNext()) {
                if (ForwardingSortedMap.unsafeCompare(comparator(), it.next(), obj) == 0) {
                    it.remove();
                    return true;
                }
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    protected SortedSet<E> standardSubSet(@ParametricNullness E e2, @ParametricNullness E e3) {
        return tailSet(e2).headSet(e3);
    }
}
