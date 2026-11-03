package com.bytedance.crash.runtime;

import android.os.Looper;
import android.os.SystemClock;
import android.util.Printer;
import com.bytedance.crash.util.y;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/* compiled from: LooperMessageManager.java */
@Deprecated
/* loaded from: classes2.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static final char f7056a = '>';

    /* renamed from: b  reason: collision with root package name */
    private static final char f7057b = '<';

    /* renamed from: c  reason: collision with root package name */
    private static Printer f7058c = null;

    /* renamed from: d  reason: collision with root package name */
    private static k f7059d = null;

    /* renamed from: e  reason: collision with root package name */
    private static final long f7060e = 5000;

    /* renamed from: i  reason: collision with root package name */
    private static final Printer f7061i = new Printer() { // from class: com.bytedance.crash.runtime.k.1
        @Override // android.util.Printer
        public void println(String str) {
            if (str == null) {
                return;
            }
            if (str.charAt(0) == '>') {
                k.getInstance().a(str);
            } else if (str.charAt(0) == '<') {
                k.getInstance().b(str);
            }
            if (k.f7058c == null || k.f7058c == k.f7061i) {
                return;
            }
            k.f7058c.println(str);
        }
    };

    /* renamed from: f  reason: collision with root package name */
    private long f7062f = -1;

    /* renamed from: g  reason: collision with root package name */
    private final List<Printer> f7063g = new ArrayList();

    /* renamed from: h  reason: collision with root package name */
    private final List<Printer> f7064h = new ArrayList();

    /* renamed from: j  reason: collision with root package name */
    private boolean f7065j;

    private k() {
    }

    public static k getInstance() {
        if (f7059d == null) {
            synchronized (k.class) {
                if (f7059d == null) {
                    f7059d = new k();
                }
            }
        }
        return f7059d;
    }

    public void start() {
        if (this.f7065j) {
            return;
        }
        this.f7065j = true;
        Printer c2 = c();
        f7058c = c2;
        Printer printer = f7061i;
        if (c2 == printer) {
            f7058c = null;
        }
        Looper.getMainLooper().setMessageLogging(printer);
    }

    private Printer c() {
        try {
            Field declaredField = Class.forName("android.os.Looper").getDeclaredField("mLogging");
            declaredField.setAccessible(true);
            return (Printer) declaredField.get(Looper.getMainLooper());
        } catch (Exception e2) {
            y.w(e2);
            return null;
        }
    }

    public void stop() {
        if (this.f7065j) {
            this.f7065j = false;
            if (c() != f7061i || f7058c == null) {
                return;
            }
            Looper.getMainLooper().setMessageLogging(f7058c);
        }
    }

    public boolean isStarted() {
        return this.f7065j;
    }

    public void registerSyncEndPrinter(Printer printer) {
        this.f7064h.add(printer);
    }

    public synchronized void registerSyncStartPrinter(Printer printer) {
        this.f7063g.add(printer);
    }

    public boolean noMessageDispatch() {
        return this.f7062f != -1 && SystemClock.uptimeMillis() - this.f7062f > 5000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.f7062f = -1L;
        try {
            a(this.f7063g, str);
        } catch (Exception e2) {
            y.e(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        this.f7062f = SystemClock.uptimeMillis();
        try {
            a(this.f7064h, str);
        } catch (Exception e2) {
            y.w(e2);
        }
    }

    private static void a(List<? extends Printer> list, String str) {
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                Printer printer = list.get(i2);
                if (printer == null) {
                    return;
                }
                printer.println(str);
            }
        } catch (Throwable th) {
            y.e(th);
        }
    }
}
