package com.bytedance.watson.assist.file;

import java.io.File;
/* compiled from: BaseStatFile.java */
/* loaded from: classes8.dex */
public abstract class a {

    /* renamed from: b  reason: collision with root package name */
    private String f16252b;

    /* renamed from: a  reason: collision with root package name */
    protected b f16251a = null;

    /* renamed from: c  reason: collision with root package name */
    private File f16253c = null;

    protected b a(File file) {
        return null;
    }

    public a(String str) {
        this.f16252b = null;
        this.f16252b = str;
    }

    public b refresh() {
        if (this.f16252b == null) {
            com.bytedance.watson.assist.utils.b.w("refresh and path null");
            return null;
        }
        if (this.f16253c == null) {
            this.f16253c = new File(this.f16252b);
        }
        b a2 = a(this.f16253c);
        this.f16251a = a2;
        return a2;
    }

    public b getInfo() {
        return this.f16251a;
    }

    public String toString() {
        return "BaseStatFile{mPath='" + this.f16252b + "', mFile=" + this.f16253c + ", mLastInfo=" + this.f16251a + '}';
    }
}
