package com.bytedance.crash.service;

import android.util.Pair;
import java.io.File;
import java.util.List;
import java.util.Map;
/* compiled from: IMultipartService.java */
/* loaded from: classes2.dex */
public interface d {
    Pair<Integer, String> a();

    void a(File file, String str, Map<String, String> map);

    void a(File file, Map<String, String> map);

    void a(String str, File file);

    void a(String str, String str2);

    void a(String str, List<File> list);

    void a(String str, byte[] bArr);
}
