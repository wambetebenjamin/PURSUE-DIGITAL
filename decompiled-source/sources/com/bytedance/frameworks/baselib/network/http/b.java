package com.bytedance.frameworks.baselib.network.http;

import java.util.List;
import okhttp3.OkHttpClient;
import org.json.JSONObject;
/* compiled from: BaseRequestContext.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public boolean f7607a;
    @Deprecated

    /* renamed from: c  reason: collision with root package name */
    public boolean f7609c;

    /* renamed from: e  reason: collision with root package name */
    public boolean f7611e;

    /* renamed from: f  reason: collision with root package name */
    public int f7612f;
    public boolean force_handle_response;
    public int input_stream_buffer_size;

    /* renamed from: j  reason: collision with root package name */
    public a f7616j;
    public InterfaceC0107b k;
    public List<c> l;
    public long protect_timeout;
    public String remoteIp;
    public int request_flag;
    public long socket_connect_timeout;
    public long socket_read_timeout;
    public long socket_write_timeout;
    public int status;
    public long throttle_net_speed;
    public long timeout_connect;
    public long timeout_read;
    public long timeout_write;

    /* renamed from: b  reason: collision with root package name */
    public boolean f7608b = false;

    /* renamed from: d  reason: collision with root package name */
    public int f7610d = 0;
    public boolean bypass_network_status_check = true;
    public boolean followRedirectInternal = true;

    /* renamed from: g  reason: collision with root package name */
    public boolean f7613g = false;
    @Deprecated
    public boolean is_need_monitor_in_cancel = true;
    public boolean bypassCookie = false;

    /* renamed from: h  reason: collision with root package name */
    public boolean f7614h = false;

    /* renamed from: i  reason: collision with root package name */
    public boolean f7615i = false;
    public JSONObject m = new JSONObject();
    public int n = -1;

    /* compiled from: BaseRequestContext.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.http.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0107b {
        void a(OkHttpClient.Builder builder);
    }

    /* compiled from: BaseRequestContext.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public String f7617a;

        /* renamed from: b  reason: collision with root package name */
        public String f7618b;

        public String toString() {
            return "AuthCredentials{username=" + this.f7617a + ", password=" + this.f7618b + '}';
        }
    }

    /* compiled from: BaseRequestContext.java */
    /* loaded from: classes2.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public String f7619a;

        /* renamed from: b  reason: collision with root package name */
        public String f7620b;

        public c(String str, String str2) {
            this.f7619a = str;
            this.f7620b = str2;
        }

        /* renamed from: a */
        public c clone() {
            return new c(this.f7619a, this.f7620b);
        }
    }
}
