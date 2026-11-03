package com.bytedance.apm6.foundation.safety;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.apm6.util.e;
import com.bytedance.apm6.util.f;
import com.bytedance.apm6.util.m;
import com.bytedance.common.utility.l;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: CustomExceptionHelper.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2205a = "APM-CustomException";

    /* renamed from: b  reason: collision with root package name */
    private static final String f2206b = "event_type";

    /* renamed from: c  reason: collision with root package name */
    private static final String f2207c = "filters";

    /* renamed from: d  reason: collision with root package name */
    private static final String f2208d = "timestamp";

    /* renamed from: e  reason: collision with root package name */
    private static final String f2209e = "stack";

    /* renamed from: f  reason: collision with root package name */
    private static final String f2210f = "exception_type";

    /* renamed from: g  reason: collision with root package name */
    private static final String f2211g = "message";

    /* renamed from: h  reason: collision with root package name */
    private static final String f2212h = "apm_sdk";

    /* renamed from: i  reason: collision with root package name */
    private static final String f2213i = "exception";

    /* renamed from: j  reason: collision with root package name */
    private static final String f2214j = "apm6_error";
    private static final String k = "host_aid";
    private static final int l = 2085;
    private static final int m = 3;
    private static AtomicInteger n = new AtomicInteger(0);
    private static boolean o = true;
    private static String p;

    public static void setEnableReport(boolean z) {
        o = z;
    }

    public static void setExceptionUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        p = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(final String str, final String str2, final Throwable th) {
        com.bytedance.apm.logging.a.e(str, str2);
        if (!o || n.get() >= 3) {
            return;
        }
        n.incrementAndGet();
        com.bytedance.apm6.util.timetask.b.getInstance(com.bytedance.apm6.util.timetask.c.IO).sendTask(new com.bytedance.apm6.util.timetask.a() { // from class: com.bytedance.apm6.foundation.safety.b.1
            @Override // java.lang.Runnable
            public void run() {
                b.c(str, str2, th);
            }
        });
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
            jSONObject2.put(f2212h, f2214j);
            jSONObject2.put("host_aid", String.valueOf(com.bytedance.apm6.foundation.context.a.getAid()));
            jSONObject.put("filters", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            jSONObject3.put("data", jSONArray);
            JSONObject jSONObject4 = new JSONObject();
            f.copyJson2(jSONObject4, com.bytedance.apm6.foundation.context.a.getStableHeaderExtras());
            com.bytedance.apm6.foundation.context.b apmContextAdapter = com.bytedance.apm6.foundation.context.a.getApmContextAdapter();
            if (apmContextAdapter != null) {
                f.copyJson2(jSONObject4, apmContextAdapter.getDynamicHeaderExtras());
            }
            jSONObject4.put("os", com.bytedance.bdturing.utils.b.f4370d);
            jSONObject4.put("aid", l);
            jSONObject4.put("device_id", com.bytedance.apm6.foundation.context.a.getDeviceId());
            jSONObject4.put("channel", com.bytedance.apm6.foundation.context.a.getChannel());
            jSONObject4.put("version_code", com.bytedance.apm6.foundation.context.a.getVersionCode());
            jSONObject4.put("update_version_code", com.bytedance.apm6.foundation.context.a.getUpdateVersionCode());
            jSONObject4.put("device_id", com.bytedance.apm6.foundation.context.a.getDeviceId());
            jSONObject4.put("uid", com.bytedance.apm6.foundation.context.a.getUserID());
            jSONObject4.put("process_name", com.bytedance.apm6.foundation.context.a.getCurrentProcessName());
            jSONObject3.put("header", jSONObject4);
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                Log.e(f2205a, "tag:" + str + " message:" + str2, aVar);
            }
            a(p, jSONObject3.toString().getBytes());
        } catch (Throwable unused) {
        }
    }

    private static void a(String str, byte[] bArr) {
        if (!l.isNetworkAvailable(com.bytedance.apm6.foundation.context.a.getContext())) {
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.d(f2205a, "network unreachable");
            }
        } else if (bArr == null || bArr.length == 0) {
        } else {
            try {
                com.bytedance.apm6.service.http.a b2 = b(str, bArr);
                if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                    com.bytedance.apm6.util.log.b.d(f2205a, "http request:url:" + b2.url + " headers:" + b2.headerMap);
                }
                com.bytedance.apm6.foundation.context.a.doPost(b2.url, b2.headerMap, b2.data);
            } catch (Throwable th) {
                if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static com.bytedance.apm6.service.http.a b(String str, byte[] bArr) {
        HashMap hashMap = new HashMap();
        if (bArr.length > 128) {
            try {
                byte[] a2 = a(bArr);
                hashMap.put("Content-Encoding", "gzip");
                bArr = a2;
            } catch (IOException unused) {
            }
        }
        return new com.bytedance.apm6.service.http.a(m.addParamsToURL(str, com.bytedance.apm6.foundation.context.a.getUrlParams()), hashMap, bArr);
    }

    private static byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        GZIPOutputStream gZIPOutputStream = null;
        try {
            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream2.write(bArr);
                e.closeQuietly(gZIPOutputStream2);
                return byteArrayOutputStream.toByteArray();
            } catch (Throwable th) {
                th = th;
                gZIPOutputStream = gZIPOutputStream2;
                e.closeQuietly(gZIPOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
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
