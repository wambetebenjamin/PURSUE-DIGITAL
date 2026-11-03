package com.bytedance.apm.agent.instrumentation.okhttp3;

import com.bytedance.ttgame.channel.account.Constant;
import java.util.ArrayList;
import java.util.List;
import okhttp3.internal.Version;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: OkHttpRecord.java */
/* loaded from: classes.dex */
public class e {
    public JSONObject recordJson = new JSONObject();
    public List<b> addressList = new ArrayList();
    public f socketInfo = new f();
    public C0009e recordResponse = new C0009e();
    public g timeRequest = new g();
    public a timeDetailedDuration = new a();
    public d otherInfo = new d();
    public c headerRequest = new c();

    /* compiled from: OkHttpRecord.java */
    /* loaded from: classes.dex */
    public static class a {
        public int body_recv;
        public boolean cdn_hit_cache;
        public int dns;
        public int edge;
        public int header_recv;
        public int inner;
        public int origin;
        public int proxy = -3;
        public int rtt;
        public int send;
        public int ssl;
        public int tcp;
        public int ttfb;
    }

    /* compiled from: OkHttpRecord.java */
    /* loaded from: classes.dex */
    public static class b {
        public String address;
    }

    /* compiled from: OkHttpRecord.java */
    /* loaded from: classes.dex */
    public static class c {
        public List<String> serverTimings = new ArrayList();
        public String x_tt_content_encoding;
        public String x_tt_trace_host;
        public String x_tt_trace_id;
        public String x_tt_trace_tag;
    }

    /* compiled from: OkHttpRecord.java */
    /* loaded from: classes.dex */
    public static class d {
        public String libcore = "okhttp";
        public String version = Version.userAgent();
        public boolean is_main_process = com.bytedance.apm.c.isMainProcess();
    }

    /* compiled from: OkHttpRecord.java */
    /* loaded from: classes.dex */
    public static class f {
        public String host;
        public String port;
        public String remote;
        public boolean socketReused;
    }

    /* compiled from: OkHttpRecord.java */
    /* loaded from: classes.dex */
    public static class g {
        public long duration;
        public long request_sent_time;
        public long response_recv_time;
        public long start_time;
    }

    /* compiled from: OkHttpRecord.java */
    /* renamed from: com.bytedance.apm.agent.instrumentation.okhttp3.e$e  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0009e {
        public int code;
        public boolean network_accessed;
        public long received_bytes;
        public long sent_bytes;
        public boolean via_Proxy;

        public C0009e() {
        }
    }

    public String toString() {
        f();
        e();
        d();
        c();
        b();
        a();
        return this.recordJson.toString();
    }

    private void a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(com.bytedance.apm.agent.instrumentation.okhttp3.d.X_TT_CONTENT_ENCODING, this.headerRequest.x_tt_content_encoding);
            jSONObject.put(com.bytedance.apm.agent.instrumentation.okhttp3.d.X_TT_TRACE_HOST, this.headerRequest.x_tt_trace_host);
            jSONObject.put(com.bytedance.apm.agent.instrumentation.okhttp3.d.X_TT_TRACE_ID, this.headerRequest.x_tt_trace_id);
            jSONObject.put(com.bytedance.apm.agent.instrumentation.okhttp3.d.X_TT_TRACE_TAG, this.headerRequest.x_tt_trace_tag);
            JSONArray jSONArray = new JSONArray();
            for (String str : this.headerRequest.serverTimings) {
                jSONArray.put(str);
            }
            jSONObject.put("server-timing", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        try {
            this.recordJson.put("header", jSONObject);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }

    private void b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("libcore", this.otherInfo.libcore);
            jSONObject.put("version", this.otherInfo.version);
            jSONObject.put("is_main_process", this.otherInfo.is_main_process);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        try {
            this.recordJson.put("other", jSONObject);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }

    private void c() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("duration", this.timeRequest.duration);
            jSONObject2.put("request_sent_time", this.timeRequest.request_sent_time);
            jSONObject2.put("response_recv_time", this.timeRequest.response_recv_time);
            jSONObject2.put(com.bytedance.apm.constant.c.KEY_START_TIME, this.timeRequest.start_time);
            jSONObject.put("request", jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("edge", this.timeDetailedDuration.edge);
            jSONObject3.put("ttfb", this.timeDetailedDuration.ttfb);
            jSONObject3.put("rtt", this.timeDetailedDuration.rtt);
            jSONObject3.put("inner", this.timeDetailedDuration.inner);
            jSONObject3.put("cdn_hit_cache", this.timeDetailedDuration.cdn_hit_cache);
            jSONObject3.put("origin", this.timeDetailedDuration.origin);
            jSONObject3.put("proxy", this.timeDetailedDuration.proxy);
            jSONObject3.put(com.bytedance.ttnet.utils.b.f15516h, this.timeDetailedDuration.dns);
            jSONObject3.put("tcp", this.timeDetailedDuration.tcp);
            jSONObject3.put("ssl", this.timeDetailedDuration.ssl);
            jSONObject3.put(com.bytedance.apm.constant.c.KEY_SEND, this.timeDetailedDuration.send);
            jSONObject3.put("header_recv", this.timeDetailedDuration.header_recv);
            jSONObject3.put("body_recv", this.timeDetailedDuration.body_recv);
            jSONObject.put("detailed_duration", jSONObject3);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        try {
            this.recordJson.put("timing", jSONObject);
        } catch (JSONException e4) {
            e4.printStackTrace();
        }
    }

    private void d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", this.recordResponse.code);
            jSONObject.put("sent_bytes", this.recordResponse.sent_bytes);
            jSONObject.put("received_bytes", this.recordResponse.received_bytes);
            jSONObject.put("via_proxy", this.recordResponse.via_Proxy);
            jSONObject.put("network_accessed", this.recordResponse.network_accessed);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        try {
            this.recordJson.put(com.bytedance.apm.perf.traffic.a.TTNET_RESPONSE, jSONObject);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }

    private void e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Constant.SOURCE_REMOTE, this.socketInfo.remote);
            jSONObject.put("remote_host", this.socketInfo.host);
            jSONObject.put("remote_port", this.socketInfo.port);
            jSONObject.put("socket_reused", this.socketInfo.socketReused);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        try {
            this.recordJson.put("socket", jSONObject);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }

    private void f() {
        JSONObject jSONObject = new JSONObject();
        if (!this.addressList.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            for (b bVar : this.addressList) {
                jSONArray.put(bVar.address);
            }
            try {
                jSONObject.put("address_list", jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        try {
            this.recordJson.put(com.bytedance.ttnet.utils.b.f15516h, jSONObject);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }
}
