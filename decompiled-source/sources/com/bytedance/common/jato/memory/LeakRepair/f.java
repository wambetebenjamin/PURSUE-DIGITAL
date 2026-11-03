package com.bytedance.common.jato.memory.LeakRepair;

import android.os.SystemClock;
import android.util.Log;
import com.facebook.internal.AnalyticsEvents;
import java.lang.ref.WeakReference;
/* compiled from: RefWatcher.java */
/* loaded from: classes.dex */
public class f<T> {

    /* renamed from: h  reason: collision with root package name */
    private static final int f4863h = 3;

    /* renamed from: i  reason: collision with root package name */
    private static long[] f4864i = new long[3];

    /* renamed from: a  reason: collision with root package name */
    private WeakReference<T> f4865a;

    /* renamed from: c  reason: collision with root package name */
    private String f4867c;

    /* renamed from: d  reason: collision with root package name */
    private String f4868d = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;

    /* renamed from: e  reason: collision with root package name */
    private int f4869e = 0;

    /* renamed from: f  reason: collision with root package name */
    private boolean f4870f = false;

    /* renamed from: g  reason: collision with root package name */
    private boolean f4871g = false;

    /* renamed from: j  reason: collision with root package name */
    private long f4872j = 0;

    /* renamed from: b  reason: collision with root package name */
    private WeakReference<Object> f4866b = new WeakReference<>(new Object());

    public f(T t) {
        this.f4867c = t.getClass().getName();
        this.f4865a = new WeakReference<>(t);
    }

    public String a() {
        return this.f4867c;
    }

    public WeakReference<T> b() {
        return this.f4865a;
    }

    private boolean i() {
        return this.f4866b.get() == null;
    }

    public boolean c() {
        return this.f4865a.get() == null;
    }

    public boolean d() {
        return this.f4870f;
    }

    public boolean e() {
        return this.f4871g;
    }

    public void a(String str) {
        this.f4868d = str;
    }

    public String f() {
        return this.f4868d;
    }

    public void g() {
        this.f4870f = true;
    }

    public void h() {
        if (this.f4871g || this.f4870f || c()) {
            return;
        }
        if (this.f4869e < 3) {
            if (i()) {
                f4864i[this.f4869e] = SystemClock.uptimeMillis();
                this.f4869e++;
                this.f4866b = new WeakReference<>(new Object());
                if (d.f4834b) {
                    Log.d(d.f4833a, String.format("reference reclaimed by GC, obj:%s maybe leaked. %s", this.f4868d, Integer.valueOf(this.f4869e)));
                }
            }
        } else if (SystemClock.uptimeMillis() - f4864i[0] >= this.f4872j) {
            this.f4871g = true;
        }
    }

    public void a(long j2) {
        this.f4872j = j2;
    }
}
