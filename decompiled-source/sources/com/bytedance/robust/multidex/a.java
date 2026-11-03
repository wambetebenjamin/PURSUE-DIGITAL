package com.bytedance.robust.multidex;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import com.facebook.internal.AnalyticsEvents;
import com.meituan.robust.ChangeQuickRedirect;
import com.meituan.robust.Constants;
import com.meituan.robust.PatchProxy;
import com.meituan.robust.PatchProxyResult;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
/* compiled from: MultiDexExtractor.java */
/* loaded from: classes2.dex */
public final class a implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    public static ChangeQuickRedirect f12113a = null;

    /* renamed from: b  reason: collision with root package name */
    static final String f12114b = ".dex";

    /* renamed from: c  reason: collision with root package name */
    static final String f12115c = ".zip";

    /* renamed from: d  reason: collision with root package name */
    private static final String f12116d = "PatchMultiDex";

    /* renamed from: e  reason: collision with root package name */
    private static final String f12117e = "classes";

    /* renamed from: f  reason: collision with root package name */
    private static final String f12118f = ".classes";

    /* renamed from: g  reason: collision with root package name */
    private static final int f12119g = 3;

    /* renamed from: h  reason: collision with root package name */
    private static final String f12120h = "multidex.version";

    /* renamed from: i  reason: collision with root package name */
    private static final String f12121i = "timestamp";

    /* renamed from: j  reason: collision with root package name */
    private static final String f12122j = "crc";
    private static final String k = "dex.number";
    private static final String l = "dex.crc.";
    private static final String m = "dex.time.";
    private static final int n = 16384;
    private static final long o = -1;
    private static final String p = "MultiDex.lock";
    private final File q;
    private final long r;
    private final File s;
    private final RandomAccessFile t;
    private final FileChannel u;
    private final FileLock v;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(File file, File file2) throws IOException {
        Log.i(f12116d, " MultiDexExtractor(" + file.getPath() + ", " + file2.getPath() + ")");
        this.q = file;
        this.s = file2;
        this.r = b(file);
        File file3 = new File(file2, p);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file3, "rw");
        this.t = randomAccessFile;
        try {
            FileChannel channel = randomAccessFile.getChannel();
            this.u = channel;
            try {
                Log.i(f12116d, " Blocking on lock " + file3.getPath());
                this.v = channel.lock();
                Log.i(f12116d, " " + file3.getPath() + " locked");
            } catch (IOException e2) {
                e = e2;
                a(this.u);
                throw e;
            } catch (Error e3) {
                e = e3;
                a(this.u);
                throw e;
            } catch (RuntimeException e4) {
                e = e4;
                a(this.u);
                throw e;
            }
        } catch (IOException | Error | RuntimeException e5) {
            a(this.t);
            throw e5;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<? extends File> a(Context context, String str, boolean z) throws IOException {
        List<C0178a> a2;
        List<C0178a> list;
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{context, str, new Byte(z ? (byte) 1 : (byte) 0)}, this, f12113a, false, "c50ae33faf3435b8f199b6823df4902a");
        if (proxy != null) {
            return (List) proxy.result;
        }
        Log.i(f12116d, " MultiDexExtractor.load(" + this.q.getPath() + ", " + z + ", " + str + ")");
        if (!this.v.isValid()) {
            throw new IllegalStateException("MultiDexExtractor was closed");
        }
        if (!z && !a(context, this.q, this.r, str)) {
            try {
                list = a(context, str);
            } catch (IOException e2) {
                Log.e(f12116d, " Failed to reload existing extracted secondary dex files, falling back to fresh extraction", e2);
                a2 = a();
                a(context, str, a(this.q), this.r, a2);
            }
            Log.i(f12116d, " load found " + list.size() + " secondary dex files");
            return list;
        }
        if (z) {
            Log.i(f12116d, " Forced extraction must be performed.");
        } else {
            Log.i(f12116d, " Detected that extraction must be performed.");
        }
        a2 = a();
        a(context, str, a(this.q), this.r, a2);
        list = a2;
        Log.i(f12116d, " load found " + list.size() + " secondary dex files");
        return list;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (PatchProxy.proxy(new Object[0], this, f12113a, false, "eebf4cdb8900404a10e60e4a3d48839f") != null) {
            return;
        }
        this.v.release();
        this.u.close();
        this.t.close();
    }

    private List<C0178a> a(Context context, String str) throws IOException {
        int i2 = 2;
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{context, str}, this, f12113a, false, "fe107518a1520e0443451fc9cfe2afa2");
        if (proxy != null) {
            return (List) proxy.result;
        }
        Log.i(f12116d, " loading existing secondary dex files");
        String str2 = this.q.getName() + f12118f;
        SharedPreferences a2 = a(context);
        int i3 = a2.getInt(str + k, 1);
        ArrayList arrayList = new ArrayList(i3 - 1);
        while (i2 <= i3) {
            C0178a c0178a = new C0178a(this.s, str2 + i2 + f12115c);
            if (!c0178a.isFile()) {
                throw new IOException("Missing extracted secondary dex file '" + c0178a.getPath() + "'");
            }
            c0178a.f12125a = b(c0178a);
            long j2 = a2.getLong(str + l + i2, -1L);
            long j3 = a2.getLong(str + m + i2, -1L);
            long lastModified = c0178a.lastModified();
            if (j3 == lastModified) {
                String str3 = str2;
                SharedPreferences sharedPreferences = a2;
                if (j2 == c0178a.f12125a) {
                    arrayList.add(c0178a);
                    i2++;
                    a2 = sharedPreferences;
                    str2 = str3;
                }
            }
            throw new IOException("Invalid extracted dex: " + c0178a + " (key \"" + str + "\"), expected modification time: " + j3 + ", modification time: " + lastModified + ", expected crc: " + j2 + ", file crc: " + c0178a.f12125a);
        }
        return arrayList;
    }

    private static boolean a(Context context, File file, long j2, String str) {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{context, file, new Long(j2), str}, null, f12113a, true, "6eb4e6240e68e2e713160173e1a8a4e0");
        if (proxy != null) {
            return ((Boolean) proxy.result).booleanValue();
        }
        SharedPreferences a2 = a(context);
        return (a2.getLong(new StringBuilder().append(str).append("timestamp").toString(), -1L) == a(file) && a2.getLong(new StringBuilder().append(str).append(f12122j).toString(), -1L) == j2) ? false : true;
    }

    private static long a(File file) {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{file}, null, f12113a, true, "3dfa61b9feffdaa461a18aea451696cd");
        if (proxy != null) {
            return ((Long) proxy.result).longValue();
        }
        long lastModified = file.lastModified();
        return lastModified == -1 ? lastModified - 1 : lastModified;
    }

    private static long b(File file) throws IOException {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{file}, null, f12113a, true, "e9a8a87e814d9fa0840b9239d9e3d7c7");
        if (proxy != null) {
            return ((Long) proxy.result).longValue();
        }
        long a2 = c.a(file);
        return a2 == -1 ? a2 - 1 : a2;
    }

    private List<C0178a> a() throws IOException {
        int i2;
        int i3 = 0;
        PatchProxyResult proxy = PatchProxy.proxy(new Object[0], this, f12113a, false, "fde2452c098cd589149797b35336ab4b");
        if (proxy != null) {
            return (List) proxy.result;
        }
        String str = this.q.getName() + f12118f;
        b();
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(this.q);
        try {
            ZipEntry entry = zipFile.getEntry(f12117e + 2 + f12114b);
            int i4 = 2;
            while (entry != null) {
                C0178a c0178a = new C0178a(this.s, str + i4 + f12115c);
                arrayList.add(c0178a);
                Log.i(f12116d, " Extraction is needed for file " + c0178a);
                int i5 = i3;
                int i6 = i5;
                while (i5 < 3 && i6 == 0) {
                    int i7 = i5 + 1;
                    a(zipFile, entry, c0178a, str);
                    try {
                        c0178a.f12125a = b(c0178a);
                        i2 = 1;
                    } catch (IOException e2) {
                        Log.e(f12116d, " Failed to read crc from " + c0178a.getAbsolutePath(), e2);
                        i2 = i3;
                    }
                    String str2 = str;
                    Log.i(f12116d, " Extraction " + (i2 != 0 ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : "failed") + " '" + c0178a.getAbsolutePath() + "': length " + c0178a.length() + " - crc: " + c0178a.f12125a);
                    if (i2 == 0) {
                        c0178a.delete();
                        if (c0178a.exists()) {
                            Log.w(f12116d, " Failed to delete corrupted secondary dex '" + c0178a.getPath() + "'");
                        }
                    }
                    i6 = i2;
                    i5 = i7;
                    str = str2;
                    i3 = 0;
                }
                String str3 = str;
                if (i6 != 0) {
                    i4++;
                    entry = zipFile.getEntry(f12117e + i4 + f12114b);
                    str = str3;
                    i3 = 0;
                } else {
                    throw new IOException("Could not create zip file " + c0178a.getAbsolutePath() + " for secondary dex (" + i4 + ")");
                }
            }
            try {
                zipFile.close();
            } catch (IOException e3) {
                Log.e(f12116d, " Failed to close resource", e3);
            }
            return arrayList;
        } catch (Throwable th) {
            try {
                zipFile.close();
            } catch (IOException e4) {
                Log.e(f12116d, " Failed to close resource", e4);
            }
            throw th;
        }
    }

    private static void a(Context context, String str, long j2, long j3, List<C0178a> list) {
        int i2 = 2;
        if (PatchProxy.proxy(new Object[]{context, str, new Long(j2), new Long(j3), list}, null, f12113a, true, "5a4bfaeb0bf6672751dd52baf4a6aec0") != null) {
            return;
        }
        SharedPreferences.Editor edit = a(context).edit();
        edit.putLong(str + "timestamp", j2);
        edit.putLong(str + f12122j, j3);
        edit.putInt(str + k, list.size() + 1);
        for (C0178a c0178a : list) {
            edit.putLong(str + l + i2, c0178a.f12125a);
            edit.putLong(str + m + i2, c0178a.lastModified());
            i2++;
        }
        edit.commit();
    }

    private static SharedPreferences a(Context context) {
        PatchProxyResult proxy = PatchProxy.proxy(new Object[]{context}, null, f12113a, true, "91ba593bfa4a6757b2f94f979c59148d");
        if (proxy != null) {
            return (SharedPreferences) proxy.result;
        }
        return context.getSharedPreferences(f12120h, Build.VERSION.SDK_INT >= 11 ? 4 : 0);
    }

    private void b() {
        if (PatchProxy.proxy(new Object[0], this, f12113a, false, "fef342ffa140f1d290df9c2267565870") != null) {
            return;
        }
        File[] listFiles = this.s.listFiles(new FileFilter() { // from class: com.bytedance.robust.multidex.a.1

            /* renamed from: a  reason: collision with root package name */
            public static ChangeQuickRedirect f12123a;

            @Override // java.io.FileFilter
            public boolean accept(File file) {
                PatchProxyResult proxy = PatchProxy.proxy(new Object[]{file}, this, f12123a, false, "3248e8166040034323b25510cd6d940e");
                return proxy != null ? ((Boolean) proxy.result).booleanValue() : !file.getName().equals(a.p);
            }
        });
        if (listFiles == null) {
            Log.w(f12116d, " Failed to list secondary dex dir content (" + this.s.getPath() + ").");
            return;
        }
        for (File file : listFiles) {
            Log.i(f12116d, " Trying to delete old file " + file.getPath() + " of size " + file.length());
            if (!file.delete()) {
                Log.w(f12116d, " Failed to delete old file " + file.getPath());
            } else {
                Log.i(f12116d, " Deleted old file " + file.getPath());
            }
        }
    }

    private static void a(ZipFile zipFile, ZipEntry zipEntry, File file, String str) throws IOException, FileNotFoundException {
        if (PatchProxy.proxy(new Object[]{zipFile, zipEntry, file, str}, null, f12113a, true, "147c46ca96673dc85c7141947b25996b") != null) {
            return;
        }
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        File createTempFile = File.createTempFile("tmp-" + str, f12115c, file.getParentFile());
        Log.i(f12116d, " Extracting " + createTempFile.getPath());
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(createTempFile)));
            ZipEntry zipEntry2 = new ZipEntry(Constants.CLASSES_DEX_NAME);
            zipEntry2.setTime(zipEntry.getTime());
            zipOutputStream.putNextEntry(zipEntry2);
            byte[] bArr = new byte[16384];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                zipOutputStream.write(bArr, 0, read);
            }
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            if (!createTempFile.setReadOnly()) {
                throw new IOException("Failed to mark readonly \"" + createTempFile.getAbsolutePath() + "\" (tmp of \"" + file.getAbsolutePath() + "\")");
            }
            Log.i(f12116d, " Renaming to " + file.getPath());
            if (!createTempFile.renameTo(file)) {
                throw new IOException("Failed to rename \"" + createTempFile.getAbsolutePath() + "\" to \"" + file.getAbsolutePath() + "\"");
            }
        } finally {
            a(inputStream);
            createTempFile.delete();
        }
    }

    private static void a(Closeable closeable) {
        if (PatchProxy.proxy(new Object[]{closeable}, null, f12113a, true, "6659b2a7a3d84b0c9b072565a1ae179b") != null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e2) {
            Log.e(f12116d, " Failed to close resource", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MultiDexExtractor.java */
    /* renamed from: com.bytedance.robust.multidex.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0178a extends File {

        /* renamed from: a  reason: collision with root package name */
        public long f12125a;

        public C0178a(File file, String str) {
            super(file, str);
            this.f12125a = -1L;
        }
    }
}
