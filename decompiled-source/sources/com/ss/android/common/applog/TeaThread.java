package com.ss.android.common.applog;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayList;
import java.util.LinkedList;
/* loaded from: classes9.dex */
public class TeaThread extends HandlerThread {
    private static final int LIMIT_EVENT_SIZE = 1000;
    private static final String TAG = "TeaThread";
    private static volatile TeaThread sInst;
    private final LinkedList<Runnable> afterLooperPrepared;
    private volatile boolean isLooperPrepared;
    private final Object lock;
    private Handler mHandler;

    private TeaThread() {
        super(TAG);
        this.lock = new Object();
        this.isLooperPrepared = false;
        this.afterLooperPrepared = new LinkedList<>();
    }

    private TeaThread(String str) {
        super(str);
        this.lock = new Object();
        this.isLooperPrepared = false;
        this.afterLooperPrepared = new LinkedList<>();
    }

    public static TeaThread getInst() {
        if (sInst == null) {
            synchronized (TeaThread.class) {
                if (sInst == null) {
                    sInst = new TeaThread();
                    sInst.start();
                }
            }
        }
        return sInst;
    }

    public static TeaThread createNewThread(String str) {
        return new TeaThread(str);
    }

    @Override // android.os.HandlerThread
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        synchronized (this.lock) {
            this.isLooperPrepared = true;
            ArrayList<Runnable> arrayList = new ArrayList(this.afterLooperPrepared);
            this.afterLooperPrepared.clear();
            if (!arrayList.isEmpty()) {
                for (Runnable runnable : arrayList) {
                    post(runnable);
                }
            }
        }
    }

    public void ensureTeaThreadLite(Runnable runnable) {
        ensureTeaThreadLiteDelay(runnable, 0L);
    }

    public void ensureTeaThreadLiteDelay(Runnable runnable, long j2) {
        if (runnable == null) {
            return;
        }
        if (this.isLooperPrepared) {
            postDelay(runnable, j2);
            return;
        }
        synchronized (this.lock) {
            if (this.isLooperPrepared) {
                postDelay(runnable, j2);
            } else {
                if (this.afterLooperPrepared.size() > 1000) {
                    this.afterLooperPrepared.poll();
                }
                this.afterLooperPrepared.add(runnable);
            }
        }
    }

    public void post(Runnable runnable) {
        if (runnable != null) {
            getTeaHandler().post(runnable);
        }
    }

    public void repost(Runnable runnable) {
        if (runnable != null) {
            removeCallbacks(runnable);
            post(runnable);
        }
    }

    public void repost(Runnable runnable, long j2) {
        if (runnable != null) {
            removeCallbacks(runnable);
            postDelay(runnable, j2);
        }
    }

    public void postDelay(Runnable runnable, long j2) {
        if (runnable != null) {
            getTeaHandler().postDelayed(runnable, j2);
        }
    }

    public void removeCallbacks(Runnable runnable) {
        getTeaHandler().removeCallbacks(runnable);
    }

    public void ensureTeaThread(Runnable runnable) {
        ensureTeaThreadLite(runnable);
    }

    public Handler getTeaHandler() {
        if (this.mHandler == null) {
            synchronized (this) {
                if (this.mHandler == null) {
                    this.mHandler = new Handler(getLooper());
                }
            }
        }
        return this.mHandler;
    }

    public Handler createTeaHandler() {
        return new Handler(getLooper());
    }
}
