package com.bytedance.geckox.buffer.stream;

import java.io.OutputStream;
/* compiled from: RandomOutputStream.java */
/* loaded from: classes2.dex */
public abstract class c extends OutputStream {
    public abstract void write(long j2, int i2);

    public abstract void write(long j2, byte[] bArr);

    public abstract void write(long j2, byte[] bArr, int i2, int i3);
}
