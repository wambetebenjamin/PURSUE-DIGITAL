package com.google.androidgamesdk;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
/* loaded from: classes6.dex */
final class b extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public Handler f16888a;

    private b() {
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Log.i("ChoreographerCallback", "Starting looper thread");
        Looper.prepare();
        this.f16888a = new Handler();
        Looper.loop();
        Log.i("ChoreographerCallback", "Terminating looper thread");
    }
}
