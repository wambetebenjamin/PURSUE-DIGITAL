package com.bytedance.apm;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.bytedance.apm.net.DefaultHttpServiceImpl;
import com.bytedance.apm.util.ad;
import com.bytedance.apm.util.ae;
import com.bytedance.services.apm.api.IHttpService;
import com.facebook.internal.security.CertificateUtil;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ApmContext.java */
/* loaded from: classes.dex */
public class c {
    private static String A;
    private static g C;
    static long G;
    static long H;
    static long I;

    /* renamed from: J  reason: collision with root package name */
    static long f837J;
    private static long K;

    /* renamed from: a  reason: collision with root package name */
    private static Context f838a;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f839b;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f840c;

    /* renamed from: d  reason: collision with root package name */
    private static boolean f841d;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f842e;

    /* renamed from: f  reason: collision with root package name */
    private static long f843f;

    /* renamed from: g  reason: collision with root package name */
    private static long f844g;
    private static boolean o;
    private static long p;
    private static long q;
    private static long r;
    private static long s;
    private static String t;
    private static boolean u;
    private static boolean v;
    private static boolean w;
    private static List<String> z;

    /* renamed from: h  reason: collision with root package name */
    private static JSONObject f845h = new JSONObject();

    /* renamed from: i  reason: collision with root package name */
    private static com.bytedance.apm.core.c f846i = new com.bytedance.apm.core.b();

    /* renamed from: j  reason: collision with root package name */
    private static Map<String, String> f847j = Collections.emptyMap();
    private static IHttpService k = new DefaultHttpServiceImpl();
    private static long l = -1;
    private static volatile int m = -1;
    private static boolean n = false;
    private static long x = 0;
    private static boolean y = true;
    private static boolean B = true;
    private static boolean D = false;
    private static e E = null;
    private static com.bytedance.apm.core.d F = null;

    public static boolean isInitFinish() {
        return D;
    }

    public static void setIsInitFinish(boolean z2) {
        D = z2;
    }

    public static Context getContext() {
        return f838a;
    }

    public static void setContext(Context context) {
        if (context == null) {
            return;
        }
        f838a = com.bytedance.apm.util.b.getApplication(context);
    }

    public static void setCurrentProcessName(String str) {
        t = str;
    }

    public static void setInitCostTime(long j2) {
        f843f = j2;
    }

    public static void a(long j2) {
        K = j2;
    }

    public static void b(long j2) {
        G = j2;
    }

    public static void c(long j2) {
        H = j2;
    }

    public static void d(long j2) {
        I = j2;
    }

    public static void e(long j2) {
        f837J = j2;
    }

    public static void setStartCostTime(long j2) {
        f844g = j2;
    }

    public static boolean isNeedSalvage() {
        return y;
    }

    public static void setNeedSalvage(boolean z2) {
        y = z2;
    }

    public static e getProgressListener() {
        return E;
    }

    public static void setProgressListener(e eVar) {
        E = eVar;
    }

    public static String getCurrentProcessName() {
        if (TextUtils.isEmpty(t)) {
            t = com.bytedance.apm.util.b.getProcessName(Process.myPid());
        }
        return t;
    }

    public static boolean isMainProcess() {
        boolean z2 = true;
        if (u) {
            return true;
        }
        if (f838a == null) {
            return isMainProcessSimple();
        }
        String currentProcessName = getCurrentProcessName();
        if (currentProcessName != null && currentProcessName.contains(CertificateUtil.DELIMITER)) {
            u = false;
        } else {
            if (currentProcessName == null || !currentProcessName.equals(f838a.getPackageName())) {
                z2 = false;
            }
            u = z2;
        }
        return u;
    }

    public static boolean isMainProcessSimple() {
        String currentProcessName;
        return u || (currentProcessName = getCurrentProcessName()) == null || !currentProcessName.contains(CertificateUtil.DELIMITER);
    }

    public static void setStopWhenBackground(boolean z2) {
        n = z2;
    }

    public static boolean isStopWhenBackground() {
        return n;
    }

