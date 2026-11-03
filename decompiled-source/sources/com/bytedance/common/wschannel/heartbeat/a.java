package com.bytedance.common.wschannel.heartbeat;

import android.os.Handler;
import com.bytedance.common.wschannel.heartbeat.model.b;
/* compiled from: BaseHeartBeatPolicy.java */
/* loaded from: classes2.dex */
public abstract class a<T extends com.bytedance.common.wschannel.heartbeat.model.b> implements d {

    /* renamed from: a  reason: collision with root package name */
    protected T f5646a;

    public abstract void a(c cVar, Handler handler);

    public a(T t) {
        this.f5646a = t;
    }
}
