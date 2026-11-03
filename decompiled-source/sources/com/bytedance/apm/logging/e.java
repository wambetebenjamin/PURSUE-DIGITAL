package com.bytedance.apm.logging;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: Logger.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static d f1217a;

    /* renamed from: b  reason: collision with root package name */
    private static d f1218b;

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(String... strArr) {
        StringBuilder sb = new StringBuilder(400);
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }

    static {
        d dVar = new d() { // from class: com.bytedance.apm.logging.e.1
            @Override // com.bytedance.apm.logging.d
            public void v(String str, String... strArr) {
                Log.v(str, e.b(strArr));
            }

            @Override // com.bytedance.apm.logging.d
            public void i(String str, String... strArr) {
                Log.i(str, e.b(strArr));
            }

            @Override // com.bytedance.apm.logging.d
            public void d(String str, String... strArr) {
                Log.d(str, e.b(strArr));
            }

            @Override // com.bytedance.apm.logging.d
            public void w(String str, String... strArr) {
                Log.w(str, e.b(strArr));
            }

            @Override // com.bytedance.apm.logging.d
            public void e(String str, String... strArr) {
                Log.e(str, e.b(strArr));
            }

            @Override // com.bytedance.apm.logging.d
            public void printErrStackTrace(String str, Throwable th, String... strArr) {
                String b2 = e.b(strArr);
                if (b2 == null) {
                    b2 = "";
                }
                Log.e(str, b2 + "  " + Log.getStackTraceString(th));
            }
        };
        f1217a = dVar;
        f1218b = dVar;
    }

    private e() {
    }

    public static void seApmLogImp(d dVar) {
        f1218b = dVar;
    }

    public static d getImpl() {
        return f1218b;
    }

    public static void v(String str, String... strArr) {
        d dVar = f1218b;
        if (dVar != null) {
            dVar.v(str, strArr);
        }
    }

    public static void e(String str, String... strArr) {
        d dVar = f1218b;
        if (dVar != null) {
            dVar.e(str, strArr);
        }
    }

    public static void w(String str, String... strArr) {
        d dVar = f1218b;
        if (dVar != null) {
            dVar.w(str, strArr);
        }
    }

    public static void i(String str, String... strArr) {
        d dVar = f1218b;
        if (dVar != null) {
            dVar.i(str, strArr);
        }
    }

    public static void iJson(String str, Object... objArr) {
        if (f1218b != null) {
            JSONObject jSONObject = new JSONObject();
            int length = objArr.length;
            if (length % 2 != 0) {
                throw new IllegalArgumentException();
            }
            for (int i2 = 0; i2 < length; i2 += 2) {
                try {
                    jSONObject.put(String.valueOf(objArr[i2]), String.valueOf(objArr[i2 + 1]));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            f1218b.i(str, jSONObject.toString());
        }
    }

    public static void d(String str, String... strArr) {
        d dVar = f1218b;
        if (dVar != null) {
            dVar.d(str, strArr);
        }
    }

    public static void printErrStackTrace(String str, Throwable th, String... strArr) {
        d dVar = f1218b;
        if (dVar != null) {
            dVar.printErrStackTrace(str, th, strArr);
        }
    }
}
