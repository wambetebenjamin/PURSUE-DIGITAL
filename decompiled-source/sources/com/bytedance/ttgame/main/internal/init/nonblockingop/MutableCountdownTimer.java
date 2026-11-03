package com.bytedance.ttgame.main.internal.init.nonblockingop;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
/* loaded from: classes3.dex */
public abstract class MutableCountdownTimer {
    private final long mCountdownInterval;
    private final long mMillisInFuture;
    private long mStopTimeInFuture;
    private boolean mCancelled = false;
    private boolean mRunning = false;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.bytedance.ttgame.main.internal.init.nonblockingop.MutableCountdownTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            long j2;
            synchronized (MutableCountdownTimer.this) {
                if (!MutableCountdownTimer.this.mCancelled) {
                    long elapsedRealtime = MutableCountdownTimer.this.mStopTimeInFuture - SystemClock.elapsedRealtime();
                    long j3 = 0;
                    if (elapsedRealtime <= 0) {
                        MutableCountdownTimer.this.onFinish();
                        MutableCountdownTimer.this.mRunning = false;
                    } else {
                        long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        MutableCountdownTimer.this.onTick(elapsedRealtime);
                        long elapsedRealtime3 = SystemClock.elapsedRealtime() - elapsedRealtime2;
                        if (elapsedRealtime < MutableCountdownTimer.this.mCountdownInterval) {
                            j2 = elapsedRealtime - elapsedRealtime3;
                            if (j2 < 0) {
                                sendMessageDelayed(obtainMessage(1), j3);
                            }
                        } else {
                            j2 = MutableCountdownTimer.this.mCountdownInterval - elapsedRealtime3;
                            while (j2 < 0) {
                                j2 += MutableCountdownTimer.this.mCountdownInterval;
                            }
                        }
                        j3 = j2;
                        sendMessageDelayed(obtainMessage(1), j3);
                    }
                }
            }
        }
    };

    public abstract void onFinish();

    public abstract void onTick(long j2);

    public MutableCountdownTimer(long j2, long j3) {
        this.mMillisInFuture = j2;
        this.mCountdownInterval = j3;
    }

    public final synchronized void cancel() {
        this.mCancelled = true;
        this.mRunning = false;
        this.mHandler.removeMessages(1);
    }

    public final synchronized MutableCountdownTimer start() {
        this.mCancelled = false;
        this.mRunning = true;
        if (this.mMillisInFuture <= 0) {
            onFinish();
            this.mRunning = false;
        } else {
            this.mStopTimeInFuture = SystemClock.elapsedRealtime() + this.mMillisInFuture;
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(1));
        }
        return this;
    }

    public synchronized void addMillisInFuture(long j2) {
        if (this.mRunning) {
            this.mStopTimeInFuture = SystemClock.elapsedRealtime() + j2;
        }
    }

    public boolean isRunning() {
        return this.mRunning;
    }
}
