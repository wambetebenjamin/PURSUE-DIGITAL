package com.google.common.util.concurrent;

import com.google.common.math.LongMath;
import com.google.common.util.concurrent.RateLimiter;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public abstract class SmoothRateLimiter extends RateLimiter {
    double maxPermits;
    private long nextFreeTicketMicros;
    double stableIntervalMicros;
    double storedPermits;

    abstract double coolDownIntervalMicros();

    abstract void doSetRate(double d2, double d3);

    abstract long storedPermitsToWaitTime(double d2, double d3);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class SmoothWarmingUp extends SmoothRateLimiter {
        private double coldFactor;
        private double slope;
        private double thresholdPermits;
        private final long warmupPeriodMicros;

        /* JADX INFO: Access modifiers changed from: package-private */
        public SmoothWarmingUp(RateLimiter.SleepingStopwatch sleepingStopwatch, long j2, TimeUnit timeUnit, double d2) {
            super(sleepingStopwatch);
            this.warmupPeriodMicros = timeUnit.toMicros(j2);
            this.coldFactor = d2;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        void doSetRate(double d2, double d3) {
            double d4;
            double d5 = this.maxPermits;
            double d6 = this.coldFactor * d3;
            long j2 = this.warmupPeriodMicros;
            double d7 = (j2 * 0.5d) / d3;
            this.thresholdPermits = d7;
            this.maxPermits = d7 + ((j2 * 2.0d) / (d3 + d6));
            this.slope = (d6 - d3) / (this.maxPermits - this.thresholdPermits);
            if (d5 == Double.POSITIVE_INFINITY) {
                this.storedPermits = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                return;
            }
            if (d5 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d4 = this.maxPermits;
            } else {
                d4 = (this.storedPermits * this.maxPermits) / d5;
            }
            this.storedPermits = d4;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        long storedPermitsToWaitTime(double d2, double d3) {
            long j2;
            double d4 = d2 - this.thresholdPermits;
            if (d4 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                double min = Math.min(d4, d3);
                j2 = (long) (((permitsToTime(d4) + permitsToTime(d4 - min)) * min) / 2.0d);
                d3 -= min;
            } else {
                j2 = 0;
            }
            return j2 + ((long) (this.stableIntervalMicros * d3));
        }

        private double permitsToTime(double d2) {
            return this.stableIntervalMicros + (d2 * this.slope);
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        double coolDownIntervalMicros() {
            return this.warmupPeriodMicros / this.maxPermits;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class SmoothBursty extends SmoothRateLimiter {
        final double maxBurstSeconds;

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        long storedPermitsToWaitTime(double d2, double d3) {
            return 0L;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public SmoothBursty(RateLimiter.SleepingStopwatch sleepingStopwatch, double d2) {
            super(sleepingStopwatch);
            this.maxBurstSeconds = d2;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        void doSetRate(double d2, double d3) {
            double d4 = this.maxPermits;
            this.maxPermits = this.maxBurstSeconds * d2;
            if (d4 == Double.POSITIVE_INFINITY) {
                this.storedPermits = this.maxPermits;
                return;
            }
            double d5 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            if (d4 != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d5 = (this.storedPermits * this.maxPermits) / d4;
            }
            this.storedPermits = d5;
        }

        @Override // com.google.common.util.concurrent.SmoothRateLimiter
        double coolDownIntervalMicros() {
            return this.stableIntervalMicros;
        }
    }

    private SmoothRateLimiter(RateLimiter.SleepingStopwatch sleepingStopwatch) {
        super(sleepingStopwatch);
        this.nextFreeTicketMicros = 0L;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    final void doSetRate(double d2, long j2) {
        resync(j2);
        double micros = TimeUnit.SECONDS.toMicros(1L) / d2;
        this.stableIntervalMicros = micros;
        doSetRate(d2, micros);
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    final double doGetRate() {
        return TimeUnit.SECONDS.toMicros(1L) / this.stableIntervalMicros;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    final long queryEarliestAvailable(long j2) {
        return this.nextFreeTicketMicros;
    }

    @Override // com.google.common.util.concurrent.RateLimiter
    final long reserveEarliestAvailable(int i2, long j2) {
        resync(j2);
        long j3 = this.nextFreeTicketMicros;
        double d2 = i2;
        double min = Math.min(d2, this.storedPermits);
        this.nextFreeTicketMicros = LongMath.saturatedAdd(this.nextFreeTicketMicros, storedPermitsToWaitTime(this.storedPermits, min) + ((long) ((d2 - min) * this.stableIntervalMicros)));
        this.storedPermits -= min;
        return j3;
    }

    void resync(long j2) {
        long j3 = this.nextFreeTicketMicros;
        if (j2 > j3) {
            this.storedPermits = Math.min(this.maxPermits, this.storedPermits + ((j2 - j3) / coolDownIntervalMicros()));
            this.nextFreeTicketMicros = j2;
        }
    }
}
