package com.ss.android.socialbase.downloader.network.throttle;

import android.net.TrafficStats;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.network.ExponentialGeometricAverage;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes9.dex */
public class SmartThrottleInputStream extends ThrottleInputStream {
    private static final boolean DEBUG = true;
    private static final double DEFAULT_DECAY_CONSTANT = 0.1d;
    private static final int LEFT_MOVE = -1;
    private static final int RIGHT_MOVE = 1;
    private static final String TAG = "SmartThrottle";
    private static final float THROTTLE_FACTOR_MAX = 0.6f;
    private static final float THROTTLE_FACTOR_MIDDLE = 0.3f;
    private static final float THROTTLE_FACTOR_MIN = 0.0f;
    private long curReadBytes;
    private long curTotalBytes;
    private double lastOtherAvgSpeed;
    private double lastReadAvgSpeed;
    private long lastReadBytes;
    private long lastStatsSpeedTime;
    private double lastTotalAvgSpeed;
    private long lastTotalBytes;
    private long lastUpdateThrottleFactorTime;
    private int otherSpeedDeltaThreshold;
    private long overSleepTime;
    private double pendingOtherAvgSpeedDelta;
    private final ExponentialGeometricAverage readSpeedAverage;
    private long statsSpeedPeriod;
    private float throttleFactor;
    private int throttleFactorMoveTrend;
    private float throttleFactorStep;
    private final ExponentialGeometricAverage totalSpeedAverage;
    private long updateThrottleFactorPeriod;

    public SmartThrottleInputStream(InputStream inputStream) {
        super(inputStream);
        this.throttleFactor = 0.1f;
        this.throttleFactorStep = 0.1f;
        this.statsSpeedPeriod = 1000L;
        this.updateThrottleFactorPeriod = 2000L;
        this.readSpeedAverage = new ExponentialGeometricAverage(0.1d);
        this.totalSpeedAverage = new ExponentialGeometricAverage(0.1d);
        this.throttleFactorMoveTrend = 1;
        this.otherSpeedDeltaThreshold = 60;
    }

