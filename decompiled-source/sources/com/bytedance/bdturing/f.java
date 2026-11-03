package com.bytedance.bdturing;

import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: EventReport.java */
/* loaded from: classes.dex */
public class f {
    public static final String A = "h5_init";
    public static final String B = "custom";
    public static final String C = "h5_aquire_data";
    public static final String D = "h5_show_picture";
    @Deprecated
    public static final String DIALOG_BACKGROUND = "background";
    @Deprecated
    public static final String DIALOG_POP = "pop";
    public static final String E = "h5_result";
    public static final String F = "h5_close";
    public static final String G = "h5_feedback";
    public static final String H = "h5_action";
    public static final String I = "h5_action_refresh";

    /* renamed from: J  reason: collision with root package name */
    public static final String f4165J = "is_success";
    public static final String K = "style";
    private static final String L = "params_for_special";
    private static final String M = "sdk_version";
    private static final String N = "host_app_id";
    private static final String O = "app_name";
    private static final String P = "app_version";
    private static final String Q = "aid";
    private static final String R = "appkey";
    private static final String S = "did";
    @Deprecated
    public static final String SCREEN_ORIENTATION = "orientation";
    @Deprecated
    public static final String SCREEN_ORIENTATION_CHANGE = "orientation_change";
    @Deprecated
    public static final String SDK_INIT = "init";
    @Deprecated
    public static final String SYSTEM_TOO_LOW = "system_low";
    private static final String T = "iid";
    private static final String U = "uid";
    private static final String V = "session_id";
    @Deprecated
    public static final String VERIFY_RESULT = "result";
    private static final String W = "device_brand";
    private static final String X = "device_model";
    private static final String Y = "os_name";
    private static final String Z = "os_type";

    /* renamed from: a  reason: collision with root package name */
    private static final String f4166a = "EventReport";
    private static final String aa = "channel";
    private static final String ab = "region";
    private static final String ac = "lang";
    private static final String ad = "locale";
    private static final String ae = "resolution";
    private static final String af = "time";
    private static final String ag = "challenge_id";
    private static final String ah = "duration_from_init";
    private static final String ai = "img1";
    private static final String aj = "img2";
    private static final String ak = "sampling_rate";
    private static final String al = "product_path_mobile";
    private static final String am = "turing_verify_sdk";
    private static final Map<String, Double> an;

    /* renamed from: b  reason: collision with root package name */
    private static long f4167b = 0;

    /* renamed from: c  reason: collision with root package name */
    public static final String f4168c = "pop";

    /* renamed from: d  reason: collision with root package name */
    private static final String f4169d = "mode";

    /* renamed from: e  reason: collision with root package name */
    public static final String f4170e = "close";

    /* renamed from: f  reason: collision with root package name */
    public static final String f4171f = "duration";

    /* renamed from: g  reason: collision with root package name */
    public static final String f4172g = "system_low";

    /* renamed from: h  reason: collision with root package name */
    private static final String f4173h = "os_version";

    /* renamed from: i  reason: collision with root package name */
    public static final String f4174i = "load_webview";

    /* renamed from: j  reason: collision with root package name */
    public static final String f4175j = "msg";
    @Deprecated
    public static final String k = "close";
    public static final String l = "native_request";
    public static final String m = "result";
    public static final String n = "key";
    public static final String o = "result";
    @Deprecated
    public static final String p = "load_webview";
    @Deprecated
    public static final String q = "setting";
    @Deprecated
    public static final String r = "pre_create_success";
    @Deprecated
    public static final String s = "pre_create_load_success";
    public static final String t = "orientation";
    public static final String u = "orientation_change";
    public static final String v = "init";
    public static final String w = "setting";
    public static final String x = "background";
    @Deprecated
    public static final String y = "pre_create_success";
    @Deprecated
    public static final String z = "pre_create_load_success";

    /* compiled from: EventReport.java */
    /* loaded from: classes.dex */
    public enum a {
        CLOSE_REASON_MASK("mask_click_close"),
        CLOSE_REASON_BACK("back_close"),
        CLOSE_REASON_APP("app_close"),
        CLOSE_FB_MASK("close_fb_mask"),
        CLOSE_FB_CLOSE("close_fb_close"),
        CLOSE_FB_FEEDBACK("close_fb_feedback"),
        CLOSE_FB_SYSTEM("close_fb_system");
        

        /* renamed from: i  reason: collision with root package name */
        private final String f4184i;

        a(String str) {
            this.f4184i = str;
        }

        public String a() {
            return this.f4184i;
        }
    }

    public static String a(JSONObject jSONObject) {
        try {
            return jSONObject.getString("key");
        } catch (JSONException unused) {
            return null;
        }
    }

    public static long b(JSONObject jSONObject) {
        try {
            return jSONObject.getLong("duration");
        } catch (JSONException unused) {
            return -1L;
        }
    }

