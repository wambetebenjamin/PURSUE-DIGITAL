package com.bytedance.apm6.foundation.context;

import android.os.Build;
import android.text.TextUtils;
import com.bytedance.apm.listener.d;
import com.bytedance.services.apm.api.IHttpService;
import com.facebook.internal.security.CertificateUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: ApmContext.java */
/* loaded from: classes.dex */
public class a extends com.bytedance.apm6.util.a {

    /* renamed from: a  reason: collision with root package name */
    private static b f2194a = null;

    /* renamed from: b  reason: collision with root package name */
    private static String f2195b = null;

    /* renamed from: c  reason: collision with root package name */
    private static Boolean f2196c = null;

    /* renamed from: d  reason: collision with root package name */
    private static String f2197d = null;

    /* renamed from: e  reason: collision with root package name */
    private static int f2198e = -1;

    /* renamed from: f  reason: collision with root package name */
    private static String f2199f = null;

    /* renamed from: g  reason: collision with root package name */
    private static int f2200g = -1;

    /* renamed from: h  reason: collision with root package name */
    private static String f2201h = null;

    /* renamed from: i  reason: collision with root package name */
    private static String f2202i = null;

    /* renamed from: j  reason: collision with root package name */
    private static long f2203j = -1;
    private static long k = 0;
    private static int l = -1;
    private static JSONObject m = null;
    private static Map<String, String> n = null;
    private static long o = -1;
    private static d p;

    public static void setApmContextAdapter(b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("apmAdapter cannot be null!");
        }
        f2194a = bVar;
        com.bytedance.apm6.util.a.setContext(bVar.getContext());
    }

    public static void setNtpTimeService(d dVar) {
        p = dVar;
    }

    public static d getNtpTimeService() {
        return p;
    }

    public static b getApmContextAdapter() {
        return f2194a;
    }

    public static void setInitTimeStamp(long j2) {
        k = j2;
    }

    public static void setStartId(long j2) {
        f2203j = j2;
    }

    public static long getStartId() {
        if (f2203j < 0) {
            f2203j = System.currentTimeMillis();
        }
        return f2203j;
    }

    public static long getInitTimeStamp() {
        if (k <= 0) {
            k = System.currentTimeMillis();
        }
        return k;
    }

    public static boolean isMainProcess() {
        if (f2196c == null) {
            synchronized (a.class) {
                if (f2196c == null) {
                    String currentProcessName = getCurrentProcessName();
                    boolean z = false;
                    if (currentProcessName != null && currentProcessName.contains(CertificateUtil.DELIMITER)) {
                        f2196c = false;
                    } else {
                        if (currentProcessName != null && currentProcessName.equals(getContext().getPackageName())) {
                            z = true;
                        }
                        f2196c = Boolean.valueOf(z);
                    }
                }
            }
        }
        return f2196c.booleanValue();
    }

    public static String getCurrentProcessName() {
        if (f2195b == null) {
            synchronized (a.class) {
                if (f2195b == null) {
                    f2195b = f2194a.getProcessName();
                }
            }
        }
        return f2195b;
    }

    public static int getAid() {
        return f2194a.getAid();
    }

    public static String getChannel() {
        if (f2197d == null) {
            synchronized (a.class) {
                if (f2197d == null) {
                    f2197d = f2194a.getChannel();
                }
            }
        }
        return f2197d;
    }

    public static int getUpdateVersionCode() {
        if (f2198e == -1) {
            synchronized (a.class) {
                if (f2198e == -1) {
                    f2198e = f2194a.getUpdateVersionCode();
                }
            }
        }
        return f2198e;
    }

    public static String getVersionName() {
        if (TextUtils.isEmpty(f2199f)) {
            synchronized (a.class) {
                if (TextUtils.isEmpty(f2199f)) {
                    f2199f = f2194a.getVersionName();
                }
            }
        }
        return f2199f;
    }

    public static int getVersionCode() {
        if (f2200g == -1) {
            synchronized (a.class) {
                if (f2200g == -1) {
                    f2200g = f2194a.getVersionCode();
                }
            }
        }
        return f2200g;
    }

    public static String getAppVersion() {
        if (TextUtils.isEmpty(f2201h)) {
            synchronized (a.class) {
                if (TextUtils.isEmpty(f2201h)) {
                    f2201h = f2194a.getAppVersion();
                }
            }
        }
        return f2201h;
    }

    public static String getReleaseBuild() {
        if (TextUtils.isEmpty(f2202i)) {
            synchronized (a.class) {
                if (TextUtils.isEmpty(f2202i)) {
                    f2202i = f2194a.getReleaseBuild();
                }
            }
        }
        return f2202i;
    }

    public static String getManifestVersionCode() {
        if (l == -1) {
            synchronized (a.class) {
                if (l == -1) {
                    l = f2194a.getManifestVersionCode();
                }
            }
        }
        return String.valueOf(l);
    }

    public static JSONObject getStableHeaderExtras() {
        if (m == null) {
            synchronized (a.class) {
                if (m == null) {
                    m = f2194a.getStableHeaderExtras();
                }
            }
        }
        return m;
    }

    public static String getDeviceId() {
        return f2194a.getDeviceId();
    }

    public static String getSessionId() {
        return f2194a.getSessionId();
    }

    public static long getUserID() {
        return f2194a.getUid();
    }

    public static void setConfigTime(long j2) {
        o = j2;
    }

    public static long getConfigTime() {
        return o;
    }

    public static Map<String, String> getUrlParams() {
        if (n == null) {
            HashMap hashMap = new HashMap();
            n = hashMap;
            hashMap.put("aid", String.valueOf(getAid()));
            n.put("os", com.bytedance.bdturing.utils.b.f4370d);
            n.put("device_platform", "android");
            n.put("os_api", Build.VERSION.SDK_INT + "");
            n.put("update_version_code", String.valueOf(getUpdateVersionCode()));
            n.put("version_code", getAppVersion());
            n.put("channel", getChannel());
            n.put("device_model", Build.MODEL);
            n.put("device_brand", Build.BRAND);
        }
        n.put("device_id", getDeviceId());
        if (isDebugMode()) {
            n.put("_log_level", "debug");
        }
        try {
            Map<String, String> paramsExtras = getApmContextAdapter().getParamsExtras();
            if (paramsExtras != null && paramsExtras.size() > 0) {
                for (Map.Entry<String, String> entry : paramsExtras.entrySet()) {
                    n.put(entry.getKey(), entry.getValue());
                }
            }
        } catch (Throwable unused) {
        }
        return n;
    }

    public static com.bytedance.services.apm.api.d doPost(String str, Map<String, String> map, byte[] bArr) {
        return ((IHttpService) com.bytedance.apm6.service.d.getService(IHttpService.class)).doPost(str, bArr, map);
    }

    public static com.bytedance.services.apm.api.d doGet(String str, Map<String, String> map) {
        return ((IHttpService) com.bytedance.apm6.service.d.getService(IHttpService.class)).doGet(str, map);
    }
}
