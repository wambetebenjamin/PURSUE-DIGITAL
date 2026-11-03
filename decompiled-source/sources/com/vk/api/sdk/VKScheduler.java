package com.vk.api.sdk;

import android.os.Handler;
import android.os.Looper;
import com.bytedance.apm.constant.k;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
/* compiled from: VKScheduler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/vk/api/sdk/VKScheduler;", "", "()V", "NETWORK_THREADS_COUNT", "", "counter", "Ljava/util/concurrent/atomic/AtomicInteger;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "Lkotlin/Lazy;", "networkExecutor", "Ljava/util/concurrent/ExecutorService;", "runOnMainThread", "", "runnable", "Ljava/lang/Runnable;", k.RESPONSE_DELAY, "", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class VKScheduler {
    private static final int NETWORK_THREADS_COUNT = 32;
    public static final ExecutorService networkExecutor;
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(VKScheduler.class), "handler", "getHandler()Landroid/os/Handler;"))};
    public static final VKScheduler INSTANCE = new VKScheduler();
    private static final AtomicInteger counter = new AtomicInteger();
    private static final Lazy handler$delegate = LazyKt.lazy(new Function0<Handler>() { // from class: com.vk.api.sdk.VKScheduler$handler$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            return new Handler(Looper.getMainLooper());
        }
    });

    private final Handler getHandler() {
        Lazy lazy = handler$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (Handler) lazy.getValue();
    }

    @JvmStatic
    public static final void runOnMainThread(Runnable runnable) {
        runOnMainThread$default(runnable, 0L, 2, null);
    }

    static {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(32, new ThreadFactory() { // from class: com.vk.api.sdk.VKScheduler$networkExecutor$1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                AtomicInteger atomicInteger;
                StringBuilder append = new StringBuilder().append("vk-network-thread-");
                VKScheduler vKScheduler = VKScheduler.INSTANCE;
                atomicInteger = VKScheduler.counter;
                return new Thread(runnable, append.append(atomicInteger.getAndIncrement()).toString());
            }
        });
        if (newFixedThreadPool == null) {
            Intrinsics.throwNpe();
        }
        networkExecutor = newFixedThreadPool;
    }

    private VKScheduler() {
    }

    @JvmStatic
    public static /* synthetic */ void runOnMainThread$default(Runnable runnable, long j2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            j2 = 0;
        }
        runOnMainThread(runnable, j2);
    }

    @JvmStatic
    public static final void runOnMainThread(Runnable runnable, long j2) {
        Intrinsics.checkParameterIsNotNull(runnable, "runnable");
        if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper()) && j2 == 0) {
            runnable.run();
        } else {
            INSTANCE.getHandler().postDelayed(runnable, j2);
        }
    }
}
