package com.bytedance.services.apm.api;

import java.io.File;
import java.util.Map;
/* compiled from: IMultipartUploader.java */
/* loaded from: classes3.dex */
public interface n {
    d a();

    void a(String str, File file, String str2, Map<String, String> map);

    void a(String str, String str2, String str3, Map<String, String> map);

    void addFormField(String str, String str2);
}
