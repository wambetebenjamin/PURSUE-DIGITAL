package com.google.common.primitives;

import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public final class UnsignedLongs {
    public static final long MAX_VALUE = -1;

    private static long flip(long j2) {
        return j2 ^ Long.MIN_VALUE;
    }

    private UnsignedLongs() {
    }

    public static int compare(long j2, long j3) {
        return Longs.compare(flip(j2), flip(j3));
    }

    public static long min(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long flip = flip(jArr[0]);
        for (int i2 = 1; i2 < jArr.length; i2++) {
            long flip2 = flip(jArr[i2]);
            if (flip2 < flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    public static long max(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0);
        long flip = flip(jArr[0]);
        for (int i2 = 1; i2 < jArr.length; i2++) {
            long flip2 = flip(jArr[i2]);
            if (flip2 > flip) {
                flip = flip2;
            }
        }
        return flip(flip);
    }

    public static String join(String str, long... jArr) {
        Preconditions.checkNotNull(str);
        if (jArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(jArr.length * 5);
        sb.append(toString(jArr[0]));
        for (int i2 = 1; i2 < jArr.length; i2++) {
            sb.append(str).append(toString(jArr[i2]));
        }
        return sb.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    /* loaded from: classes9.dex */
    enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "UnsignedLongs.lexicographicalComparator()";
        }

        @Override // java.util.Comparator
        public int compare(long[] jArr, long[] jArr2) {
            int min = Math.min(jArr.length, jArr2.length);
            for (int i2 = 0; i2 < min; i2++) {
                if (jArr[i2] != jArr2[i2]) {
                    return UnsignedLongs.compare(jArr[i2], jArr2[i2]);
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    public static void sort(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        sort(jArr, 0, jArr.length);
    }

    public static void sort(long[] jArr, int i2, int i3) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(i2, i3, jArr.length);
        for (int i4 = i2; i4 < i3; i4++) {
            jArr[i4] = flip(jArr[i4]);
        }
        Arrays.sort(jArr, i2, i3);
        while (i2 < i3) {
            jArr[i2] = flip(jArr[i2]);
            i2++;
        }
    }

    public static void sortDescending(long[] jArr) {
        Preconditions.checkNotNull(jArr);
        sortDescending(jArr, 0, jArr.length);
    }

    public static void sortDescending(long[] jArr, int i2, int i3) {
        Preconditions.checkNotNull(jArr);
        Preconditions.checkPositionIndexes(i2, i3, jArr.length);
        for (int i4 = i2; i4 < i3; i4++) {
            jArr[i4] = Long.MAX_VALUE ^ jArr[i4];
        }
        Arrays.sort(jArr, i2, i3);
        while (i2 < i3) {
            jArr[i2] = jArr[i2] ^ Long.MAX_VALUE;
            i2++;
        }
    }

    public static long divide(long j2, long j3) {
        if (j3 < 0) {
            return compare(j2, j3) < 0 ? 0L : 1L;
        } else if (j2 >= 0) {
            return j2 / j3;
        } else {
            long j4 = ((j2 >>> 1) / j3) << 1;
            return j4 + (compare(j2 - (j4 * j3), j3) < 0 ? 0 : 1);
        }
    }

    public static long remainder(long j2, long j3) {
        if (j3 < 0) {
            return compare(j2, j3) < 0 ? j2 : j2 - j3;
        } else if (j2 >= 0) {
            return j2 % j3;
        } else {
            long j4 = j2 - ((((j2 >>> 1) / j3) << 1) * j3);
            if (compare(j4, j3) < 0) {
                j3 = 0;
            }
            return j4 - j3;
        }
    }

    public static long parseUnsignedLong(String str) {
        return parseUnsignedLong(str, 10);
    }

    public static long parseUnsignedLong(String str, int i2) {
        Preconditions.checkNotNull(str);
        if (str.length() != 0) {
            if (i2 < 2 || i2 > 36) {
                throw new NumberFormatException(new StringBuilder(26).append("illegal radix: ").append(i2).toString());
            }
            int i3 = ParseOverflowDetection.maxSafeDigits[i2] - 1;
            long j2 = 0;
            for (int i4 = 0; i4 < str.length(); i4++) {
                int digit = Character.digit(str.charAt(i4), i2);
                if (digit == -1) {
                    throw new NumberFormatException(str);
                }
                if (i4 > i3 && ParseOverflowDetection.overflowInParse(j2, digit, i2)) {
                    String valueOf = String.valueOf(str);
                    throw new NumberFormatException(valueOf.length() != 0 ? "Too large for unsigned long: ".concat(valueOf) : new String("Too large for unsigned long: "));
                }
                j2 = (j2 * i2) + digit;
            }
            return j2;
        }
        throw new NumberFormatException("empty string");
    }

    public static long decode(String str) {
        ParseRequest fromString = ParseRequest.fromString(str);
        try {
            return parseUnsignedLong(fromString.rawValue, fromString.radix);
        } catch (NumberFormatException e2) {
            String valueOf = String.valueOf(str);
            NumberFormatException numberFormatException = new NumberFormatException(valueOf.length() != 0 ? "Error parsing value: ".concat(valueOf) : new String("Error parsing value: "));
            numberFormatException.initCause(e2);
            throw numberFormatException;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class ParseOverflowDetection {
        static final long[] maxValueDivs = new long[37];
        static final int[] maxValueMods = new int[37];
        static final int[] maxSafeDigits = new int[37];

        private ParseOverflowDetection() {
        }

        static {
            BigInteger bigInteger = new BigInteger("10000000000000000", 16);
            for (int i2 = 2; i2 <= 36; i2++) {
                long j2 = i2;
                maxValueDivs[i2] = UnsignedLongs.divide(-1L, j2);
                maxValueMods[i2] = (int) UnsignedLongs.remainder(-1L, j2);
                maxSafeDigits[i2] = bigInteger.toString(i2).length() - 1;
            }
        }

        static boolean overflowInParse(long j2, int i2, int i3) {
            if (j2 >= 0) {
                long[] jArr = maxValueDivs;
                if (j2 < jArr[i3]) {
                    return false;
                }
                return j2 > jArr[i3] || i2 > maxValueMods[i3];
            }
            return true;
        }
    }

    public static String toString(long j2) {
        return toString(j2, 10);
    }

    public static String toString(long j2, int i2) {
        long divide;
        Preconditions.checkArgument(i2 >= 2 && i2 <= 36, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", i2);
        int i3 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i3 == 0) {
            return "0";
        }
        if (i3 > 0) {
            return Long.toString(j2, i2);
        }
        int i4 = 64;
        char[] cArr = new char[64];
        int i5 = i2 - 1;
        if ((i2 & i5) == 0) {
            int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i2);
            do {
                i4--;
                cArr[i4] = Character.forDigit(((int) j2) & i5, i2);
                j2 >>>= numberOfTrailingZeros;
            } while (j2 != 0);
        } else {
            if ((i2 & 1) == 0) {
                divide = (j2 >>> 1) / (i2 >>> 1);
            } else {
                divide = divide(j2, i2);
            }
            long j3 = i2;
            cArr[63] = Character.forDigit((int) (j2 - (divide * j3)), i2);
            i4 = 63;
            while (divide > 0) {
                i4--;
                cArr[i4] = Character.forDigit((int) (divide % j3), i2);
                divide /= j3;
            }
        }
        return new String(cArr, i4, 64 - i4);
    }
}
