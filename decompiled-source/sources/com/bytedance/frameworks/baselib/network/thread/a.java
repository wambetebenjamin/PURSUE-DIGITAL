package com.bytedance.frameworks.baselib.network.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.bytedance.librarian.c;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* compiled from: PlatformHandlerThread.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8501a = "PlatformHandlerThread";

    /* renamed from: b  reason: collision with root package name */
    private static final String f8502b = "common_handler_thread";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8503c = "back_handler_thread";

    /* renamed from: d  reason: collision with root package name */
    private static volatile HandlerThread f8504d = null;

    /* renamed from: e  reason: collision with root package name */
    private static volatile HandlerThread f8505e = null;

    /* renamed from: f  reason: collision with root package name */
    private static volatile Handler f8506f = null;

    /* renamed from: g  reason: collision with root package name */
    private static volatile Handler f8507g = null;

    /* renamed from: h  reason: collision with root package name */
    private static volatile Handler f8508h = new Handler(Looper.getMainLooper());

    /* renamed from: i  reason: collision with root package name */
    private static HashMap<String, HandlerThread> f8509i = new HashMap<>();

    /* renamed from: j  reason: collision with root package name */
    private static List<String> f8510j = Arrays.asList("NpthHandlerThread", "NewVideo-HandlerThread", "trace_time_update_thread");
    private static List<String> k = Arrays.asList("DBHelper-AsyncOp-New");
    private static Map<String, String> l = new HashMap();
    private static final String m = "\u200b";

    public static Handler a() {
        return f8508h;
    }

    public static Handler b() {
        Handler handler;
        synchronized (a.class) {
            if (f8506f == null) {
                c();
            }
            handler = f8506f;
        }
        return handler;
    }

    public static HandlerThread c() {
        HandlerThread handlerThread;
        synchronized (a.class) {
            if (f8504d == null) {
                f8504d = new HandlerThreadC0119a(f8502b);
                f8504d.start();
                f8506f = new Handler(f8504d.getLooper());
            }
            handlerThread = f8504d;
        }
        return handlerThread;
    }

    public static Handler d() {
        Handler handler;
        synchronized (a.class) {
            if (f8507g == null) {
                e();
            }
            handler = f8507g;
        }
        return handler;
    }

    public static HandlerThread e() {
        HandlerThread handlerThread;
        synchronized (a.class) {
            if (f8505e == null) {
                f8505e = new HandlerThreadC0119a(f8503c, 10);
                f8505e.start();
                f8507g = new Handler(f8505e.getLooper());
            }
            handlerThread = f8505e;
        }
        return handlerThread;
    }

    public static HandlerThread a(String str) {
        return a(str, 0, "");
    }

    public static HandlerThread a(String str, String str2) {
        return a(str, 0, str2);
    }

    public static HandlerThread a(String str, int i2, String str2) {
        Iterator<Map.Entry<String, HandlerThread>> it = f8509i.entrySet().iterator();
        while (it.hasNext()) {
            if (!it.next().getValue().isAlive()) {
                it.remove();
            }
        }
        HandlerThread handlerThread = f8509i.get(str);
        if (handlerThread == null) {
            HandlerThreadC0119a handlerThreadC0119a = new HandlerThreadC0119a(str, i2);
            handlerThreadC0119a.start();
            f8509i.put(str, handlerThreadC0119a);
            return handlerThreadC0119a;
        }
        return handlerThread;
    }

    private static String b(String str, String str2) {
        String[] split;
        if (TextUtils.isEmpty(str)) {
            return "platform_handler";
        }
        if (str.split(c.a.SEPARATOR).length >= 4) {
            String str3 = split[0] + split[1] + split[2] + split[3];
            String str4 = l.get(str3);
            if (TextUtils.isEmpty(str4)) {
                l.put(str3, str2);
                return str2;
            }
            return str4;
        }
        return "platform_handler";
    }

    /* compiled from: PlatformHandlerThread.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.thread.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class HandlerThreadC0119a extends HandlerThread {

        /* renamed from: a  reason: collision with root package name */
        private volatile boolean f8511a;

        public HandlerThreadC0119a(String str) {
            super(str);
            this.f8511a = false;
        }

        public HandlerThreadC0119a(String str, int i2) {
            super(str, i2);
            this.f8511a = false;
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            if (this.f8511a) {
                return;
            }
            this.f8511a = true;
            super.start();
        }
    }
}
