package com.bytedance.geckox.loader.fileloader;

import com.bytedance.geckox.d;
import com.facebook.common.util.UriUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/* compiled from: NormalFileLoader.java */
/* loaded from: classes2.dex */
public class c extends a {
    public c(File file) {
        super(file);
    }

    @Override // com.bytedance.geckox.loader.fileloader.a
    protected InputStream a(File file, String str) {
        File file2 = new File(file, UriUtil.LOCAL_RESOURCE_SCHEME + File.separator + str);
        if (!file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
            throw new IOException("file not found");
        }
        com.bytedance.geckox.logger.b.a(d.f8984a, "NormalFileLoader, file:", file2.getAbsolutePath());
        return new FileInputStream(file2.getCanonicalFile());
    }

    @Override // com.bytedance.geckox.loader.fileloader.a
    protected boolean b(File file, String str) {
        return new File(file, UriUtil.LOCAL_RESOURCE_SCHEME + File.separator + str).exists();
    }
}
