package tt.g.p.f.n;

import android.content.Context;
import com.bytedance.librarian.c;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* loaded from: classes10.dex */
public class ae {

    /* renamed from: a  reason: collision with root package name */
    private Context f21133a;

    /* renamed from: b  reason: collision with root package name */
    private String f21134b;

    /* renamed from: c  reason: collision with root package name */
    private String f21135c;

    /* renamed from: d  reason: collision with root package name */
    private String f21136d;

    /* renamed from: e  reason: collision with root package name */
    private String f21137e;

    /* renamed from: f  reason: collision with root package name */
    private String f21138f;

    /* renamed from: g  reason: collision with root package name */
    private String f21139g;

    /* renamed from: h  reason: collision with root package name */
    private Map<String, String> f21140h;

    /* loaded from: classes10.dex */
    private static class b {

        /* renamed from: a  reason: collision with root package name */
        private static final ae f21141a = new ae();
    }

    private ae() {
        this.f21133a = null;
        this.f21135c = "";
        this.f21134b = "ExtractLib";
        this.f21136d = "";
        this.f21137e = "";
        this.f21138f = "HfCfg.dat";
        this.f21139g = "HfClientGuard.dat";
        this.f21140h = new HashMap();
        this.f21136d = System.mapLibraryName("gp");
        this.f21140h.put("arm64", "arm64-v8a");
        this.f21140h.put("arm", "armeabi-v7a");
        this.f21140h.put("x86", "x86");
    }

    private String a(String str) {
        Closeable closeable = null;
        try {
            File file = new File(str);
            if (!file.exists()) {
                a((Closeable) null);
                return "";
            } else if (file.length() != 40) {
                a((Closeable) null);
                return "";
            } else {
                FileInputStream fileInputStream = new FileInputStream(str);
                try {
                    byte[] bArr = new byte[40];
                    if (fileInputStream.read(bArr, 0, 40) != 40) {
                        a(fileInputStream);
                        return "";
                    }
                    String str2 = new String(bArr);
                    a(fileInputStream);
                    return str2;
                } catch (Throwable unused) {
                    closeable = fileInputStream;
                    a(closeable);
                    return "";
                }
            }
        } catch (Throwable unused2) {
        }
    }

