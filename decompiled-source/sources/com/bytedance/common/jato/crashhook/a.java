package com.bytedance.common.jato.crashhook;

import java.lang.Thread;
import java.util.ArrayList;
import java.util.Iterator;
/* compiled from: CrashHook.java */
/* loaded from: classes.dex */
public class a implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    private static a f4646a;

    /* renamed from: b  reason: collision with root package name */
    private Thread.UncaughtExceptionHandler f4647b;

    /* renamed from: c  reason: collision with root package name */
    private ArrayList<InterfaceC0057a> f4648c = new ArrayList<>();

    /* compiled from: CrashHook.java */
    /* renamed from: com.bytedance.common.jato.crashhook.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0057a {
        boolean a(Thread thread, Throwable th);
    }

    private a() {
        b();
    }

    public static a a() {
        if (f4646a == null) {
            f4646a = new a();
        }
        return f4646a;
    }

    private void b() {
        Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != this) {
            this.f4647b = defaultUncaughtExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public synchronized void a(InterfaceC0057a interfaceC0057a) {
        this.f4648c.add(interfaceC0057a);
    }

    public synchronized void b(InterfaceC0057a interfaceC0057a) {
        this.f4648c.remove(interfaceC0057a);
    }

    private synchronized void a(Thread thread, Throwable th) {
        Iterator<InterfaceC0057a> it = this.f4648c.iterator();
        while (it.hasNext()) {
            if (it.next().a(thread, th)) {
                return;
            }
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f4647b;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (this.f4647b != null) {
            a(thread, th);
        }
    }
}
