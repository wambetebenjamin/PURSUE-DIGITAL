package com.bytedance.sliver;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.react.bridge.BaseJavaModule;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes3.dex */
public final class Sliver {
    private static final int SUPPORT_API_MAX = 34;
    private static final int SUPPORT_API_MIN = 19;
    private static final String TAG = "SLIVER";
    private static final boolean isArt;
    private static volatile boolean isInit = false;
    private static final d sDefaultExecutor;
    private static final e sDefaultLogger;
    private static d sExecutor;
    private static e sLogger;
    private static boolean sLoggerEnable;
    private static final Field threadPeerField;
    private static final Field threadPeerFieldDalvik;
    private static final Map<Thread, b> cache = new ConcurrentHashMap(16);
    private static final Map<String, Long> mark = new ConcurrentHashMap(16);
    private static final List<c> sListenerList = new CopyOnWriteArrayList();

    /* loaded from: classes3.dex */
    public enum a {
        RING,
        NORMAL
    }

    /* loaded from: classes3.dex */
    public interface c {
        void a(Thread thread, long[] jArr, long j2);
    }

    /* loaded from: classes3.dex */
    public interface d {
        void a(Runnable runnable);
    }

    /* loaded from: classes3.dex */
    public interface e {
        void a(String str);

        void a(String str, Throwable th);
    }

    /* loaded from: classes3.dex */
    public interface f {
        boolean a(Thread thread);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nClear(long j2);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nDump(long j2, String str, long j3);

    private static native long[] nGetMethodStackTrace(Thread thread, long j2);

    private static native String nGetStackTrace(Thread thread, long j2);

    private static native String nGetStackTrace(long[] jArr);

    private static native String[] nGetStackTrace3(long[] jArr);

    private static native boolean nInit(int i2, Method method, boolean z, boolean z2);

    private static native void nPause(long j2);

    private static native void nResetSamplingMs(long j2, int i2);

    private static native void nResume(long j2);

    private static native long nStart(Thread thread, long j2, int i2, int i3, int i4);

    private static native void nStop(long j2);

    static {
        Field field;
        Field declaredField;
        e eVar = new e() { // from class: com.bytedance.sliver.Sliver.1
            @Override // com.bytedance.sliver.Sliver.e
            public void a(String str) {
                Log.i(Sliver.TAG, str);
            }

            @Override // com.bytedance.sliver.Sliver.e
            public void a(String str, Throwable th) {
                Log.e(Sliver.TAG, str, th);
            }
        };
        sDefaultLogger = eVar;
        sLogger = eVar;
        sLoggerEnable = false;
        d dVar = new d() { // from class: com.bytedance.sliver.Sliver.2
            @Override // com.bytedance.sliver.Sliver.d
            public void a(Runnable runnable) {
                AsyncTask.execute(runnable);
            }
        };
        sDefaultExecutor = dVar;
        sExecutor = dVar;
        boolean isArt2 = isArt();
        isArt = isArt2;
        Field field2 = null;
        try {
            if (isArt2) {
                declaredField = Thread.class.getDeclaredField("nativePeer");
            } else {
                declaredField = Thread.class.getDeclaredField("vmThread");
                try {
                    field2 = Class.forName("java.lang.VMThread").getDeclaredField("vmData");
                    field2.setAccessible(true);
                } catch (Throwable unused) {
                    Field field3 = field2;
                    field2 = declaredField;
                    field = field3;
                }
            }
            Field field4 = field2;
            field2 = declaredField;
            field = field4;
            try {
                field2.setAccessible(true);
            } catch (Throwable unused2) {
            }
        } catch (Throwable unused3) {
            field = field2;
        }
        threadPeerField = field2;
        threadPeerFieldDalvik = field;
    }

    public static void enableLog() {
        sLoggerEnable = true;
    }

    public static void setLogger(e eVar) {
        if (eVar != null) {
            enableLog();
            sLogger = eVar;
        }
    }

    public static void setExecutor(d dVar) {
        if (dVar != null) {
            sExecutor = dVar;
        }
    }

