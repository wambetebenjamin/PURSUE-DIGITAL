package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public abstract class ForwardingList<E> extends ForwardingCollection<E> implements List<E> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract List<E> delegate();

    public void add(int i2, @ParametricNullness E e2) {
        delegate().add(i2, e2);
    }

    public boolean addAll(int i2, Collection<? extends E> collection) {
        return delegate().addAll(i2, collection);
    }

    @Override // java.util.List
    @ParametricNullness
    public E get(int i2) {
        return delegate().get(i2);
    }

    @Override // java.util.List
    public int indexOf(@CheckForNull Object obj) {
        return delegate().indexOf(obj);
    }

    @Override // java.util.List
    public int lastIndexOf(@CheckForNull Object obj) {
        return delegate().lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return delegate().listIterator();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i2) {
        return delegate().listIterator(i2);
    }

    @Override // java.util.List
    @ParametricNullness
    public E remove(int i2) {
        return delegate().remove(i2);
    }

    @Override // java.util.List
    @ParametricNullness
    public E set(int i2, @ParametricNullness E e2) {
        return delegate().set(i2, e2);
    }

    @Override // java.util.List
    public List<E> subList(int i2, int i3) {
        return delegate().subList(i2, i3);
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(@CheckForNull Object obj) {
        return obj == this || delegate().equals(obj);
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return delegate().hashCode();
    }

    protected boolean standardAdd(@ParametricNullness E e2) {
        add(size(), e2);
        return true;
    }

    protected boolean standardAddAll(int i2, Iterable<? extends E> iterable) {
        return Lists.addAllImpl(this, i2, iterable);
    }

    protected int standardIndexOf(@CheckForNull Object obj) {
        return Lists.indexOfImpl(this, obj);
    }

    protected int standardLastIndexOf(@CheckForNull Object obj) {
        return Lists.lastIndexOfImpl(this, obj);
    }

    protected Iterator<E> standardIterator() {
        return listIterator();
    }

    protected ListIterator<E> standardListIterator() {
        return listIterator(0);
    }

    protected ListIterator<E> standardListIterator(int i2) {
        return Lists.listIteratorImpl(this, i2);
    }

    protected List<E> standardSubList(int i2, int i3) {
        return Lists.subListImpl(this, i2, i3);
    }

    protected boolean standardEquals(@CheckForNull Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    protected int standardHashCode() {
        return Lists.hashCodeImpl(this);
    }
}
