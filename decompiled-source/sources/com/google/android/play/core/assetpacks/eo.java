package com.google.android.play.core.assetpacks;

import com.bytedance.sdk.account.platform.base.i;
import com.bytedance.ttgame.module.dynamic.api.DynamicPageState;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Properties;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class eo {

    /* renamed from: a  reason: collision with root package name */
    private static final com.google.android.play.core.assetpacks.internal.o f16783a = new com.google.android.play.core.assetpacks.internal.o("SliceMetadataManager");

    /* renamed from: c  reason: collision with root package name */
    private final bh f16785c;

    /* renamed from: d  reason: collision with root package name */
    private final String f16786d;

    /* renamed from: e  reason: collision with root package name */
    private final int f16787e;

    /* renamed from: f  reason: collision with root package name */
    private final long f16788f;

    /* renamed from: g  reason: collision with root package name */
    private final String f16789g;

    /* renamed from: b  reason: collision with root package name */
    private final byte[] f16784b = new byte[8192];

    /* renamed from: h  reason: collision with root package name */
    private int f16790h = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public eo(bh bhVar, String str, int i2, long j2, String str2) {
        this.f16785c = bhVar;
        this.f16786d = str;
        this.f16787e = i2;
        this.f16788f = j2;
        this.f16789g = str2;
    }

    private final File n() {
        File o = this.f16785c.o(this.f16786d, this.f16787e, this.f16788f, this.f16789g);
        if (!o.exists()) {
            o.mkdirs();
        }
        return o;
    }

    private final File o() throws IOException {
        File n = this.f16785c.n(this.f16786d, this.f16787e, this.f16788f, this.f16789g);
        n.getParentFile().mkdirs();
        n.createNewFile();
        return n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() throws IOException {
        File n = this.f16785c.n(this.f16786d, this.f16787e, this.f16788f, this.f16789g);
        if (n.exists()) {
            FileInputStream fileInputStream = new FileInputStream(n);
            try {
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                if (Integer.parseInt(properties.getProperty("fileStatus", i.a.ERROR_CODE_UNKNOW)) == 4) {
                    return -1;
                }
                if (properties.getProperty("previousChunk") == null) {
                    throw new ck("Slice checkpoint file corrupt.");
                }
                return Integer.parseInt(properties.getProperty("previousChunk")) + 1;
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
                throw th;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final em b() throws IOException {
        File n = this.f16785c.n(this.f16786d, this.f16787e, this.f16788f, this.f16789g);
        if (!n.exists()) {
            throw new ck("Slice checkpoint file does not exist.");
        }
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(n);
        try {
            properties.load(fileInputStream);
            fileInputStream.close();
            if (properties.getProperty("fileStatus") == null || properties.getProperty("previousChunk") == null) {
                throw new ck("Slice checkpoint file corrupt.");
            }
            try {
                int parseInt = Integer.parseInt(properties.getProperty("fileStatus"));
                String property = properties.getProperty("fileName");
                long parseLong = Long.parseLong(properties.getProperty("fileOffset", i.a.ERROR_CODE_UNKNOW));
                long parseLong2 = Long.parseLong(properties.getProperty("remainingBytes", i.a.ERROR_CODE_UNKNOW));
                int parseInt2 = Integer.parseInt(properties.getProperty("previousChunk"));
                this.f16790h = Integer.parseInt(properties.getProperty("metadataFileCounter", "0"));
                return new bp(parseInt, property, parseLong, parseLong2, parseInt2);
            } catch (NumberFormatException e2) {
                throw new ck("Slice checkpoint file corrupt.", e2);
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File c() {
        return new File(n(), String.format("%s-NAM.dat", Integer.valueOf(this.f16790h)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(InputStream inputStream, long j2) throws IOException {
        int read;
        RandomAccessFile randomAccessFile = new RandomAccessFile(c(), "rw");
        try {
            randomAccessFile.seek(j2);
            do {
                read = inputStream.read(this.f16784b);
                if (read > 0) {
                    randomAccessFile.write(this.f16784b, 0, read);
                }
            } while (read == 8192);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(long j2, byte[] bArr, int i2, int i3) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(c(), "rw");
        try {
            randomAccessFile.seek(j2);
            randomAccessFile.write(bArr, i2, i3);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void f(int i2) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "3");
        properties.put("fileOffset", String.valueOf(c().length()));
        properties.put("previousChunk", String.valueOf(i2));
        properties.put("metadataFileCounter", String.valueOf(this.f16790h));
        FileOutputStream fileOutputStream = new FileOutputStream(o());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void g(String str, long j2, long j3, int i2) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "1");
        properties.put("fileName", str);
        properties.put("fileOffset", String.valueOf(j2));
        properties.put("remainingBytes", String.valueOf(j3));
        properties.put("previousChunk", String.valueOf(i2));
        properties.put("metadataFileCounter", String.valueOf(this.f16790h));
        FileOutputStream fileOutputStream = new FileOutputStream(o());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void h(byte[] bArr, int i2) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "2");
        properties.put("previousChunk", String.valueOf(i2));
        properties.put("metadataFileCounter", String.valueOf(this.f16790h));
        FileOutputStream fileOutputStream = new FileOutputStream(o());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
            File m = this.f16785c.m(this.f16786d, this.f16787e, this.f16788f, this.f16789g);
            if (m.exists()) {
                m.delete();
            }
            fileOutputStream = new FileOutputStream(m);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.close();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (Throwable th) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th);
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void i(int i2) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", DynamicPageState.NOT_FOUND);
        properties.put("previousChunk", String.valueOf(i2));
        properties.put("metadataFileCounter", String.valueOf(this.f16790h));
        FileOutputStream fileOutputStream = new FileOutputStream(o());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void j(byte[] bArr) throws IOException {
        this.f16790h++;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(n(), String.format("%s-LFH.dat", Integer.valueOf(this.f16790h))));
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (IOException e2) {
            throw new ck("Could not write metadata file.", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void k(byte[] bArr, InputStream inputStream) throws IOException {
        this.f16790h++;
        FileOutputStream fileOutputStream = new FileOutputStream(c());
        try {
            fileOutputStream.write(bArr);
            int read = inputStream.read(this.f16784b);
            while (read > 0) {
                fileOutputStream.write(this.f16784b, 0, read);
                read = inputStream.read(this.f16784b);
            }
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void l(byte[] bArr, int i2, int i3) throws IOException {
        this.f16790h++;
        FileOutputStream fileOutputStream = new FileOutputStream(c());
        try {
            fileOutputStream.write(bArr, 0, i3);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean m() {
        File n = this.f16785c.n(this.f16786d, this.f16787e, this.f16788f, this.f16789g);
        if (n.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(n);
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("fileStatus") != null) {
                    return Integer.parseInt(properties.getProperty("fileStatus")) == 4;
                }
                f16783a.b("Slice checkpoint file corrupt while checking if extraction finished.", new Object[0]);
                return false;
            } catch (IOException e2) {
                f16783a.b("Could not read checkpoint while checking if extraction finished. %s", e2);
                return false;
            }
        }
        return false;
    }
}
