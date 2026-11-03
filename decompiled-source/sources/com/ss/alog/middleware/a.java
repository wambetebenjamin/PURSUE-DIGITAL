package com.ss.alog.middleware;

import android.content.Intent;
import android.os.Bundle;
/* compiled from: ALogService.java */
/* loaded from: classes9.dex */
public abstract class a implements c {

    /* renamed from: a  reason: collision with root package name */
    protected static a f17866a = null;

    /* renamed from: b  reason: collision with root package name */
    protected static volatile boolean f17867b = false;

    protected a() {
        f17866a = this;
    }

    public static void a(a aVar) {
        f17866a = aVar;
    }

    public static void a() {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.g();
    }

    @Deprecated
    public static void a(String str) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.c(str);
    }

    public static boolean b() {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return false;
        }
        return aVar.b(3);
    }

    public static boolean b(String str) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return false;
        }
        return aVar.d(str);
    }

    public static void a(String str, String str2) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.f(str, str2);
    }

    public static void b(String str, String str2) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.g(str, str2);
    }

    public static void c(String str, String str2) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.h(str, str2);
    }

    public static void d(String str, String str2) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.i(str, str2);
    }

    public static void a(String str, String str2, Throwable th) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.c(str, str2, th);
    }

    public static void a(String str, Throwable th) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.c(str, th);
    }

    public static void e(String str, String str2) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.j(str, str2);
    }

    public static void b(String str, String str2, Throwable th) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.d(str, str2, th);
    }

    public static void b(String str, Throwable th) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.d(str, th);
    }

    public static void a(int i2, String str, String str2) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.c(i2, str, str2);
    }

    public static void b(int i2, String str, String str2) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.d(i2, str, str2);
    }

    public static void a(int i2, String str, Bundle bundle) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.b(i2, str, bundle);
    }

    public static void a(int i2, String str, Intent intent) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.b(i2, str, intent);
    }

    public static void a(int i2, String str, Throwable th) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.b(i2, str, th);
    }

    public static void a(int i2, String str, Thread thread) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.b(i2, str, thread);
    }

    public static void a(int i2, String str, StackTraceElement[] stackTraceElementArr) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.b(i2, str, stackTraceElementArr);
    }

    public static void a(int i2) {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.c(i2);
    }

    public static void c() {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.h();
    }

    public static void d() {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.i();
    }

    public static void e() {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.j();
    }

    public static void f() {
        a aVar = f17866a;
        if (aVar == null) {
            if (f17867b) {
                throw new RuntimeException("implement AlogService and set sInstance first");
            }
            return;
        }
        aVar.k();
    }
}
