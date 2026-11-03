package com.bytedance.geckox.clean.cache;

import java.io.File;
import java.util.List;
/* compiled from: FIFOCachePolicy.java */
/* loaded from: classes2.dex */
class e extends b {
    @Override // com.bytedance.geckox.clean.cache.b
    public void visit(String str) {
    }

    @Override // com.bytedance.geckox.clean.cache.b
    public void attach(a aVar, File file, List<String> list) {
        super.attach(aVar, file, list);
    }

    @Override // com.bytedance.geckox.clean.cache.b
    public void append() {
        for (String str : this.f8983c) {
            List<File> b2 = com.bytedance.geckox.utils.f.b(new File(this.f8982b, str));
            if (b2 != null && b2.size() > this.f8981a.getLimitCount()) {
                for (File file : b2.subList(0, b2.size() - this.f8981a.getLimitCount())) {
                    boolean b3 = com.bytedance.geckox.clean.c.b(file.getAbsolutePath());
                    if (this.f8981a.getCleanListener() != null) {
                        this.f8981a.getCleanListener().onCleaned(file.getName(), b3);
                    }
                }
            }
        }
    }
}
