package com.bytedance.frameworks.baselib.network.http.util;

import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/* compiled from: AsyncIOTaskManager.java */
/* loaded from: classes2.dex */
public class q {

    /* renamed from: d  reason: collision with root package name */
    private static q f8369d = null;

    /* renamed from: e  reason: collision with root package name */
    private static final String f8370e = "q";

    /* renamed from: a  reason: collision with root package name */
    private ScheduledThreadPoolExecutor f8371a;

    /* renamed from: b  reason: collision with root package name */
    private ConcurrentHashMap<r, ScheduledFuture> f8372b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private ConcurrentHashMap<r, Runnable> f8373c = new ConcurrentHashMap<>();

    private q() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new s("ttnet-io"));
        this.f8371a = scheduledThreadPoolExecutor;
        scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
    }

    public static q a() {
        if (f8369d == null) {
            synchronized (q.class) {
                if (f8369d == null) {
                    f8369d = new q();
                }
            }
        }
        return f8369d;
    }

    public void a(r rVar) {
        ScheduledFuture<?> schedule;
        if (rVar == null) {
            return;
        }
        try {
            a aVar = new a(rVar);
            if (rVar.isLoop()) {
                schedule = this.f8371a.scheduleWithFixedDelay(aVar, rVar.getInitialDelay(), rVar.getLoopInterval(), TimeUnit.MILLISECONDS);
            } else {
                schedule = this.f8371a.schedule(aVar, rVar.getInitialDelay(), TimeUnit.MILLISECONDS);
            }
            this.f8373c.put(rVar, aVar);
            this.f8372b.put(rVar, schedule);
        } catch (Throwable th) {
            Logger.e(f8370e, "sendTask failed.", th);
        }
    }

    public void b(r rVar) {
        try {
            this.f8371a.remove(this.f8373c.remove(rVar));
            ScheduledFuture remove = this.f8372b.remove(rVar);
            if (remove != null) {
                remove.cancel(true);
            }
        } catch (Throwable th) {
            Logger.e(f8370e, "removeTask failed", th);
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Iterator<Map.Entry<r, Runnable>> it = this.f8373c.entrySet().iterator();
            while (it.hasNext()) {
                r key = it.next().getKey();
                if (str.equals(key.getTaskIdentifier())) {
                    it.remove();
                    this.f8371a.remove(key);
                }
            }
            Iterator<Map.Entry<r, ScheduledFuture>> it2 = this.f8372b.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<r, ScheduledFuture> next = it2.next();
                if (str.equals(next.getKey().getTaskIdentifier())) {
                    ScheduledFuture value = next.getValue();
                    if (value != null) {
                        value.cancel(true);
                    }
                    it2.remove();
                }
            }
        } catch (Throwable th) {
            Logger.e(f8370e, "removeTask failed", th);
        }
    }

    /* compiled from: AsyncIOTaskManager.java */
    /* loaded from: classes2.dex */
    private class a implements Runnable {

        /* renamed from: b  reason: collision with root package name */
        private r f8375b;

        private a(r rVar) {
            this.f8375b = rVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.f8375b.run();
                if (Logger.debug()) {
                    Logger.d(q.f8370e, "Task execute time: " + (System.currentTimeMillis() - currentTimeMillis));
                }
            } catch (Throwable th) {
                try {
                    Logger.e(q.f8370e, "thread " + Thread.currentThread().getName() + " exception", th);
                    if (this.f8375b.isLoop()) {
                    }
                } finally {
                    if (!this.f8375b.isLoop()) {
                        q.this.f8372b.remove(this.f8375b);
                        q.this.f8373c.remove(this.f8375b);
                    }
                }
            }
        }
    }
}
