package com.bytedance.bdinstall;

import android.util.Pair;
import java.util.List;
import java.util.Map;
/* compiled from: INetworkClient.java */
/* loaded from: classes.dex */
public interface ai {
    String get(String str, Map<String, String> map);

    String post(String str, List<Pair<String, String>> list);

    String post(String str, byte[] bArr, String str2);

    String post(String str, byte[] bArr, Map<String, String> map);

    byte[] postStream(String str, byte[] bArr, Map<String, String> map);
}
