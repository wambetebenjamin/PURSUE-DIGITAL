package com.google.common.collect;

import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
final class Hashing {
    private static final long C1 = -862048943;
    private static final long C2 = 461845907;
    private static final int MAX_TABLE_SIZE = 1073741824;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean needsResizing(int i2, int i3, double d2) {
        return ((double) i2) > d2 * ((double) i3) && i3 < 1073741824;
    }

    private Hashing() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int smear(int i2) {
        return (int) (Integer.rotateLeft((int) (i2 * C1), 15) * C2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int smearedHash(@CheckForNull Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int closedTableSize(int i2, double d2) {
        int max = Math.max(i2, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (max > ((int) (d2 * highestOneBit))) {
            int i3 = highestOneBit << 1;
            if (i3 > 0) {
                return i3;
            }
            return 1073741824;
        }
        return highestOneBit;
    }
}
