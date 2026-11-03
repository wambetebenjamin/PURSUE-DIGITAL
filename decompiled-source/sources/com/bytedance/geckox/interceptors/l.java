package com.bytedance.geckox.interceptors;

import android.util.Pair;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: LoadLocalChannelVersionInterceptorMulti.java */
/* loaded from: classes2.dex */
public class l extends com.bytedance.pipeline.d<String, Map<String, List<Pair<String, Long>>>> {

    /* renamed from: a  reason: collision with root package name */
    private File f9124a;

    /* renamed from: b  reason: collision with root package name */
    private List<String> f9125b;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.pipeline.d
    public void a(Object... objArr) {
        super.a(objArr);
        this.f9124a = (File) objArr[0];
        this.f9125b = (List) objArr[1];
    }

    @Override // com.bytedance.pipeline.d
    /* renamed from: a */
    public Object a_(com.bytedance.pipeline.b<Map<String, List<Pair<String, Long>>>> bVar, String str) {
        Long latestChannelVersion;
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "get local channel version:", str);
        long currentTimeMillis = System.currentTimeMillis();
        HashMap hashMap = new HashMap();
        for (String str2 : this.f9125b) {
            File file = new File(this.f9124a, str2);
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
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "get local channel version costs:", Long.valueOf((System.currentTimeMillis() - currentTimeMillis) / 1000), "s");
        return bVar.a((com.bytedance.pipeline.b<Map<String, List<Pair<String, Long>>>>) hashMap);
    }
}
