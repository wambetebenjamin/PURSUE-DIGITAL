package kotlin.ranges;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ULongRange.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001c2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00030\u0004:\u0001\u001cB\u0018\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\u001b\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016R \u0010\b\u001a\u00020\u00038VX\u0097\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\u001a\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001d"}, d2 = {"Lkotlin/ranges/ULongRange;", "Lkotlin/ranges/ULongProgression;", "Lkotlin/ranges/ClosedRange;", "Lkotlin/ULong;", "Lkotlin/ranges/OpenEndRange;", "start", "endInclusive", "(JJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "endExclusive", "getEndExclusive-s-VKNKU$annotations", "()V", "getEndExclusive-s-VKNKU", "()J", "getEndInclusive-s-VKNKU", "getStart-s-VKNKU", "contains", "", "value", "contains-VKZWuLQ", "(J)Z", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ULongRange extends ULongProgression implements ClosedRange<ULong>, OpenEndRange<ULong> {
    public static final Companion Companion = new Companion(null);
    private static final ULongRange EMPTY = new ULongRange(-1, 0, null);

    public /* synthetic */ ULongRange(long j2, long j3, DefaultConstructorMarker defaultConstructorMarker) {
        this(j2, j3);
    }

    @Deprecated(message = "Can throw an exception when it's impossible to represent the value with ULong type, for example, when the range includes MAX_VALUE. It's recommended to use 'endInclusive' property that doesn't throw.")
    /* renamed from: getEndExclusive-s-VKNKU$annotations  reason: not valid java name */
    public static /* synthetic */ void m2116getEndExclusivesVKNKU$annotations() {
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ boolean contains(ULong uLong) {
        return m2117containsVKZWuLQ(uLong.m1094unboximpl());
    }

    @Override // kotlin.ranges.OpenEndRange
    public /* bridge */ /* synthetic */ ULong getEndExclusive() {
        return ULong.m1036boximpl(m2118getEndExclusivesVKNKU());
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ ULong getEndInclusive() {
        return ULong.m1036boximpl(m2119getEndInclusivesVKNKU());
    }

    @Override // kotlin.ranges.ClosedRange
    public /* bridge */ /* synthetic */ ULong getStart() {
        return ULong.m1036boximpl(m2120getStartsVKNKU());
    }

    private ULongRange(long j2, long j3) {
        super(j2, j3, 1L, null);
    }

    /* renamed from: getStart-s-VKNKU  reason: not valid java name */
    public long m2120getStartsVKNKU() {
        return m2112getFirstsVKNKU();
    }

    /* renamed from: getEndInclusive-s-VKNKU  reason: not valid java name */
    public long m2119getEndInclusivesVKNKU() {
        return m2113getLastsVKNKU();
    }

    /* renamed from: getEndExclusive-s-VKNKU  reason: not valid java name */
    public long m2118getEndExclusivesVKNKU() {
        if (m2113getLastsVKNKU() == -1) {
            throw new IllegalStateException("Cannot return the exclusive upper bound of a range that includes MAX_VALUE.".toString());
        }
        return ULong.m1042constructorimpl(m2113getLastsVKNKU() + ULong.m1042constructorimpl(1 & 4294967295L));
    }

    /* renamed from: contains-VKZWuLQ  reason: not valid java name */
    public boolean m2117containsVKZWuLQ(long j2) {
        int compare;
        int compare2;
        compare = Long.compare(m2112getFirstsVKNKU() ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
        if (compare <= 0) {
            compare2 = Long.compare(j2 ^ Long.MIN_VALUE, m2113getLastsVKNKU() ^ Long.MIN_VALUE);
            if (compare2 <= 0) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlin.ranges.ULongProgression, kotlin.ranges.ClosedRange
    public boolean isEmpty() {
        int compare;
        compare = Long.compare(m2112getFirstsVKNKU() ^ Long.MIN_VALUE, m2113getLastsVKNKU() ^ Long.MIN_VALUE);
        return compare > 0;
    }

    @Override // kotlin.ranges.ULongProgression
    public boolean equals(Object obj) {
        if (obj instanceof ULongRange) {
            if (!isEmpty() || !((ULongRange) obj).isEmpty()) {
                ULongRange uLongRange = (ULongRange) obj;
                if (m2112getFirstsVKNKU() != uLongRange.m2112getFirstsVKNKU() || m2113getLastsVKNKU() != uLongRange.m2113getLastsVKNKU()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.ULongProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((int) ULong.m1042constructorimpl(m2112getFirstsVKNKU() ^ ULong.m1042constructorimpl(m2112getFirstsVKNKU() >>> 32))) * 31) + ((int) ULong.m1042constructorimpl(m2113getLastsVKNKU() ^ ULong.m1042constructorimpl(m2113getLastsVKNKU() >>> 32)));
    }

    @Override // kotlin.ranges.ULongProgression
    public String toString() {
        return ((Object) ULong.m1088toStringimpl(m2112getFirstsVKNKU())) + ".." + ((Object) ULong.m1088toStringimpl(m2113getLastsVKNKU()));
    }

    /* compiled from: ULongRange.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/ranges/ULongRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/ULongRange;", "getEMPTY", "()Lkotlin/ranges/ULongRange;", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ULongRange getEMPTY() {
            return ULongRange.EMPTY;
        }
    }
}