    public static synchronized boolean init(boolean z) {
        synchronized (Sliver.class) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 19 && i2 <= 34) {
                if (isInit) {
                    onStep("sliver already init");
                    return true;
                } else if (loadLibrary()) {
                    Method method = null;
                    try {
                        method = Looper.class.getMethod("loop", new Class[0]);
                    } catch (Throwable unused) {
                    }
                    if (!nInit(i2, method, z, isArt)) {
                        onError("sliver init failed, because native init error", new Throwable());
                        return false;
                    }
                    isInit = true;
                    onStep("sliver init success, protect is " + z);
                    return true;
                } else {
                    return false;
                }
            }
            onError("not support api : " + i2 + ", sliver support 19-34", new Throwable());
            return false;
        }
    }

    public static boolean isInit() {
        return isInit;
    }

    public static void mark(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        mark.put(str, Long.valueOf(System.nanoTime()));
    }

    public static synchronized void reportStack(Thread thread, long[] jArr, long j2) {
        synchronized (Sliver.class) {
            int i2 = 0;
            while (true) {
                List<c> list = sListenerList;
                if (i2 < list.size()) {
                    list.get(i2).a(thread, jArr, j2);
                    i2++;
                }
            }
        }
    }

    public static void registerSamplingListener(c cVar) {
        List<c> list = sListenerList;
        if (list.contains(cVar)) {
            return;
        }
        list.add(cVar);
    }

    public static void unRegisterSamplingListener(c cVar) {
        sListenerList.remove(cVar);
    }

    public static synchronized long[] getMethodStackTrace(Thread thread) {
        synchronized (Sliver.class) {
            if (!init(false)) {
                return new long[0];
            }
            long threadPeer = getThreadPeer(thread);
            if (threadPeer == 0) {
                return new long[0];
            }
            return nGetMethodStackTrace(thread, threadPeer);
        }
    }

    public static synchronized String getStackTrace(long[] jArr) {
        synchronized (Sliver.class) {
            if (jArr != null) {
                if (jArr.length != 0) {
                    return nGetStackTrace(jArr);
                }
            }
            return "";
        }
    }

    public static synchronized String[] getStackTraceArray(long[] jArr) {
        synchronized (Sliver.class) {
            if (jArr != null) {
                if (jArr.length != 0) {
                    return nGetStackTrace3(jArr);
                }
            }
            return null;
        }
    }

    public static synchronized String getStackTrace(Thread thread) {
        synchronized (Sliver.class) {
            if (init(false)) {
                long threadPeer = getThreadPeer(thread);
                if (threadPeer == 0) {
                    return "";
                }
                return nGetStackTrace(thread, threadPeer);
            }
            return "";
        }
    }

    public static boolean start(int i2) {
        return start(Thread.currentThread(), i2, 100000, a.RING);
    }

    public static synchronized boolean start(Thread thread, int i2, int i3, a aVar) {
        synchronized (Sliver.class) {
            if (SliverAllThreadSupport.isStart()) {
                onError("sliver start failed , because sliver all thread was started", new Throwable());
                return false;
            } else if (!init(false)) {
                onError("sliver start failed , because sliver init failed", new Throwable());
                return false;
            } else {
                Map<Thread, b> map = cache;
                if (map.containsKey(thread)) {
                    onStep(thread + " already sampling by sliver");
                    return true;
                }
                long threadPeer = getThreadPeer(thread);
                if (threadPeer == 0) {
                    onError("sliver start failed , because can't get thread peer", new Throwable());
                    return false;
                }
                int mode2Int = mode2Int(aVar);
                long nStart = nStart(thread, threadPeer, i2, i3, mode2Int);
                if (nStart == 0) {
                    onError("sliver start failed , because can't get native peer", new Throwable());
                    return false;
                }
                b bVar = new b();
                bVar.f13328a = nStart;
                bVar.f13329b = thread;
                bVar.f13330c = i2;
                bVar.f13331d = i3;
                bVar.f13332e = true;
                bVar.f13333f = mode2Int;
                map.put(thread, bVar);
                onStep(thread + " sampling by sliver success");
                return true;
            }
        }
    }

    public static boolean pause() {
        return pause(Thread.currentThread());
    }

    public static synchronized boolean pause(Thread thread) {
        synchronized (Sliver.class) {
            b bVar = cache.get(thread);
            if (bVar == null) {
                onError(thread + " pause failed, because thread is not sampling", new Throwable());
                return false;
            } else if (!bVar.f13332e) {
                onStep(thread + " already pause");
                return true;
            } else {
                nPause(bVar.f13328a);
                bVar.f13332e = false;
                onStep(thread + " pause success");
                return true;
            }
        }
    }

    public static boolean resume() {
        return resume(Thread.currentThread());
    }

    public static synchronized boolean resume(Thread thread) {
        synchronized (Sliver.class) {
            b bVar = cache.get(thread);
            if (bVar == null) {
                onError(thread + " resume failed, because thread is not sampling", new Throwable());
                return false;
            } else if (bVar.f13332e) {
                onStep(thread + " already resume");
                return true;
            } else {
                nResume(bVar.f13328a);
                bVar.f13332e = true;
                onStep(thread + " resume success");
                return true;
            }
        }
    }

    public static boolean stop() {
        return stop(Thread.currentThread());
    }

    public static synchronized boolean stop(Thread thread) {
        synchronized (Sliver.class) {
            if (!isInit) {
                onError(thread + " stop failed, because sliver is not init", new Throwable());
                return false;
            }
            b remove = cache.remove(thread);
            if (remove == null) {
                onError(thread + " stop failed, because thread is not sampling", new Throwable());
                return true;
            }
            nStop(remove.f13328a);
            onStep(thread + " stop success");
            return true;
        }
    }

    public static boolean dump(String str) {
        return dump(Thread.currentThread(), str);
    }

    public static boolean dump(String str, String str2) {
        return dump(Thread.currentThread(), str, str2);
    }

    public static boolean dump(Thread thread, String str) {
        return dump(thread, str, false);
    }

    public static boolean dump(Thread thread, String str, String str2) {
        return dump(thread, str, false, str2);
    }

    public static synchronized boolean dump(Thread thread, String str, boolean z) {
        boolean dump;
        synchronized (Sliver.class) {
            dump = dump(thread, str, z, null);
        }
        return dump;
    }

    public static synchronized boolean dump(final Thread thread, final String str, final boolean z, final String str2) {
        synchronized (Sliver.class) {
            if (!isInit) {
                onError(thread + " dump failed, because sliver is not init", new Throwable());
                return false;
            }
            final b bVar = cache.get(thread);
            if (bVar == null) {
                onError(thread + " stop failed, because thread is not sampling", new Throwable());
                return false;
            }
            Runnable runnable = new Runnable() { // from class: com.bytedance.sliver.Sliver.3
                @Override // java.lang.Runnable
                public void run() {
                    Long l;
                    Sliver.nDump(bVar.f13328a, str, (TextUtils.isEmpty(str2) || (l = (Long) Sliver.mark.get(str2)) == null) ? -1L : l.longValue());
                    Sliver.onStep(thread + " dump " + (z ? BaseJavaModule.METHOD_TYPE_SYNC : BaseJavaModule.METHOD_TYPE_ASYNC) + " success");
                }
            };
            onStep(thread + " dump " + (z ? BaseJavaModule.METHOD_TYPE_SYNC : BaseJavaModule.METHOD_TYPE_ASYNC) + " start");
            if (z) {
                runnable.run();
            } else {
                sExecutor.a(runnable);
            }
            return true;
        }
    }

    public static boolean clear() {
        return clear(Thread.currentThread());
    }

    public static synchronized boolean clear(final Thread thread) {
        synchronized (Sliver.class) {
            if (!isInit) {
                onError(thread + " clear failed, because sliver is not init", new Throwable());
                return false;
            }
            final b bVar = cache.get(thread);
            if (bVar == null) {
                onError(thread + " clear failed, because thread is not sampling", new Throwable());
                return false;
            }
            sExecutor.a(new Runnable() { // from class: com.bytedance.sliver.Sliver.4
                @Override // java.lang.Runnable
                public void run() {
                    Sliver.nClear(b.this.f13328a);
                    Sliver.onStep(thread + " clear success");
                }
            });
            return true;
        }
    }

    public static boolean startAll(int i2, int i3) {
        return startAll(i2, i3, 50000, a.RING, null);
    }

    public static synchronized boolean startAll(int i2, int i3, int i4, a aVar, f fVar) {
        synchronized (Sliver.class) {
            if (cache.isEmpty()) {
                if (init(false)) {
                    return SliverAllThreadSupport.startAll(i2, i3, i4, aVar, fVar);
                }
                return false;
            }
            return false;
        }
    }

    public static synchronized boolean stopAll() {
        boolean stopAll;
        synchronized (Sliver.class) {
            stopAll = SliverAllThreadSupport.stopAll();
        }
        return stopAll;
    }

    public static synchronized boolean dumpAll(String str) {
        boolean dumpAll;
        synchronized (Sliver.class) {
            dumpAll = dumpAll(str, false);
        }
        return dumpAll;
    }

    public static synchronized boolean dumpAll(String str, boolean z) {
        boolean dumpAll;
        synchronized (Sliver.class) {
            dumpAll = SliverAllThreadSupport.dumpAll(str, z);
        }
        return dumpAll;
    }

    public static synchronized boolean pauseAll() {
        boolean pauseAll;
        synchronized (Sliver.class) {
            pauseAll = SliverAllThreadSupport.pauseAll();
        }
        return pauseAll;
    }

    public static synchronized boolean resumeAll() {
        boolean resumeAll;
        synchronized (Sliver.class) {
            resumeAll = SliverAllThreadSupport.resumeAll();
        }
        return resumeAll;
    }

    public static synchronized boolean clearAll() {
        boolean clearAll;
        synchronized (Sliver.class) {
            clearAll = SliverAllThreadSupport.clearAll();
        }
        return clearAll;
    }

    public static synchronized boolean resetSamplingMs(int i2) {
        boolean resetSamplingMs;
        synchronized (Sliver.class) {
            resetSamplingMs = resetSamplingMs(Thread.currentThread(), i2);
        }
        return resetSamplingMs;
    }

    public static synchronized boolean resetSamplingMs(Thread thread, int i2) {
        synchronized (Sliver.class) {
            if (!isInit) {
                onError(thread + " reset sampling ms  failed, because sliver is not init", new Throwable());
                return false;
            }
            b bVar = cache.get(thread);
            if (bVar == null) {
                onError(thread + " reset sampling ms  failed, because thread is not sampling", new Throwable());
                return false;
            }
            nResetSamplingMs(bVar.f13328a, i2);
            onStep(thread + " reset sampling ms success");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getThreadPeer(Thread thread) {
        Field field;
        Object obj;
        Object obj2;
        try {
            if (isArt) {
                Field field2 = threadPeerField;
                if (field2 != null && (obj2 = field2.get(thread)) != null) {
                    return ((Long) obj2).longValue();
                }
                return 0L;
            }
            Field field3 = threadPeerField;
            if (field3 != null && (field = threadPeerFieldDalvik) != null && (obj = field.get(field3.get(thread))) != null) {
                return ((Integer) obj).intValue();
            }
            return 0L;
        } catch (Throwable unused) {
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int mode2Int(a aVar) {
        return (aVar != a.RING && aVar == a.NORMAL) ? 1 : 0;
    }

    private static boolean isArt() {
        String property;
        if (Build.VERSION.SDK_INT >= 21 || (property = System.getProperty("java.vm.version")) == null) {
            return true;
        }
        Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(property);
        if (matcher.matches()) {
            try {
                int parseInt = Integer.parseInt(matcher.group(1));
                int parseInt2 = Integer.parseInt(matcher.group(2));
                if (parseInt <= 2) {
                    return parseInt == 2 && parseInt2 >= 1;
                }
                return true;
            } catch (Throwable unused) {
                return true;
            }
        }
        return true;
    }

    private static boolean loadLibrary() {
        try {
            System.loadLibrary("sliver");
            return true;
        } catch (Throwable th) {
            onError("sliver so load failed", th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        long f13328a;

        /* renamed from: b  reason: collision with root package name */
        Thread f13329b;

        /* renamed from: c  reason: collision with root package name */
        int f13330c;

        /* renamed from: d  reason: collision with root package name */
        int f13331d;

        /* renamed from: e  reason: collision with root package name */
        boolean f13332e;

        /* renamed from: f  reason: collision with root package name */
        int f13333f;

        private b() {
        }
    }

    static void onStep(String str) {
        if (sLoggerEnable) {
            sLogger.a(str);
        }
    }

    static void onError(String str, Throwable th) {
        if (sLoggerEnable) {
            sLogger.a(str, th);
        }
    }
}
