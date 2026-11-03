package kotlin.time;

import androidx.exifinterface.media.ExifInterface;
import com.airbnb.lottie.utils.Utils;
import com.bytedance.crash.dumper.Scraps;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
/* compiled from: Duration.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0087@\u0018\u0000 ¤\u00012\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002¤\u0001B\u0014\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010D\u001a\u00020\u00002\u0006\u0010E\u001a\u00020\u00032\u0006\u0010F\u001a\u00020\u0003H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bG\u0010HJ\u001b\u0010I\u001a\u00020\t2\u0006\u0010J\u001a\u00020\u0000H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\bK\u0010LJ\u001e\u0010M\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u000fH\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010PJ\u001e\u0010M\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\tH\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bO\u0010QJ\u001b\u0010M\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bR\u0010SJ\u001a\u0010T\u001a\u00020U2\b\u0010J\u001a\u0004\u0018\u00010VHÖ\u0003¢\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001a\u00020\tHÖ\u0001¢\u0006\u0004\bZ\u0010\rJ\r\u0010[\u001a\u00020U¢\u0006\u0004\b\\\u0010]J\u000f\u0010^\u001a\u00020UH\u0002¢\u0006\u0004\b_\u0010]J\u000f\u0010`\u001a\u00020UH\u0002¢\u0006\u0004\ba\u0010]J\r\u0010b\u001a\u00020U¢\u0006\u0004\bc\u0010]J\r\u0010d\u001a\u00020U¢\u0006\u0004\be\u0010]J\r\u0010f\u001a\u00020U¢\u0006\u0004\bg\u0010]J\u001b\u0010h\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bi\u0010jJ\u001b\u0010k\u001a\u00020\u00002\u0006\u0010J\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\bl\u0010jJ\u001e\u0010m\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\u000fH\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bn\u0010PJ\u001e\u0010m\u001a\u00020\u00002\u0006\u0010N\u001a\u00020\tH\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bn\u0010QJ\u009d\u0001\u0010o\u001a\u0002Hp\"\u0004\b\u0000\u0010p2u\u0010q\u001aq\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(u\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(v\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(w\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0rH\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bz\u0010{J\u0088\u0001\u0010o\u001a\u0002Hp\"\u0004\b\u0000\u0010p2`\u0010q\u001a\\\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(v\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(w\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0|H\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bz\u0010}Js\u0010o\u001a\u0002Hp\"\u0004\b\u0000\u0010p2K\u0010q\u001aG\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(w\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0~H\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0004\bz\u0010\u007fJ`\u0010o\u001a\u0002Hp\"\u0004\b\u0000\u0010p27\u0010q\u001a3\u0012\u0013\u0012\u00110\u0003¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t¢\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0\u0080\u0001H\u0086\bø\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0005\bz\u0010\u0081\u0001J\u0019\u0010\u0082\u0001\u001a\u00020\u000f2\u0007\u0010\u0083\u0001\u001a\u00020=¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u0019\u0010\u0086\u0001\u001a\u00020\t2\u0007\u0010\u0083\u0001\u001a\u00020=¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J\u0011\u0010\u0089\u0001\u001a\u00030\u008a\u0001¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001J\u0019\u0010\u008d\u0001\u001a\u00020\u00032\u0007\u0010\u0083\u0001\u001a\u00020=¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001J\u0011\u0010\u0090\u0001\u001a\u00020\u0003H\u0007¢\u0006\u0005\b\u0091\u0001\u0010\u0005J\u0011\u0010\u0092\u0001\u001a\u00020\u0003H\u0007¢\u0006\u0005\b\u0093\u0001\u0010\u0005J\u0013\u0010\u0094\u0001\u001a\u00030\u008a\u0001H\u0016¢\u0006\u0006\b\u0095\u0001\u0010\u008c\u0001J%\u0010\u0094\u0001\u001a\u00030\u008a\u00012\u0007\u0010\u0083\u0001\u001a\u00020=2\t\b\u0002\u0010\u0096\u0001\u001a\u00020\t¢\u0006\u0006\b\u0095\u0001\u0010\u0097\u0001J\u0018\u0010\u0098\u0001\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0005\b\u0099\u0001\u0010\u0005JK\u0010\u009a\u0001\u001a\u00030\u009b\u0001*\b0\u009c\u0001j\u0003`\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020\t2\u0007\u0010\u009f\u0001\u001a\u00020\t2\u0007\u0010 \u0001\u001a\u00020\t2\b\u0010\u0083\u0001\u001a\u00030\u008a\u00012\u0007\u0010¡\u0001\u001a\u00020UH\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001R\u0017\u0010\u0006\u001a\u00020\u00008Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u001a\u0010\b\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0015\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0017\u0010\u000b\u001a\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u001b\u0010\u0012R\u001a\u0010\u001c\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000b\u001a\u0004\b\u001e\u0010\u0012R\u001a\u0010\u001f\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b \u0010\u000b\u001a\u0004\b!\u0010\u0012R\u001a\u0010\"\u001a\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b#\u0010\u000b\u001a\u0004\b$\u0010\u0012R\u0011\u0010%\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b&\u0010\u0005R\u0011\u0010'\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b(\u0010\u0005R\u0011\u0010)\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b*\u0010\u0005R\u0011\u0010+\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b,\u0010\u0005R\u0011\u0010-\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b.\u0010\u0005R\u0011\u0010/\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b0\u0010\u0005R\u0011\u00101\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b2\u0010\u0005R\u001a\u00103\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b4\u0010\u000b\u001a\u0004\b5\u0010\rR\u001a\u00106\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b7\u0010\u000b\u001a\u0004\b8\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\u00020\t8@X\u0081\u0004¢\u0006\f\u0012\u0004\b:\u0010\u000b\u001a\u0004\b;\u0010\rR\u0014\u0010<\u001a\u00020=8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0015\u0010@\u001a\u00020\t8Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\bA\u0010\rR\u0014\u0010B\u001a\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bC\u0010\u0005\u0088\u0001\u0002\u0092\u0001\u00020\u0003ø\u0001\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u009920\u0001¨\u0006¥\u0001"}, d2 = {"Lkotlin/time/Duration;", "", "rawValue", "", "constructor-impl", "(J)J", "absoluteValue", "getAbsoluteValue-UwyO8pc", "hoursComponent", "", "getHoursComponent$annotations", "()V", "getHoursComponent-impl", "(J)I", "inDays", "", "getInDays$annotations", "getInDays-impl", "(J)D", "inHours", "getInHours$annotations", "getInHours-impl", "inMicroseconds", "getInMicroseconds$annotations", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds$annotations", "getInMilliseconds-impl", "inMinutes", "getInMinutes$annotations", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds$annotations", "getInNanoseconds-impl", "inSeconds", "getInSeconds$annotations", "getInSeconds-impl", "inWholeDays", "getInWholeDays-impl", "inWholeHours", "getInWholeHours-impl", "inWholeMicroseconds", "getInWholeMicroseconds-impl", "inWholeMilliseconds", "getInWholeMilliseconds-impl", "inWholeMinutes", "getInWholeMinutes-impl", "inWholeNanoseconds", "getInWholeNanoseconds-impl", "inWholeSeconds", "getInWholeSeconds-impl", "minutesComponent", "getMinutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "getNanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "getSecondsComponent$annotations", "getSecondsComponent-impl", "storageUnit", "Lkotlin/time/DurationUnit;", "getStorageUnit-impl", "(J)Lkotlin/time/DurationUnit;", "unitDiscriminator", "getUnitDiscriminator-impl", "value", "getValue-impl", "addValuesMixedRanges", "thisMillis", "otherNanos", "addValuesMixedRanges-UwyO8pc", "(JJJ)J", "compareTo", "other", "compareTo-LRDsOJo", "(JJ)I", "div", "scale", "div-UwyO8pc", "(JD)J", "(JI)J", "div-LRDsOJo", "(JJ)D", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "isFinite", "isFinite-impl", "(J)Z", "isInMillis", "isInMillis-impl", "isInNanos", "isInNanos-impl", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "(JJ)J", "plus", "plus-LRDsOJo", "times", "times-UwyO8pc", "toComponents", ExifInterface.GPS_DIRECTION_TRUE, "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(JLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(JLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(JLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", Scraps.INNER_UNIT, "toDouble-impl", "(JLkotlin/time/DurationUnit;)D", "toInt", "toInt-impl", "(JLkotlin/time/DurationUnit;)I", "toIsoString", "", "toIsoString-impl", "(J)Ljava/lang/String;", "toLong", "toLong-impl", "(JLkotlin/time/DurationUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(JLkotlin/time/DurationUnit;I)Ljava/lang/String;", "unaryMinus", "unaryMinus-UwyO8pc", "appendFractional", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "whole", "fractional", "fractionalSize", "isoZeroes", "appendFractional-impl", "(JLjava/lang/StringBuilder;IIILjava/lang/String;Z)V", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* loaded from: classes10.dex */
public final class Duration implements Comparable<Duration> {
    private final long rawValue;
    public static final Companion Companion = new Companion(null);
    private static final long ZERO = m2194constructorimpl(0);
    private static final long INFINITE = DurationKt.access$durationOfMillis(DurationKt.MAX_MILLIS);
    private static final long NEG_INFINITE = DurationKt.access$durationOfMillis(-4611686018427387903L);

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ Duration m2192boximpl(long j2) {
        return new Duration(j2);
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m2198equalsimpl(long j2, Object obj) {
        return (obj instanceof Duration) && j2 == ((Duration) obj).m2248unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m2199equalsimpl0(long j2, long j3) {
        return j2 == j3;
    }

    public static /* synthetic */ void getHoursComponent$annotations() {
    }

    @Deprecated(message = "Use inWholeDays property instead or convert toDouble(DAYS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.DAYS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    public static /* synthetic */ void getInDays$annotations() {
    }

    @Deprecated(message = "Use inWholeHours property instead or convert toDouble(HOURS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.HOURS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    public static /* synthetic */ void getInHours$annotations() {
    }

    @Deprecated(message = "Use inWholeMicroseconds property instead or convert toDouble(MICROSECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MICROSECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    public static /* synthetic */ void getInMicroseconds$annotations() {
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead or convert toDouble(MILLISECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MILLISECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    public static /* synthetic */ void getInMilliseconds$annotations() {
    }

    @Deprecated(message = "Use inWholeMinutes property instead or convert toDouble(MINUTES) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MINUTES)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    public static /* synthetic */ void getInMinutes$annotations() {
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead or convert toDouble(NANOSECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.NANOSECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    public static /* synthetic */ void getInNanoseconds$annotations() {
    }

    @Deprecated(message = "Use inWholeSeconds property instead or convert toDouble(SECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.SECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    public static /* synthetic */ void getInSeconds$annotations() {
    }

    public static /* synthetic */ void getMinutesComponent$annotations() {
    }

    public static /* synthetic */ void getNanosecondsComponent$annotations() {
    }

    public static /* synthetic */ void getSecondsComponent$annotations() {
    }

    /* renamed from: getUnitDiscriminator-impl  reason: not valid java name */
    private static final int m2220getUnitDiscriminatorimpl(long j2) {
        return ((int) j2) & 1;
    }

    /* renamed from: getValue-impl  reason: not valid java name */
    private static final long m2221getValueimpl(long j2) {
        return j2 >> 1;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m2222hashCodeimpl(long j2) {
        return Long.hashCode(j2);
    }

    /* renamed from: isInMillis-impl  reason: not valid java name */
    private static final boolean m2224isInMillisimpl(long j2) {
        return (((int) j2) & 1) == 1;
    }

    /* renamed from: isInNanos-impl  reason: not valid java name */
    private static final boolean m2225isInNanosimpl(long j2) {
        return (((int) j2) & 1) == 0;
    }

    /* renamed from: isNegative-impl  reason: not valid java name */
    public static final boolean m2227isNegativeimpl(long j2) {
        return j2 < 0;
    }

    /* renamed from: isPositive-impl  reason: not valid java name */
    public static final boolean m2228isPositiveimpl(long j2) {
        return j2 > 0;
    }

    public boolean equals(Object obj) {
        return m2198equalsimpl(this.rawValue, obj);
    }

    public int hashCode() {
        return m2222hashCodeimpl(this.rawValue);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m2248unboximpl() {
        return this.rawValue;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Duration duration) {
        return m2247compareToLRDsOJo(duration.m2248unboximpl());
    }

    private /* synthetic */ Duration(long j2) {
        this.rawValue = j2;
    }

    /* renamed from: getStorageUnit-impl  reason: not valid java name */
    private static final DurationUnit m2219getStorageUnitimpl(long j2) {
        return m2225isInNanosimpl(j2) ? DurationUnit.NANOSECONDS : DurationUnit.MILLISECONDS;
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static long m2194constructorimpl(long j2) {
        if (DurationJvmKt.getDurationAssertionsEnabled()) {
            if (m2225isInNanosimpl(j2)) {
                if (!new LongRange(-4611686018426999999L, DurationKt.MAX_NANOS).contains(m2221getValueimpl(j2))) {
                    throw new AssertionError(m2221getValueimpl(j2) + " ns is out of nanoseconds range");
                }
            } else if (!new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS).contains(m2221getValueimpl(j2))) {
                throw new AssertionError(m2221getValueimpl(j2) + " ms is out of milliseconds range");
            } else {
                if (new LongRange(-4611686018426L, 4611686018426L).contains(m2221getValueimpl(j2))) {
                    throw new AssertionError(m2221getValueimpl(j2) + " ms is denormalized");
                }
            }
        }
        return j2;
    }

    /* compiled from: Duration.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010*\u001a\u00020\r2\u0006\u0010+\u001a\u00020\r2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0007J\u001d\u0010\f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0011J\u001d\u0010\f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0014J\u001d\u0010\f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0017J\u001d\u0010\u0018\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b0\u0010\u0011J\u001d\u0010\u0018\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b0\u0010\u0014J\u001d\u0010\u0018\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b0\u0010\u0017J\u001d\u0010\u001b\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0011J\u001d\u0010\u001b\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0014J\u001d\u0010\u001b\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010\u0017J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b2\u0010\u0011J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b2\u0010\u0014J\u001d\u0010\u001e\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b2\u0010\u0017J\u001d\u0010!\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b3\u0010\u0011J\u001d\u0010!\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b3\u0010\u0014J\u001d\u0010!\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b3\u0010\u0017J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u0010\u0011J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u0010\u0014J\u001d\u0010$\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b4\u0010\u0017J\u001b\u00105\u001a\u00020\u00042\u0006\u0010+\u001a\u000206ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b7\u00108J\u001b\u00109\u001a\u00020\u00042\u0006\u0010+\u001a\u000206ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b:\u00108J\u001b\u0010;\u001a\u0004\u0018\u00010\u00042\u0006\u0010+\u001a\u000206ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\b<J\u001b\u0010=\u001a\u0004\u0018\u00010\u00042\u0006\u0010+\u001a\u000206ø\u0001\u0001ø\u0001\u0000¢\u0006\u0002\b>J\u001d\u0010'\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\rH\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b?\u0010\u0011J\u001d\u0010'\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0012H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b?\u0010\u0014J\u001d\u0010'\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0015H\u0007ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b?\u0010\u0017R\u0019\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R%\u0010\f\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R%\u0010\f\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u000e\u0010\u0013\u001a\u0004\b\u0010\u0010\u0014R%\u0010\f\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u000e\u0010\u0016\u001a\u0004\b\u0010\u0010\u0017R%\u0010\u0018\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u001a\u0010\u0011R%\u0010\u0018\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u0019\u0010\u0013\u001a\u0004\b\u001a\u0010\u0014R%\u0010\u0018\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u001a\u0010\u0017R%\u0010\u001b\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001c\u0010\u000f\u001a\u0004\b\u001d\u0010\u0011R%\u0010\u001b\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001c\u0010\u0013\u001a\u0004\b\u001d\u0010\u0014R%\u0010\u001b\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001c\u0010\u0016\u001a\u0004\b\u001d\u0010\u0017R%\u0010\u001e\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001f\u0010\u000f\u001a\u0004\b \u0010\u0011R%\u0010\u001e\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001f\u0010\u0013\u001a\u0004\b \u0010\u0014R%\u0010\u001e\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\u001f\u0010\u0016\u001a\u0004\b \u0010\u0017R%\u0010!\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\"\u0010\u000f\u001a\u0004\b#\u0010\u0011R%\u0010!\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010\u0014R%\u0010!\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b\"\u0010\u0016\u001a\u0004\b#\u0010\u0017R%\u0010$\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b%\u0010\u000f\u001a\u0004\b&\u0010\u0011R%\u0010$\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b%\u0010\u0013\u001a\u0004\b&\u0010\u0014R%\u0010$\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b%\u0010\u0016\u001a\u0004\b&\u0010\u0017R%\u0010'\u001a\u00020\u0004*\u00020\r8Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b(\u0010\u000f\u001a\u0004\b)\u0010\u0011R%\u0010'\u001a\u00020\u0004*\u00020\u00128Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b(\u0010\u0013\u001a\u0004\b)\u0010\u0014R%\u0010'\u001a\u00020\u0004*\u00020\u00158Æ\u0002X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\u0012\u0004\b(\u0010\u0016\u001a\u0004\b)\u0010\u0017\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006@"}, d2 = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE-UwyO8pc", "()J", "J", "NEG_INFINITE", "getNEG_INFINITE-UwyO8pc$kotlin_stdlib", "ZERO", "getZERO-UwyO8pc", "days", "", "getDays-UwyO8pc$annotations", "(D)V", "getDays-UwyO8pc", "(D)J", "", "(I)V", "(I)J", "", "(J)V", "(J)J", "hours", "getHours-UwyO8pc$annotations", "getHours-UwyO8pc", "microseconds", "getMicroseconds-UwyO8pc$annotations", "getMicroseconds-UwyO8pc", "milliseconds", "getMilliseconds-UwyO8pc$annotations", "getMilliseconds-UwyO8pc", "minutes", "getMinutes-UwyO8pc$annotations", "getMinutes-UwyO8pc", "nanoseconds", "getNanoseconds-UwyO8pc$annotations", "getNanoseconds-UwyO8pc", "seconds", "getSeconds-UwyO8pc$annotations", "getSeconds-UwyO8pc", "convert", "value", "sourceUnit", "Lkotlin/time/DurationUnit;", "targetUnit", "days-UwyO8pc", "hours-UwyO8pc", "microseconds-UwyO8pc", "milliseconds-UwyO8pc", "minutes-UwyO8pc", "nanoseconds-UwyO8pc", "parse", "", "parse-UwyO8pc", "(Ljava/lang/String;)J", "parseIsoString", "parseIsoString-UwyO8pc", "parseIsoStringOrNull", "parseIsoStringOrNull-FghU774", "parseOrNull", "parseOrNull-FghU774", "seconds-UwyO8pc", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* loaded from: classes10.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* renamed from: getDays-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2252getDaysUwyO8pc$annotations(double d2) {
        }

        /* renamed from: getDays-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2253getDaysUwyO8pc$annotations(int i2) {
        }

        /* renamed from: getDays-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2254getDaysUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getHours-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2258getHoursUwyO8pc$annotations(double d2) {
        }

        /* renamed from: getHours-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2259getHoursUwyO8pc$annotations(int i2) {
        }

        /* renamed from: getHours-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2260getHoursUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getMicroseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2264getMicrosecondsUwyO8pc$annotations(double d2) {
        }

        /* renamed from: getMicroseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2265getMicrosecondsUwyO8pc$annotations(int i2) {
        }

        /* renamed from: getMicroseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2266getMicrosecondsUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getMilliseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2270getMillisecondsUwyO8pc$annotations(double d2) {
        }

        /* renamed from: getMilliseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2271getMillisecondsUwyO8pc$annotations(int i2) {
        }

        /* renamed from: getMilliseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2272getMillisecondsUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getMinutes-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2276getMinutesUwyO8pc$annotations(double d2) {
        }

        /* renamed from: getMinutes-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2277getMinutesUwyO8pc$annotations(int i2) {
        }

        /* renamed from: getMinutes-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2278getMinutesUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getNanoseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2282getNanosecondsUwyO8pc$annotations(double d2) {
        }

        /* renamed from: getNanoseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2283getNanosecondsUwyO8pc$annotations(int i2) {
        }

        /* renamed from: getNanoseconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2284getNanosecondsUwyO8pc$annotations(long j2) {
        }

        /* renamed from: getSeconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2288getSecondsUwyO8pc$annotations(double d2) {
        }

        /* renamed from: getSeconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2289getSecondsUwyO8pc$annotations(int i2) {
        }

        /* renamed from: getSeconds-UwyO8pc$annotations  reason: not valid java name */
        public static /* synthetic */ void m2290getSecondsUwyO8pc$annotations(long j2) {
        }

        private Companion() {
        }

        /* renamed from: getZERO-UwyO8pc  reason: not valid java name */
        public final long m2296getZEROUwyO8pc() {
            return Duration.ZERO;
        }

        /* renamed from: getINFINITE-UwyO8pc  reason: not valid java name */
        public final long m2294getINFINITEUwyO8pc() {
            return Duration.INFINITE;
        }

        /* renamed from: getNEG_INFINITE-UwyO8pc$kotlin_stdlib  reason: not valid java name */
        public final long m2295getNEG_INFINITEUwyO8pc$kotlin_stdlib() {
            return Duration.NEG_INFINITE;
        }

        public final double convert(double d2, DurationUnit sourceUnit, DurationUnit targetUnit) {
            Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
            Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
            return DurationUnitKt.convertDurationUnit(d2, sourceUnit, targetUnit);
        }

        /* renamed from: getNanoseconds-UwyO8pc  reason: not valid java name */
        private final long m2280getNanosecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.NANOSECONDS);
        }

        /* renamed from: getNanoseconds-UwyO8pc  reason: not valid java name */
        private final long m2281getNanosecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.NANOSECONDS);
        }

        /* renamed from: getNanoseconds-UwyO8pc  reason: not valid java name */
        private final long m2279getNanosecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.NANOSECONDS);
        }

        /* renamed from: getMicroseconds-UwyO8pc  reason: not valid java name */
        private final long m2262getMicrosecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MICROSECONDS);
        }

