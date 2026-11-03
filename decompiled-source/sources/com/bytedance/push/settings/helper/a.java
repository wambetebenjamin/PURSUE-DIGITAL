package com.bytedance.push.settings.helper;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
/* compiled from: SettingsExclusiveFileLock.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11770a = "SettingsExclusiveFileLock";

    /* renamed from: b  reason: collision with root package name */
    private final String f11771b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f11772c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f11773d;

    /* renamed from: e  reason: collision with root package name */
    private FileLock f11774e;

    /* renamed from: f  reason: collision with root package name */
    private RandomAccessFile f11775f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f11776g;

    public a(String str, boolean z) {
        this.f11771b = str;
        this.f11776g = z;
    }

    public a(String str) {
        this.f11771b = str;
    }

    public boolean tryLockFile(Context context) {
        return isFirstLockFile(context);
    }

    public boolean isFirstLockFile(Context context) {
        try {
            if (this.f11772c) {
                return this.f11773d;
            }
            this.f11772c = true;
            File file = new File(context.getFilesDir(), this.f11771b);
            if (file.isDirectory()) {
                file.delete();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            this.f11773d = a(file);
            com.bytedance.push.settings.logger.b.a().a(f11770a, "isFirstLockFile: sIsFirst = " + this.f11773d + "  process = " + com.bytedance.push.settings.utils.a.getCurProcessName(context) + file.getPath());
            return this.f11773d;
        } catch (Throwable th) {
            th.printStackTrace();
            this.f11773d = false;
            return false;
        }
    }

    private boolean a(File file) {
        boolean z = false;
        if (file.exists()) {
            FileLock fileLock = null;
            boolean z2 = true;
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
                this.f11775f = randomAccessFile;
                fileLock = randomAccessFile.getChannel().tryLock();
                if (fileLock != null) {
                    this.f11774e = fileLock;
                }
                FileLock fileLock2 = this.f11774e;
                if (fileLock2 != null) {
                    if (fileLock2.isValid()) {
                        z = true;
                    }
                }
                return z;
            } catch (Throwable th) {
                try {
                    if (!(th instanceof IOException) || !th.getMessage().contains("fcntl failed: EAGAIN")) {
                        z2 = false;
                    }
                    if (!z2) {
                        th.printStackTrace();
                    }
                    if (this.f11776g && fileLock == null) {
                        a(this.f11775f);
                    }
                    return false;
                } finally {
                    if (this.f11776g && fileLock == null) {
                        a(this.f11775f);
                    }
                }
            }
        }
        return false;
    }

    private void a(final RandomAccessFile randomAccessFile) {
        new Thread(new Runnable() { // from class: com.bytedance.push.settings.helper.a.1
            @Override // java.lang.Runnable
            public void run() {
                if (randomAccessFile == null) {
                    return;
                }
                try {
                    com.bytedance.push.settings.logger.b.a().a(a.f11770a, "pending lock file , mNeedMonitorLockRelease is " + a.this.f11776g);
                    FileLock lock = randomAccessFile.getChannel().lock();
                    if (lock != null) {
                        a.this.f11774e = lock;
                    }
                    com.bytedance.push.settings.logger.b.a().a(a.f11770a, "lock file success!" + ((a.this.f11774e == null || !a.this.f11774e.isValid() || a.this.f11774e.isShared()) ? false : true));
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }).start();
    }

    public void releaseLockFile() {
        try {
            this.f11774e.release();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
