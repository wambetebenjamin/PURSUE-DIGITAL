package com.bytedance.retrofit2;
/* loaded from: classes2.dex */
public class TTMetricsUtil {
    public static long nanoSecondsToMilliSeconds(long j2) {
        if (j2 == -1) {
            return -1L;
        }
        return j2 / 1000000;
    }

    public static long nanoSecondsToMicroSeconds(long j2) {
        if (j2 == -1) {
            return -1L;
        }
        return j2 / 1000;
    }

    public static long calculateWallClockTimeMs(long j2, long j3, long j4) {
        if (j2 < 0 || j3 < 0 || j4 < 0 || j3 > j4) {
            return -1L;
        }
        return nanoSecondsToMilliSeconds(j4 - j3) + j2;
    }

    public static long calculateDurationUs(long j2, long j3) {
        if (j2 == -1 || j3 == -1 || j2 > j3) {
            return -1L;
        }
        return nanoSecondsToMicroSeconds(j3 - j2);
    }
}
