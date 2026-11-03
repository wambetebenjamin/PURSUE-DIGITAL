package com.ttnet.org.chromium.base.jank_tracker;

import android.view.Window;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes9.dex */
class FrameMetricsListener implements Window.OnFrameMetricsAvailableListener {
    private final FrameMetricsStore mFrameMetricsStore;
    private final AtomicBoolean mIsRecording = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrameMetricsListener(FrameMetricsStore frameMetricsStore) {
        this.mFrameMetricsStore = frameMetricsStore;
    }

    public void setIsListenerRecording(boolean z) {
        this.mIsRecording.set(z);
    }

    @Override // android.view.Window.OnFrameMetricsAvailableListener
    public void onFrameMetricsAvailable(Window window, android.view.FrameMetrics frameMetrics, int i2) {
        if (this.mIsRecording.get()) {
            long metric = frameMetrics.getMetric(8);
            this.mFrameMetricsStore.addFrameMeasurement(frameMetrics.getMetric(10), metric, i2);
        }
    }
}
