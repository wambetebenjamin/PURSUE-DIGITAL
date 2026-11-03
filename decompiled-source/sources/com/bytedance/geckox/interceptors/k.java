package com.bytedance.geckox.interceptors;

import android.util.Pair;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* compiled from: LoadLocalChannelVersionInterceptorCombine.java */
/* loaded from: classes2.dex */
public class k extends com.bytedance.pipeline.d<String, Map<String, List<Pair<String, Long>>>> {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, String> f9123a;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.pipeline.d
    public void a(Object... objArr) {
        super.a(objArr);
        this.f9123a = (Map) objArr[0];
    }

    @Override // com.bytedance.pipeline.d
    /* renamed from: a */
    public Object a_(com.bytedance.pipeline.b<Map<String, List<Pair<String, Long>>>> bVar, String str) {
        Long latestChannelVersion;
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "get local channel version", "group:" + str);
        Set<String> keySet = this.f9123a.keySet();
        HashMap hashMap = new HashMap();
        for (String str2 : keySet) {
            File file = new File(this.f9123a.get(str2), str2);
            String[] list = file.list();
            ArrayList arrayList = new ArrayList();
            if (list != null && list.length > 0) {
                for (String str3 : list) {
                    File file2 = new File(file, str3);
                    if (file2.isDirectory() && (latestChannelVersion = com.bytedance.geckox.utils.o.getLatestChannelVersion(file2)) != null) {
                        arrayList.add(new Pair<>(str3, latestChannelVersion));
                    }
                }
            }
            hashMap.put(str2, arrayList);
        }
        return bVar.a((com.bytedance.pipeline.b<Map<String, List<Pair<String, Long>>>>) hashMap);
    }
}
