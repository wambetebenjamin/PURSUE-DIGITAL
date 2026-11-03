package com.ixigua.touchtileimageview.drawable;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BitmapRegionDecoderDrawable.java */
/* loaded from: classes9.dex */
public class b extends Handler {

    /* renamed from: a  reason: collision with root package name */
    private boolean f17072a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Looper looper) {
        super(looper);
        this.f17072a = true;
        if (Build.VERSION.SDK_INT < 16) {
            this.f17072a = false;
        } else if (!this.f17072a || Build.VERSION.SDK_INT >= 22) {
        } else {
            Message obtain = Message.obtain();
            try {
                obtain.setAsynchronous(true);
            } catch (NoSuchMethodError unused) {
                this.f17072a = false;
            }
            obtain.recycle();
        }
    }

    public void postAsyncIfNeeded(Runnable runnable) {
        Message obtain = Message.obtain(this, runnable);
        if (this.f17072a) {
            obtain.setAsynchronous(true);
        }
        sendMessage(obtain);
    }
}
