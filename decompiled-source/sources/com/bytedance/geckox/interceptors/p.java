package com.bytedance.geckox.interceptors;

import com.bytedance.geckox.model.LocalPackageModel;
import com.bytedance.geckox.model.UpdatePackage;
import java.io.File;
/* compiled from: PreDownloadInterceptor.java */
/* loaded from: classes2.dex */
public class p extends com.bytedance.pipeline.d<UpdatePackage, UpdatePackage> {

    /* renamed from: a  reason: collision with root package name */
    private File f9129a;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.geckox.listener.a f9130b;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.pipeline.d
    public void a(Object... objArr) {
        super.a(objArr);
        this.f9129a = (File) objArr[0];
        this.f9130b = (com.bytedance.geckox.listener.a) objArr[1];
    }

    @Override // com.bytedance.pipeline.d
    /* renamed from: a */
    public Object a_(com.bytedance.pipeline.b<UpdatePackage> bVar, UpdatePackage updatePackage) {
        String accessKey = updatePackage.getAccessKey();
        String channel = updatePackage.getChannel();
        File file = new File(new File(this.f9129a, accessKey), updatePackage.getChannel());
        if (file.isFile()) {
            com.bytedance.geckox.utils.f.delete(file);
        }
        if (!file.mkdirs() && !file.isDirectory()) {
            com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "can not create channel dir：", file.getAbsolutePath());
            throw new RuntimeException("can not create channel dir:" + file.getAbsolutePath());
        }
        com.bytedance.geckox.lock.a aVar = null;
        try {
            com.bytedance.geckox.lock.a a2 = com.bytedance.geckox.lock.a.a(file.getAbsolutePath() + File.separator + com.bytedance.geckox.lock.a.f9203a);
            Long latestChannelVersion = com.bytedance.geckox.utils.n.getLatestChannelVersion(this.f9129a, accessKey, channel);
            long longValue = latestChannelVersion == null ? 0L : latestChannelVersion.longValue();
            updatePackage.setLocalVersion(longValue);
            if (a2 == null) {
                com.bytedance.geckox.listener.a aVar2 = this.f9130b;
                if (aVar2 != null) {
                    aVar2.onUpdating(channel);
                    com.bytedance.geckox.listener.c.a().a(accessKey, channel, this.f9130b);
                }
                throw new RuntimeException("current channel is updating");
            } else if (longValue == updatePackage.getVersion()) {
                if (this.f9130b != null) {
                    LocalPackageModel localPackageModel = new LocalPackageModel(accessKey, channel);
                    localPackageModel.setLatestVersion(latestChannelVersion.longValue());
                    localPackageModel.setChannelPath(com.bytedance.geckox.utils.n.getChannelPath(this.f9129a, accessKey, channel, latestChannelVersion.longValue()));
                    this.f9130b.onLocalNewestVersion(localPackageModel);
                }
                throw new RuntimeException("current channel is the newest");
            } else {
                Object a3 = bVar.a((com.bytedance.pipeline.b<UpdatePackage>) updatePackage);
                if (a2 != null) {
                    a2.a();
                }
                return a3;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                aVar.a();
            }
            throw th;
        }
    }
}
