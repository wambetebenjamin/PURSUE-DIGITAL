package com.google.androidgamesdk;

import android.view.Choreographer;
/* loaded from: classes6.dex */
public class ChoreographerCallback implements Choreographer.FrameCallback {
    private static final String LOG_TAG = "ChoreographerCallback";
    private long mCookie;
    private b mLooper;

    public ChoreographerCallback(long j2) {
        this.mCookie = j2;
        b bVar = new b();
        this.mLooper = bVar;
        bVar.start();
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j2) {
        nOnChoreographer(this.mCookie, j2);
    }

    public native void nOnChoreographer(long j2, long j3);

    public void postFrameCallback() {
        this.mLooper.f16888a.post(new a(this));
    }

    public void postFrameCallbackDelayed(long j2) {
        Choreographer.getInstance().postFrameCallbackDelayed(this, j2);
    }

    public void terminate() {
        this.mLooper.f16888a.getLooper().quit();
    }
}
