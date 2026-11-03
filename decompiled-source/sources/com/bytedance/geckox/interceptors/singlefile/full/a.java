package com.bytedance.geckox.interceptors.singlefile.full;

import android.util.Pair;
import com.bytedance.geckox.model.UpdatePackage;
import com.bytedance.geckox.utils.k;
import java.io.File;
/* compiled from: CheckFullSingleFileMD5Interceptor.java */
/* loaded from: classes2.dex */
public class a extends com.bytedance.pipeline.d<Pair<com.bytedance.geckox.buffer.a, UpdatePackage>, Pair<File, UpdatePackage>> {
    @Override // com.bytedance.pipeline.d
    /* renamed from: a */
    public Object a_(com.bytedance.pipeline.b<Pair<File, UpdatePackage>> bVar, Pair<com.bytedance.geckox.buffer.a, UpdatePackage> pair) {
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "start check full single file channel:", ((UpdatePackage) pair.second).getChannel());
        com.bytedance.geckox.buffer.a aVar = (com.bytedance.geckox.buffer.a) pair.first;
        UpdatePackage updatePackage = (UpdatePackage) pair.second;
        aVar.position(0L);
        try {
            k.a(new com.bytedance.geckox.buffer.stream.a(aVar), updatePackage.getFullPackage().getMd5());
            aVar.persistence();
            return bVar.a((com.bytedance.pipeline.b<Pair<File, UpdatePackage>>) new Pair<>(aVar.swap(), updatePackage));
        } catch (Throwable th) {
            throw new com.bytedance.geckox.exception.b("check full single file failed, channel:" + updatePackage.getChannel() + ", pkg id:" + updatePackage.getFullPackage().getId() + ", caused by:" + th.getMessage(), th);
        }
    }
}
