package com.bytedance.frameworks.baselib.network.sampling;

import com.bytedance.frameworks.baselib.network.sampling.a;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import java.util.EnumSet;
import java.util.List;
/* compiled from: TTUrlRequestLogSampler.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8495a = "TTUrlRequestLogSampler";

    /* renamed from: b  reason: collision with root package name */
    private static final String f8496b = "sdk_aid";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8497c = "traceparent";

    /* renamed from: d  reason: collision with root package name */
    private static final String f8498d = "01";

    /* renamed from: e  reason: collision with root package name */
    private EnumSet<a.e> f8499e;

    /* renamed from: f  reason: collision with root package name */
    private a.c f8500f = new a.c();

    public c() {
        this.f8499e = EnumSet.noneOf(a.e.class);
        this.f8499e = a.d().c();
    }

    public a.c a() {
        return this.f8500f;
    }

    private boolean b() {
        return this.f8499e.contains(a.e.SLARDAR);
    }

    private static boolean b(Request request) {
        String value;
        List<Header> headers = request.headers(f8497c);
        if (headers == null || headers.isEmpty() || (value = headers.get(0).getValue()) == null || value.isEmpty()) {
            return false;
        }
        return value.endsWith(f8498d);
    }

    private static int c(Request request) {
        List<Header> headers = request.headers(f8496b);
        if (headers == null || headers.isEmpty()) {
            return 0;
        }
        try {
            int parseInt = Integer.parseInt(headers.get(0).getValue());
            if (parseInt < 0) {
                return -1;
            }
            return parseInt;
        } catch (Exception unused) {
            return -1;
        }
    }

    public void a(Request request) {
        this.f8500f.f8455c = a.d.ResultSlaDisabled;
        if (b()) {
            this.f8500f = d(request);
        }
        this.f8500f.f8458f = a.d().b();
    }

    private a.c d(Request request) {
        a.c cVar = new a.c();
        cVar.f8456d = a.b.SlaApiLog;
        if (request == null) {
            cVar.f8455c = a.d.ResultSlaInvalidRequest;
            return cVar;
        } else if (b(request)) {
            cVar.f8453a = true;
            cVar.f8454b = true;
            cVar.f8455c = a.d.ResultSlaMoveLineFeature;
            return cVar;
        } else if (a.d().e()) {
            cVar.f8455c = a.d.ResultSlaNoSetting;
            cVar.f8457e = a.d().a();
            return cVar;
        } else {
            int c2 = c(request);
            if (c2 < 0) {
                cVar.f8455c = a.d.ResultSlaInvalidSdkAidHeader;
                return cVar;
            }
            a.h a2 = a.d().a(c2);
            if (a2 == null) {
                cVar.f8455c = a.d.ResultSlaNoAvailableSetting;
                return cVar;
            }
            a.f c3 = a2.c();
            if (c3 == null) {
                cVar.f8455c = a.d.ResultSlaNoAvailableRule;
                return cVar;
            }
            return c3.a(request);
        }
    }
}