    public static Object c(JSONObject jSONObject) {
        try {
            return jSONObject.get("result");
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Integer d(JSONObject jSONObject) {
        try {
            return Integer.valueOf(jSONObject.getInt("result"));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static String e(JSONObject jSONObject) {
        try {
            return jSONObject.getString("result");
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Object f(JSONObject jSONObject) {
        try {
            return jSONObject.get("custom");
        } catch (JSONException unused) {
            return null;
        }
    }

    static {
        HashMap hashMap = new HashMap();
        an = hashMap;
        Double valueOf = Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        hashMap.put("init", valueOf);
        hashMap.put("setting", valueOf);
    }

    public static double a(String str) {
        double doubleValue;
        Map<String, Double> map = an;
        synchronized (map) {
            Double d2 = map.get(str);
            doubleValue = d2 == null ? 1.0d : d2.doubleValue();
        }
        return doubleValue;
    }

    public static double g(JSONObject jSONObject) {
        return a(a(jSONObject));
    }

    public static void a(String str, double d2) {
        Map<String, Double> map = an;
        synchronized (map) {
            map.put(str, Double.valueOf(d2));
        }
    }

    public static void a(Map<? extends String, ? extends Double> map) {
        Map<String, Double> map2 = an;
        synchronized (map2) {
            map2.putAll(map);
        }
    }

    static boolean h(JSONObject jSONObject) {
        double g2 = g(jSONObject);
        if (g2 >= 1.0d || Math.random() < g2) {
            try {
                jSONObject.put("sampling_rate", g2);
                return true;
            } catch (Exception unused) {
                return true;
            }
        }
        return false;
    }

    public static void verifyStart() {
        f4167b = System.currentTimeMillis();
    }

    static void a(JSONObject jSONObject, String str, boolean z2) {
        if (jSONObject.has(str)) {
            return;
        }
        jSONObject.put(str, z2);
    }

    static void a(JSONObject jSONObject, String str, double d2) {
        if (jSONObject.has(str)) {
            return;
        }
        jSONObject.put(str, d2);
    }

    static void a(JSONObject jSONObject, String str, int i2) {
        if (jSONObject.has(str)) {
            return;
        }
        jSONObject.put(str, i2);
    }

    static void a(JSONObject jSONObject, String str, long j2) {
        if (jSONObject.has(str)) {
            return;
        }
        jSONObject.put(str, j2);
    }

    static <T> void a(JSONObject jSONObject, String str, T t2) {
        if (jSONObject.has(str) || t2 == null) {
            return;
        }
        jSONObject.put(str, t2);
    }

    public static void onEvent(String str, JSONObject jSONObject) {
        c config;
        e eventClient;
        if (TextUtils.isEmpty(str) || !h(jSONObject) || (config = com.bytedance.bdturing.a.getInstance().getConfig()) == null || (eventClient = config.getEventClient()) == null) {
            return;
        }
        try {
            a(jSONObject, "time", System.currentTimeMillis());
            a(jSONObject, "params_for_special", "turing");
            a(jSONObject, "sdk_version", "2.3.10.i18n");
            a(jSONObject, S, config.getDeviceId());
            a(jSONObject, "device_brand", config.getDeviceBrand());
            a(jSONObject, "device_model", config.getDeviceModel());
            a(jSONObject, Z, 0);
            a(jSONObject, Y, com.bytedance.bdturing.utils.b.f4370d);
            a(jSONObject, "os_version", c.m);
            a(jSONObject, N, config.getAppId());
            a(jSONObject, "aid", config.getAppId());
            a(jSONObject, "app_name", config.getAppName());
            a(jSONObject, "app_version", config.getAppVersion());
            a(jSONObject, "appkey", config.getAppKey());
            a(jSONObject, "iid", config.getInstallId());
            a(jSONObject, "session_id", config.getSessionId());
            a(jSONObject, "uid", config.d());
            a(jSONObject, "region", config.a());
            a(jSONObject, "lang", config.getLanguage());
            a(jSONObject, ad, config.getLocale());
            a(jSONObject, f4169d, config.g());
            a(jSONObject, "channel", config.getChannel());
            a(jSONObject, "resolution", config.c());
            a(jSONObject, "sampling_rate", g(jSONObject));
            eventClient.onEvent(str, jSONObject);
            if (h.isDebug()) {
                h.d("event", jSONObject.toString());
            }
        } catch (Exception e2) {
            h.printException(e2);
        }
    }

    public static void a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", "pop");
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void statisticSdkInit(long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("duration", j2);
            jSONObject.put("key", "init");
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", "background");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        onEvent(am, jSONObject);
    }

    public static void a(long j2, int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("duration", j2);
            jSONObject.put("result", i2);
            jSONObject.put("key", "setting");
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void a(long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("duration", j2);
            jSONObject.put("key", "pre_create_success");
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void b(long j2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("duration", j2);
            jSONObject.put("key", "pre_create_load_success");
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void a(a aVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("duration", System.currentTimeMillis() - f4167b);
            jSONObject.put("result", aVar.a());
            jSONObject.put("key", "close");
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("key", "system_low");
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void a(int i2, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("duration", System.currentTimeMillis() - f4167b);
            jSONObject.put("result", i2);
            if (str == null) {
                str = "";
            }
            jSONObject.put("custom", str);
            jSONObject.put("key", "load_webview");
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void a(int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("duration", System.currentTimeMillis() - f4167b);
            jSONObject.put("result", i2);
            jSONObject.put("key", "result");
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void statisticOrientation(int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", i2);
            jSONObject.put("key", "orientation");
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void statisticOrientationChange(int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", i2);
            jSONObject.put("key", "orientation_change");
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    public static void b(int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("result", i2);
            jSONObject.put("key", l);
            onEvent(am, jSONObject);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }
}
