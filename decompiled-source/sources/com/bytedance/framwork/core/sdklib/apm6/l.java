package com.bytedance.framwork.core.sdklib.apm6;

import android.content.Context;
import com.bytedance.services.apm.api.IHttpService;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* compiled from: SDKContext.java */
/* loaded from: classes2.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private static volatile Context f8707a;

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, String> f8709c;

    /* renamed from: d  reason: collision with root package name */
    private static FileLock f8710d;

    /* renamed from: f  reason: collision with root package name */
    private static boolean f8712f;

    /* renamed from: b  reason: collision with root package name */
    private static final ConcurrentHashMap<Long, JSONObject> f8708b = new ConcurrentHashMap<>();

    /* renamed from: e  reason: collision with root package name */
    private static volatile boolean f8711e = false;

    public static synchronized void a(Context context) {
        synchronized (l.class) {
            if (f8707a != null) {
                return;
            }
            f8707a = context;
        }
    }

    public static Context a() {
        return f8707a;
    }

    public static synchronized JSONObject a(long j2) {
        JSONObject jSONObject;
        synchronized (l.class) {
            jSONObject = f8708b.get(Long.valueOf(j2));
        }
        return jSONObject;
    }

    public static synchronized JSONObject b() {
        synchronized (l.class) {
            ConcurrentHashMap<Long, JSONObject> concurrentHashMap = f8708b;
            Iterator<Long> it = concurrentHashMap.keySet().iterator();
            if (it.hasNext()) {
                return concurrentHashMap.get(it.next());
            }
            return null;
        }
    }

    public static synchronized void a(String str, JSONObject jSONObject) {
        synchronized (l.class) {
            try {
                f8708b.put(Long.valueOf(Long.parseLong(str)), jSONObject);
            } catch (Exception e2) {
                com.bytedance.framwork.core.sdklib.apm6.safety.d.b(a.f8583a, "setHeader", e2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.util.Map<java.lang.String, java.lang.String> c() {
        /*
            java.util.Map<java.lang.String, java.lang.String> r0 = com.bytedance.framwork.core.sdklib.apm6.l.f8709c
            java.lang.String r1 = "device_id"
            java.lang.Object r0 = r0.get(r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L13
            java.util.Map<java.lang.String, java.lang.String> r0 = com.bytedance.framwork.core.sdklib.apm6.l.f8709c
            return r0
        L13:
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, org.json.JSONObject> r0 = com.bytedance.framwork.core.sdklib.apm6.l.f8708b
            java.util.Set r0 = r0.keySet()
            java.util.Iterator r0 = r0.iterator()
        L1d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L44
            java.lang.Object r2 = r0.next()
            java.lang.Long r2 = (java.lang.Long) r2
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, org.json.JSONObject> r3 = com.bytedance.framwork.core.sdklib.apm6.l.f8708b
            java.lang.Object r2 = r3.get(r2)
            org.json.JSONObject r2 = (org.json.JSONObject) r2
            if (r2 != 0) goto L34
            goto L1d
        L34:
            java.lang.String r3 = r2.optString(r1)     // Catch: java.lang.Throwable -> L1d
            java.lang.Long.parseLong(r3)     // Catch: java.lang.Throwable -> L1d
            java.util.Map<java.lang.String, java.lang.String> r3 = com.bytedance.framwork.core.sdklib.apm6.l.f8709c     // Catch: java.lang.Throwable -> L1d
            java.lang.String r2 = r2.optString(r1)     // Catch: java.lang.Throwable -> L1d
            r3.put(r1, r2)     // Catch: java.lang.Throwable -> L1d
        L44:
            java.util.Map<java.lang.String, java.lang.String> r0 = com.bytedance.framwork.core.sdklib.apm6.l.f8709c
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.framwork.core.sdklib.apm6.l.c():java.util.Map");
    }

    public static void a(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        if (f8709c == null) {
            f8709c = new HashMap();
        }
        f8709c.putAll(map);
        f8709c.remove("aid");
    }

    public static synchronized boolean d() {
        synchronized (l.class) {
            try {
                if (f8711e) {
                    return true;
                }
                File g2 = b.g();
                if (!g2.exists()) {
                    g2.createNewFile();
                }
                FileChannel channel = new RandomAccessFile(g2, "rw").getChannel();
                if (channel != null) {
                    FileLock tryLock = channel.tryLock();
                    f8710d = tryLock;
                    if (tryLock != null) {
                        f8711e = tryLock.isValid();
                    }
                }
                return f8711e;
            } catch (Throwable unused) {
                return false;
            }
        }
    }

    public static void a(boolean z) {
        f8712f = z;
    }

    public static boolean e() {
        return f8712f;
    }

    public static com.bytedance.services.apm.api.d a(String str, Map<String, String> map, byte[] bArr) {
        IHttpService iHttpService = (IHttpService) com.bytedance.news.common.service.manager.e.getService(IHttpService.class);
        if (iHttpService != null) {
            return iHttpService.doPost(str, bArr, map);
        }
        return com.bytedance.framwork.core.sdklib.apm6.http.a.a(str, bArr, map);
    }
}
