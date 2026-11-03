package com.bytedance.applog.sampling;

import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* compiled from: SamplingTeaHashDid.java */
/* loaded from: classes.dex */
public class i extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public i(int i2, Set<String> set, Map<String, List<Set<String>>> map, Map<String, List<Map<String, Object>>> map2) {
        super(i2, set, map, map2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.applog.sampling.a
    public boolean a(b bVar) {
        int a2 = a();
        if (a2 < 0) {
            return true;
        }
        if (a2 > 0 || bVar == null || TextUtils.isEmpty(bVar.a()) || TextUtils.isEmpty(bVar.b())) {
            return false;
        }
        return !com.bytedance.applog.sampling.util.c.a(bVar.a() + CertificateUtil.DELIMITER + bVar.b(), 10000L, this.f3347c);
    }
}
