package com.bytedancehttpdns.httpdns.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
/* loaded from: classes8.dex */
public class WeakHandler extends Handler {
    WeakReference<IHandler> mRef;

    /* loaded from: classes8.dex */
    public interface IHandler {
        void handleMsg(Message message);
    }

    public WeakHandler(IHandler iHandler) {
        this(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper(), iHandler);
    }

    public WeakHandler(Looper looper, IHandler iHandler) {
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
