package com.bytedance.geckox.interceptors;

import android.net.Uri;
import android.util.Pair;
import com.bytedance.geckox.model.UpdatePackage;
import java.util.List;
/* compiled from: RetryPatchDownloadInterceptor.java */
/* loaded from: classes2.dex */
public class r extends com.bytedance.pipeline.l<UpdatePackage, Pair<Uri, UpdatePackage>> {

    /* renamed from: a  reason: collision with root package name */
    private int f9132a = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.pipeline.l
    /* renamed from: a */
    public Pair<Uri, UpdatePackage> b() {
        UpdatePackage h2 = h();
        List<String> urlList = h2.getPatch().getUrlList();
        int i2 = this.f9132a;
        this.f9132a = i2 + 1;
        return new Pair<>(Uri.parse(urlList.get(i2)), h2);
    }

    @Override // com.bytedance.pipeline.l
    protected boolean a(Throwable th) {
        com.bytedance.geckox.logger.b.w(com.bytedance.geckox.d.f8984a, "patch update failed", th);
        if (this.f9132a >= h().getPatch().getUrlList().size()) {
            return false;
        }
        return (th instanceof com.bytedance.geckox.exception.a) || (th instanceof com.bytedance.geckox.exception.b);
    }
}
