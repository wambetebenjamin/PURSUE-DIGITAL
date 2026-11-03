package com.bytedance.geckox.interceptors.singlefile.patch;

import android.util.Pair;
import com.bytedance.geckox.model.UpdatePackage;
import com.bytedance.geckox.utils.k;
/* compiled from: CheckPatchSingleFileMD5Interceptor.java */
/* loaded from: classes2.dex */
public class b extends com.bytedance.pipeline.d<Pair<com.bytedance.geckox.buffer.a, UpdatePackage>, Pair<com.bytedance.geckox.buffer.a, UpdatePackage>> {
    @Override // com.bytedance.pipeline.d
    /* renamed from: a */
    public Object a_(com.bytedance.pipeline.b<Pair<com.bytedance.geckox.buffer.a, UpdatePackage>> bVar, Pair<com.bytedance.geckox.buffer.a, UpdatePackage> pair) {
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "start check patch single file, channel:", ((UpdatePackage) pair.second).getChannel());
        com.bytedance.geckox.buffer.a aVar = (com.bytedance.geckox.buffer.a) pair.first;
        aVar.position(0L);
        UpdatePackage updatePackage = (UpdatePackage) pair.second;
        try {
            k.a(new com.bytedance.geckox.buffer.stream.a(aVar), updatePackage.getPatch().getMd5());
            return bVar.a((com.bytedance.pipeline.b<Pair<com.bytedance.geckox.buffer.a, UpdatePackage>>) pair);
        } catch (Throwable th) {
            throw new com.bytedance.geckox.exception.b("check patch single file failed, channel:" + updatePackage.getChannel() + ", pkg id:" + updatePackage.getPatch().getId() + ", caused by:" + th.getMessage(), th);
        }
    }
}
