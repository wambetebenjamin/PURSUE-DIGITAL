package com.bytedance.crash.service;

import com.bytedance.crash.CrashType;
import java.io.File;
import org.json.JSONObject;
/* compiled from: ICrashBodyExtensionDumper.java */
/* loaded from: classes2.dex */
public interface a {
    JSONObject a(CrashType crashType);

    void a(File file);

    void a(JSONObject jSONObject, JSONObject jSONObject2, File file);
}
