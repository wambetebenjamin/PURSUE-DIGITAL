package com.bytedance.geckox.interceptors;

import android.util.Pair;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/* compiled from: LoadLocalChannelVersionInterceptor.java */
/* loaded from: classes2.dex */
public class j extends com.bytedance.pipeline.d<List<String>, List<Pair<String, Long>>> {

    /* renamed from: a  reason: collision with root package name */
    private File f9121a;

    /* renamed from: b  reason: collision with root package name */
    private String f9122b;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.pipeline.d
    public void a(Object... objArr) {
        super.a(objArr);
        this.f9121a = (File) objArr[0];
        this.f9122b = (String) objArr[1];
    }

    @Override // com.bytedance.pipeline.d
    /* renamed from: a */
    public Object a_(com.bytedance.pipeline.b<List<Pair<String, Long>>> bVar, List<String> list) {
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "get local channel version:", list);
        File file = new File(this.f9121a, this.f9122b);
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            Long latestChannelVersion = com.bytedance.geckox.utils.o.getLatestChannelVersion(new File(file, str));
            arrayList.add(new Pair<>(str, Long.valueOf(latestChannelVersion == null ? 0L : latestChannelVersion.longValue())));
        }
        return bVar.a((com.bytedance.pipeline.b<List<Pair<String, Long>>>) arrayList);
    }
}
