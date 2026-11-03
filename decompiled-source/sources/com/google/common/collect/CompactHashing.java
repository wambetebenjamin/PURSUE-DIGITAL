package com.google.common.collect;

import com.google.common.base.Objects;
import java.util.Arrays;
import javax.annotation.CheckForNull;
import kotlin.UShort;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
final class CompactHashing {
    private static final int BYTE_MASK = 255;
    private static final int BYTE_MAX_SIZE = 256;
    static final int DEFAULT_SIZE = 3;
    static final int HASH_TABLE_BITS_MASK = 31;
    private static final int HASH_TABLE_BITS_MAX_BITS = 5;
    static final int MAX_SIZE = 1073741823;
    private static final int MIN_HASH_TABLE_SIZE = 4;
    static final int MODIFICATION_COUNT_INCREMENT = 32;
    private static final int SHORT_MASK = 65535;
    private static final int SHORT_MAX_SIZE = 65536;
    static final byte UNSET = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getHashPrefix(int i2, int i3) {
        return i2 & (~i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getNext(int i2, int i3) {
        return i2 & i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int maskCombine(int i2, int i3, int i4) {
        return (i2 & (~i4)) | (i3 & i4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int newCapacity(int i2) {
        return (i2 < 32 ? 4 : 2) * (i2 + 1);
    }

    private CompactHashing() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int tableSize(int i2) {
        return Math.max(4, Hashing.closedTableSize(i2 + 1, 1.0d));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object createTable(int i2) {
        if (i2 < 2 || i2 > 1073741824 || Integer.highestOneBit(i2) != i2) {
            throw new IllegalArgumentException(new StringBuilder(52).append("must be power of 2 between 2^1 and 2^30: ").append(i2).toString());
        }
        if (i2 <= 256) {
            return new byte[i2];
        }
        if (i2 <= 65536) {
            return new short[i2];
        }
        return new int[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void tableClear(Object obj) {
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int tableGet(Object obj, int i2) {
        if (obj instanceof byte[]) {
            return ((byte[]) obj)[i2] & 255;
        }
        if (obj instanceof short[]) {
            return ((short[]) obj)[i2] & UShort.MAX_VALUE;
        }
        return ((int[]) obj)[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void tableSet(Object obj, int i2, int i3) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i2] = (byte) i3;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i2] = (short) i3;
        } else {
            ((int[]) obj)[i2] = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int remove(@CheckForNull Object obj, @CheckForNull Object obj2, int i2, Object obj3, int[] iArr, Object[] objArr, @CheckForNull Object[] objArr2) {
        int i3;
        int i4;
        int smearedHash = Hashing.smearedHash(obj);
        int i5 = smearedHash & i2;
        int tableGet = tableGet(obj3, i5);
        if (tableGet == 0) {
            return -1;
        }
        int hashPrefix = getHashPrefix(smearedHash, i2);
        int i6 = -1;
        while (true) {
            i3 = tableGet - 1;
            i4 = iArr[i3];
            if (getHashPrefix(i4, i2) != hashPrefix || !Objects.equal(obj, objArr[i3]) || (objArr2 != null && !Objects.equal(obj2, objArr2[i3]))) {
                int next = getNext(i4, i2);
                if (next == 0) {
                    return -1;
                }
                i6 = i3;
                tableGet = next;
            }
        }
        int next2 = getNext(i4, i2);
        if (i6 == -1) {
            tableSet(obj3, i5, next2);
        } else {
            iArr[i6] = maskCombine(iArr[i6], next2, i2);
        }
        return i3;
    }
}
