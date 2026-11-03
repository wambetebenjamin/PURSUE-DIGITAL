package com.bytedance.ttnet;

import android.os.Handler;
import android.os.Looper;
import com.bytedance.ttnet.TTWiFiCellSwitcher;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.json.JSONObject;
/* compiled from: TTWiFiCellSimpleSwitcher.java */
/* loaded from: classes8.dex */
public class o implements TTWiFiCellSwitcher.b {

    /* renamed from: a  reason: collision with root package name */
    private volatile int f15356a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<a, Handler> f15357b;

    /* compiled from: TTWiFiCellSimpleSwitcher.java */
    /* loaded from: classes8.dex */
    public interface a {
        void a(int i2, int i3);
    }

    /* compiled from: TTWiFiCellSimpleSwitcher.java */
    /* loaded from: classes8.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public static final int f15365a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f15366b = 1;
    }

    /* compiled from: TTWiFiCellSimpleSwitcher.java */
    /* loaded from: classes8.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public static final int f15367a = -1;

        /* renamed from: b  reason: collision with root package name */
        public static final int f15368b = 0;

        /* renamed from: c  reason: collision with root package name */
        public static final int f15369c = 1;

        /* renamed from: d  reason: collision with root package name */
        public static final int f15370d = 2;

        /* renamed from: e  reason: collision with root package name */
        public static final int f15371e = 3;
    }

    public void a(int i2, int i3, int i4, JSONObject jSONObject) {
    }

    public int a() {
        return this.f15356a;
    }

    /* compiled from: TTWiFiCellSimpleSwitcher.java */
    /* loaded from: classes8.dex */
    private static final class b {

        /* renamed from: a  reason: collision with root package name */
        static final o f15364a = new o();

        private b() {
        }
    }

    public static o b() {
        return b.f15364a;
    }

    private o() {
        this.f15356a = -1;
        this.f15357b = new HashMap();
        TTWiFiCellSwitcher.inst().registerObserver(this, null);
    }

    public void a(a aVar, Handler handler) {
        Objects.requireNonNull(aVar, "observer == null");
        synchronized (this) {
            if (!this.f15357b.containsKey(aVar)) {
                if (handler == null) {
                    this.f15357b.put(aVar, new Handler(Looper.getMainLooper()));
                } else {
                    this.f15357b.put(aVar, handler);
                }
            }
        }
    }

    public void a(a aVar) {
        Objects.requireNonNull(aVar, "observer == null");
        synchronized (this) {
            this.f15357b.remove(aVar);
        }
    }

    public void c() {
        TTWiFiCellSwitcher.inst().start();
    }

    public void d() {
        TTWiFiCellSwitcher.inst().stop();
    }

    public void e() {
        TTWiFiCellSwitcher.inst().start();
        TTWiFiCellSwitcher.inst().triggerSwitchingToNetwork(TTWiFiCellSwitcher.a.CELL);
    }

    public void f() {
        TTWiFiCellSwitcher.inst().start();
        TTWiFiCellSwitcher.inst().triggerSwitchingToNetwork(TTWiFiCellSwitcher.a.WIFI);
    }

    /* compiled from: TTWiFiCellSimpleSwitcher.java */
    /* renamed from: com.bytedance.ttnet.o$2  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f15363a;

        static {
            int[] iArr = new int[TTWiFiCellSwitcher.d.values().length];
            f15363a = iArr;
            try {
                iArr[TTWiFiCellSwitcher.d.DEFAULT_CELLULAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15363a[TTWiFiCellSwitcher.d.WIFI_WITH_CELLULAR_TRANS_DATA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15363a[TTWiFiCellSwitcher.d.DEFAULT_WIFI_WITH_CELLULAR_DOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15363a[TTWiFiCellSwitcher.d.DEFAULT_WIFI_WITH_CELLULAR_UP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15363a[TTWiFiCellSwitcher.d.WAIT_CELLULAR_ALWAYS_UP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15363a[TTWiFiCellSwitcher.d.WAIT_USER_ALLOW_TO_CELLULAR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15363a[TTWiFiCellSwitcher.d.WAIT_USER_ALLOW_TO_WIFI.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // com.bytedance.ttnet.TTWiFiCellSwitcher.b
    public void a(TTWiFiCellSwitcher.d dVar, TTWiFiCellSwitcher.d dVar2) {
        int i2;
        switch (AnonymousClass2.f15363a[dVar2.ordinal()]) {
            case 1:
            case 2:
                i2 = 2;
                break;
            case 3:
            case 4:
            case 5:
                i2 = 0;
                break;
            case 6:
                i2 = 1;
                break;
            case 7:
                i2 = 3;
                break;
            default:
                i2 = -1;
                break;
        }
        if (this.f15356a != i2) {
            synchronized (this) {
                for (Map.Entry<a, Handler> entry : this.f15357b.entrySet()) {
                    Handler value = entry.getValue();
                    Runnable runnable = new Runnable(i2, entry.getKey()) { // from class: com.bytedance.ttnet.o.1

                        /* renamed from: a  reason: collision with root package name */
                        final /* synthetic */ int f15358a;

                        /* renamed from: b  reason: collision with root package name */
                        final /* synthetic */ a f15359b;

                        /* renamed from: d  reason: collision with root package name */
                        private final int f15361d;

                        /* renamed from: e  reason: collision with root package name */
                        private final int f15362e;

                        {
                            this.f15358a = i2;
                            this.f15359b = r3;
                            this.f15361d = o.this.f15356a;
                            this.f15362e = i2;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            this.f15359b.a(this.f15361d, this.f15362e);
                        }
                    };
                    if (value != null) {
                        value.post(runnable);
                    }
                }
            }
            this.f15356a = i2;
        }
    }
}
