package com.ss.android.agilelogger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.android.alog.Alog;
import com.bytedance.apm.constant.r;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.ss.android.agilelogger.b;
import com.ss.android.agilelogger.utils.b;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
/* compiled from: ALog.java */
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static int f17921a = 3;

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f17922b = false;

    /* renamed from: c  reason: collision with root package name */
    private static volatile Set<String> f17923c = null;

    /* renamed from: d  reason: collision with root package name */
    private static volatile boolean f17924d = false;

    /* renamed from: e  reason: collision with root package name */
    private static volatile d f17925e = null;

    /* renamed from: j  reason: collision with root package name */
    private static HandlerThread f17930j = null;
    private static volatile Handler k = null;
    private static final int m = 1;
    private static final int n = 2;
    public static volatile com.ss.android.agilelogger.b sConfig;

    /* renamed from: f  reason: collision with root package name */
    private static volatile List<INativeFuncAddrCallback> f17926f = new ArrayList();

    /* renamed from: g  reason: collision with root package name */
    private static ScheduledExecutorService f17927g = null;

    /* renamed from: h  reason: collision with root package name */
    private static volatile Alog f17928h = null;

    /* renamed from: i  reason: collision with root package name */
    private static ArrayList<WeakReference<Alog>> f17929i = new ArrayList<>();
    private static volatile ExecutorService sSingleThreadExecutor = null;
    private static volatile boolean o = false;
    private static Object p = new Object();
    private static final ThreadLocal<Long> sThreadId = new ThreadLocal<Long>() { // from class: com.ss.android.agilelogger.a.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public Long initialValue() {
            return Long.valueOf(Process.myTid());
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static int a(int i2) {
        return i2 - 2;
    }

    @Deprecated
    public static void forceLogSharding() {
    }

    @Deprecated
    public static void setPrintStackTrace(boolean z) {
    }

    @Deprecated
    public static void setsPackageClassName(String str) {
    }

    static /* synthetic */ long access$500() {
        return getThreadId();
    }

    public static boolean init(com.ss.android.agilelogger.b bVar) {
        Queue<f> cachedLog;
        boolean z = false;
        if (bVar == null) {
            return false;
        }
        sConfig = bVar;
        try {
            Alog.init(new e());
            synchronized (p) {
                if (o) {
                    return false;
                }
                o = true;
                f17921a = bVar.getLevel();
                boolean isMainProcess = h.isMainProcess(bVar.getContext());
                boolean isOffloadMainThreadWrite = bVar.isOffloadMainThreadWrite();
                boolean z2 = !isOffloadMainThreadWrite && bVar.isMainThreadSpeedUp() && isMainProcess;
                boolean b2 = bVar.b();
                boolean e2 = bVar.e();
                if (!isMainProcess) {
                    bVar.setMaxDirSize((int) (bVar.getMaxDirSize() * bVar.a()));
                }
                com.bytedance.android.alog.c.setInstance(new Alog.b(bVar.getContext()).setInstanceName("default").setLevel(a(bVar.getLevel())).setSyslog(f17922b).setLogFileDir(bVar.getLogDirPath()).setLogFileSizeEach(bVar.getPerSize()).setLogFileSizeTotal(z2 ? (bVar.getMaxDirSize() / 3) * 2 : bVar.getMaxDirSize()).setLogFileExpDays(bVar.getLogFileExpDays()).setCacheFileDir(bVar.getBufferDirPath()).setCacheFileSizeEach(isMainProcess ? bVar.f() * 1024 : 32768).setCacheFileSizeTotal(isMainProcess ? bVar.f() * 3 * 1024 : 65536).setMode(Alog.d.SAFE).setTimeFormat(Alog.g.RAW).setPrefixFormat(Alog.e.LEGACY).setCompress(bVar.isCompress() ? Alog.c.ZSTD : Alog.c.NONE).setSymCrypt(bVar.isEncrypt() ? Alog.f.TEA_16 : Alog.f.NONE).setAsymCrypt(bVar.isEncrypt() ? Alog.a.EC_SECP256K1 : Alog.a.NONE).setServerPublicKey(bVar.getPubKey()).a(bVar.d()).build());
                if (isOffloadMainThreadWrite && (b2 || isMainProcess)) {
                    if (e2) {
                        sSingleThreadExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.ss.android.agilelogger.a.2
                            @Override // java.util.concurrent.ThreadFactory
                            public Thread newThread(Runnable runnable) {
                                Thread thread = new Thread(runnable);
                                thread.setName("Alog_newthreadpoolimpl");
                                return thread;
                            }
                        });
                    } else {
                        HandlerThread handlerThread = new HandlerThread("Alog_main_delegate");
                        f17930j = handlerThread;
                        handlerThread.start();
                        k = new Handler(f17930j.getLooper()) { // from class: com.ss.android.agilelogger.a.3
                            @Override // android.os.Handler
                            public void handleMessage(Message message) {
                                int i2 = message.what;
                                if (i2 != 1) {
                                    if (i2 != 2) {
                                        return;
                                    }
                                    com.bytedance.android.alog.c.asyncFlush();
                                } else if (message.obj == null || !(message.obj instanceof C0325a)) {
                                } else {
                                    a.b((C0325a) message.obj);
                                }
                            }
                        };
                    }
                }
                if (z2) {
                    f17928h = new Alog.b(bVar.getContext()).setInstanceName("main").setLevel(a(bVar.getLevel())).setSyslog(f17922b).setLogFileDir(bVar.getLogDirPath()).setLogFileSizeEach(bVar.getPerSize() / 2).setLogFileSizeTotal(bVar.getMaxDirSize() / 3).setLogFileExpDays(bVar.getLogFileExpDays()).setCacheFileDir(bVar.getBufferDirPath()).setCacheFileSizeEach(32768).setCacheFileSizeTotal(98304).setMode(Alog.d.SAFE).setTimeFormat(Alog.g.RAW).setPrefixFormat(Alog.e.LEGACY).setCompress(bVar.isCompress() ? Alog.c.ZSTD : Alog.c.NONE).setSymCrypt(bVar.isEncrypt() ? Alog.f.TEA_16 : Alog.f.NONE).setAsymCrypt(bVar.isEncrypt() ? Alog.a.EC_SECP256K1 : Alog.a.NONE).setServerPublicKey(bVar.getPubKey()).a(bVar.d()).build();
                }
                final String bufferDirPath = bVar.getBufferDirPath();
                final String logDirPath = bVar.getLogDirPath();
                final Queue<f> queue = null;
                if (f17925e != null && ((cachedLog = f17925e.getCachedLog()) == null || cachedLog.size() != 0)) {
                    queue = cachedLog;
                }
                if (queue != null || getNativeFuncAddrCallbackList().size() > 0) {
                    Runnable runnable = new Runnable() { // from class: com.ss.android.agilelogger.a.4
                        @Override // java.lang.Runnable
                        public void run() {
                            Queue queue2 = queue;
                            if (queue2 != null) {
                                a.b(queue2);
                                a.f17925e.notifyCacheLogConsumed();
                            }
                            for (INativeFuncAddrCallback iNativeFuncAddrCallback : a.getNativeFuncAddrCallbackList()) {
                                if (iNativeFuncAddrCallback != null) {
                                    iNativeFuncAddrCallback.onNativeFuncReady(com.bytedance.android.alog.c.getLegacyWriteFuncAddr());
                                }
                            }
                            try {
                                Thread.sleep(r.APP_START_MAX_LIMIT_MS);
                            } catch (Exception unused) {
                            }
                            a.b(bufferDirPath, logDirPath);
                        }
                    };
                    ScheduledExecutorService scheduledExecutorService = f17927g;
                    if (scheduledExecutorService == null) {
                        new Thread(runnable, "_ALOG_OPT_").start();
                    } else {
                        scheduledExecutorService.execute(runnable);
                    }
                    z = true;
                }
                if (!z) {
                    ScheduledExecutorService scheduledExecutorService2 = f17927g;
                    if (scheduledExecutorService2 == null) {
                        new Timer("_ALOG_OPT_").schedule(new TimerTask() { // from class: com.ss.android.agilelogger.a.5
                            @Override // java.util.TimerTask, java.lang.Runnable
                            public void run() {
                                a.b(bufferDirPath, logDirPath);
                            }
                        }, r.APP_START_MAX_LIMIT_MS);
                    } else {
                        scheduledExecutorService2.schedule(new Runnable() { // from class: com.ss.android.agilelogger.a.6
                            @Override // java.lang.Runnable
                            public void run() {
                                a.b(bufferDirPath, logDirPath);
                            }
                        }, 15L, TimeUnit.SECONDS);
                    }
                }
                f17924d = true;
                return true;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, String str2) {
        File[] listFiles;
        File[] listFiles2;
        File file = new File(str);
        if (file.exists() && (listFiles2 = file.listFiles(new FilenameFilter() { // from class: com.ss.android.agilelogger.a.7
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str3) {
                return str3.startsWith(".logCache_");
            }
        })) != null) {
            for (File file2 : listFiles2) {
                file2.delete();
            }
        }
        File file3 = new File(str2);
        if (!file3.exists() || (listFiles = file3.listFiles(new FilenameFilter() { // from class: com.ss.android.agilelogger.a.13
            @Override // java.io.FilenameFilter
            public boolean accept(File file4, String str3) {
                if (str3.endsWith(".hoting")) {
                    return true;
                }
                return str3.endsWith(".hot") && !str3.endsWith(".alog.hot");
            }
        })) == null) {
            return;
        }
        for (File file4 : listFiles) {
            file4.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Queue<f> queue) {
        for (f fVar : queue) {
            if (b(fVar.mLevel, fVar.mTag)) {
                a(fVar);
                com.bytedance.android.alog.c.a(a(fVar.mLevel), fVar.mTag, fVar.mMsg);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ALog.java */
    /* renamed from: com.ss.android.agilelogger.a$12  reason: invalid class name */
    /* loaded from: classes9.dex */
    public static /* synthetic */ class AnonymousClass12 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f17939a;

        static {
            int[] iArr = new int[b.a.values().length];
            f17939a = iArr;
            try {
                iArr[b.a.MSG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17939a[b.a.STACKTRACE_STR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17939a[b.a.BORDER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f17939a[b.a.JSON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f17939a[b.a.BUNDLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f17939a[b.a.INTENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f17939a[b.a.THROWABLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f17939a[b.a.THREAD.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f17939a[b.a.STACKTRACE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private static void a(f fVar) {
        String str;
        switch (AnonymousClass12.f17939a[fVar.mFormatType.ordinal()]) {
            case 1:
                str = (String) fVar.mObj;
                break;
            case 2:
                if (fVar.mObj2 == null) {
                    str = com.ss.android.agilelogger.utils.e.getStackTraceString((Throwable) fVar.mObj);
                    break;
                } else {
                    str = fVar.mObj2 + com.ss.android.agilelogger.utils.e.getStackTraceString((Throwable) fVar.mObj);
                    break;
                }
            case 3:
            case 4:
                str = com.ss.android.agilelogger.utils.b.format(fVar.mFormatType, (String) fVar.mObj);
                break;
            case 5:
                str = com.ss.android.agilelogger.utils.b.format(fVar.mFormatType, (Bundle) fVar.mObj);
                break;
            case 6:
                str = com.ss.android.agilelogger.utils.b.format(fVar.mFormatType, (Intent) fVar.mObj);
                break;
            case 7:
                str = com.ss.android.agilelogger.utils.b.format(fVar.mFormatType, (Throwable) fVar.mObj);
                break;
            case 8:
                str = com.ss.android.agilelogger.utils.b.format(fVar.mFormatType, (Thread) fVar.mObj);
                break;
            case 9:
                str = com.ss.android.agilelogger.utils.b.format(fVar.mFormatType, (StackTraceElement[]) fVar.mObj);
                break;
            default:
                str = "";
                break;
        }
        fVar.mMsg = str;
    }

    public static void setDebug(boolean z) {
        f17922b = z;
        com.bytedance.android.alog.c.setSyslog(f17922b);
        if (f17928h != null) {
            f17928h.setSyslog(f17922b);
        }
    }

    public static boolean isInitSuccess() {
        return f17924d;
    }

    public static void setBlockTagSet(Set<String> set) {
        f17923c = Collections.unmodifiableSet(set);
    }

    public static Set<String> getBlockTagSet() {
        return f17923c;
    }

    public static void setILogCacheCallback(d dVar) {
        f17925e = dVar;
    }

    public static void addNativeFuncAddrCallback(INativeFuncAddrCallback iNativeFuncAddrCallback) {
        f17926f.add(iNativeFuncAddrCallback);
    }

    public static List<INativeFuncAddrCallback> getNativeFuncAddrCallbackList() {
        return f17926f;
    }

    public static boolean b(int i2, String str) {
        if (i2 < f17921a) {
            return false;
        }
        Set<String> set = f17923c;
        return set == null || TextUtils.isEmpty(str) || !set.contains(str);
    }

    public static void v(String str, String str2) {
        if (b(2, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(2, str, str2);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(2, str, str2);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(2, str, str2);
                } else if (isMainThread && k != null) {
                    a(2, str, str2);
                } else if (f17928h != null && isMainThread) {
                    f17928h.v(str, str2);
                } else {
                    com.bytedance.android.alog.c.a(str, str2);
                }
            }
        }
    }

    public static void d(String str, String str2) {
        if (b(3, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(3, str, str2);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(3, str, str2);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(3, str, str2);
                } else if (isMainThread && k != null) {
                    a(3, str, str2);
                } else if (f17928h != null && isMainThread) {
                    f17928h.d(str, str2);
                } else {
                    com.bytedance.android.alog.c.b(str, str2);
                }
            }
        }
    }

    public static void i(String str, String str2) {
        if (b(4, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(4, str, str2);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(4, str, str2);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(4, str, str2);
                } else if (isMainThread && k != null) {
                    a(4, str, str2);
                } else if (f17928h != null && isMainThread) {
                    f17928h.i(str, str2);
                } else {
                    com.bytedance.android.alog.c.c(str, str2);
                }
            }
        }
    }

    public static void w(String str, String str2) {
        if (b(5, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(5, str, str2);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(5, str, str2);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(5, str, str2);
                } else if (isMainThread && k != null) {
                    a(5, str, str2);
                } else if (f17928h != null && isMainThread) {
                    f17928h.w(str, str2);
                } else {
                    com.bytedance.android.alog.c.g(str, str2);
                }
            }
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (b(5, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(5, str, str2, th, null, null);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(5, str, str2, th, null, null);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(5, str, str2, th, null, null);
                } else if (isMainThread && k != null) {
                    a(5, str, str2, th, null, null);
                } else {
                    String str3 = str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + com.ss.android.agilelogger.utils.e.getStackTraceString(th);
                    if (f17928h != null && isMainThread) {
                        f17928h.w(str, str3);
                    } else {
                        com.bytedance.android.alog.c.g(str, str3);
                    }
                }
            }
        }
    }

    public static void w(String str, Throwable th) {
        if (b(5, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(5, str, null, th, null, null);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(5, str, null, th, null, null);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(5, str, null, th, null, null);
                } else if (isMainThread && k != null) {
                    a(5, str, null, th, null, null);
                } else {
                    String stackTraceString = com.ss.android.agilelogger.utils.e.getStackTraceString(th);
                    if (f17928h != null && isMainThread) {
                        f17928h.w(str, stackTraceString);
                    } else {
                        com.bytedance.android.alog.c.g(str, stackTraceString);
                    }
                }
            }
        }
    }

    public static void e(String str, String str2) {
        if (b(6, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(6, str, str2);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(6, str, str2);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(6, str, str2);
                } else if (isMainThread && k != null) {
                    a(6, str, str2);
                } else if (f17928h != null && isMainThread) {
                    f17928h.e(str, str2);
                } else {
                    com.bytedance.android.alog.c.h(str, str2);
                }
            }
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (b(6, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(6, str, str2, th, null, null);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(6, str, str2, th, null, null);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(6, str, str2, th, null, null);
                } else if (isMainThread && k != null) {
                    a(6, str, str2, th, null, null);
                } else {
                    String str3 = str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + com.ss.android.agilelogger.utils.e.getStackTraceString(th);
                    if (f17928h != null && isMainThread) {
                        f17928h.e(str, str3);
                    } else {
                        com.bytedance.android.alog.c.h(str, str3);
                    }
                }
            }
        }
    }

    public static void e(String str, Throwable th) {
        if (b(6, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(6, str, null, th, null, null);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(6, str, null, th, null, null);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(6, str, null, th, null, null);
                } else if (isMainThread && k != null) {
                    a(6, str, null, th, null, null);
                } else {
                    String stackTraceString = com.ss.android.agilelogger.utils.e.getStackTraceString(th);
                    if (f17928h != null && isMainThread) {
                        f17928h.e(str, stackTraceString);
                    } else {
                        com.bytedance.android.alog.c.h(str, stackTraceString);
                    }
                }
            }
        }
    }

    public static void header(int i2, String str, String str2) {
        if (b(i2, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(i2, str, str2, null, b.a.BORDER, null);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(i2, str, str2, null, b.a.BORDER, null);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(i2, str, str2, null, b.a.BORDER, null);
                } else if (isMainThread && k != null) {
                    a(i2, str, str2, null, b.a.BORDER, null);
                } else {
                    int a2 = a(i2);
                    String format = com.ss.android.agilelogger.utils.b.format(b.a.BORDER, str2);
                    if (f17928h != null && isMainThread) {
                        f17928h.write(a2, str, format);
                    } else {
                        com.bytedance.android.alog.c.a(a2, str, format);
                    }
                }
            }
        }
    }

    public static void json(int i2, String str, String str2) {
        if (b(i2, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(i2, str, str2, null, b.a.JSON, null);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(i2, str, str2, null, b.a.JSON, null);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(i2, str, str2, null, b.a.JSON, null);
                } else if (isMainThread && k != null) {
                    a(i2, str, str2, null, b.a.JSON, null);
                } else {
                    int a2 = a(i2);
                    String format = com.ss.android.agilelogger.utils.b.format(b.a.JSON, str2);
                    if (f17928h != null && isMainThread) {
                        f17928h.write(a2, str, format);
                    } else {
                        com.bytedance.android.alog.c.a(a2, str, format);
                    }
                }
            }
        }
    }

    public static void bundle(int i2, String str, Bundle bundle) {
        if (b(i2, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(i2, str, null, null, b.a.BUNDLE, bundle);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(i2, str, null, null, b.a.BUNDLE, bundle);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(i2, str, null, null, b.a.BUNDLE, bundle);
                } else if (isMainThread && k != null) {
                    a(i2, str, null, null, b.a.BUNDLE, bundle);
                } else {
                    int a2 = a(i2);
                    String format = com.ss.android.agilelogger.utils.b.format(b.a.BUNDLE, bundle);
                    if (f17928h != null && isMainThread) {
                        f17928h.write(a2, str, format);
                    } else {
                        com.bytedance.android.alog.c.a(a2, str, format);
                    }
                }
            }
        }
    }

    public static void intent(int i2, String str, Intent intent) {
        if (b(i2, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(i2, str, null, null, b.a.INTENT, intent);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(i2, str, null, null, b.a.INTENT, intent);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(i2, str, null, null, b.a.INTENT, intent);
                } else if (isMainThread && k != null) {
                    a(i2, str, null, null, b.a.INTENT, intent);
                } else {
                    int a2 = a(i2);
                    String format = com.ss.android.agilelogger.utils.b.format(b.a.INTENT, intent);
                    if (f17928h != null && isMainThread) {
                        f17928h.write(a2, str, format);
                    } else {
                        com.bytedance.android.alog.c.a(a2, str, format);
                    }
                }
            }
        }
    }

    public static void throwable(int i2, String str, Throwable th) {
        if (b(i2, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(i2, str, null, th, b.a.THROWABLE, null);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(i2, str, null, th, b.a.THROWABLE, null);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(i2, str, null, th, b.a.THROWABLE, null);
                } else if (isMainThread && k != null) {
                    a(i2, str, null, null, b.a.THROWABLE, th);
                } else {
                    int a2 = a(i2);
                    String format = com.ss.android.agilelogger.utils.b.format(b.a.THROWABLE, th);
                    if (f17928h != null && isMainThread) {
                        f17928h.write(a2, str, format);
                    } else {
                        com.bytedance.android.alog.c.a(a2, str, format);
                    }
                }
            }
        }
    }

    public static void thread(int i2, String str, Thread thread) {
        if (b(i2, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(i2, str, null, null, b.a.THREAD, thread);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(i2, str, null, null, b.a.THREAD, thread);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(i2, str, null, null, b.a.THREAD, thread);
                } else if (isMainThread && k != null) {
                    a(i2, str, null, null, b.a.THREAD, thread);
                } else {
                    int a2 = a(i2);
                    String format = com.ss.android.agilelogger.utils.b.format(b.a.THREAD, thread);
                    if (f17928h != null && isMainThread) {
                        f17928h.write(a2, str, format);
                    } else {
                        com.bytedance.android.alog.c.a(a2, str, format);
                    }
                }
            }
        }
    }

    public static void stacktrace(int i2, String str, StackTraceElement[] stackTraceElementArr) {
        if (b(i2, str)) {
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(i2, str, null, null, b.a.STACKTRACE, stackTraceElementArr);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(i2, str, null, null, b.a.STACKTRACE, stackTraceElementArr);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(i2, str, null, null, b.a.STACKTRACE, stackTraceElementArr);
                } else if (isMainThread && k != null) {
                    a(i2, str, null, null, b.a.STACKTRACE, stackTraceElementArr);
                } else {
                    int a2 = a(i2);
                    String format = com.ss.android.agilelogger.utils.b.format(b.a.STACKTRACE, stackTraceElementArr);
                    if (f17928h != null && isMainThread) {
                        f17928h.write(a2, str, format);
                    } else {
                        com.bytedance.android.alog.c.a(a2, str, format);
                    }
                }
            }
        }
    }

    public static void statcktrace(int i2, String str, StackTraceElement[] stackTraceElementArr) {
        stacktrace(i2, str, stackTraceElementArr);
    }

    public static void println(int i2, String str, Object obj, b.a aVar) {
        String str2;
        if (b(i2, str)) {
            int a2 = a(i2);
            switch (AnonymousClass12.f17939a[aVar.ordinal()]) {
                case 1:
                    str2 = (String) obj;
                    break;
                case 2:
                    str2 = com.ss.android.agilelogger.utils.e.getStackTraceString((Throwable) obj);
                    break;
                case 3:
                    str2 = com.ss.android.agilelogger.utils.b.format(b.a.BORDER, (String) obj);
                    break;
                case 4:
                    str2 = com.ss.android.agilelogger.utils.b.format(b.a.JSON, (String) obj);
                    break;
                case 5:
                    str2 = com.ss.android.agilelogger.utils.b.format(b.a.BUNDLE, (Bundle) obj);
                    break;
                case 6:
                    str2 = com.ss.android.agilelogger.utils.b.format(b.a.INTENT, (Intent) obj);
                    break;
                case 7:
                    str2 = com.ss.android.agilelogger.utils.b.format(b.a.THROWABLE, (Throwable) obj);
                    break;
                case 8:
                    str2 = com.ss.android.agilelogger.utils.b.format(b.a.THREAD, (Thread) obj);
                    break;
                case 9:
                    str2 = com.ss.android.agilelogger.utils.b.format(b.a.STACKTRACE, (StackTraceElement[]) obj);
                    break;
                default:
                    str2 = "";
                    break;
            }
            if (sConfig != null && sConfig.c() && sSingleThreadExecutor != null) {
                postAsyncLogByThreadPool(i2, str, str2);
            } else if (sConfig != null && sConfig.c() && k != null) {
                a(i2, str, str2);
            } else {
                boolean isMainThread = h.isMainThread();
                if (isMainThread && sSingleThreadExecutor != null) {
                    postAsyncLogByThreadPool(i2, str, str2);
                } else if (isMainThread && k != null) {
                    a(i2, str, str2);
                } else if (f17928h != null && isMainThread) {
                    f17928h.write(a2, str, str2);
                } else {
                    com.bytedance.android.alog.c.a(a2, str, str2);
                }
            }
        }
    }

    public static void asyncFlush() {
        Alog alog;
        if (sSingleThreadExecutor != null) {
            sSingleThreadExecutor.execute(new Runnable() { // from class: com.ss.android.agilelogger.a.14
                @Override // java.lang.Runnable
                public void run() {
                    com.bytedance.android.alog.c.asyncFlush();
                }
            });
        } else if (k != null) {
            k.sendEmptyMessage(2);
        }
        com.bytedance.android.alog.c.asyncFlush();
        if (f17928h != null) {
            f17928h.asyncFlush();
        }
        Iterator<WeakReference<Alog>> it = f17929i.iterator();
        while (it.hasNext()) {
            WeakReference<Alog> next = it.next();
            if (next != null && (alog = next.get()) != null) {
                alog.asyncFlush();
            }
        }
    }

    public static void syncFlush() {
        if (sSingleThreadExecutor != null) {
            sSingleThreadExecutor.execute(new Runnable() { // from class: com.ss.android.agilelogger.a.8
                @Override // java.lang.Runnable
                public void run() {
                    com.bytedance.android.alog.c.asyncFlush();
                }
            });
        }
        if (k != null) {
            k.sendEmptyMessage(2);
        }
        com.bytedance.android.alog.c.syncFlush();
        if (f17928h != null) {
            f17928h.syncFlush();
        }
        Iterator<WeakReference<Alog>> it = f17929i.iterator();
        while (it.hasNext()) {
            Alog alog = it.next().get();
            if (alog != null) {
                alog.syncFlush();
            }
        }
    }

    public static void timedSyncFlush(int i2) {
        if (sSingleThreadExecutor != null) {
            sSingleThreadExecutor.execute(new Runnable() { // from class: com.ss.android.agilelogger.a.9
                @Override // java.lang.Runnable
                public void run() {
                    com.bytedance.android.alog.c.asyncFlush();
                }
            });
        }
        if (k != null) {
            k.sendEmptyMessage(2);
        }
        com.bytedance.android.alog.c.timedSyncFlush(i2);
        if (f17928h != null) {
            f17928h.timedSyncFlush(i2);
        }
        Iterator<WeakReference<Alog>> it = f17929i.iterator();
        while (it.hasNext()) {
            Alog alog = it.next().get();
            if (alog != null) {
                alog.timedSyncFlush(i2);
            }
        }
    }

    public static void setOuterExecutorService(ScheduledExecutorService scheduledExecutorService) {
        f17927g = scheduledExecutorService;
    }

    public static void changeLevel(int i2) {
        f17921a = i2;
        com.bytedance.android.alog.c.setLevel(a(i2));
        if (f17928h != null) {
            f17928h.setLevel(a(i2));
        }
    }

    public static List<String> getALogFiles(long j2, long j3) {
        ArrayList arrayList = new ArrayList();
        try {
            File[] logs = com.bytedance.android.alog.c.getLogs(null, null, j2 * 1000, j3 * 1000);
            for (File file : logs) {
                arrayList.add(file.getAbsolutePath());
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public static HashMap<String, String> getLastFetchErrorInfo() {
        return com.bytedance.android.alog.c.getLastFetchErrorInfo();
    }

    public static String getStatus() {
        try {
            return com.bytedance.android.alog.c.getStatus();
        } catch (Exception unused) {
            return "getStatus exception";
        }
    }

    public static List<String> getALogFiles(String str, String str2, long j2, long j3) {
        ArrayList arrayList = new ArrayList();
        try {
            File[] logs = com.bytedance.android.alog.c.getLogs(str, str2, j2 * 1000, j3 * 1000);
            for (File file : logs) {
                arrayList.add(file.getAbsolutePath());
            }
        } catch (Exception unused) {
        }
        return arrayList;
    }

    public static void destroy() {
        com.bytedance.android.alog.c.close();
        if (f17928h != null) {
            f17928h.close();
        }
        if (k != null) {
            f17930j.quit();
            f17930j = null;
            k = null;
        }
        if (sSingleThreadExecutor != null) {
            sSingleThreadExecutor.shutdown();
            sSingleThreadExecutor = null;
        }
    }

    public static void release() {
        com.bytedance.android.alog.c.close();
        if (f17928h != null) {
            f17928h.close();
        }
        if (k != null) {
            f17930j.quit();
            f17930j = null;
            k = null;
        }
        if (sSingleThreadExecutor != null) {
            sSingleThreadExecutor.shutdown();
            sSingleThreadExecutor = null;
        }
    }

    @Deprecated
    public static void flush() {
        if (sSingleThreadExecutor != null) {
            sSingleThreadExecutor.execute(new Runnable() { // from class: com.ss.android.agilelogger.a.10
                @Override // java.lang.Runnable
                public void run() {
                    com.bytedance.android.alog.c.asyncFlush();
                }
            });
        }
        if (k != null) {
            k.sendEmptyMessage(2);
        }
        com.bytedance.android.alog.c.asyncFlush();
        if (f17928h != null) {
            f17928h.asyncFlush();
        }
    }

    public static long getALogWriteFuncAddr() {
        return com.bytedance.android.alog.c.getLegacyWriteFuncAddr();
    }

    public static long getAlogNativeFlushV2FuncAddr() {
        return com.bytedance.android.alog.c.getLegacyFlushFuncAddr();
    }

    public static long getAlogNativeLogStoreDirFuncAddr() {
        return com.bytedance.android.alog.c.getLegacyGetLogFileDirFuncAddr();
    }

    public static long getALogSimpleWriteFuncAddr() {
        return com.bytedance.android.alog.c.getNativeWriteFuncAddr();
    }

    public static long getALogSimpleWriteAsyncFuncAddr() {
        return com.bytedance.android.alog.c.a();
    }

    public static long getALogSimpleInitFuncAddr() {
        return com.bytedance.android.alog.c.b();
    }

    public static void writeAsyncLog(int i2, String str, String str2, long j2, long j3) {
        com.bytedance.android.alog.c.a(a(i2), str, str2, j2, j3);
    }

    private static void postAsyncLogByThreadPool(int i2, String str, String str2) {
        postAsyncLogByThreadPool(i2, str, str2, null, null, null);
    }

    private static void postAsyncLogByThreadPool(final int i2, final String str, final String str2, final Throwable th, final b.a aVar, final Object obj) {
        final long threadId = getThreadId();
        final long currentTimeMillis = System.currentTimeMillis();
        sSingleThreadExecutor.execute(new Runnable() { // from class: com.ss.android.agilelogger.a.11
            @Override // java.lang.Runnable
            public void run() {
                String str3;
                String format;
                int a2 = a.a(i2);
                b.a aVar2 = aVar;
                if (aVar2 == null) {
                    if (th == null) {
                        format = str2;
                    } else {
                        format = (str2 != null ? str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE : "") + com.ss.android.agilelogger.utils.e.getStackTraceString(th);
                    }
                } else if (aVar2 == b.a.BORDER) {
                    format = com.ss.android.agilelogger.utils.b.format(b.a.BORDER, str2);
                } else if (aVar == b.a.JSON) {
                    format = com.ss.android.agilelogger.utils.b.format(b.a.JSON, str2);
                } else if (aVar == b.a.BUNDLE) {
                    format = com.ss.android.agilelogger.utils.b.format(b.a.BUNDLE, (Bundle) obj);
                } else if (aVar == b.a.INTENT) {
                    format = com.ss.android.agilelogger.utils.b.format(b.a.INTENT, (Intent) obj);
                } else if (aVar == b.a.THROWABLE) {
                    format = com.ss.android.agilelogger.utils.b.format(b.a.THROWABLE, (Throwable) obj);
                } else if (aVar == b.a.THREAD) {
                    format = com.ss.android.agilelogger.utils.b.format(b.a.THREAD, (Thread) obj);
                } else if (aVar == b.a.STACKTRACE) {
                    format = com.ss.android.agilelogger.utils.b.format(b.a.STACKTRACE, (StackTraceElement[]) obj);
                } else {
                    str3 = "";
                    com.bytedance.android.alog.c.a(a2, str, str3, threadId, currentTimeMillis);
                }
                str3 = format;
                com.bytedance.android.alog.c.a(a2, str, str3, threadId, currentTimeMillis);
            }
        });
    }

    private static void a(int i2, String str, String str2) {
        a(i2, str, str2, null, null, null);
    }

    private static void a(int i2, String str, String str2, Throwable th, b.a aVar, Object obj) {
        C0325a obtain = C0325a.obtain();
        obtain.level = i2;
        obtain.tag = str;
        obtain.msg = str2;
        obtain.throwable = th;
        obtain.formatType = aVar;
        obtain.infoObject = obj;
        obtain.tid = getThreadId();
        obtain.timestampMs = System.currentTimeMillis();
        Message obtain2 = Message.obtain();
        obtain2.what = 1;
        obtain2.obj = obtain;
        k.sendMessage(obtain2);
    }

    private static long getThreadId() {
        return sThreadId.get().longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(C0325a c0325a) {
        String str;
        String format;
        int a2 = a(c0325a.level);
        if (c0325a.formatType == null) {
            if (c0325a.throwable == null) {
                format = c0325a.msg;
            } else {
                format = (c0325a.msg != null ? c0325a.msg + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE : "") + com.ss.android.agilelogger.utils.e.getStackTraceString(c0325a.throwable);
            }
        } else if (c0325a.formatType == b.a.BORDER) {
            format = com.ss.android.agilelogger.utils.b.format(b.a.BORDER, c0325a.msg);
        } else if (c0325a.formatType == b.a.JSON) {
            format = com.ss.android.agilelogger.utils.b.format(b.a.JSON, c0325a.msg);
        } else if (c0325a.formatType == b.a.BUNDLE) {
            format = com.ss.android.agilelogger.utils.b.format(b.a.BUNDLE, (Bundle) c0325a.infoObject);
        } else if (c0325a.formatType == b.a.INTENT) {
            format = com.ss.android.agilelogger.utils.b.format(b.a.INTENT, (Intent) c0325a.infoObject);
        } else if (c0325a.formatType == b.a.THROWABLE) {
            format = com.ss.android.agilelogger.utils.b.format(b.a.THROWABLE, (Throwable) c0325a.infoObject);
        } else if (c0325a.formatType == b.a.THREAD) {
            format = com.ss.android.agilelogger.utils.b.format(b.a.THREAD, (Thread) c0325a.infoObject);
        } else if (c0325a.formatType == b.a.STACKTRACE) {
            format = com.ss.android.agilelogger.utils.b.format(b.a.STACKTRACE, (StackTraceElement[]) c0325a.infoObject);
        } else {
            str = "";
            com.bytedance.android.alog.c.a(a2, c0325a.tag, str, c0325a.tid, c0325a.timestampMs);
            c0325a.recycle();
        }
        str = format;
        com.bytedance.android.alog.c.a(a2, c0325a.tag, str, c0325a.tid, c0325a.timestampMs);
        c0325a.recycle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ALog.java */
    /* renamed from: com.ss.android.agilelogger.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0325a {

        /* renamed from: a  reason: collision with root package name */
        private static final Object f17947a = new Object();

        /* renamed from: b  reason: collision with root package name */
        private static C0325a f17948b = null;

        /* renamed from: c  reason: collision with root package name */
        private static int f17949c = 0;

        /* renamed from: d  reason: collision with root package name */
        private static final int f17950d = 50;
        public b.a formatType = null;
        public Object infoObject;
        public int level;
        public String msg;
        public C0325a next;
        public String tag;
        public Throwable throwable;
        public long tid;
        public long timestampMs;

        private C0325a() {
        }

        public static C0325a obtain() {
            synchronized (f17947a) {
                C0325a c0325a = f17948b;
                if (c0325a != null) {
                    f17948b = c0325a.next;
                    c0325a.next = null;
                    f17949c--;
                    return c0325a;
                }
                return new C0325a();
            }
        }

        public void recycle() {
            this.tag = null;
            this.msg = null;
            this.throwable = null;
            this.formatType = null;
            this.infoObject = null;
            this.tid = -1L;
            this.timestampMs = 0L;
            this.next = null;
            synchronized (f17947a) {
                int i2 = f17949c;
                if (i2 < 50) {
                    this.next = f17948b;
                    f17948b = this;
                    f17949c = i2 + 1;
                }
            }
        }
    }

    public static b createInstance(String str, Context context) {
        if (context == null) {
            return null;
        }
        return createInstance(str, new b.a(context).build());
    }

    public static b createInstance(String str, com.ss.android.agilelogger.b bVar) {
        if (bVar == null) {
            return null;
        }
        if (!f17924d) {
            try {
                Alog.init(new e());
            } catch (Throwable unused) {
                return null;
            }
        }
        Alog build = new Alog.b(bVar.getContext()).setInstanceName(str).setLevel(a(bVar.getLevel())).setSyslog(f17922b).setLogFileDir(sConfig != null ? sConfig.getLogDirPath() : bVar.getLogDirPath()).setLogFileSizeEach(bVar.getPerSize()).setLogFileSizeTotal(bVar.getMaxDirSize()).setLogFileExpDays(bVar.getLogFileExpDays()).setCacheFileDir(sConfig != null ? sConfig.getBufferDirPath() : bVar.getBufferDirPath()).setCacheFileSizeEach(65536).setCacheFileSizeTotal(196608).setMode(Alog.d.SAFE).setTimeFormat(Alog.g.RAW).setPrefixFormat(Alog.e.LEGACY).setCompress(bVar.isCompress() ? Alog.c.ZSTD : Alog.c.NONE).setSymCrypt(bVar.isEncrypt() ? Alog.f.TEA_16 : Alog.f.NONE).setAsymCrypt(bVar.isEncrypt() ? Alog.a.EC_SECP256K1 : Alog.a.NONE).setServerPublicKey(bVar.getPubKey()).a(sConfig != null ? sConfig.d() : false).build();
        if (build == null) {
            return null;
        }
        f17929i.add(new WeakReference<>(build));
        return new b(build, str);
    }

    /* compiled from: ALog.java */
    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: b  reason: collision with root package name */
        private static ExecutorService f17951b;

        /* renamed from: a  reason: collision with root package name */
        private final Alog f17952a;

        public b(Alog alog, final String str) {
            this.f17952a = alog;
            f17951b = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.ss.android.agilelogger.a.b.1
                @Override // java.util.concurrent.ThreadFactory
                public Thread newThread(Runnable runnable) {
                    Thread thread = new Thread(runnable);
                    thread.setName("Alog_instance-" + str);
                    return thread;
                }
            });
        }

        public void a() {
            f17951b.shutdown();
        }

        public void v(final String str, final String str2) {
            final long access$500 = a.access$500();
            final long currentTimeMillis = System.currentTimeMillis();
            if (a.b(2, str)) {
                f17951b.submit(new Runnable() { // from class: com.ss.android.agilelogger.a.b.2
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.f17952a.writeAsyncMsg(a.a(2), str, str2, access$500, currentTimeMillis);
                    }
                });
            }
        }

        public void d(final String str, final String str2) {
            final long access$500 = a.access$500();
            final long currentTimeMillis = System.currentTimeMillis();
            if (a.b(3, str)) {
                f17951b.submit(new Runnable() { // from class: com.ss.android.agilelogger.a.b.3
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.f17952a.writeAsyncMsg(a.a(3), str, str2, access$500, currentTimeMillis);
                    }
                });
            }
        }

        public void i(final String str, final String str2) {
            final long access$500 = a.access$500();
            final long currentTimeMillis = System.currentTimeMillis();
            if (a.b(4, str)) {
                f17951b.submit(new Runnable() { // from class: com.ss.android.agilelogger.a.b.4
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.f17952a.writeAsyncMsg(a.a(4), str, str2, access$500, currentTimeMillis);
                    }
                });
            }
        }

        public void w(final String str, final String str2) {
            final long access$500 = a.access$500();
            final long currentTimeMillis = System.currentTimeMillis();
            if (a.b(5, str)) {
                f17951b.submit(new Runnable() { // from class: com.ss.android.agilelogger.a.b.5
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.f17952a.writeAsyncMsg(a.a(5), str, str2, access$500, currentTimeMillis);
                    }
                });
            }
        }

        public void e(final String str, final String str2) {
            final long access$500 = a.access$500();
            final long currentTimeMillis = System.currentTimeMillis();
            if (a.b(6, str)) {
                f17951b.submit(new Runnable() { // from class: com.ss.android.agilelogger.a.b.6
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.f17952a.writeAsyncMsg(a.a(6), str, str2, access$500, currentTimeMillis);
                    }
                });
            }
        }

        public long getNativeRef() {
            return this.f17952a.getNativeRef();
        }

        public List<String> getFiles(long j2, long j3) {
            ArrayList arrayList = new ArrayList();
            try {
                File[] logs = this.f17952a.getLogs(j2, j3);
                for (File file : logs) {
                    arrayList.add(file.getAbsolutePath());
                }
            } catch (Exception unused) {
            }
            return arrayList;
        }

        public List<String> getFilesOfAllProcesses(long j2, long j3) {
            ArrayList arrayList = new ArrayList();
            try {
                File[] logs = this.f17952a.getLogs(null, j2, j3);
                for (File file : logs) {
                    arrayList.add(file.getAbsolutePath());
                }
            } catch (Exception unused) {
            }
            return arrayList;
        }

        public void asyncFlush() {
            this.f17952a.asyncFlush();
        }

        public void syncFlush() {
            this.f17952a.syncFlush();
        }

        public void timedSyncFlush(int i2) {
            this.f17952a.timedSyncFlush(i2);
        }
    }

    public static void removeObsoleteInstance(String str, Context context, boolean z) {
        String bufferDirPath;
        String str2;
        File[] listFiles;
        File[] listFiles2;
        String processName = h.getProcessName();
        if (processName == null || processName.contains(CertificateUtil.DELIMITER)) {
            return;
        }
        if (!z) {
            processName = processName + '-';
        }
        if (sConfig != null) {
            str2 = sConfig.getLogDirPath();
            bufferDirPath = sConfig.getBufferDirPath();
        } else {
            String absolutePath = com.ss.android.agilelogger.utils.a.getDefaultLogDir(context).getAbsolutePath();
            bufferDirPath = com.ss.android.agilelogger.utils.a.getBufferDirPath(context);
            str2 = absolutePath;
        }
        File file = new File(str2);
        if (file.exists() && file.isDirectory()) {
            String str3 = "__" + str + ".alog.hot";
            for (File file2 : file.listFiles()) {
                String name = file2.getName();
                if (name != null && name.endsWith(str3) && name.contains(processName)) {
                    file2.delete();
                }
            }
            File file3 = new File(bufferDirPath);
            if (file3.exists() && file3.isDirectory()) {
                String str4 = "__" + str;
                for (File file4 : file3.listFiles()) {
                    String name2 = file4.getName();
                    if (name2 != null && name2.contains(str4) && name2.contains(processName)) {
                        file4.delete();
                    }
                }
            }
        }
    }

    public static void addMessageInterceptor(com.bytedance.android.alog.e eVar) {
        Alog.a(eVar);
    }

    public static void removeMessageInterceptor(com.bytedance.android.alog.e eVar) {
        Alog.b(eVar);
    }
}