    public static String getPackageName() {
        Context context = f838a;
        return context == null ? "" : context.getPackageName();
    }

    public static String getTimeRange(long j2) {
        long j3 = j2 - q;
        return j3 < 30000 ? "0 - 30s" : j3 < 60000 ? "30s - 1min" : j3 < 120000 ? "1min - 2min" : j3 < 300000 ? "2min - 5min" : j3 < 600000 ? "5min - 10min" : j3 < com.bytedance.apm.constant.k.LAST_STOP_INTERVAL ? "10min - 30min" : j3 < 3600000 ? "30min - 1h" : "1h - ";
    }

    public static long getStartId() {
        if (l == -1) {
            l = System.currentTimeMillis();
        }
        return l;
    }

    public static int getLaunchMode() {
        return m;
    }

    public static void setLaunchMode(int i2) {
        m = i2;
    }

    public static void setDebugMode(boolean z2) {
        f839b = z2;
        com.bytedance.apm6.foundation.context.a.setDebugMode(isDebugMode());
    }

    public static void setDoctorDebugMode(boolean z2) {
        f841d = z2;
        com.bytedance.apm6.foundation.context.a.setDebugMode(isDebugMode());
    }

    public static void setInternalTest(boolean z2) {
        f840c = z2;
    }

    public static boolean isDebugMode() {
        return f839b || f841d;
    }

    public static boolean a() {
        return f841d;
    }

    public static void setAutomationTest(boolean z2) {
        f842e = z2;
    }

    public static boolean isAutomationTest() {
        return f842e;
    }

    public static boolean isInternalTest() {
        return f840c;
    }

    public static synchronized void setHeaderInfo(JSONObject jSONObject) {
        synchronized (c.class) {
            try {
                if (C == null) {
                    C = new g();
                }
                jSONObject.put("os", com.bytedance.bdturing.utils.b.f4370d);
                jSONObject.put("device_platform", "android");
                jSONObject.put("os_version", Build.VERSION.RELEASE);
                jSONObject.put("os_api", Build.VERSION.SDK_INT);
                jSONObject.put("device_model", Build.MODEL);
                jSONObject.put("device_brand", Build.BRAND);
                jSONObject.put("device_manufacturer", Build.MANUFACTURER);
                jSONObject.put("process_name", com.bytedance.apm.util.b.getProcessName(Process.myPid()));
                jSONObject.put("sid", getStartId());
                jSONObject.put("phone_startup_time", getInitTimeStamp());
                jSONObject.put("verify_info", ae.getReleaseBuild());
                jSONObject.put("rom_version", ad.getRomInfo());
                PackageInfo packageInfo = null;
                if (!jSONObject.has("version_name")) {
                    packageInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
                    jSONObject.put("version_name", packageInfo.versionName);
                }
                if (TextUtils.isEmpty(jSONObject.optString("app_version"))) {
                    jSONObject.put("app_version", jSONObject.optString("version_name"));
                }
                if (!jSONObject.has("version_code")) {
                    if (packageInfo == null) {
                        packageInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
                    }
                    jSONObject.put("version_code", packageInfo.versionCode);
                }
                if (TextUtils.isEmpty(jSONObject.optString("package"))) {
                    jSONObject.put("package", getContext().getPackageName());
                }
                jSONObject.put("monitor_version", "5.0.21.0-rc.23");
            } catch (Exception unused) {
            }
            C.setProcessName(jSONObject.optString("process_name"));
            C.setDeviceId(jSONObject.optString("device_id"));
            try {
                C.setAid(jSONObject.optInt("aid"));
                C.setChannel(jSONObject.optString("channel"));
                if (jSONObject.has("update_version_code")) {
                    if (jSONObject.get("update_version_code") instanceof String) {
                        C.setUpdateVersionCode(Integer.valueOf(jSONObject.optString("update_version_code")).intValue());
                    } else {
                        C.setUpdateVersionCode(jSONObject.optInt("update_version_code"));
                    }
                }
                if (jSONObject.has("version_name")) {
                    C.setVersionName(jSONObject.optString("version_name"));
                }
                if (jSONObject.has("manifest_version_code")) {
                    if (jSONObject.get("manifest_version_code") instanceof String) {
                        C.setManifestVersionCode(Integer.valueOf(jSONObject.optString("manifest_version_code")).intValue());
                    } else {
                        C.setManifestVersionCode(jSONObject.optInt("manifest_version_code"));
                    }
                }
                if (jSONObject.has("version_code")) {
                    if (jSONObject.get("version_code") instanceof String) {
                        C.setVersionCode(Integer.valueOf(jSONObject.optString("version_code")).intValue());
                    } else {
                        C.setVersionCode(jSONObject.optInt("version_code"));
                    }
                }
                if (jSONObject.has("app_version")) {
                    C.setAppVersion(jSONObject.optString("app_version"));
                }
                if (jSONObject.has("release_build")) {
                    C.setReleaseBuild(jSONObject.optString("release_build"));
                } else if (!ae.getReleaseBuild().isEmpty()) {
                    C.setReleaseBuild(ae.getReleaseBuild());
                }
            } catch (Exception unused2) {
            }
            C.setStaticHeaderExtras(com.bytedance.apm.util.l.deepCopy(jSONObject));
            f845h = jSONObject;
        }
    }

