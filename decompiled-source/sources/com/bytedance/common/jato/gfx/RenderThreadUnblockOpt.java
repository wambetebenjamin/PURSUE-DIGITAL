package com.bytedance.common.jato.gfx;
/* loaded from: classes.dex */
public class RenderThreadUnblockOpt {

    /* renamed from: a  reason: collision with root package name */
    public static final int f4714a = 1;

    /* renamed from: b  reason: collision with root package name */
    public static final int f4715b = 2;

    /* renamed from: c  reason: collision with root package name */
    public static final int f4716c = 4;

    /* renamed from: d  reason: collision with root package name */
    public static final int f4717d = 9;

    /* renamed from: e  reason: collision with root package name */
    public static final int f4718e = 7;

    /* renamed from: f  reason: collision with root package name */
    public static final int f4719f = 256;

    /* renamed from: g  reason: collision with root package name */
    private static final int f4720g = 8;

    private static native int nativeUnblockRenderThreadTask(int i2);

    static {
        com.bytedance.common.jato.d.loadLibrary();
    }

    public static void a(int i2) {
        nativeUnblockRenderThreadTask(i2);
    }
}
