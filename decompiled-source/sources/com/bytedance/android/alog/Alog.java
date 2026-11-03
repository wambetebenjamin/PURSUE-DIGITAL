package com.bytedance.android.alog;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class Alog {
    public static final int LEVEL_ALL = 0;
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_SILENT = 6;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 3;

    /* renamed from: a  reason: collision with root package name */
    static final long f141a = 0;

    /* renamed from: b  reason: collision with root package name */
    private static final String f142b = "alog";

    /* renamed from: c  reason: collision with root package name */
    private static final String f143c = "/alog";

    /* renamed from: d  reason: collision with root package name */
    private static final String f144d = "default";

    /* renamed from: e  reason: collision with root package name */
    private static final int f145e = 0;

    /* renamed from: f  reason: collision with root package name */
    private static final boolean f146f = false;

    /* renamed from: g  reason: collision with root package name */
    private static final int f147g = 2097152;

    /* renamed from: h  reason: collision with root package name */
    private static final int f148h = 20971520;

    /* renamed from: i  reason: collision with root package name */
    private static final int f149i = 7;

    /* renamed from: j  reason: collision with root package name */
    private static final int f150j = 65536;
    private static final int k = 196608;
    private static final String r = "b012e20c9aab1cb5257aca2069cb79a9339b3a2224f771c78d64972137936eaf0b2f7ebd8d46c2607e1d7fe7723d40b147b8ecfa8fe2eaeee05210c75822381a";
    private String A = null;
    private String B;
    private long C;
    private Context u;
    private int v;
    private String w;
    private String x;
    private int y;
    private int z;
    private static final int l = d.SAFE.a();
    private static final int m = g.RAW.a();
    private static final int n = e.DEFAULT.a();
    private static final int o = c.ZSTD.a();
    private static final int p = f.TEA_16.a();
    private static final int q = a.EC_SECP256K1.a();
    private static volatile List<com.bytedance.android.alog.e> D = null;
    private static final ArrayList<String> s = new ArrayList<>();
    private static boolean t = false;

    private static native void nativeAsyncFlush(long j2);

    private static native long nativeCreate(int i2, boolean z, String str, String str2, int i3, int i4, int i5, String str3, int i6, int i7, String str4, int i8, int i9, int i10, int i11, int i12, int i13, String str5, long j2, boolean z2);

    private static native void nativeDestroy(long j2);

    private static native long nativeGetLegacyFlushFuncAddr();

    private static native long nativeGetLegacyGetLogFileDirFuncAddr();

    private static native long nativeGetLegacyWriteFuncAddr();

    private static native long nativeGetNativeInitFuncAddr();

    private static native long nativeGetNativeWriteAsyncFuncAddr();

    private static native long nativeGetNativeWriteFuncAddr();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void nativeSetDefaultInstance(long j2);

    private static native void nativeSetLevel(long j2, int i2);

    private static native void nativeSetSyslog(long j2, boolean z);

    private static native void nativeSyncFlush(long j2);

    private static native void nativeTimedSyncFlush(long j2, int i2);

    private static native void nativeWrite(long j2, int i2, String str, String str2);

    private static native void nativeWriteAsyncMsg(long j2, int i2, String str, String str2, long j3, long j4);

    public static synchronized void init() {
        synchronized (Alog.class) {
            init(null);
        }
    }

    public static synchronized void init(com.bytedance.android.alog.b bVar) {
        synchronized (Alog.class) {
            if (t) {
                return;
            }
            if (bVar == null) {
                System.loadLibrary(f142b);
            } else {
                bVar.loadLibrary(f142b);
            }
            t = true;
        }
    }

    public Alog(Context context, int i2, boolean z, String str, String str2, int i3, int i4, int i5, String str3, int i6, int i7, String str4, int i8, int i9, int i10, int i11, int i12, int i13, String str5, boolean z2) {
        this.u = context;
        this.v = i2;
        this.w = str2;
        this.x = str3;
        this.y = i6;
        this.z = i7 / i6;
        this.B = str;
        this.C = nativeCreate(i2, z, str, str2, i3, i4, i5, str3, i6, i7, str4, i8, i9, i10, i11, i12, i13, str5, TimeZone.getDefault().getRawOffset() / 1000, z2);
    }

    public void close() {
        synchronized (this) {
            long j2 = this.C;
            if (j2 != 0) {
                this.u = null;
                this.v = 6;
                nativeDestroy(j2);
                this.C = 0L;
            }
        }
    }

    public void finalize() {
        try {
            super.finalize();
        } finally {
            close();
        }
    }

    public static void a(com.bytedance.android.alog.e eVar) {
        if (eVar == null) {
            return;
        }
        if (D == null) {
            D = new CopyOnWriteArrayList();
        }
        if (D.contains(eVar)) {
            return;
        }
        D.add(eVar);
    }

    public static void b(com.bytedance.android.alog.e eVar) {
        if (eVar == null || D == null) {
            return;
        }
        D.remove(eVar);
    }

    public void write(int i2, String str, String str2) {
        if (this.C == 0 || i2 < this.v || str == null || str2 == null) {
            return;
        }
        if (D != null) {
            com.bytedance.android.alog.f fVar = new com.bytedance.android.alog.f(i2, str, str2);
            for (com.bytedance.android.alog.e eVar : D) {
                fVar = eVar.a(fVar);
            }
            if (fVar == null) {
                return;
            }
            nativeWrite(this.C, fVar.f189a, fVar.f190b, fVar.f191c);
            return;
        }
        nativeWrite(this.C, i2, str, str2);
    }

    public void writeAsyncMsg(int i2, String str, String str2, long j2, long j3) {
        if (this.C == 0 || i2 < this.v || str == null || str2 == null) {
            return;
        }
        if (D != null) {
            com.bytedance.android.alog.f fVar = new com.bytedance.android.alog.f(i2, str, str2);
            for (com.bytedance.android.alog.e eVar : D) {
                fVar = eVar.a(fVar);
            }
            if (fVar == null) {
                return;
            }
            nativeWriteAsyncMsg(this.C, fVar.f189a, fVar.f190b, fVar.f191c, j2, j3);
            return;
        }
        nativeWriteAsyncMsg(this.C, i2, str, str2, j2, j3);
    }

    public void asyncFlush() {
        long j2 = this.C;
        if (j2 != 0) {
            nativeAsyncFlush(j2);
        }
    }

    public void syncFlush() {
        long j2 = this.C;
        if (j2 != 0) {
            nativeSyncFlush(j2);
        }
    }

    public void timedSyncFlush(int i2) {
        long j2 = this.C;
        if (j2 != 0) {
            nativeTimedSyncFlush(j2, i2);
        }
    }

    public void setLevel(int i2) {
        this.v = i2;
        long j2 = this.C;
        if (j2 != 0) {
            nativeSetLevel(j2, i2);
        }
    }

    public void setSyslog(boolean z) {
        long j2 = this.C;
        if (j2 != 0) {
            nativeSetSyslog(j2, z);
        }
    }

    public long getLegacyWriteFuncAddr() {
        if (this.C != 0) {
            return nativeGetLegacyWriteFuncAddr();
        }
        return 0L;
    }

    public long getLegacyFlushFuncAddr() {
        if (this.C != 0) {
            return nativeGetLegacyFlushFuncAddr();
        }
        return 0L;
    }

    public long getLegacyGetLogFileDirFuncAddr() {
        if (this.C != 0) {
            return nativeGetLegacyGetLogFileDirFuncAddr();
        }
        return 0L;
    }

    public long getNativeWriteFuncAddr() {
        if (this.C != 0) {
            return nativeGetNativeWriteFuncAddr();
        }
        return 0L;
    }

    public long h() {
        if (this.C != 0) {
            return nativeGetNativeWriteAsyncFuncAddr();
        }
        return 0L;
    }

    public long i() {
        if (this.C != 0) {
            return nativeGetNativeInitFuncAddr();
        }
        return 0L;
    }

    public void v(String str, String str2) {
        write(0, str, str2);
    }

    public void d(String str, String str2) {
        write(1, str, str2);
    }

    public void i(String str, String str2) {
        write(2, str, str2);
    }

    public void w(String str, String str2) {
        write(3, str, str2);
    }

    public void e(String str, String str2) {
        write(4, str, str2);
    }

    public void f(String str, String str2) {
        write(5, str, str2);
    }

    public File[] getLogs(long j2, long j3) {
        if (this.A == null) {
            String b2 = com.bytedance.android.alog.d.b(this.u);
            this.A = b2;
            if (b2 == null) {
                return new File[0];
            }
        }
        return com.bytedance.android.alog.a.a(this.w, this.A, this.B, j2, j3);
    }

    public File[] getLogs(String str, long j2, long j3) {
        return getLogs(str, this.B, j2, j3);
    }

    public File[] getLogs(String str, String str2, long j2, long j3) {
        return com.bytedance.android.alog.a.a(this.w, str, str2, j2, j3);
    }

    public static File[] getLogs(String str, String str2, String str3, long j2, long j3) {
        return com.bytedance.android.alog.a.a(str, str2, str3, j2, j3);
    }

    public HashMap<String, String> getLastFetchErrorInfo() {
        return com.bytedance.android.alog.a.a();
    }

    public String getStatus() {
        File[] fileArr;
        Pattern pattern;
        int i2;
        if (t) {
            if (this.A == null) {
                this.A = com.bytedance.android.alog.d.b(this.u);
            }
            String str = this.A;
            if (str == null) {
                return "get process name failed";
            }
            String replace = str.replace(':', '-');
            File file = new File(this.x);
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    return "cache dir is empty";
                }
                String str2 = replace + "__" + this.B + ".alog.cache.guard";
                Pattern compile = Pattern.compile("^" + Pattern.quote(replace) + "__" + Pattern.quote(this.B) + "__\\d{5}\\.alog\\.cache$");
                int length = listFiles.length;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                while (i3 < length) {
                    File file2 = listFiles[i3];
                    if (file2.getName().equals(str2)) {
                        i4++;
                        if (file2.length() >= 24576) {
                            i5++;
                        }
                        fileArr = listFiles;
                    } else {
                        fileArr = listFiles;
                        if (file2.getName().startsWith(replace + "__" + this.B + "__") && compile.matcher(file2.getName()).find()) {
                            i6++;
                            pattern = compile;
                            i2 = length;
                            if (file2.length() >= this.y) {
                                i7++;
                            }
                            i3++;
                            compile = pattern;
                            listFiles = fileArr;
                            length = i2;
                        }
                    }
                    pattern = compile;
                    i2 = length;
                    i3++;
                    compile = pattern;
                    listFiles = fileArr;
                    length = i2;
                }
                if (i4 < 1) {
                    Log.d("alog_trace", str2);
                    Log.d("alog_trace", file.getAbsolutePath());
                    return "cache guard not exists";
                } else if (i5 < 1) {
                    return "cache guard size insufficiently";
                } else {
                    int i8 = this.z;
                    if (i6 < i8) {
                        return "cache block count insufficiently";
                    }
                    if (i7 < i8) {
                        return "cache block size insufficiently";
                    }
                    File file3 = new File(this.w);
                    if (file3.exists()) {
                        final Pattern compile2 = Pattern.compile("^\\d{4}_\\d{2}_\\d{2}_\\d+__" + Pattern.quote(replace) + "__" + Pattern.quote(this.B) + "\\.alog\\.hot$");
                        File[] listFiles2 = file3.listFiles(new FilenameFilter() { // from class: com.bytedance.android.alog.Alog.1
                            @Override // java.io.FilenameFilter
                            public boolean accept(File file4, String str3) {
                                return compile2.matcher(str3).find();
                            }
                        });
                        return (listFiles2 == null || listFiles2.length == 0) ? "no log file for current process and instance" : "OK";
                    }
                    return "log dir not exists";
                }
            }
            return "cache dir not exists";
        }
        return "not inited";
    }

    public long getNativeRef() {
        return this.C;
    }

    /* loaded from: classes.dex */
    public enum d {
        SPEED(0),
        SAFE(1);
        

        /* renamed from: a  reason: collision with root package name */
        private final int f168a;

        d(int i2) {
            this.f168a = i2;
        }

        int a() {
            return this.f168a;
        }
    }

    /* loaded from: classes.dex */
    public enum g {
        RAW(0),
        ISO_8601(1);
        

        /* renamed from: a  reason: collision with root package name */
        private final int f174a;

        g(int i2) {
            this.f174a = i2;
        }

        int a() {
            return this.f174a;
        }
    }

    /* loaded from: classes.dex */
    public enum e {
        DEFAULT(0),
        LEGACY(1);
        

        /* renamed from: a  reason: collision with root package name */
        private final int f170a;

        e(int i2) {
            this.f170a = i2;
        }

        int a() {
            return this.f170a;
        }
    }

    /* loaded from: classes.dex */
    public enum c {
        NONE(0),
        ZLIB(1),
        ZSTD(2);
        

        /* renamed from: a  reason: collision with root package name */
        private final int f166a;

        c(int i2) {
            this.f166a = i2;
        }

        int a() {
            return this.f166a;
        }
    }

    /* loaded from: classes.dex */
    public enum f {
        NONE(0),
        TEA_16(1),
        TEA_32(2),
        TEA_64(3);
        

        /* renamed from: a  reason: collision with root package name */
        private final int f172a;

        f(int i2) {
            this.f172a = i2;
        }

        int a() {
            return this.f172a;
        }
    }

    /* loaded from: classes.dex */
    public enum a {
        NONE(0),
        EC_SECP256K1(1),
        EC_SECP256R1(2);
        

        /* renamed from: a  reason: collision with root package name */
        private final int f154a;

        a(int i2) {
            this.f154a = i2;
        }

        int a() {
            return this.f154a;
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private Context f155a;

        /* renamed from: b  reason: collision with root package name */
        private int f156b = 0;

        /* renamed from: c  reason: collision with root package name */
        private boolean f157c = false;

        /* renamed from: d  reason: collision with root package name */
        private String f158d = null;

        /* renamed from: e  reason: collision with root package name */
        private String f159e = null;

        /* renamed from: f  reason: collision with root package name */
        private int f160f = 2097152;

        /* renamed from: g  reason: collision with root package name */
        private int f161g = Alog.f148h;

        /* renamed from: h  reason: collision with root package name */
        private int f162h = 7;

        /* renamed from: i  reason: collision with root package name */
        private String f163i = null;

        /* renamed from: j  reason: collision with root package name */
        private int f164j = 65536;
        private int k = Alog.k;
        private String l = null;
        private int m = Alog.l;
        private int n = Alog.m;
        private int o = Alog.n;
        private int p = Alog.o;
        private int q = Alog.p;
        private int r = Alog.q;
        private String s = Alog.r;
        private boolean t = false;

        public b(Context context) {
            Context applicationContext = context.getApplicationContext();
            this.f155a = applicationContext != null ? applicationContext : context;
        }

        public b setLevel(int i2) {
            this.f156b = i2;
            return this;
        }

        public b setSyslog(boolean z) {
            this.f157c = z;
            return this;
        }

        public b setInstanceName(String str) {
            if (!TextUtils.isEmpty(str)) {
                if (str.contains("-")) {
                    str = str.replace("-", "");
                }
                if (str.contains("_")) {
                    str = str.replace("_", "");
                }
                if (!TextUtils.isEmpty(str)) {
                    this.f158d = str;
                }
            }
            return this;
        }

        public b a(boolean z) {
            this.t = z;
            return this;
        }

        public b setLogFileDir(String str) {
            this.f159e = str;
            return this;
        }

        public b setLogFileSizeEach(int i2) {
            this.f160f = i2;
            return this;
        }

        public b setLogFileSizeTotal(int i2) {
            this.f161g = i2;
            return this;
        }

        public b setLogFileExpDays(int i2) {
            this.f162h = i2;
            return this;
        }

        public b setCacheFileDir(String str) {
            this.f163i = str;
            return this;
        }

        public b setCacheFileSizeEach(int i2) {
            this.f164j = i2;
            return this;
        }

        public b setCacheFileSizeTotal(int i2) {
            this.k = i2;
            return this;
        }

        public b setAppVersion(String str) {
            this.l = str;
            return this;
        }

        public b setMode(d dVar) {
            this.m = dVar.a();
            return this;
        }

        public b setTimeFormat(g gVar) {
            this.n = gVar.a();
            return this;
        }

        public b setPrefixFormat(e eVar) {
            this.o = eVar.a();
            return this;
        }

        public b setCompress(c cVar) {
            this.p = cVar.a();
            return this;
        }

        public b setSymCrypt(f fVar) {
            this.q = fVar.a();
            return this;
        }

        public b setAsymCrypt(a aVar) {
            this.r = aVar.a();
            return this;
        }

        public b setServerPublicKey(String str) {
            this.s = str;
            return this;
        }

        public Alog build() {
            if (this.f158d == null) {
                this.f158d = "default";
            }
            synchronized (Alog.s) {
                Iterator it = Alog.s.iterator();
                while (it.hasNext()) {
                    if (((String) it.next()).equals(this.f158d)) {
                        return null;
                    }
                }
                Alog.s.add(this.f158d);
                if (this.f159e == null) {
                    File externalFilesDir = this.f155a.getExternalFilesDir(null);
                    if (externalFilesDir != null) {
                        this.f159e = externalFilesDir.getPath() + Alog.f143c;
                    } else {
                        this.f159e = this.f155a.getFilesDir() + Alog.f143c;
                    }
                }
                if (this.f163i == null) {
                    this.f163i = this.f155a.getFilesDir() + Alog.f143c;
                }
                if (this.l == null) {
                    this.l = com.bytedance.android.alog.d.a(this.f155a);
                }
                int i2 = (this.f164j / 4096) * 4096;
                this.f164j = i2;
                int i3 = (this.k / 4096) * 4096;
                this.k = i3;
                if (i2 < 4096) {
                    this.f164j = 4096;
                }
                int i4 = this.f164j;
                if (i3 < i4 * 2) {
                    this.k = i4 * 2;
                }
                return new Alog(this.f155a, this.f156b, this.f157c, this.f158d, this.f159e, this.f160f, this.f161g, this.f162h, this.f163i, this.f164j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t);
            }
        }
    }
}
