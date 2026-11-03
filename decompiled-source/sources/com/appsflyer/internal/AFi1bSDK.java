package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ/\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0007¢\u0006\u0004\b\u000b\u0010\f"}, d2 = {"Lcom/appsflyer/internal/AFi1bSDK;", "", "Ljava/util/concurrent/ScheduledExecutorService;", "p0", "Ljava/lang/Runnable;", "p1", "", "p2", "Ljava/util/concurrent/TimeUnit;", "p3", "", "AFInAppEventType", "(Ljava/util/concurrent/ScheduledExecutorService;Ljava/lang/Runnable;JLjava/util/concurrent/TimeUnit;)V", "<init>", "()V"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AFi1bSDK {
    public static final AFi1bSDK INSTANCE = new AFi1bSDK();

    private AFi1bSDK() {
    }

    @JvmStatic
    public static final void AFInAppEventType(ScheduledExecutorService scheduledExecutorService, Runnable runnable, long j2, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(scheduledExecutorService, "");
        Intrinsics.checkNotNullParameter(runnable, "");
        Intrinsics.checkNotNullParameter(timeUnit, "");
        try {
            scheduledExecutorService.schedule(runnable, j2, timeUnit);
        } catch (RejectedExecutionException e2) {
            AFLogger.afErrorLog("scheduleJob failed with RejectedExecutionException Exception", e2);
        } catch (Throwable th) {
            AFLogger.afErrorLog("scheduleJob failed with Exception", th);
        }
    }
}
