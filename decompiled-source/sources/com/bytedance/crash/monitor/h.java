package com.bytedance.crash.monitor;

import android.content.Context;
import com.bytedance.crash.Flavor;
import com.bytedance.crash.Global;
import com.bytedance.crash.ICommonParams;
import com.bytedance.crash.NpthCore;
import com.bytedance.crash.UserDataCenter;
import java.util.ArrayList;
/* compiled from: MonitorManager.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static a f6930a;

    /* renamed from: b  reason: collision with root package name */
    private static com.bytedance.crash.event.e f6931b;

    public static a a(Context context, ICommonParams iCommonParams) {
        Global.b(context);
        synchronized (a.class) {
            a aVar = f6930a;
            if (aVar != null) {
                return aVar;
            }
            g gVar = new g(iCommonParams);
            f6930a = gVar;
            com.bytedance.crash.event.e eVar = f6931b;
            if (eVar != null) {
                eVar.a(gVar);
            }
            NpthCore.a(context, Global.a(context));
            return f6930a;
        }
    }

    public static a a(Context context, String str, String str2, long j2) {
        Global.b(context);
        synchronized (a.class) {
            a aVar = f6930a;
            if (aVar != null) {
                return aVar;
            }
            a aVar2 = new a(str, str2, j2);
            f6930a = aVar2;
            com.bytedance.crash.event.e eVar = f6931b;
            if (eVar != null) {
                eVar.a(aVar2);
            }
            Flavor.a(context, f6930a);
            return f6930a;
        }
    }

    public static synchronized f a(Context context, String str, long j2, long j3, String str2) {
        final f fVar;
        synchronized (h.class) {
            Global.b(context);
            fVar = new f(str, str2, j2, j3, 0L);
            NpthCore.a();
            com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.monitor.h.1
                @Override // java.lang.Runnable
                public void run() {
                    new e(f.this).n();
                }
            });
        }
        return fVar;
    }

    public static i a(Context context, String str, String str2, long j2, String[] strArr, String[] strArr2) {
        Global.b(context);
        i iVar = new i(str, str2, j2, strArr, strArr2);
        Flavor.b(context, iVar);
        return iVar;
    }

    public static a a() {
        return f6930a;
    }

    public static com.bytedance.crash.event.e b() {
        if (f6931b == null) {
            synchronized (h.class) {
                if (f6931b == null) {
                    f6931b = new com.bytedance.crash.event.e(f6930a);
                }
            }
        }
        return f6931b;
    }

    public static String c() {
        a aVar = f6930a;
        if (aVar != null) {
            return aVar.f();
        }
        return null;
    }

    public static f a(String str) {
        return d.a(str);
    }

    public static ArrayList<d> d() {
        return d.e();
    }

    public static UserDataCenter e() {
        return a.a();
    }
}
