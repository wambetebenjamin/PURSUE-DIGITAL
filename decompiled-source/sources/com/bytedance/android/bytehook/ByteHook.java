package com.bytedance.android.bytehook;

import com.bytedance.shadowhook.ShadowHook;
/* loaded from: classes.dex */
public class ByteHook {
    private static final int ERRNO_INIT_EXCEPTION = 101;
    private static final int ERRNO_LOAD_LIBRARY_EXCEPTION = 100;
    private static final int ERRNO_OK = 0;
    private static final int ERRNO_UNINIT = 1;
    private static final boolean defaultDebug = false;
    private static final com.bytedance.android.bytehook.a defaultLibLoader = null;
    private static final boolean defaultRecordable = false;
    private static final boolean defaultShadowhookDebug = false;
    private static final boolean defaultShadowhookRecordable = false;
    private static long initCostMs = -1;
    private static int initStatus = 1;
    private static boolean inited = false;
    private static final String libName = "bytehook";
    private static final int recordItemAll = 255;
    private static final int recordItemCallerLibName = 2;
    private static final int recordItemErrno = 64;
    private static final int recordItemLibName = 8;
    private static final int recordItemNewAddr = 32;
    private static final int recordItemOp = 4;
    private static final int recordItemStub = 128;
    private static final int recordItemSymName = 16;
    private static final int recordItemTimestamp = 1;
    private static final int defaultMode = c.AUTOMATIC.a();
    private static final ShadowHook.c defaultShadowhookLibLoader = null;
    private static final ShadowHook.d defaultShadowhookMode = ShadowHook.d.SHARED;

    /* loaded from: classes.dex */
    public enum d {
        TIMESTAMP,
        CALLER_LIB_NAME,
        OP,
        LIB_NAME,
        SYM_NAME,
        NEW_ADDR,
        ERRNO,
        STUB
    }

    private static native int nativeAddIgnore(String str);

    private static native String nativeGetArch();

    private static native boolean nativeGetDebug();

    private static native int nativeGetMode();

    private static native boolean nativeGetRecordable();

    private static native String nativeGetRecords(int i2);

    private static native String nativeGetVersion();

    private static native int nativeInit(int i2, boolean z);

    private static native void nativeSetDebug(boolean z);

    private static native void nativeSetRecordable(boolean z);

    public static String getVersion() {
        return nativeGetVersion();
    }

    public static int init() {
        return init(null);
    }

    public static synchronized int init(a aVar) {
        synchronized (ByteHook.class) {
            if (inited) {
                return initStatus;
            }
            inited = true;
            long currentTimeMillis = System.currentTimeMillis();
            if (aVar == null) {
                aVar = new b().build();
            }
            ShadowHook.init(new ShadowHook.b().a(aVar.b()).a(aVar.c()).a(aVar.d()).b(aVar.e()).a());
            if (aVar.getLibLoader() == null) {
                System.loadLibrary(libName);
            } else {
                aVar.getLibLoader().loadLibrary(libName);
            }
            initStatus = nativeInit(aVar.getMode(), aVar.getDebug());
            if (aVar.a()) {
                nativeSetRecordable(aVar.a());
            }
            initCostMs = System.currentTimeMillis() - currentTimeMillis;
            return initStatus;
        }
    }

    public static int addIgnore(String str) {
        int i2 = initStatus;
        return i2 == 0 ? nativeAddIgnore(str) : i2;
    }

    public static int getInitErrno() {
        return initStatus;
    }

    public static long getInitCostMs() {
        return initCostMs;
    }

    public static c getMode() {
        if (initStatus == 0) {
            return c.AUTOMATIC.a() == nativeGetMode() ? c.AUTOMATIC : c.MANUAL;
        }
        return c.AUTOMATIC;
    }

    public static boolean getDebug() {
        if (initStatus == 0) {
            return nativeGetDebug();
        }
        return false;
    }

    public static void setDebug(boolean z) {
        if (initStatus == 0) {
            nativeSetDebug(z);
        }
    }

    public static boolean getRecordable() {
        if (initStatus == 0) {
            return nativeGetRecordable();
        }
        return false;
    }

    public static void setRecordable(boolean z) {
        if (initStatus == 0) {
            nativeSetRecordable(z);
        }
    }

    public static String getRecords(d... dVarArr) {
        if (initStatus == 0) {
            int i2 = 0;
            for (d dVar : dVarArr) {
                switch (AnonymousClass1.f192a[dVar.ordinal()]) {
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
                }
            }
            if (i2 == 0) {
                i2 = 255;
            }
            return nativeGetRecords(i2);
        }
        return null;
    }

