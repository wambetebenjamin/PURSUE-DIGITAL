package com.bytedance.apm.npth;

import java.util.Map;
/* compiled from: NpthUtil.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static c f1234a = null;

    /* renamed from: b  reason: collision with root package name */
    private static b f1235b = null;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f1236c = false;

    /* compiled from: NpthUtil.java */
    /* renamed from: com.bytedance.apm.npth.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0018a {
        Map<String, String> getUserData();
    }

    /* compiled from: NpthUtil.java */
    /* loaded from: classes.dex */
    public interface b {
        void startMonitor();
    }

    /* compiled from: NpthUtil.java */
    /* loaded from: classes.dex */
    public interface c {
        void addAttachUserData(InterfaceC0018a interfaceC0018a);

        void addTags(Map<String, String> map);
    }

    public static void setMonitorListener(b bVar) {
        f1235b = bVar;
    }

    public static void startMonitor() {
        b bVar;
        if (f1236c || (bVar = f1235b) == null) {
            return;
        }
        bVar.startMonitor();
        f1236c = true;
    }

    public static void setNpthCallback(c cVar) {
        f1234a = cVar;
    }

    public static void addNpthTags(Map<String, String> map) {
        c cVar = f1234a;
        if (cVar != null) {
            cVar.addTags(map);
        }
    }

    public static void addNpthUserData(InterfaceC0018a interfaceC0018a) {
        c cVar = f1234a;
        if (cVar != null) {
            cVar.addAttachUserData(interfaceC0018a);
        }
    }
}
