package com.bytedance.falconx.loader;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.falconx.f;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
/* compiled from: GeckoResLoader.java */
/* loaded from: classes2.dex */
public class b implements c {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.geckox.loader.b f7392a;

    public b(Context context, String str) {
        if (context == null) {
            throw new RuntimeException("context is null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("access key is empty");
        }
        this.f7392a = new com.bytedance.geckox.loader.b(context, str);
    }

    public b(Context context, String str, File file) {
        if (context == null) {
            throw new RuntimeException("context is null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("access key is empty");
        }
        if (file == null) {
            throw new RuntimeException("resRootDir is null");
        }
        this.f7392a = new com.bytedance.geckox.loader.b(context, str, file);
    }

    @Override // com.bytedance.falconx.loader.c
    public InputStream a(String str) {
        com.bytedance.geckox.logger.b.d(f.f7364a, "GeckoResLoader ready to load, file:" + str);
        return this.f7392a.getInputStream(str);
    }

    @Override // com.bytedance.falconx.loader.c
    public boolean b(String str) {
        return this.f7392a.exist(str);
    }

    @Override // com.bytedance.falconx.loader.c
    public String a() {
        return this.f7392a.getResRootDir();
    }

    @Override // com.bytedance.falconx.loader.c
    public Map<String, Long> b() {
        return this.f7392a.getChannelVersion();
    }

    @Override // com.bytedance.falconx.loader.c
    public void c() {
        this.f7392a.release();
    }

    protected void finalize() {
        super.finalize();
        try {
            c();
        } catch (Exception e2) {
            com.bytedance.geckox.logger.b.w("finalize:", e2);
        }
    }
}
