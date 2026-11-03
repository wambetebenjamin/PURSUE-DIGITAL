package com.bytedance.pipo.payment.common.lib.support.impl;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* compiled from: ExecutorsService.java */
/* loaded from: classes2.dex */
public class c implements com.bytedance.pipo.payment.common.lib.support.service.c {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10952a = 3;

    /* renamed from: b  reason: collision with root package name */
    private final Executor f10953b = Executors.newFixedThreadPool(3);

    /* renamed from: c  reason: collision with root package name */
    private final Executor f10954c = new a();

    /* compiled from: ExecutorsService.java */
    /* loaded from: classes2.dex */
    private static class a implements Executor {

        /* renamed from: a  reason: collision with root package name */
        private Handler f10955a;

        private a() {
            this.f10955a = new Handler(Looper.getMainLooper());
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.f10955a.post(runnable);
        }
    }

    @Override // com.bytedance.pipo.payment.common.lib.support.service.c
    public Executor a() {
        return this.f10954c;
    }

    @Override // com.bytedance.pipo.payment.common.lib.support.service.c
    public Executor b() {
        return this.f10953b;
    }
}
