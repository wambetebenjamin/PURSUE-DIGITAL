package com.bytedance.frameworks.baselib.network.http;

import android.os.SystemClock;
import android.text.TextUtils;
import com.bytedance.apm.constant.p;
import com.bytedance.apm.constant.s;
import com.bytedance.frameworks.baselib.network.http.b;
import com.bytedance.frameworks.baselib.network.sampling.a;
import com.bytedance.retrofit2.LegacyLogRecorder;
import com.bytedance.retrofit2.RetrofitMetrics;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: BaseHttpRequestInfo.java */
/* loaded from: classes2.dex */
public class a<T extends b> implements LegacyLogRecorder {
    public static final String Q = "response-headers";
    private static volatile InterfaceC0106a S = new InterfaceC0106a() { // from class: com.bytedance.frameworks.baselib.network.http.a.1
        @Override // com.bytedance.frameworks.baselib.network.http.a.InterfaceC0106a
        public a a() {
            return new a();
        }
    };
    public static final String U = "ab_test";
    public static final String V = "hit";

    /* renamed from: a  reason: collision with root package name */
    public static final int f7596a = 0;

    /* renamed from: b  reason: collision with root package name */
    public static final int f7597b = 1;

    /* renamed from: c  reason: collision with root package name */
    public static final String f7598c = "hc";

    /* renamed from: d  reason: collision with root package name */
    public static final String f7599d = "ua";

    /* renamed from: e  reason: collision with root package name */
    public static final String f7600e = "hcv";

    /* renamed from: f  reason: collision with root package name */
    public static final String f7601f = "ex";

    /* renamed from: g  reason: collision with root package name */
    public static final String f7602g = "cookie";
    @Deprecated
    public long A;
    @Deprecated
    public long B;
    public String I;

    /* renamed from: J  reason: collision with root package name */
    public String f7603J;
    @Deprecated
    public long K;
    public URL Z;
    public long aa;
    public boolean aj;
    public String al;
    public l ap;
    public long aq;
    @Deprecated

    /* renamed from: h  reason: collision with root package name */
    public String f7604h;

    /* renamed from: i  reason: collision with root package name */
    public T f7605i;
    @Deprecated

    /* renamed from: j  reason: collision with root package name */
    public long f7606j;
    @Deprecated
    public long k;
    @Deprecated
    public long m;
    @Deprecated
    public long n;
    @Deprecated
    public long o;
    @Deprecated
    public long q;
    @Deprecated
    public long r;
    @Deprecated
    public long s;
    @Deprecated
    public long t;
    @Deprecated
    public long u;
    @Deprecated
    public long v;
    @Deprecated
    public boolean w;
    @Deprecated
    public long x;
    @Deprecated
    public long y;
    @Deprecated
    public long z;
    public boolean W = false;
    public int X = -1;
    public RetrofitMetrics Y = new RetrofitMetrics(this);
    @Deprecated
    public long l = System.currentTimeMillis();
    @Deprecated
    public long L = -1;
    @Deprecated
    public long M = -1;
    @Deprecated
    public long ab = -1;
    @Deprecated
    public long ac = -1;
    @Deprecated
    public int p = -1;
    public boolean ad = false;
    public int C = -1;
    public int D = -1;
    public String E = "";
    @Deprecated
    public String F = "";
    public JSONObject G = new JSONObject();
    @Deprecated
    public boolean H = false;
    public String R = "";
    public String ae = "";
    public boolean af = false;
    public a.c ag = new a.c();
    public long ah = -1;
    public long ai = -1;
    private final com.bytedance.frameworks.baselib.network.sampling.c T = new com.bytedance.frameworks.baselib.network.sampling.c();
    public boolean N = false;
    public boolean P = false;
    public int ak = 0;
    @Deprecated
    public long O = -1;
    public boolean am = false;
    @Deprecated
    public long an = -1;
    public boolean ao = false;
    @Deprecated
    public boolean ar = false;
    @Deprecated
    public final AtomicBoolean as = new AtomicBoolean(false);

