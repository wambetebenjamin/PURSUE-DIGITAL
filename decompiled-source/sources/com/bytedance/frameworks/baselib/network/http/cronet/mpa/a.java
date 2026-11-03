package com.bytedance.frameworks.baselib.network.http.cronet.mpa;

import java.util.List;
/* compiled from: IMpaService.java */
/* loaded from: classes2.dex */
public interface a {

    /* compiled from: IMpaService.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.http.cronet.mpa.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0111a {
        void a(boolean z, String str);
    }

    void command(String str, String str2);

    void init(InterfaceC0111a interfaceC0111a);

    void setAccAddress(List<String> list, InterfaceC0111a interfaceC0111a);

    void start();

    void stop();
}
