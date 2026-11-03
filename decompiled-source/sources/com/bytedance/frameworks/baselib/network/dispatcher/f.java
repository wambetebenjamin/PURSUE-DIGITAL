package com.bytedance.frameworks.baselib.network.dispatcher;
/* compiled from: IRequest.java */
/* loaded from: classes2.dex */
public interface f {

    /* compiled from: IRequest.java */
    /* loaded from: classes2.dex */
    public enum a {
        LOWEST,
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    a c();

    int d();
}
