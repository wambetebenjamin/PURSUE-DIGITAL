package com.bytedance.geckox.interceptors;

import android.util.Pair;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* compiled from: LoadLocalChannelVersionInterceptorV4.java */
/* loaded from: classes2.dex */
public class m extends com.bytedance.pipeline.d<String, Map<String, List<Pair<String, Long>>>> {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, List<String>> f9126a;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.pipeline.d
    public void a(Object... objArr) {
        super.a(objArr);
        this.f9126a = (Map) objArr[0];
    }

    @Override // com.bytedance.pipeline.d
    /* renamed from: a */
    public Object a_(com.bytedance.pipeline.b<Map<String, List<Pair<String, Long>>>> bVar, String str) {
        Long latestChannelVersion;
        int i2 = 0;
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "get local channel version", "group:" + str);
        Set<String> keySet = this.f9126a.keySet();
        HashMap hashMap = new HashMap();
        for (String str2 : keySet) {
            for (String str3 : this.f9126a.get(str2)) {
                File file = new File(str3, str2);
                String[] list = file.list();
                ArrayList arrayList = new ArrayList();
                if (list != null && list.length > 0) {
                    int length = list.length;
                    for (int i3 = i2; i3 < length; i3++) {
                        String str4 = list[i3];
                        File file2 = new File(file, str4);
                        if (file2.isDirectory() && (latestChannelVersion = com.bytedance.geckox.utils.o.getLatestChannelVersion(file2)) != null) {
                            arrayList.add(new Pair<>(str4, latestChannelVersion));
                        }
                    }
                }
                List<Pair<String, Long>> list2 = hashMap.get(str2);
                if (list2 != null) {
                    list2.addAll(arrayList);
                } else {
                    hashMap.put(str2, arrayList);
                }
                i2 = 0;
            }
        }
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "get local channel version", hashMap);
        return bVar.a((com.bytedance.pipeline.b<Map<String, List<Pair<String, Long>>>>) hashMap);
    }
}
