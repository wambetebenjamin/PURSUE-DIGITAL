package com.bytedance.sdk.account.network.dispatcher;

import android.os.Looper;
import android.os.Message;
import com.bytedance.common.utility.collection.WeakHandler;
import com.bytedance.common.utility.o;
import com.bytedance.sdk.account.network.dispatcher.e;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: ApiThread.java */
/* loaded from: classes2.dex */
public abstract class c implements WeakHandler.IHandler, e, Comparable<e>, Runnable {
    public static final int ENQUEUE_EXPIRE = 1000;

    /* renamed from: d  reason: collision with root package name */
    private static final int f12657d = 0;

    /* renamed from: e  reason: collision with root package name */
    private static final int f12658e = 1;

    /* renamed from: f  reason: collision with root package name */
    private static f f12659f = f.getDefaultRequestQueue();

    /* renamed from: c  reason: collision with root package name */
    protected final e.a f12662c;

    /* renamed from: h  reason: collision with root package name */
    private int f12664h;

    /* renamed from: i  reason: collision with root package name */
    private final String f12665i;

    /* renamed from: a  reason: collision with root package name */
    protected final AtomicBoolean f12660a = new AtomicBoolean(false);

    /* renamed from: b  reason: collision with root package name */
    protected final AtomicBoolean f12661b = new AtomicBoolean(false);

    /* renamed from: g  reason: collision with root package name */
    private WeakHandler f12663g = new WeakHandler(Looper.getMainLooper(), this);

    public boolean isDownload() {
        return false;
    }

    public boolean needTryLocal() {
        return false;
    }

    public void run() {
    }

    public boolean run4Local() {
        return false;
    }

    public static void setRequestQueue(f fVar) {
        f12659f = fVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public c(String str, e.a aVar) {
        this.f12662c = aVar;
        this.f12665i = o.isEmpty(str) ? getClass().getSimpleName() : str;
    }

    public void cancel() {
        this.f12661b.compareAndSet(false, true);
    }

    public boolean isCanceled() {
        return this.f12661b.get();
    }

    public String getName() {
        return this.f12665i;
    }

    public final void start() {
        if (this.f12660a.compareAndSet(false, true)) {
            if (f12659f == null) {
                f12659f = f.getDefaultRequestQueue();
            }
            if (isDownload()) {
                f12659f.addDownload(this);
            } else {
                f12659f.add(this);
            }
        }
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.e
    public e.a getPriority() {
        return this.f12662c;
    }

    @Override // com.bytedance.sdk.account.network.dispatcher.e
    public int getSequence() {
        return this.f12664h;
    }

    public final c setSequence(int i2) {
        this.f12664h = i2;
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(e eVar) {
        e.a priority = getPriority();
        e.a priority2 = eVar.getPriority();
        if (priority == null) {
            priority = e.a.NORMAL;
        }
        if (priority2 == null) {
            priority2 = e.a.NORMAL;
        }
        if (priority == priority2) {
            return getSequence() - eVar.getSequence();
        }
        return priority2.ordinal() - priority.ordinal();
    }

    @Override // com.bytedance.common.utility.collection.WeakHandler.IHandler
    public void handleMsg(Message message) {
        if (message == null) {
            return;
        }
        try {
            int i2 = message.what;
            if (i2 == 0) {
                f12659f.handleExpandRequestQueueSize();
            } else if (i2 == 1) {
                f12659f.handleExpandDownloadRequestQueueSize();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void sendEnQueueExpireMsg() {
        cancelEnQueueExpireMsg();
        this.f12663g.sendEmptyMessageDelayed(0, 1000L);
    }

    public void cancelEnQueueExpireMsg() {
        this.f12663g.removeMessages(0);
    }

    public void sendEnDownloadQueueExpireMsg() {
        cancelEnDownloadQueueExpireMsg();
        this.f12663g.sendEmptyMessageDelayed(1, 1000L);
    }

    public void cancelEnDownloadQueueExpireMsg() {
        this.f12663g.removeMessages(1);
    }
}
