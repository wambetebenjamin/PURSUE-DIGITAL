package com.bytedance.sdk.account.network.dispatcher;

import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.common.utility.collection.WeakHandler;
import com.bytedance.common.utility.o;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ApiDispatcher.java */
/* loaded from: classes2.dex */
public class a extends Thread implements WeakHandler.IHandler {
    public static final int SHRINK_EXPIRE = 2000;

    /* renamed from: a  reason: collision with root package name */
    protected static final int f12644a = 0;

    /* renamed from: b  reason: collision with root package name */
    protected static f f12645b = f.getDefaultRequestQueue();

    /* renamed from: c  reason: collision with root package name */
    protected static final AtomicInteger f12646c = new AtomicInteger();

    /* renamed from: e  reason: collision with root package name */
    private static final String f12647e = "ApiDispatcher";

    /* renamed from: d  reason: collision with root package name */
    protected WeakHandler f12648d;

    /* renamed from: f  reason: collision with root package name */
    private final BlockingQueue<e> f12649f;

    /* renamed from: g  reason: collision with root package name */
    private volatile boolean f12650g;

    /* renamed from: h  reason: collision with root package name */
    private volatile boolean f12651h;

    /* renamed from: i  reason: collision with root package name */
    private String f12652i;

    public a(BlockingQueue<e> blockingQueue, String str, String str2) {
        super(TextUtils.isEmpty(str) ? "ApiDispatcher-Thread" : str);
        this.f12648d = new WeakHandler(Looper.getMainLooper(), this);
        this.f12650g = false;
        this.f12651h = false;
        this.f12652i = f12647e;
        this.f12649f = blockingQueue;
        this.f12652i = str2;
    }

    public void quit() {
        this.f12650g = true;
        interrupt();
    }

    public boolean isRunning() {
        return this.f12651h;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Process.setThreadPriority(10);
        while (true) {
            try {
                e take = this.f12649f.take();
                cancelShrinkExpireMsg();
                if (take != null && (take instanceof c)) {
                    a((c) take);
                }
            } catch (InterruptedException unused) {
                if (this.f12650g) {
                    return;
                }
            }
        }
    }

    private void a(c cVar) {
        String str;
        String str2 = null;
        try {
            this.f12651h = true;
            cancelEnQueueExpireMsg(cVar);
        } catch (Throwable th) {
            th = th;
            str = null;
        }
        if (cVar.isCanceled()) {
            this.f12651h = false;
            return;
        }
        String str3 = Thread.currentThread().getName();
        try {
            str2 = cVar.getName();
            if (!o.isEmpty(str2) && !o.isEmpty(str3)) {
                Thread.currentThread().setName(str2);
            }
            com.ss.android.e.b(this.f12652i, "thread (inc) count: " + f12646c.incrementAndGet());
            cVar.run();
            sendShrinkExpireMsg();
        } catch (Throwable th2) {
            th = th2;
            String str4 = str2;
            str2 = str3;
            str = str4;
            com.ss.android.e.b(this.f12652i, "processLegacyApiThread: ", th);
            String str5 = str2;
            str2 = str;
            str3 = str5;
            this.f12651h = false;
            if (!o.isEmpty(str2)) {
                Thread.currentThread().setName(str3);
            }
            com.ss.android.e.b(this.f12652i, "thread (dec) count: " + f12646c.decrementAndGet());
        }
        this.f12651h = false;
        if (!o.isEmpty(str2) && !o.isEmpty(str3)) {
            Thread.currentThread().setName(str3);
        }
        com.ss.android.e.b(this.f12652i, "thread (dec) count: " + f12646c.decrementAndGet());
    }

    @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
    public void handleMsg(Message message) {
        if (message == null) {
            return;
        }
        try {
            if (message.what != 0) {
                return;
            }
            f12645b.handleShrinkRequestQueueSize();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void cancelEnQueueExpireMsg(c cVar) {
        if (cVar != null) {
            cVar.cancelEnQueueExpireMsg();
        }
    }

    public void sendShrinkExpireMsg() {
        cancelShrinkExpireMsg();
        this.f12648d.sendEmptyMessageDelayed(0, 2000L);
    }

    public void cancelShrinkExpireMsg() {
        this.f12648d.removeMessages(0);
    }
}
