package com.ttnet.org.chromium.base.jank_tracker;
/* loaded from: classes9.dex */
class JankMetricCalculator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long JANK_BURST_CONSECUTIVE_FRAME_THRESHOLD_NS = 50000000;
    private static final long JANK_THRESHOLD_NS = 16000000;
    private static final long NANOSECONDS_PER_MILLISECOND = 1000000;

    JankMetricCalculator() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0044, code lost:
        if (isFrameJanky(r8, r11) != false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static long[] calculateJankBurstDurationsNs(long[] r10, long[] r11) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = 0
            r4 = r2
        L9:
            int r6 = r10.length
            if (r1 >= r6) goto L52
            if (r1 <= 0) goto L22
            int r6 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r6 <= 0) goto L22
            int r6 = r1 + (-1)
            boolean r6 = areFramesConsecutive(r6, r1, r10, r11)
            if (r6 != 0) goto L22
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r0.add(r4)
            r4 = r2
        L22:
            r6 = r11[r1]
            boolean r8 = isFrameJanky(r1, r11)
            if (r8 == 0) goto L2c
        L2a:
            long r4 = r4 + r6
            goto L4f
        L2c:
            int r8 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r8 <= 0) goto L4f
            int r8 = r1 + (-1)
            boolean r8 = areFramesConsecutive(r8, r1, r10, r11)
            if (r8 == 0) goto L47
            int r8 = r1 + 1
            boolean r9 = areFramesConsecutive(r1, r8, r10, r11)
            if (r9 == 0) goto L47
            boolean r8 = isFrameJanky(r8, r11)
            if (r8 == 0) goto L47
            goto L2a
        L47:
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            r0.add(r4)
            r4 = r2
        L4f:
            int r1 = r1 + 1
            goto L9
        L52:
            int r10 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r10 <= 0) goto L5d
            java.lang.Long r10 = java.lang.Long.valueOf(r4)
            r0.add(r10)
        L5d:
            int r10 = r0.size()
            java.lang.Long[] r10 = new java.lang.Long[r10]
            java.lang.Object[] r10 = r0.toArray(r10)
            java.lang.Long[] r10 = (java.lang.Long[]) r10
            long[] r10 = longArrayToPrimitiveArray(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ttnet.org.chromium.base.jank_tracker.JankMetricCalculator.calculateJankBurstDurationsNs(long[], long[]):long[]");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JankMetrics calculateJankMetrics(FrameMetrics frameMetrics) {
        long[] longArrayToPrimitiveArray = longArrayToPrimitiveArray(frameMetrics.timestampsNs);
        long[] longArrayToPrimitiveArray2 = longArrayToPrimitiveArray(frameMetrics.durationsNs);
        return new JankMetrics(longArrayToPrimitiveArray, longArrayToPrimitiveArray2, calculateJankBurstDurationsNs(longArrayToPrimitiveArray, longArrayToPrimitiveArray2), sumArray(frameMetrics.skippedFrames));
    }

    private static boolean isFrameJanky(int i2, long[] jArr) {
        return i2 >= 0 && i2 < jArr.length && jArr[i2] > JANK_THRESHOLD_NS;
    }

    private static boolean areFramesConsecutive(int i2, int i3, long[] jArr, long[] jArr2) {
        if (i2 < 0 || i3 < 0 || i2 >= jArr.length || i3 >= jArr.length) {
            return false;
        }
        return (jArr[i3] - jArr2[i3]) - jArr[i2] < JANK_BURST_CONSECUTIVE_FRAME_THRESHOLD_NS;
    }

    private static long[] longArrayToPrimitiveArray(Long[] lArr) {
        long[] jArr = new long[lArr.length];
        for (int i2 = 0; i2 < lArr.length; i2++) {
            jArr[i2] = lArr[i2].longValue();
        }
        return jArr;
    }

    private static int sumArray(Integer[] numArr) {
        int i2 = 0;
        for (Integer num : numArr) {
            i2 += num.intValue();
        }
        return i2;
    }
}
