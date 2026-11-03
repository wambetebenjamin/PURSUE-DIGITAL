package com.bytedance.frameworks.baselib.network;

import com.bytedance.common.utility.Logger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONObject;
/* compiled from: TTDelayStateManager.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7426a = "c";

    /* renamed from: b  reason: collision with root package name */
    private static volatile a f7427b = a.NormalStart;

    /* renamed from: c  reason: collision with root package name */
    private static final AtomicBoolean f7428c = new AtomicBoolean(false);

    /* renamed from: d  reason: collision with root package name */
    private static final AtomicLong f7429d = new AtomicLong(0);

    /* renamed from: e  reason: collision with root package name */
    private static final AtomicLong f7430e = new AtomicLong(0);

    /* renamed from: f  reason: collision with root package name */
    private static final AtomicLong f7431f = new AtomicLong(0);

    /* renamed from: g  reason: collision with root package name */
    private static final AtomicLong f7432g = new AtomicLong(0);

    /* compiled from: TTDelayStateManager.java */
    /* loaded from: classes2.dex */
    public enum a {
        NormalStart(-1),
        ColdStart(0),
        HotStart(1),
        WarmStart(2);
        

        /* renamed from: e  reason: collision with root package name */
        final int f7439e;

        a(int i2) {
            this.f7439e = i2;
        }

        public int a() {
            return this.f7439e;
        }
    }

    public static a a() {
        if (Logger.debug()) {
            Logger.d(f7426a, "get coldDuration hostDuration warmDuration " + f7430e.get() + f7431f.get() + f7432g.get());
        }
        int i2 = AnonymousClass1.f7433a[f7427b.ordinal()];
        if (i2 == 1) {
            if (a(f7429d.get(), f7430e.get())) {
                f7427b = a.NormalStart;
            }
            return f7427b;
        } else if (i2 == 2) {
            if (a(f7429d.get(), f7431f.get())) {
                f7427b = a.NormalStart;
            }
            return f7427b;
        } else if (i2 == 3) {
            if (a(f7429d.get(), f7432g.get())) {
                f7427b = a.NormalStart;
            }
            return f7427b;
        } else {
            return f7427b;
        }
    }

    /* compiled from: TTDelayStateManager.java */
    /* renamed from: com.bytedance.frameworks.baselib.network.c$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f7433a;

        static {
            int[] iArr = new int[a.values().length];
            f7433a = iArr;
            try {
                iArr[a.ColdStart.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7433a[a.HotStart.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7433a[a.WarmStart.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static a b() {
        return f7427b;
    }

    public static boolean c() {
        return f7428c.get();
    }

    private static boolean a(long j2, long j3) {
        return (System.currentTimeMillis() / 1000) - j2 > j3;
    }

    public static void a(JSONObject jSONObject) {
        f7428c.set(jSONObject.optInt("request_tag_enabled") > 0);
        AtomicLong atomicLong = f7430e;
        atomicLong.set(jSONObject.optInt("cold_start_seconds"));
        AtomicLong atomicLong2 = f7431f;
        atomicLong2.set(jSONObject.optInt("hot_start_seconds"));
        AtomicLong atomicLong3 = f7432g;
        atomicLong3.set(jSONObject.optInt("warm_start_seconds"));
        if (Logger.debug()) {
            Logger.d(f7426a, "set coldDuration hostDuration warmDuration " + atomicLong.get() + atomicLong2.get() + atomicLong3.get());
        }
    }

    public static void a(int i2) {
        f7429d.set(System.currentTimeMillis() / 1000);
        if (i2 == 0) {
            f7427b = a.ColdStart;
        } else if (i2 == 1) {
            f7427b = a.HotStart;
        } else if (i2 == 2) {
            f7427b = a.WarmStart;
        } else {
            f7427b = a.NormalStart;
        }
    }
}
