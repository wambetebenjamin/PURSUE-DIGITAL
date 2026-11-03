package com.ttnet.org.chromium.base.jank_tracker;

import android.app.Activity;
import com.ttnet.org.chromium.base.ApplicationStatus;
import com.ttnet.org.chromium.base.ThreadUtils;
import com.ttnet.org.chromium.base.lifetime.DestroyChecker;
import java.lang.ref.WeakReference;
/* loaded from: classes9.dex */
class JankActivityTracker implements ApplicationStatus.ActivityStateListener {
    private WeakReference<Activity> mActivityReference;
    private final FrameMetricsListener mFrameMetricsListener;
    private final JankReportingScheduler mReportingScheduler;
    private final ThreadUtils.ThreadChecker mThreadChecker = new ThreadUtils.ThreadChecker();
    private final DestroyChecker mDestroyChecker = new DestroyChecker();

    /* JADX INFO: Access modifiers changed from: package-private */
    public JankActivityTracker(Activity activity, FrameMetricsListener frameMetricsListener, JankReportingScheduler jankReportingScheduler) {
        this.mActivityReference = new WeakReference<>(activity);
        this.mFrameMetricsListener = frameMetricsListener;
        this.mReportingScheduler = jankReportingScheduler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void initialize() {
        assertValidState();
        Activity activity = this.mActivityReference.get();
        if (activity != null) {
            ApplicationStatus.registerStateListenerForActivity(this, activity);
            onActivityStateChange(activity, ApplicationStatus.getStateForActivity(activity));
            activity.getWindow().addOnFrameMetricsAvailableListener(this.mFrameMetricsListener, this.mReportingScheduler.getOrCreateHandler());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void destroy() {
        this.mThreadChecker.assertOnValidThread();
        ApplicationStatus.unregisterActivityStateListener(this);
        stopMetricRecording();
        stopReportingTimer();
        Activity activity = this.mActivityReference.get();
        if (activity != null) {
            activity.getWindow().removeOnFrameMetricsAvailableListener(this.mFrameMetricsListener);
        }
        this.mDestroyChecker.destroy();
    }

    private void startReportingTimer() {
        assertValidState();
        this.mReportingScheduler.startReportingPeriodicMetrics();
    }

    private void stopReportingTimer() {
        assertValidState();
        this.mReportingScheduler.stopReportingPeriodicMetrics();
    }

    private void startMetricRecording() {
        assertValidState();
        this.mFrameMetricsListener.setIsListenerRecording(true);
    }

    private void stopMetricRecording() {
        assertValidState();
        this.mFrameMetricsListener.setIsListenerRecording(false);
    }

    private void assertValidState() {
        this.mThreadChecker.assertOnValidThread();
        this.mDestroyChecker.checkNotDestroyed();
    }

    @Override // com.ttnet.org.chromium.base.ApplicationStatus.ActivityStateListener
    public void onActivityStateChange(Activity activity, int i2) {
        assertValidState();
        if (i2 == 2 || i2 == 3) {
            startReportingTimer();
            startMetricRecording();
        } else if (i2 == 4) {
            startReportingTimer();
            stopMetricRecording();
        } else if (i2 != 5) {
        } else {
            stopMetricRecording();
            stopReportingTimer();
        }
    }
}
