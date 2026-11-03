package com.ss.android.deviceregister.base;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.ss.android.deviceregister.u;
import java.util.concurrent.CountDownLatch;
/* compiled from: ServiceBlockBinder.java */
/* loaded from: classes9.dex */
final class f<SERVICE, RESULT> {

    /* renamed from: a  reason: collision with root package name */
    private final CountDownLatch f18154a = new CountDownLatch(1);

    /* renamed from: b  reason: collision with root package name */
    private final Intent f18155b;

    /* renamed from: c  reason: collision with root package name */
    private final b<SERVICE, RESULT> f18156c;

    /* renamed from: d  reason: collision with root package name */
    private final Context f18157d;

    /* compiled from: ServiceBlockBinder.java */
    /* loaded from: classes9.dex */
    interface b<T, RESULT> {
        T asInterface(IBinder iBinder);

        RESULT fetchResult(T t);
    }

    f(Context context, Intent intent, b<SERVICE, RESULT> bVar) {
        this.f18157d = context;
        this.f18155b = intent;
        this.f18156c = bVar;
    }

    RESULT a() {
        f<SERVICE, RESULT>.a aVar;
        try {
            aVar = new a(this.f18154a, this.f18156c);
            this.f18157d.bindService(this.f18155b, aVar, 1);
            this.f18154a.await();
            try {
                return this.f18156c.fetchResult(aVar.f18158a);
            } catch (Throwable th) {
                th = th;
                try {
                    th.printStackTrace();
                    return null;
                } finally {
                    a(aVar);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            aVar = null;
        }
    }

    private void a(f<SERVICE, RESULT>.a aVar) {
        if (aVar != null) {
            try {
                this.f18157d.unbindService(aVar);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* compiled from: ServiceBlockBinder.java */
    /* loaded from: classes9.dex */
    class a implements ServiceConnection {

        /* renamed from: a  reason: collision with root package name */
        SERVICE f18158a;

        /* renamed from: c  reason: collision with root package name */
        private final CountDownLatch f18160c;

        /* renamed from: d  reason: collision with root package name */
        private final b<SERVICE, RESULT> f18161d;

        a(CountDownLatch countDownLatch, b<SERVICE, RESULT> bVar) {
            this.f18160c = countDownLatch;
            this.f18161d = bVar;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            u.b(u.f18267a, "ServiceBlockBinder#onServiceConnected " + componentName);
            try {
                this.f18158a = this.f18161d.asInterface(iBinder);
                this.f18160c.countDown();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            u.b(u.f18267a, "ServiceBlockBinder#onServiceDisconnected" + componentName);
            try {
                this.f18160c.countDown();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
