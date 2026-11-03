package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;
/* compiled from: UInt.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0087@\u0018\u0000 {2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001{B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0000H\u0097\nø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u000bJ\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0005J\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u000fJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u000bJ\u001b\u0010\u0019\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001b\u0010\u0019\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u0016J\u001a\u0010\u001f\u001a\u00020 2\b\u0010\t\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b\"\u0010#J\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b%\u0010\u000fJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b&\u0010\u000bJ\u001b\u0010$\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b'\u0010\u001dJ\u001b\u0010$\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b(\u0010\u0016J\u0010\u0010)\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b*\u0010\u0005J\u0016\u0010+\u001a\u00020\u0000H\u0087\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010\u0005J\u0016\u0010-\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b.\u0010\u0005J\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b0\u0010\u000fJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b1\u0010\u000bJ\u001b\u0010/\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b2\u0010\u001dJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\b3\u0010\u0016J\u001b\u00104\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\rH\u0087\bø\u0001\u0000¢\u0006\u0004\b5\u00106J\u001b\u00104\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\bø\u0001\u0000¢\u0006\u0004\b7\u0010\u000bJ\u001b\u00104\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\bø\u0001\u0000¢\u0006\u0004\b8\u0010\u001dJ\u001b\u00104\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0087\bø\u0001\u0000¢\u0006\u0004\b9\u0010:J\u001b\u0010;\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\b<\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\b>\u0010\u000fJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010\u000bJ\u001b\u0010=\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\b@\u0010\u001dJ\u001b\u0010=\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bA\u0010\u0016J\u001b\u0010B\u001a\u00020C2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bD\u0010EJ\u001b\u0010F\u001a\u00020C2\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bG\u0010EJ\u001b\u0010H\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bI\u0010\u000fJ\u001b\u0010H\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bJ\u0010\u000bJ\u001b\u0010H\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bK\u0010\u001dJ\u001b\u0010H\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bL\u0010\u0016J\u001e\u0010M\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u0003H\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010\u000bJ\u001e\u0010P\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u0003H\u0087\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bQ\u0010\u000bJ\u001b\u0010R\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\rH\u0087\nø\u0001\u0000¢\u0006\u0004\bS\u0010\u000fJ\u001b\u0010R\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\nø\u0001\u0000¢\u0006\u0004\bT\u0010\u000bJ\u001b\u0010R\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0011H\u0087\nø\u0001\u0000¢\u0006\u0004\bU\u0010\u001dJ\u001b\u0010R\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0014H\u0087\nø\u0001\u0000¢\u0006\u0004\bV\u0010\u0016J\u0010\u0010W\u001a\u00020XH\u0087\b¢\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001a\u00020\\H\u0087\b¢\u0006\u0004\b]\u0010^J\u0010\u0010_\u001a\u00020`H\u0087\b¢\u0006\u0004\ba\u0010bJ\u0010\u0010c\u001a\u00020\u0003H\u0087\b¢\u0006\u0004\bd\u0010\u0005J\u0010\u0010e\u001a\u00020fH\u0087\b¢\u0006\u0004\bg\u0010hJ\u0010\u0010i\u001a\u00020jH\u0087\b¢\u0006\u0004\bk\u0010lJ\u000f\u0010m\u001a\u00020nH\u0016¢\u0006\u0004\bo\u0010pJ\u0016\u0010q\u001a\u00020\rH\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\br\u0010ZJ\u0016\u0010s\u001a\u00020\u0000H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bt\u0010\u0005J\u0016\u0010u\u001a\u00020\u0011H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bv\u0010hJ\u0016\u0010w\u001a\u00020\u0014H\u0087\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bx\u0010lJ\u001b\u0010y\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0087\fø\u0001\u0000¢\u0006\u0004\bz\u0010\u000bR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006|"}, d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-pVg5ArA", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(ILjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "inc", "inc-pVg5ArA", "inv", "inv-pVg5ArA", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(IB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(IS)S", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-WZ4Q5Ns", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toDouble", "", "toDouble-impl", "(I)D", "toFloat", "", "toFloat-impl", "(I)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* loaded from: classes10.dex */
public final class UInt implements Comparable<UInt> {
    public static final Companion Companion = new Companion(null);
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UInt m957boximpl(int i2) {
        return new UInt(i2);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static int m963constructorimpl(int i2) {
        return i2;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m969equalsimpl(int i2, Object obj) {
        return (obj instanceof UInt) && i2 == ((UInt) obj).m1015unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m970equalsimpl0(int i2, int i3) {
        return i2 == i3;
    }

    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m975hashCodeimpl(int i2) {
        return Integer.hashCode(i2);
    }

    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m1003toByteimpl(int i2) {
        return (byte) i2;
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m1006toIntimpl(int i2) {
        return i2;
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m1007toLongimpl(int i2) {
        return i2 & 4294967295L;
    }

    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m1008toShortimpl(int i2) {
        return (short) i2;
    }

    /* renamed from: toUInt-pVg5ArA  reason: not valid java name */
    private static final int m1011toUIntpVg5ArA(int i2) {
        return i2;
    }

    public boolean equals(Object obj) {
        return m969equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m975hashCodeimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m1015unboximpl() {
        return this.data;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return UnsignedKt.uintCompare(m1015unboximpl(), uInt.m1015unboximpl());
    }

    private /* synthetic */ UInt(int i2) {
        this.data = i2;
    }

    /* compiled from: UInt.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001a\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"}, d2 = {"Lkotlin/UInt$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UInt;", "I", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static final int m958compareTo7apg3OU(int i2, byte b2) {
        int compare;
        compare = Integer.compare(i2 ^ Integer.MIN_VALUE, m963constructorimpl(b2 & 255) ^ Integer.MIN_VALUE);
        return compare;
    }

    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m962compareToxj2QHRw(int i2, short s) {
        int compare;
        compare = Integer.compare(i2 ^ Integer.MIN_VALUE, m963constructorimpl(s & UShort.MAX_VALUE) ^ Integer.MIN_VALUE);
        return compare;
    }

    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private int m960compareToWZ4Q5Ns(int i2) {
        return UnsignedKt.uintCompare(m1015unboximpl(), i2);
    }

    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static int m961compareToWZ4Q5Ns(int i2, int i3) {
        return UnsignedKt.uintCompare(i2, i3);
    }

    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static final int m959compareToVKZWuLQ(int i2, long j2) {
        int compare;
        compare = Long.compare(ULong.m1042constructorimpl(i2 & 4294967295L) ^ Long.MIN_VALUE, j2 ^ Long.MIN_VALUE);
        return compare;
    }

    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final int m987plus7apg3OU(int i2, byte b2) {
        return m963constructorimpl(i2 + m963constructorimpl(b2 & 255));
    }

    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final int m990plusxj2QHRw(int i2, short s) {
        return m963constructorimpl(i2 + m963constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final int m989plusWZ4Q5Ns(int i2, int i3) {
        return m963constructorimpl(i2 + i3);
    }

    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m988plusVKZWuLQ(int i2, long j2) {
        return ULong.m1042constructorimpl(ULong.m1042constructorimpl(i2 & 4294967295L) + j2);
    }

    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final int m978minus7apg3OU(int i2, byte b2) {
        return m963constructorimpl(i2 - m963constructorimpl(b2 & 255));
    }

    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final int m981minusxj2QHRw(int i2, short s) {
        return m963constructorimpl(i2 - m963constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final int m980minusWZ4Q5Ns(int i2, int i3) {
        return m963constructorimpl(i2 - i3);
    }

    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m979minusVKZWuLQ(int i2, long j2) {
        return ULong.m1042constructorimpl(ULong.m1042constructorimpl(i2 & 4294967295L) - j2);
    }

    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final int m999times7apg3OU(int i2, byte b2) {
        return m963constructorimpl(i2 * m963constructorimpl(b2 & 255));
    }

    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final int m1002timesxj2QHRw(int i2, short s) {
        return m963constructorimpl(i2 * m963constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final int m1001timesWZ4Q5Ns(int i2, int i3) {
        return m963constructorimpl(i2 * i3);
    }

    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m1000timesVKZWuLQ(int i2, long j2) {
        return ULong.m1042constructorimpl(ULong.m1042constructorimpl(i2 & 4294967295L) * j2);
    }

    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final int m965div7apg3OU(int i2, byte b2) {
        return UByte$$ExternalSyntheticBackport1.m(i2, m963constructorimpl(b2 & 255));
    }

    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final int m968divxj2QHRw(int i2, short s) {
        return UByte$$ExternalSyntheticBackport1.m(i2, m963constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final int m967divWZ4Q5Ns(int i2, int i3) {
        return UnsignedKt.m1219uintDivideJ1ME1BU(i2, i3);
    }

    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m966divVKZWuLQ(int i2, long j2) {
        return UByte$$ExternalSyntheticBackport4.m(ULong.m1042constructorimpl(i2 & 4294967295L), j2);
    }

    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final int m993rem7apg3OU(int i2, byte b2) {
        return UByte$$ExternalSyntheticBackport2.m(i2, m963constructorimpl(b2 & 255));
    }

    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final int m996remxj2QHRw(int i2, short s) {
        return UByte$$ExternalSyntheticBackport2.m(i2, m963constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final int m995remWZ4Q5Ns(int i2, int i3) {
        return UnsignedKt.m1220uintRemainderJ1ME1BU(i2, i3);
    }

    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m994remVKZWuLQ(int i2, long j2) {
        return UByte$$ExternalSyntheticBackport5.m(ULong.m1042constructorimpl(i2 & 4294967295L), j2);
    }

    /* renamed from: floorDiv-7apg3OU  reason: not valid java name */
    private static final int m971floorDiv7apg3OU(int i2, byte b2) {
        return UByte$$ExternalSyntheticBackport1.m(i2, m963constructorimpl(b2 & 255));
    }

    /* renamed from: floorDiv-xj2QHRw  reason: not valid java name */
    private static final int m974floorDivxj2QHRw(int i2, short s) {
        return UByte$$ExternalSyntheticBackport1.m(i2, m963constructorimpl(s & UShort.MAX_VALUE));
    }

    /* renamed from: floorDiv-WZ4Q5Ns  reason: not valid java name */
    private static final int m973floorDivWZ4Q5Ns(int i2, int i3) {
        return UByte$$ExternalSyntheticBackport1.m(i2, i3);
    }

    /* renamed from: floorDiv-VKZWuLQ  reason: not valid java name */
    private static final long m972floorDivVKZWuLQ(int i2, long j2) {
        return UByte$$ExternalSyntheticBackport4.m(ULong.m1042constructorimpl(i2 & 4294967295L), j2);
    }

    /* renamed from: mod-7apg3OU  reason: not valid java name */
    private static final byte m982mod7apg3OU(int i2, byte b2) {
        return UByte.m886constructorimpl((byte) UByte$$ExternalSyntheticBackport2.m(i2, m963constructorimpl(b2 & 255)));
    }

    /* renamed from: mod-xj2QHRw  reason: not valid java name */
    private static final short m985modxj2QHRw(int i2, short s) {
        return UShort.m1149constructorimpl((short) UByte$$ExternalSyntheticBackport2.m(i2, m963constructorimpl(s & UShort.MAX_VALUE)));
    }

    /* renamed from: mod-WZ4Q5Ns  reason: not valid java name */
    private static final int m984modWZ4Q5Ns(int i2, int i3) {
        return UByte$$ExternalSyntheticBackport2.m(i2, i3);
    }

    /* renamed from: mod-VKZWuLQ  reason: not valid java name */
    private static final long m983modVKZWuLQ(int i2, long j2) {
        return UByte$$ExternalSyntheticBackport5.m(ULong.m1042constructorimpl(i2 & 4294967295L), j2);
    }

    /* renamed from: inc-pVg5ArA  reason: not valid java name */
    private static final int m976incpVg5ArA(int i2) {
        return m963constructorimpl(i2 + 1);
    }

    /* renamed from: dec-pVg5ArA  reason: not valid java name */
    private static final int m964decpVg5ArA(int i2) {
        return m963constructorimpl(i2 - 1);
    }

    /* renamed from: rangeTo-WZ4Q5Ns  reason: not valid java name */
    private static final UIntRange m991rangeToWZ4Q5Ns(int i2, int i3) {
        return new UIntRange(i2, i3, null);
    }

    /* renamed from: rangeUntil-WZ4Q5Ns  reason: not valid java name */
    private static final UIntRange m992rangeUntilWZ4Q5Ns(int i2, int i3) {
        return URangesKt.m2148untilJ1ME1BU(i2, i3);
    }

    /* renamed from: shl-pVg5ArA  reason: not valid java name */
    private static final int m997shlpVg5ArA(int i2, int i3) {
        return m963constructorimpl(i2 << i3);
    }

    /* renamed from: shr-pVg5ArA  reason: not valid java name */
    private static final int m998shrpVg5ArA(int i2, int i3) {
        return m963constructorimpl(i2 >>> i3);
    }

    /* renamed from: and-WZ4Q5Ns  reason: not valid java name */
    private static final int m956andWZ4Q5Ns(int i2, int i3) {
        return m963constructorimpl(i2 & i3);
    }

    /* renamed from: or-WZ4Q5Ns  reason: not valid java name */
    private static final int m986orWZ4Q5Ns(int i2, int i3) {
        return m963constructorimpl(i2 | i3);
    }

    /* renamed from: xor-WZ4Q5Ns  reason: not valid java name */
    private static final int m1014xorWZ4Q5Ns(int i2, int i3) {
        return m963constructorimpl(i2 ^ i3);
    }

    /* renamed from: inv-pVg5ArA  reason: not valid java name */
    private static final int m977invpVg5ArA(int i2) {
        return m963constructorimpl(~i2);
    }

    /* renamed from: toUByte-w2LRezQ  reason: not valid java name */
    private static final byte m1010toUBytew2LRezQ(int i2) {
        return UByte.m886constructorimpl((byte) i2);
    }

    /* renamed from: toUShort-Mh2AYeg  reason: not valid java name */
    private static final short m1013toUShortMh2AYeg(int i2) {
        return UShort.m1149constructorimpl((short) i2);
    }

    /* renamed from: toULong-s-VKNKU  reason: not valid java name */
    private static final long m1012toULongsVKNKU(int i2) {
        return ULong.m1042constructorimpl(i2 & 4294967295L);
    }

    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m1005toFloatimpl(int i2) {
        return (float) UnsignedKt.uintToDouble(i2);
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m1004toDoubleimpl(int i2) {
        return UnsignedKt.uintToDouble(i2);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m1009toStringimpl(int i2) {
        return String.valueOf(i2 & 4294967295L);
    }

    public String toString() {
        return m1009toStringimpl(this.data);
    }
}
