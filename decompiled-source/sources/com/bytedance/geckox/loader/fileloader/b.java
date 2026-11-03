package com.bytedance.geckox.loader.fileloader;

import com.bytedance.geckox.d;
import java.io.File;
import java.io.InputStream;
/* compiled from: MyArchiveFileLoader.java */
/* loaded from: classes2.dex */
public class b extends a {
    public b(File file) {
        super(file);
    }

    @Override // com.bytedance.geckox.loader.fileloader.a
    protected InputStream a(File file, String str) {
        com.bytedance.geckox.logger.b.a(d.f8984a, "MyArchiveFileLoader, file:", new File(str).getCanonicalPath());
        return null;
    }

    @Override // com.bytedance.geckox.loader.fileloader.a
    protected boolean b(File file, String str) {
        new File(str).getCanonicalPath();
        return false;
    }
}