    /* renamed from: com.bytedance.android.bytehook.ByteHook$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f192a;

        static {
            int[] iArr = new int[d.values().length];
            f192a = iArr;
            try {
                iArr[d.TIMESTAMP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f192a[d.CALLER_LIB_NAME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f192a[d.OP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f192a[d.LIB_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f192a[d.SYM_NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f192a[d.NEW_ADDR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f192a[d.ERRNO.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f192a[d.STUB.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static String getArch() {
        return initStatus == 0 ? nativeGetArch() : "unknown";
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private com.bytedance.android.bytehook.a f193a;

        /* renamed from: b  reason: collision with root package name */
        private int f194b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f195c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f196d;

        /* renamed from: e  reason: collision with root package name */
        private ShadowHook.c f197e;

        /* renamed from: f  reason: collision with root package name */
        private ShadowHook.d f198f;

        /* renamed from: g  reason: collision with root package name */
        private boolean f199g;

        /* renamed from: h  reason: collision with root package name */
        private boolean f200h;

        public void setLibLoader(com.bytedance.android.bytehook.a aVar) {
            this.f193a = aVar;
        }

        public com.bytedance.android.bytehook.a getLibLoader() {
            return this.f193a;
        }

        public void setMode(int i2) {
            this.f194b = i2;
        }

        public int getMode() {
            return this.f194b;
        }

        public void setDebug(boolean z) {
            this.f195c = z;
        }

        public boolean getDebug() {
            return this.f195c;
        }

        public void a(boolean z) {
            this.f196d = z;
        }

        public boolean a() {
            return this.f196d;
        }

        public void a(ShadowHook.c cVar) {
            this.f197e = cVar;
        }

        public ShadowHook.c b() {
            return this.f197e;
        }

        public void a(ShadowHook.d dVar) {
            this.f198f = dVar;
        }

        public ShadowHook.d c() {
            return this.f198f;
        }

        public void b(boolean z) {
            this.f199g = z;
        }

        public boolean d() {
            return this.f199g;
        }

        public void c(boolean z) {
            this.f200h = z;
        }

        public boolean e() {
            return this.f200h;
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private com.bytedance.android.bytehook.a f201a = ByteHook.defaultLibLoader;

        /* renamed from: b  reason: collision with root package name */
        private int f202b = ByteHook.defaultMode;

        /* renamed from: c  reason: collision with root package name */
        private boolean f203c = false;

        /* renamed from: d  reason: collision with root package name */
        private boolean f204d = false;

        /* renamed from: e  reason: collision with root package name */
        private ShadowHook.c f205e = ByteHook.defaultShadowhookLibLoader;

        /* renamed from: f  reason: collision with root package name */
        private ShadowHook.d f206f = ByteHook.defaultShadowhookMode;

        /* renamed from: g  reason: collision with root package name */
        private boolean f207g = false;

        /* renamed from: h  reason: collision with root package name */
        private boolean f208h = false;

        public b setLibLoader(com.bytedance.android.bytehook.a aVar) {
            this.f201a = aVar;
            return this;
        }

        public b setMode(c cVar) {
            this.f202b = cVar.a();
            return this;
        }

        public b setDebug(boolean z) {
            this.f203c = z;
            return this;
        }

        public b a(boolean z) {
            this.f204d = z;
            return this;
        }

        public b a(ShadowHook.c cVar) {
            this.f205e = cVar;
            return this;
        }

        public b a(ShadowHook.d dVar) {
            this.f206f = dVar;
            return this;
        }

        public b b(boolean z) {
            this.f207g = z;
            return this;
        }

        public b c(boolean z) {
            this.f208h = z;
            return this;
        }

        public a build() {
            a aVar = new a();
            aVar.setLibLoader(this.f201a);
            aVar.setMode(this.f202b);
            aVar.setDebug(this.f203c);
            aVar.a(this.f204d);
            aVar.a(this.f205e);
            aVar.a(this.f206f);
            aVar.b(this.f207g);
            aVar.c(this.f208h);
            return aVar;
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        AUTOMATIC(0),
        MANUAL(1);
        

        /* renamed from: a  reason: collision with root package name */
        private final int f210a;

        c(int i2) {
            this.f210a = i2;
        }

        int a() {
            return this.f210a;
        }
    }
}
