package com.bytedance.ttnet.utils;

import com.bytedance.common.utility.o;
import com.bytedance.frameworks.baselib.network.http.util.n;
import com.bytedance.retrofit2.RetrofitMetrics;
import com.bytedance.retrofit2.client.Header;
import java.util.List;
import org.json.JSONObject;
/* compiled from: RequestTicketUtil.java */
/* loaded from: classes8.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15519a = "X-SS-REQ-TICKET";

    /* renamed from: b  reason: collision with root package name */
    private static b f15520b = null;

    /* renamed from: c  reason: collision with root package name */
    private static a f15521c = null;

    /* renamed from: d  reason: collision with root package name */
    private static volatile boolean f15522d = true;

    /* compiled from: RequestTicketUtil.java */
    /* loaded from: classes8.dex */
    public interface a {
        void a(String str, com.bytedance.ttnet.http.b bVar);
    }

    /* compiled from: RequestTicketUtil.java */
    /* loaded from: classes8.dex */
    public interface b {
        void a(String str, String str2, int i2, boolean z, JSONObject jSONObject);

        void a(String str, String str2, String str3, com.bytedance.ttnet.http.b bVar);
    }

    public static void a(String str, List<Header> list, RetrofitMetrics retrofitMetrics) {
        if (f15522d) {
            retrofitMetrics.genReqTicketStart();
            try {
                String valueOf = n.a(str).getHost().endsWith(com.bytedance.ttnet.b.a()) ? String.valueOf(System.currentTimeMillis()) : null;
                if (!o.isEmpty(valueOf)) {
                    list.add(new Header(f15519a, valueOf));
                }
            } catch (Throwable unused) {
            }
            retrofitMetrics.genReqTicketEnd();
        }
    }

    public static void a(String str, String str2, String str3, com.bytedance.ttnet.http.b bVar) {
        b bVar2 = f15520b;
        if (bVar2 != null) {
            bVar2.a(str, str2, str3, bVar);
        }
    }

    public static void a(String str, String str2, int i2, boolean z, JSONObject jSONObject) {
        b bVar = f15520b;
        if (bVar != null) {
            bVar.a(str, str2, i2, z, jSONObject);
        }
    }

    public static b a() {
        return f15520b;
    }

    public static void a(b bVar) {
        f15520b = bVar;
    }

    public static a b() {
        return f15521c;
    }

    public static void a(a aVar) {
        f15521c = aVar;
    }

    public static void a(boolean z) {
        f15522d = z;
    }
}
