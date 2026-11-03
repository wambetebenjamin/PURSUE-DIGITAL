package com.bytedance.common.jato;
/* compiled from: JatoNativeLoader.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static int f4650a;

    public static synchronized boolean loadLibrary() {
        synchronized (d.class) {
            int i2 = f4650a;
            if (i2 != 0) {
                return i2 > 0;
            }
            System.loadLibrary("jato");
            f4650a = 1;
            return f4650a > 0;
        }
    }

    public static boolean a() {
        return f4650a > 0;
    }
}