    public static void setHttpService(IHttpService iHttpService) {
        if (iHttpService != null) {
            k = iHttpService;
        }
    }

    public static boolean isLocalChannel() {
        JSONObject jSONObject = f845h;
        if (jSONObject == null || jSONObject.optString("channel") == null) {
            return false;
        }
        return f845h.optString("channel").contains("local");
    }

    public static synchronized g getParams() {
        g gVar;
        synchronized (c.class) {
            gVar = C;
        }
        return gVar;
    }

    public static synchronized Map<String, String> getParamsExtras() {
        Map<String, String> queryParams;
        synchronized (c.class) {
            com.bytedance.apm.core.d dVar = F;
            if (dVar != null && (queryParams = dVar.getQueryParams()) != null && queryParams.size() > 0) {
                if (C.getParamExtras() != null) {
                    HashMap hashMap = new HashMap(C.getParamExtras());
                    hashMap.putAll(queryParams);
                    return hashMap;
                }
                return queryParams;
            }
            g gVar = C;
            if (gVar != null) {
                return gVar.getParamExtras();
            }
            return null;
        }
    }

    public static synchronized void setQueryParams(com.bytedance.apm.core.d dVar) {
        synchronized (c.class) {
            F = dVar;
        }
    }

    public static synchronized com.bytedance.apm.core.d getQueryParams() {
        com.bytedance.apm.core.d dVar;
        synchronized (c.class) {
            dVar = F;
        }
        return dVar;
    }

    public static synchronized void setDynamicParams(com.bytedance.apm.core.c cVar) {
        synchronized (c.class) {
            f846i = cVar;
            Map<String, String> commonParams = cVar.getCommonParams();
            f847j = commonParams;
            if (commonParams == null) {
                f847j = new HashMap();
            }
            if (!f847j.containsKey("aid")) {
                f847j.put("aid", f845h.optString("aid"));
            }
            if (!f847j.containsKey("device_id")) {
                f847j.put("device_id", f845h.optString("device_id"));
            }
            if (!f847j.containsKey("device_platform")) {
                f847j.put("device_platform", "android");
            }
            f847j.put("os", com.bytedance.bdturing.utils.b.f4370d);
            if (!f847j.containsKey("update_version_code")) {
                f847j.put("update_version_code", f845h.optString("update_version_code"));
            }
            if (!f847j.containsKey("version_code")) {
                f847j.put("version_code", f845h.optString("version_code"));
            }
            if (!f847j.containsKey("channel")) {
                f847j.put("channel", f845h.optString("channel"));
            }
            if (!f847j.containsKey("os_api")) {
                f847j.put("os_api", Build.VERSION.SDK_INT + "");
            }
            if (isDebugMode() && !f847j.containsKey("_log_level")) {
                f847j.put("_log_level", "debug");
            }
            if (C == null) {
                C = new g();
            }
            C.setParamExtras(new HashMap(f847j));
        }
    }

