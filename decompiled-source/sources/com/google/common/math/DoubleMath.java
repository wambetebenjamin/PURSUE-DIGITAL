package com.google.common.math;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Iterator;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public final class DoubleMath {
    static final int MAX_FACTORIAL = 170;
    private static final double MAX_INT_AS_DOUBLE = 2.147483647E9d;
    private static final double MAX_LONG_AS_DOUBLE_PLUS_ONE = 9.223372036854776E18d;
    private static final double MIN_INT_AS_DOUBLE = -2.147483648E9d;
    private static final double MIN_LONG_AS_DOUBLE = -9.223372036854776E18d;
    private static final double LN_2 = Math.log(2.0d);
    static final double[] everySixteenthFactorial = {1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};

    static double roundIntermediate(double d2, RoundingMode roundingMode) {
        if (!DoubleUtils.isFinite(d2)) {
            throw new ArithmeticException("input is infinite or NaN");
        }
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isMathematicalInteger(d2));
                return d2;
            case 2:
                return (d2 >= 0.0d || isMathematicalInteger(d2)) ? d2 : ((long) d2) - 1;
            case 3:
                return (d2 <= 0.0d || isMathematicalInteger(d2)) ? d2 : ((long) d2) + 1;
            case 4:
                return d2;
            case 5:
                if (isMathematicalInteger(d2)) {
                    return d2;
                }
                return ((long) d2) + (d2 > 0.0d ? 1 : -1);
            case 6:
                return Math.rint(d2);
            case 7:
                double rint = Math.rint(d2);
                return Math.abs(d2 - rint) == 0.5d ? d2 + Math.copySign(0.5d, d2) : rint;
            case 8:
                double rint2 = Math.rint(d2);
                return Math.abs(d2 - rint2) == 0.5d ? d2 : rint2;
            default:
                throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.math.DoubleMath$1  reason: invalid class name */
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
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static int roundToInt(double d2, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d2, roundingMode);
        MathPreconditions.checkInRangeForRoundingInputs((roundIntermediate > -2.147483649E9d) & (roundIntermediate < 2.147483648E9d), d2, roundingMode);
        return (int) roundIntermediate;
    }

    public static long roundToLong(double d2, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d2, roundingMode);
        MathPreconditions.checkInRangeForRoundingInputs((MIN_LONG_AS_DOUBLE - roundIntermediate < 1.0d) & (roundIntermediate < MAX_LONG_AS_DOUBLE_PLUS_ONE), d2, roundingMode);
        return (long) roundIntermediate;
    }

    public static BigInteger roundToBigInteger(double d2, RoundingMode roundingMode) {
        double roundIntermediate = roundIntermediate(d2, roundingMode);
        if ((MIN_LONG_AS_DOUBLE - roundIntermediate < 1.0d) & (roundIntermediate < MAX_LONG_AS_DOUBLE_PLUS_ONE)) {
            return BigInteger.valueOf((long) roundIntermediate);
        }
        BigInteger shiftLeft = BigInteger.valueOf(DoubleUtils.getSignificand(roundIntermediate)).shiftLeft(Math.getExponent(roundIntermediate) - 52);
        return roundIntermediate < 0.0d ? shiftLeft.negate() : shiftLeft;
    }

    public static boolean isPowerOfTwo(double d2) {
        if (d2 <= 0.0d || !DoubleUtils.isFinite(d2)) {
            return false;
        }
        long significand = DoubleUtils.getSignificand(d2);
        return (significand & (significand - 1)) == 0;
    }

    public static double log2(double d2) {
        return Math.log(d2) / LN_2;
    }

    public static int log2(double d2, RoundingMode roundingMode) {
        boolean isPowerOfTwo;
        Preconditions.checkArgument(d2 > 0.0d && DoubleUtils.isFinite(d2), "x must be positive and finite");
        int exponent = Math.getExponent(d2);
        if (!DoubleUtils.isNormal(d2)) {
            return log2(d2 * 4.503599627370496E15d, roundingMode) - 52;
        }
        switch (AnonymousClass1.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(d2));
                break;
            case 2:
                break;
            case 3:
                r1 = !isPowerOfTwo(d2);
                break;
            case 4:
                r1 = exponent < 0;
                isPowerOfTwo = isPowerOfTwo(d2);
                r1 &= !isPowerOfTwo;
                break;
            case 5:
                r1 = exponent >= 0;
                isPowerOfTwo = isPowerOfTwo(d2);
                r1 &= !isPowerOfTwo;
                break;
            case 6:
            case 7:
            case 8:
                double scaleNormalize = DoubleUtils.scaleNormalize(d2);
                if (scaleNormalize * scaleNormalize > 2.0d) {
                    r1 = true;
                    break;
                }
                break;
            default:
                throw new AssertionError();
        }
        return r1 ? exponent + 1 : exponent;
    }

    public static boolean isMathematicalInteger(double d2) {
        return DoubleUtils.isFinite(d2) && (d2 == 0.0d || 52 - Long.numberOfTrailingZeros(DoubleUtils.getSignificand(d2)) <= Math.getExponent(d2));
    }

    public static double factorial(int i2) {
        MathPreconditions.checkNonNegative("n", i2);
        if (i2 > MAX_FACTORIAL) {
            return Double.POSITIVE_INFINITY;
        }
        double d2 = 1.0d;
        int i3 = i2 & (-16);
        while (true) {
            i3++;
            if (i3 > i2) {
                return d2 * everySixteenthFactorial[i2 >> 4];
            }
            d2 *= i3;
        }
    }

    public static boolean fuzzyEquals(double d2, double d3, double d4) {
        MathPreconditions.checkNonNegative("tolerance", d4);
        return Math.copySign(d2 - d3, 1.0d) <= d4 || d2 == d3 || (Double.isNaN(d2) && Double.isNaN(d3));
    }

    public static int fuzzyCompare(double d2, double d3, double d4) {
        if (fuzzyEquals(d2, d3, d4)) {
            return 0;
        }
        if (d2 < d3) {
            return -1;
        }
        if (d2 > d3) {
            return 1;
        }
        return Booleans.compare(Double.isNaN(d2), Double.isNaN(d3));
    }

    @Deprecated
    public static double mean(double... dArr) {
        Preconditions.checkArgument(dArr.length > 0, "Cannot take mean of 0 values");
        double checkFinite = checkFinite(dArr[0]);
        long j2 = 1;
        for (int i2 = 1; i2 < dArr.length; i2++) {
            checkFinite(dArr[i2]);
            j2++;
            checkFinite += (dArr[i2] - checkFinite) / j2;
        }
        return checkFinite;
    }

    @Deprecated
    public static double mean(int... iArr) {
        Preconditions.checkArgument(iArr.length > 0, "Cannot take mean of 0 values");
        long j2 = 0;
        for (int i2 : iArr) {
            j2 += i2;
        }
        return j2 / iArr.length;
    }

    @Deprecated
    public static double mean(long... jArr) {
        Preconditions.checkArgument(jArr.length > 0, "Cannot take mean of 0 values");
        double d2 = jArr[0];
        long j2 = 1;
        for (int i2 = 1; i2 < jArr.length; i2++) {
            j2++;
            d2 += (jArr[i2] - d2) / j2;
        }
        return d2;
    }

    @Deprecated
    public static double mean(Iterable<? extends Number> iterable) {
        return mean(iterable.iterator());
    }

    @Deprecated
    public static double mean(Iterator<? extends Number> it) {
        Preconditions.checkArgument(it.hasNext(), "Cannot take mean of 0 values");
        double checkFinite = checkFinite(it.next().doubleValue());
        long j2 = 1;
        while (it.hasNext()) {
            j2++;
            checkFinite += (checkFinite(it.next().doubleValue()) - checkFinite) / j2;
        }
        return checkFinite;
    }

    private static double checkFinite(double d2) {
        Preconditions.checkArgument(DoubleUtils.isFinite(d2));
        return d2;
    }

    private DoubleMath() {
    }
}
