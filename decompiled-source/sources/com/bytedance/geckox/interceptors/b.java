package com.bytedance.geckox.interceptors;

import com.bytedance.geckox.model.UpdatePackage;
/* compiled from: FileTypeBranchInterceptor.java */
/* loaded from: classes2.dex */
public class b extends com.bytedance.pipeline.a<UpdatePackage, UpdatePackage> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9065a = "branch_zip";

    /* renamed from: b  reason: collision with root package name */
    public static final String f9066b = "branch_single_file";

    /* renamed from: c  reason: collision with root package name */
    public static final String f9067c = "branch_myarchive_file";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.pipeline.a
    public String a(com.bytedance.pipeline.b<UpdatePackage> bVar, UpdatePackage updatePackage) {
        int packageType = updatePackage.getPackageType();
        if (packageType != 0) {
            if (packageType == 1) {
                return f9066b;
            }
            throw new RuntimeException("unknow file type: " + packageType);
        }
        return f9065a;
    }
}
