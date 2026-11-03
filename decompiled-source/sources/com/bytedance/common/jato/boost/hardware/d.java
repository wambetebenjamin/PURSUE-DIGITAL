package com.bytedance.common.jato.boost.hardware;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.Method;
/* compiled from: MtkCpuBoost.java */
/* loaded from: classes.dex */
public class d implements c {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4607a = 23;

    /* renamed from: b  reason: collision with root package name */
    private static final int f4608b = 4;

    /* renamed from: c  reason: collision with root package name */
    private static final int f4609c = 8;

    /* renamed from: d  reason: collision with root package name */
    private Object f4610d;

    /* renamed from: e  reason: collision with root package name */
    private Method f4611e;

    /* renamed from: f  reason: collision with root package name */
    private Method f4612f;

    /* renamed from: g  reason: collision with root package name */
    private Method f4613g;

    /* renamed from: h  reason: collision with root package name */
    private Method f4614h;

    /* renamed from: i  reason: collision with root package name */
    private a f4615i;

    @Override // com.bytedance.common.jato.boost.hardware.c
    public boolean b(long j2) {
        return false;
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public void a(a aVar, Context context) {
        this.f4615i = aVar;
        try {
            Class<?> cls = Class.forName("com.mediatek.perfservice.PerfServiceWrapper");
            this.f4610d = cls.getConstructor(new Class[0]).newInstance(new Object[0]);
            this.f4611e = cls.getDeclaredMethod("userRegScn", new Class[0]);
            this.f4612f = cls.getDeclaredMethod("userRegScnConfig", Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
            this.f4613g = cls.getDeclaredMethod("userEnable", Integer.TYPE);
            this.f4614h = cls.getDeclaredMethod("userResetAll", new Class[0]);
        } catch (Throwable th) {
            this.f4615i.b("cpuboost init fail: " + th.getMessage());
        }
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public boolean a(long j2) {
        return a(j2, 23) || a(j2, 4);
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public boolean c(long j2) {
        return a(j2, 8);
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public void a() {
        try {
            this.f4614h.invoke(this.f4610d, new Object[0]);
        } catch (Throwable th) {
            this.f4615i.b("cpuboost release fail" + th.getMessage());
        }
    }

    private boolean a(long j2, int i2) {
        if (j2 <= 0) {
            return false;
        }
        try {
            int intValue = ((Integer) this.f4611e.invoke(this.f4610d, new Object[0])).intValue();
            this.f4612f.invoke(this.f4610d, Integer.valueOf(intValue), Integer.valueOf(i2), 1000, 0, 0, 0);
            this.f4613g.invoke(this.f4610d, Integer.valueOf(intValue));
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.bytedance.common.jato.boost.hardware.d.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        d.this.f4614h.invoke(d.this.f4610d, new Object[0]);
                    } catch (Throwable th) {
                        d.this.f4615i.b("cpuboost boost fail:" + th.getMessage());
                    }
                }
            }, j2);
            return true;
        } catch (Throwable th) {
            this.f4615i.b("cpuboost boost fail: " + th.getMessage());
            return false;
        }
    }
}
