package com.bytedance.bdinstall;

import java.util.HashMap;
import java.util.Map;
/* compiled from: AbsDeviceTokenService.java */
/* loaded from: classes.dex */
abstract class b implements s {
    protected abstract String b();

    @Override // com.bytedance.bdinstall.s
    public Map<String, String> a() {
        String b2 = b();
        HashMap hashMap = new HashMap();
        if (b2 == null) {
            b2 = "";
        }
        hashMap.put("x-tt-dt", b2);
        return hashMap;
    }
}
