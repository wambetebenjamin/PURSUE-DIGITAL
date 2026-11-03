package com.bytedance.applog.monitor;

import com.bytedance.applog.p;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
/* compiled from: LogReportMonitor.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2826a = "REQUEST_FAILED_EXCEPTION";

    /* renamed from: b  reason: collision with root package name */
    public static final String f2827b = "REQUEST_FAILED_RESPONSE_INVALID";

    /* renamed from: c  reason: collision with root package name */
    public static final String f2828c = "REQUEST_FAILED_RESPONSE_EMPTY";

    /* renamed from: d  reason: collision with root package name */
    public static final String f2829d = "REQUEST_FAILED_ENCRYPT_EXCEPTION";

    /* renamed from: e  reason: collision with root package name */
    public static final String f2830e = "DB_DELETE_LOG_FAILED";

    /* renamed from: f  reason: collision with root package name */
    public static final String f2831f = "DB_DELETE_LOG_ERROR";

    /* renamed from: g  reason: collision with root package name */
    private final CopyOnWriteArraySet<p> f2832g = new CopyOnWriteArraySet<>();

    public void a(String str, String str2) {
        Iterator<p> it = this.f2832g.iterator();
        while (it.hasNext()) {
            it.next().a(str, str2);
        }
    }

    public void a(String str, Throwable th) {
        if (this.f2832g.isEmpty() || th == null) {
            return;
        }
        String th2 = th.toString();
        Iterator<p> it = this.f2832g.iterator();
        while (it.hasNext()) {
            it.next().b(str, th2);
        }
    }

    public void b(String str, String str2) {
        if (this.f2832g.isEmpty()) {
            return;
        }
        Iterator<p> it = this.f2832g.iterator();
        while (it.hasNext()) {
            it.next().b(str, str2);
        }
    }

    public void a(p pVar) {
        if (pVar == null) {
            return;
        }
        this.f2832g.add(pVar);
    }

    public void b(p pVar) {
        if (pVar == null) {
            return;
        }
        this.f2832g.remove(pVar);
    }
}
