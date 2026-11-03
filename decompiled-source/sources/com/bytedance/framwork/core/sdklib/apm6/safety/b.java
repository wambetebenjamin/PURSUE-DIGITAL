package com.bytedance.framwork.core.sdklib.apm6.safety;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.apm6.util.m;
import com.bytedance.framwork.core.sdklib.apm6.l;
import com.bytedance.framwork.core.sdkmonitor.j;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: CustomExceptionHelper.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8713a = "/monitor/collect/c/exception";

    /* renamed from: b  reason: collision with root package name */
    private static final String f8714b = "event_type";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8715c = "filters";

    /* renamed from: d  reason: collision with root package name */
    private static final String f8716d = "timestamp";

    /* renamed from: e  reason: collision with root package name */
    private static final String f8717e = "stack";

    /* renamed from: f  reason: collision with root package name */
    private static final String f8718f = "exception_type";

    /* renamed from: g  reason: collision with root package name */
    private static final String f8719g = "message";

    /* renamed from: h  reason: collision with root package name */
    private static final String f8720h = "apm_sdk";

    /* renamed from: i  reason: collision with root package name */
    private static final String f8721i = "exception";

    /* renamed from: j  reason: collision with root package name */
    private static final String f8722j = "apm6_error";
    private static final int k = 3;
    private static AtomicInteger l = new AtomicInteger(0);
    private static boolean m = true;
    private static String n;

    public static void a(boolean z) {
        m = z;
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        n = str;
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        n = str + "/monitor/collect/c/exception";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(final String str, final String str2, final Throwable th) {
        if (!TextUtils.isEmpty(n) && m && l.get() < 3) {
            l.incrementAndGet();
            com.bytedance.framwork.core.sdklib.thread.a.getInstance().post(new Runnable() { // from class: com.bytedance.framwork.core.sdklib.apm6.safety.b.1
                @Override // java.lang.Runnable
                public void run() {
                    b.c(str, str2, th);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(String str, String str2, Throwable th) {
        a aVar = new a("tag=" + str + " message=" + str2, th);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event_type", "exception");
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("stack", a(aVar));
            jSONObject.put("exception_type", 1);
            jSONObject.put("message", str + "_" + str2);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(f8720h, f8722j);
            jSONObject.put("filters", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            jSONObject3.put("data", jSONArray);
            JSONObject a2 = com.bytedance.framwork.core.sdklib.util.c.a(l.b());
            if (a2 != null) {
                a2.put("aid", "44444");
            }
            jSONObject3.put("header", a2);
            if (l.e()) {
                Log.e(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "tag:" + str + " message:" + str2, aVar);
            }
            a(n, jSONObject3.toString().getBytes());
        } catch (Throwable unused) {
            if (l.e()) {
                Log.e(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "tag:" + str + " message:" + str2, aVar);
            }
        }
    }

    private static void a(String str, byte[] bArr) {
        if (!com.bytedance.common.utility.l.isNetworkAvailable(l.a())) {
            if (l.e()) {
                com.bytedance.apm6.util.log.b.d(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "network unreachable");
            }
        } else if (bArr == null || bArr.length == 0) {
        } else {
            try {
                HashMap hashMap = new HashMap();
                byte[] compress = j.compress(bArr, hashMap);
                HashMap hashMap2 = new HashMap(l.c());
                hashMap2.put("aid", "44444");
                String addParamsToURL = m.addParamsToURL(str, hashMap2);
                if (l.e()) {
                    com.bytedance.apm6.util.log.b.d(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "http request:url:" + addParamsToURL);
                }
                l.a(addParamsToURL, hashMap, compress);
            } catch (Throwable th) {
                if (l.e()) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static String a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        Throwable cause = th.getCause();
        if (cause != null) {
            cause.printStackTrace(printWriter);
            Throwable cause2 = cause.getCause();
            if (cause2 != null) {
                cause2.printStackTrace(printWriter);
            }
        }
        String stringWriter2 = stringWriter.toString();
        printWriter.close();
        return stringWriter2;
    }
}
