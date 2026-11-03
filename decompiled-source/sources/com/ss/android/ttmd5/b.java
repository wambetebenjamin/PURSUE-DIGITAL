package com.ss.android.ttmd5;
/* compiled from: IRandomAccess.java */
/* loaded from: classes9.dex */
public interface b {
    void close();

    long length();

    int read(byte[] bArr, int i2, int i3);

    void seek(long j2, long j3);
}
