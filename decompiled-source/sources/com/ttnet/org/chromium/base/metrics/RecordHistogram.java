package com.ttnet.org.chromium.base.metrics;

import com.ttnet.org.chromium.base.annotations.MainDex;
@MainDex
/* loaded from: classes9.dex */
public class RecordHistogram {
    private static int clampToInt(long j2) {
        if (j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j2 < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j2;
    }

    public static void recordBooleanHistogram(String str, boolean z) {
        UmaRecorderHolder.get().recordBooleanHistogram(str, z);
    }

    public static void recordEnumeratedHistogram(String str, int i2, int i3) {
        recordExactLinearHistogram(str, i2, i3);
    }

    public static void recordCount1MHistogram(String str, int i2) {
        UmaRecorderHolder.get().recordExponentialHistogram(str, i2, 1, 1000000, 50);
    }

    public static void recordCount100Histogram(String str, int i2) {
        UmaRecorderHolder.get().recordExponentialHistogram(str, i2, 1, 100, 50);
    }

    public static void recordCount1000Histogram(String str, int i2) {
        UmaRecorderHolder.get().recordExponentialHistogram(str, i2, 1, 1000, 50);
    }

    public static void recordCount100000Histogram(String str, int i2) {
        UmaRecorderHolder.get().recordExponentialHistogram(str, i2, 1, 100000, 50);
    }

    public static void recordCustomCountHistogram(String str, int i2, int i3, int i4, int i5) {
        UmaRecorderHolder.get().recordExponentialHistogram(str, i2, i3, i4, i5);
    }

    public static void recordLinearCountHistogram(String str, int i2, int i3, int i4, int i5) {
        UmaRecorderHolder.get().recordLinearHistogram(str, i2, i3, i4, i5);
    }

    public static void recordPercentageHistogram(String str, int i2) {
        recordExactLinearHistogram(str, i2, 101);
    }

    public static void recordSparseHistogram(String str, int i2) {
        UmaRecorderHolder.get().recordSparseHistogram(str, i2);
    }

    public static void recordTimesHistogram(String str, long j2) {
        recordCustomTimesHistogramMilliseconds(str, j2, 1L, 10000L, 50);
    }

    public static void recordMediumTimesHistogram(String str, long j2) {
        recordCustomTimesHistogramMilliseconds(str, j2, 10L, 180000L, 50);
    }

    public static void recordLongTimesHistogram(String str, long j2) {
        recordCustomTimesHistogramMilliseconds(str, j2, 1L, 3600000L, 50);
    }

    public static void recordLongTimesHistogram100(String str, long j2) {
        recordCustomTimesHistogramMilliseconds(str, j2, 1L, 3600000L, 100);
    }

    public static void recordCustomTimesHistogram(String str, long j2, long j3, long j4, int i2) {
        recordCustomTimesHistogramMilliseconds(str, j2, j3, j4, i2);
    }

    public static void recordMemoryKBHistogram(String str, int i2) {
        UmaRecorderHolder.get().recordExponentialHistogram(str, i2, 1000, 500000, 50);
    }

    public static void recordExactLinearHistogram(String str, int i2, int i3) {
        UmaRecorderHolder.get().recordLinearHistogram(str, i2, 1, i3, i3 + 1);
    }

    private static void recordCustomTimesHistogramMilliseconds(String str, long j2, long j3, long j4, int i2) {
        UmaRecorderHolder.get().recordExponentialHistogram(str, clampToInt(j2), clampToInt(j3), clampToInt(j4), i2);
    }

    public static int getHistogramValueCountForTesting(String str, int i2) {
        return UmaRecorderHolder.get().getHistogramValueCountForTesting(str, i2);
    }

    public static int getHistogramTotalCountForTesting(String str) {
        return UmaRecorderHolder.get().getHistogramTotalCountForTesting(str);
    }
}
