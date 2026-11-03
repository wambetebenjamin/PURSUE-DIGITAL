package com.bytedance.apm6.util.log;

import android.util.Log;
/* compiled from: Logger.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static com.bytedance.apm6.util.log.a f2408a = new a();

    /* compiled from: Logger.java */
    /* loaded from: classes.dex */
    public static class a implements com.bytedance.apm6.util.log.a {
        @Override // com.bytedance.apm6.util.log.a
        public void v(String str, String str2) {
            if (com.bytedance.apm6.util.a.isDebugMode()) {
                Log.v(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void d(String str, String str2) {
            if (com.bytedance.apm6.util.a.isDebugMode()) {
                Log.d(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void i(String str, String str2) {
            if (com.bytedance.apm6.util.a.isDebugMode()) {
                Log.i(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void w(String str, String str2) {
            if (com.bytedance.apm6.util.a.isDebugMode()) {
                Log.w(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void w(String str, String str2, Throwable th) {
            if (com.bytedance.apm6.util.a.isDebugMode()) {
                Log.w(str, str2, th);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void e(String str, String str2) {
            if (com.bytedance.apm6.util.a.isDebugMode()) {
                Log.e(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void e(String str, String str2, Throwable th) {
            if (com.bytedance.apm6.util.a.isDebugMode()) {
                Log.e(str, str2, th);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void wtf(String str, String str2) {
            if (com.bytedance.apm6.util.a.isDebugMode()) {
                Log.wtf(str, str2);
            }
        }

        @Override // com.bytedance.apm6.util.log.a
        public void wtf(String str, String str2, Throwable th) {
            if (com.bytedance.apm6.util.a.isDebugMode()) {
                Log.e(str, str2, th);
            }
        }
    }

    private b() {
    }

    public static void setLoggerImpl(com.bytedance.apm6.util.log.a aVar) {
        f2408a = aVar;
    }

    public static void v(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f2408a;
        if (aVar != null) {
            aVar.v(str, str2);
        }
    }

    public static void d(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f2408a;
        if (aVar != null) {
            aVar.d(str, str2);
        }
    }

    public static void i(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f2408a;
        if (aVar != null) {
            aVar.i(str, str2);
        }
    }

    public static void w(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f2408a;
        if (aVar != null) {
            aVar.w(str, str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        com.bytedance.apm6.util.log.a aVar = f2408a;
        if (aVar != null) {
            aVar.w(str, str2, th);
        }
    }

    public static void e(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f2408a;
        if (aVar != null) {
            aVar.e(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        com.bytedance.apm6.util.log.a aVar = f2408a;
        if (aVar != null) {
            aVar.e(str, str2, th);
        }
    }

    public static void wtf(String str, String str2) {
        com.bytedance.apm6.util.log.a aVar = f2408a;
        if (aVar != null) {
            aVar.e(str, str2);
        }
    }

    public static void wtf(String str, String str2, Throwable th) {
        com.bytedance.apm6.util.log.a aVar = f2408a;
        if (aVar != null) {
            aVar.e(str, str2, th);
        }
    }
}
