package com.ss.thor;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: Thor.java */
/* loaded from: classes9.dex */
public class b {
    public static final int NO_C = 3;
    public static final int NO_I = 1;
    public static final int NO_U = 2;
    public static final int NO_UI = 4;
    public static final int NO_UIC = 5;
    public static final String TAG = "Thor";

    /* renamed from: a  reason: collision with root package name */
    private static final int f18777a = 3000;

    /* renamed from: b  reason: collision with root package name */
    private static HandlerThread f18778b = null;

    /* renamed from: c  reason: collision with root package name */
    private static a f18779c = null;

    /* renamed from: d  reason: collision with root package name */
    private static c f18780d = null;

    /* renamed from: e  reason: collision with root package name */
    private static int f18781e = 0;

    /* renamed from: f  reason: collision with root package name */
    private static ExecutorService f18782f = null;

    /* renamed from: g  reason: collision with root package name */
    private static AtomicInteger f18783g = new AtomicInteger(0);

    /* renamed from: h  reason: collision with root package name */
    private static final int f18784h = 30;

    public static void start(Context context, c cVar) {
        start(context, cVar, 1, 3000);
    }

    public static void start(Context context, c cVar, int i2, int i3) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (f18778b == null) {
            HandlerThread handlerThread = new HandlerThread("Thor-Handler-Thread");
            f18778b = handlerThread;
            handlerThread.start();
            f18779c = new a(f18778b.getLooper());
            f18780d = cVar;
            cVar.onStart();
            f18781e = i3;
        }
        a aVar = f18779c;
        aVar.sendMessage(aVar.obtainMessage(i2, context));
    }

    public static void stop() {
        HandlerThread handlerThread = f18778b;
        if (handlerThread != null) {
            handlerThread.quit();
            f18780d.onStop();
            f18778b = null;
            f18779c = null;
            f18780d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Thor.java */
    /* loaded from: classes9.dex */
    public static class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (b.f18780d == null) {
                return;
            }
            int i2 = message.what;
            Context context = (Context) message.obj;
            if (i2 == 1) {
                b.f18780d.onUpdate(d.getGalvanicNow(context), -1.0f, -1L);
            } else if (i2 == 2) {
                b.f18780d.onUpdate(-1.0f, d.getVoltage(context), -1L);
            } else if (i2 == 3) {
                b.f18780d.onUpdate(-1.0f, -1.0f, d.getCapacity(context));
            } else if (i2 != 4) {
                if (i2 == 5) {
                    b.f18780d.onUpdate(d.getGalvanicNow(context), d.getVoltage(context), d.getCapacity(context));
                }
                sendMessageDelayed(obtainMessage(i2, context), b.f18781e);
            } else {
                b.f18780d.onUpdate(d.getGalvanicNow(context), d.getVoltage(context), -1L);
            }
            sendMessageDelayed(obtainMessage(i2, context), b.f18781e);
        }
    }

    public static ExecutorService getExecutorService() {
        if (f18782f == null) {
            synchronized (b.class) {
                if (f18782f == null) {
                    f18782f = d();
                }
            }
        }
        return f18782f;
    }

    private static ThreadFactory a(final String str, final boolean z, final int i2) {
        return new ThreadFactory() { // from class: com.ss.thor.b.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(final Runnable runnable) {
                Thread thread = new Thread(new Runnable() { // from class: com.ss.thor.b.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Process.setThreadPriority(i2);
                        runnable.run();
                    }
                });
                thread.setName(str + "-" + b.f18783g.incrementAndGet());
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    private static ExecutorService d() {
        return Executors.newSingleThreadExecutor(a(TAG, false, 0));
    }
}
