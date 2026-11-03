package com.ttnet.org.chromium.base.jank_tracker;
/* loaded from: classes9.dex */
class FrameMetrics {
    public final Long[] durationsNs;
    public final Integer[] skippedFrames;
    public final Long[] timestampsNs;

    public FrameMetrics(Long[] lArr, Long[] lArr2, Integer[] numArr) {
        this.timestampsNs = lArr;
        this.durationsNs = lArr2;
        this.skippedFrames = numArr;
    }

    public FrameMetrics() {
        this.timestampsNs = new Long[0];
        this.durationsNs = new Long[0];
        this.skippedFrames = new Integer[0];
    }
}
