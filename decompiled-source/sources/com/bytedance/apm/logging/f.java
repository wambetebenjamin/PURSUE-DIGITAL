package com.bytedance.apm.logging;

import android.util.Log;
/* compiled from: MethodLog.java */
/* loaded from: classes.dex */
public class f {
    public static a sLog = new a() { // from class: com.bytedance.apm.logging.f.1
        @Override // com.bytedance.apm.logging.f.a
        public void v(String str, String str2, Object... objArr) {
            if (objArr != null && objArr.length != 0) {
                str2 = String.format(str2, objArr);
            }
            Log.v(str, str2);
        }

        @Override // com.bytedance.apm.logging.f.a
        public void i(String str, String str2, Object... objArr) {
            if (objArr != null && objArr.length != 0) {
                str2 = String.format(str2, objArr);
            }
            Log.i(str, str2);
        }

        @Override // com.bytedance.apm.logging.f.a
        public void d(String str, String str2, Object... objArr) {
            if (objArr != null && objArr.length != 0) {
                str2 = String.format(str2, objArr);
            }
            Log.d(str, str2);
        }

        @Override // com.bytedance.apm.logging.f.a
        public void w(String str, String str2, Object... objArr) {
            if (objArr != null && objArr.length != 0) {
                str2 = String.format(str2, objArr);
            }
            Log.w(str, str2);
        }

        @Override // com.bytedance.apm.logging.f.a
        public void e(String str, String str2, Object... objArr) {
            if (objArr != null && objArr.length != 0) {
                str2 = String.format(str2, objArr);
            }
            Log.e(str, str2);
        }

        @Override // com.bytedance.apm.logging.f.a
        public void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
            if (objArr != null && objArr.length != 0) {
                str2 = String.format(str2, objArr);
            }
            if (str2 == null) {
                str2 = "";
            }
            Log.e(str, str2 + "  " + Log.getStackTraceString(th));
        }
    };

    /* compiled from: MethodLog.java */
    /* loaded from: classes.dex */
    public interface a {
        void d(String str, String str2, Object... objArr);

        void e(String str, String str2, Object... objArr);

        void i(String str, String str2, Object... objArr);

        void printErrStackTrace(String str, Throwable th, String str2, Object... objArr);

        void v(String str, String str2, Object... objArr);

        void w(String str, String str2, Object... objArr);
    }
}