        /* renamed from: getMicroseconds-UwyO8pc  reason: not valid java name */
        private final long m2263getMicrosecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MICROSECONDS);
        }

        /* renamed from: getMicroseconds-UwyO8pc  reason: not valid java name */
        private final long m2261getMicrosecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MICROSECONDS);
        }

        /* renamed from: getMilliseconds-UwyO8pc  reason: not valid java name */
        private final long m2268getMillisecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MILLISECONDS);
        }

        /* renamed from: getMilliseconds-UwyO8pc  reason: not valid java name */
        private final long m2269getMillisecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MILLISECONDS);
        }

        /* renamed from: getMilliseconds-UwyO8pc  reason: not valid java name */
        private final long m2267getMillisecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MILLISECONDS);
        }

        /* renamed from: getSeconds-UwyO8pc  reason: not valid java name */
        private final long m2286getSecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.SECONDS);
        }

        /* renamed from: getSeconds-UwyO8pc  reason: not valid java name */
        private final long m2287getSecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.SECONDS);
        }

        /* renamed from: getSeconds-UwyO8pc  reason: not valid java name */
        private final long m2285getSecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.SECONDS);
        }

        /* renamed from: getMinutes-UwyO8pc  reason: not valid java name */
        private final long m2274getMinutesUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MINUTES);
        }

        /* renamed from: getMinutes-UwyO8pc  reason: not valid java name */
        private final long m2275getMinutesUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MINUTES);
        }

        /* renamed from: getMinutes-UwyO8pc  reason: not valid java name */
        private final long m2273getMinutesUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MINUTES);
        }

        /* renamed from: getHours-UwyO8pc  reason: not valid java name */
        private final long m2256getHoursUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.HOURS);
        }

        /* renamed from: getHours-UwyO8pc  reason: not valid java name */
        private final long m2257getHoursUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.HOURS);
        }

        /* renamed from: getHours-UwyO8pc  reason: not valid java name */
        private final long m2255getHoursUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.HOURS);
        }

        /* renamed from: getDays-UwyO8pc  reason: not valid java name */
        private final long m2250getDaysUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.DAYS);
        }

        /* renamed from: getDays-UwyO8pc  reason: not valid java name */
        private final long m2251getDaysUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.DAYS);
        }

        /* renamed from: getDays-UwyO8pc  reason: not valid java name */
        private final long m2249getDaysUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.DAYS);
        }

        @Deprecated(message = "Use 'Int.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m2310nanosecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.NANOSECONDS);
        }

        @Deprecated(message = "Use 'Long.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m2311nanosecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.NANOSECONDS);
        }

        @Deprecated(message = "Use 'Double.nanoseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: nanoseconds-UwyO8pc  reason: not valid java name */
        public final long m2309nanosecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.NANOSECONDS);
        }

        @Deprecated(message = "Use 'Int.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m2301microsecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MICROSECONDS);
        }

        @Deprecated(message = "Use 'Long.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m2302microsecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MICROSECONDS);
        }

        @Deprecated(message = "Use 'Double.microseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: microseconds-UwyO8pc  reason: not valid java name */
        public final long m2300microsecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MICROSECONDS);
        }

        @Deprecated(message = "Use 'Int.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m2304millisecondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MILLISECONDS);
        }

        @Deprecated(message = "Use 'Long.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m2305millisecondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MILLISECONDS);
        }

        @Deprecated(message = "Use 'Double.milliseconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: milliseconds-UwyO8pc  reason: not valid java name */
        public final long m2303millisecondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MILLISECONDS);
        }

        @Deprecated(message = "Use 'Int.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m2317secondsUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.SECONDS);
        }

        @Deprecated(message = "Use 'Long.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m2318secondsUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.SECONDS);
        }

        @Deprecated(message = "Use 'Double.seconds' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: seconds-UwyO8pc  reason: not valid java name */
        public final long m2316secondsUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.SECONDS);
        }

        @Deprecated(message = "Use 'Int.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m2307minutesUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.MINUTES);
        }

        @Deprecated(message = "Use 'Long.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m2308minutesUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.MINUTES);
        }

        @Deprecated(message = "Use 'Double.minutes' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: minutes-UwyO8pc  reason: not valid java name */
        public final long m2306minutesUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.MINUTES);
        }

        @Deprecated(message = "Use 'Int.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m2298hoursUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.HOURS);
        }

        @Deprecated(message = "Use 'Long.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m2299hoursUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.HOURS);
        }

        @Deprecated(message = "Use 'Double.hours' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: hours-UwyO8pc  reason: not valid java name */
        public final long m2297hoursUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.HOURS);
        }

        @Deprecated(message = "Use 'Int.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m2292daysUwyO8pc(int i2) {
            return DurationKt.toDuration(i2, DurationUnit.DAYS);
        }

        @Deprecated(message = "Use 'Long.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m2293daysUwyO8pc(long j2) {
            return DurationKt.toDuration(j2, DurationUnit.DAYS);
        }

        @Deprecated(message = "Use 'Double.days' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.6")
        /* renamed from: days-UwyO8pc  reason: not valid java name */
        public final long m2291daysUwyO8pc(double d2) {
            return DurationKt.toDuration(d2, DurationUnit.DAYS);
        }

        /* renamed from: parse-UwyO8pc  reason: not valid java name */
        public final long m2312parseUwyO8pc(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return DurationKt.access$parseDuration(value, false);
            } catch (IllegalArgumentException e2) {
                throw new IllegalArgumentException("Invalid duration string format: '" + value + "'.", e2);
            }
        }

        /* renamed from: parseIsoString-UwyO8pc  reason: not valid java name */
        public final long m2313parseIsoStringUwyO8pc(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return DurationKt.access$parseDuration(value, true);
            } catch (IllegalArgumentException e2) {
                throw new IllegalArgumentException("Invalid ISO duration string format: '" + value + "'.", e2);
            }
        }

        /* renamed from: parseOrNull-FghU774  reason: not valid java name */
        public final Duration m2315parseOrNullFghU774(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return Duration.m2192boximpl(DurationKt.access$parseDuration(value, false));
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        /* renamed from: parseIsoStringOrNull-FghU774  reason: not valid java name */
        public final Duration m2314parseIsoStringOrNullFghU774(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            try {
                return Duration.m2192boximpl(DurationKt.access$parseDuration(value, true));
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
    }

    /* renamed from: unaryMinus-UwyO8pc  reason: not valid java name */
    public static final long m2246unaryMinusUwyO8pc(long j2) {
        return DurationKt.access$durationOf(-m2221getValueimpl(j2), ((int) j2) & 1);
    }

    /* renamed from: plus-LRDsOJo  reason: not valid java name */
    public static final long m2230plusLRDsOJo(long j2, long j3) {
        if (m2226isInfiniteimpl(j2)) {
            if (m2223isFiniteimpl(j3) || (j3 ^ j2) >= 0) {
                return j2;
            }
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        } else if (m2226isInfiniteimpl(j3)) {
            return j3;
        } else {
            if ((((int) j2) & 1) == (((int) j3) & 1)) {
                long m2221getValueimpl = m2221getValueimpl(j2) + m2221getValueimpl(j3);
                if (m2225isInNanosimpl(j2)) {
                    return DurationKt.access$durationOfNanosNormalized(m2221getValueimpl);
                }
                return DurationKt.access$durationOfMillisNormalized(m2221getValueimpl);
            } else if (m2224isInMillisimpl(j2)) {
                return m2190addValuesMixedRangesUwyO8pc(j2, m2221getValueimpl(j2), m2221getValueimpl(j3));
            } else {
                return m2190addValuesMixedRangesUwyO8pc(j2, m2221getValueimpl(j3), m2221getValueimpl(j2));
            }
        }
    }

    /* renamed from: addValuesMixedRanges-UwyO8pc  reason: not valid java name */
    private static final long m2190addValuesMixedRangesUwyO8pc(long j2, long j3, long j4) {
        long access$nanosToMillis = DurationKt.access$nanosToMillis(j4);
        long j5 = j3 + access$nanosToMillis;
        if (new LongRange(-4611686018426L, 4611686018426L).contains(j5)) {
            return DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(j5) + (j4 - DurationKt.access$millisToNanos(access$nanosToMillis)));
        }
        return DurationKt.access$durationOfMillis(RangesKt.coerceIn(j5, -4611686018427387903L, (long) DurationKt.MAX_MILLIS));
    }

    /* renamed from: minus-LRDsOJo  reason: not valid java name */
    public static final long m2229minusLRDsOJo(long j2, long j3) {
        return m2230plusLRDsOJo(j2, m2246unaryMinusUwyO8pc(j3));
    }

    /* renamed from: times-UwyO8pc  reason: not valid java name */
    public static final long m2232timesUwyO8pc(long j2, int i2) {
        if (m2226isInfiniteimpl(j2)) {
            if (i2 != 0) {
                return i2 > 0 ? j2 : m2246unaryMinusUwyO8pc(j2);
            }
            throw new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
        } else if (i2 == 0) {
            return ZERO;
        } else {
            long m2221getValueimpl = m2221getValueimpl(j2);
            long j3 = i2;
            long j4 = m2221getValueimpl * j3;
            if (m2225isInNanosimpl(j2)) {
                if (new LongRange(-2147483647L, 2147483647L).contains(m2221getValueimpl)) {
                    return DurationKt.access$durationOfNanos(j4);
                }
                if (j4 / j3 == m2221getValueimpl) {
                    return DurationKt.access$durationOfNanosNormalized(j4);
                }
                long access$nanosToMillis = DurationKt.access$nanosToMillis(m2221getValueimpl);
                long j5 = access$nanosToMillis * j3;
                long access$nanosToMillis2 = DurationKt.access$nanosToMillis((m2221getValueimpl - DurationKt.access$millisToNanos(access$nanosToMillis)) * j3) + j5;
                if (j5 / j3 != access$nanosToMillis || (access$nanosToMillis2 ^ j5) < 0) {
                    return MathKt.getSign(m2221getValueimpl) * MathKt.getSign(i2) > 0 ? INFINITE : NEG_INFINITE;
                }
                return DurationKt.access$durationOfMillis(RangesKt.coerceIn(access$nanosToMillis2, new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
            } else if (j4 / j3 == m2221getValueimpl) {
                return DurationKt.access$durationOfMillis(RangesKt.coerceIn(j4, new LongRange(-4611686018427387903L, DurationKt.MAX_MILLIS)));
            } else {
                return MathKt.getSign(m2221getValueimpl) * MathKt.getSign(i2) > 0 ? INFINITE : NEG_INFINITE;
            }
        }
    }

    /* renamed from: times-UwyO8pc  reason: not valid java name */
    public static final long m2231timesUwyO8pc(long j2, double d2) {
        int roundToInt = MathKt.roundToInt(d2);
        if (((double) roundToInt) == d2) {
            return m2232timesUwyO8pc(j2, roundToInt);
        }
        DurationUnit m2219getStorageUnitimpl = m2219getStorageUnitimpl(j2);
        return DurationKt.toDuration(m2237toDoubleimpl(j2, m2219getStorageUnitimpl) * d2, m2219getStorageUnitimpl);
    }

    /* renamed from: div-UwyO8pc  reason: not valid java name */
    public static final long m2197divUwyO8pc(long j2, int i2) {
        if (i2 == 0) {
            if (m2228isPositiveimpl(j2)) {
                return INFINITE;
            }
            if (m2227isNegativeimpl(j2)) {
                return NEG_INFINITE;
            }
            throw new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
        } else if (m2225isInNanosimpl(j2)) {
            return DurationKt.access$durationOfNanos(m2221getValueimpl(j2) / i2);
        } else {
            if (m2226isInfiniteimpl(j2)) {
                return m2232timesUwyO8pc(j2, MathKt.getSign(i2));
            }
            long j3 = i2;
            long m2221getValueimpl = m2221getValueimpl(j2) / j3;
            if (new LongRange(-4611686018426L, 4611686018426L).contains(m2221getValueimpl)) {
                return DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(m2221getValueimpl) + (DurationKt.access$millisToNanos(m2221getValueimpl(j2) - (m2221getValueimpl * j3)) / j3));
            }
            return DurationKt.access$durationOfMillis(m2221getValueimpl);
        }
    }

    /* renamed from: div-UwyO8pc  reason: not valid java name */
    public static final long m2196divUwyO8pc(long j2, double d2) {
        int roundToInt = MathKt.roundToInt(d2);
        if ((((double) roundToInt) == d2) && roundToInt != 0) {
            return m2197divUwyO8pc(j2, roundToInt);
        }
        DurationUnit m2219getStorageUnitimpl = m2219getStorageUnitimpl(j2);
        return DurationKt.toDuration(m2237toDoubleimpl(j2, m2219getStorageUnitimpl) / d2, m2219getStorageUnitimpl);
    }

    /* renamed from: div-LRDsOJo  reason: not valid java name */
    public static final double m2195divLRDsOJo(long j2, long j3) {
        DurationUnit durationUnit = (DurationUnit) ComparisonsKt.maxOf(m2219getStorageUnitimpl(j2), m2219getStorageUnitimpl(j3));
        return m2237toDoubleimpl(j2, durationUnit) / m2237toDoubleimpl(j3, durationUnit);
    }

    /* renamed from: isInfinite-impl  reason: not valid java name */
    public static final boolean m2226isInfiniteimpl(long j2) {
        return j2 == INFINITE || j2 == NEG_INFINITE;
    }

    /* renamed from: isFinite-impl  reason: not valid java name */
    public static final boolean m2223isFiniteimpl(long j2) {
        return !m2226isInfiniteimpl(j2);
    }

    /* renamed from: getAbsoluteValue-UwyO8pc  reason: not valid java name */
    public static final long m2200getAbsoluteValueUwyO8pc(long j2) {
        return m2227isNegativeimpl(j2) ? m2246unaryMinusUwyO8pc(j2) : j2;
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public int m2247compareToLRDsOJo(long j2) {
        return m2193compareToLRDsOJo(this.rawValue, j2);
    }

    /* renamed from: compareTo-LRDsOJo  reason: not valid java name */
    public static int m2193compareToLRDsOJo(long j2, long j3) {
        long j4 = j2 ^ j3;
        if (j4 < 0 || (((int) j4) & 1) == 0) {
            return Intrinsics.compare(j2, j3);
        }
        int i2 = (((int) j2) & 1) - (((int) j3) & 1);
        return m2227isNegativeimpl(j2) ? -i2 : i2;
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m2236toComponentsimpl(long j2, Function5<? super Long, ? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m2209getInWholeDaysimpl(j2)), Integer.valueOf(m2201getHoursComponentimpl(j2)), Integer.valueOf(m2216getMinutesComponentimpl(j2)), Integer.valueOf(m2218getSecondsComponentimpl(j2)), Integer.valueOf(m2217getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m2235toComponentsimpl(long j2, Function4<? super Long, ? super Integer, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m2210getInWholeHoursimpl(j2)), Integer.valueOf(m2216getMinutesComponentimpl(j2)), Integer.valueOf(m2218getSecondsComponentimpl(j2)), Integer.valueOf(m2217getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m2234toComponentsimpl(long j2, Function3<? super Long, ? super Integer, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m2213getInWholeMinutesimpl(j2)), Integer.valueOf(m2218getSecondsComponentimpl(j2)), Integer.valueOf(m2217getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: toComponents-impl  reason: not valid java name */
    public static final <T> T m2233toComponentsimpl(long j2, Function2<? super Long, ? super Integer, ? extends T> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return action.invoke(Long.valueOf(m2215getInWholeSecondsimpl(j2)), Integer.valueOf(m2217getNanosecondsComponentimpl(j2)));
    }

    /* renamed from: getHoursComponent-impl  reason: not valid java name */
    public static final int m2201getHoursComponentimpl(long j2) {
        if (m2226isInfiniteimpl(j2)) {
            return 0;
        }
        return (int) (m2210getInWholeHoursimpl(j2) % 24);
    }

    /* renamed from: getMinutesComponent-impl  reason: not valid java name */
    public static final int m2216getMinutesComponentimpl(long j2) {
        if (m2226isInfiniteimpl(j2)) {
            return 0;
        }
        return (int) (m2213getInWholeMinutesimpl(j2) % 60);
    }

    /* renamed from: getSecondsComponent-impl  reason: not valid java name */
    public static final int m2218getSecondsComponentimpl(long j2) {
        if (m2226isInfiniteimpl(j2)) {
            return 0;
        }
        return (int) (m2215getInWholeSecondsimpl(j2) % 60);
    }

    /* renamed from: getNanosecondsComponent-impl  reason: not valid java name */
    public static final int m2217getNanosecondsComponentimpl(long j2) {
        if (m2226isInfiniteimpl(j2)) {
            return 0;
        }
        return (int) (m2224isInMillisimpl(j2) ? DurationKt.access$millisToNanos(m2221getValueimpl(j2) % 1000) : m2221getValueimpl(j2) % ((long) Utils.SECOND_IN_NANOS));
    }

    /* renamed from: toDouble-impl  reason: not valid java name */
    public static final double m2237toDoubleimpl(long j2, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (j2 == INFINITE) {
            return Double.POSITIVE_INFINITY;
        }
        if (j2 == NEG_INFINITE) {
            return Double.NEGATIVE_INFINITY;
        }
        return DurationUnitKt.convertDurationUnit(m2221getValueimpl(j2), m2219getStorageUnitimpl(j2), unit);
    }

    /* renamed from: toLong-impl  reason: not valid java name */
    public static final long m2240toLongimpl(long j2, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (j2 == INFINITE) {
            return Long.MAX_VALUE;
        }
        if (j2 == NEG_INFINITE) {
            return Long.MIN_VALUE;
        }
        return DurationUnitKt.convertDurationUnit(m2221getValueimpl(j2), m2219getStorageUnitimpl(j2), unit);
    }

    /* renamed from: toInt-impl  reason: not valid java name */
    public static final int m2238toIntimpl(long j2, DurationUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return (int) RangesKt.coerceIn(m2240toLongimpl(j2, unit), -2147483648L, 2147483647L);
    }

    /* renamed from: getInDays-impl  reason: not valid java name */
    public static final double m2202getInDaysimpl(long j2) {
        return m2237toDoubleimpl(j2, DurationUnit.DAYS);
    }

    /* renamed from: getInHours-impl  reason: not valid java name */
    public static final double m2203getInHoursimpl(long j2) {
        return m2237toDoubleimpl(j2, DurationUnit.HOURS);
    }

    /* renamed from: getInMinutes-impl  reason: not valid java name */
    public static final double m2206getInMinutesimpl(long j2) {
        return m2237toDoubleimpl(j2, DurationUnit.MINUTES);
    }

    /* renamed from: getInSeconds-impl  reason: not valid java name */
    public static final double m2208getInSecondsimpl(long j2) {
        return m2237toDoubleimpl(j2, DurationUnit.SECONDS);
    }

    /* renamed from: getInMilliseconds-impl  reason: not valid java name */
    public static final double m2205getInMillisecondsimpl(long j2) {
        return m2237toDoubleimpl(j2, DurationUnit.MILLISECONDS);
    }

    /* renamed from: getInMicroseconds-impl  reason: not valid java name */
    public static final double m2204getInMicrosecondsimpl(long j2) {
        return m2237toDoubleimpl(j2, DurationUnit.MICROSECONDS);
    }

    /* renamed from: getInNanoseconds-impl  reason: not valid java name */
    public static final double m2207getInNanosecondsimpl(long j2) {
        return m2237toDoubleimpl(j2, DurationUnit.NANOSECONDS);
    }

    /* renamed from: getInWholeDays-impl  reason: not valid java name */
    public static final long m2209getInWholeDaysimpl(long j2) {
        return m2240toLongimpl(j2, DurationUnit.DAYS);
    }

    /* renamed from: getInWholeHours-impl  reason: not valid java name */
    public static final long m2210getInWholeHoursimpl(long j2) {
        return m2240toLongimpl(j2, DurationUnit.HOURS);
    }

    /* renamed from: getInWholeMinutes-impl  reason: not valid java name */
    public static final long m2213getInWholeMinutesimpl(long j2) {
        return m2240toLongimpl(j2, DurationUnit.MINUTES);
    }

    /* renamed from: getInWholeSeconds-impl  reason: not valid java name */
    public static final long m2215getInWholeSecondsimpl(long j2) {
        return m2240toLongimpl(j2, DurationUnit.SECONDS);
    }

    /* renamed from: getInWholeMilliseconds-impl  reason: not valid java name */
    public static final long m2212getInWholeMillisecondsimpl(long j2) {
        return (m2224isInMillisimpl(j2) && m2223isFiniteimpl(j2)) ? m2221getValueimpl(j2) : m2240toLongimpl(j2, DurationUnit.MILLISECONDS);
    }

    /* renamed from: getInWholeMicroseconds-impl  reason: not valid java name */
    public static final long m2211getInWholeMicrosecondsimpl(long j2) {
        return m2240toLongimpl(j2, DurationUnit.MICROSECONDS);
    }

    /* renamed from: getInWholeNanoseconds-impl  reason: not valid java name */
    public static final long m2214getInWholeNanosecondsimpl(long j2) {
        long m2221getValueimpl = m2221getValueimpl(j2);
        if (m2225isInNanosimpl(j2)) {
            return m2221getValueimpl;
        }
        if (m2221getValueimpl > 9223372036854L) {
            return Long.MAX_VALUE;
        }
        if (m2221getValueimpl < -9223372036854L) {
            return Long.MIN_VALUE;
        }
        return DurationKt.access$millisToNanos(m2221getValueimpl);
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead.", replaceWith = @ReplaceWith(expression = "this.inWholeNanoseconds", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    /* renamed from: toLongNanoseconds-impl  reason: not valid java name */
    public static final long m2242toLongNanosecondsimpl(long j2) {
        return m2214getInWholeNanosecondsimpl(j2);
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead.", replaceWith = @ReplaceWith(expression = "this.inWholeMilliseconds", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", warningSince = "1.5")
    /* renamed from: toLongMilliseconds-impl  reason: not valid java name */
    public static final long m2241toLongMillisecondsimpl(long j2) {
        return m2212getInWholeMillisecondsimpl(j2);
    }

    public String toString() {
        return m2243toStringimpl(this.rawValue);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m2243toStringimpl(long j2) {
        if (j2 == 0) {
            return "0s";
        }
        if (j2 == INFINITE) {
            return "Infinity";
        }
        if (j2 == NEG_INFINITE) {
            return "-Infinity";
        }
        boolean m2227isNegativeimpl = m2227isNegativeimpl(j2);
        StringBuilder sb = new StringBuilder();
        if (m2227isNegativeimpl) {
            sb.append('-');
        }
        long m2200getAbsoluteValueUwyO8pc = m2200getAbsoluteValueUwyO8pc(j2);
        long m2209getInWholeDaysimpl = m2209getInWholeDaysimpl(m2200getAbsoluteValueUwyO8pc);
        int m2201getHoursComponentimpl = m2201getHoursComponentimpl(m2200getAbsoluteValueUwyO8pc);
        int m2216getMinutesComponentimpl = m2216getMinutesComponentimpl(m2200getAbsoluteValueUwyO8pc);
        int m2218getSecondsComponentimpl = m2218getSecondsComponentimpl(m2200getAbsoluteValueUwyO8pc);
        int m2217getNanosecondsComponentimpl = m2217getNanosecondsComponentimpl(m2200getAbsoluteValueUwyO8pc);
        int i2 = 0;
        boolean z = m2209getInWholeDaysimpl != 0;
        boolean z2 = m2201getHoursComponentimpl != 0;
        boolean z3 = m2216getMinutesComponentimpl != 0;
        boolean z4 = (m2218getSecondsComponentimpl == 0 && m2217getNanosecondsComponentimpl == 0) ? false : true;
        if (z) {
            sb.append(m2209getInWholeDaysimpl).append('d');
            i2 = 1;
        }
        if (z2 || (z && (z3 || z4))) {
            int i3 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            sb.append(m2201getHoursComponentimpl).append('h');
            i2 = i3;
        }
        if (z3 || (z4 && (z2 || z))) {
            int i4 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            sb.append(m2216getMinutesComponentimpl).append('m');
            i2 = i4;
        }
        if (z4) {
            int i5 = i2 + 1;
            if (i2 > 0) {
                sb.append(' ');
            }
            if (m2218getSecondsComponentimpl != 0 || z || z2 || z3) {
                m2191appendFractionalimpl(j2, sb, m2218getSecondsComponentimpl, m2217getNanosecondsComponentimpl, 9, "s", false);
            } else if (m2217getNanosecondsComponentimpl >= 1000000) {
                m2191appendFractionalimpl(j2, sb, m2217getNanosecondsComponentimpl / 1000000, m2217getNanosecondsComponentimpl % 1000000, 6, "ms", false);
            } else if (m2217getNanosecondsComponentimpl >= 1000) {
                m2191appendFractionalimpl(j2, sb, m2217getNanosecondsComponentimpl / 1000, m2217getNanosecondsComponentimpl % 1000, 3, "us", false);
            } else {
                sb.append(m2217getNanosecondsComponentimpl).append("ns");
            }
            i2 = i5;
        }
        if (m2227isNegativeimpl && i2 > 1) {
            sb.insert(1, '(').append(')');
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    /* renamed from: appendFractional-impl  reason: not valid java name */
    private static final void m2191appendFractionalimpl(long j2, StringBuilder sb, int i2, int i3, int i4, String str, boolean z) {
        sb.append(i2);
        if (i3 != 0) {
            sb.append('.');
            String padStart = StringsKt.padStart(String.valueOf(i3), i4, '0');
            int i5 = -1;
            int length = padStart.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i6 = length - 1;
                    if (padStart.charAt(length) != '0') {
                        i5 = length;
                        break;
                    } else if (i6 < 0) {
                        break;
                    } else {
                        length = i6;
                    }
                }
            }
            int i7 = i5 + 1;
            if (!z && i7 < 3) {
                Intrinsics.checkNotNullExpressionValue(sb.append((CharSequence) padStart, 0, i7), "this.append(value, startIndex, endIndex)");
            } else {
                Intrinsics.checkNotNullExpressionValue(sb.append((CharSequence) padStart, 0, ((i7 + 2) / 3) * 3), "this.append(value, startIndex, endIndex)");
            }
        }
        sb.append(str);
    }

    /* renamed from: toString-impl$default  reason: not valid java name */
    public static /* synthetic */ String m2245toStringimpl$default(long j2, DurationUnit durationUnit, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        return m2244toStringimpl(j2, durationUnit, i2);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static final String m2244toStringimpl(long j2, DurationUnit unit, int i2) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        if (!(i2 >= 0)) {
            throw new IllegalArgumentException(("decimals must be not negative, but was " + i2).toString());
        }
        double m2237toDoubleimpl = m2237toDoubleimpl(j2, unit);
        return Double.isInfinite(m2237toDoubleimpl) ? String.valueOf(m2237toDoubleimpl) : DurationJvmKt.formatToExactDecimals(m2237toDoubleimpl, RangesKt.coerceAtMost(i2, 12)) + DurationUnitKt.shortName(unit);
    }

    /* renamed from: toIsoString-impl  reason: not valid java name */
    public static final String m2239toIsoStringimpl(long j2) {
        StringBuilder sb = new StringBuilder();
        if (m2227isNegativeimpl(j2)) {
            sb.append('-');
        }
        sb.append("PT");
        long m2200getAbsoluteValueUwyO8pc = m2200getAbsoluteValueUwyO8pc(j2);
        long m2210getInWholeHoursimpl = m2210getInWholeHoursimpl(m2200getAbsoluteValueUwyO8pc);
        int m2216getMinutesComponentimpl = m2216getMinutesComponentimpl(m2200getAbsoluteValueUwyO8pc);
        int m2218getSecondsComponentimpl = m2218getSecondsComponentimpl(m2200getAbsoluteValueUwyO8pc);
        int m2217getNanosecondsComponentimpl = m2217getNanosecondsComponentimpl(m2200getAbsoluteValueUwyO8pc);
        if (m2226isInfiniteimpl(j2)) {
            m2210getInWholeHoursimpl = 9999999999999L;
        }
        boolean z = true;
        boolean z2 = m2210getInWholeHoursimpl != 0;
        boolean z3 = (m2218getSecondsComponentimpl == 0 && m2217getNanosecondsComponentimpl == 0) ? false : true;
        if (m2216getMinutesComponentimpl == 0 && (!z3 || !z2)) {
            z = false;
        }
        if (z2) {
            sb.append(m2210getInWholeHoursimpl).append('H');
        }
        if (z) {
            sb.append(m2216getMinutesComponentimpl).append('M');
        }
        if (z3 || (!z2 && !z)) {
            m2191appendFractionalimpl(j2, sb, m2218getSecondsComponentimpl, m2217getNanosecondsComponentimpl, 9, ExifInterface.LATITUDE_SOUTH, true);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
