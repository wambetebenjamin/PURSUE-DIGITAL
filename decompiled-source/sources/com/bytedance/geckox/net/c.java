package com.bytedance.geckox.net;

import android.util.Pair;
import java.util.List;
/* compiled from: INetWork.java */
/* loaded from: classes2.dex */
public interface c {
    d doGet(String str);

    d doPost(String str, String str2);

    d doPost(String str, List<Pair<String, String>> list);

    void downloadFile(String str, long j2, com.bytedance.geckox.buffer.stream.b bVar);
}
