package com.bytedance.framwork.core.sdklib.apm6.safety;

import android.util.Log;
import com.bytedance.framwork.core.sdklib.apm6.l;
/* compiled from: Logger.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static com.bytedance.apm6.util.log.a f8727a = new a();

    /* compiled from: Logger.java */
    /* loaded from: classes2.dex */
    public static class a implements com.bytedance.apm6.util.log.a {
        @Override // com.bytedance.apm6.util.log.a
        public void v(String str, String str2) {
            if (l.e()) {
                Log.v(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void d(String str, String str2) {
            if (l.e()) {
                Log.d(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void i(String str, String str2) {
            if (l.e()) {
                Log.i(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void w(String str, String str2) {
            if (l.e()) {
                Log.w(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void w(String str, String str2, Throwable th) {
            if (l.e()) {
                Log.w(str, str2, th);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void e(String str, String str2) {
            if (l.e()) {
                Log.e(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void e(String str, String str2, Throwable th) {
            if (l.e()) {
                Log.e(str, str2, th);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void wtf(String str, String str2) {
            if (l.e()) {
                Log.wtf(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void wtf(String str, String str2, Throwable th) {
            if (l.e()) {
                Log.e(str, str2, th);
            }
        }
    }

    private d() {
    }

    public static void a(com.bytedance.apm6.util.log.a aVar) {
        f8727a = aVar;
    }

    public static void a(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f8727a;
        if (aVar != null) {
            aVar.v(str, str2);
        }
    }

    public static void b(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f8727a;
        if (aVar != null) {
            aVar.d(str, str2);
        }
    }

    public static void c(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f8727a;
        if (aVar != null) {
            aVar.i(str, str2);
        }
    }

    public static void d(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f8727a;
        if (aVar != null) {
            aVar.w(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        com.bytedance.apm6.util.log.a aVar = f8727a;
        if (aVar != null) {
            aVar.w(str, str2, th);
        }
    }

    public static void e(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f8727a;
        if (aVar != null) {
            aVar.e(str, str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        com.bytedance.apm6.util.log.a aVar = f8727a;
        if (aVar != null) {
            aVar.e(str, str2, th);
        }
    }

    public static void f(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f8727a;
        if (aVar != null) {
            aVar.e(str, str2);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        com.bytedance.apm6.util.log.a aVar = f8727a;
        if (aVar != null) {
            aVar.e(str, str2, th);
        }
    }
}
