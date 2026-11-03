package com.bytedance.sdk.account.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
/* compiled from: WeakHandler.java */
/* loaded from: classes2.dex */
public class m extends Handler {

    /* renamed from: a  reason: collision with root package name */
    WeakReference<a> f13096a;

    /* compiled from: WeakHandler.java */
    /* loaded from: classes2.dex */
    public interface a {
        void handleMsg(Message message);
    }

    public m(a aVar) {
        this.f13096a = new WeakReference<>(aVar);
    }

    public m(Looper looper, a aVar) {
        super(looper);
        this.f13096a = new WeakReference<>(aVar);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        a aVar = this.f13096a.get();
        if (aVar == null || message == null) {
            return;
        }
        aVar.handleMsg(message);
    }
}
