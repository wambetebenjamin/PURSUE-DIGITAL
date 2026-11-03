package com.google.common.primitives;

import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
@Immutable
/* loaded from: classes9.dex */
public final class ImmutableLongArray implements Serializable {
    private static final ImmutableLongArray EMPTY = new ImmutableLongArray(new long[0]);
    private final long[] array;
    private final int end;
    private final transient int start;

    public static ImmutableLongArray of() {
        return EMPTY;
    }

    public static ImmutableLongArray of(long j2) {
        return new ImmutableLongArray(new long[]{j2});
    }

    public static ImmutableLongArray of(long j2, long j3) {
        return new ImmutableLongArray(new long[]{j2, j3});
    }

    public static ImmutableLongArray of(long j2, long j3, long j4) {
        return new ImmutableLongArray(new long[]{j2, j3, j4});
    }

    public static ImmutableLongArray of(long j2, long j3, long j4, long j5) {
        return new ImmutableLongArray(new long[]{j2, j3, j4, j5});
    }

    public static ImmutableLongArray of(long j2, long j3, long j4, long j5, long j6) {
        return new ImmutableLongArray(new long[]{j2, j3, j4, j5, j6});
    }

    public static ImmutableLongArray of(long j2, long j3, long j4, long j5, long j6, long j7) {
        return new ImmutableLongArray(new long[]{j2, j3, j4, j5, j6, j7});
    }

    public static ImmutableLongArray of(long j2, long... jArr) {
        Preconditions.checkArgument(jArr.length <= 2147483646, "the total number of elements must fit in an int");
        long[] jArr2 = new long[jArr.length + 1];
        jArr2[0] = j2;
        System.arraycopy(jArr, 0, jArr2, 1, jArr.length);
        return new ImmutableLongArray(jArr2);
    }

    public static ImmutableLongArray copyOf(long[] jArr) {
        if (jArr.length == 0) {
            return EMPTY;
        }
        return new ImmutableLongArray(Arrays.copyOf(jArr, jArr.length));
    }

    public static ImmutableLongArray copyOf(Collection<Long> collection) {
        return collection.isEmpty() ? EMPTY : new ImmutableLongArray(Longs.toArray(collection));
    }

    public static ImmutableLongArray copyOf(Iterable<Long> iterable) {
        if (iterable instanceof Collection) {
            return copyOf((Collection<Long>) iterable);
        }
        return builder().addAll(iterable).build();
    }

    public static Builder builder(int i2) {
        Preconditions.checkArgument(i2 >= 0, "Invalid initialCapacity: %s", i2);
        return new Builder(i2);
    }

    public static Builder builder() {
        return new Builder(10);
    }

    /* loaded from: classes9.dex */
    public static final class Builder {
        private long[] array;
        private int count = 0;

        Builder(int i2) {
            this.array = new long[i2];
        }

        public Builder add(long j2) {
            ensureRoomFor(1);
            long[] jArr = this.array;
            int i2 = this.count;
            jArr[i2] = j2;
            this.count = i2 + 1;
            return this;
        }

        public Builder addAll(long[] jArr) {
            ensureRoomFor(jArr.length);
            System.arraycopy(jArr, 0, this.array, this.count, jArr.length);
            this.count += jArr.length;
            return this;
        }

        public Builder addAll(Iterable<Long> iterable) {
            if (iterable instanceof Collection) {
                return addAll((Collection) iterable);
            }
            for (Long l : iterable) {
                add(l.longValue());
            }
            return this;
        }

        public Builder addAll(Collection<Long> collection) {
            ensureRoomFor(collection.size());
            for (Long l : collection) {
                long[] jArr = this.array;
                int i2 = this.count;
                this.count = i2 + 1;
                jArr[i2] = l.longValue();
            }
            return this;
        }

        public Builder addAll(ImmutableLongArray immutableLongArray) {
            ensureRoomFor(immutableLongArray.length());
            System.arraycopy(immutableLongArray.array, immutableLongArray.start, this.array, this.count, immutableLongArray.length());
            this.count += immutableLongArray.length();
            return this;
        }

        private void ensureRoomFor(int i2) {
            int i3 = this.count + i2;
            long[] jArr = this.array;
            if (i3 > jArr.length) {
                this.array = Arrays.copyOf(jArr, expandedCapacity(jArr.length, i3));
            }
        }

        private static int expandedCapacity(int i2, int i3) {
            if (i3 >= 0) {
                int i4 = i2 + (i2 >> 1) + 1;
                if (i4 < i3) {
                    i4 = Integer.highestOneBit(i3 - 1) << 1;
                }
                if (i4 < 0) {
                    return Integer.MAX_VALUE;
                }
                return i4;
            }
            throw new AssertionError("cannot store more than MAX_VALUE elements");
        }

