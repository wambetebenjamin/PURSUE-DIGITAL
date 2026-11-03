package com.bytedance.common.jato.boost.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
/* compiled from: SimpleHandler.java */
/* loaded from: classes.dex */
public class c extends Handler {

    /* renamed from: a  reason: collision with root package name */
    private String f4579a;

    /* renamed from: b  reason: collision with root package name */
    private Handler f4580b;

    public Handler a() {
        return this.f4580b;
    }

    public c(Looper looper) {
        super(looper);
    }

    public final void a(String str, Handler handler) {
        this.f4580b = handler;
        this.f4579a = str + handler.getClass().getSimpleName();
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        Handler handler = this.f4580b;
        if (handler != null) {
            handler.handleMessage(message);
        }
    }

    @Override // android.os.Handler
    public String toString() {
        return this.f4579a;
    }
}
