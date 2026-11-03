package com.ttnet.org.chromium.base.jank_tracker;
/* loaded from: classes9.dex */
class JankReportingRunnable implements Runnable {
    private final boolean mIsStartingTracking;
    private final FrameMetricsStore mMetricsStore;
    private final int mScenario;

    /* JADX INFO: Access modifiers changed from: package-private */
    public JankReportingRunnable(FrameMetricsStore frameMetricsStore, int i2, boolean z) {
        this.mMetricsStore = frameMetricsStore;
        this.mScenario = i2;
        this.mIsStartingTracking = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.mIsStartingTracking) {
            this.mMetricsStore.startTrackingScenario(this.mScenario);
            return;
        }
        FrameMetrics stopTrackingScenario = this.mMetricsStore.stopTrackingScenario(this.mScenario);
        if (stopTrackingScenario == null || stopTrackingScenario.timestampsNs.length == 0) {
            return;
        }
        JankMetricUMARecorderJni.get();
        JankMetricUMARecorder.recordJankMetricsToUMA(JankMetricCalculator.calculateJankMetrics(stopTrackingScenario), this.mScenario);
    }
}
