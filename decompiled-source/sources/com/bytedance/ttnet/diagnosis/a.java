package com.bytedance.ttnet.diagnosis;

import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.frameworks.baselib.network.http.cronet.diagnosis.a;
import java.util.List;
/* compiled from: TTNetDiagnosisRequest.java */
/* loaded from: classes8.dex */
class a implements IDiagnosisRequest {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15263a = "a";

    /* renamed from: b  reason: collision with root package name */
    private static final String f15264b = "org.chromium.diagnosis.CronetDiagnosisRequestImpl";

    /* renamed from: c  reason: collision with root package name */
    private IDiagnosisCallback f15265c;

    /* renamed from: d  reason: collision with root package name */
    private int f15266d;

    /* renamed from: e  reason: collision with root package name */
    private List<String> f15267e;

    /* renamed from: f  reason: collision with root package name */
    private int f15268f;

    /* renamed from: g  reason: collision with root package name */
    private int f15269g;

    /* renamed from: j  reason: collision with root package name */
    private String f15272j;
    private com.bytedance.frameworks.baselib.network.http.cronet.diagnosis.a l;
    private int m;
    private a.InterfaceC0109a k = new C0244a();

    /* renamed from: h  reason: collision with root package name */
    private boolean f15270h = false;

    /* renamed from: i  reason: collision with root package name */
    private boolean f15271i = false;

    /* compiled from: TTNetDiagnosisRequest.java */
    /* renamed from: com.bytedance.ttnet.diagnosis.a$a  reason: collision with other inner class name */
    /* loaded from: classes8.dex */
    class C0244a implements a.InterfaceC0109a {
        C0244a() {
        }

        @Override // com.bytedance.frameworks.baselib.network.http.cronet.diagnosis.a.InterfaceC0109a
        public void a(String str) {
            if (a.this.f15265c != null) {
                a.this.f15265c.onDiagnosisComplete(str);
            }
        }
    }

    public a(int i2, List<String> list, int i3, int i4, int i5) {
        this.f15266d = i2;
        this.f15267e = list;
        this.f15268f = i3;
        this.m = i4;
        this.f15269g = i5;
        a();
    }

    private void a() {
        if (this.l == null) {
            Class<?> a2 = a(f15264b);
            if (a2 == null) {
                throw new ClassNotFoundException("org.chromium.diagnosis.CronetDiagnosisRequestImpl class not found");
            }
            Object newInstance = a2.getDeclaredConstructor(a.InterfaceC0109a.class, Integer.TYPE, List.class, Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(this.k, Integer.valueOf(this.f15266d), this.f15267e, Integer.valueOf(this.f15268f), Integer.valueOf(this.m), Integer.valueOf(this.f15269g));
            if (newInstance instanceof com.bytedance.frameworks.baselib.network.http.cronet.diagnosis.a) {
                this.l = (com.bytedance.frameworks.baselib.network.http.cronet.diagnosis.a) newInstance;
            }
        }
    }

    private Class<?> a(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.bytedance.ttnet.diagnosis.IDiagnosisRequest
    public void start(IDiagnosisCallback iDiagnosisCallback) {
        synchronized (this) {
            if (this.f15270h) {
                return;
            }
            this.f15265c = iDiagnosisCallback;
            this.l.start();
            this.f15270h = true;
            String str = this.f15272j;
            if (str != null && !str.isEmpty()) {
                doExtraCommand(WsConstants.KEY_CONNECTION_EXTRA_INFO, this.f15272j);
            }
        }
    }

    @Override // com.bytedance.ttnet.diagnosis.IDiagnosisRequest
    public void cancel() {
        synchronized (this) {
            if (this.f15270h && !this.f15271i) {
                this.l.cancel();
                this.f15271i = true;
            }
        }
    }

    @Override // com.bytedance.ttnet.diagnosis.IDiagnosisRequest
    public void doExtraCommand(String str, String str2) {
        synchronized (this) {
            if (this.f15270h) {
                this.l.doExtraCommand(str, str2);
            }
        }
    }

    @Override // com.bytedance.ttnet.diagnosis.IDiagnosisRequest
    public void setUserExtraInfo(String str) {
        synchronized (this) {
            this.f15272j = str;
            doExtraCommand(WsConstants.KEY_CONNECTION_EXTRA_INFO, str);
        }
    }
}