    @Override // com.ss.android.socialbase.downloader.network.throttle.ThrottleInputStream, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        if (this.throttleNetSpeed == -1) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.periodStartTime > this.throttlePeriod) {
                this.periodStartTime = currentTimeMillis;
                statsSpeed(currentTimeMillis);
                updateThrottleFactor(currentTimeMillis);
                long j2 = (long) (this.throttlePeriod * (1.0f - this.throttleFactor));
                long j3 = this.overSleepTime;
                if (j3 >= j2) {
                    this.overSleepTime = j3 - j2;
                } else {
                    if (j3 > 0) {
                        j2 -= j3;
                        this.overSleepTime = 0L;
                    }
                    if (j2 > 0) {
                        long currentTimeMillis2 = System.currentTimeMillis();
                        try {
                            Thread.sleep(j2);
                            long currentTimeMillis3 = (System.currentTimeMillis() - currentTimeMillis2) - j2;
                            if (currentTimeMillis3 > 0) {
                                this.overSleepTime = currentTimeMillis3;
                            }
                        } catch (InterruptedException e2) {
                            throw new IOException(e2);
                        }
                    }
                }
            }
            int superRead = superRead(bArr, i2, i3);
            if (superRead != -1) {
                this.curReadBytes += superRead;
            }
            return superRead;
        }
        return super.read(bArr, i2, i3);
    }

    private void statsSpeed(long j2) {
        long j3 = this.lastStatsSpeedTime;
        if (j2 - j3 >= this.statsSpeedPeriod) {
            double d2 = j2 - j3;
            if (j3 <= 0 || d2 <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                this.lastStatsSpeedTime = j2;
                this.lastTotalBytes = TrafficStats.getTotalRxBytes();
                this.lastReadBytes = this.curReadBytes;
                return;
            }
            this.lastStatsSpeedTime = j2;
            long totalRxBytes = TrafficStats.getTotalRxBytes();
            this.curTotalBytes = totalRxBytes;
            long j4 = this.curReadBytes;
            long j5 = totalRxBytes - this.lastTotalBytes;
            this.lastReadBytes = j4;
            this.lastTotalBytes = totalRxBytes;
            this.readSpeedAverage.addMeasurement((j4 - this.lastReadBytes) / d2);
            this.totalSpeedAverage.addMeasurement(j5 / d2);
        }
    }

    private void updateThrottleFactor(long j2) {
        long j3 = this.lastUpdateThrottleFactorTime;
        if (j2 - j3 >= this.updateThrottleFactorPeriod) {
            if (j3 == 0) {
                this.lastUpdateThrottleFactorTime = j2;
                return;
            }
            this.lastUpdateThrottleFactorTime = j2;
            double average = this.readSpeedAverage.getAverage();
            double average2 = this.totalSpeedAverage.getAverage();
            double d2 = average2 - average;
            double d3 = average - this.lastReadAvgSpeed;
            double d4 = average2 - this.lastTotalAvgSpeed;
            double d5 = d2 - this.lastOtherAvgSpeed;
            this.lastReadAvgSpeed = average;
            this.lastTotalAvgSpeed = average2;
            this.lastOtherAvgSpeed = d2;
            Logger.i(TAG, "updateThrottleFactor: readAvgSpeed = " + average + "KB/s, totalAvgSpeed = " + average2 + "KB/s, otherAvgSpeed = " + d2 + "KB/s, readAvgSpeedDelta = " + d3 + ", totalAvgSpeedDelta = " + d4 + ", otherAvgSpeedDelta = " + d5 + ", throttleFactor = " + this.throttleFactor);
            int calculatorThrottleFactorTrend = calculatorThrottleFactorTrend(d3, d4, d5);
            if (calculatorThrottleFactorTrend > 0) {
                rightMoveThrottleFactor();
            } else if (calculatorThrottleFactorTrend < 0) {
                leftMoveThrottleFactor();
            } else if (this.throttleFactor < 0.3f) {
                addThrottleFactor(this.throttleFactorStep);
            } else {
                addThrottleFactor(-this.throttleFactorStep);
            }
        }
    }

    private int calculatorThrottleFactorTrend(double d2, double d3, double d4) {
        double d5 = d4 + this.pendingOtherAvgSpeedDelta;
        this.pendingOtherAvgSpeedDelta = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        int i2 = this.otherSpeedDeltaThreshold;
        if (d5 < i2 && d5 >= (-i2)) {
            this.pendingOtherAvgSpeedDelta = d5;
            return d5 > ((double) (((float) (-i2)) / 2.0f)) ? 1 : 0;
        }
        return -1;
    }

    private void leftMoveThrottleFactor() {
        float f2;
        if (this.throttleFactor > 0.3f) {
            f2 = (-this.throttleFactorStep) * 2.0f;
        } else {
            f2 = -this.throttleFactorStep;
        }
        addThrottleFactor(f2);
    }

    private void rightMoveThrottleFactor() {
        float f2;
        if (this.throttleFactor < 0.3f) {
            f2 = this.throttleFactorStep * 2.0f;
        } else {
            f2 = this.throttleFactorStep;
        }
        addThrottleFactor(f2);
    }

    private void addThrottleFactor(float f2) {
        float max;
        if (f2 > 0.0f) {
            if (this.throttleFactor >= THROTTLE_FACTOR_MAX) {
                f2 = -this.throttleFactorStep;
            }
        } else if (f2 < 0.0f && this.throttleFactor <= 0.0f) {
            f2 = this.throttleFactorStep;
        }
        float f3 = this.throttleFactor + f2;
        this.throttleFactor = f3;
        if (f2 > 0.0f) {
            this.throttleFactorMoveTrend = 1;
            max = Math.min(f3, (float) THROTTLE_FACTOR_MAX);
        } else {
            this.throttleFactorMoveTrend = -1;
            max = Math.max(0.0f, f3);
        }
        this.throttleFactor = max;
    }
}
