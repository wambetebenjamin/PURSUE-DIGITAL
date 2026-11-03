package com.ttnet.org.chromium.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
/* loaded from: classes9.dex */
public class TTWeakHandler extends Handler {
    WeakReference<IHandler> mRef;

    /* loaded from: classes9.dex */
    public interface IHandler {
        void handleMsg(Message message);
    }

    public TTWeakHandler(IHandler iHandler) {
        this.mRef = new WeakReference<>(iHandler);
    }

    public TTWeakHandler(Looper looper, IHandler iHandler) {
        super(looper);
        this.mRef = new WeakReference<>(iHandler);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        IHandler iHandler = this.mRef.get();
        if (iHandler == null || message == null) {
            return;
        }
        iHandler.handleMsg(message);
    }
}
