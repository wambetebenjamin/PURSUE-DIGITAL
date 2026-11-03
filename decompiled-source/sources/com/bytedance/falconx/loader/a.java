package com.bytedance.falconx.loader;

import android.content.Context;
import android.content.res.AssetManager;
import com.bytedance.falconx.f;
import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: AssetResLoader.java */
/* loaded from: classes2.dex */
public class a implements c {

    /* renamed from: a  reason: collision with root package name */
    private final File f7389a;

    /* renamed from: b  reason: collision with root package name */
    private AssetManager f7390b;

    /* renamed from: c  reason: collision with root package name */
    private AtomicBoolean f7391c = new AtomicBoolean(false);

    public a(Context context, File file) {
        this.f7389a = file;
        this.f7390b = context.getAssets();
    }

    @Override // com.bytedance.falconx.loader.c
    public InputStream a(String str) {
        if (this.f7391c.get()) {
            throw new RuntimeException("released!");
        }
        com.bytedance.geckox.logger.b.d(f.f7364a, "AssetResLoader ready to load, file:" + str);
        return this.f7390b.open(new File(this.f7389a, str).getPath());
    }

    @Override // com.bytedance.falconx.loader.c
    public boolean b(String str) {
        if (this.f7391c.get()) {
            throw new RuntimeException("released!");
        }
        File file = new File(this.f7389a, str);
        return Arrays.asList(this.f7390b.list(file.getParent())).contains(file.getName());
    }

    @Override // com.bytedance.falconx.loader.c
    public String a() {
        return "asset:///" + this.f7389a;
    }

    @Override // com.bytedance.falconx.loader.c
    public Map<String, Long> b() {
        return Collections.emptyMap();
    }

    @Override // com.bytedance.falconx.loader.c
    public void c() {
        this.f7391c.getAndSet(true);
    }
}
