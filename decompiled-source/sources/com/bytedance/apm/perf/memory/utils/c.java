package com.bytedance.apm.perf.memory.utils;

import android.os.HandlerThread;
/* compiled from: PreparedHandlerThread.java */
@Deprecated
/* loaded from: classes.dex */
public class c extends HandlerThread {

    /* renamed from: a  reason: collision with root package name */
    private a f1348a;

    /* compiled from: PreparedHandlerThread.java */
    /* loaded from: classes.dex */
    public interface a {
        void onLooperPrepared();
    }

    public c(String str) {
        super(str);
    }

    public c(String str, int i2) {
        super(str, i2);
    }

    public void setPreparedCallBack(a aVar) {
        this.f1348a = aVar;
    }

    @Override // android.os.HandlerThread
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        synchronized (c.class) {
            a aVar = this.f1348a;
            if (aVar != null) {
                aVar.onLooperPrepared();
            }
        }
    }
}
