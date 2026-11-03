package com.bytedance.ttgame.gecko;

import android.util.Pair;
import com.bytedance.geckox.net.b;
import com.bytedance.geckox.net.d;
import java.util.List;
/* loaded from: classes3.dex */
public class BoeNetWork extends b {
    @Override // com.bytedance.geckox.net.b, com.bytedance.geckox.net.c
    public d doGet(String str) {
        return super.doGet(str.replace("https", "http"));
    }

    @Override // com.bytedance.geckox.net.b, com.bytedance.geckox.net.c
    public d doPost(String str, String str2) {
        return super.doPost(str.replace("https", "http"), str2);
    }

    @Override // com.bytedance.geckox.net.b, com.bytedance.geckox.net.c
    public d doPost(String str, List<Pair<String, String>> list) {
        return super.doPost(str.replace("https", "http"), list);
    }
}
