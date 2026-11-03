package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public abstract class ImmutableSortedSet<E> extends ImmutableSortedSetFauxverideShim<E> implements NavigableSet<E>, SortedIterable<E> {
    final transient Comparator<? super E> comparator;
    @CheckForNull
    @LazyInit
    transient ImmutableSortedSet<E> descendingSet;

    abstract ImmutableSortedSet<E> createDescendingSet();

    @Override // java.util.NavigableSet
    public abstract UnmodifiableIterator<E> descendingIterator();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ImmutableSortedSet<E> headSetImpl(E e2, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int indexOf(@CheckForNull Object obj);

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public abstract UnmodifiableIterator<E> iterator();

    abstract ImmutableSortedSet<E> subSetImpl(E e2, boolean z, E e3, boolean z2);

    abstract ImmutableSortedSet<E> tailSetImpl(E e2, boolean z);

    /* JADX WARN: Multi-variable type inference failed */
    public /* bridge */ /* synthetic */ NavigableSet headSet(Object obj, boolean z) {
        return headSet((ImmutableSortedSet<E>) obj, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* bridge */ /* synthetic */ SortedSet headSet(Object obj) {
        return headSet((ImmutableSortedSet<E>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* bridge */ /* synthetic */ NavigableSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        return subSet((boolean) obj, z, (boolean) obj2, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* bridge */ /* synthetic */ NavigableSet tailSet(Object obj, boolean z) {
        return tailSet((ImmutableSortedSet<E>) obj, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* bridge */ /* synthetic */ SortedSet tailSet(Object obj) {
        return tailSet((ImmutableSortedSet<E>) obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> RegularImmutableSortedSet<E> emptySet(Comparator<? super E> comparator) {
        if (Ordering.natural().equals(comparator)) {
            return (RegularImmutableSortedSet<E>) RegularImmutableSortedSet.NATURAL_EMPTY_SET;
        }
        return new RegularImmutableSortedSet<>(ImmutableList.of(), comparator);
    }

    public static <E> ImmutableSortedSet<E> of() {
        return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable comparable) {
        return new RegularImmutableSortedSet(ImmutableList.of(comparable), Ordering.natural());
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable comparable, Comparable comparable2) {
        return construct(Ordering.natural(), 2, comparable, comparable2);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable comparable, Comparable comparable2, Comparable comparable3) {
        return construct(Ordering.natural(), 3, comparable, comparable2, comparable3);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable comparable, Comparable comparable2, Comparable comparable3, Comparable comparable4) {
        return construct(Ordering.natural(), 4, comparable, comparable2, comparable3, comparable4);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;TE;TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable comparable, Comparable comparable2, Comparable comparable3, Comparable comparable4, Comparable comparable5) {
        return construct(Ordering.natural(), 5, comparable, comparable2, comparable3, comparable4, comparable5);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>(TE;TE;TE;TE;TE;TE;[TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet of(Comparable comparable, Comparable comparable2, Comparable comparable3, Comparable comparable4, Comparable comparable5, Comparable comparable6, Comparable... comparableArr) {
        int length = comparableArr.length + 6;
        Comparable[] comparableArr2 = new Comparable[length];
        comparableArr2[0] = comparable;
        comparableArr2[1] = comparable2;
        comparableArr2[2] = comparable3;
        comparableArr2[3] = comparable4;
        comparableArr2[4] = comparable5;
        comparableArr2[5] = comparable6;
        System.arraycopy(comparableArr, 0, comparableArr2, 6, comparableArr.length);
        return construct(Ordering.natural(), length, comparableArr2);
    }

    /* JADX WARN: Incorrect types in method signature: <E::Ljava/lang/Comparable<-TE;>;>([TE;)Lcom/google/common/collect/ImmutableSortedSet<TE;>; */
    public static ImmutableSortedSet copyOf(Comparable[] comparableArr) {
        return construct(Ordering.natural(), comparableArr.length, (Comparable[]) comparableArr.clone());
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterable<? extends E> iterable) {
        return copyOf(Ordering.natural(), iterable);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Collection<? extends E> collection) {
        return copyOf((Comparator) Ordering.natural(), (Collection) collection);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Iterator<? extends E> it) {
        return copyOf(Ordering.natural(), it);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Iterator<? extends E> it) {
        return new Builder(comparator).addAll((Iterator) it).build();
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Iterable<? extends E> iterable) {
        Preconditions.checkNotNull(comparator);
        if (SortedIterables.hasSameComparator(comparator, iterable) && (iterable instanceof ImmutableSortedSet)) {
            ImmutableSortedSet<E> immutableSortedSet = (ImmutableSortedSet) iterable;
            if (!immutableSortedSet.isPartialView()) {
                return immutableSortedSet;
            }
        }
        Object[] array = Iterables.toArray(iterable);
        return construct(comparator, array.length, array);
    }

    public static <E> ImmutableSortedSet<E> copyOf(Comparator<? super E> comparator, Collection<? extends E> collection) {
        return copyOf((Comparator) comparator, (Iterable) collection);
    }

    public static <E> ImmutableSortedSet<E> copyOfSorted(SortedSet<E> sortedSet) {
        Comparator comparator = SortedIterables.comparator(sortedSet);
        ImmutableList copyOf = ImmutableList.copyOf((Collection) sortedSet);
        if (copyOf.isEmpty()) {
            return emptySet(comparator);
        }
        return new RegularImmutableSortedSet(copyOf, comparator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <E> ImmutableSortedSet<E> construct(Comparator<? super E> comparator, int i2, E... eArr) {
        if (i2 == 0) {
            return emptySet(comparator);
        }
        ObjectArrays.checkElementsNotNull(eArr, i2);
        Arrays.sort(eArr, 0, i2, comparator);
        int i3 = 1;
        for (int i4 = 1; i4 < i2; i4++) {
            Object obj = (Object) eArr[i4];
            if (comparator.compare(obj, (Object) eArr[i3 - 1]) != 0) {
                eArr[i3] = obj;
                i3++;
            }
        }
        Arrays.fill(eArr, i3, i2, (Object) null);
        if (i3 < eArr.length / 2) {
            eArr = (E[]) Arrays.copyOf(eArr, i3);
        }
        return new RegularImmutableSortedSet(ImmutableList.asImmutableList(eArr, i3), comparator);
    }

    public static <E> Builder<E> orderedBy(Comparator<E> comparator) {
        return new Builder<>(comparator);
    }

    public static <E extends Comparable<?>> Builder<E> reverseOrder() {
        return new Builder<>(Collections.reverseOrder());
    }

    public static <E extends Comparable<?>> Builder<E> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    /* loaded from: classes9.dex */
    public static final class Builder<E> extends ImmutableSet.Builder<E> {
        private final Comparator<? super E> comparator;

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableCollection.ArrayBasedBuilder add(Object obj) {
            return add((Builder<E>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableCollection.Builder add(Object obj) {
            return add((Builder<E>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public /* bridge */ /* synthetic */ ImmutableSet.Builder add(Object obj) {
            return add((Builder<E>) obj);
        }

        public Builder(Comparator<? super E> comparator) {
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E e2) {
            super.add((Builder<E>) e2);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> add(E... eArr) {
            super.add((Object[]) eArr);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterable<? extends E> iterable) {
            super.addAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        public Builder<E> addAll(Iterator<? extends E> it) {
            super.addAll((Iterator) it);
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.ImmutableSet.Builder
        public Builder<E> combine(ImmutableSet.Builder<E> builder) {
            super.combine((ImmutableSet.Builder) builder);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.Builder
        public ImmutableSortedSet<E> build() {
            ImmutableSortedSet<E> construct = ImmutableSortedSet.construct(this.comparator, this.size, this.contents);
            this.size = construct.size();
            this.forceCopy = true;
            return construct;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int unsafeCompare(Object obj, @CheckForNull Object obj2) {
        return unsafeCompare(this.comparator, obj, obj2);
    }

    static int unsafeCompare(Comparator<?> comparator, Object obj, @CheckForNull Object obj2) {
        return comparator.compare(obj, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableSortedSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    @Override // java.util.SortedSet, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    public ImmutableSortedSet<E> headSet(E e2) {
        return headSet((ImmutableSortedSet<E>) e2, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ImmutableSortedSet<E> headSet(E e2, boolean z) {
        return headSetImpl(Preconditions.checkNotNull(e2), z);
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public ImmutableSortedSet<E> subSet(E e2, E e3) {
        return subSet((boolean) e2, true, (boolean) e3, false);
    }

    public ImmutableSortedSet<E> subSet(E e2, boolean z, E e3, boolean z2) {
        Preconditions.checkNotNull(e2);
        Preconditions.checkNotNull(e3);
        Preconditions.checkArgument(this.comparator.compare(e2, e3) <= 0);
        return subSetImpl(e2, z, e3, z2);
    }

    public ImmutableSortedSet<E> tailSet(E e2) {
        return tailSet((ImmutableSortedSet<E>) e2, true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ImmutableSortedSet<E> tailSet(E e2, boolean z) {
        return tailSetImpl(Preconditions.checkNotNull(e2), z);
    }

    @CheckForNull
    public E lower(E e2) {
        return (E) Iterators.getNext(headSet((ImmutableSortedSet<E>) e2, false).descendingIterator(), null);
    }

    @CheckForNull
    public E floor(E e2) {
        return (E) Iterators.getNext(headSet((ImmutableSortedSet<E>) e2, true).descendingIterator(), null);
    }

    @CheckForNull
    public E ceiling(E e2) {
        return (E) Iterables.getFirst(tailSet((ImmutableSortedSet<E>) e2, true), null);
    }

    @CheckForNull
    public E higher(E e2) {
        return (E) Iterables.getFirst(tailSet((ImmutableSortedSet<E>) e2, false), null);
    }

    public E first() {
        return iterator().next();
    }

    public E last() {
        return descendingIterator().next();
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    @Deprecated
    public final E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    @CheckForNull
    @Deprecated
    public final E pollLast() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    public ImmutableSortedSet<E> descendingSet() {
        ImmutableSortedSet<E> immutableSortedSet = this.descendingSet;
        if (immutableSortedSet == null) {
            ImmutableSortedSet<E> createDescendingSet = createDescendingSet();
            this.descendingSet = createDescendingSet;
            createDescendingSet.descendingSet = this;
            return createDescendingSet;
        }
        return immutableSortedSet;
    }

    /* loaded from: classes9.dex */
    private static class SerializedForm<E> implements Serializable {
        private static final long serialVersionUID = 0;
        final Comparator<? super E> comparator;
        final Object[] elements;

        public SerializedForm(Comparator<? super E> comparator, Object[] objArr) {
            this.comparator = comparator;
            this.elements = objArr;
        }

        /* JADX WARN: Multi-variable type inference failed */
        Object readResolve() {
            return new Builder(this.comparator).add(this.elements).build();
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    Object writeReplace() {
        return new SerializedForm(this.comparator, toArray());
    }
}
