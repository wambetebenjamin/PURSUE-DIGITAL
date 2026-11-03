package com.bytedance.common.jato.boost.hardware;

import android.content.Context;
import android.os.Build;
import androidx.fragment.app.FragmentTransaction;
import java.lang.reflect.Method;
/* compiled from: HisiCpuBoost.java */
/* loaded from: classes.dex */
public class b implements c {

    /* renamed from: a  reason: collision with root package name */
    private Class f4603a = null;

    /* renamed from: b  reason: collision with root package name */
    private Method f4604b = null;

    /* renamed from: c  reason: collision with root package name */
    private Object f4605c = null;

    /* renamed from: d  reason: collision with root package name */
    private a f4606d;

    @Override // com.bytedance.common.jato.boost.hardware.c
    public void a() {
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public boolean b(long j2) {
        return false;
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public boolean c(long j2) {
        return false;
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public void a(a aVar, Context context) {
        this.f4606d = aVar;
        try {
            if (Build.VERSION.SDK_INT == 29) {
                Class<?> a2 = com.bytedance.common.jato.util.c.a("android.scrollerboostmanager.ScrollerBoostManager");
                Class[] clsArr = null;
                Object[] objArr = null;
                this.f4605c = com.bytedance.common.jato.util.c.a(a2, "getInstance", null).invoke(a2, null);
                this.f4604b = com.bytedance.common.jato.util.c.a(a2, "listFling", Integer.TYPE);
                Class[] clsArr2 = null;
                Object[] objArr2 = null;
                a2.getDeclaredMethod("init", null).invoke(this.f4605c, null);
            } else {
                if ((Build.VERSION.SDK_INT == 28) | (Build.VERSION.SDK_INT == 27) | (Build.VERSION.SDK_INT == 26)) {
                    Class<?> a3 = com.bytedance.common.jato.util.c.a("android.iawareperf.UniPerf");
                    this.f4603a = a3;
                    this.f4604b = com.bytedance.common.jato.util.c.a(a3, "uniPerfEvent", Integer.TYPE, String.class, int[].class);
                    this.f4605c = com.bytedance.common.jato.util.c.a(this.f4603a, "getInstance", new Class[0]).invoke(null, new Object[0]);
                }
            }
        } catch (Throwable th) {
            this.f4606d.b("cpuboost init fail:" + th);
        }
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public boolean a(long j2) {
        if (j2 <= 0) {
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT == 29) {
                this.f4604b.invoke(this.f4605c, Integer.valueOf((int) j2));
            } else {
                if ((Build.VERSION.SDK_INT == 28) | (Build.VERSION.SDK_INT == 27) | (Build.VERSION.SDK_INT == 26)) {
                    ((Integer) this.f4604b.invoke(this.f4605c, Integer.valueOf((int) FragmentTransaction.TRANSIT_FRAGMENT_FADE), "canBoost=true|screenOn=1", new int[0])).intValue();
                }
            }
            return true;
        } catch (Throwable th) {
            this.f4606d.b("cpuboost boost fail:" + th);
            return false;
        }
    }
}
