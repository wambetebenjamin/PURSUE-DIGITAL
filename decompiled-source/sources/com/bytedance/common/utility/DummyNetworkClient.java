package com.bytedance.common.utility;

import android.util.Pair;
import com.bytedance.common.utility.k;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/* compiled from: NetworkClient.java */
/* loaded from: classes2.dex */
class DummyNetworkClient extends k {
    @Override // com.bytedance.common.utility.k
    public String get(String str, Map<String, String> map, k.a aVar) {
        throw new IOException("not implemented");
    }

    @Override // com.bytedance.common.utility.k
    public String post(String str, List<Pair<String, String>> list, Map<String, String> map, k.a aVar) {
        throw new d(0, "not implemented");
    }

    @Override // com.bytedance.common.utility.k
    public String post(String str, byte[] bArr, Map<String, String> map, k.a aVar) {
        throw new d(0, "not implemented");
    }
}
