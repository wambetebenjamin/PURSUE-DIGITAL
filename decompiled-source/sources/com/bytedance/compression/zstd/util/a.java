package com.bytedance.compression.zstd.util;
/* compiled from: Native.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6037a = "bdzstd";

    /* renamed from: b  reason: collision with root package name */
    private static boolean f6038b = false;

    public static synchronized void a() {
        synchronized (a.class) {
            if (f6038b) {
                return;
            }
            System.loadLibrary(f6037a);
            f6038b = true;
        }
    }
}
