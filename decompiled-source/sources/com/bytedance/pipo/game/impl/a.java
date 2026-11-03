package com.bytedance.pipo.game.impl;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* compiled from: AppExecutors.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final a f10217a = new a();

    /* renamed from: b  reason: collision with root package name */
    private static final int f10218b = 3;

    /* renamed from: c  reason: collision with root package name */
    private final Executor f10219c;

    /* renamed from: d  reason: collision with root package name */
    private final Executor f10220d;

    /* renamed from: e  reason: collision with root package name */
    private final Executor f10221e;

    private a(Executor executor, Executor executor2, Executor executor3) {
        this.f10219c = executor;
        this.f10220d = executor2;
        this.f10221e = executor3;
    }

    private a() {
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3), new ExecutorC0143a());
    }

    public static a a() {
        return f10217a;
    }

    public Executor b() {
        return this.f10219c;
    }

    public Executor c() {
        return this.f10220d;
    }

    public Executor d() {
        return this.f10221e;
    }

    /* compiled from: AppExecutors.java */
    /* renamed from: com.bytedance.pipo.game.impl.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static class ExecutorC0143a implements Executor {

        /* renamed from: a  reason: collision with root package name */
        private Handler f10222a;

        private ExecutorC0143a() {
            this.f10222a = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            com.bytedance.pipo.game.impl.util.d.a("AppExecuteors", "execute " + Thread.currentThread().getName());
            this.f10222a.post(runnable);
        }
    }
}
