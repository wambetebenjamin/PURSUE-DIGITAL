package kotlin.time;

import kotlin.Metadata;
import kotlin.time.Duration;
/* compiled from: longSaturatedMath.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a\"\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a\"\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\n\u001a \u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a \u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a \u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a\r\u0010\u0016\u001a\u00020\u0017*\u00020\u0001H\u0082\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"checkInfiniteSumDefined", "", "longNs", "duration", "Lkotlin/time/Duration;", "durationNs", "checkInfiniteSumDefined-PjuGub4", "(JJJ)J", "saturatingAdd", "saturatingAdd-pTJri5U", "(JJ)J", "saturatingAddInHalves", "saturatingAddInHalves-pTJri5U", "saturatingDiff", "valueNs", "originNs", "saturatingFiniteDiff", "value1Ns", "value2Ns", "saturatingOriginsDiff", "origin1Ns", "origin2Ns", "isSaturated", "", "kotlin-stdlib"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class LongSaturatedMathKt {
    private static final boolean isSaturated(long j2) {
        return ((j2 - 1) | 1) == Long.MAX_VALUE;
    }

    /* renamed from: saturatingAdd-pTJri5U  reason: not valid java name */
    public static final long m2322saturatingAddpTJri5U(long j2, long j3) {
        long m2214getInWholeNanosecondsimpl = Duration.m2214getInWholeNanosecondsimpl(j3);
        if (((j2 - 1) | 1) == Long.MAX_VALUE) {
            return m2321checkInfiniteSumDefinedPjuGub4(j2, j3, m2214getInWholeNanosecondsimpl);
        }
        if ((1 | (m2214getInWholeNanosecondsimpl - 1)) == Long.MAX_VALUE) {
            return m2323saturatingAddInHalvespTJri5U(j2, j3);
        }
        long j4 = j2 + m2214getInWholeNanosecondsimpl;
        return ((j2 ^ j4) & (m2214getInWholeNanosecondsimpl ^ j4)) < 0 ? j2 < 0 ? Long.MIN_VALUE : Long.MAX_VALUE : j4;
    }

    /* renamed from: checkInfiniteSumDefined-PjuGub4  reason: not valid java name */
    private static final long m2321checkInfiniteSumDefinedPjuGub4(long j2, long j3, long j4) {
        if (!Duration.m2226isInfiniteimpl(j3) || (j2 ^ j4) >= 0) {
            return j2;
        }
        throw new IllegalArgumentException("Summing infinities of different signs");
    }

    /* renamed from: saturatingAddInHalves-pTJri5U  reason: not valid java name */
    private static final long m2323saturatingAddInHalvespTJri5U(long j2, long j3) {
        long m2197divUwyO8pc = Duration.m2197divUwyO8pc(j3, 2);
        if (((Duration.m2214getInWholeNanosecondsimpl(m2197divUwyO8pc) - 1) | 1) == Long.MAX_VALUE) {
            return (long) (j2 + Duration.m2237toDoubleimpl(j3, DurationUnit.NANOSECONDS));
        }
        return m2322saturatingAddpTJri5U(m2322saturatingAddpTJri5U(j2, m2197divUwyO8pc), Duration.m2229minusLRDsOJo(j3, m2197divUwyO8pc));
    }

    public static final long saturatingDiff(long j2, long j3) {
        if ((1 | (j3 - 1)) == Long.MAX_VALUE) {
            return Duration.m2246unaryMinusUwyO8pc(DurationKt.toDuration(j3, DurationUnit.DAYS));
        }
        return saturatingFiniteDiff(j2, j3);
    }

    public static final long saturatingOriginsDiff(long j2, long j3) {
        if (((j3 - 1) | 1) == Long.MAX_VALUE) {
            if (j2 == j3) {
                return Duration.Companion.m2296getZEROUwyO8pc();
            }
            return Duration.m2246unaryMinusUwyO8pc(DurationKt.toDuration(j3, DurationUnit.DAYS));
        }
        if ((1 | (j2 - 1)) == Long.MAX_VALUE) {
            return DurationKt.toDuration(j2, DurationUnit.DAYS);
        }
        return saturatingFiniteDiff(j2, j3);
    }

    private static final long saturatingFiniteDiff(long j2, long j3) {
        long j4 = j2 - j3;
        if (((j4 ^ j2) & (~(j4 ^ j3))) < 0) {
            long j5 = 1000000;
            long j6 = (j2 % j5) - (j3 % j5);
            Duration.Companion companion = Duration.Companion;
            long duration = DurationKt.toDuration((j2 / j5) - (j3 / j5), DurationUnit.MILLISECONDS);
            Duration.Companion companion2 = Duration.Companion;
            return Duration.m2230plusLRDsOJo(duration, DurationKt.toDuration(j6, DurationUnit.NANOSECONDS));
        }
        Duration.Companion companion3 = Duration.Companion;
        return DurationKt.toDuration(j4, DurationUnit.NANOSECONDS);
    }
}
