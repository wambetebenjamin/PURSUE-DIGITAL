package com.bytedance.ug.sdk.share.impl.network.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: ThreadPlus.java */
/* loaded from: classes8.dex */
public class c implements Runnable {

    /* renamed from: d  reason: collision with root package name */
    private Runnable f15891d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f15892e;

    /* renamed from: b  reason: collision with root package name */
    private static ExecutorService f15889b = a.getNormalExecutor();

    /* renamed from: c  reason: collision with root package name */
    private static ExecutorService f15890c = a.getNormalExecutor();

    /* renamed from: a  reason: collision with root package name */
    protected static final AtomicInteger f15888a = new AtomicInteger();

    public static void shutdown() {
    }

    public c(Runnable runnable, String str, boolean z) {
        this.f15891d = runnable;
        this.f15892e = z;
    }

    public c(String str) {
        this(false);
    }

    public c() {
        this(false);
    }

    public c(boolean z) {
        this.f15892e = z;
    }

    public static void setExecutorService(ExecutorService executorService) {
        f15889b = executorService;
        f15890c = executorService;
    }

    @Override // java.lang.Runnable
    public void run() {
        Runnable runnable = this.f15891d;
        if (runnable != null) {
            runnable.run();
        }
    }

    public void start() {
        if (this.f15892e) {
            f15890c.submit(this);
        } else {
            f15889b.submit(this);
        }
    }

    public static void submitRunnable(Runnable runnable) {
        if (runnable != null) {
            f15889b.submit(runnable);
        }
    }
}