    /* compiled from: BaseHttpRequestInfo.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.http.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0106a {
        a a();
    }

    public void a(RetrofitMetrics retrofitMetrics) {
        retrofitMetrics.requestInfo = new WeakReference<>(this);
        this.Y = retrofitMetrics;
        long j2 = retrofitMetrics.retrofit.eventTs.ssHttpCallApiStartTime;
        this.l = j2;
        this.f7606j = j2;
        this.k = retrofitMetrics.ConvertToWallClockTimeMs(retrofitMetrics.retrofit.eventTs.requestInterceptorStart);
        this.ab = retrofitMetrics.retrofit.misc.requestStartAppResumeState;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setRequestEnd(long j2) {
        this.o = j2;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setKernelDns(long j2) {
        this.q = j2;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setKernelTCPConnect(long j2) {
        this.r = j2;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setKernelTls(long j2) {
        this.s = j2;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setKernelSend(long j2) {
        this.t = j2;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setKernelPush(long j2) {
        this.u = j2;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setKernelTTFB(long j2) {
        this.x = j2;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setKernelReceiveBody(long j2) {
        this.v = j2;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setKernelTotal(long j2) {
        this.y = j2;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setRequestEndAppResumeState(long j2) {
        this.ac = j2;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setNetworkKernelRawRequestLog(String str) {
        this.F = str;
    }

    public com.bytedance.frameworks.baselib.network.sampling.c c() {
        return this.T;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setBdTuringCallbackDuration(long j2) {
        this.O = j2;
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void setAccountRetryCallbackDuration(long j2) {
        this.an = j2;
    }

    public static void a(InterfaceC0106a interfaceC0106a) {
        if (interfaceC0106a != null) {
            S = interfaceC0106a;
        }
    }

    public static JSONObject a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(",");
        JSONArray jSONArray = new JSONArray();
        for (String str2 : split) {
            jSONArray.put(str2);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(V, jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static a a() {
        return S.a();
    }

    @Deprecated
    public long d() {
        return this.Y.retrofit.getRequestDurationMs();
    }

    @Override // com.bytedance.retrofit2.LegacyLogRecorder
    public void generateOldFormatLog(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        a(jSONObject);
        b(jSONObject);
    }

    @Deprecated
    private void a(JSONObject jSONObject) {
        jSONObject.put("requestStart", this.l);
        jSONObject.put("responseBack", this.m);
        jSONObject.put("completeReadResponse", this.n);
        jSONObject.put("appLevelRequestStart", this.f7606j);
        jSONObject.put("beforeAllInterceptors", this.k);
        jSONObject.put("requestEnd", this.o);
        jSONObject.put("recycleCount", this.p);
        jSONObject.put("timing_dns", this.q);
        jSONObject.put("timing_connect", this.r);
        jSONObject.put("timing_ssl", this.s);
        jSONObject.put("timing_send", this.t);
        jSONObject.put("timing_waiting", this.x);
        jSONObject.put("timing_receive", this.v);
        jSONObject.put("timing_total", this.y);
        jSONObject.put("timing_isSocketReused", this.w);
        jSONObject.put(s.KEY_SENT_BYTES, this.z);
        jSONObject.put(s.KEY_RECEIVED_BYTES, this.A);
        jSONObject.put("timing_remoteIP", this.f7604h);
        jSONObject.put("request_log", this.F);
        JSONObject jSONObject2 = this.G;
        if (jSONObject2 != null) {
            jSONObject.put("req_info", jSONObject2);
        }
        jSONObject.put("download", this.H);
        jSONObject.put("addExecutorToAllInterceptors", this.k - this.f7606j);
        jSONObject.put("addExecutorTime", this.f7606j);
        jSONObject.put("allInterceptorsTime", this.k);
        jSONObject.put("fallback", this.D);
        jSONObject.put(p.EXT_REQUEST_HEADER, this.I);
        jSONObject.put(p.EXT_RESPONSE_HEADER, this.f7603J);
        if (this.af) {
            jSONObject.put("hit_rules", 1);
        }
    }

    @Deprecated
    private void b(JSONObject jSONObject) {
        jSONObject.put("use_deprecated_api", this.ar);
        T t = this.f7605i;
        if (t != null) {
            if (t.protect_timeout > 0) {
                jSONObject.put("protect_timeout", this.f7605i.protect_timeout);
                jSONObject.put("connect_timeout", this.f7605i.socket_connect_timeout);
                jSONObject.put(DownloadSettingKeys.SegmentConfig.READ_TIMEOUT, this.f7605i.socket_read_timeout);
                jSONObject.put("write_timeout", this.f7605i.socket_write_timeout);
            } else {
                jSONObject.put("connect_timeout", this.f7605i.timeout_connect);
                jSONObject.put(DownloadSettingKeys.SegmentConfig.READ_TIMEOUT, this.f7605i.timeout_read);
                jSONObject.put("write_timeout", this.f7605i.timeout_write);
            }
        }
        RetrofitMetrics retrofitMetrics = this.Y;
        if (retrofitMetrics == null) {
            return;
        }
        jSONObject.put("resp_comp", retrofitMetrics.responseCompressType);
        jSONObject.put("req_comp", this.Y.requestCompressType);
        Long l = this.Y.responseInterceptDuration.get("CallServerInterceptor");
        jSONObject.put("parse_time", l != null ? l.longValue() : 0L);
        jSONObject.put("priority_level", this.Y.priorityLevel);
        jSONObject.put("req_priority_level", this.Y.requestPriorityLevel);
        jSONObject.put(com.bytedance.hades.downloader.impl.settings.DownloadSettingKeys.THREAD_PRIORITY, Thread.currentThread().getPriority());
        if (this.Y.executeTime > 0) {
            jSONObject.put("biz_before_time", this.Y.executeTime - this.Y.appCreateRetrofitStartUpTime);
            jSONObject.put("queue_time", this.Y.responseChainTime - this.Y.executeTime);
            jSONObject.put("preprocess_time", this.Y.executeCallStartTime - this.Y.responseChainTime);
            jSONObject.put("realcall_time", this.Y.executeCallEndTime - this.Y.executeCallStartTime);
            jSONObject.put("is_async", 0);
            if (this.H) {
                if (this.Y.executeEndTime > 0) {
                    if (this.Y.interceptorResponseInfos.size() > 0) {
                        jSONObject.put("postprocess_time", this.Y.executeEndTime - this.Y.interceptorResponseInfos.get(0).start);
                    }
                    jSONObject.put("cb_time", this.Y.executeCallEndTime - this.Y.executeEndTime);
                } else {
                    jSONObject.put("postprocess_time", -1);
                    jSONObject.put("cb_time", -1);
                    jSONObject.put("executeCallEndTime", this.Y.executeCallEndTime);
                    jSONObject.put("executeEndTime", this.Y.executeEndTime);
                }
                jSONObject.put("biz_total_time", this.Y.executeCallEndTime - this.Y.appCreateRetrofitStartUpTime);
            } else {
                jSONObject.put("postprocess_time", this.Y.executeEndTime - this.Y.executeCallEndTime);
                jSONObject.put("biz_total_time", this.Y.executeEndTime - this.Y.appCreateRetrofitStartUpTime);
            }
        } else if (this.Y.enqueueTime > 0) {
            jSONObject.put("biz_before_time", this.Y.enqueueTime - this.Y.appCreateRetrofitStartUpTime);
            jSONObject.put("queue_time", this.Y.responseChainTime - this.Y.enqueueTime);
            jSONObject.put("preprocess_time", this.Y.executeCallStartTime - this.Y.responseChainTime);
            jSONObject.put("realcall_time", this.Y.executeCallEndTime - this.Y.executeCallStartTime);
            jSONObject.put("is_async", 1);
            if (this.H) {
                if (this.Y.enqueueCallbackStartTime > 0) {
                    if (this.Y.interceptorResponseInfos.size() > 0) {
                        jSONObject.put("postprocess_time", this.Y.enqueueCallbackStartTime - this.Y.interceptorResponseInfos.get(0).start);
                    }
                    jSONObject.put("cb_time", this.Y.executeCallEndTime - this.Y.enqueueCallbackStartTime);
                } else {
                    jSONObject.put("postprocess_time", -1);
                    jSONObject.put("cb_time", -1);
                    jSONObject.put("enqueueCallbackStartTime", this.Y.enqueueCallbackStartTime);
                    jSONObject.put("executeCallEndTime", this.Y.executeCallEndTime);
                }
                jSONObject.put("biz_total_time", this.Y.executeCallEndTime - this.Y.appCreateRetrofitStartUpTime);
            } else {
                long uptimeMillis = SystemClock.uptimeMillis();
                jSONObject.put("postprocess_time", this.Y.enqueueCallbackStartTime - this.Y.executeCallEndTime);
                jSONObject.put("cb_time", uptimeMillis - this.Y.enqueueCallbackStartTime);
                jSONObject.put("biz_total_time", uptimeMillis - this.Y.appCreateRetrofitStartUpTime);
            }
        }
        if (this.Y.interceptorRequestInfos.size() > 0) {
            HashMap hashMap = new HashMap();
            for (int i2 = 0; i2 < this.Y.interceptorRequestInfos.size(); i2++) {
                RetrofitMetrics.InterceptorInfo interceptorInfo = this.Y.interceptorRequestInfos.get(i2);
                long j2 = interceptorInfo.end - interceptorInfo.start;
                Long l2 = (Long) hashMap.get(interceptorInfo.name);
                if (l2 == null) {
                    hashMap.put(interceptorInfo.name, Long.valueOf(j2));
                } else {
                    hashMap.put(interceptorInfo.name, Long.valueOf(l2.longValue() + j2));
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            for (String str : hashMap.keySet()) {
                jSONObject2.put(str, hashMap.get(str));
            }
            jSONObject.put("request_interceptors_time", jSONObject2);
            HashMap hashMap2 = new HashMap();
            for (int i3 = 0; i3 < this.Y.interceptorResponseInfos.size(); i3++) {
                RetrofitMetrics.InterceptorInfo interceptorInfo2 = this.Y.interceptorResponseInfos.get(i3);
                long j3 = interceptorInfo2.end - interceptorInfo2.start;
                Long l3 = (Long) hashMap2.get(interceptorInfo2.name);
                if (l3 == null) {
                    hashMap2.put(interceptorInfo2.name, Long.valueOf(j3));
                } else {
                    hashMap2.put(interceptorInfo2.name, Long.valueOf(l3.longValue() + j3));
                }
            }
            JSONObject jSONObject3 = new JSONObject();
            for (String str2 : hashMap2.keySet()) {
                jSONObject3.put(str2, hashMap2.get(str2));
            }
            jSONObject.put("response_interceptors_time", jSONObject3);
        }
    }
}