    public static synchronized Map<String, String> getQueryParamsMap() {
        Map<String, String> queryParams;
        synchronized (c.class) {
            com.bytedance.apm.core.d dVar = F;
            if (dVar != null && (queryParams = dVar.getQueryParams()) != null && queryParams.size() > 0) {
                HashMap hashMap = new HashMap(f847j);
                hashMap.putAll(queryParams);
                return hashMap;
            }
            return f847j;
        }
    }

    public static void setExceptionTrafficDetect(boolean z2) {
        o = z2;
    }

    public static JSONObject getHeader() {
        return f845h;
    }

    public static boolean extendHeader(String str, String str2) {
        JSONObject jSONObject = f845h;
        if (jSONObject == null) {
            return false;
        }
        try {
            jSONObject.put(str, str2);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    public static com.bytedance.apm.core.c getDynamicParams() {
        return f846i;
    }

    public static boolean isExceptionTrafficSwitchOn() {
        return o;
    }

    public static IHttpService getHttpService() {
        return k;
    }

    public static com.bytedance.services.apm.api.d doPost(String str, byte[] bArr, Map<String, String> map) {
        return k.doPost(str, bArr, map);
    }

    public static com.bytedance.services.apm.api.d doGet(String str, Map<String, String> map) {
        return k.doGet(str, map);
    }

    public static com.bytedance.services.apm.api.d uploadFiles(String str, List<File> list, Map<String, String> map) {
        return k.uploadFiles(str, list, map);
    }

    public static com.bytedance.services.apm.api.n a(String str, String str2, boolean z2) {
        return k.buildMultipartUpload(str, str2, z2);
    }

    public static com.bytedance.services.apm.api.n a(String str, String str2, boolean z2, Map<String, String> map) {
        return k.buildMultipartUpload(str, str2, z2, map);
    }

    public static long getInitTimeStamp() {
        if (q == 0) {
            q = System.currentTimeMillis();
        }
        return q;
    }

    public static void setInitTimeStamp(long j2) {
        q = j2;
    }

    public static long getInitUpTimestamp() {
        return r;
    }

    public static void setInitUpTimestamp(long j2) {
        r = j2;
    }

    public static long getDeltaTime() {
        return System.currentTimeMillis() - SystemClock.uptimeMillis();
    }

    public static long getStartTimeStamp() {
        return s;
    }

    public static void setStartTimeStamp(long j2) {
        s = j2;
    }

    public static void setAppLaunchStartTimestamp(long j2) {
        if (j2 <= 0) {
            return;
        }
        long j3 = p;
        if (j3 == 0 || j2 < j3) {
            p = j2;
        }
    }

    public static long getAppLaunchStartTimestamp() {
        return p;
    }

    public static boolean supportMultiFrameRate() {
        return w;
    }

    public static void setSupportMultiFrameRate(boolean z2) {
        w = z2;
    }

    public static long getInitCostTime() {
        return f843f;
    }

    public static long b() {
        return K;
    }

    public static long c() {
        return G;
    }

    public static long d() {
        return H;
    }

    public static long e() {
        return I;
    }

    public static long f() {
        return f837J;
    }

    public static long getStartCostTime() {
        return f844g;
    }

    public static void setDeviceInfoOnPerfDataEnabled(boolean z2) {
        v = z2;
    }

    public static boolean isDeviceInfoOnPerfDataEnabled() {
        return v;
    }

    public static long getReportSeqNo() {
        long j2 = x;
        x = 1 + j2;
        return j2;
    }

    public static List<String> getSlardarConfigUrls() {
        return z;
    }

    public static void setSlardarConfigUrls(List<String> list) {
        z = list;
    }

    public static void setAlogFilesDir(String str) {
        A = str;
    }

    public static String getAlogFilesDir() {
        return A;
    }

    public static String getNetType() {
        String simpleName = k.getClass().getSimpleName();
        return "DefaultTTNetImpl".equals(simpleName) ? "ttnet" : simpleName;
    }
}
