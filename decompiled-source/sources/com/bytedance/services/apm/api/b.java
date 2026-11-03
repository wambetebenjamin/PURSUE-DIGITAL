package com.bytedance.services.apm.api;

import java.util.Collection;
import java.util.Map;
/* compiled from: EnsureManager.java */
/* loaded from: classes3.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static IEnsure f13267a;

    private b() {
    }

    public static void setEnsureImpl(IEnsure iEnsure) {
        f13267a = iEnsure;
    }

    public static IEnsure getEnsureImpl() {
        return f13267a;
    }

    public static boolean ensureTrue(boolean z) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return z;
        }
        iEnsure.ensureTrue(z);
        return z;
    }

    public static boolean ensureFalse(boolean z) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return z;
        }
        iEnsure.ensureFalse(z);
        return z;
    }

    public static boolean ensureTrue(boolean z, String str) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return z;
        }
        iEnsure.ensureTrue(z, str);
        return z;
    }

    public static boolean ensureTrue(boolean z, String str, Map<String, String> map) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return z;
        }
        iEnsure.ensureTrue(z, str, map);
        return z;
    }

    public static boolean ensureFalse(boolean z, String str) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return z;
        }
        iEnsure.ensureFalse(z, str);
        return z;
    }

    public static boolean ensureFalse(boolean z, String str, Map<String, String> map) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return z;
        }
        iEnsure.ensureFalse(z, str, map);
        return z;
    }

    public static void ensureNotReachHere() {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return;
        }
        iEnsure.ensureNotReachHere();
    }

    public static void ensureNotReachHere(String str) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return;
        }
        iEnsure.ensureNotReachHere(str);
    }

    public static void ensureNotReachHere(Throwable th) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return;
        }
        iEnsure.ensureNotReachHere(th);
    }

    public static void reportLogEException(int i2, Throwable th, String str, boolean z) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return;
        }
        iEnsure.reportLogException(i2, th, str);
    }

    public static void ensureNotReachHere(Throwable th, String str) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return;
        }
        iEnsure.ensureNotReachHere(th, str);
    }

    public static void ensureNotReachHere(String str, Map<String, String> map) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return;
        }
        iEnsure.ensureNotReachHere(str, map);
    }

    public static void ensureNotReachHere(Throwable th, String str, Map<String, String> map) {
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return;
        }
        iEnsure.ensureNotReachHere(th, str, map);
    }

    public static boolean ensureNotEmpty(Collection collection) {
        boolean z = (collection == null || collection.isEmpty()) ? false : true;
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return z;
        }
        iEnsure.ensureNotEmpty(collection);
        return z;
    }

    public static boolean ensureNotNull(Object obj) {
        boolean z = obj != null;
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return z;
        }
        iEnsure.ensureNotNull(obj);
        return z;
    }

    public static boolean ensureNotNull(Object obj, String str) {
        boolean z = obj != null;
        IEnsure iEnsure = f13267a;
        if (iEnsure == null) {
            return z;
        }
        iEnsure.ensureNotNull(obj, str);
        return z;
    }
}
