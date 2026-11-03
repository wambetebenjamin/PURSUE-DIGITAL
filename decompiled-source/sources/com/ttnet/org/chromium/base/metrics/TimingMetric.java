package com.ttnet.org.chromium.base.metrics;

import com.ttnet.org.chromium.base.TimeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public class TimingMetric implements AutoCloseable {
    private final String mMetricName;
    private long mStartTime;
    private final int mTimerType;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    private @interface TimerType {
        public static final int MEDIUM_UPTIME = 1;
        public static final int SHORT_THREAD_TIME = 2;
        public static final int SHORT_UPTIME = 0;
    }

    public static TimingMetric shortUptime(String str) {
        TimingMetric timingMetric = new TimingMetric(str, 0);
        timingMetric.mStartTime = TimeUtils.uptimeMillis();
        return timingMetric;
    }

    public static TimingMetric mediumUptime(String str) {
        TimingMetric timingMetric = new TimingMetric(str, 1);
        timingMetric.mStartTime = TimeUtils.uptimeMillis();
        return timingMetric;
    }

    public static TimingMetric shortThreadTime(String str) {
        TimingMetric timingMetric = new TimingMetric(str, 2);
        timingMetric.mStartTime = TimeUtils.currentThreadTimeMillis();
        return timingMetric;
    }

    private TimingMetric(String str, int i2) {
        this.mMetricName = str;
        this.mTimerType = i2;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        String str = this.mMetricName;
        long j2 = this.mStartTime;
        if (j2 == 0) {
            return;
        }
        this.mStartTime = 0L;
        int i2 = this.mTimerType;
        if (i2 == 0) {
            RecordHistogram.recordTimesHistogram(str, TimeUtils.uptimeMillis() - j2);
        } else if (i2 == 1) {
            RecordHistogram.recordMediumTimesHistogram(str, TimeUtils.uptimeMillis() - j2);
        } else if (i2 != 2) {
        } else {
            RecordHistogram.recordTimesHistogram(str, TimeUtils.currentThreadTimeMillis() - j2);
        }
    }
}
