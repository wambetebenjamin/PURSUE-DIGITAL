package com.bytedance.push.helper;

import android.content.Context;
import android.os.Build;
import com.bytedance.push.utils.h;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;
/* compiled from: BDFileLockHelper.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11347a = "FileLockHelper";

    /* renamed from: e  reason: collision with root package name */
    private static final Map<String, a> f11348e = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final String f11349b;

    /* renamed from: c  reason: collision with root package name */
    private FileLock f11350c;

    /* renamed from: d  reason: collision with root package name */
    private RandomAccessFile f11351d;

    public static a a(String str) {
        Map<String, a> map = f11348e;
        a aVar = map.get(str);
        if (aVar == null) {
            synchronized (map) {
                aVar = map.get(str);
                if (aVar == null) {
                    aVar = new a(str);
                    map.put(str, aVar);
                }
            }
        }
        return aVar;
    }

    private a(String str) {
        this.f11349b = str;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0020 -> B:49:0x005b). Please submit an issue!!! */
    public boolean b() {
        FileLock fileLock = this.f11350c;
        if (fileLock != null) {
            try {
                try {
                    try {
                        fileLock.release();
                        if (Build.VERSION.SDK_INT >= 19) {
                            try {
                                this.f11350c.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        RandomAccessFile randomAccessFile = this.f11351d;
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                    } catch (Throwable th) {
                        if (Build.VERSION.SDK_INT >= 19) {
                            try {
                                this.f11350c.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        RandomAccessFile randomAccessFile2 = this.f11351d;
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e5) {
                    e5.printStackTrace();
                    if (Build.VERSION.SDK_INT >= 19) {
                        try {
                            this.f11350c.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                    }
                    RandomAccessFile randomAccessFile3 = this.f11351d;
                    if (randomAccessFile3 != null) {
                        randomAccessFile3.close();
                    }
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            return false;
        }
        return false;
    }

    public boolean a(Context context) {
        try {
            File file = new File(context.getFilesDir(), this.f11349b);
            if (file.isDirectory()) {
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            boolean a2 = a(file);
            if (h.debug()) {
                h.d(f11347a, "isFirstLockFile: sIsFirst = " + a2 + "  process = " + com.ss.android.message.util.b.getCurProcessName(context) + file.getPath());
            }
            return a2;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private boolean a(File file) {
        if (file.exists()) {
            boolean z = true;
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
                this.f11351d = randomAccessFile;
                FileLock lock = randomAccessFile.getChannel().lock();
                if (lock != null) {
                    this.f11350c = lock;
                }
                FileLock fileLock = this.f11350c;
                if (fileLock != null) {
                    return fileLock.isValid();
                }
                return false;
            } catch (Throwable th) {
                if (!(th instanceof IOException) || !th.getMessage().contains("fcntl failed: EAGAIN")) {
                    z = false;
                }
                if (!z) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }
}
