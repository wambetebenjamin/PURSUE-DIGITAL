package com.bytedance.common.jato.threads;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import kotlin.UShort;
/* compiled from: ThreadCore.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5018a = "HeapTaskDaemon";

    /* renamed from: b  reason: collision with root package name */
    private static final String f5019b = "Jit thread pool worker thread";

    public static boolean a(Thread thread) {
        int nativeGetThreadTid = ThreadNative.nativeGetThreadTid(thread);
        if (nativeGetThreadTid > 0) {
            return ThreadNative.nativeSuspendThread(nativeGetThreadTid);
        }
        return false;
    }

    public static boolean b(Thread thread) {
        int nativeGetThreadTid = ThreadNative.nativeGetThreadTid(thread);
        if (nativeGetThreadTid > 0) {
            return ThreadNative.nativeResumeThread(nativeGetThreadTid);
        }
        return false;
    }

    public static boolean a(String str) {
        return ThreadNative.nativeSuspendThreadByName(str);
    }

    public static boolean b(String str) {
        return ThreadNative.nativeResumeThreadByName(str);
    }

    public static ThreadInfo[] a(boolean z) {
        return ThreadNative.nativeGetAllThreadInfo(z);
    }

    public static int[] c(Thread thread) {
        int nativeGetThreadTid = ThreadNative.nativeGetThreadTid(thread);
        if (nativeGetThreadTid > 0) {
            return ThreadNative.nativeGetThreadStats(nativeGetThreadTid);
        }
        return null;
    }

    public static boolean a() {
        return ThreadNative.nativeSuspendThreadByName(f5018a);
    }

    public static boolean b() {
        return ThreadNative.nativeResumeThreadByName(f5018a);
    }

    public static void c() {
        ThreadInfo[] nativeGetAllThreadInfo = ThreadNative.nativeGetAllThreadInfo(false);
        if (nativeGetAllThreadInfo == null || nativeGetAllThreadInfo.length == 0) {
            return;
        }
        for (ThreadInfo threadInfo : nativeGetAllThreadInfo) {
            String threadName = threadInfo.getThreadName();
            if (threadName != null && threadName.startsWith(f5019b)) {
                ThreadNative.nativeSuspendThread(threadInfo.getTid());
            }
        }
    }

    public static void d() {
        ThreadInfo[] nativeGetAllThreadInfo = ThreadNative.nativeGetAllThreadInfo(false);
        if (nativeGetAllThreadInfo == null || nativeGetAllThreadInfo.length == 0) {
            return;
        }
        for (ThreadInfo threadInfo : nativeGetAllThreadInfo) {
            String threadName = threadInfo.getThreadName();
            if (threadName != null && threadName.startsWith(f5019b)) {
                ThreadNative.nativeResumeThread(threadInfo.getTid());
            }
        }
    }

    public static List<b> e() {
        byte[] bArr;
        try {
            Method declaredMethod = Class.forName("org.apache.harmony.dalvik.ddmc.DdmVmInternal").getDeclaredMethod("getThreadStats", new Class[0]);
            declaredMethod.setAccessible(true);
            bArr = (byte[]) declaredMethod.invoke(null, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
            bArr = null;
        }
        if (bArr == null) {
            return null;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr, 0, bArr.length);
        wrap.order(ByteOrder.BIG_ENDIAN);
        wrap.get();
        wrap.get();
        int i2 = wrap.getShort() & UShort.MAX_VALUE;
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(new b(wrap.getInt(), wrap.get() & 255, wrap.getInt(), wrap.getInt(), wrap.getInt(), wrap.get() & 255));
        }
        return arrayList;
    }
}
