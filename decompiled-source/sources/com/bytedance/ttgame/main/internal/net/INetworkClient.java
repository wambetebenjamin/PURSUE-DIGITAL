package com.bytedance.ttgame.main.internal.net;

import android.util.Pair;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public interface INetworkClient {
    String get(String str, Map<String, String> map) throws Exception;

    String post(String str, List<Pair<String, String>> list, Map<String, String> map);

    String post(String str, byte[] bArr, Map<String, String> map) throws Exception;

    byte[] postDataStream(String str, byte[] bArr, Map<String, String> map, boolean z) throws Exception;
}
