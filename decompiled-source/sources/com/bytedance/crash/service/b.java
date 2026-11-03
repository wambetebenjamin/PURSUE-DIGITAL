package com.bytedance.crash.service;

import android.util.Pair;
import java.util.Map;
/* compiled from: IHttpService.java */
/* loaded from: classes2.dex */
public interface b {
    Pair<Integer, byte[]> a(String str, Map<String, String> map);

    Pair<Integer, byte[]> a(String str, byte[] bArr, Map<String, String> map);

    d a(String str, String str2, boolean z);
}
