package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public final class HashBiMap<K, V> extends AbstractMap<K, V> implements BiMap<K, V>, Serializable {
    private static final int ABSENT = -1;
    private static final int ENDPOINT = -2;
    private transient Set<Map.Entry<K, V>> entrySet;
    private transient int firstInInsertionOrder;
    private transient int[] hashTableKToV;
    private transient int[] hashTableVToK;
    @CheckForNull
    @LazyInit
    private transient BiMap<V, K> inverse;
    private transient Set<K> keySet;
    transient K[] keys;
    private transient int lastInInsertionOrder;
    transient int modCount;
    private transient int[] nextInBucketKToV;
    private transient int[] nextInBucketVToK;
    private transient int[] nextInInsertionOrder;
    private transient int[] prevInInsertionOrder;
    transient int size;
    private transient Set<V> valueSet;
    transient V[] values;

    public static <K, V> HashBiMap<K, V> create() {
        return create(16);
    }

    public static <K, V> HashBiMap<K, V> create(int i2) {
        return new HashBiMap<>(i2);
    }

    public static <K, V> HashBiMap<K, V> create(Map<? extends K, ? extends V> map) {
        HashBiMap<K, V> create = create(map.size());
        create.putAll(map);
        return create;
    }

    private HashBiMap(int i2) {
        init(i2);
    }

    void init(int i2) {
        CollectPreconditions.checkNonnegative(i2, "expectedSize");
        int closedTableSize = Hashing.closedTableSize(i2, 1.0d);
        this.size = 0;
        this.keys = (K[]) new Object[i2];
        this.values = (V[]) new Object[i2];
        this.hashTableKToV = createFilledWithAbsent(closedTableSize);
        this.hashTableVToK = createFilledWithAbsent(closedTableSize);
        this.nextInBucketKToV = createFilledWithAbsent(i2);
        this.nextInBucketVToK = createFilledWithAbsent(i2);
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.prevInInsertionOrder = createFilledWithAbsent(i2);
        this.nextInInsertionOrder = createFilledWithAbsent(i2);
    }

    private static int[] createFilledWithAbsent(int i2) {
        int[] iArr = new int[i2];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    private static int[] expandAndFillWithAbsent(int[] iArr, int i2) {
        int length = iArr.length;
        int[] copyOf = Arrays.copyOf(iArr, i2);
        Arrays.fill(copyOf, length, i2, -1);
        return copyOf;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.size;
    }

    private void ensureCapacity(int i2) {
        int[] iArr = this.nextInBucketKToV;
        if (iArr.length < i2) {
            int expandedCapacity = ImmutableCollection.Builder.expandedCapacity(iArr.length, i2);
            this.keys = (K[]) Arrays.copyOf(this.keys, expandedCapacity);
            this.values = (V[]) Arrays.copyOf(this.values, expandedCapacity);
            this.nextInBucketKToV = expandAndFillWithAbsent(this.nextInBucketKToV, expandedCapacity);
            this.nextInBucketVToK = expandAndFillWithAbsent(this.nextInBucketVToK, expandedCapacity);
            this.prevInInsertionOrder = expandAndFillWithAbsent(this.prevInInsertionOrder, expandedCapacity);
            this.nextInInsertionOrder = expandAndFillWithAbsent(this.nextInInsertionOrder, expandedCapacity);
        }
        if (this.hashTableKToV.length < i2) {
            int closedTableSize = Hashing.closedTableSize(i2, 1.0d);
            this.hashTableKToV = createFilledWithAbsent(closedTableSize);
            this.hashTableVToK = createFilledWithAbsent(closedTableSize);
            for (int i3 = 0; i3 < this.size; i3++) {
                int bucket = bucket(Hashing.smearedHash(this.keys[i3]));
                int[] iArr2 = this.nextInBucketKToV;
                int[] iArr3 = this.hashTableKToV;
                iArr2[i3] = iArr3[bucket];
                iArr3[bucket] = i3;
                int bucket2 = bucket(Hashing.smearedHash(this.values[i3]));
                int[] iArr4 = this.nextInBucketVToK;
                int[] iArr5 = this.hashTableVToK;
                iArr4[i3] = iArr5[bucket2];
                iArr5[bucket2] = i3;
            }
        }
    }

    private int bucket(int i2) {
        return i2 & (this.hashTableKToV.length - 1);
    }

    int findEntryByKey(@CheckForNull Object obj) {
        return findEntryByKey(obj, Hashing.smearedHash(obj));
    }

    int findEntryByKey(@CheckForNull Object obj, int i2) {
        return findEntry(obj, i2, this.hashTableKToV, this.nextInBucketKToV, this.keys);
    }

    int findEntryByValue(@CheckForNull Object obj) {
        return findEntryByValue(obj, Hashing.smearedHash(obj));
    }

    int findEntryByValue(@CheckForNull Object obj, int i2) {
        return findEntry(obj, i2, this.hashTableVToK, this.nextInBucketVToK, this.values);
    }

    int findEntry(@CheckForNull Object obj, int i2, int[] iArr, int[] iArr2, Object[] objArr) {
        int i3 = iArr[bucket(i2)];
        while (i3 != -1) {
            if (Objects.equal(objArr[i3], obj)) {
                return i3;
            }
            i3 = iArr2[i3];
        }
        return -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(@CheckForNull Object obj) {
        return findEntryByKey(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsValue(@CheckForNull Object obj) {
        return findEntryByValue(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public V get(@CheckForNull Object obj) {
        int findEntryByKey = findEntryByKey(obj);
        if (findEntryByKey == -1) {
            return null;
        }
        return this.values[findEntryByKey];
    }

    @CheckForNull
    K getInverse(@CheckForNull Object obj) {
        int findEntryByValue = findEntryByValue(obj);
        if (findEntryByValue == -1) {
            return null;
        }
        return this.keys[findEntryByValue];
    }

    @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
    @CheckForNull
    public V put(@ParametricNullness K k, @ParametricNullness V v) {
        return put(k, v, false);
    }

    @CheckForNull
    V put(@ParametricNullness K k, @ParametricNullness V v, boolean z) {
        int smearedHash = Hashing.smearedHash(k);
        int findEntryByKey = findEntryByKey(k, smearedHash);
        if (findEntryByKey != -1) {
            V v2 = this.values[findEntryByKey];
            if (Objects.equal(v2, v)) {
                return v;
            }
            replaceValueInEntry(findEntryByKey, v, z);
            return v2;
        }
        int smearedHash2 = Hashing.smearedHash(v);
        int findEntryByValue = findEntryByValue(v, smearedHash2);
        if (!z) {
            Preconditions.checkArgument(findEntryByValue == -1, "Value already present: %s", v);
        } else if (findEntryByValue != -1) {
            removeEntryValueHashKnown(findEntryByValue, smearedHash2);
        }
        ensureCapacity(this.size + 1);
        K[] kArr = this.keys;
        int i2 = this.size;
        kArr[i2] = k;
        this.values[i2] = v;
        insertIntoTableKToV(i2, smearedHash);
        insertIntoTableVToK(this.size, smearedHash2);
        setSucceeds(this.lastInInsertionOrder, this.size);
        setSucceeds(this.size, -2);
        this.size++;
        this.modCount++;
        return null;
    }

    @Override // com.google.common.collect.BiMap
    @CheckForNull
    public V forcePut(@ParametricNullness K k, @ParametricNullness V v) {
        return put(k, v, true);
    }

    @CheckForNull
    K putInverse(@ParametricNullness V v, @ParametricNullness K k, boolean z) {
        int smearedHash = Hashing.smearedHash(v);
        int findEntryByValue = findEntryByValue(v, smearedHash);
        if (findEntryByValue != -1) {
            K k2 = this.keys[findEntryByValue];
            if (Objects.equal(k2, k)) {
                return k;
            }
            replaceKeyInEntry(findEntryByValue, k, z);
            return k2;
        }
        int i2 = this.lastInInsertionOrder;
        int smearedHash2 = Hashing.smearedHash(k);
        int findEntryByKey = findEntryByKey(k, smearedHash2);
        if (!z) {
            Preconditions.checkArgument(findEntryByKey == -1, "Key already present: %s", k);
        } else if (findEntryByKey != -1) {
            i2 = this.prevInInsertionOrder[findEntryByKey];
            removeEntryKeyHashKnown(findEntryByKey, smearedHash2);
        }
        ensureCapacity(this.size + 1);
        K[] kArr = this.keys;
        int i3 = this.size;
        kArr[i3] = k;
        this.values[i3] = v;
        insertIntoTableKToV(i3, smearedHash2);
        insertIntoTableVToK(this.size, smearedHash);
        int i4 = i2 == -2 ? this.firstInInsertionOrder : this.nextInInsertionOrder[i2];
        setSucceeds(i2, this.size);
        setSucceeds(this.size, i4);
        this.size++;
        this.modCount++;
        return null;
    }

    private void setSucceeds(int i2, int i3) {
        if (i2 == -2) {
            this.firstInInsertionOrder = i3;
        } else {
            this.nextInInsertionOrder[i2] = i3;
        }
        if (i3 == -2) {
            this.lastInInsertionOrder = i2;
        } else {
            this.prevInInsertionOrder[i3] = i2;
        }
    }

    private void insertIntoTableKToV(int i2, int i3) {
        Preconditions.checkArgument(i2 != -1);
        int bucket = bucket(i3);
        int[] iArr = this.nextInBucketKToV;
        int[] iArr2 = this.hashTableKToV;
        iArr[i2] = iArr2[bucket];
        iArr2[bucket] = i2;
    }

    private void insertIntoTableVToK(int i2, int i3) {
        Preconditions.checkArgument(i2 != -1);
        int bucket = bucket(i3);
        int[] iArr = this.nextInBucketVToK;
        int[] iArr2 = this.hashTableVToK;
        iArr[i2] = iArr2[bucket];
        iArr2[bucket] = i2;
    }

    private void deleteFromTableKToV(int i2, int i3) {
        Preconditions.checkArgument(i2 != -1);
        int bucket = bucket(i3);
        int[] iArr = this.hashTableKToV;
        if (iArr[bucket] == i2) {
            int[] iArr2 = this.nextInBucketKToV;
            iArr[bucket] = iArr2[i2];
            iArr2[i2] = -1;
            return;
        }
        int i4 = iArr[bucket];
        int i5 = this.nextInBucketKToV[i4];
        while (true) {
            int i6 = i5;
            int i7 = i4;
            i4 = i6;
            if (i4 == -1) {
                String valueOf = String.valueOf(this.keys[i2]);
                throw new AssertionError(new StringBuilder(String.valueOf(valueOf).length() + 32).append("Expected to find entry with key ").append(valueOf).toString());
            } else if (i4 != i2) {
                i5 = this.nextInBucketKToV[i4];
            } else {
                int[] iArr3 = this.nextInBucketKToV;
                iArr3[i7] = iArr3[i2];
                iArr3[i2] = -1;
                return;
            }
        }
    }

    private void deleteFromTableVToK(int i2, int i3) {
        Preconditions.checkArgument(i2 != -1);
        int bucket = bucket(i3);
        int[] iArr = this.hashTableVToK;
        if (iArr[bucket] == i2) {
            int[] iArr2 = this.nextInBucketVToK;
            iArr[bucket] = iArr2[i2];
            iArr2[i2] = -1;
            return;
        }
        int i4 = iArr[bucket];
        int i5 = this.nextInBucketVToK[i4];
        while (true) {
            int i6 = i5;
            int i7 = i4;
            i4 = i6;
            if (i4 == -1) {
                String valueOf = String.valueOf(this.values[i2]);
                throw new AssertionError(new StringBuilder(String.valueOf(valueOf).length() + 34).append("Expected to find entry with value ").append(valueOf).toString());
            } else if (i4 != i2) {
                i5 = this.nextInBucketVToK[i4];
            } else {
                int[] iArr3 = this.nextInBucketVToK;
                iArr3[i7] = iArr3[i2];
                iArr3[i2] = -1;
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replaceValueInEntry(int i2, @ParametricNullness V v, boolean z) {
        Preconditions.checkArgument(i2 != -1);
        int smearedHash = Hashing.smearedHash(v);
        int findEntryByValue = findEntryByValue(v, smearedHash);
        if (findEntryByValue != -1) {
            if (z) {
                removeEntryValueHashKnown(findEntryByValue, smearedHash);
                if (i2 == this.size) {
                    i2 = findEntryByValue;
                }
            } else {
                String valueOf = String.valueOf(v);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 30).append("Value already present in map: ").append(valueOf).toString());
            }
        }
        deleteFromTableVToK(i2, Hashing.smearedHash(this.values[i2]));
        this.values[i2] = v;
        insertIntoTableVToK(i2, smearedHash);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void replaceKeyInEntry(int i2, @ParametricNullness K k, boolean z) {
        Preconditions.checkArgument(i2 != -1);
        int smearedHash = Hashing.smearedHash(k);
        int findEntryByKey = findEntryByKey(k, smearedHash);
        int i3 = this.lastInInsertionOrder;
        int i4 = -2;
        if (findEntryByKey != -1) {
            if (z) {
                i3 = this.prevInInsertionOrder[findEntryByKey];
                i4 = this.nextInInsertionOrder[findEntryByKey];
                removeEntryKeyHashKnown(findEntryByKey, smearedHash);
                if (i2 == this.size) {
                    i2 = findEntryByKey;
                }
            } else {
                String valueOf = String.valueOf(k);
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 28).append("Key already present in map: ").append(valueOf).toString());
            }
        }
        if (i3 == i2) {
            i3 = this.prevInInsertionOrder[i2];
        } else if (i3 == this.size) {
            i3 = findEntryByKey;
        }
        if (i4 == i2) {
            findEntryByKey = this.nextInInsertionOrder[i2];
        } else if (i4 != this.size) {
            findEntryByKey = i4;
        }
        setSucceeds(this.prevInInsertionOrder[i2], this.nextInInsertionOrder[i2]);
        deleteFromTableKToV(i2, Hashing.smearedHash(this.keys[i2]));
        this.keys[i2] = k;
        insertIntoTableKToV(i2, Hashing.smearedHash(k));
        setSucceeds(i3, i2);
        setSucceeds(i2, findEntryByKey);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CheckForNull
    public V remove(@CheckForNull Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int findEntryByKey = findEntryByKey(obj, smearedHash);
        if (findEntryByKey == -1) {
            return null;
        }
        V v = this.values[findEntryByKey];
        removeEntryKeyHashKnown(findEntryByKey, smearedHash);
        return v;
    }

    @CheckForNull
    K removeInverse(@CheckForNull Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int findEntryByValue = findEntryByValue(obj, smearedHash);
        if (findEntryByValue == -1) {
            return null;
        }
        K k = this.keys[findEntryByValue];
        removeEntryValueHashKnown(findEntryByValue, smearedHash);
        return k;
    }

    void removeEntry(int i2) {
        removeEntryKeyHashKnown(i2, Hashing.smearedHash(this.keys[i2]));
    }

    private void removeEntry(int i2, int i3, int i4) {
        Preconditions.checkArgument(i2 != -1);
        deleteFromTableKToV(i2, i3);
        deleteFromTableVToK(i2, i4);
        setSucceeds(this.prevInInsertionOrder[i2], this.nextInInsertionOrder[i2]);
        moveEntryToIndex(this.size - 1, i2);
        K[] kArr = this.keys;
        int i5 = this.size;
        kArr[i5 - 1] = null;
        this.values[i5 - 1] = null;
        this.size = i5 - 1;
        this.modCount++;
    }

    void removeEntryKeyHashKnown(int i2, int i3) {
        removeEntry(i2, i3, Hashing.smearedHash(this.values[i2]));
    }

    void removeEntryValueHashKnown(int i2, int i3) {
        removeEntry(i2, Hashing.smearedHash(this.keys[i2]), i3);
    }

    private void moveEntryToIndex(int i2, int i3) {
        int i4;
        int i5;
        if (i2 == i3) {
            return;
        }
        int i6 = this.prevInInsertionOrder[i2];
        int i7 = this.nextInInsertionOrder[i2];
        setSucceeds(i6, i3);
        setSucceeds(i3, i7);
        K[] kArr = this.keys;
        K k = kArr[i2];
        V[] vArr = this.values;
        V v = vArr[i2];
        kArr[i3] = k;
        vArr[i3] = v;
        int bucket = bucket(Hashing.smearedHash(k));
        int[] iArr = this.hashTableKToV;
        if (iArr[bucket] == i2) {
            iArr[bucket] = i3;
        } else {
            int i8 = iArr[bucket];
            int i9 = this.nextInBucketKToV[i8];
            while (true) {
                int i10 = i9;
                i4 = i8;
                i8 = i10;
                if (i8 == i2) {
                    break;
                }
                i9 = this.nextInBucketKToV[i8];
            }
            this.nextInBucketKToV[i4] = i3;
        }
        int[] iArr2 = this.nextInBucketKToV;
        iArr2[i3] = iArr2[i2];
        iArr2[i2] = -1;
        int bucket2 = bucket(Hashing.smearedHash(v));
        int[] iArr3 = this.hashTableVToK;
        if (iArr3[bucket2] == i2) {
            iArr3[bucket2] = i3;
        } else {
            int i11 = iArr3[bucket2];
            int i12 = this.nextInBucketVToK[i11];
            while (true) {
                int i13 = i12;
                i5 = i11;
                i11 = i13;
                if (i11 == i2) {
                    break;
                }
                i12 = this.nextInBucketVToK[i11];
            }
            this.nextInBucketVToK[i5] = i3;
        }
        int[] iArr4 = this.nextInBucketVToK;
        iArr4[i3] = iArr4[i2];
        iArr4[i2] = -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, (Object) null);
        Arrays.fill(this.hashTableKToV, -1);
        Arrays.fill(this.hashTableVToK, -1);
        Arrays.fill(this.nextInBucketKToV, 0, this.size, -1);
        Arrays.fill(this.nextInBucketVToK, 0, this.size, -1);
        Arrays.fill(this.prevInInsertionOrder, 0, this.size, -1);
        Arrays.fill(this.nextInInsertionOrder, 0, this.size, -1);
        this.size = 0;
        this.firstInInsertionOrder = -2;
        this.lastInInsertionOrder = -2;
        this.modCount++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static abstract class View<K, V, T> extends AbstractSet<T> {
        final HashBiMap<K, V> biMap;

        @ParametricNullness
        abstract T forEntry(int i2);

        View(HashBiMap<K, V> hashBiMap) {
            this.biMap = hashBiMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<T> iterator() {
            return new Iterator<T>() { // from class: com.google.common.collect.HashBiMap.View.1
                private int expectedModCount;
                private int index;
                private int indexToRemove = -1;
                private int remaining;

                {
                    this.index = ((HashBiMap) View.this.biMap).firstInInsertionOrder;
                    this.expectedModCount = View.this.biMap.modCount;
                    this.remaining = View.this.biMap.size;
                }

                private void checkForComodification() {
                    if (View.this.biMap.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    checkForComodification();
                    return this.index != -2 && this.remaining > 0;
                }

                @Override // java.util.Iterator
                @ParametricNullness
                public T next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    T t = (T) View.this.forEntry(this.index);
                    this.indexToRemove = this.index;
                    this.index = ((HashBiMap) View.this.biMap).nextInInsertionOrder[this.index];
                    this.remaining--;
                    return t;
                }

                @Override // java.util.Iterator
                public void remove() {
                    checkForComodification();
                    CollectPreconditions.checkRemove(this.indexToRemove != -1);
                    View.this.biMap.removeEntry(this.indexToRemove);
                    if (this.index == View.this.biMap.size) {
                        this.index = this.indexToRemove;
                    }
                    this.indexToRemove = -1;
                    this.expectedModCount = View.this.biMap.modCount;
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.biMap.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.biMap.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<K> keySet() {
        Set<K> set = this.keySet;
        if (set == null) {
            KeySet keySet = new KeySet();
            this.keySet = keySet;
            return keySet;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public final class KeySet extends View<K, V, K> {
        KeySet() {
            super(HashBiMap.this);
        }

        @Override // com.google.common.collect.HashBiMap.View
        @ParametricNullness
        K forEntry(int i2) {
            return (K) NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.keys[i2]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            return HashBiMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            int smearedHash = Hashing.smearedHash(obj);
            int findEntryByKey = HashBiMap.this.findEntryByKey(obj, smearedHash);
            if (findEntryByKey != -1) {
                HashBiMap.this.removeEntryKeyHashKnown(findEntryByKey, smearedHash);
                return true;
            }
            return false;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
    public Set<V> values() {
        Set<V> set = this.valueSet;
        if (set == null) {
            ValueSet valueSet = new ValueSet();
            this.valueSet = valueSet;
            return valueSet;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public final class ValueSet extends View<K, V, V> {
        ValueSet() {
            super(HashBiMap.this);
        }

        @Override // com.google.common.collect.HashBiMap.View
        @ParametricNullness
        V forEntry(int i2) {
            return (V) NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.values[i2]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            return HashBiMap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            int smearedHash = Hashing.smearedHash(obj);
            int findEntryByValue = HashBiMap.this.findEntryByValue(obj, smearedHash);
            if (findEntryByValue != -1) {
                HashBiMap.this.removeEntryValueHashKnown(findEntryByValue, smearedHash);
                return true;
            }
            return false;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.entrySet;
        if (set == null) {
            EntrySet entrySet = new EntrySet();
            this.entrySet = entrySet;
            return entrySet;
        }
        return set;
    }

    /* loaded from: classes9.dex */
    final class EntrySet extends View<K, V, Map.Entry<K, V>> {
        EntrySet() {
            super(HashBiMap.this);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int findEntryByKey = HashBiMap.this.findEntryByKey(key);
                return findEntryByKey != -1 && Objects.equal(value, HashBiMap.this.values[findEntryByKey]);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int smearedHash = Hashing.smearedHash(key);
                int findEntryByKey = HashBiMap.this.findEntryByKey(key, smearedHash);
                if (findEntryByKey == -1 || !Objects.equal(value, HashBiMap.this.values[findEntryByKey])) {
                    return false;
                }
                HashBiMap.this.removeEntryKeyHashKnown(findEntryByKey, smearedHash);
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        public Map.Entry<K, V> forEntry(int i2) {
            return new EntryForKey(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public final class EntryForKey extends AbstractMapEntry<K, V> {
        int index;
        @ParametricNullness
        final K key;

        EntryForKey(int i2) {
            this.key = (K) NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.keys[i2]);
            this.index = i2;
        }

        void updateIndex() {
            int i2 = this.index;
            if (i2 == -1 || i2 > HashBiMap.this.size || !Objects.equal(HashBiMap.this.keys[this.index], this.key)) {
                this.index = HashBiMap.this.findEntryByKey(this.key);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public K getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V getValue() {
            updateIndex();
            return this.index == -1 ? (V) NullnessCasts.unsafeNull() : (V) NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.values[this.index]);
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V setValue(@ParametricNullness V v) {
            updateIndex();
            if (this.index == -1) {
                HashBiMap.this.put(this.key, v);
                return (V) NullnessCasts.unsafeNull();
            }
            V v2 = (V) NullnessCasts.uncheckedCastNullableTToT(HashBiMap.this.values[this.index]);
            if (Objects.equal(v2, v)) {
                return v;
            }
            HashBiMap.this.replaceValueInEntry(this.index, v, false);
            return v2;
        }
    }

    @Override // com.google.common.collect.BiMap
    public BiMap<V, K> inverse() {
        BiMap<V, K> biMap = this.inverse;
        if (biMap == null) {
            Inverse inverse = new Inverse(this);
            this.inverse = inverse;
            return inverse;
        }
        return biMap;
    }

    /* loaded from: classes9.dex */
    static class Inverse<K, V> extends AbstractMap<V, K> implements BiMap<V, K>, Serializable {
        private final HashBiMap<K, V> forward;
        private transient Set<Map.Entry<V, K>> inverseEntrySet;

        Inverse(HashBiMap<K, V> hashBiMap) {
            this.forward = hashBiMap;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.forward.size;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@CheckForNull Object obj) {
            return this.forward.containsValue(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CheckForNull
        public K get(@CheckForNull Object obj) {
            return this.forward.getInverse(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsValue(@CheckForNull Object obj) {
            return this.forward.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
        @CheckForNull
        public K put(@ParametricNullness V v, @ParametricNullness K k) {
            return this.forward.putInverse(v, k, false);
        }

        @Override // com.google.common.collect.BiMap
        @CheckForNull
        public K forcePut(@ParametricNullness V v, @ParametricNullness K k) {
            return this.forward.putInverse(v, k, true);
        }

        @Override // com.google.common.collect.BiMap
        public BiMap<K, V> inverse() {
            return this.forward;
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CheckForNull
        public K remove(@CheckForNull Object obj) {
            return this.forward.removeInverse(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            this.forward.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<V> keySet() {
            return this.forward.values();
        }

        @Override // java.util.AbstractMap, java.util.Map, com.google.common.collect.BiMap
        public Set<K> values() {
            return this.forward.keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<V, K>> entrySet() {
            Set<Map.Entry<V, K>> set = this.inverseEntrySet;
            if (set == null) {
                InverseEntrySet inverseEntrySet = new InverseEntrySet(this.forward);
                this.inverseEntrySet = inverseEntrySet;
                return inverseEntrySet;
            }
            return set;
        }

        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            ((HashBiMap) this.forward).inverse = this;
        }
    }

    /* loaded from: classes9.dex */
    static class InverseEntrySet<K, V> extends View<K, V, Map.Entry<V, K>> {
        InverseEntrySet(HashBiMap<K, V> hashBiMap) {
            super(hashBiMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@CheckForNull Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int findEntryByValue = this.biMap.findEntryByValue(key);
                return findEntryByValue != -1 && Objects.equal(this.biMap.keys[findEntryByValue], value);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@CheckForNull Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                int smearedHash = Hashing.smearedHash(key);
                int findEntryByValue = this.biMap.findEntryByValue(key, smearedHash);
                if (findEntryByValue == -1 || !Objects.equal(this.biMap.keys[findEntryByValue], value)) {
                    return false;
                }
                this.biMap.removeEntryValueHashKnown(findEntryByValue, smearedHash);
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.HashBiMap.View
        public Map.Entry<V, K> forEntry(int i2) {
            return new EntryForValue(this.biMap, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class EntryForValue<K, V> extends AbstractMapEntry<V, K> {
        final HashBiMap<K, V> biMap;
        int index;
        @ParametricNullness
        final V value;

        EntryForValue(HashBiMap<K, V> hashBiMap, int i2) {
            this.biMap = hashBiMap;
            this.value = (V) NullnessCasts.uncheckedCastNullableTToT(hashBiMap.values[i2]);
            this.index = i2;
        }

        private void updateIndex() {
            int i2 = this.index;
            if (i2 == -1 || i2 > this.biMap.size || !Objects.equal(this.value, this.biMap.values[this.index])) {
                this.index = this.biMap.findEntryByValue(this.value);
            }
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public V getKey() {
            return this.value;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public K getValue() {
            updateIndex();
            return this.index == -1 ? (K) NullnessCasts.unsafeNull() : (K) NullnessCasts.uncheckedCastNullableTToT(this.biMap.keys[this.index]);
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        @ParametricNullness
        public K setValue(@ParametricNullness K k) {
            updateIndex();
            if (this.index == -1) {
                this.biMap.putInverse(this.value, k, false);
                return (K) NullnessCasts.unsafeNull();
            }
            K k2 = (K) NullnessCasts.uncheckedCastNullableTToT(this.biMap.keys[this.index]);
            if (Objects.equal(k2, k)) {
                return k;
            }
            this.biMap.replaceKeyInEntry(this.index, k, false);
            return k2;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Serialization.writeMap(this, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readCount = Serialization.readCount(objectInputStream);
        init(16);
        Serialization.populateMap(this, objectInputStream, readCount);
    }
}
