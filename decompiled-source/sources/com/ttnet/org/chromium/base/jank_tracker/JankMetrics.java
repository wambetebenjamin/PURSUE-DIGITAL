package com.ttnet.org.chromium.base.jank_tracker;
/* loaded from: classes9.dex */
class JankMetrics {
    public final long[] durationsNs;
    public final long[] jankBurstsNs;
    public final int skippedFrames;
    public final long[] timestampsNs;

    public JankMetrics(long[] jArr, long[] jArr2, long[] jArr3, int i2) {
        this.timestampsNs = jArr;
        this.durationsNs = jArr2;
        this.jankBurstsNs = jArr3;
        this.skippedFrames = i2;
    }
}
