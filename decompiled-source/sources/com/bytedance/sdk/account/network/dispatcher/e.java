package com.bytedance.sdk.account.network.dispatcher;
/* compiled from: IRequest.java */
/* loaded from: classes2.dex */
public interface e {

    /* compiled from: IRequest.java */
    /* loaded from: classes2.dex */
    public enum a {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    a getPriority();

    int getSequence();
}