        @CheckReturnValue
        public ImmutableLongArray build() {
            return this.count == 0 ? ImmutableLongArray.EMPTY : new ImmutableLongArray(this.array, 0, this.count);
        }
    }

    private ImmutableLongArray(long[] jArr) {
        this(jArr, 0, jArr.length);
    }

    private ImmutableLongArray(long[] jArr, int i2, int i3) {
        this.array = jArr;
        this.start = i2;
        this.end = i3;
    }

    public int length() {
        return this.end - this.start;
    }

    public boolean isEmpty() {
        return this.end == this.start;
    }

    public long get(int i2) {
        Preconditions.checkElementIndex(i2, length());
        return this.array[this.start + i2];
    }

    public int indexOf(long j2) {
        for (int i2 = this.start; i2 < this.end; i2++) {
            if (this.array[i2] == j2) {
                return i2 - this.start;
            }
        }
        return -1;
    }

    public int lastIndexOf(long j2) {
        int i2 = this.end;
        while (true) {
            i2--;
            int i3 = this.start;
            if (i2 < i3) {
                return -1;
            }
            if (this.array[i2] == j2) {
                return i2 - i3;
            }
        }
    }

    public boolean contains(long j2) {
        return indexOf(j2) >= 0;
    }

    public long[] toArray() {
        return Arrays.copyOfRange(this.array, this.start, this.end);
    }

    public ImmutableLongArray subArray(int i2, int i3) {
        Preconditions.checkPositionIndexes(i2, i3, length());
        if (i2 == i3) {
            return EMPTY;
        }
        long[] jArr = this.array;
        int i4 = this.start;
        return new ImmutableLongArray(jArr, i2 + i4, i4 + i3);
    }

    public List<Long> asList() {
        return new AsList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class AsList extends AbstractList<Long> implements RandomAccess, Serializable {
        private final ImmutableLongArray parent;

        private AsList(ImmutableLongArray immutableLongArray) {
            this.parent = immutableLongArray;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parent.length();
        }

        @Override // java.util.AbstractList, java.util.List
        public Long get(int i2) {
            return Long.valueOf(this.parent.get(i2));
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(@CheckForNull Object obj) {
            return indexOf(obj) >= 0;
        }

        @Override // java.util.AbstractList, java.util.List
        public int indexOf(@CheckForNull Object obj) {
            if (obj instanceof Long) {
                return this.parent.indexOf(((Long) obj).longValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public int lastIndexOf(@CheckForNull Object obj) {
            if (obj instanceof Long) {
                return this.parent.lastIndexOf(((Long) obj).longValue());
            }
            return -1;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<Long> subList(int i2, int i3) {
            return this.parent.subArray(i2, i3).asList();
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof AsList) {
                return this.parent.equals(((AsList) obj).parent);
            }
            if (obj instanceof List) {
                List list = (List) obj;
                if (size() != list.size()) {
                    return false;
                }
                int i2 = this.parent.start;
                for (Object obj2 : list) {
                    if (obj2 instanceof Long) {
                        int i3 = i2 + 1;
                        if (this.parent.array[i2] == ((Long) obj2).longValue()) {
                            i2 = i3;
                        }
                    }
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractList, java.util.Collection, java.util.List
        public int hashCode() {
            return this.parent.hashCode();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return this.parent.toString();
        }
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ImmutableLongArray) {
            ImmutableLongArray immutableLongArray = (ImmutableLongArray) obj;
            if (length() != immutableLongArray.length()) {
                return false;
            }
            for (int i2 = 0; i2 < length(); i2++) {
                if (get(i2) != immutableLongArray.get(i2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i2 = 1;
        for (int i3 = this.start; i3 < this.end; i3++) {
            i2 = (i2 * 31) + Longs.hashCode(this.array[i3]);
        }
        return i2;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(length() * 5);
        sb.append('[').append(this.array[this.start]);
        int i2 = this.start;
        while (true) {
            i2++;
            if (i2 < this.end) {
                sb.append(", ").append(this.array[i2]);
            } else {
                sb.append(']');
                return sb.toString();
            }
        }
    }

    public ImmutableLongArray trimmed() {
        return isPartialView() ? new ImmutableLongArray(toArray()) : this;
    }

    private boolean isPartialView() {
        return this.start > 0 || this.end < this.array.length;
    }

    Object writeReplace() {
        return trimmed();
    }

    Object readResolve() {
        return isEmpty() ? EMPTY : this;
    }
}
