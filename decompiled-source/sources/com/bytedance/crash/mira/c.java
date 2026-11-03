package com.bytedance.crash.mira;

import org.json.JSONArray;
/* compiled from: NpthMiraApi.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    static a f6864a = null;

    /* renamed from: b  reason: collision with root package name */
    private static InterfaceC0098c f6865b = null;

    /* renamed from: c  reason: collision with root package name */
    private static boolean f6866c = false;

    /* compiled from: NpthMiraApi.java */
    /* loaded from: classes2.dex */
    interface a {
        void setMiraCallbackImpl(b bVar);
    }

    /* compiled from: NpthMiraApi.java */
    /* loaded from: classes2.dex */
    public static class b {
        public void onPluginInstallResult(String str, boolean z) {
        }

        public void onPluginLoaded(String str) {
        }
    }

    /* compiled from: NpthMiraApi.java */
    /* renamed from: com.bytedance.crash.mira.c$c  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface InterfaceC0098c {
        JSONArray getPluginInfo();
    }

    public static void setPluginInfoCallback(InterfaceC0098c interfaceC0098c) {
        f6865b = interfaceC0098c;
    }

    public static JSONArray getPluginInfo() {
        InterfaceC0098c interfaceC0098c = f6865b;
        if (interfaceC0098c != null) {
            try {
                return interfaceC0098c.getPluginInfo();
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public static boolean miraInited() {
        return f6866c;
    }

    public static void setMiraInited() {
        f6866c = true;
    }

    public static void registerMiraCallback(b bVar) {
        a aVar = f6864a;
        if (aVar != null) {
            try {
                aVar.setMiraCallbackImpl(bVar);
            } catch (Throwable unused) {
            }
        }
    }

    static void a(a aVar) {
        f6864a = aVar;
    }
}
