package com.bytedance.apm.maps;

import java.util.Map;
/* compiled from: ApmMapsInfoApi.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static InterfaceC0017a f1220a;

    /* compiled from: ApmMapsInfoApi.java */
    /* renamed from: com.bytedance.apm.maps.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0017a {
        void addInfo(Map<Object, Object> map);
    }

    public static void addMapInfo(Map<Object, Object> map) {
        InterfaceC0017a interfaceC0017a = f1220a;
        if (interfaceC0017a != null) {
            try {
                interfaceC0017a.addInfo(map);
            } catch (Throwable unused) {
            }
        }
    }

    public static void SetCallback(InterfaceC0017a interfaceC0017a) {
        if (interfaceC0017a != null) {
            f1220a = interfaceC0017a;
        }
    }
}
