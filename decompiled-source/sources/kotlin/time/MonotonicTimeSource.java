package kotlin.time;

import kotlin.Metadata;
import kotlin.time.TimeSource;
/* compiled from: MonoTimeSource.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ \u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u000bJ\u0018\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0013\u001a\u00020\u0006H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\b\u0010\u0016\u001a\u00020\u0004H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0019"}, d2 = {"Lkotlin/time/MonotonicTimeSource;", "Lkotlin/time/TimeSource$WithComparableMarks;", "()V", "zero", "", "adjustReading", "Lkotlin/time/TimeSource$Monotonic$ValueTimeMark;", "timeMark", "duration", "Lkotlin/time/Duration;", "adjustReading-6QKq23U", "(JJ)J", "differenceBetween", "one", "another", "differenceBetween-fRLX17w", "elapsedFrom", "elapsedFrom-6eNON_k", "(J)J", "markNow", "markNow-z9LOYto", "()J", "read", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class MonotonicTimeSource implements TimeSource.WithComparableMarks {
    public static final MonotonicTimeSource INSTANCE = new MonotonicTimeSource();
    private static final long zero = System.nanoTime();

    public String toString() {
        return "TimeSource(System.nanoTime())";
    }

    private MonotonicTimeSource() {
    }

    @Override // kotlin.time.TimeSource.WithComparableMarks, kotlin.time.TimeSource
    public /* bridge */ /* synthetic */ ComparableTimeMark markNow() {
        return TimeSource.Monotonic.ValueTimeMark.m2333boximpl(m2327markNowz9LOYto());
    }

    @Override // kotlin.time.TimeSource
    public /* bridge */ /* synthetic */ TimeMark markNow() {
        return TimeSource.Monotonic.ValueTimeMark.m2333boximpl(m2327markNowz9LOYto());
    }

    private final long read() {
        return System.nanoTime() - zero;
    }

    /* renamed from: markNow-z9LOYto  reason: not valid java name */
    public long m2327markNowz9LOYto() {
        return TimeSource.Monotonic.ValueTimeMark.m2336constructorimpl(read());
    }

    /* renamed from: elapsedFrom-6eNON_k  reason: not valid java name */
    public final long m2326elapsedFrom6eNON_k(long j2) {
        return LongSaturatedMathKt.saturatingDiff(read(), j2);
    }

    /* renamed from: differenceBetween-fRLX17w  reason: not valid java name */
    public final long m2325differenceBetweenfRLX17w(long j2, long j3) {
        return LongSaturatedMathKt.saturatingOriginsDiff(j2, j3);
    }

    /* renamed from: adjustReading-6QKq23U  reason: not valid java name */
    public final long m2324adjustReading6QKq23U(long j2, long j3) {
        return TimeSource.Monotonic.ValueTimeMark.m2336constructorimpl(LongSaturatedMathKt.m2322saturatingAddpTJri5U(j2, j3));
    }
}
