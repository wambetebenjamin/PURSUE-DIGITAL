package com.google.common.collect;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public class CompactLinkedHashSet<E> extends CompactHashSet<E> {
    private static final int ENDPOINT = -2;
    private transient int firstEntry;
    private transient int lastEntry;
    @CheckForNull
    private transient int[] predecessor;
    @CheckForNull
    private transient int[] successor;

    public static <E> CompactLinkedHashSet<E> create() {
        return new CompactLinkedHashSet<>();
    }

    public static <E> CompactLinkedHashSet<E> create(Collection<? extends E> collection) {
        CompactLinkedHashSet<E> createWithExpectedSize = createWithExpectedSize(collection.size());
        createWithExpectedSize.addAll(collection);
        return createWithExpectedSize;
    }

    @SafeVarargs
    public static <E> CompactLinkedHashSet<E> create(E... eArr) {
        CompactLinkedHashSet<E> createWithExpectedSize = createWithExpectedSize(eArr.length);
        Collections.addAll(createWithExpectedSize, eArr);
        return createWithExpectedSize;
    }

    public static <E> CompactLinkedHashSet<E> createWithExpectedSize(int i2) {
        return new CompactLinkedHashSet<>(i2);
    }

    CompactLinkedHashSet() {
    }

    CompactLinkedHashSet(int i2) {
        super(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void init(int i2) {
        super.init(i2);
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public int allocArrays() {
        int allocArrays = super.allocArrays();
        this.predecessor = new int[allocArrays];
        this.successor = new int[allocArrays];
        return allocArrays;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public Set<E> convertToHashFloodingResistantImplementation() {
        Set<E> convertToHashFloodingResistantImplementation = super.convertToHashFloodingResistantImplementation();
        this.predecessor = null;
        this.successor = null;
        return convertToHashFloodingResistantImplementation;
    }

    private int getPredecessor(int i2) {
        return requirePredecessors()[i2] - 1;
    }

    @Override // com.google.common.collect.CompactHashSet
    int getSuccessor(int i2) {
        return requireSuccessors()[i2] - 1;
    }

    private void setSuccessor(int i2, int i3) {
        requireSuccessors()[i2] = i3 + 1;
    }

    private void setPredecessor(int i2, int i3) {
        requirePredecessors()[i2] = i3 + 1;
    }

    private void setSucceeds(int i2, int i3) {
        if (i2 == -2) {
            this.firstEntry = i3;
        } else {
            setSuccessor(i2, i3);
        }
        if (i3 == -2) {
            this.lastEntry = i2;
        } else {
            setPredecessor(i3, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void insertEntry(int i2, @ParametricNullness E e2, int i3, int i4) {
        super.insertEntry(i2, e2, i3, i4);
        setSucceeds(this.lastEntry, i2);
        setSucceeds(i2, -2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void moveLastEntry(int i2, int i3) {
        int size = size() - 1;
        super.moveLastEntry(i2, i3);
        setSucceeds(getPredecessor(i2), getSuccessor(i2));
        if (i2 < size) {
            setSucceeds(getPredecessor(size), i2);
            setSucceeds(i2, getSuccessor(size));
        }
        requirePredecessors()[size] = 0;
        requireSuccessors()[size] = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashSet
    public void resizeEntries(int i2) {
        super.resizeEntries(i2);
        this.predecessor = Arrays.copyOf(requirePredecessors(), i2);
        this.successor = Arrays.copyOf(requireSuccessors(), i2);
    }

    @Override // com.google.common.collect.CompactHashSet
    int firstEntryIndex() {
        return this.firstEntry;
    }

    @Override // com.google.common.collect.CompactHashSet
    int adjustAfterRemove(int i2, int i3) {
        return i2 >= size() ? i3 : i2;
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        return ObjectArrays.toArrayImpl(this);
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) ObjectArrays.toArrayImpl(this, tArr);
    }

    @Override // com.google.common.collect.CompactHashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        this.firstEntry = -2;
        this.lastEntry = -2;
        int[] iArr = this.predecessor;
        if (iArr != null && this.successor != null) {
            Arrays.fill(iArr, 0, size(), 0);
            Arrays.fill(this.successor, 0, size(), 0);
        }
        super.clear();
    }

    private int[] requirePredecessors() {
        return (int[]) Objects.requireNonNull(this.predecessor);
    }

    private int[] requireSuccessors() {
        return (int[]) Objects.requireNonNull(this.successor);
    }
}
