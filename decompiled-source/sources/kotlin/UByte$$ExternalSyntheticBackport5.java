package kotlin;
/* compiled from: D8$$SyntheticClass */
/* loaded from: classes10.dex */
public final /* synthetic */ class UByte$$ExternalSyntheticBackport5 {
    public static /* synthetic */ long m(long j2, long j3) {
        if (j3 < 0) {
            return (j2 ^ Long.MIN_VALUE) < (j3 ^ Long.MIN_VALUE) ? j2 : j2 - j3;
        } else if (j2 >= 0) {
            return j2 % j3;
        } else {
            long j4 = j2 - ((((j2 >>> 1) / j3) << 1) * j3);
            if ((j4 ^ Long.MIN_VALUE) < (j3 ^ Long.MIN_VALUE)) {
                j3 = 0;
            }
            return j4 - j3;
        }
    }
}
