package com.bytedance.common.jato.gfx;

import java.io.File;
/* loaded from: classes.dex */
public class BufferBarrier {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4707a = "BufferKeeper";

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f4708b = false;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f4709c = false;

    private static native void nativeBegin();

    private static native void nativeEnd();

    private static native void nativeHookLogs();

    private static native boolean nativeInit();

    public static synchronized void a() {
        synchronized (BufferBarrier.class) {
            if (com.bytedance.common.jato.d.loadLibrary()) {
                if (f4708b) {
                    return;
                }
                if (new File("/data/local/tmp/barrier_disable").exists()) {
                    return;
                }
                f4708b = nativeInit();
                if (new File("/data/local/tmp/barrier_logs").exists()) {
                    b();
                }
            }
        }
    }

    public static void b() {
        f4709c = true;
        nativeHookLogs();
    }

    public static synchronized void c() {
        synchronized (BufferBarrier.class) {
            if (f4708b) {
                nativeBegin();
            }
        }
    }

    public static synchronized void d() {
        synchronized (BufferBarrier.class) {
            if (f4708b) {
                nativeEnd();
            }
        }
    }
}
