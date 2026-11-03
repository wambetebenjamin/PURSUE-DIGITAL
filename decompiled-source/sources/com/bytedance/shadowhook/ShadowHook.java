package com.bytedance.shadowhook;

import com.facebook.internal.AnalyticsEvents;
/* loaded from: classes3.dex */
public final class ShadowHook {
    private static final int ERRNO_INIT_EXCEPTION = 101;
    private static final int ERRNO_LOAD_LIBRARY_EXCEPTION = 100;
    private static final int ERRNO_OK = 0;
    private static final int ERRNO_PENDING = 1;
    private static final int ERRNO_UNINIT = 2;
    private static final boolean defaultDebuggable = false;
    private static final c defaultLibLoader = null;
    private static final int defaultMode = d.SHARED.a();
    private static final boolean defaultRecordable = false;
    private static long initCostMs = -1;
    private static int initErrno = 2;
    private static boolean inited = false;
    private static final String libName = "shadowhook";
    private static final int recordItemAll = 1023;
    private static final int recordItemBackupLen = 128;
    private static final int recordItemCallerLibName = 2;
    private static final int recordItemErrno = 256;
    private static final int recordItemLibName = 8;
    private static final int recordItemNewAddr = 64;
    private static final int recordItemOp = 4;
    private static final int recordItemStub = 512;
    private static final int recordItemSymAddr = 32;
    private static final int recordItemSymName = 16;
    private static final int recordItemTimestamp = 1;

    /* loaded from: classes3.dex */
    public interface c {
        void a(String str);
    }

    /* loaded from: classes3.dex */
    public enum e {
        TIMESTAMP,
        CALLER_LIB_NAME,
        OP,
        LIB_NAME,
        SYM_NAME,
        SYM_ADDR,
        NEW_ADDR,
        BACKUP_LEN,
        ERRNO,
        STUB
    }

    private static native String nativeGetArch();

    private static native boolean nativeGetDebuggable();

    private static native int nativeGetInitErrno();

    private static native int nativeGetMode();

    private static native boolean nativeGetRecordable();

    private static native String nativeGetRecords(int i2);

    private static native String nativeGetVersion();

    private static native int nativeInit(int i2, boolean z);

    private static native void nativeSetDebuggable(boolean z);

    private static native void nativeSetRecordable(boolean z);

    private static native String nativeToErrmsg(int i2);

    public static String getVersion() {
        return nativeGetVersion();
    }

    public static int init() {
        return init(null);
    }

    public static synchronized int init(a aVar) {
        synchronized (ShadowHook.class) {
            if (inited) {
                return initErrno;
            }
            inited = true;
            long currentTimeMillis = System.currentTimeMillis();
            if (aVar == null) {
                aVar = new b().a();
            }
            if (!loadLibrary(aVar)) {
                initErrno = 100;
                initCostMs = System.currentTimeMillis() - currentTimeMillis;
                return initErrno;
            }
            initErrno = nativeInit(aVar.b(), aVar.c());
            if (aVar.d()) {
                nativeSetRecordable(aVar.d());
            }
            initCostMs = System.currentTimeMillis() - currentTimeMillis;
            return initErrno;
        }
    }

    public static int getInitErrno() {
        return initErrno;
    }

    public static long getInitCostMs() {
        return initCostMs;
    }

    public static d getMode() {
        if (isInitedOk()) {
            return d.SHARED.a() == nativeGetMode() ? d.SHARED : d.UNIQUE;
        }
        return d.SHARED;
    }

    public static boolean getDebuggable() {
        if (isInitedOk()) {
            return nativeGetDebuggable();
        }
        return false;
    }

    public static void setDebuggable(boolean z) {
        if (isInitedOk()) {
            nativeSetDebuggable(z);
        }
    }

    public static boolean getRecordable() {
        if (isInitedOk()) {
            return nativeGetRecordable();
        }
        return false;
    }

    public static void setRecordable(boolean z) {
        if (isInitedOk()) {
            nativeSetRecordable(z);
        }
    }

    public static String toErrmsg(int i2) {
        return i2 == 0 ? "OK" : i2 == 1 ? "Pending task" : i2 == 2 ? "Not initialized" : i2 == 100 ? "Load libshadowhook.so failed" : i2 == 101 ? "Init exception" : isInitedOk() ? nativeToErrmsg(i2) : AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
    }

