package com.bytedance.common.jato.boost.hardware;

import android.content.Context;
import android.os.Build;
import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: QcmCpuBoost.java */
/* loaded from: classes.dex */
public class e implements c {

    /* renamed from: b  reason: collision with root package name */
    private static final int[] f4617b = {1077936128, 1, 1086324736, 1, 1082130432, 4095, 1082130688, 4095, 1082130944, 4095, 1098907648, 255, 1119944704, 1, 1124073472, 255, 1128267776, 65535, 1115799552, 2000, 1115734016, 1000};

    /* renamed from: c  reason: collision with root package name */
    private static final int[] f4618c = {1077936128, 1, 1086324736, 1, 1082130432, 4095, 1082130688, 4095, 1098907648, 255, 1119944704, 1, 1124073472, 255, 1128267776, 65535, 1115799552, 2000, 1115734016, 1000};

    /* renamed from: d  reason: collision with root package name */
    private static final int[] f4619d = {1077936128, 1, 1086324736, 1, 1082130432, 4095, 1082130688, 4095, 1098907648, 255, 1119944704, 1, 1124073472, 255, 1128267776, 65535, 1115799552, 1, 1115734016, 1000};

    /* renamed from: e  reason: collision with root package name */
    private static final int[] f4620e = {1077936128, 1, 1086324736, 1, 1082130432, 4095, 1082130688, 4095, 1098907648, 255, 1119944704, 1};

    /* renamed from: f  reason: collision with root package name */
    private static final int[] f4621f = {1077936128, 1, 1086324736, 1, 1090519040, 4, 1090519296, 4, 1082130432, 4095, 1082130688, 4095, 1098907648, 255};

    /* renamed from: g  reason: collision with root package name */
    private static final int[] f4622g = {1077936128, 1, 1086324736, 1, 1082130432, 4095, 1082130688, 4095, 1098907648, 255, 1119944704, 1};

    /* renamed from: i  reason: collision with root package name */
    private static int[] f4623i = {1115701248, 1, 1115734016, 2000};

    /* renamed from: j  reason: collision with root package name */
    private static int[] f4624j = {1077936128, 1};
    private static int[] k = {1119944704, 1};
    private static Object l = null;
    private static Method m = null;
    private static Method n = null;
    private static CopyOnWriteArrayList<Integer> p = new CopyOnWriteArrayList<>();

    /* renamed from: h  reason: collision with root package name */
    private int[] f4626h = f4622g;
    private a o = null;

    /* renamed from: a  reason: collision with root package name */
    Method[] f4625a = null;

    @Override // com.bytedance.common.jato.boost.hardware.c
    public void a(a aVar, Context context) {
        this.o = aVar;
        try {
            Class<?> a2 = com.bytedance.common.jato.util.c.a("android.util.BoostFramework");
            this.f4625a = a2.getDeclaredMethods();
            l = com.bytedance.common.jato.util.c.a(a2, Context.class).newInstance(context);
            m = com.bytedance.common.jato.util.c.a(a2, "perfLockAcquire", Integer.TYPE, int[].class);
            n = com.bytedance.common.jato.util.c.a(a2, "perfLockReleaseHandler", Integer.TYPE);
        } catch (Throwable th) {
            this.o.b("cpuboost init fail: " + th.getMessage());
        }
        int b2 = b();
        if (Build.VERSION.SDK_INT >= 28) {
            if (b2 == 3) {
                this.f4626h = f4617b;
            } else {
                this.f4626h = f4618c;
            }
        } else if (Build.VERSION.SDK_INT >= 26) {
            this.f4626h = f4619d;
        } else if (Build.VERSION.SDK_INT >= 24) {
            this.f4626h = f4620e;
        } else if (Build.VERSION.SDK_INT >= 23) {
            this.f4626h = f4621f;
        }
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public boolean a(long j2) {
        a(j2, f4624j);
        return a(j2, this.f4626h);
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public boolean b(long j2) {
        return a(j2, k);
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public boolean c(long j2) {
        return a(j2, f4623i);
    }

    @Override // com.bytedance.common.jato.boost.hardware.c
    public void a() {
        if (n == null || l == null) {
            return;
        }
        for (int i2 = 0; i2 < p.size(); i2++) {
            try {
                n.invoke(l, p.get(i2));
            } catch (Throwable th) {
                this.o.b("cpuboost release fail" + th.getMessage());
                return;
            }
        }
        p.clear();
    }

    private boolean a(long j2, int[] iArr) {
        int intValue;
        if (j2 <= 0) {
            return false;
        }
        try {
            if (l != null && m != null && (intValue = ((Integer) m.invoke(l, Integer.valueOf((int) j2), iArr)).intValue()) != 0) {
                p.add(Integer.valueOf(intValue));
            }
            return true;
        } catch (Throwable th) {
            this.o.b("cpuboost boost fail:" + th.getMessage());
            return false;
        }
    }

    private static int b() {
        File[] listFiles = new File("/sys/devices/system/cpu/cpufreq").listFiles();
        if (listFiles != null) {
            return listFiles.length;
        }
        return 1;
    }
}
