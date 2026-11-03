package com.bytedance.common.jato.logcut;

import android.os.Build;
import com.bytedance.common.jato.d;
/* loaded from: classes.dex */
public class LogcutV2 {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4792a = 1;

    /* renamed from: b  reason: collision with root package name */
    public static final int f4793b = 2;

    /* renamed from: c  reason: collision with root package name */
    public static final int f4794c = 3;

    /* renamed from: d  reason: collision with root package name */
    public static int f4795d = 0;

    /* renamed from: e  reason: collision with root package name */
    private static LogcutV2 f4796e = null;

    /* renamed from: f  reason: collision with root package name */
    public static final int f4797f = 0;

    /* renamed from: g  reason: collision with root package name */
    public static final int f4798g = 2;

    /* renamed from: h  reason: collision with root package name */
    public static final int f4799h = 3;

    /* renamed from: i  reason: collision with root package name */
    public static final int f4800i = 4;

    /* renamed from: j  reason: collision with root package name */
    public static final int f4801j = 5;
    public static final int k = 6;
    public static final int l = 7;

    private static native int initLogCutInternal(int i2, int i3);

    public native void startLogcut(int i2);

    public native void stopLogcut();

    static {
        d.loadLibrary();
        f4795d = 3;
        f4796e = null;
    }

    public static LogcutV2 a() {
        if (f4796e == null) {
            synchronized (LogcutV2.class) {
                if (f4796e == null) {
                    f4796e = new LogcutV2();
                    try {
                        initLogCutInternal(f4795d, Build.VERSION.SDK_INT);
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return f4796e;
    }
}
