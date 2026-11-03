package com.bytedance.common.process.cross;

import java.util.List;
/* compiled from: IMethodObserver.java */
/* loaded from: classes2.dex */
public interface c {
    String getMethodName();

    String onMethodCall(com.bytedance.common.model.b bVar, List list);
}
