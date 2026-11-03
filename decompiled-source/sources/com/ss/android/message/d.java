package com.ss.android.message;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.bytedance.common.utility.collection.WeakHandler;
import java.util.HashSet;
import java.util.Set;
/* compiled from: PushThreadHandlerManager.java */
/* loaded from: classes9.dex */
public class d implements WeakHandler.IHandler {

    /* renamed from: a  reason: collision with root package name */
    private static volatile d f18313a = null;

    /* renamed from: c  reason: collision with root package name */
    private static HandlerThread f18314c = null;

    /* renamed from: d  reason: collision with root package name */
    private static boolean f18315d = false;

    /* renamed from: b  reason: collision with root package name */
    private final WeakHandler f18316b;

    /* renamed from: e  reason: collision with root package name */
    private final Set<Handler.Callback> f18317e = new HashSet();

    public static void setStartedHandlerThread(HandlerThread handlerThread) {
        if (f18315d || handlerThread == null) {
            return;
        }
        f18314c = handlerThread;
    }

    public void a(Handler.Callback callback) {
        synchronized (this.f18317e) {
            this.f18317e.add(callback);
        }
    }

    public static d inst() {
        if (f18313a == null) {
            synchronized (d.class) {
                if (f18313a == null) {
                    f18313a = new d();
                }
            }
        }
        return f18313a;
    }

    private d() {
        if (f18314c == null) {
            HandlerThread handlerThread = new HandlerThread("PushThreadHandler");
            f18314c = handlerThread;
            handlerThread.start();
            f18315d = true;
        }
        this.f18316b = new WeakHandler(f18314c.getLooper(), this);
    }

    @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
    public void handleMsg(Message message) {
        synchronized (this.f18317e) {
            for (Handler.Callback callback : this.f18317e) {
                if (callback.handleMessage(message)) {
                    return;
                }
            }
        }
    }

    public Looper getLooper() {
        return f18314c.getLooper();
    }

    public WeakHandler getHandler() {
        return this.f18316b;
    }

    public WeakHandler b(Handler.Callback callback) {
        a(callback);
        return getHandler();
    }

    public void postRunnable(Runnable runnable) {
        postRunnable(runnable, 0L);
    }

    public void postRunnable(Runnable runnable, long j2) {
        if (j2 <= 0) {
            if (Looper.myLooper() != this.f18316b.getLooper()) {
                this.f18316b.post(runnable);
                return;
            } else if (runnable != null) {
                runnable.run();
                return;
            } else {
                return;
            }
        }
        this.f18316b.postDelayed(runnable, j2);
    }
}
