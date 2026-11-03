package com.bytedance.apm.impl;

import android.text.TextUtils;
import com.bytedance.retrofit2.mime.TypedOutput;
import java.io.OutputStream;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: MonitorAdditionalPart.java */
/* loaded from: classes.dex */
final class c implements TypedOutput {

    /* renamed from: a  reason: collision with root package name */
    private final String f1078a;

    /* renamed from: b  reason: collision with root package name */
    private Map<String, String> f1079b;

    /* renamed from: c  reason: collision with root package name */
    private final String f1080c;

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String md5Stub() {
        return null;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String mimeType() {
        return "text/plain";
    }

    public c(String str, String str2, Map<String, String> map) {
        this.f1078a = str;
        this.f1079b = map;
        if (TextUtils.isEmpty(str2)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("defaultData", "none commonParams");
            } catch (JSONException unused) {
            }
            this.f1080c = jSONObject.toString();
            return;
        }
        this.f1080c = str2;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String fileName() {
        return a(this.f1078a, this.f1079b);
    }

    private String a(String str, Map<String, String> map) {
        StringBuilder append = new StringBuilder(str).append("\"");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            append.append("; ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        append.deleteCharAt(append.length() - 1);
        return append.toString();
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public long length() {
        return this.f1080c.getBytes().length;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public void writeTo(OutputStream outputStream) {
        outputStream.write(this.f1080c.getBytes());
    }
}
