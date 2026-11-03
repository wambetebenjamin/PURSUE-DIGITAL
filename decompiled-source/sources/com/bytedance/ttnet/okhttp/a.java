package com.bytedance.ttnet.okhttp;

import com.bytedance.frameworks.baselib.network.http.cronet.ICronetAppProvider;
import com.bytedance.frameworks.baselib.network.http.ok3.b;
import com.bytedance.ttnet.TTNetInit;
import com.bytedance.ttnet.e;
import java.util.List;
/* compiled from: OkHttpAppInfoProvider.java */
/* loaded from: classes8.dex */
public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private static volatile a f15372a;

    /* renamed from: b  reason: collision with root package name */
    private ICronetAppProvider f15373b;

    /* renamed from: c  reason: collision with root package name */
    private e f15374c;

    public static a f() {
        if (f15372a == null) {
            synchronized (a.class) {
                if (f15372a == null) {
                    f15372a = new a();
                }
            }
        }
        return f15372a;
    }

    private a() {
    }

    public void a(ICronetAppProvider iCronetAppProvider) {
        this.f15373b = iCronetAppProvider;
    }

    public void a(e eVar) {
        this.f15374c = eVar;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.b
    public int a() {
        e eVar = this.f15374c;
        if (eVar != null) {
            return eVar.getAppId();
        }
        return 0;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.b
    public String b() {
        e eVar = this.f15374c;
        if (eVar != null) {
            return eVar.getTTNetServiceDomainMap().get(TTNetInit.DOMAIN_HTTPDNS_KEY);
        }
        return null;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.b
    public String[] c() {
        e eVar = this.f15374c;
        if (eVar instanceof com.bytedance.ttnet.a) {
            return ((com.bytedance.ttnet.a) eVar).d();
        }
        return null;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.b
    public String[] d() {
        e eVar = this.f15374c;
        if (eVar instanceof com.bytedance.ttnet.a) {
            return ((com.bytedance.ttnet.a) eVar).c();
        }
        return null;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.b
    public List<String> e() {
        ICronetAppProvider iCronetAppProvider = this.f15373b;
        if (iCronetAppProvider != null) {
            return iCronetAppProvider.getAbSdkVersion();
        }
        return null;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.b
    public void a(String str, String str2) {
        ICronetAppProvider iCronetAppProvider = this.f15373b;
        if (iCronetAppProvider != null) {
            iCronetAppProvider.sendAppMonitorEvent(str, str2);
        }
    }
}
