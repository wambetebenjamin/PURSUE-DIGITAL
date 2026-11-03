package com.bytedance.common.jato.boost.sched;

import android.app.Application;
import android.os.Process;
import android.util.Log;
import com.bytedance.common.jato.JatoXL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
/* compiled from: PriorityManager.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f4641c;

    /* renamed from: d  reason: collision with root package name */
    private static Map<Integer, Integer> f4642d = new ConcurrentHashMap();

    public static void a(int i2) {
        a(-1, i2);
    }

    public static void a(int i2, int i3) {
        int threadPriority;
        try {
            int i4 = 0;
            if (f4642d.get(Integer.valueOf(i2)) == null) {
                if (i2 != -1) {
                    threadPriority = Process.getThreadPriority(i2);
                } else {
                    threadPriority = Process.getThreadPriority(0);
                }
                i4 = threadPriority;
                f4642d.put(Integer.valueOf(i2), Integer.valueOf(i4));
            }
            if (i4 != i3) {
                if (i2 != -1) {
                    Process.setThreadPriority(i2, i3);
                } else {
                    Process.setThreadPriority(i3);
                }
            }
        } catch (Throwable th) {
            Log.e("jato", "error when set priority", th);
        }
    }

    public static synchronized void a() {
        synchronized (b.class) {
            Integer valueOf = Integer.valueOf(ProcTidFetcher.a());
            if (f4641c && valueOf != null) {
                JatoXL.resetCoreBind(valueOf.intValue());
                JatoXL.resetPriority(valueOf.intValue());
                f4641c = false;
            }
        }
    }

    public static synchronized void a(Application application, ExecutorService executorService, int i2) {
        synchronized (b.class) {
            Integer valueOf = Integer.valueOf(ProcTidFetcher.a());
            if (!f4641c && valueOf != null) {
                f4641c = true;
                JatoXL.bindBigCore(valueOf.intValue());
                a(valueOf.intValue(), i2);
            }
        }
    }

    public static void b() {
        try {
            if (f4642d.get(-1) == null) {
                return;
            }
            Process.setThreadPriority(0, f4642d.get(-1).intValue());
        } catch (Throwable th) {
            Log.e("jato", "error when reset priority", th);
        }
    }

    public static void b(int i2) {
        try {
            if (f4642d.get(Integer.valueOf(i2)) == null) {
                return;
            }
            Process.setThreadPriority(i2, f4642d.get(Integer.valueOf(i2)).intValue());
        } catch (Throwable th) {
            Log.e("jato", "error when reset priority", th);
        }
    }
}
