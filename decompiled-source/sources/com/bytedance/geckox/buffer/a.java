package com.bytedance.geckox.buffer;

import java.io.File;
/* compiled from: Buffer.java */
/* loaded from: classes2.dex */
public interface a {
    long length();

    void persistence();

    long position();

    void position(long j2);

    int read();

    int read(byte[] bArr);

    int read(byte[] bArr, int i2, int i3);

    void release();

    long skip(long j2);

    File swap();

    int write(byte[] bArr, int i2, int i3);

    void write(int i2);

    void write(byte[] bArr);
}
