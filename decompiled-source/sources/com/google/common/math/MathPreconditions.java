package com.google.common.math;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigInteger;
import java.math.RoundingMode;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
final class MathPreconditions {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int checkPositive(String str, int i2) {
        if (i2 > 0) {
            return i2;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 26).append(str).append(" (").append(i2).append(") must be > 0").toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long checkPositive(String str, long j2) {
        if (j2 > 0) {
            return j2;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 35).append(str).append(" (").append(j2).append(") must be > 0").toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BigInteger checkPositive(String str, BigInteger bigInteger) {
        if (bigInteger.signum() > 0) {
            return bigInteger;
        }
        String valueOf = String.valueOf(bigInteger);
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 15 + String.valueOf(valueOf).length()).append(str).append(" (").append(valueOf).append(") must be > 0").toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int checkNonNegative(String str, int i2) {
        if (i2 >= 0) {
            return i2;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 27).append(str).append(" (").append(i2).append(") must be >= 0").toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long checkNonNegative(String str, long j2) {
        if (j2 >= 0) {
            return j2;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 36).append(str).append(" (").append(j2).append(") must be >= 0").toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BigInteger checkNonNegative(String str, BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            return bigInteger;
        }
        String valueOf = String.valueOf(bigInteger);
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 16 + String.valueOf(valueOf).length()).append(str).append(" (").append(valueOf).append(") must be >= 0").toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double checkNonNegative(String str, double d2) {
        if (d2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return d2;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 40).append(str).append(" (").append(d2).append(") must be >= 0").toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkRoundingUnnecessary(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkInRangeForRoundingInputs(boolean z, double d2, RoundingMode roundingMode) {
        if (z) {
            return;
        }
        String valueOf = String.valueOf(roundingMode);
        throw new ArithmeticException(new StringBuilder(String.valueOf(valueOf).length() + 83).append("rounded value is out of range for input ").append(d2).append(" and rounding mode ").append(valueOf).toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkNoOverflow(boolean z, String str, int i2, int i3) {
        if (!z) {
            throw new ArithmeticException(new StringBuilder(String.valueOf(str).length() + 36).append("overflow: ").append(str).append("(").append(i2).append(", ").append(i3).append(")").toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkNoOverflow(boolean z, String str, long j2, long j3) {
        if (!z) {
            throw new ArithmeticException(new StringBuilder(String.valueOf(str).length() + 54).append("overflow: ").append(str).append("(").append(j2).append(", ").append(j3).append(")").toString());
        }
    }

    private MathPreconditions() {
    }
}
