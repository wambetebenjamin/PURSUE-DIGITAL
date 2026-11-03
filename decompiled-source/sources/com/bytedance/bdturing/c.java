package com.bytedance.bdturing;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONObject;
/* compiled from: BdTuringConfig.java */
/* loaded from: classes.dex */
public class c {
    public static final int DEFAULT_EVENT_COUNT = 6000;
    public static final String E = "boe";

    /* renamed from: a  reason: collision with root package name */
    private static final String f4133a = "BdTuringConfig";

    /* renamed from: b  reason: collision with root package name */
    static final int f4134b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static final int f4135c = 2;

    /* renamed from: d  reason: collision with root package name */
    static final String f4136d = "Android";
    static final String m = "" + Build.VERSION.SDK_INT;
    @Deprecated
    public static final String n = "cn";
    public static final String r = "sg";
    public static final String s = "va";
    public static final String v = "in";
    public static final String w = "ttp";
    private boolean A;
    private boolean B;
    private String C;
    private boolean D;
    private e F;
    private String G;
    private String H;
    private JSONObject I;

    /* renamed from: J  reason: collision with root package name */
    private com.bytedance.bdturing.net.a f4137J;
    private String K;
    private boolean L;
    private InterfaceC0050c M;
    private JSONObject N;
    private JSONObject O;
    private JSONObject P;
    private JSONObject Q;
    private l bdTuringDepend;

    /* renamed from: e  reason: collision with root package name */
    private String f4138e;

    /* renamed from: f  reason: collision with root package name */
    private String f4139f;

    /* renamed from: g  reason: collision with root package name */
    private String f4140g;

    /* renamed from: h  reason: collision with root package name */
    private String f4141h;

    /* renamed from: i  reason: collision with root package name */
    private final String f4142i;

    /* renamed from: j  reason: collision with root package name */
    private String f4143j;
    private String k;
    private String l;
    private boolean mInjectHeader;
    private String mOpenUdid;
    private JSONObject mQATheme;
    private JSONObject mSMSTheme;
    private com.bytedance.bdturing.setting.i mServiceInterceptor;
    private String o;
    private String p;
    private String q;
    private String t;
    private HashMap<Integer, Pair<String, String>> testConfig;
    private String ticket;
    private com.bytedance.bdturing.twiceverify.b twiceVerifyDepend;
    private Context u;
    private String x;
    private int y;
    private String z;

    /* compiled from: BdTuringConfig.java */
    /* renamed from: com.bytedance.bdturing.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0050c {
        void a(c cVar);
    }

    @Deprecated
    public String getOsName() {
        return "Android";
    }

    @Deprecated
    public int getOsType() {
        return 0;
    }

    @Deprecated
    public String getSdkVersion() {
        return "2.3.10.i18n";
    }

    /* compiled from: BdTuringConfig.java */
    @Deprecated
    /* loaded from: classes.dex */
    public enum b {
        REGION_CN("cn"),
        REGION_SINGAPOER(c.r),
        REGION_USA_EAST(c.s),
        REGION_INDIA(c.v),
        REGION_BOE("boe"),
        REGION_OTHER("other");
        

        /* renamed from: a  reason: collision with root package name */
        private String f4158a;

        b(String str) {
            this.f4158a = str;
        }

        public String getName() {
            return this.f4158a;
        }
    }

