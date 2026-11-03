package com.bytedance.geckox.lock;

import com.bytedance.geckox.utils.FileLock;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* compiled from: SelectChannelVersionLock.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9207a = "select.lock";

    /* renamed from: b  reason: collision with root package name */
    private static final Map<String, Lock> f9208b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private static ReentrantLock f9209c = new ReentrantLock();

    /* renamed from: d  reason: collision with root package name */
    private String f9210d;

    /* renamed from: e  reason: collision with root package name */
    private FileLock f9211e;

    private b(String str, FileLock fileLock) {
        this.f9210d = str;
        this.f9211e = fileLock;
    }

    public static b a(String str) {
        f9209c.lock();
        try {
            FileLock a2 = FileLock.a(str);
            Map<String, Lock> map = f9208b;
            Lock lock = map.get(str);
            if (lock == null) {
                lock = new ReentrantLock();
                map.put(str, lock);
            }
            lock.lock();
            return new b(str, a2);
        } catch (Throwable th) {
            f9209c.unlock();
            throw th;
        }
    }

    public void a() {
        try {
            this.f9211e.a();
            this.f9211e.b();
            Lock lock = f9208b.get(this.f9210d);
            if (lock == null) {
                return;
            }
            lock.unlock();
        } finally {
            f9209c.unlock();
        }
    }
}
