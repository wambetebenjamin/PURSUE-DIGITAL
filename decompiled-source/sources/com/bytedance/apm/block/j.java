package com.bytedance.apm.block;

import org.json.JSONObject;
/* compiled from: NpthApmBlockApi.java */
/* loaded from: classes.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    private static Runnable f715a;

    /* renamed from: b  reason: collision with root package name */
    private static Runnable f716b;

    /* renamed from: c  reason: collision with root package name */
    private static Runnable f717c;

    /* renamed from: d  reason: collision with root package name */
    private static a f718d;

    /* compiled from: NpthApmBlockApi.java */
    /* loaded from: classes.dex */
    public interface a {
        JSONObject a();
    }

    public static void notifySigQuit() {
    }

    public static void notifySigQuitEnd() {
    }

    public static void a(a aVar) {
        if (f718d == null) {
            f718d = aVar;
        }
    }

    public static JSONObject a() {
        a aVar = f718d;
        if (aVar != null) {
            return aVar.a();
        }
        return null;
    }

    public static void notifyANR() {
        Runnable runnable = f715a;
        if (runnable != null) {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Runnable runnable) {
        f715a = runnable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(Runnable runnable) {
        f716b = runnable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(Runnable runnable) {
        f717c = runnable;
    }
}
