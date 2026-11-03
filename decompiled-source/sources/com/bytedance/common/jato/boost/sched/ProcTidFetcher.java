package com.bytedance.common.jato.boost.sched;

import java.util.HashMap;
/* loaded from: classes.dex */
public class ProcTidFetcher {

    /* renamed from: a  reason: collision with root package name */
    private static final long f4631a = 1000;

    /* renamed from: b  reason: collision with root package name */
    private static long f4632b;

    /* renamed from: c  reason: collision with root package name */
    private static final HashMap<String, Integer> f4633c = new HashMap<>();

    /* renamed from: d  reason: collision with root package name */
    private static int f4634d = -1;

    /* renamed from: e  reason: collision with root package name */
    private static Thread f4635e = null;

    /* loaded from: classes.dex */
    public interface MonitorCallback {
        void a(String str, int i2);
    }

    /* loaded from: classes.dex */
    public interface a {
        void a(HashMap<String, Integer> hashMap);
    }

    private static native void nativeFetchThreadTid(String[] strArr, int[] iArr);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeHookMonitorThreadName(String[] strArr, MonitorCallback monitorCallback);

    private static native void nativeStopHookMonitorThreadName();

    public static int a() {
        return f4634d;
    }

    public static HashMap<String, Integer> b() {
        return f4633c;
    }

    public static void a(String[] strArr, a aVar) {
        int[] iArr = new int[strArr.length];
        nativeFetchThreadTid(strArr, iArr);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            int i3 = iArr[i2];
            if (i3 > 0) {
                f4633c.put(strArr[i2], Integer.valueOf(i3));
            }
        }
        if (aVar != null) {
            aVar.a(f4633c);
        }
    }

    public static void c() {
        int[] iArr = new int[1];
        nativeFetchThreadTid(new String[]{"(RenderThread)"}, iArr);
        if (iArr[0] > 0) {
            f4634d = iArr[0];
        }
    }

    public static void a(final String[] strArr, final MonitorCallback monitorCallback) {
        if (f4635e != null) {
            return;
        }
        Thread thread = new Thread() { // from class: com.bytedance.common.jato.boost.sched.ProcTidFetcher.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                ProcTidFetcher.nativeHookMonitorThreadName(strArr, monitorCallback);
            }
        };
        f4635e = thread;
        thread.start();
    }
}
