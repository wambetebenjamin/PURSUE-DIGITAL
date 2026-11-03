package com.bytedance.geckox.lock;

import com.bytedance.geckox.utils.FileLock;
import com.bytedance.geckox.utils.e;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* compiled from: ChannelUpdateLock.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9203a = "update.lock";

    /* renamed from: b  reason: collision with root package name */
    private static final Map<String, Lock> f9204b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private FileLock f9205c;

    /* renamed from: d  reason: collision with root package name */
    private String f9206d;

    private a(String str, FileLock fileLock) {
        this.f9206d = str;
        this.f9205c = fileLock;
    }

    public static a a(String str) {
        Map<String, Lock> map = f9204b;
        synchronized (map) {
            Lock lock = map.get(str);
            if (lock == null) {
                lock = new ReentrantLock();
                map.put(str, lock);
            }
            if (lock.tryLock()) {
                try {
                    FileLock c2 = FileLock.c(str);
                    if (c2 == null) {
                        lock.unlock();
                        return null;
                    }
                    return new a(str, c2);
                } catch (Exception e2) {
                    lock.lock();
                    e.a(new RuntimeException(e2));
                    return null;
                }
            }
            return null;
        }
    }

    public void a() {
        Map<String, Lock> map = f9204b;
        synchronized (map) {
            this.f9205c.a();
            this.f9205c.b();
            map.get(this.f9206d).unlock();
        }
    }
}
