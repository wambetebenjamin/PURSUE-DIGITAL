package com.bytedance.apm.impl;

import com.bytedance.retrofit2.mime.TypedFile;
import com.bytedance.retrofit2.mime.TypedOutput;
import java.io.OutputStream;
import java.util.Map;
/* compiled from: MonitorFiledPart.java */
/* loaded from: classes.dex */
final class d implements TypedOutput {

    /* renamed from: a  reason: collision with root package name */
    private final TypedFile f1081a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, String> f1082b;

    public d(Map<String, String> map, TypedFile typedFile) {
        this.f1082b = map;
        this.f1081a = typedFile;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String fileName() {
        return a();
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String mimeType() {
        return this.f1081a.mimeType();
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public long length() {
        return this.f1081a.length();
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public void writeTo(OutputStream outputStream) {
        this.f1081a.writeTo(outputStream);
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String md5Stub() {
        return this.f1081a.md5Stub();
    }

    private String a() {
        StringBuilder append = new StringBuilder(this.f1081a.fileName()).append("\"");
        for (Map.Entry<String, String> entry : this.f1082b.entrySet()) {
            append.append("; ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        append.deleteCharAt(append.length() - 1);
        return append.toString();
    }
}
