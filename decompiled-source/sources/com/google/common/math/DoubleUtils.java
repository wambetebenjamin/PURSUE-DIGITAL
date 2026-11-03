package com.google.common.math;

import com.google.common.base.Preconditions;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigInteger;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
final class DoubleUtils {
    static final int EXPONENT_BIAS = 1023;
    static final long EXPONENT_MASK = 9218868437227405312L;
    static final long IMPLICIT_BIT = 4503599627370496L;
    static final long ONE_BITS = 4607182418800017408L;
    static final int SIGNIFICAND_BITS = 52;
    static final long SIGNIFICAND_MASK = 4503599627370495L;
    static final long SIGN_MASK = Long.MIN_VALUE;

    private DoubleUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double nextDown(double d2) {
        return -Math.nextUp(-d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getSignificand(double d2) {
        Preconditions.checkArgument(isFinite(d2), "not a normal value");
        int exponent = Math.getExponent(d2);
        long doubleToRawLongBits = Double.doubleToRawLongBits(d2) & SIGNIFICAND_MASK;
        return exponent == -1023 ? doubleToRawLongBits << 1 : doubleToRawLongBits | IMPLICIT_BIT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isFinite(double d2) {
        return Math.getExponent(d2) <= 1023;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isNormal(double d2) {
        return Math.getExponent(d2) >= -1022;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double scaleNormalize(double d2) {
        return Double.longBitsToDouble((Double.doubleToRawLongBits(d2) & SIGNIFICAND_MASK) | ONE_BITS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double bigToDouble(BigInteger bigInteger) {
        BigInteger abs = bigInteger.abs();
        boolean z = true;
        int bitLength = abs.bitLength() - 1;
        if (bitLength < 63) {
            return bigInteger.longValue();
        }
        if (bitLength > 1023) {
            return bigInteger.signum() * Double.POSITIVE_INFINITY;
        }
        int i2 = (bitLength - 52) - 1;
        long longValue = abs.shiftRight(i2).longValue();
        long j2 = (longValue >> 1) & SIGNIFICAND_MASK;
        if ((longValue & 1) == 0 || ((j2 & 1) == 0 && abs.getLowestSetBit() >= i2)) {
            z = false;
        }
        if (z) {
            j2++;
        }
        return Double.longBitsToDouble((((bitLength + 1023) << 52) + j2) | (bigInteger.signum() & Long.MIN_VALUE));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double ensureNonNegative(double d2) {
        Preconditions.checkArgument(!Double.isNaN(d2));
        return Math.max(d2, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }
}
