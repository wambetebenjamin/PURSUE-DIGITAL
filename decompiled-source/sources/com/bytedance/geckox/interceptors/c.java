package com.bytedance.geckox.interceptors;

import com.bytedance.geckox.model.UpdatePackage;
import java.util.List;
/* compiled from: FullPatchRetryInterceptor.java */
/* loaded from: classes2.dex */
public class c extends com.bytedance.pipeline.k<UpdatePackage, UpdatePackage> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9068a = "full";

    /* renamed from: b  reason: collision with root package name */
    public static final String f9069b = "patch";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.pipeline.k
    public String a(com.bytedance.pipeline.b<UpdatePackage> bVar, UpdatePackage updatePackage) {
        UpdatePackage.Package patch = updatePackage.getPatch();
        if (patch == null) {
            com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "start full update, channel:", updatePackage.getChannel());
            return f9068a;
        }
        List<String> urlList = patch.getUrlList();
        if (urlList == null || urlList.isEmpty()) {
            com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "start full update, channel:", updatePackage.getChannel());
            return f9068a;
        }
        com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "start full update, channel:", updatePackage.getChannel());
        return f9069b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.pipeline.k
    public String a(com.bytedance.pipeline.b<UpdatePackage> bVar, UpdatePackage updatePackage, Throwable th, String str) {
        if (f9069b.equals(str)) {
            com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "start full update, channel:", updatePackage.getChannel());
            return f9068a;
        }
        throw new RuntimeException("full update failed, caused by:" + th.getMessage(), th);
    }
}
