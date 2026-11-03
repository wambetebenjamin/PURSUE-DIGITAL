package com.ttnet.org.chromium.base.jank_tracker;

import android.app.Activity;
import android.os.Build;
/* loaded from: classes9.dex */
public class JankTrackerImpl implements JankTracker {
    private static final boolean IS_TRACKING_ENABLED;
    private final JankActivityTracker mActivityTracker;
    private final JankReportingScheduler mReportingScheduler;

    static {
        IS_TRACKING_ENABLED = Build.VERSION.SDK_INT >= 24;
    }

    public JankTrackerImpl(Activity activity) {
        if (!IS_TRACKING_ENABLED) {
            this.mActivityTracker = null;
            this.mReportingScheduler = null;
            return;
        }
        FrameMetricsStore frameMetricsStore = new FrameMetricsStore();
        FrameMetricsListener frameMetricsListener = new FrameMetricsListener(frameMetricsStore);
        JankReportingScheduler jankReportingScheduler = new JankReportingScheduler(frameMetricsStore);
        this.mReportingScheduler = jankReportingScheduler;
        JankActivityTracker jankActivityTracker = new JankActivityTracker(activity, frameMetricsListener, jankReportingScheduler);
        this.mActivityTracker = jankActivityTracker;
        jankActivityTracker.initialize();
    }

    @Override // com.ttnet.org.chromium.base.jank_tracker.JankTracker
    public void startTrackingScenario(int i2) {
        if (IS_TRACKING_ENABLED) {
            this.mReportingScheduler.startTrackingScenario(i2);
        }
    }

    @Override // com.ttnet.org.chromium.base.jank_tracker.JankTracker
    public void finishTrackingScenario(int i2) {
        if (IS_TRACKING_ENABLED) {
            this.mReportingScheduler.finishTrackingScenario(i2);
        }
    }

    public void destroy() {
        if (IS_TRACKING_ENABLED) {
            this.mActivityTracker.destroy();
        }
    }
}
