package com.bytedance.apm.data;

import org.json.JSONObject;
/* compiled from: ITypeData.java */
/* loaded from: classes.dex */
public interface b {
    String getSubTypeLabel();

    String getTypeLabel();

    boolean isSampled(JSONObject jSONObject);

    boolean isSaveImmediately();

    boolean isUploadImmediately();

    JSONObject packLog();

    boolean supportFetch();
}
