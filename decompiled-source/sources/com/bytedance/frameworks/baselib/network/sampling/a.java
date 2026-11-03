package com.bytedance.frameworks.baselib.network.sampling;

import android.os.SystemClock;
import com.bytedance.retrofit2.client.Request;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* compiled from: SamplingSettingProvider.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8430a = "x-tt-sla-has-sampled";

    /* renamed from: b  reason: collision with root package name */
    public static final String f8431b = "x-tt-sla-api-allow-collection";

    /* renamed from: c  reason: collision with root package name */
    public static final String f8432c = "x-tt-sla-sampling-duration";

    /* renamed from: d  reason: collision with root package name */
    public static final String f8433d = "x-tt-sla-sampling-result-code";

    /* renamed from: e  reason: collision with root package name */
    public static final String f8434e = "x-tt-sla-no-setting-reason";

    /* renamed from: f  reason: collision with root package name */
    public static final String f8435f = "x-tt-sla-first-config-delay";

    /* renamed from: g  reason: collision with root package name */
    private static a f8436g;

    /* renamed from: h  reason: collision with root package name */
    private final ConcurrentHashMap<i, h> f8437h = new ConcurrentHashMap<>();

    /* renamed from: i  reason: collision with root package name */
    private volatile EnumC0118a f8438i = EnumC0118a.NO_CALLBACK;

    /* renamed from: j  reason: collision with root package name */
    private volatile long f8439j = -1;
    private volatile long k = -1;
    private volatile EnumSet<e> l = EnumSet.noneOf(e.class);

    /* compiled from: SamplingSettingProvider.java */
    /* loaded from: classes2.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public boolean f8453a = false;

        /* renamed from: b  reason: collision with root package name */
        public boolean f8454b = false;

        /* renamed from: c  reason: collision with root package name */
        public d f8455c = d.ResultNone;

        /* renamed from: d  reason: collision with root package name */
        public b f8456d = b.UnknownLogType;

        /* renamed from: e  reason: collision with root package name */
        public EnumC0118a f8457e = EnumC0118a.NONE;

        /* renamed from: f  reason: collision with root package name */
        public long f8458f = -1;
    }

    public void a(boolean z) {
        if (z) {
            this.l.add(e.SLARDAR);
        } else {
            this.l.remove(e.SLARDAR);
        }
    }

    public void a(EnumC0118a enumC0118a) {
        this.f8438i = enumC0118a;
    }

    public EnumC0118a a() {
        return this.f8438i;
    }

    public void a(long j2) {
        this.f8439j = j2;
    }

    public long b() {
        return this.k;
    }

    public EnumSet<e> c() {
        return this.l;
    }

    public static a d() {
        if (f8436g == null) {
            f8436g = new a();
        }
        return f8436g;
    }

    public boolean e() {
        return this.f8437h.isEmpty();
    }

    public h f() {
        return a(0);
    }

    public h a(int i2) {
        return this.f8437h.get(new i(i2));
    }

    public void g() {
        this.f8437h.clear();
    }

    public boolean a(JSONObject jSONObject, int i2) {
        try {
            if (this.k < 0) {
                this.k = (SystemClock.uptimeMillis() - this.f8439j) / 1000;
            }
            h a2 = com.bytedance.frameworks.baselib.network.sampling.b.a(jSONObject, i2);
            if (a2 == null) {
                return false;
            }
            this.f8438i = EnumC0118a.NONE;
            i e2 = a2.e();
            h hVar = this.f8437h.get(e2);
            if (hVar == null || !hVar.equals(a2)) {
                this.f8437h.put(e2, a2);
                return true;
            }
            return false;
        } catch (Exception e3) {
            e3.printStackTrace();
            return false;
        }
    }

    /* compiled from: SamplingSettingProvider.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.sampling.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public enum EnumC0118a {
        NONE(0),
        NO_CALLBACK(1),
        CONFIG_EMPTY(2),
        AID_FETCH_FAILED(3),
        NO_MATCHING_CONFIG(4),
        PARSE_CONFIG_HAS_EXCEPTION(5);
        

        /* renamed from: g  reason: collision with root package name */
        final int f8447g;

        EnumC0118a(int i2) {
            this.f8447g = i2;
        }

        public int a() {
            return this.f8447g;
        }
    }

    /* compiled from: SamplingSettingProvider.java */
    /* loaded from: classes2.dex */
    public enum e {
        NONE(0),
        SLARDAR(1),
        APPLOG(2);
        

        /* renamed from: d  reason: collision with root package name */
        final int f8473d;

        e(int i2) {
            this.f8473d = i2;
        }

        public int a() {
            return this.f8473d;
        }
    }

    /* compiled from: SamplingSettingProvider.java */
    /* loaded from: classes2.dex */
    public static class i {

        /* renamed from: a  reason: collision with root package name */
        private int f8486a;

        public i(int i2) {
            this.f8486a = i2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.f8486a == ((i) obj).f8486a;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.f8486a));
        }
    }

    /* compiled from: SamplingSettingProvider.java */
    /* loaded from: classes2.dex */
    public static class h {

        /* renamed from: a  reason: collision with root package name */
        private int f8481a;

        /* renamed from: b  reason: collision with root package name */
        private int f8482b;

        /* renamed from: c  reason: collision with root package name */
        private f f8483c;

        /* renamed from: d  reason: collision with root package name */
        private g f8484d;

        /* renamed from: e  reason: collision with root package name */
        private i f8485e;

        public h(int i2, f fVar, g gVar) {
            this(i2, 0, fVar, gVar);
        }

        public h(int i2, int i3, f fVar, g gVar) {
            this.f8481a = i2;
            this.f8482b = i3;
            this.f8483c = fVar;
            this.f8484d = gVar;
        }

        public int a() {
            return this.f8481a;
        }

        public int b() {
            return this.f8482b;
        }

        public f c() {
            return this.f8483c;
        }

        public g d() {
            return this.f8484d;
        }

        public i e() {
            if (this.f8485e == null) {
                this.f8485e = new i(this.f8482b);
            }
            return this.f8485e;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            h hVar = (h) obj;
            return this.f8481a == hVar.f8481a && this.f8482b == hVar.f8482b && Objects.equals(this.f8483c, hVar.f8483c) && Objects.equals(this.f8484d, hVar.f8484d);
        }
    }

    /* compiled from: SamplingSettingProvider.java */
    /* loaded from: classes2.dex */
    public static class f {

        /* renamed from: a  reason: collision with root package name */
        private boolean f8474a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f8475b;

        /* renamed from: c  reason: collision with root package name */
        private List<com.bytedance.frameworks.baselib.network.utils.a> f8476c;

        /* renamed from: d  reason: collision with root package name */
        private List<com.bytedance.frameworks.baselib.network.utils.a> f8477d;

        /* renamed from: e  reason: collision with root package name */
        private List<String> f8478e;

        /* renamed from: f  reason: collision with root package name */
        private List<String> f8479f;

        public f(boolean z, boolean z2, List<com.bytedance.frameworks.baselib.network.utils.a> list, List<String> list2, List<com.bytedance.frameworks.baselib.network.utils.a> list3, List<String> list4) {
            this.f8474a = z;
            this.f8475b = z2;
            this.f8476c = list;
            this.f8477d = list3;
            this.f8478e = list2;
            this.f8479f = list4;
        }

        public boolean a() {
            return this.f8474a;
        }

        public boolean b() {
            return this.f8475b;
        }

        public List<String> c() {
            return this.f8478e;
        }

        public List<String> d() {
            return this.f8479f;
        }

        public c a(Request request) {
            c cVar = new c();
            cVar.f8453a = true;
            if (this.f8474a) {
                cVar.f8454b = true;
                cVar.f8455c = d.ResultSlaBaseApiAll;
                return cVar;
            }
            List<com.bytedance.frameworks.baselib.network.utils.a> list = this.f8476c;
            if (list != null && !list.isEmpty()) {
                for (com.bytedance.frameworks.baselib.network.utils.a aVar : this.f8476c) {
                    if (aVar.b(request.getUrl())) {
                        cVar.f8454b = false;
                        cVar.f8455c = d.ResultSlaUrlRegexBlackList;
                        return cVar;
                    }
                }
            }
            if (this.f8475b) {
                cVar.f8454b = true;
                cVar.f8455c = d.ResultSlaApiAllUpload;
                return cVar;
            }
            List<com.bytedance.frameworks.baselib.network.utils.a> list2 = this.f8477d;
            if (list2 != null && !list2.isEmpty()) {
                for (com.bytedance.frameworks.baselib.network.utils.a aVar2 : this.f8477d) {
                    if (aVar2.a(request.getPath())) {
                        cVar.f8454b = true;
                        cVar.f8455c = d.ResultSlaApiAllowList;
                        return cVar;
                    }
                }
            }
            cVar.f8454b = false;
            cVar.f8455c = d.ResultSlaNoMatchRule;
            return cVar;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null) {
                try {
                    if (getClass() == obj.getClass()) {
                        f fVar = (f) obj;
                        if (this.f8474a == fVar.f8474a && this.f8475b == fVar.f8475b && Objects.equals(this.f8476c, fVar.f8476c)) {
                            if (Objects.equals(this.f8477d, fVar.f8477d)) {
                                return true;
                            }
                        }
                        return false;
                    }
                } catch (Exception unused) {
                }
            }
            return false;
        }
    }

    /* compiled from: SamplingSettingProvider.java */
    /* loaded from: classes2.dex */
    public static class g {

        /* renamed from: a  reason: collision with root package name */
        private Set<String> f8480a;

        public g(Set<String> set) {
            this.f8480a = set;
        }

        public Set<String> a() {
            return this.f8480a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null) {
                try {
                    if (getClass() == obj.getClass()) {
                        return Objects.equals(this.f8480a, ((g) obj).f8480a);
                    }
                } catch (Exception unused) {
                }
            }
            return false;
        }
    }

    /* compiled from: SamplingSettingProvider.java */
    /* loaded from: classes2.dex */
    public enum b {
        UnknownLogType(0),
        SlaApiLog(1),
        SlaEventLog(2);
        

        /* renamed from: d  reason: collision with root package name */
        final int f8452d;

        b(int i2) {
            this.f8452d = i2;
        }

        public int a() {
            return this.f8452d;
        }
    }

    /* compiled from: SamplingSettingProvider.java */
    /* loaded from: classes2.dex */
    public enum d {
        ResultNone(0),
        ResultSlaDisabled(100),
        ResultSlaNoSetting(101),
        ResultSlaMoveLineFeature(103),
        ResultSlaInvalidSdkAidHeader(104),
        ResultSlaNoAvailableSetting(105),
        ResultSlaNoAvailableRule(106),
        ResultSlaInvalidRequest(107),
        ResultSlaBaseApiAll(120),
        ResultSlaApiAllUpload(121),
        ResultSlaUrlRegexBlackList(122),
        ResultSlaNoMatchRule(129),
        ResultSlaHitCommonLogType(130),
        ResultSlaApiAllowList(131);
        
        final int o;

        d(int i2) {
            this.o = i2;
        }

        public int a() {
            return this.o;
        }
    }
}
