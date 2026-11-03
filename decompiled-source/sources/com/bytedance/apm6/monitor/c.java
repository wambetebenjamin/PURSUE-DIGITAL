package com.bytedance.apm6.monitor;

import org.json.JSONObject;
/* compiled from: Monitorable.java */
/* loaded from: classes.dex */
public interface c {
    String getLogType();

    boolean isValid();

    JSONObject toJsonObject();
}
