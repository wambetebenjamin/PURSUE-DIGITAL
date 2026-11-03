package com.bytedance.crash;

import org.json.JSONObject;
/* loaded from: classes2.dex */
public interface CrashUploadCallback {
    void a(CrashType crashType, JSONObject jSONObject);
}
