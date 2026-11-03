package com.bytedance.platform.godzilla.common;

import android.os.Looper;
import java.lang.Thread;
import java.util.LinkedList;
import java.util.List;
/* compiled from: ConsumeExceptionHandler.java */
/* loaded from: classes2.dex */
public class d implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11048a = 20;

    /* renamed from: d  reason: collision with root package name */
    private static final List<m> f11049d = new LinkedList();

    /* renamed from: b  reason: collision with root package name */
    private boolean f11050b;

    /* renamed from: c  reason: collision with root package name */
    private Thread.UncaughtExceptionHandler f11051c;

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        try {
            if (!a(th) && b(thread, th)) {
                if (thread != null && thread.getName().equals("main") && Looper.myLooper() == Looper.getMainLooper()) {
                    while (true) {
                        try {
                            Looper.loop();
                        } catch (Exception e2) {
                            if (a(e2) || !b(thread, e2)) {
                                a(thread, e2);
                                return;
                            }
                        }
                    }
                    a(thread, e2);
                    return;
                }
                return;
            }
            a(thread, th);
        } catch (Throwable th2) {
            a(thread, th2);
        }
    }

    public boolean addExceptionConsumer(m mVar) {
        boolean add;
        List<m> list = f11049d;
        synchronized (list) {
            add = list.add(mVar);
        }
        return add;
    }

    public boolean removeExceptionConsumer(m mVar) {
        boolean remove;
        List<m> list = f11049d;
        synchronized (list) {
            remove = list.remove(mVar);
        }
        return remove;
    }

    public void removeAllExceptionConsumer() {
        List<m> list = f11049d;
        synchronized (list) {
            list.clear();
        }
    }

    private void a(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f11051c;
        if (uncaughtExceptionHandler == null || uncaughtExceptionHandler == this) {
            return;
        }
        uncaughtExceptionHandler.uncaughtException(thread, th);
    }

    public void register() {
        if (this.f11050b) {
            return;
        }
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.f11051c = defaultUncaughtExceptionHandler;
        if (defaultUncaughtExceptionHandler != this) {
            Thread.setDefaultUncaughtExceptionHandler(this);
        } else {
            this.f11051c = null;
        }
        this.f11050b = true;
    }

    private boolean b(Thread thread, Throwable th) {
        List<m> list;
        try {
            list = f11049d;
        } catch (Throwable unused) {
        }
        synchronized (list) {
            for (m mVar : list) {
                if (mVar.consumeUncaughtException(thread, th)) {
                    e.getInstance().addAnalysisResult(thread, th, mVar, true);
                    return true;
                }
                e.getInstance().addAnalysisResult(thread, th, mVar, false);
            }
            return false;
        }
    }

    private boolean a(Throwable th) {
        if (th == null) {
            return false;
        }
        int i2 = 0;
        while (th != null) {
            try {
                if (th instanceof OutOfMemoryError) {
                    return true;
                }
                if (i2 > 20) {
                    return false;
                }
                i2++;
                th = th.getCause();
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    @Deprecated
    public static void addConsumerPluginOutSide(m mVar) {
        List<m> list = f11049d;
        synchronized (list) {
            list.add(mVar);
        }
    }
}
