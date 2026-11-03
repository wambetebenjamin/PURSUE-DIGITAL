package com.vk.api.sdk.utils;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ExponentialBackoff.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0016\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u0006\u0010\u001a\u001a\u00020\u0018J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0006H\u0002R$\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/vk/api/sdk/utils/ExponentialBackoff;", "", "minDelayMs", "", "maxDelayMs", "factor", "", "jitter", "(JJFF)V", "<set-?>", "delayMs", "getDelayMs", "()J", "setDelayMs", "(J)V", "", "errorsCount", "getErrorsCount", "()I", "setErrorsCount", "(I)V", "random", "Ljava/util/Random;", "increase", "", "onError", "reset", "shouldWait", "", "variance", "std", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public class ExponentialBackoff {
    public static final Companion Companion = new Companion(null);
    private long delayMs;
    private int errorsCount;
    private final float factor;
    private final float jitter;
    private final long maxDelayMs;
    private final long minDelayMs;
    private final Random random;

    public ExponentialBackoff() {
        this(0L, 0L, 0.0f, 0.0f, 15, null);
    }

    @JvmStatic
    public static final ExponentialBackoff forNetworkWait() {
        return Companion.forNetworkWait();
    }

    public ExponentialBackoff(long j2, long j3, float f2, float f3) {
        this.minDelayMs = j2;
        this.maxDelayMs = j3;
        this.factor = f2;
        this.jitter = f3;
        this.random = new Random(System.currentTimeMillis());
        this.delayMs = j2;
    }

    public /* synthetic */ ExponentialBackoff(long j2, long j3, float f2, float f3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? TimeUnit.MILLISECONDS.toMillis(100L) : j2, (i2 & 2) != 0 ? TimeUnit.MINUTES.toMillis(5L) : j3, (i2 & 4) != 0 ? 2.0f : f2, (i2 & 8) != 0 ? 0.1f : f3);
    }

    private final void setDelayMs(long j2) {
        this.delayMs = j2;
    }

    public final long getDelayMs() {
        return this.delayMs;
    }

    private final void setErrorsCount(int i2) {
        this.errorsCount = i2;
    }

    public final int getErrorsCount() {
        return this.errorsCount;
    }

    public final boolean shouldWait() {
        return this.errorsCount > 0;
    }

    public final void reset() {
        this.delayMs = this.minDelayMs;
        this.errorsCount = 0;
    }

    public final void onError() {
        increase();
    }

    public final void increase() {
        long min = Math.min(((float) this.delayMs) * this.factor, (float) this.maxDelayMs);
        this.delayMs = min;
        this.delayMs = min + variance(((float) min) * this.jitter);
        this.errorsCount++;
    }

    private final long variance(float f2) {
        return (long) (this.random.nextGaussian() * f2);
    }

    /* compiled from: ExponentialBackoff.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/vk/api/sdk/utils/ExponentialBackoff$Companion;", "", "()V", "forNetworkWait", "Lcom/vk/api/sdk/utils/ExponentialBackoff;", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ExponentialBackoff forNetworkWait() {
            return new ExponentialBackoff(500L, 60000L, 1.5f, 0.0f, 8, null);
        }
    }
}
