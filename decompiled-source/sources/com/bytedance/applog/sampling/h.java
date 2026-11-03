package com.bytedance.applog.sampling;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
/* compiled from: SamplingRandom.java */
/* loaded from: classes.dex */
public class h extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public h(int i2, Set<String> set, Map<String, List<Set<String>>> map, Map<String, List<Map<String, Object>>> map2) {
        super(i2, set, map, map2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.applog.sampling.a
    public boolean a(b bVar) {
        int a2 = a();
        if (a2 < 0) {
            return true;
        }
        return a2 <= 0 && new Random().nextInt(10000) >= this.f3347c;
    }
}
