package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.CheckForNull;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public class CompactHashSet<E> extends AbstractSet<E> implements Serializable {
    static final double HASH_FLOODING_FPP = 0.001d;
    private static final int MAX_HASH_BUCKET_LENGTH = 9;
    @CheckForNull
    transient Object[] elements;
    @CheckForNull
    private transient int[] entries;
    private transient int metadata;
    private transient int size;
    @CheckForNull
    private transient Object table;

    int adjustAfterRemove(int i2, int i3) {
        return i2 - 1;
    }

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<>();
    }

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(collection.size());
        createWithExpectedSize.addAll(collection);
        return createWithExpectedSize;
    }

    @SafeVarargs
    public static <E> CompactHashSet<E> create(E... eArr) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(eArr.length);
        Collections.addAll(createWithExpectedSize, eArr);
        return createWithExpectedSize;
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int i2) {
        return new CompactHashSet<>(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompactHashSet() {
        init(3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompactHashSet(int i2) {
        init(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(int i2) {
        Preconditions.checkArgument(i2 >= 0, "Expected size must be >= 0");
        this.metadata = Ints.constrainToRange(i2, 1, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean needsAllocArrays() {
        return this.table == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int allocArrays() {
        Preconditions.checkState(needsAllocArrays(), "Arrays already allocated");
        int i2 = this.metadata;
        int tableSize = CompactHashing.tableSize(i2);
        this.table = CompactHashing.createTable(tableSize);
        setHashTableMask(tableSize - 1);
        this.entries = new int[i2];
        this.elements = new Object[i2];
        return i2;
    }

    @CheckForNull
    Set<E> delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Set) {
            return (Set) obj;
        }
        return null;
    }

    private Set<E> createHashFloodingResistantDelegate(int i2) {
        return new LinkedHashSet(i2, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<E> convertToHashFloodingResistantImplementation() {
        Set<E> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(hashTableMask() + 1);
        int firstEntryIndex = firstEntryIndex();
        while (firstEntryIndex >= 0) {
            createHashFloodingResistantDelegate.add(element(firstEntryIndex));
            firstEntryIndex = getSuccessor(firstEntryIndex);
        }
        this.table = createHashFloodingResistantDelegate;
        this.entries = null;
        this.elements = null;
        incrementModCount();
        return createHashFloodingResistantDelegate;
    }

    boolean isUsingHashFloodingResistance() {
        return delegateOrNull() != null;
    }

    private void setHashTableMask(int i2) {
        this.metadata = CompactHashing.maskCombine(this.metadata, 32 - Integer.numberOfLeadingZeros(i2), 31);
    }

    private int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    void incrementModCount() {
        this.metadata += 32;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(@ParametricNullness E e2) {
        if (needsAllocArrays()) {
            allocArrays();
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.add(e2);
        }
        int[] requireEntries = requireEntries();
        Object[] requireElements = requireElements();
        int i2 = this.size;
        int i3 = i2 + 1;
        int smearedHash = Hashing.smearedHash(e2);
        int hashTableMask = hashTableMask();
        int i4 = smearedHash & hashTableMask;
        int tableGet = CompactHashing.tableGet(requireTable(), i4);
        if (tableGet != 0) {
            int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
            int i5 = 0;
            while (true) {
                int i6 = tableGet - 1;
                int i7 = requireEntries[i6];
                if (CompactHashing.getHashPrefix(i7, hashTableMask) == hashPrefix && Objects.equal(e2, requireElements[i6])) {
                    return false;
                }
                int next = CompactHashing.getNext(i7, hashTableMask);
                i5++;
                if (next != 0) {
                    tableGet = next;
                } else if (i5 >= 9) {
                    return convertToHashFloodingResistantImplementation().add(e2);
                } else {
                    if (i3 > hashTableMask) {
                        hashTableMask = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, i2);
                    } else {
                        requireEntries[i6] = CompactHashing.maskCombine(i7, i3, hashTableMask);
                    }
                }
            }
        } else if (i3 > hashTableMask) {
            hashTableMask = resizeTable(hashTableMask, CompactHashing.newCapacity(hashTableMask), smearedHash, i2);
        } else {
            CompactHashing.tableSet(requireTable(), i4, i3);
        }
        resizeMeMaybe(i3);
        insertEntry(i2, e2, smearedHash, hashTableMask);
        this.size = i3;
        incrementModCount();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertEntry(int i2, @ParametricNullness E e2, int i3, int i4) {
        setEntry(i2, CompactHashing.maskCombine(i3, 0, i4));
        setElement(i2, e2);
    }

    private void resizeMeMaybe(int i2) {
        int min;
        int length = requireEntries().length;
        if (i2 <= length || (min = Math.min((int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK, (Math.max(1, length >>> 1) + length) | 1)) == length) {
            return;
        }
        resizeEntries(min);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resizeEntries(int i2) {
        this.entries = Arrays.copyOf(requireEntries(), i2);
        this.elements = Arrays.copyOf(requireElements(), i2);
    }

    private int resizeTable(int i2, int i3, int i4, int i5) {
        Object createTable = CompactHashing.createTable(i3);
        int i6 = i3 - 1;
        if (i5 != 0) {
            CompactHashing.tableSet(createTable, i4 & i6, i5 + 1);
        }
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        for (int i7 = 0; i7 <= i2; i7++) {
            int tableGet = CompactHashing.tableGet(requireTable, i7);
            while (tableGet != 0) {
                int i8 = tableGet - 1;
                int i9 = requireEntries[i8];
                int hashPrefix = CompactHashing.getHashPrefix(i9, i2) | i7;
                int i10 = hashPrefix & i6;
                int tableGet2 = CompactHashing.tableGet(createTable, i10);
                CompactHashing.tableSet(createTable, i10, tableGet);
                requireEntries[i8] = CompactHashing.maskCombine(hashPrefix, tableGet2, i6);
                tableGet = CompactHashing.getNext(i9, i2);
            }
        }
        this.table = createTable;
        setHashTableMask(i6);
        return i6;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return false;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.contains(obj);
        }
        int smearedHash = Hashing.smearedHash(obj);
        int hashTableMask = hashTableMask();
        int tableGet = CompactHashing.tableGet(requireTable(), smearedHash & hashTableMask);
        if (tableGet == 0) {
            return false;
        }
        int hashPrefix = CompactHashing.getHashPrefix(smearedHash, hashTableMask);
        do {
            int i2 = tableGet - 1;
            int entry = entry(i2);
            if (CompactHashing.getHashPrefix(entry, hashTableMask) == hashPrefix && Objects.equal(obj, element(i2))) {
                return true;
            }
            tableGet = CompactHashing.getNext(entry, hashTableMask);
        } while (tableGet != 0);
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(@CheckForNull Object obj) {
        if (needsAllocArrays()) {
            return false;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.remove(obj);
        }
        int hashTableMask = hashTableMask();
        int remove = CompactHashing.remove(obj, null, hashTableMask, requireTable(), requireEntries(), requireElements(), null);
        if (remove == -1) {
            return false;
        }
        moveLastEntry(remove, hashTableMask);
        this.size--;
        incrementModCount();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void moveLastEntry(int i2, int i3) {
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        Object[] requireElements = requireElements();
        int size = size() - 1;
        if (i2 < size) {
            Object obj = requireElements[size];
            requireElements[i2] = obj;
            requireElements[size] = null;
            requireEntries[i2] = requireEntries[size];
            requireEntries[size] = 0;
            int smearedHash = Hashing.smearedHash(obj) & i3;
            int tableGet = CompactHashing.tableGet(requireTable, smearedHash);
            int i4 = size + 1;
            if (tableGet == i4) {
                CompactHashing.tableSet(requireTable, smearedHash, i2 + 1);
                return;
            }
            while (true) {
                int i5 = tableGet - 1;
                int i6 = requireEntries[i5];
                int next = CompactHashing.getNext(i6, i3);
                if (next == i4) {
                    requireEntries[i5] = CompactHashing.maskCombine(i6, i2 + 1, i3);
                    return;
                }
                tableGet = next;
            }
        } else {
            requireElements[i2] = null;
            requireEntries[i2] = 0;
        }
    }

    int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    int getSuccessor(int i2) {
        int i3 = i2 + 1;
        if (i3 < this.size) {
            return i3;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.iterator();
        }
        return new Iterator<E>() { // from class: com.google.common.collect.CompactHashSet.1
            int currentIndex;
            int expectedMetadata;
            int indexToRemove = -1;

            {
                this.expectedMetadata = CompactHashSet.this.metadata;
                this.currentIndex = CompactHashSet.this.firstEntryIndex();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.currentIndex >= 0;
            }

            @Override // java.util.Iterator
            @ParametricNullness
            public E next() {
                checkForConcurrentModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i2 = this.currentIndex;
                this.indexToRemove = i2;
                E e2 = (E) CompactHashSet.this.element(i2);
                this.currentIndex = CompactHashSet.this.getSuccessor(this.currentIndex);
                return e2;
            }

            @Override // java.util.Iterator
            public void remove() {
                checkForConcurrentModification();
                CollectPreconditions.checkRemove(this.indexToRemove >= 0);
                incrementExpectedModCount();
                CompactHashSet compactHashSet = CompactHashSet.this;
                compactHashSet.remove(compactHashSet.element(this.indexToRemove));
                this.currentIndex = CompactHashSet.this.adjustAfterRemove(this.currentIndex, this.indexToRemove);
                this.indexToRemove = -1;
            }

            void incrementExpectedModCount() {
                this.expectedMetadata += 32;
            }

            private void checkForConcurrentModification() {
                if (CompactHashSet.this.metadata != this.expectedMetadata) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        Set<E> delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.size() : this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        if (needsAllocArrays()) {
            return new Object[0];
        }
        Set<E> delegateOrNull = delegateOrNull();
        return delegateOrNull != null ? delegateOrNull.toArray() : Arrays.copyOf(requireElements(), this.size);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        if (needsAllocArrays()) {
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return (T[]) delegateOrNull.toArray(tArr);
        }
        return (T[]) ObjectArrays.toArrayImpl(requireElements(), 0, this.size, tArr);
    }

    public void trimToSize() {
        if (needsAllocArrays()) {
            return;
        }
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            Set<E> createHashFloodingResistantDelegate = createHashFloodingResistantDelegate(size());
            createHashFloodingResistantDelegate.addAll(delegateOrNull);
            this.table = createHashFloodingResistantDelegate;
            return;
        }
        int i2 = this.size;
        if (i2 < requireEntries().length) {
            resizeEntries(i2);
        }
        int tableSize = CompactHashing.tableSize(i2);
        int hashTableMask = hashTableMask();
        if (tableSize < hashTableMask) {
            resizeTable(hashTableMask, tableSize, 0, 0);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        if (needsAllocArrays()) {
            return;
        }
        incrementModCount();
        Set<E> delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            this.metadata = Ints.constrainToRange(size(), 3, LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
            delegateOrNull.clear();
            this.table = null;
            this.size = 0;
            return;
        }
        Arrays.fill(requireElements(), 0, this.size, (Object) null);
        CompactHashing.tableClear(requireTable());
        Arrays.fill(requireEntries(), 0, this.size, 0);
        this.size = 0;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            objectOutputStream.writeObject(it.next());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt < 0) {
            throw new InvalidObjectException(new StringBuilder(25).append("Invalid size: ").append(readInt).toString());
        }
        init(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            add(objectInputStream.readObject());
        }
    }

    private Object requireTable() {
        return java.util.Objects.requireNonNull(this.table);
    }

    private int[] requireEntries() {
        return (int[]) java.util.Objects.requireNonNull(this.entries);
    }

    private Object[] requireElements() {
        return (Object[]) java.util.Objects.requireNonNull(this.elements);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public E element(int i2) {
        return (E) requireElements()[i2];
    }

    private int entry(int i2) {
        return requireEntries()[i2];
    }

    private void setElement(int i2, E e2) {
        requireElements()[i2] = e2;
    }

    private void setEntry(int i2, int i3) {
        requireEntries()[i2] = i3;
    }
}
