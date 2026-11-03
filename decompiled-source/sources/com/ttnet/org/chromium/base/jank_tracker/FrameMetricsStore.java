package com.ttnet.org.chromium.base.jank_tracker;

import com.ttnet.org.chromium.build.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes9.dex */
class FrameMetricsStore {
    private final Object mLock = new Object();
    private final ArrayList<Long> mTimestampsNs = new ArrayList<>();
    private final ArrayList<Long> mTotalDurationsNs = new ArrayList<>();
    private final ArrayList<Integer> mSkippedFrames = new ArrayList<>();
    private final HashMap<Integer, Long> mScenarioPreviousFrameTimestampNs = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addFrameMeasurement(long j2, long j3, int i2) {
        synchronized (this.mLock) {
            if (this.mScenarioPreviousFrameTimestampNs.isEmpty()) {
                return;
            }
            this.mTimestampsNs.add(Long.valueOf(j2));
            this.mTotalDurationsNs.add(Long.valueOf(j3));
            this.mSkippedFrames.add(Integer.valueOf(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startTrackingScenario(int i2) {
        synchronized (this.mLock) {
            if (this.mScenarioPreviousFrameTimestampNs.containsKey(Integer.valueOf(i2))) {
                return;
            }
            Long l = 0L;
            if (!this.mTimestampsNs.isEmpty()) {
                ArrayList<Long> arrayList = this.mTimestampsNs;
                l = arrayList.get(arrayList.size() - 1);
            }
            this.mScenarioPreviousFrameTimestampNs.put(Integer.valueOf(i2), l);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrameMetrics stopTrackingScenario(int i2) {
        int indexOf;
        synchronized (this.mLock) {
            Long remove = this.mScenarioPreviousFrameTimestampNs.remove(Integer.valueOf(i2));
            if (remove == null) {
                return new FrameMetrics();
            }
            if (remove.longValue() == 0) {
                indexOf = 0;
            } else {
                indexOf = this.mTimestampsNs.indexOf(remove) + 1;
                if (indexOf >= this.mTimestampsNs.size()) {
                    return new FrameMetrics();
                }
            }
            int size = this.mTimestampsNs.size();
            int i3 = size - indexOf;
            FrameMetrics frameMetrics = new FrameMetrics((Long[]) this.mTimestampsNs.subList(indexOf, size).toArray(new Long[i3]), (Long[]) this.mTotalDurationsNs.subList(indexOf, size).toArray(new Long[i3]), (Integer[]) this.mSkippedFrames.subList(indexOf, size).toArray(new Integer[i3]));
            removeUnusedFrames();
            return frameMetrics;
        }
    }

    FrameMetrics getAllStoredMetricsForTesting() {
        FrameMetrics frameMetrics;
        synchronized (this.mLock) {
            ArrayList<Long> arrayList = this.mTimestampsNs;
            ArrayList<Long> arrayList2 = this.mTotalDurationsNs;
            ArrayList<Integer> arrayList3 = this.mSkippedFrames;
            frameMetrics = new FrameMetrics((Long[]) arrayList.toArray(new Long[arrayList.size()]), (Long[]) arrayList2.toArray(new Long[arrayList2.size()]), (Integer[]) arrayList3.toArray(new Integer[arrayList3.size()]));
        }
        return frameMetrics;
    }

    private void removeUnusedFrames() {
        if (this.mScenarioPreviousFrameTimestampNs.isEmpty()) {
            this.mTimestampsNs.clear();
            this.mTotalDurationsNs.clear();
            this.mSkippedFrames.clear();
            return;
        }
        long findFirstUsedTimestamp = findFirstUsedTimestamp();
        if (findFirstUsedTimestamp == 0) {
            return;
        }
        int indexOf = this.mTimestampsNs.indexOf(Long.valueOf(findFirstUsedTimestamp));
        if (indexOf == -1) {
            if (BuildConfig.ENABLE_ASSERTS) {
                throw new IllegalStateException("Timestamp for tracked scenario not found");
            }
            return;
        }
        this.mTimestampsNs.subList(0, indexOf).clear();
        this.mTotalDurationsNs.subList(0, indexOf).clear();
        this.mSkippedFrames.subList(0, indexOf).clear();
    }

    private long findFirstUsedTimestamp() {
        long j2 = Long.MAX_VALUE;
        for (Long l : this.mScenarioPreviousFrameTimestampNs.values()) {
            long longValue = l.longValue();
            if (longValue < j2) {
                j2 = longValue;
            }
        }
        return j2;
    }
}
