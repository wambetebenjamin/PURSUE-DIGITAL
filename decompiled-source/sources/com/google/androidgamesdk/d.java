package com.google.androidgamesdk;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes6.dex */
final class d extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public Handler f16891a;

    /* renamed from: b  reason: collision with root package name */
    private ReentrantLock f16892b;

    /* renamed from: c  reason: collision with root package name */
    private Condition f16893c;

    private d() {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.f16892b = reentrantLock;
        this.f16893c = reentrantLock.newCondition();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Log.i("SwappyDisplayManager", "Starting looper thread");
        this.f16892b.lock();
        Looper.prepare();
        this.f16891a = new Handler();
        this.f16893c.signal();
        this.f16892b.unlock();
        Looper.loop();
        Log.i("SwappyDisplayManager", "Terminating looper thread");
    }

    @Override // java.lang.Thread
    public final void start() {
        this.f16892b.lock();
        super.start();
        try {
            this.f16893c.await();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        this.f16892b.unlock();
    }
}
