package com.bytedance.geckox.loader.fileloader;

import java.io.File;
import java.io.InputStream;
/* compiled from: FileLoader.java */
/* loaded from: classes2.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private File f9202a;

    protected abstract InputStream a(File file, String str);

    protected abstract boolean b(File file, String str);

    public a(File file) {
        this.f9202a = file;
    }

    public final InputStream a(String str) {
        return a(this.f9202a, str);
    }

    public final boolean b(String str) {
        return b(this.f9202a, str);
    }
}
