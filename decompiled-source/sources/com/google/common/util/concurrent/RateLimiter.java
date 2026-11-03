package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.SmoothRateLimiter;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public abstract class RateLimiter {
    @CheckForNull
    private volatile Object mutexDoNotUseDirectly;
    private final SleepingStopwatch stopwatch;

    abstract double doGetRate();

    abstract void doSetRate(double d2, long j2);

    abstract long queryEarliestAvailable(long j2);

    abstract long reserveEarliestAvailable(int i2, long j2);

    public static RateLimiter create(double d2) {
        return create(d2, SleepingStopwatch.createFromSystemTimer());
    }

    static RateLimiter create(double d2, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothBursty smoothBursty = new SmoothRateLimiter.SmoothBursty(sleepingStopwatch, 1.0d);
        smoothBursty.setRate(d2);
        return smoothBursty;
    }

    public static RateLimiter create(double d2, long j2, TimeUnit timeUnit) {
        Preconditions.checkArgument(j2 >= 0, "warmupPeriod must not be negative: %s", j2);
        return create(d2, j2, timeUnit, 3.0d, SleepingStopwatch.createFromSystemTimer());
    }

    static RateLimiter create(double d2, long j2, TimeUnit timeUnit, double d3, SleepingStopwatch sleepingStopwatch) {
        SmoothRateLimiter.SmoothWarmingUp smoothWarmingUp = new SmoothRateLimiter.SmoothWarmingUp(sleepingStopwatch, j2, timeUnit, d3);
        smoothWarmingUp.setRate(d2);
        return smoothWarmingUp;
    }

    private Object mutex() {
        Object obj = this.mutexDoNotUseDirectly;
        if (obj == null) {
            synchronized (this) {
                obj = this.mutexDoNotUseDirectly;
                if (obj == null) {
                    obj = new Object();
                    this.mutexDoNotUseDirectly = obj;
                }
            }
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RateLimiter(SleepingStopwatch sleepingStopwatch) {
        this.stopwatch = (SleepingStopwatch) Preconditions.checkNotNull(sleepingStopwatch);
    }

    public final void setRate(double d2) {
        Preconditions.checkArgument(d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && !Double.isNaN(d2), "rate must be positive");
        synchronized (mutex()) {
            doSetRate(d2, this.stopwatch.readMicros());
        }
    }

    public final double getRate() {
        double doGetRate;
        synchronized (mutex()) {
            doGetRate = doGetRate();
        }
        return doGetRate;
    }

    public double acquire() {
        return acquire(1);
    }

    public double acquire(int i2) {
        long reserve = reserve(i2);
        this.stopwatch.sleepMicrosUninterruptibly(reserve);
        return (reserve * 1.0d) / TimeUnit.SECONDS.toMicros(1L);
    }

    final long reserve(int i2) {
        long reserveAndGetWaitLength;
        checkPermits(i2);
        synchronized (mutex()) {
            reserveAndGetWaitLength = reserveAndGetWaitLength(i2, this.stopwatch.readMicros());
        }
        return reserveAndGetWaitLength;
    }

    public boolean tryAcquire(long j2, TimeUnit timeUnit) {
        return tryAcquire(1, j2, timeUnit);
    }

    public boolean tryAcquire(int i2) {
        return tryAcquire(i2, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire() {
        return tryAcquire(1, 0L, TimeUnit.MICROSECONDS);
    }

    public boolean tryAcquire(int i2, long j2, TimeUnit timeUnit) {
        long max = Math.max(timeUnit.toMicros(j2), 0L);
        checkPermits(i2);
        synchronized (mutex()) {
            long readMicros = this.stopwatch.readMicros();
            if (canAcquire(readMicros, max)) {
                this.stopwatch.sleepMicrosUninterruptibly(reserveAndGetWaitLength(i2, readMicros));
                return true;
            }
            return false;
        }
    }

    private boolean canAcquire(long j2, long j3) {
        return queryEarliestAvailable(j2) - j3 <= j2;
    }

    final long reserveAndGetWaitLength(int i2, long j2) {
        return Math.max(reserveEarliestAvailable(i2, j2) - j2, 0L);
    }

    public String toString() {
        return String.format(Locale.ROOT, "RateLimiter[stableRate=%3.1fqps]", Double.valueOf(getRate()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static abstract class SleepingStopwatch {
        protected abstract long readMicros();

        protected abstract void sleepMicrosUninterruptibly(long j2);

        protected SleepingStopwatch() {
        }

        public static SleepingStopwatch createFromSystemTimer() {
            return new SleepingStopwatch() { // from class: com.google.common.util.concurrent.RateLimiter.SleepingStopwatch.1
                final Stopwatch stopwatch = Stopwatch.createStarted();

                @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
                protected long readMicros() {
                    return this.stopwatch.elapsed(TimeUnit.MICROSECONDS);
                }

                @Override // com.google.common.util.concurrent.RateLimiter.SleepingStopwatch
                protected void sleepMicrosUninterruptibly(long j2) {
                    if (j2 > 0) {
                        Uninterruptibles.sleepUninterruptibly(j2, TimeUnit.MICROSECONDS);
                    }
                }
            };
        }
    }

    private static void checkPermits(int i2) {
        Preconditions.checkArgument(i2 > 0, "Requested permits (%s) must be positive", i2);
    }
}