    private c(a aVar) {
        String str;
        this.G = s;
        this.f4142i = "2.3.10.i18n";
        this.p = Build.BRAND;
        this.q = Build.MODEL;
        this.testConfig = new HashMap<>();
        this.H = null;
        this.mQATheme = null;
        this.mSMSTheme = null;
        this.I = null;
        this.ticket = null;
        this.C = null;
        this.D = true;
        this.z = null;
        this.A = false;
        this.L = false;
        this.M = null;
        this.bdTuringDepend = null;
        this.G = aVar.f4144a;
        this.f4138e = aVar.f4145b;
        this.f4139f = aVar.f4146c;
        this.f4140g = aVar.f4147d;
        this.f4141h = aVar.f4148e;
        this.f4143j = aVar.f4149f;
        this.l = aVar.f4150g;
        this.x = TextUtils.isEmpty(aVar.s) ? Locale.getDefault().toString() : aVar.s;
        this.F = aVar.p;
        this.f4137J = aVar.f4152i;
        this.twiceVerifyDepend = aVar.r;
        this.bdTuringDepend = aVar.u;
        if (TextUtils.isEmpty(aVar.s) && (str = this.x) != null) {
            String[] split = str.split("_");
            if (split.length > 2) {
                this.x = split[0] + "_" + split[1];
            }
        }
        try {
            this.p = URLEncoder.encode(Build.BRAND, com.bytedance.platform.godzilla.common.c.CHARSET_UTF_8);
            this.q = URLEncoder.encode(Build.MODEL, com.bytedance.platform.godzilla.common.c.CHARSET_UTF_8);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        this.k = aVar.f4153j;
        this.o = aVar.k;
        this.t = aVar.n;
        this.K = aVar.l;
        this.mOpenUdid = aVar.t;
        this.M = aVar.v;
        this.u = aVar.f4151h;
        this.B = aVar.o;
        this.mInjectHeader = aVar.m;
        this.mServiceInterceptor = aVar.x;
        this.N = aVar.w;
        this.O = aVar.y;
        this.P = aVar.z;
        this.Q = aVar.A;
    }

    @Deprecated
    public b getRegionType() {
        String str = this.G;
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case 3179:
                if (str.equals("cn")) {
                    c2 = 0;
                    break;
                }
                break;
            case 3365:
                if (str.equals(v)) {
                    c2 = 1;
                    break;
                }
                break;
            case 3668:
                if (str.equals(r)) {
                    c2 = 2;
                    break;
                }
                break;
            case 3755:
                if (str.equals(s)) {
                    c2 = 3;
                    break;
                }
                break;
            case 97720:
                if (str.equals("boe")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return b.REGION_CN;
            case 1:
                return b.REGION_INDIA;
            case 2:
                return b.REGION_SINGAPOER;
            case 3:
                return b.REGION_USA_EAST;
            case 4:
                return b.REGION_BOE;
            default:
                return b.REGION_OTHER;
        }
    }

    @Deprecated
    public void a(b bVar) {
        this.G = bVar.name();
    }

    public String a() {
        return this.G;
    }

    public c a(boolean z) {
        if (z) {
            this.G = "boe";
        }
        return this;
    }

    public boolean b() {
        return this.G.equals("boe");
    }

    public String getAppId() {
        return this.f4138e;
    }

    public String getAppName() {
        return this.f4140g;
    }

    public String getAppVersion() {
        return this.l;
    }

    public String getLanguage() {
        return this.f4139f;
    }

    public String getAppKey() {
        return this.f4143j;
    }

    public String getChannel() {
        return this.f4141h;
    }

    public String getDeviceBrand() {
        return this.p;
    }

    public String getDeviceModel() {
        return this.q;
    }

    @Deprecated
    public String getOsVersion() {
        return m;
    }

    public Context getApplicationContext() {
        return this.u;
    }

    public Activity getTopActivity() {
        l lVar = this.bdTuringDepend;
        if (lVar != null) {
            return lVar.a();
        }
        return null;
    }

    public String c() {
        Activity topActivity = getTopActivity();
        if (topActivity == null) {
            return null;
        }
        return com.bytedance.bdturing.utils.e.b(topActivity);
    }

    public Pair<String, String> getTestConfig(int i2) {
        return this.testConfig.get(Integer.valueOf(i2));
    }

    public String getInstallId() {
        return this.k;
    }

    public String getDeviceId() {
        return this.o;
    }

    public String getSessionId() {
        return this.t;
    }

    public String d() {
        return this.K;
    }

    public String getOpenUdid() {
        return this.mOpenUdid;
    }

    public InterfaceC0050c e() {
        return this.M;
    }

    public boolean f() {
        return this.L;
    }

    public String getLocale() {
        String str = this.x;
        try {
            if (TextUtils.isEmpty(str)) {
                str = Locale.getDefault().toString();
            }
            if (str == null) {
                return "";
            }
        } catch (Exception unused) {
            if (str == null) {
                return "";
            }
        }
        return str;
    }

    public String g() {
        return this.H;
    }

    public boolean getInjectHeader() {
        return this.mInjectHeader;
    }

    @Deprecated
    public boolean getMaskCancel() {
        return this.B;
    }

    public JSONObject getTheme(int i2) {
        if (i2 != 1) {
            if (i2 == 3) {
                return this.mQATheme;
            }
            return this.I;
        }
        return this.mSMSTheme;
    }

    public e getEventClient() {
        return this.F;
    }

    public void a(com.bytedance.bdturing.net.a aVar) {
        this.f4137J = aVar;
    }

    public com.bytedance.bdturing.net.a h() {
        return this.f4137J;
    }

    @Deprecated
    public int getChallengeCode() {
        return this.y;
    }

    @Deprecated
    public String getScene() {
        return this.z;
    }

    @Deprecated
    public boolean getShowToastSuccess() {
        return this.A;
    }

    @Deprecated
    public String getRiskInfo() {
        return this.C;
    }

    @Deprecated
    public boolean getFullScreen() {
        return this.D;
    }

    @Deprecated
    public String getTicket() {
        return this.ticket;
    }

    public com.bytedance.bdturing.twiceverify.b getTwiceVerifyDepend() {
        return this.twiceVerifyDepend;
    }

    public JSONObject i() {
        return this.N;
    }

    public JSONObject j() {
        return this.O;
    }

    public JSONObject k() {
        return this.P;
    }

    public JSONObject l() {
        return this.Q;
    }

    public c setInstallId(String str) {
        this.k = str;
        return this;
    }

    public c setTestConfig(int i2, String str, String str2) {
        this.testConfig.put(Integer.valueOf(i2), new Pair<>(str, str2));
        return this;
    }

    public c removeTestConfig(int i2) {
        this.testConfig.remove(Integer.valueOf(i2));
        return this;
    }

    public c setDeviceId(String str) {
        this.o = str;
        return this;
    }

    public c setSessionId(String str) {
        this.t = str;
        return this;
    }

    public c a(String str) {
        this.K = str;
        return this;
    }

    public c setOpenUdid(String str) {
        this.mOpenUdid = str;
        return this;
    }

    public c b(boolean z) {
        this.L = z;
        return this;
    }

    public c a(InterfaceC0050c interfaceC0050c) {
        this.M = interfaceC0050c;
        return this;
    }

    @Deprecated
    public c setMaskCancel(boolean z) {
        this.B = z;
        return this;
    }

    public c setAppId(String str) {
        this.f4138e = str;
        return this;
    }

    @Deprecated
    public c setChallengeCode(int i2) {
        this.y = i2;
        return this;
    }

    @Deprecated
    public c setScene(String str) {
        this.z = str;
        return this;
    }

    @Deprecated
    public c setShowToastSuccess(boolean z) {
        this.A = z;
        return this;
    }

    @Deprecated
    public c setTicket(String str) {
        this.ticket = str;
        return this;
    }

    @Deprecated
    public c setFullScreen(boolean z) {
        this.D = z;
        return this;
    }

    @Deprecated
    public c setRiskInfo(String str) {
        this.C = str;
        return this;
    }

    public c setTheme(JSONObject jSONObject, int i2) {
        if (i2 == 3) {
            this.mQATheme = jSONObject;
        } else if (i2 == 1) {
            this.mSMSTheme = jSONObject;
        } else {
            this.I = jSONObject;
        }
        return this;
    }

    public c setLanguage(String str) {
        this.f4139f = str;
        return this;
    }

    public c setLocale(String str) {
        this.x = str;
        return this;
    }

    public c b(String str) {
        this.H = str;
        return this;
    }

    public c c(String str) {
        this.G = str;
        return this;
    }

    public c setIBdturingDepend(l lVar) {
        this.bdTuringDepend = lVar;
        return this;
    }

    public c setServiceInterceptor(com.bytedance.bdturing.setting.i iVar) {
        this.mServiceInterceptor = iVar;
        return this;
    }

    public com.bytedance.bdturing.setting.i getServiceInterceptor() {
        return this.mServiceInterceptor;
    }

    /* compiled from: BdTuringConfig.java */
    /* loaded from: classes.dex */
    public static class a {
        private JSONObject A;

        /* renamed from: a  reason: collision with root package name */
        private String f4144a;

        /* renamed from: b  reason: collision with root package name */
        private String f4145b;

        /* renamed from: c  reason: collision with root package name */
        private String f4146c;

        /* renamed from: d  reason: collision with root package name */
        private String f4147d;

        /* renamed from: e  reason: collision with root package name */
        private String f4148e;

        /* renamed from: g  reason: collision with root package name */
        private String f4150g;

        /* renamed from: h  reason: collision with root package name */
        private Context f4151h;

        /* renamed from: i  reason: collision with root package name */
        private com.bytedance.bdturing.net.a f4152i;

        /* renamed from: j  reason: collision with root package name */
        private String f4153j;
        private String k;
        private String l;
        private String n;
        private e p;
        private String q;
        private com.bytedance.bdturing.twiceverify.b r;
        private String s;
        private String t;
        private l u;
        private InterfaceC0050c v;
        private JSONObject w;
        private com.bytedance.bdturing.setting.i x;
        private JSONObject y;
        private JSONObject z;

        /* renamed from: f  reason: collision with root package name */
        private String f4149f = "";
        private boolean o = true;
        private boolean m = true;

        public c build(Context context) {
            this.f4151h = context;
            return new c(this);
        }

        public a appId(String str) {
            this.f4145b = str;
            return this;
        }

        public a appName(String str) {
            this.f4147d = str;
            return this;
        }

        public a appVersion(String str) {
            this.f4150g = str;
            return this;
        }

        public a language(String str) {
            this.f4146c = str;
            return this;
        }

        public a b(String str) {
            this.s = str;
            return this;
        }

        public a a(String str) {
            this.q = str;
            return this;
        }

        public a channel(String str) {
            this.f4148e = str;
            return this;
        }

        public a d(String str) {
            this.f4144a = str;
            return this;
        }

        @Deprecated
        public a regionType(b bVar) {
            this.f4144a = bVar.getName();
            return this;
        }

        public a b(boolean z) {
            if (z) {
                this.f4144a = "boe";
            }
            return this;
        }

        public a appKey(String str) {
            this.f4149f = str;
            return this;
        }

        public a installId(String str) {
            this.f4153j = str;
            return this;
        }

        public a deviceId(String str) {
            this.k = str;
            return this;
        }

        public a userId(String str) {
            this.l = str;
            return this;
        }

        public a sessionId(String str) {
            this.n = str;
            return this;
        }

        @Deprecated
        public a c(String str) {
            this.t = str;
            return this;
        }

        public a e(String str) {
            this.t = str;
            return this;
        }

        public a a(InterfaceC0050c interfaceC0050c) {
            this.v = interfaceC0050c;
            return this;
        }

        public a maskCancel(boolean z) {
            this.o = z;
            return this;
        }

        public a a(boolean z) {
            this.m = z;
            return this;
        }

        public a eventClient(e eVar) {
            this.p = eVar;
            return this;
        }

        public a a(com.bytedance.bdturing.net.a aVar) {
            this.f4152i = aVar;
            return this;
        }

        public a a(com.bytedance.bdturing.twiceverify.b bVar) {
            this.r = bVar;
            return this;
        }

        public a a(l lVar) {
            this.u = lVar;
            return this;
        }

        public a a(com.bytedance.bdturing.setting.i iVar) {
            this.x = iVar;
            return this;
        }

        public a a(com.bytedance.bdturing.net.models.a aVar) {
            this.w = aVar.a();
            this.y = aVar.b();
            this.z = aVar.c();
            return this;
        }

        public a a(JSONObject jSONObject) {
            this.A = jSONObject;
            return this;
        }
    }
}
