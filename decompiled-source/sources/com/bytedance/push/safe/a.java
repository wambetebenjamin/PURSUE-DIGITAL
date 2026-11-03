package com.bytedance.push.safe;

import com.bytedance.push.g;
/* compiled from: IPassThoughMsgCache.java */
/* loaded from: classes2.dex */
public interface a extends com.ss.android.ug.bus.a {
    void cacheMsg(g gVar, int i2);

    int getMsgFrom(g gVar);

    g getRevokedMsg(long j2);

    boolean isFromPassThough(String str);
}
