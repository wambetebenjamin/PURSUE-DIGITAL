package com.bytedance.frameworks.baselib.network.http.impl;

import java.net.URI;
/* compiled from: CookiePolicy.java */
/* loaded from: classes2.dex */
public interface b {

    /* renamed from: a  reason: collision with root package name */
    public static final b f7850a = new b() { // from class: com.bytedance.frameworks.baselib.network.http.impl.b.1
        @Override // com.bytedance.frameworks.baselib.network.http.impl.b
        public boolean a(URI uri, e eVar) {
            return true;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    public static final b f7851b = new b() { // from class: com.bytedance.frameworks.baselib.network.http.impl.b.2
        @Override // com.bytedance.frameworks.baselib.network.http.impl.b
        public boolean a(URI uri, e eVar) {
            return false;
        }
    };

    /* renamed from: c  reason: collision with root package name */
    public static final b f7852c = new b() { // from class: com.bytedance.frameworks.baselib.network.http.impl.b.3
        @Override // com.bytedance.frameworks.baselib.network.http.impl.b
        public boolean a(URI uri, e eVar) {
            return e.a(eVar.d(), uri.getHost());
        }
    };

    boolean a(URI uri, e eVar);
}
