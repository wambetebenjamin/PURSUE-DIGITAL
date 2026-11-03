package com.google.common.math;

import com.bytedance.frameworks.baselib.network.http.util.f;
import com.bytedance.ttgame.module.webview.api.WebViewConfig;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import com.google.common.primitives.UnsignedLongs;
import com.twitter.sdk.android.core.internal.TwitterApiConstants;
import java.math.RoundingMode;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public final class LongMath {
    static final long FLOOR_SQRT_MAX_LONG = 3037000499L;
    static final long MAX_POWER_OF_SQRT2_UNSIGNED = -5402926248376769404L;
    static final long MAX_SIGNED_POWER_OF_TWO = 4611686018427387904L;
    private static final int SIEVE_30 = -545925251;
    static final byte[] maxLog10ForLeadingZeros = {19, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, 17, 17, 17, Ascii.DLE, Ascii.DLE, Ascii.DLE, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SO, Ascii.SO, Ascii.SO, Ascii.CR, Ascii.CR, Ascii.CR, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.VT, Ascii.VT, Ascii.VT, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};
    static final long[] powersOf10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};
    static final long[] halfPowersOf10 = {3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
    static final long[] factorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    static final int[] biggestBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, 265, 206, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
    static final int[] biggestSimpleBinomials = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, f.m, 287, 214, 169, TwitterApiConstants.Errors.ALREADY_FAVORITED, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    private static final long[][] millerRabinBaseSets = {new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{Long.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};

    static boolean fitsInInt(long j2) {
        return ((long) ((int) j2)) == j2;
    }

    public static boolean isPowerOfTwo(long j2) {
        return (j2 > 0) & ((j2 & (j2 - 1)) == 0);
    }

    static int lessThanBranchFree(long j2, long j3) {
        return (int) ((~(~(j2 - j3))) >>> 63);
    }

    public static long mean(long j2, long j3) {
        return (j2 & j3) + ((j2 ^ j3) >> 1);
    }

    public static long saturatedAdd(long j2, long j3) {
        long j4 = j2 + j3;
        return (((j3 ^ j2) > 0L ? 1 : ((j3 ^ j2) == 0L ? 0 : -1)) < 0) | ((j2 ^ j4) >= 0) ? j4 : ((j4 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    public static long saturatedSubtract(long j2, long j3) {
        long j4 = j2 - j3;
        return (((j3 ^ j2) > 0L ? 1 : ((j3 ^ j2) == 0L ? 0 : -1)) >= 0) | ((j2 ^ j4) >= 0) ? j4 : ((j4 >>> 63) ^ 1) + Long.MAX_VALUE;
    }

    public static long ceilingPowerOfTwo(long j2) {
        MathPreconditions.checkPositive(WebViewConfig.LOCATION_X, j2);
        if (j2 > 4611686018427387904L) {
            throw new ArithmeticException(new StringBuilder(70).append("ceilingPowerOfTwo(").append(j2).append(") is not representable as a long").toString());
        }
        return 1 << (-Long.numberOfLeadingZeros(j2 - 1));
    }

    public static long floorPowerOfTwo(long j2) {
        MathPreconditions.checkPositive(WebViewConfig.LOCATION_X, j2);
        return 1 << (63 - Long.numberOfLeadingZeros(j2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.LongMath$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode;

        static {
            int[] iArr = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = iArr;
            try {
                iArr[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int log2(long j2, RoundingMode roundingMode) {
        MathPreconditions.checkPositive(WebViewConfig.LOCATION_X, j2);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(j2));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 64 - Long.numberOfLeadingZeros(j2 - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Long.numberOfLeadingZeros(j2);
                return (63 - numberOfLeadingZeros) + lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> numberOfLeadingZeros, j2);
            default:
                throw new AssertionError("impossible");
        }
        return 63 - Long.numberOfLeadingZeros(j2);
    }

    public static int log10(long j2, RoundingMode roundingMode) {
        int lessThanBranchFree;
        MathPreconditions.checkPositive(WebViewConfig.LOCATION_X, j2);
        int log10Floor = log10Floor(j2);
        long j3 = powersOf10[log10Floor];
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(j2 == j3);
                return log10Floor;
            case 2:
            case 3:
                return log10Floor;
            case 4:
            case 5:
                lessThanBranchFree = lessThanBranchFree(j3, j2);
                return log10Floor + lessThanBranchFree;
            case 6:
            case 7:
            case 8:
                lessThanBranchFree = lessThanBranchFree(halfPowersOf10[log10Floor], j2);
                return log10Floor + lessThanBranchFree;
            default:
                throw new AssertionError();
        }
    }

    static int log10Floor(long j2) {
        byte b2 = maxLog10ForLeadingZeros[Long.numberOfLeadingZeros(j2)];
        return b2 - lessThanBranchFree(j2, powersOf10[b2]);
    }

    public static long pow(long j2, int i2) {
        MathPreconditions.checkNonNegative("exponent", i2);
        if (-2 > j2 || j2 > 2) {
            long j3 = 1;
            while (i2 != 0) {
                if (i2 == 1) {
                    return j3 * j2;
                }
                j3 *= (i2 & 1) == 0 ? 1L : j2;
                j2 *= j2;
                i2 >>= 1;
            }
            return j3;
        }
        int i3 = (int) j2;
        if (i3 == -2) {
            if (i2 < 64) {
                return (i2 & 1) == 0 ? 1 << i2 : -(1 << i2);
            }
            return 0L;
        } else if (i3 == -1) {
            return (i2 & 1) == 0 ? 1L : -1L;
        } else if (i3 == 0) {
            return i2 == 0 ? 1L : 0L;
        } else if (i3 != 1) {
            if (i3 == 2) {
                if (i2 < 64) {
                    return 1 << i2;
                }
                return 0L;
            }
            throw new AssertionError();
        } else {
            return 1L;
        }
    }

    public static long sqrt(long j2, RoundingMode roundingMode) {
        MathPreconditions.checkNonNegative(WebViewConfig.LOCATION_X, j2);
        if (fitsInInt(j2)) {
            return IntMath.sqrt((int) j2, roundingMode);
        }
        long sqrt = (long) Math.sqrt(j2);
        long j3 = sqrt * sqrt;
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(j3 == j2);
                return sqrt;
            case 2:
            case 3:
                return j2 < j3 ? sqrt - 1 : sqrt;
            case 4:
            case 5:
                return j2 > j3 ? sqrt + 1 : sqrt;
            case 6:
            case 7:
            case 8:
                long j4 = sqrt - (j2 >= j3 ? 0 : 1);
                return j4 + lessThanBranchFree((j4 * j4) + j4, j2);
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004a, code lost:
        if (r9 > 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004d, code lost:
        if (r9 < 0) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long divide(long r9, long r11, java.math.RoundingMode r13) {
        /*
            com.google.common.base.Preconditions.checkNotNull(r13)
            long r0 = r9 / r11
            long r2 = r11 * r0
            long r2 = r9 - r2
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L10
            return r0
        L10:
            long r9 = r9 ^ r11
            r7 = 63
            long r9 = r9 >> r7
            int r9 = (int) r9
            r10 = 1
            r9 = r9 | r10
            int[] r7 = com.google.common.math.LongMath.AnonymousClass1.$SwitchMap$java$math$RoundingMode
            int r8 = r13.ordinal()
            r7 = r7[r8]
            r8 = 0
            switch(r7) {
                case 1: goto L50;
                case 2: goto L57;
                case 3: goto L4d;
                case 4: goto L58;
                case 5: goto L4a;
                case 6: goto L29;
                case 7: goto L29;
                case 8: goto L29;
                default: goto L23;
            }
        L23:
            java.lang.AssertionError r9 = new java.lang.AssertionError
            r9.<init>()
            throw r9
        L29:
            long r2 = java.lang.Math.abs(r2)
            long r11 = java.lang.Math.abs(r11)
            long r11 = r11 - r2
            long r2 = r2 - r11
            int r11 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r11 != 0) goto L47
            java.math.RoundingMode r11 = java.math.RoundingMode.HALF_UP
            if (r13 == r11) goto L58
            java.math.RoundingMode r11 = java.math.RoundingMode.HALF_EVEN
            if (r13 != r11) goto L57
            r11 = 1
            long r11 = r11 & r0
            int r11 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r11 == 0) goto L57
            goto L58
        L47:
            if (r11 <= 0) goto L57
            goto L58
        L4a:
            if (r9 <= 0) goto L57
            goto L58
        L4d:
            if (r9 >= 0) goto L57
            goto L58
        L50:
            if (r6 != 0) goto L53
            goto L54
        L53:
            r10 = r8
        L54:
            com.google.common.math.MathPreconditions.checkRoundingUnnecessary(r10)
        L57:
            r10 = r8
        L58:
            if (r10 == 0) goto L5c
            long r9 = (long) r9
            long r0 = r0 + r9
        L5c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.math.LongMath.divide(long, long, java.math.RoundingMode):long");
    }

    public static int mod(long j2, int i2) {
        return (int) mod(j2, i2);
    }

    public static long mod(long j2, long j3) {
        if (j3 <= 0) {
            throw new ArithmeticException("Modulus must be positive");
        }
        long j4 = j2 % j3;
        return j4 >= 0 ? j4 : j4 + j3;
    }

    public static long gcd(long j2, long j3) {
        MathPreconditions.checkNonNegative("a", j2);
        MathPreconditions.checkNonNegative("b", j3);
        if (j2 == 0) {
            return j3;
        }
        if (j3 == 0) {
            return j2;
        }
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j2);
        long j4 = j2 >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j3);
        long j5 = j3 >> numberOfTrailingZeros2;
        while (j4 != j5) {
            long j6 = j4 - j5;
            long j7 = (j6 >> 63) & j6;
            long j8 = (j6 - j7) - j7;
            j5 += j7;
            j4 = j8 >> Long.numberOfTrailingZeros(j8);
        }
        return j4 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    public static long checkedAdd(long j2, long j3) {
        long j4 = j2 + j3;
        MathPreconditions.checkNoOverflow(((j2 ^ j3) < 0) | ((j2 ^ j4) >= 0), "checkedAdd", j2, j3);
        return j4;
    }

    public static long checkedSubtract(long j2, long j3) {
        long j4 = j2 - j3;
        MathPreconditions.checkNoOverflow(((j2 ^ j3) >= 0) | ((j2 ^ j4) >= 0), "checkedSubtract", j2, j3);
        return j4;
    }

    public static long checkedMultiply(long j2, long j3) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j2) + Long.numberOfLeadingZeros(j3) + Long.numberOfLeadingZeros(~j3);
        if (numberOfLeadingZeros > 65) {
            return j2 * j3;
        }
        MathPreconditions.checkNoOverflow(numberOfLeadingZeros >= 64, "checkedMultiply", j2, j3);
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        MathPreconditions.checkNoOverflow((i2 >= 0) | (j3 != Long.MIN_VALUE), "checkedMultiply", j2, j3);
        long j4 = j2 * j3;
        MathPreconditions.checkNoOverflow(i2 == 0 || j4 / j2 == j3, "checkedMultiply", j2, j3);
        return j4;
    }

    public static long checkedPow(long j2, int i2) {
        MathPreconditions.checkNonNegative("exponent", i2);
        long j3 = 1;
        if ((j2 >= -2) && (j2 <= 2)) {
            int i3 = (int) j2;
            if (i3 == -2) {
                MathPreconditions.checkNoOverflow(i2 < 64, "checkedPow", j2, i2);
                return (i2 & 1) == 0 ? 1 << i2 : (-1) << i2;
            } else if (i3 == -1) {
                return (i2 & 1) == 0 ? 1L : -1L;
            } else if (i3 == 0) {
                return i2 == 0 ? 1L : 0L;
            } else if (i3 != 1) {
                if (i3 == 2) {
                    MathPreconditions.checkNoOverflow(i2 < 63, "checkedPow", j2, i2);
                    return 1 << i2;
                }
                throw new AssertionError();
            } else {
                return 1L;
            }
        }
        long j4 = j2;
        int i4 = i2;
        while (i4 != 0) {
            if (i4 == 1) {
                return checkedMultiply(j3, j4);
            }
            if ((i4 & 1) != 0) {
                j3 = checkedMultiply(j3, j4);
            }
            long j5 = j3;
            int i5 = i4 >> 1;
            if (i5 > 0) {
                MathPreconditions.checkNoOverflow(-3037000499L <= j4 && j4 <= FLOOR_SQRT_MAX_LONG, "checkedPow", j4, i5);
                j4 *= j4;
            }
            i4 = i5;
            j3 = j5;
        }
        return j3;
    }

    public static long saturatedMultiply(long j2, long j3) {
        int numberOfLeadingZeros = Long.numberOfLeadingZeros(j2) + Long.numberOfLeadingZeros(~j2) + Long.numberOfLeadingZeros(j3) + Long.numberOfLeadingZeros(~j3);
        if (numberOfLeadingZeros > 65) {
            return j2 * j3;
        }
        long j4 = ((j2 ^ j3) >>> 63) + Long.MAX_VALUE;
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if ((numberOfLeadingZeros < 64) || ((j3 == Long.MIN_VALUE) & (i2 < 0))) {
            return j4;
        }
        long j5 = j2 * j3;
        return (i2 == 0 || j5 / j2 == j3) ? j5 : j4;
    }

    public static long saturatedPow(long j2, int i2) {
        MathPreconditions.checkNonNegative("exponent", i2);
        long j3 = 1;
        if (!(j2 >= -2) || !(j2 <= 2)) {
            long j4 = ((j2 >>> 63) & i2 & 1) + Long.MAX_VALUE;
            while (i2 != 0) {
                if (i2 == 1) {
                    return saturatedMultiply(j3, j2);
                }
                if ((i2 & 1) != 0) {
                    j3 = saturatedMultiply(j3, j2);
                }
                i2 >>= 1;
                if (i2 > 0) {
                    if ((-3037000499L > j2) || (j2 > FLOOR_SQRT_MAX_LONG)) {
                        return j4;
                    }
                    j2 *= j2;
                }
            }
            return j3;
        }
        int i3 = (int) j2;
        if (i3 == -2) {
            return i2 >= 64 ? (i2 & 1) + Long.MAX_VALUE : (i2 & 1) == 0 ? 1 << i2 : (-1) << i2;
        } else if (i3 == -1) {
            return (i2 & 1) == 0 ? 1L : -1L;
        } else if (i3 == 0) {
            return i2 == 0 ? 1L : 0L;
        } else if (i3 != 1) {
            if (i3 == 2) {
                if (i2 >= 63) {
                    return Long.MAX_VALUE;
                }
                return 1 << i2;
            }
            throw new AssertionError();
        } else {
            return 1L;
        }
    }

    public static long factorial(int i2) {
        MathPreconditions.checkNonNegative("n", i2);
        long[] jArr = factorials;
        if (i2 < jArr.length) {
            return jArr[i2];
        }
        return Long.MAX_VALUE;
    }

    public static long binomial(int i2, int i3) {
        MathPreconditions.checkNonNegative("n", i2);
        MathPreconditions.checkNonNegative("k", i3);
        Preconditions.checkArgument(i3 <= i2, "k (%s) > n (%s)", i3, i2);
        if (i3 > (i2 >> 1)) {
            i3 = i2 - i3;
        }
        long j2 = 1;
        if (i3 != 0) {
            if (i3 != 1) {
                long[] jArr = factorials;
                if (i2 < jArr.length) {
                    return jArr[i2] / (jArr[i3] * jArr[i2 - i3]);
                }
                int[] iArr = biggestBinomials;
                if (i3 >= iArr.length || i2 > iArr[i3]) {
                    return Long.MAX_VALUE;
                }
                int[] iArr2 = biggestSimpleBinomials;
                if (i3 < iArr2.length && i2 <= iArr2[i3]) {
                    int i4 = i2 - 1;
                    long j3 = i2;
                    for (int i5 = 2; i5 <= i3; i5++) {
                        j3 = (j3 * i4) / i5;
                        i4--;
                    }
                    return j3;
                }
                long j4 = i2;
                int log2 = log2(j4, RoundingMode.CEILING);
                int i6 = i2 - 1;
                int i7 = log2;
                int i8 = 2;
                long j5 = j4;
                long j6 = 1;
                while (i8 <= i3) {
                    i7 += log2;
                    if (i7 < 63) {
                        j5 *= i6;
                        j6 *= i8;
                    } else {
                        j2 = multiplyFraction(j2, j5, j6);
                        j5 = i6;
                        j6 = i8;
                        i7 = log2;
                    }
                    i8++;
                    i6--;
                }
                return multiplyFraction(j2, j5, j6);
            }
            return i2;
        }
        return 1L;
    }

    static long multiplyFraction(long j2, long j3, long j4) {
        if (j2 == 1) {
            return j3 / j4;
        }
        long gcd = gcd(j2, j4);
        return (j2 / gcd) * (j3 / (j4 / gcd));
    }

    public static boolean isPrime(long j2) {
        long[][] jArr;
        if (j2 < 2) {
            MathPreconditions.checkNonNegative("n", j2);
            return false;
        } else if (j2 < 66) {
            return ((722865708377213483 >> (((int) j2) + (-2))) & 1) != 0;
        } else if ((SIEVE_30 & (1 << ((int) (j2 % 30)))) != 0 || j2 % 7 == 0 || j2 % 11 == 0 || j2 % 13 == 0) {
            return false;
        } else {
            if (j2 < 289) {
                return true;
            }
            for (long[] jArr2 : millerRabinBaseSets) {
                if (j2 <= jArr2[0]) {
                    for (int i2 = 1; i2 < jArr2.length; i2++) {
                        if (!MillerRabinTester.test(jArr2[i2], j2)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            throw new AssertionError();
        }
    }

    /* loaded from: classes9.dex */
    private enum MillerRabinTester {
        SMALL { // from class: com.google.common.math.LongMath.MillerRabinTester.1
            @Override // com.google.common.math.LongMath.MillerRabinTester
            long mulMod(long j2, long j3, long j4) {
                return (j2 * j3) % j4;
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            long squareMod(long j2, long j3) {
                return (j2 * j2) % j3;
            }
        },
        LARGE { // from class: com.google.common.math.LongMath.MillerRabinTester.2
            private long plusMod(long j2, long j3, long j4) {
                int i2 = (j2 > (j4 - j3) ? 1 : (j2 == (j4 - j3) ? 0 : -1));
                long j5 = j2 + j3;
                return i2 >= 0 ? j5 - j4 : j5;
            }

            private long times2ToThe32Mod(long j2, long j3) {
                int i2 = 32;
                do {
                    int min = Math.min(i2, Long.numberOfLeadingZeros(j2));
                    j2 = UnsignedLongs.remainder(j2 << min, j3);
                    i2 -= min;
                } while (i2 > 0);
                return j2;
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            long mulMod(long j2, long j3, long j4) {
                long j5 = j2 >>> 32;
                long j6 = j3 >>> 32;
                long j7 = j2 & 4294967295L;
                long j8 = j3 & 4294967295L;
                long times2ToThe32Mod = times2ToThe32Mod(j5 * j6, j4) + (j5 * j8);
                if (times2ToThe32Mod < 0) {
                    times2ToThe32Mod = UnsignedLongs.remainder(times2ToThe32Mod, j4);
                }
                return plusMod(times2ToThe32Mod(times2ToThe32Mod + (j6 * j7), j4), UnsignedLongs.remainder(j7 * j8, j4), j4);
            }

            @Override // com.google.common.math.LongMath.MillerRabinTester
            long squareMod(long j2, long j3) {
                long j4 = j2 >>> 32;
                long j5 = j2 & 4294967295L;
                long times2ToThe32Mod = times2ToThe32Mod(j4 * j4, j3);
                long j6 = j4 * j5 * 2;
                if (j6 < 0) {
                    j6 = UnsignedLongs.remainder(j6, j3);
                }
                return plusMod(times2ToThe32Mod(times2ToThe32Mod + j6, j3), UnsignedLongs.remainder(j5 * j5, j3), j3);
            }
        };

        abstract long mulMod(long j2, long j3, long j4);

        abstract long squareMod(long j2, long j3);

        /* synthetic */ MillerRabinTester(AnonymousClass1 anonymousClass1) {
            this();
        }

        static boolean test(long j2, long j3) {
            return (j3 <= LongMath.FLOOR_SQRT_MAX_LONG ? SMALL : LARGE).testWitness(j2, j3);
        }

        private long powMod(long j2, long j3, long j4) {
            long j5 = 1;
            while (j3 != 0) {
                if ((j3 & 1) != 0) {
                    j5 = mulMod(j5, j2, j4);
                }
                j2 = squareMod(j2, j4);
                j3 >>= 1;
            }
            return j5;
        }

        private boolean testWitness(long j2, long j3) {
            long j4 = j3 - 1;
            int numberOfTrailingZeros = Long.numberOfTrailingZeros(j4);
            long j5 = j4 >> numberOfTrailingZeros;
            long j6 = j2 % j3;
            if (j6 == 0) {
                return true;
            }
            long powMod = powMod(j6, j5, j3);
            if (powMod == 1) {
                return true;
            }
            int i2 = 0;
            while (powMod != j4) {
                i2++;
                if (i2 == numberOfTrailingZeros) {
                    return false;
                }
                powMod = squareMod(powMod, j3);
            }
            return true;
        }
    }

    public static double roundToDouble(long j2, RoundingMode roundingMode) {
        long j3;
        double d2;
        double d3 = j2;
        long j4 = (long) d3;
        int compare = j4 == Long.MAX_VALUE ? -1 : Longs.compare(j2, j4);
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(compare == 0);
                return d3;
            case 2:
                return j2 >= 0 ? compare >= 0 ? d3 : DoubleUtils.nextDown(d3) : compare <= 0 ? d3 : Math.nextUp(d3);
            case 3:
                return compare >= 0 ? d3 : DoubleUtils.nextDown(d3);
            case 4:
                return j2 >= 0 ? compare <= 0 ? d3 : Math.nextUp(d3) : compare >= 0 ? d3 : DoubleUtils.nextDown(d3);
            case 5:
                return compare <= 0 ? d3 : Math.nextUp(d3);
            case 6:
            case 7:
            case 8:
                if (compare >= 0) {
                    d2 = Math.nextUp(d3);
                    j3 = (long) Math.ceil(d2);
                } else {
                    double nextDown = DoubleUtils.nextDown(d3);
                    j4 = (long) Math.floor(nextDown);
                    j3 = j4;
                    d3 = nextDown;
                    d2 = d3;
                }
                long j5 = j2 - j4;
                long j6 = j3 - j2;
                if (j3 == Long.MAX_VALUE) {
                    j6++;
                }
                int compare2 = Longs.compare(j5, j6);
                if (compare2 < 0) {
                    return d3;
                }
                if (compare2 > 0) {
                    return d2;
                }
                int i2 = AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()];
                if (i2 == 6) {
                    return j2 >= 0 ? d3 : d2;
                } else if (i2 == 7) {
                    return j2 >= 0 ? d2 : d3;
                } else if (i2 == 8) {
                    return (DoubleUtils.getSignificand(d3) & 1) == 0 ? d3 : d2;
                } else {
                    throw new AssertionError("impossible");
                }
            default:
                throw new AssertionError("impossible");
        }
    }

    private LongMath() {
    }
}
