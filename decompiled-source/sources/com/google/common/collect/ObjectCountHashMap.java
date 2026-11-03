package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.Arrays;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public class ObjectCountHashMap<K> {
    static final float DEFAULT_LOAD_FACTOR = 1.0f;
    static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;
    transient long[] entries;
    transient Object[] keys;
    private transient float loadFactor;
    transient int modCount;
    transient int size;
    private transient int[] table;
    private transient int threshold;
    transient int[] values;

    private static int getHash(long j2) {
        return (int) (j2 >>> 32);
    }

    private static int getNext(long j2) {
        return (int) j2;
    }

    private static long swapNext(long j2, int i2) {
        return (j2 & HASH_MASK) | (i2 & NEXT_MASK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int nextIndexAfterRemove(int i2, int i3) {
        return i2 - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K> ObjectCountHashMap<K> create() {
        return new ObjectCountHashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K> ObjectCountHashMap<K> createWithExpectedSize(int i2) {
        return new ObjectCountHashMap<>(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap() {
        init(3, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(ObjectCountHashMap<? extends K> objectCountHashMap) {
        init(objectCountHashMap.size(), 1.0f);
        int firstIndex = objectCountHashMap.firstIndex();
        while (firstIndex != -1) {
            put(objectCountHashMap.getKey(firstIndex), objectCountHashMap.getValue(firstIndex));
            firstIndex = objectCountHashMap.nextIndex(firstIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(int i2) {
        this(i2, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(int i2, float f2) {
        init(i2, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(int i2, float f2) {
        Preconditions.checkArgument(i2 >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(f2 > 0.0f, "Illegal load factor");
        int closedTableSize = Hashing.closedTableSize(i2, f2);
        this.table = newTable(closedTableSize);
        this.loadFactor = f2;
        this.keys = new Object[i2];
        this.values = new int[i2];
        this.entries = newEntries(i2);
        this.threshold = Math.max(1, (int) (closedTableSize * f2));
    }

    private static int[] newTable(int i2) {
        int[] iArr = new int[i2];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private static long[] newEntries(int i2) {
        long[] jArr = new long[i2];
        Arrays.fill(jArr, -1L);
        return jArr;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int firstIndex() {
        return this.size == 0 ? -1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int nextIndex(int i2) {
        int i3 = i2 + 1;
        if (i3 < this.size) {
            return i3;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int size() {
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @ParametricNullness
    public K getKey(int i2) {
        Preconditions.checkElementIndex(i2, this.size);
        return (K) this.keys[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getValue(int i2) {
        Preconditions.checkElementIndex(i2, this.size);
        return this.values[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValue(int i2, int i3) {
        Preconditions.checkElementIndex(i2, this.size);
        this.values[i2] = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Multiset.Entry<K> getEntry(int i2) {
        Preconditions.checkElementIndex(i2, this.size);
        return new MapEntry(i2);
    }

    /* loaded from: classes9.dex */
    class MapEntry extends Multisets.AbstractEntry<K> {
        @ParametricNullness
        final K key;
        int lastKnownIndex;

        MapEntry(int i2) {
            this.key = (K) ObjectCountHashMap.this.keys[i2];
            this.lastKnownIndex = i2;
        }

        @Override // com.google.common.collect.Multiset.Entry
        @ParametricNullness
        public K getElement() {
            return this.key;
        }

        void updateLastKnownIndex() {
            int i2 = this.lastKnownIndex;
            if (i2 == -1 || i2 >= ObjectCountHashMap.this.size() || !Objects.equal(this.key, ObjectCountHashMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = ObjectCountHashMap.this.indexOf(this.key);
            }
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                return 0;
            }
            return ObjectCountHashMap.this.values[this.lastKnownIndex];
        }

        public int setCount(int i2) {
            updateLastKnownIndex();
            if (this.lastKnownIndex == -1) {
                ObjectCountHashMap.this.put(this.key, i2);
                return 0;
            }
            int i3 = ObjectCountHashMap.this.values[this.lastKnownIndex];
            ObjectCountHashMap.this.values[this.lastKnownIndex] = i2;
            return i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void ensureCapacity(int i2) {
        if (i2 > this.entries.length) {
            resizeEntries(i2);
        }
        if (i2 >= this.threshold) {
            resizeTable(Math.max(2, Integer.highestOneBit(i2 - 1) << 1));
        }
    }

    public int put(@ParametricNullness K k, int i2) {
        CollectPreconditions.checkPositive(i2, "count");
        long[] jArr = this.entries;
        Object[] objArr = this.keys;
        int[] iArr = this.values;
        int smearedHash = Hashing.smearedHash(k);
        int hashTableMask = hashTableMask() & smearedHash;
        int i3 = this.size;
        int[] iArr2 = this.table;
        int i4 = iArr2[hashTableMask];
        if (i4 == -1) {
            iArr2[hashTableMask] = i3;
        } else {
            while (true) {
                long j2 = jArr[i4];
                if (getHash(j2) == smearedHash && Objects.equal(k, objArr[i4])) {
                    int i5 = iArr[i4];
                    iArr[i4] = i2;
                    return i5;
                }
                int next = getNext(j2);
                if (next == -1) {
                    jArr[i4] = swapNext(j2, i3);
                    break;
                }
                i4 = next;
            }
        }
        if (i3 == Integer.MAX_VALUE) {
            throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
        }
        int i6 = i3 + 1;
        resizeMeMaybe(i6);
        insertEntry(i3, k, i2, smearedHash);
        this.size = i6;
        if (i3 >= this.threshold) {
            resizeTable(this.table.length * 2);
        }
        this.modCount++;
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertEntry(int i2, @ParametricNullness K k, int i3, int i4) {
        this.entries[i2] = (i4 << 32) | NEXT_MASK;
        this.keys[i2] = k;
        this.values[i2] = i3;
    }

    private void resizeMeMaybe(int i2) {
        int length = this.entries.length;
        if (i2 > length) {
            int max = Math.max(1, length >>> 1) + length;
            if (max < 0) {
                max = Integer.MAX_VALUE;
            }
            if (max != length) {
                resizeEntries(max);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resizeEntries(int i2) {
        this.keys = Arrays.copyOf(this.keys, i2);
        this.values = Arrays.copyOf(this.values, i2);
        long[] jArr = this.entries;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i2);
        if (i2 > length) {
            Arrays.fill(copyOf, length, i2, -1L);
        }
        this.entries = copyOf;
    }

    private void resizeTable(int i2) {
        if (this.table.length >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int i3 = ((int) (i2 * this.loadFactor)) + 1;
        int[] newTable = newTable(i2);
        long[] jArr = this.entries;
        int length = newTable.length - 1;
        for (int i4 = 0; i4 < this.size; i4++) {
            int hash = getHash(jArr[i4]);
            int i5 = hash & length;
            int i6 = newTable[i5];
            newTable[i5] = i4;
            jArr[i4] = (hash << 32) | (i6 & NEXT_MASK);
        }
        this.threshold = i3;
        this.table = newTable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int indexOf(@CheckForNull Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int i2 = this.table[hashTableMask() & smearedHash];
        while (i2 != -1) {
            long j2 = this.entries[i2];
            if (getHash(j2) == smearedHash && Objects.equal(obj, this.keys[i2])) {
                return i2;
            }
            i2 = getNext(j2);
        }
        return -1;
    }

    public boolean containsKey(@CheckForNull Object obj) {
        return indexOf(obj) != -1;
    }

    public int get(@CheckForNull Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return 0;
        }
        return this.values[indexOf];
    }

    public int remove(@CheckForNull Object obj) {
        return remove(obj, Hashing.smearedHash(obj));
    }

    private int remove(@CheckForNull Object obj, int i2) {
        int hashTableMask = hashTableMask() & i2;
        int i3 = this.table[hashTableMask];
        if (i3 == -1) {
            return 0;
        }
        int i4 = -1;
        while (true) {
            if (getHash(this.entries[i3]) == i2 && Objects.equal(obj, this.keys[i3])) {
                int i5 = this.values[i3];
                if (i4 == -1) {
                    this.table[hashTableMask] = getNext(this.entries[i3]);
                } else {
                    long[] jArr = this.entries;
                    jArr[i4] = swapNext(jArr[i4], getNext(jArr[i3]));
                }
                moveLastEntry(i3);
                this.size--;
                this.modCount++;
                return i5;
            }
            int next = getNext(this.entries[i3]);
            if (next == -1) {
                return 0;
            }
            i4 = i3;
            i3 = next;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int removeEntry(int i2) {
        return remove(this.keys[i2], getHash(this.entries[i2]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void moveLastEntry(int i2) {
        int size = size() - 1;
        if (i2 < size) {
            Object[] objArr = this.keys;
            objArr[i2] = objArr[size];
            int[] iArr = this.values;
            iArr[i2] = iArr[size];
            objArr[size] = null;
            iArr[size] = 0;
            long[] jArr = this.entries;
            long j2 = jArr[size];
            jArr[i2] = j2;
            jArr[size] = -1;
            int hash = getHash(j2) & hashTableMask();
            int[] iArr2 = this.table;
            int i3 = iArr2[hash];
            if (i3 == size) {
                iArr2[hash] = i2;
                return;
            }
            while (true) {
                long j3 = this.entries[i3];
                int next = getNext(j3);
                if (next == size) {
                    this.entries[i3] = swapNext(j3, i2);
                    return;
                }
                i3 = next;
            }
        } else {
            this.keys[i2] = null;
            this.values[i2] = 0;
            this.entries[i2] = -1;
        }
    }

    public void clear() {
        this.modCount++;
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, 0);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }
}
