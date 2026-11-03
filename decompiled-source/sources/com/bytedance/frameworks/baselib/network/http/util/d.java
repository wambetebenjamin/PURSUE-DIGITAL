package com.bytedance.frameworks.baselib.network.http.util;

import java.io.IOException;
/* compiled from: DownloadFileTooLargeException.java */
/* loaded from: classes2.dex */
public class d extends IOException {

    /* renamed from: c  reason: collision with root package name */
    private static final long f8318c = -1098012010869697449L;

    /* renamed from: a  reason: collision with root package name */
    final int f8319a;

    /* renamed from: b  reason: collision with root package name */
    final long f8320b;

    public d(int i2, long j2) {
        super("Download file too large: " + j2 + " exceed maxsize: " + i2);
        this.f8319a = i2;
        this.f8320b = j2;
    }

    public int a() {
        return this.f8319a;
    }

    public long b() {
        return this.f8320b;
    }
}
