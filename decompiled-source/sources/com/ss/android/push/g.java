package com.ss.android.push;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
/* compiled from: WeakHandler.java */
/* loaded from: classes9.dex */
public class g extends Handler {

    /* renamed from: a  reason: collision with root package name */
    WeakReference<a> f18467a;

    /* compiled from: WeakHandler.java */
    /* loaded from: classes9.dex */
    public interface a {
        void handleMsg(Message message);
    }

    public g(a aVar) {
        this.f18467a = new WeakReference<>(aVar);
    }

    public g(Looper looper, a aVar) {
        super(looper);
        this.f18467a = new WeakReference<>(aVar);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        a aVar = this.f18467a.get();
        if (aVar == null || message == null) {
            return;
        }
        aVar.handleMsg(message);
    }
}
