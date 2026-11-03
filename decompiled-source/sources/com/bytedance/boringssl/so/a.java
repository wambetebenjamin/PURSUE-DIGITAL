package com.bytedance.boringssl.so;

import android.util.Log;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* compiled from: BoringsslLoaderWrapper.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile boolean f4398a = false;

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f4399b = false;

    /* renamed from: c  reason: collision with root package name */
    private static volatile c f4400c = null;

    /* renamed from: d  reason: collision with root package name */
    private static volatile String f4401d = "ttboringssl";

    /* renamed from: e  reason: collision with root package name */
    private static volatile String f4402e = "ttcrypto";

    /* renamed from: f  reason: collision with root package name */
    private static final String f4403f = "BoringsslLoaderWrapper";

    /* renamed from: g  reason: collision with root package name */
    private static Lock f4404g = new ReentrantLock();

    public static void setDefaultBoringsslName(String str) {
        f4401d = str;
    }

    public static void setDefaultCryptoName(String str) {
        f4402e = str;
    }

    public static void setBoringsslLoader(c cVar) {
        f4400c = cVar;
    }

    public static boolean loadBoringssl() {
        try {
            try {
                f4404g.lock();
            } catch (Error e2) {
                Log.e(f4403f, "load boringssl:" + f4398a + " load crypto:" + f4399b + "  err:" + e2.toString());
            }
            if (f4400c != null) {
                return f4400c.loadBoringssl();
            }
            if (!f4399b) {
                System.loadLibrary(f4402e);
                f4399b = true;
            }
            if (!f4398a) {
                System.loadLibrary(f4401d);
                f4398a = true;
            }
            return f4398a && f4399b;
        } finally {
            f4404g.unlock();
        }
    }
}
