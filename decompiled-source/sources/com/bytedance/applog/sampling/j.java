package com.bytedance.applog.sampling;

import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* compiled from: SamplingTeaHashUtUid.java */
/* loaded from: classes.dex */
public class j extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public j(int i2, Set<String> set, Map<String, List<Set<String>>> map, Map<String, List<Map<String, Object>>> map2) {
        super(i2, set, map, map2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.bytedance.applog.sampling.a
    public boolean a(b bVar) {
        int a2 = a();
        if (a2 < 0) {
            return true;
        }
        if (a2 <= 0 && bVar != null) {
            String a3 = bVar.a();
            if (TextUtils.isEmpty(a3)) {
                return false;
            }
            String d2 = bVar.d();
            if (TextUtils.isEmpty(d2)) {
                return false;
            }
            String e2 = bVar.e();
            if (TextUtils.isEmpty(e2)) {
                return false;
            }
            return !com.bytedance.applog.sampling.util.c.a(a3 + CertificateUtil.DELIMITER + e2 + "_" + d2, 10000L, this.f3347c);
        }
        return false;
    }
}
