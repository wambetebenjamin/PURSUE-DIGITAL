package com.bytedance.apm.core;

import java.util.Map;
/* compiled from: IDynamicParams.java */
/* loaded from: classes.dex */
public interface c {
    Map<String, String> getCommonParams();

    String getSessionId();

    long getUid();
}
