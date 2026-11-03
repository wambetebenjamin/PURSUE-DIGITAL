package com.ss.android.common.applog;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.bdinstall.ai;
import com.bytedance.bdinstall.au;
import com.bytedance.common.utility.d;
import com.bytedance.common.utility.k;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public class BDTrackerNetworkClientWrapper implements ai {
    @Override // com.bytedance.bdinstall.ai
    public String get(String str, Map<String, String> map) {
        try {
            return k.getDefault().get(str, map, null);
        } catch (Exception e2) {
            if (e2 instanceof d) {
                d dVar = (d) e2;
                throw new au(dVar.getResponseCode(), dVar.getCause());
            }
            throw new au(400, e2.getCause());
        }
    }

    @Override // com.bytedance.bdinstall.ai
    public String post(String str, byte[] bArr, Map<String, String> map) {
        try {
            return k.getDefault().post(str, bArr, map, (k.a) null);
        } catch (d e2) {
            throw new au(e2.getResponseCode(), e2.getCause());
        }
    }

    @Override // com.bytedance.bdinstall.ai
    public String post(String str, byte[] bArr, String str2) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("Content-Type", str2);
        }
        return post(str, bArr, hashMap);
    }

    @Override // com.bytedance.bdinstall.ai
    public String post(String str, List<Pair<String, String>> list) {
        try {
            return k.getDefault().post(str, list);
        } catch (d e2) {
            throw new au(e2.getResponseCode(), e2.getCause());
        }
    }

    @Override // com.bytedance.bdinstall.ai
    public byte[] postStream(String str, byte[] bArr, Map<String, String> map) {
        try {
            return k.getDefault().postDataStream(str, bArr, map, null);
        } catch (d e2) {
            throw new au(e2.getResponseCode(), e2.getCause());
        }
    }
}
