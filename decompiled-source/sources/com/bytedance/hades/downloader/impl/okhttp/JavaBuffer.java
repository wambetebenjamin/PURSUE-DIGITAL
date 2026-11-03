package com.bytedance.hades.downloader.impl.okhttp;
/* loaded from: classes2.dex */
class JavaBuffer {
    public final byte[] data;
    public final long nativePtr;

    public JavaBuffer(int i2) {
        byte[] bArr = new byte[i2];
        this.data = bArr;
        this.nativePtr = OKHttpDownloadConnection.createBuffer(this, bArr, i2);
    }
}
