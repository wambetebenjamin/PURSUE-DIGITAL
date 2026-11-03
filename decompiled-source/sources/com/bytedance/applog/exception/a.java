package com.bytedance.applog.exception;

import android.os.Process;
import java.lang.Thread;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
/* compiled from: GlobalExceptionHandler.java */
/* loaded from: classes.dex */
public class a implements Thread.UncaughtExceptionHandler {

    /* renamed from: c  reason: collision with root package name */
    private static final com.bytedance.applog.log.a<a> f2647c = new com.bytedance.applog.log.a<a>() { // from class: com.bytedance.applog.exception.a.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.applog.log.a
        /* renamed from: a */
        public a create(Object... objArr) {
            return new a();
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final CopyOnWriteArraySet<b> f2648a;

    /* renamed from: b  reason: collision with root package name */
    private final Thread.UncaughtExceptionHandler f2649b;

    public static a a() {
        return f2647c.get(new Object[0]);
    }

    private a() {
        this.f2648a = new CopyOnWriteArraySet<>();
        this.f2649b = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void a(b bVar) {
        this.f2648a.add(bVar);
    }

    public void b(b bVar) {
        this.f2648a.remove(bVar);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Iterator<b> it = this.f2648a.iterator();
        while (it.hasNext()) {
            it.next().a(thread, th);
        }
        a(thread, th);
    }

    private void a(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f2649b;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        try {
            Process.killProcess(Process.myPid());
            System.exit(10);
        } catch (Throwable unused) {
        }
    }
}
