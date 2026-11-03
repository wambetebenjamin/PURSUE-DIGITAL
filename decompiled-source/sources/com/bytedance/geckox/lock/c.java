package com.bytedance.geckox.lock;

import android.os.Process;
import android.util.Pair;
import com.bytedance.geckox.utils.FileLock;
import com.bytedance.geckox.utils.f;
import com.bytedance.geckox.utils.i;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
/* compiled from: UsingLock.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9212a = "using.lock";

    /* renamed from: b  reason: collision with root package name */
    private static final Map<String, Pair<FileLock, AtomicLong>> f9213b = new HashMap();

    public static void a(String str) {
        Map<String, Pair<FileLock, AtomicLong>> map = f9213b;
        synchronized (map) {
            Pair<FileLock, AtomicLong> pair = map.get(str);
            if (pair == null) {
                Pair<FileLock, AtomicLong> pair2 = new Pair<>(FileLock.a(str, Process.myPid()), new AtomicLong(0L));
                map.put(str, pair2);
                pair = pair2;
            }
            ((AtomicLong) pair.second).incrementAndGet();
        }
    }

    public static void b(String str) {
        Map<String, Pair<FileLock, AtomicLong>> map = f9213b;
        synchronized (map) {
            Pair<FileLock, AtomicLong> pair = map.get(str);
            if (pair == null) {
                throw new RuntimeException("using.lock illegal state");
            }
            int i2 = (((AtomicLong) pair.second).decrementAndGet() > 0L ? 1 : (((AtomicLong) pair.second).decrementAndGet() == 0L ? 0 : -1));
            if (i2 < 0) {
                throw new RuntimeException("using.lock count illegal");
            }
            if (i2 == 0) {
                ((FileLock) pair.first).a();
                map.remove(str);
            }
        }
    }

    public static void c(String str) {
        Map<String, Pair<FileLock, AtomicLong>> map = f9213b;
        synchronized (map) {
            FileLock b2 = FileLock.b(str);
            if (b2 == null) {
                return;
            }
            Pair<FileLock, AtomicLong> pair = map.get(str);
            if (pair != null && ((AtomicLong) pair.second).get() != 0) {
                b2.a();
                FileLock.a(str, Process.myPid());
                return;
            }
            File parentFile = new File(str).getParentFile();
            final File file = new File(parentFile.getAbsolutePath() + com.bytedance.geckox.utils.b.PENDING_DELETE);
            if (parentFile.renameTo(file)) {
                b2.a();
                b2.b();
                i.a().execute(new Runnable() { // from class: com.bytedance.geckox.lock.c.1
                    @Override // java.lang.Runnable
                    public void run() {
                        f.delete(file);
                    }
                });
            }
        }
    }
}
