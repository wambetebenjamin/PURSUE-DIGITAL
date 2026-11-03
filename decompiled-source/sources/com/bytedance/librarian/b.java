package com.bytedance.librarian;

import android.content.Context;
import android.util.Log;
import com.bytedance.librarian.c;
/* compiled from: Librarian.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    static Context f9463a;

    /* renamed from: b  reason: collision with root package name */
    static volatile String f9464b;

    /* renamed from: c  reason: collision with root package name */
    static d f9465c;

    /* renamed from: d  reason: collision with root package name */
    private static final Object f9466d = new Object();

    public static void init(Context context, String str, d dVar) {
        synchronized (f9466d) {
            if (f9464b != null) {
                Log.w(c.a.TAG, "already set init, just skip...");
            } else if (context == null || str == null) {
                throw new IllegalStateException("context or version is null in init");
            } else {
                f9463a = context;
                f9464b = str;
                f9465c = dVar;
            }
        }
    }

    public static void loadLibrary(String str) {
        a(str, false, false, null);
    }

    public static void loadLibraryForModule(String str, Context context) {
        a(str, false, true, context);
    }

    public static void loadLibraryRecursively(String str) {
        a(str, true, false, null);
    }

    public static void loadLibraryForModuleRecursively(String str, Context context) {
        a(str, true, true, context);
    }

    private static void a(String str, boolean z, boolean z2, Context context) {
        if (z2 && f9463a == null) {
            f9463a = context;
        }
        c cVar = c.f9467a;
        if (cVar != null) {
            cVar.a(str, z);
            return;
        }
        d dVar = f9465c;
        if (dVar != null) {
            dVar.d(str);
        } else {
            System.loadLibrary(str);
        }
    }

    public static UnsatisfiedLinkError[] getErrors() {
        return e.a();
    }

    public static String getInnerInfo() {
        c cVar = c.f9467a;
        if (cVar != null) {
            return f9464b + cVar.a();
        }
        return f9464b;
    }
}
