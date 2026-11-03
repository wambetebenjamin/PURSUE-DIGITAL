package com.bytedance.geckox.utils;

import android.os.Process;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class FileLock {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9356a = "gecko-file-lock";

    /* renamed from: b  reason: collision with root package name */
    private static final Map<String, Integer> f9357b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final int f9358c;

    /* renamed from: d  reason: collision with root package name */
    private final String f9359d;

    private static native int nGetFD(String str);

    private static native void nLockFile(int i2);

    private static native void nLockFileSegment(int i2, int i3);

    private static native void nRelease(int i2);

    private static native boolean nTryLock(int i2);

    private static native void nUnlockFile(int i2);

    static {
        j.loadLib("file_lock");
    }

    private FileLock(String str, int i2) {
        this.f9359d = str;
        this.f9358c = i2;
    }

    public static FileLock a(String str) {
        try {
            int d2 = d(str);
            nLockFile(d2);
            return new FileLock(str, d2);
        } catch (Exception e2) {
            throw new RuntimeException("lock failed, file:" + str + ", pid:" + Process.myPid() + " caused by:" + e2.getMessage());
        }
    }

    public static FileLock b(String str) {
        try {
            int d2 = d(str);
            if (nTryLock(d2)) {
                return new FileLock(str, d2);
            }
            return null;
        } catch (Exception e2) {
            throw new RuntimeException("try lock failed, file:" + str + " caused by:" + e2.getMessage());
        }
    }

    public static FileLock c(String str) {
        try {
            int d2 = d(str);
            if (!nTryLock(d2)) {
                new FileLock(str, d2).b();
                return null;
            }
            return new FileLock(str, d2);
        } catch (Exception e2) {
            throw new RuntimeException("try lock failed, file:" + str + " caused by:" + e2.getMessage());
        }
    }

    public static FileLock a(String str, int i2) {
        try {
            int d2 = d(str);
            nLockFileSegment(d2, i2);
            return new FileLock(str, d2);
        } catch (Exception e2) {
            throw new RuntimeException("lock segment failed, file:" + str + " caused by:" + e2.getMessage());
        }
    }

    private static int d(String str) {
        Integer num;
        Map<String, Integer> map = f9357b;
        synchronized (map) {
            num = map.get(str);
            if (num == null) {
                new File(str).getParentFile().mkdirs();
                num = Integer.valueOf(nGetFD(str));
                map.put(str, num);
            }
        }
        return num.intValue();
    }

    public void a() {
        try {
            nUnlockFile(this.f9358c);
        } catch (Exception unused) {
            throw new RuntimeException("release lock failed，path:" + this.f9359d);
        }
    }

    public void b() {
        Integer remove;
        Map<String, Integer> map = f9357b;
        synchronized (map) {
            remove = map.remove(this.f9359d);
        }
        try {
            nRelease(remove.intValue());
        } catch (Exception e2) {
            throw new RuntimeException("release lock failed, file:" + this.f9359d + " caused by:" + e2.getMessage());
        }
    }
}
