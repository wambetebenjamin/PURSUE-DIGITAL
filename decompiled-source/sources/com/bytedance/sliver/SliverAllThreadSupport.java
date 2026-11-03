package com.bytedance.sliver;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import com.bytedance.sliver.Sliver;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SliverAllThreadSupport {
    static final int SLIVER_GROUP_CACHE_SIZE = 50000;
    private static final int SUPPORT_API_MAX = 31;
    private static final int SUPPORT_API_MIN = 21;
    private static final List<a> threadGroups = new LinkedList();
    private static final Thread mainThread = Looper.getMainLooper().getThread();
    private static volatile boolean isStart = false;
    private static volatile boolean isRunning = false;
    private static int samplingRateMs = 10;
    private static Sliver.f filter = null;
    private static Handler threadHandler = null;
    private static ThreadGroup systemThreadGroup = null;

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nClearSliverGroup(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nDumpSliverGroup(long j2, String str);

    /* JADX INFO: Access modifiers changed from: private */
    public static native int nGetThreadId(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nNotifySliverGroup(long j2, Thread[] threadArr, long[] jArr);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nPauseSliverGroup(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nResumeSliverGroup(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native long nStartSliverGroup(int i2, int i3, int i4, int i5);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nStopSliverGroup(long j2);

    SliverAllThreadSupport() {
    }

    static /* synthetic */ List access$200() {
        return getAllThread();
    }

    static /* synthetic */ Handler access$300() {
        return newThreadHandler();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isStart() {
        return isStart;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean startAll(int i2, int i3, int i4, Sliver.a aVar, Sliver.f fVar) {
        int i5 = Build.VERSION.SDK_INT;
        if (i5 < 21 || i5 > 31 || isStart || !initSystemThreadGroup()) {
            return false;
        }
        samplingRateMs = i3;
        filter = fVar;
        List<a> list = threadGroups;
        list.clear();
        int max = Math.max(i2, 2);
        a aVar2 = new a(0, i3, i4, aVar);
        aVar2.a(mainThread);
        aVar2.a();
        list.add(aVar2);
        for (int i6 = 1; i6 < max; i6++) {
            threadGroups.add(new a(i6, i3, i4, aVar));
        }
        newThreadHandler().post(new b(i3 * 5));
        isStart = true;
        isRunning = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean stopAll() {
        if (isStart) {
            for (a aVar : threadGroups) {
                aVar.c();
            }
            threadGroups.clear();
            deleteThreadHandler();
            isStart = false;
            isRunning = false;
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean dumpAll(final String str, boolean z) {
        if (isStart) {
            Runnable runnable = new Runnable() { // from class: com.bytedance.sliver.SliverAllThreadSupport.1
                @Override // java.lang.Runnable
                public void run() {
                    if (SliverAllThreadSupport.dumpHeader(str)) {
                        for (a aVar : SliverAllThreadSupport.threadGroups) {
                            aVar.a(str);
                        }
                        for (a aVar2 : SliverAllThreadSupport.threadGroups) {
                            aVar2.b(str);
                        }
                    }
                }
            };
            if (z) {
                runnable.run();
                return true;
            }
            newThreadHandler().post(runnable);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean pauseAll() {
        if (isStart && isRunning) {
            for (a aVar : threadGroups) {
                aVar.e();
            }
            deleteThreadHandler();
            isRunning = false;
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean resumeAll() {
        if (isStart && !isRunning) {
            for (a aVar : threadGroups) {
                aVar.f();
            }
            newThreadHandler().post(new b(samplingRateMs));
            isRunning = true;
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean clearAll() {
        if (isStart) {
            for (a aVar : threadGroups) {
                aVar.g();
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean dumpHeader(String str) {
        BufferedWriter bufferedWriter = null;
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(str));
            try {
                bufferedWriter2.write("# sliver\n");
                bufferedWriter2.write("# pid:" + Process.myPid() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                bufferedWriter2.flush();
                try {
                    bufferedWriter2.close();
                } catch (IOException unused) {
                }
                return true;
            } catch (Throwable unused2) {
                bufferedWriter = bufferedWriter2;
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (IOException unused3) {
                    }
                }
                return false;
            }
        } catch (Throwable unused4) {
        }
    }

    private static synchronized Handler newThreadHandler() {
        Handler handler;
        synchronized (SliverAllThreadSupport.class) {
            if (threadHandler == null) {
                HandlerThread handlerThread = new HandlerThread("sliver_check_thread");
                handlerThread.start();
                threadHandler = new Handler(handlerThread.getLooper());
            }
            handler = threadHandler;
        }
        return handler;
    }

    private static synchronized void deleteThreadHandler() {
        synchronized (SliverAllThreadSupport.class) {
            Handler handler = threadHandler;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
                threadHandler.getLooper().quitSafely();
                threadHandler = null;
            }
        }
    }

    private static boolean initSystemThreadGroup() {
        try {
            if (systemThreadGroup == null) {
                Field declaredField = ThreadGroup.class.getDeclaredField("systemThreadGroup");
                declaredField.setAccessible(true);
                systemThreadGroup = (ThreadGroup) declaredField.get(null);
            }
        } catch (Throwable unused) {
        }
        return systemThreadGroup != null;
    }

    private static List<Thread> getAllThread() {
        int activeCount = systemThreadGroup.activeCount();
        Thread[] threadArr = new Thread[activeCount + (activeCount / 2)];
        int enumerate = systemThreadGroup.enumerate(threadArr);
        ArrayList arrayList = new ArrayList(enumerate);
        for (int i2 = 0; i2 < enumerate; i2++) {
            if (threadArr[i2] != mainThread && !threadArr[i2].getName().contains("sliver")) {
                arrayList.add(threadArr[i2]);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        private final int f13342a;

        /* renamed from: b  reason: collision with root package name */
        private List<Thread> f13343b;

        public b(int i2) {
            this.f13342a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<Thread> access$200 = SliverAllThreadSupport.access$200();
            if (this.f13343b == null) {
                b(access$200);
            } else {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (Thread thread : access$200) {
                    if (!this.f13343b.contains(thread)) {
                        arrayList.add(thread);
                    }
                }
                for (Thread thread2 : this.f13343b) {
                    if (!access$200.contains(thread2)) {
                        arrayList2.add(thread2);
                    }
                }
                a(arrayList2);
                b(arrayList);
            }
            this.f13343b = access$200;
            a();
            SliverAllThreadSupport.access$300().postDelayed(this, this.f13342a);
        }

        private void a() {
            for (a aVar : SliverAllThreadSupport.threadGroups) {
                aVar.a();
            }
        }

        private void a(List<Thread> list) {
            for (Thread thread : list) {
                for (int i2 = 1; i2 < SliverAllThreadSupport.threadGroups.size() && !((a) SliverAllThreadSupport.threadGroups.get(i2)).b(thread); i2++) {
                }
            }
        }

        private void b(List<Thread> list) {
            int size = list.size();
            int i2 = 0;
            while (true) {
                int i3 = 1;
                while (i2 < size) {
                    if (SliverAllThreadSupport.filter == null || SliverAllThreadSupport.filter.a(list.get(i2))) {
                        ((a) SliverAllThreadSupport.threadGroups.get(i3)).a(list.get(i2));
                        i2++;
                        i3++;
                        if (i3 >= SliverAllThreadSupport.threadGroups.size()) {
                            break;
                        }
                    } else {
                        i2++;
                    }
                }
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final int f13335a;

        /* renamed from: b  reason: collision with root package name */
        private final int f13336b;

        /* renamed from: e  reason: collision with root package name */
        private final int f13339e;

        /* renamed from: f  reason: collision with root package name */
        private final Sliver.a f13340f;

        /* renamed from: d  reason: collision with root package name */
        private volatile long f13338d = 0;

        /* renamed from: g  reason: collision with root package name */
        private volatile boolean f13341g = false;

        /* renamed from: c  reason: collision with root package name */
        private final HashSet<Thread> f13337c = new HashSet<>();

        a(int i2, int i3, int i4, Sliver.a aVar) {
            this.f13335a = i2;
            this.f13336b = i3;
            this.f13339e = i4;
            this.f13340f = aVar;
        }

        synchronized void a(Thread thread) {
            this.f13337c.add(thread);
            this.f13341g = true;
        }

        synchronized boolean b(Thread thread) {
            if (this.f13337c.isEmpty()) {
                return false;
            }
            if (this.f13337c.remove(thread)) {
                this.f13341g = true;
                return true;
            }
            return false;
        }

        synchronized void a() {
            b();
            if (this.f13338d == 0) {
                return;
            }
            if (this.f13341g) {
                ArrayList arrayList = new ArrayList(this.f13337c.size());
                ArrayList arrayList2 = new ArrayList(this.f13337c.size());
                Iterator<Thread> it = this.f13337c.iterator();
                while (it.hasNext()) {
                    Thread next = it.next();
                    long threadPeer = Sliver.getThreadPeer(next);
                    if (threadPeer != 0) {
                        arrayList.add(Long.valueOf(threadPeer));
                        arrayList2.add(next);
                    }
                }
                long[] jArr = new long[arrayList.size()];
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    jArr[i2] = ((Long) arrayList.get(i2)).longValue();
                }
                SliverAllThreadSupport.nNotifySliverGroup(this.f13338d, (Thread[]) arrayList2.toArray(new Thread[0]), jArr);
                this.f13341g = false;
            }
        }

        synchronized void b() {
            if (this.f13338d != 0) {
                return;
            }
            this.f13338d = SliverAllThreadSupport.nStartSliverGroup(this.f13336b, this.f13339e, Sliver.mode2Int(this.f13340f), this.f13335a);
        }

        synchronized void c() {
            if (this.f13338d == 0) {
                return;
            }
            SliverAllThreadSupport.nStopSliverGroup(this.f13338d);
        }

        synchronized void a(String str) {
            BufferedWriter bufferedWriter = null;
            try {
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(str, true));
                    try {
                        Iterator<Thread> it = this.f13337c.iterator();
                        while (it.hasNext()) {
                            Thread next = it.next();
                            long threadPeer = Sliver.getThreadPeer(next);
                            if (threadPeer != 0) {
                                bufferedWriter2.write("# thread_info:" + SliverAllThreadSupport.nGetThreadId(threadPeer) + CertificateUtil.DELIMITER + next.getName() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                            }
                        }
                        bufferedWriter2.flush();
                        bufferedWriter2.close();
                    } catch (Throwable unused) {
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                    }
                } catch (Throwable unused2) {
                }
            } catch (IOException unused3) {
            }
        }

        synchronized int d() {
            return this.f13337c.size();
        }

        synchronized void b(String str) {
            SliverAllThreadSupport.nDumpSliverGroup(this.f13338d, str);
        }

        synchronized void e() {
            SliverAllThreadSupport.nPauseSliverGroup(this.f13338d);
        }

        synchronized void f() {
            SliverAllThreadSupport.nResumeSliverGroup(this.f13338d);
        }

        synchronized void g() {
            SliverAllThreadSupport.nClearSliverGroup(this.f13338d);
        }
    }
}
