package com.bytedance.librarian;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.bytedance.librarian.elf.f;
import com.facebook.internal.security.CertificateUtil;
import com.meituan.robust.Constants;
import dalvik.system.BaseDexClassLoader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LibrarianImpl.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    static final c f9467a;
    private static final String[] n = {"log", "m", "stdc++", "dl", "c", "z", "android", "jnigraphics", "EGL", "GLESv1_CM", "GLESv2", "GLESv3", "OpenSLES", "OpenMAXAL"};

    /* renamed from: b  reason: collision with root package name */
    private final File f9468b;

    /* renamed from: c  reason: collision with root package name */
    private final d f9469c;

    /* renamed from: e  reason: collision with root package name */
    private final File f9471e;

    /* renamed from: f  reason: collision with root package name */
    private volatile boolean f9472f;

    /* renamed from: g  reason: collision with root package name */
    private File f9473g;

    /* renamed from: h  reason: collision with root package name */
    private ZipFile[] f9474h;

    /* renamed from: i  reason: collision with root package name */
    private String f9475i;

    /* renamed from: j  reason: collision with root package name */
    private ApplicationInfo f9476j;
    private Map<String, String> k;
    private String[] l;
    private String m;
    final String[] o = {"/system/lib64", "/vendor/lib64"};

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, b> f9470d = new HashMap();

    /* compiled from: LibrarianImpl.java */
    /* loaded from: classes2.dex */
    interface a {
        public static final int BUFFER_SIZE = 8192;
        public static final String DEFAULT_VERSION = "default.version";
        public static final String DOT = ".";
        public static final String LIB_PREFIX = "lib";
        public static final String LOCK_FILE_NAME = "process.lock";
        public static final String SEPARATOR = "/";
        public static final String SO_SUFFIX = ".so";
        public static final String TAG = "librarian";
    }

    static {
        Context context = com.bytedance.librarian.b.f9463a;
        com.bytedance.librarian.b.f9463a = null;
        if (context == null) {
            throw new e("you should call init first or use loadLibraryForModule.");
        }
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        File dir = context.getDir(a.TAG, 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, (com.bytedance.librarian.b.f9464b != null ? com.bytedance.librarian.b.f9464b : a.DEFAULT_VERSION) + a.DOT + (new File(applicationInfo.sourceDir).lastModified() >> 8));
        File[] listFiles = dir.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (!file.getName().equals(file2.getName())) {
                    File[] listFiles2 = file2.listFiles();
                    if (listFiles2 != null) {
                        for (File file3 : listFiles2) {
                            file3.delete();
                        }
                    }
                    file2.delete();
                }
            }
        }
        file.mkdirs();
        if (file.exists()) {
            f9467a = new c(applicationInfo, file, com.bytedance.librarian.b.f9465c != null ? com.bytedance.librarian.b.f9465c : new d());
        } else {
            f9467a = null;
        }
    }

    private c(ApplicationInfo applicationInfo, File file, d dVar) {
        this.f9468b = file;
        this.f9469c = dVar;
        this.f9476j = applicationInfo;
        this.f9471e = new File(file, a.LOCK_FILE_NAME);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a() {
        String obj;
        synchronized (this.f9470d) {
            obj = this.f9470d.toString();
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, boolean z) {
        synchronized (this.f9470d) {
            b bVar = this.f9470d.get(str);
            if (bVar != null) {
                if (bVar.f9477a) {
                    return;
                }
            } else {
                bVar = new b();
                this.f9470d.put(str, bVar);
            }
            synchronized (bVar) {
                if (bVar.f9477a) {
                    this.f9469c.a("lib was already loaded before: " + str);
                    return;
                }
                b(str, z);
                bVar.f9477a = true;
            }
        }
    }

    private void b(String str, boolean z) {
        boolean z2 = Build.VERSION.SDK_INT >= 18;
        File file = new File(this.f9468b, d(str));
        if (z2) {
            if (a(str, "preload")) {
                return;
            }
            if (file.exists() && a(file, str, false)) {
                return;
            }
            b();
            if (e(str) && a(str, "lollipop")) {
                return;
            }
            File a2 = a(str, file, z);
            b(a2, z);
            if (a2 != file) {
                if (a(str, "after loading dep libs")) {
                    return;
                }
                a(file, z);
            }
        } else if (!z && a(str, "preload lower version device")) {
            return;
        } else {
            b();
            a(file, z);
            b(file, z);
        }
        a(file, str, true);
    }

    private File a(String str, File file, boolean z) {
        File file2 = new File(this.f9473g, d(str));
        if (a(file2)) {
            return file2;
        }
        a(file, z);
        return file;
    }

    private void a(File file, boolean z) {
        String name = file.getName();
        synchronized (this) {
            C0135c c0135c = new C0135c(this.f9471e);
            try {
                c0135c.a();
                if (!d(file)) {
                    if (!z) {
                        throw new e("fail to extract " + name);
                    }
                    this.f9469c.a("may be system lib, no found " + name);
                }
                c0135c.b();
            } catch (IOException e2) {
                throw new e("fail to extract " + name, e2);
            }
        }
    }

    private boolean a(File file) {
        if (!file.exists()) {
            this.f9469c.b("file is not exist: " + file.getPath());
            return false;
        }
        b(file.getName());
        long j2 = 0;
        String str = "lib/" + this.m + a.SEPARATOR + file.getName();
        ZipFile[] zipFileArr = this.f9474h;
        int length = zipFileArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            ZipEntry entry = zipFileArr[i2].getEntry(str);
            if (entry != null) {
                j2 = entry.getCrc();
                break;
            }
            i2++;
        }
        long c2 = c(file);
        if (j2 == c2) {
            this.f9469c.a(str + " compare crc ok: entry is " + j2 + ", file is " + c2);
            return true;
        }
        this.f9469c.b(str + " crc is wrong: entry is " + j2 + ", file is " + c2);
        return false;
    }

    private boolean a(String str, String str2) {
        try {
            this.f9469c.d(str);
            this.f9469c.a("loaded the origin lib " + str + ", tag: " + str2);
            return true;
        } catch (UnsatisfiedLinkError e2) {
            this.f9469c.a("fail to load " + str + ", tag: " + str2, e2);
            return false;
        }
    }

    private boolean a(File file, String str, boolean z) {
        try {
            this.f9469c.e(file.getPath());
            this.f9469c.a("loaded the extracted lib " + str);
            return true;
        } catch (UnsatisfiedLinkError e2) {
            if (z) {
                throw new e("finally fail to load " + file.getPath(), e2);
            }
            this.f9469c.a("fail to load " + file.getName() + ", out lib exists", e2);
            return false;
        }
    }

    private void b(File file, boolean z) {
        try {
            for (String str : b(file)) {
                String g2 = g(str);
                if (!a(g2)) {
                    this.f9469c.a("to load depended lib " + g2);
                    a(g2, z);
                }
            }
        } catch (IOException e2) {
            throw new e("fail to load depended lib", e2);
        }
    }

    private void b() {
        File file;
        String[] strArr;
        if (this.f9472f) {
            return;
        }
        synchronized (this) {
            if (this.f9472f) {
                return;
            }
            File file2 = new File(this.f9476j.sourceDir);
            ArrayList arrayList = new ArrayList();
            arrayList.add(file2);
            if (Build.VERSION.SDK_INT >= 21 && (strArr = this.f9476j.splitSourceDirs) != null && strArr.length != 0) {
                for (String str : strArr) {
                    arrayList.add(new File(str));
                }
            }
            ZipFile[] zipFileArr = new ZipFile[arrayList.size()];
            Iterator it = arrayList.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                int i3 = i2 + 1;
                try {
                    zipFileArr[i2] = new ZipFile((File) it.next());
                } catch (IOException e2) {
                    new e("fail to get zip file " + file.getName() + ", size " + file.length() + ", exists " + file.exists(), e2).printStackTrace();
                }
                i2 = i3;
            }
            this.f9474h = zipFileArr;
            this.f9473g = new File(this.f9476j.nativeLibraryDir);
            HashMap hashMap = new HashMap();
            this.k = hashMap;
            hashMap.put("arm64-v8a", "arm64");
            this.k.put("armeabi-v7a", "arm");
            this.k.put("armeabi", "arm");
            String str2 = null;
            if (Build.VERSION.SDK_INT >= 21) {
                try {
                    Class<?> cls = Class.forName("dalvik.system.VMRuntime");
                    str2 = (String) cls.getDeclaredMethod("vmInstructionSet", new Class[0]).invoke(cls.getDeclaredMethod("getRuntime", new Class[0]).invoke(null, new Object[0]), new Object[0]);
                } catch (Exception e3) {
                    this.f9469c.a("fail to process vm abi previously", new e("reflect err", e3));
                }
            }
            this.f9475i = str2;
            this.f9469c.a("vm instruction set: " + this.f9475i);
            this.l = Build.VERSION.SDK_INT < 21 ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : Build.SUPPORTED_ABIS;
            this.f9472f = true;
        }
    }

    private boolean e(String str) {
        File[] fileArr;
        int i2;
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23) {
            String str2 = this.f9475i;
            if (str2 != null && str2.contains("64")) {
                this.f9469c.a("try to opt native lib dirs on L");
                try {
                    BaseDexClassLoader baseDexClassLoader = (BaseDexClassLoader) getClass().getClassLoader();
                    Field declaredField = BaseDexClassLoader.class.getDeclaredField("pathList");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(baseDexClassLoader);
                    Field declaredField2 = obj.getClass().getDeclaredField("nativeLibraryDirectories");
                    declaredField2.setAccessible(true);
                    File[] fileArr2 = (File[]) declaredField2.get(obj);
                    int length = fileArr2.length;
                    File[] fileArr3 = new File[length];
                    System.arraycopy(fileArr2, 0, fileArr3, 0, length);
                    int i3 = -1;
                    int i4 = -1;
                    int i5 = -1;
                    for (int i6 = 0; i6 < length; i6++) {
                        File file = fileArr3[i6];
                        if (this.f9468b.equals(file)) {
                            i3 = i6;
                        } else if (this.f9473g.equals(file)) {
                            i5 = i6;
                        } else if (new File(file, d(str)).exists()) {
                            i4 = i6;
                        }
                    }
                    if (i3 != -1) {
                        fileArr = new File[length];
                        a(fileArr3, 0, i3);
                        i2 = 1;
                    } else {
                        fileArr = new File[length + 1];
                        fileArr[0] = this.f9468b;
                        i2 = 0;
                    }
                    if (i4 != -1) {
                        a(fileArr3, i2, i4);
                        i2++;
                    }
                    if (i5 != -1) {
                        a(fileArr3, i2, i5);
                        i2++;
                    }
                    for (int length2 = length - this.o.length; length2 < length; length2++) {
                        if (!f(fileArr3[length2].getAbsolutePath())) {
                            for (int i7 = i2; i7 < length - 2; i7++) {
                                if (f(fileArr3[i7].getAbsolutePath())) {
                                    a(fileArr3, length2, i7);
                                }
                            }
                        }
                    }
                    if (i3 != -1) {
                        System.arraycopy(fileArr3, 0, fileArr, 0, length);
                    } else {
                        System.arraycopy(fileArr3, 0, fileArr, 1, length);
                    }
                    declaredField2.set(obj, fileArr);
                    this.f9469c.a("success to insert path " + baseDexClassLoader);
                    return true;
                } catch (Exception e2) {
                    this.f9469c.a("fail to insert path", new e("opt lib dir err", e2));
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    private void a(File[] fileArr, int i2, int i3) {
        if (i2 == i3) {
            return;
        }
        File file = fileArr[i2];
        fileArr[i2] = fileArr[i3];
        fileArr[i3] = file;
    }

    private boolean f(String str) {
        for (String str2 : this.o) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(String str) {
        for (String str2 : n) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private List<String> b(File file) {
        Closeable closeable = null;
        try {
            f fVar = new f(file);
            try {
                List<String> parseNeededDependencies = fVar.parseNeededDependencies();
                Collections.sort(parseNeededDependencies);
                a(fVar);
                return parseNeededDependencies;
            } catch (Throwable th) {
                th = th;
                closeable = fVar;
                a(closeable);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private long c(File file) {
        byte[] bArr = new byte[8192];
        Closeable closeable = null;
        try {
            try {
                CheckedInputStream checkedInputStream = new CheckedInputStream(new FileInputStream(file), new CRC32());
                do {
                    try {
                    } catch (IOException e2) {
                        e = e2;
                        closeable = checkedInputStream;
                        this.f9469c.a("fail to check sum for " + file.getPath(), e);
                        a(closeable);
                        return 0L;
                    } catch (Throwable th) {
                        th = th;
                        closeable = checkedInputStream;
                        a(closeable);
                        throw th;
                    }
                } while (checkedInputStream.read(bArr) >= 0);
                long value = checkedInputStream.getChecksum().getValue();
                a(checkedInputStream);
                return value;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    private boolean d(File file) {
        ZipFile[] zipFileArr;
        b(file.getName());
        String str = "lib/" + this.m + a.SEPARATOR + file.getName();
        for (ZipFile zipFile : this.f9474h) {
            ZipEntry entry = zipFile.getEntry(str);
            if (entry != null) {
                InputStream inputStream = zipFile.getInputStream(entry);
                try {
                    a(inputStream, file);
                    this.f9469c.a("get lib file " + file.getPath() + " from " + str + " of " + zipFile.getName());
                    a(inputStream);
                    return true;
                } catch (Throwable th) {
                    a(inputStream);
                    throw th;
                }
            }
        }
        return false;
    }

    private void b(String str) {
        String[] strArr;
        ZipFile[] zipFileArr;
        if (this.m != null) {
            return;
        }
        for (String str2 : this.l) {
            if (c(str2)) {
                String str3 = "lib/" + str2 + a.SEPARATOR + str;
                for (ZipFile zipFile : this.f9474h) {
                    if (zipFile != null && zipFile.getEntry(str3) != null) {
                        this.m = str2;
                        this.f9469c.a("ensure that abi is " + str2);
                        return;
                    }
                }
                continue;
            }
        }
        throw new e("can not ensure abi for " + str + ", check " + this.f9475i + ", apks " + c());
    }

    private String c() {
        ZipFile[] zipFileArr;
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.ARRAY_TYPE);
        for (ZipFile zipFile : this.f9474h) {
            if (zipFile != null) {
                String name = zipFile.getName();
                sb.append(name).append("#").append(zipFile.size()).append(CertificateUtil.DELIMITER).append(new File(name).length()).append("|");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private boolean c(String str) {
        if (this.f9475i == null) {
            return true;
        }
        String str2 = this.k.get(str);
        String str3 = this.f9475i;
        if (str2 != null) {
            str = str2;
        }
        return str3.equals(str);
    }

    private static String d(String str) {
        return "lib" + str + a.SO_SUFFIX;
    }

    private static String g(String str) {
        return str.substring(3, str.length() - 3);
    }

    private File a(InputStream inputStream, File file) {
        File createTempFile = File.createTempFile("tmp-", file.getName(), file.getParentFile());
        this.f9469c.a("extracting " + createTempFile.getPath());
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        try {
            byte[] bArr = new byte[8192];
            int read = inputStream.read(bArr);
            while (read != -1) {
                fileOutputStream.write(bArr, 0, read);
                read = inputStream.read(bArr);
            }
            if (!createTempFile.setReadOnly()) {
                throw new IOException("failed to mark readonly \"" + createTempFile.getAbsolutePath() + "\" (tmp of \"" + file.getAbsolutePath() + "\")");
            }
            this.f9469c.a("renaming to " + file.getPath());
            if (createTempFile.renameTo(file)) {
                return file;
            }
            throw new IOException("failed to rename \"" + createTempFile.getAbsolutePath() + "\" to \"" + file.getAbsolutePath() + "\"");
        } finally {
            a(fileOutputStream);
            createTempFile.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e2) {
            this.f9469c.a("failed to close resource", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LibrarianImpl.java */
    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        boolean f9477a = false;

        b() {
        }

        public String toString() {
            return Boolean.toString(this.f9477a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: LibrarianImpl.java */
    /* renamed from: com.bytedance.librarian.c$c  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public class C0135c {

        /* renamed from: b  reason: collision with root package name */
        private RandomAccessFile f9479b;

        /* renamed from: c  reason: collision with root package name */
        private FileLock f9480c;

        /* renamed from: d  reason: collision with root package name */
        private FileChannel f9481d;

        /* renamed from: e  reason: collision with root package name */
        private File f9482e;

        C0135c(File file) {
            this.f9482e = file;
        }

        void a() {
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.f9482e, "rw");
            this.f9479b = randomAccessFile;
            try {
                this.f9481d = randomAccessFile.getChannel();
                try {
                    c.this.f9469c.a("blocking on lock " + this.f9482e.getPath());
                    this.f9480c = this.f9481d.lock();
                    c.this.f9469c.a("acquired on lock " + this.f9482e.getPath());
                } catch (IOException e2) {
                    c.this.a(this.f9481d);
                    throw e2;
                }
            } catch (IOException e3) {
                c.this.a(this.f9479b);
                throw e3;
            }
        }

        void b() {
            FileLock fileLock = this.f9480c;
            if (fileLock != null) {
                try {
                    fileLock.release();
                } catch (IOException unused) {
                }
            }
            c.this.f9469c.a("released lock " + this.f9482e.getPath());
            c.this.a(this.f9481d);
            c.this.a(this.f9479b);
        }
    }
}