    private ZipEntry a(ZipFile zipFile, String str) {
        try {
            return zipFile.getEntry(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static ae a() {
        return b.f21141a;
    }

    private void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    private static void a(ZipFile zipFile) {
        if (zipFile == null) {
            return;
        }
        try {
            zipFile.close();
        } catch (IOException unused) {
        }
    }

    private boolean a(String str, String str2) {
        try {
            File file = new File(str);
            File file2 = new File(str2);
            return !file.exists() ? file2.exists() : file.renameTo(file2);
        } catch (Throwable unused) {
            return false;
        }
    }

    private ZipEntry b(ZipFile zipFile) {
        String e2 = e();
        if (this.f21140h.containsKey(e2)) {
            return a(zipFile, String.format("lib/%s/%s", this.f21140h.get(e2), this.f21136d));
        }
        String[] split = k.a().split(",");
        int length = split.length;
        for (int i2 = 0; i2 < length; i2++) {
            ZipEntry a2 = a(zipFile, String.format("lib/%s/%s", split[i2], this.f21136d));
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    private boolean b(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return true;
            }
            file.mkdirs();
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x003b, code lost:
        if (r0 == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String c() {
        /*
            r4 = this;
            android.content.Context r0 = r4.f21133a
            if (r0 != 0) goto L7
            java.lang.String r0 = ""
            return r0
        L7:
            java.lang.String r0 = r4.f21135c
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L12
            java.lang.String r0 = r4.f21135c
            return r0
        L12:
            android.content.Context r0 = r4.f21133a
            java.io.File r0 = r0.getFilesDir()
            android.content.Context r1 = r4.f21133a
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo()
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r1 = r1.packageName
            r3 = 0
            r2[r3] = r1
            java.lang.String r1 = "/data/data/%s/files"
            java.lang.String r1 = java.lang.String.format(r1, r2)
            if (r0 != 0) goto L2f
            goto L3d
        L2f:
            android.content.Context r0 = r4.f21133a
            java.io.File r0 = r0.getFilesDir()
            java.lang.String r0 = r0.getAbsolutePath()
            r4.f21135c = r0
            if (r0 != 0) goto L3f
        L3d:
            r4.f21135c = r1
        L3f:
            java.lang.String r0 = r4.f21135c
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: tt.g.p.f.n.ae.c():java.lang.String");
    }

    private String c(String str) {
        InputStream inputStream;
        Closeable closeable;
        String str2 = "";
        ZipFile zipFile = null;
        try {
            ZipFile zipFile2 = new ZipFile(new File(this.f21133a.getApplicationInfo().sourceDir), 1);
            try {
                ZipEntry b2 = b(zipFile2);
                if (b2 == null) {
                    a((Closeable) null);
                    a((Closeable) null);
                    a(zipFile2);
                    return "";
                }
                File file = new File(str, this.f21136d);
                file.createNewFile();
                inputStream = zipFile2.getInputStream(b2);
                try {
                    closeable = new FileOutputStream(file);
                } catch (Throwable unused) {
                    closeable = null;
                    zipFile = zipFile2;
                    a(inputStream);
                    a(closeable);
                    a(zipFile);
                    return str2;
                }
                try {
                    byte[] bArr = new byte[16384];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read <= 0) {
                            str2 = file.getAbsolutePath();
                            file.setExecutable(true);
                            file.setReadable(true);
                            a(inputStream);
                            a(closeable);
                            a(zipFile2);
                            return str2;
                        }
                        closeable.write(bArr, 0, read);
                    }
                } catch (Throwable unused2) {
                    zipFile = zipFile2;
                    a(inputStream);
                    a(closeable);
                    a(zipFile);
                    return str2;
                }
            } catch (Throwable unused3) {
                inputStream = null;
            }
        } catch (Throwable unused4) {
            inputStream = null;
            closeable = null;
        }
    }

    private boolean d() {
        try {
            File file = new File(String.format("%s/HfFiles/%s/%s", c(), e(), this.f21139g));
            if (file.exists()) {
                return false;
            }
            return file.createNewFile();
        } catch (Throwable unused) {
            return false;
        }
    }

    private boolean d(String str) {
        try {
            System.load(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private String e() {
        if (this.f21137e.isEmpty()) {
            String[] split = this.f21133a.getApplicationInfo().nativeLibraryDir.split(c.a.SEPARATOR);
            if (split.length == 0) {
                return this.f21137e;
            }
            String str = split[split.length - 1];
            this.f21137e = str;
            return str;
        }
        return this.f21137e;
    }

    private String f() {
        String c2 = c();
        if (c2.isEmpty()) {
            return "";
        }
        String e2 = e();
        if (e2.isEmpty()) {
            return "";
        }
        String format = String.format("%s/HfFiles/%s", c2, e2);
        String a2 = a(String.format("%s/%s", format, this.f21138f));
        if (a2.isEmpty()) {
            return "";
        }
        String format2 = String.format("%s/%s", format, this.f21136d);
        return !a(String.format("%s/HfCache_%s", format, a2), format2) ? "" : format2;
    }

    private void g() {
        String c2 = c();
        if (c2.isEmpty()) {
            return;
        }
        String format = String.format("%s/%s", c2, this.f21134b);
        if (b(format)) {
            String c3 = c(format);
            if (c3.isEmpty()) {
                return;
            }
            d(c3);
        }
    }

    private void h() {
        try {
            System.loadLibrary("gp");
        } catch (UnsatisfiedLinkError unused) {
            g();
        }
    }

    public void a(Context context) {
        this.f21133a = context;
    }

    public void b() {
        String f2 = f();
        if (!f2.isEmpty() && d() && d(f2)) {
            return;
        }
        h();
    }
}
