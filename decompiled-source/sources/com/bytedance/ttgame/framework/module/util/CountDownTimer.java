package com.bytedance.ttgame.framework.module.util;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
/* loaded from: classes3.dex */
public abstract class CountDownTimer {
    private static final int MSG = 1;
    private final long mCountdownInterval;
    private final long mMillisInFuture;
    private long mStopTimeInFuture;
    private boolean mCancelled = false;
    private Handler mHandler = new Handler() { // from class: com.bytedance.ttgame.framework.module.util.CountDownTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            long j2;
            synchronized (CountDownTimer.this) {
                if (CountDownTimer.this.mCancelled) {
                    return;
                }
                long elapsedRealtime = CountDownTimer.this.mStopTimeInFuture - SystemClock.elapsedRealtime();
                long j3 = 0;
                if (elapsedRealtime <= 0) {
                    CountDownTimer.this.onFinish();
                } else {
                    long elapsedRealtime2 = SystemClock.elapsedRealtime();
                    CountDownTimer.this.onTick(elapsedRealtime);
                    long elapsedRealtime3 = SystemClock.elapsedRealtime() - elapsedRealtime2;
                    if (elapsedRealtime < CountDownTimer.this.mCountdownInterval) {
                        j2 = elapsedRealtime - elapsedRealtime3;
                        if (j2 < 0) {
                            sendMessageDelayed(obtainMessage(1), j3);
                        }
                    } else {
                        j2 = CountDownTimer.this.mCountdownInterval - elapsedRealtime3;
                        while (j2 < 0) {
                            j2 += CountDownTimer.this.mCountdownInterval;
                        }
                    }
                    j3 = j2;
                    sendMessageDelayed(obtainMessage(1), j3);
                }
            }
        }
    };

    public abstract void onFinish();

    public abstract void onTick(long j2);

    public CountDownTimer(long j2, long j3) {
        this.mMillisInFuture = j2;
        this.mCountdownInterval = j3;
    }

    public final synchronized void cancel() {
        this.mCancelled = true;
        this.mHandler.removeMessages(1);
    }

    public final synchronized CountDownTimer start() {
        this.mCancelled = false;
        if (this.mMillisInFuture <= 0) {
            onFinish();
            return this;
        }
        this.mStopTimeInFuture = SystemClock.elapsedRealtime() + this.mMillisInFuture;
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1));
        return this;
    }
}
