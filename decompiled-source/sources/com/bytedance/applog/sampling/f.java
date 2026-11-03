package com.bytedance.applog.sampling;

import android.text.TextUtils;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.CRC32;
/* compiled from: SamplingCrc32Did.java */
/* loaded from: classes.dex */
public class f extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public f(int i2, Set<String> set, Map<String, List<Set<String>>> map, Map<String, List<Map<String, Object>>> map2) {
        super(i2, set, map, map2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.applog.sampling.a
    public boolean a(b bVar) {
        int a2 = a();
        if (a2 < 0) {
            return true;
        }
        if (a2 > 0 || bVar == null || TextUtils.isEmpty(bVar.b())) {
            return false;
        }
        CRC32 crc32 = new CRC32();
        crc32.update(bVar.b().getBytes());
        return crc32.getValue() % 10000 >= ((long) this.f3347c);
    }
}
