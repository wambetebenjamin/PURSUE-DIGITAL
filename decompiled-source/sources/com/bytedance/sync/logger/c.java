package com.bytedance.sync.logger;

import com.bytedance.sync.interfaze.g;
/* compiled from: LogUtils.java */
/* loaded from: classes3.dex */
public class c {
    public static final String TAG = "SyncSDKLog";

    /* renamed from: a  reason: collision with root package name */
    private static g f13469a = new a();

    public static void v(String str) {
        g gVar = f13469a;
        if (gVar != null) {
            gVar.v(str);
        }
    }

    public static void e(String str) {
        g gVar = f13469a;
        if (gVar != null) {
            gVar.e(str);
        }
    }

    public static void d(String str) {
        g gVar = f13469a;
        if (gVar != null) {
            gVar.d(str);
        }
    }

    public static void i(String str) {
        g gVar = f13469a;
        if (gVar != null) {
            gVar.i(str);
        }
    }

    public static void setLogger(g gVar) {
        if (gVar != null) {
            f13469a = gVar;
        }
    }
}