    public static String getRecords(e... eVarArr) {
        if (isInitedOk()) {
            int i2 = 0;
            for (e eVar : eVarArr) {
                switch (AnonymousClass1.f13295a[eVar.ordinal()]) {
                    case 1:
                        i2 |= 1;
                        break;
                    case 2:
                        i2 |= 2;
                        break;
                    case 3:
                        i2 |= 4;
                        break;
                    case 4:
                        i2 |= 8;
                        break;
                    case 5:
                        i2 |= 16;
                        break;
                    case 6:
                        i2 |= 32;
                        break;
                    case 7:
                        i2 |= 64;
                        break;
                    case 8:
                        i2 |= 128;
                        break;
                    case 9:
                        i2 |= 256;
                        break;
                    case 10:
                        i2 |= 512;
                        break;
                }
            }
            if (i2 == 0) {
                i2 = 1023;
            }
            return nativeGetRecords(i2);
        }
        return null;
    }

    /* renamed from: com.bytedance.shadowhook.ShadowHook$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13295a;

        static {
            int[] iArr = new int[e.values().length];
            f13295a = iArr;
            try {
                iArr[e.TIMESTAMP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13295a[e.CALLER_LIB_NAME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13295a[e.OP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13295a[e.LIB_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f13295a[e.SYM_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f13295a[e.SYM_ADDR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f13295a[e.NEW_ADDR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f13295a[e.BACKUP_LEN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f13295a[e.ERRNO.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f13295a[e.STUB.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public static String getArch() {
        return isInitedOk() ? nativeGetArch() : "unknown";
    }

    private static boolean loadLibrary(a aVar) {
        if (aVar != null) {
            try {
                if (aVar.a() != null) {
                    aVar.a().a(libName);
                    return true;
                }
            } catch (Throwable unused) {
                return false;
            }
        }
        System.loadLibrary(libName);
        return true;
    }

    private static boolean loadLibrary() {
        return loadLibrary(null);
    }

    private static boolean isInitedOk() {
        if (inited) {
            return initErrno == 0;
        } else if (loadLibrary()) {
            try {
                int nativeGetInitErrno = nativeGetInitErrno();
                if (nativeGetInitErrno != 2) {
                    initErrno = nativeGetInitErrno;
                    inited = true;
                }
                return nativeGetInitErrno == 0;
            } catch (Throwable unused) {
                return false;
            }
        } else {
            return false;
        }
    }

    /* loaded from: classes3.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private c f13296a;

        /* renamed from: b  reason: collision with root package name */
        private int f13297b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f13298c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f13299d;

        public void a(c cVar) {
            this.f13296a = cVar;
        }

        public c a() {
            return this.f13296a;
        }

        public void a(int i2) {
            this.f13297b = i2;
        }

        public int b() {
            return this.f13297b;
        }

        public void a(boolean z) {
            this.f13298c = z;
        }

        public boolean c() {
            return this.f13298c;
        }

        public void b(boolean z) {
            this.f13299d = z;
        }

        public boolean d() {
            return this.f13299d;
        }
    }

    /* loaded from: classes3.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private c f13300a = ShadowHook.defaultLibLoader;

        /* renamed from: b  reason: collision with root package name */
        private int f13301b = ShadowHook.defaultMode;

        /* renamed from: c  reason: collision with root package name */
        private boolean f13302c = false;

        /* renamed from: d  reason: collision with root package name */
        private boolean f13303d = false;

        public b a(c cVar) {
            this.f13300a = cVar;
            return this;
        }

        public b a(d dVar) {
            this.f13301b = dVar.a();
            return this;
        }

        public b a(boolean z) {
            this.f13302c = z;
            return this;
        }

        public b b(boolean z) {
            this.f13303d = z;
            return this;
        }

        public a a() {
            a aVar = new a();
            aVar.a(this.f13300a);
            aVar.a(this.f13301b);
            aVar.a(this.f13302c);
            aVar.b(this.f13303d);
            return aVar;
        }
    }

    /* loaded from: classes3.dex */
    public enum d {
        SHARED(0),
        UNIQUE(1);
        

        /* renamed from: c  reason: collision with root package name */
        private final int f13307c;

        d(int i2) {
            this.f13307c = i2;
        }

        int a() {
            return this.f13307c;
        }
    }
}
