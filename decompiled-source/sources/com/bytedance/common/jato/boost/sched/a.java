package com.bytedance.common.jato.boost.sched;

import android.app.Application;
import java.util.concurrent.ExecutorService;
/* compiled from: AbsPriorityManager.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static int f4640a;

    public static void a(boolean z, boolean z2) {
    }

    public static void a(int i2) {
        if (f4640a == 0) {
            f4640a = i2;
        }
    }

    public static void a(int i2, int i3) {
        if (f4640a == 1) {
            b.a(i2, i3);
        }
    }

    public static void b(int i2) {
        if (f4640a == 1) {
            b.a(i2);
        }
    }

    public static void c(int i2) {
        if (f4640a == 1) {
            b.b(i2);
        }
    }

    public static void a() {
        if (f4640a == 1) {
            b.b();
        }
    }

    public static void a(Application application, ExecutorService executorService, int i2) {
        if (f4640a == 1) {
            b.a(application, executorService, i2);
        }
    }

    public static void b() {
        if (f4640a == 1) {
            b.a();
        }
    }
}
