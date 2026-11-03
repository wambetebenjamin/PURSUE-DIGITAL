package com.bytedance.retrofit2.mime;

import com.bytedance.retrofit2.mime.TTRequestCompressManager;
import java.io.OutputStream;
/* loaded from: classes2.dex */
public abstract class AbsTypedOutput implements TypedOutput {
    protected static final int MAX_ENCRYPT_LENGTH = 102400;
    protected volatile String mBodyMd5Stub;
    protected volatile boolean mIsBodyEncrypted;
    protected volatile int mOriginBodySize;
    protected volatile String mType;

    public abstract TTRequestCompressManager.CompressData compressRequestBody(String str, String str2, boolean z);

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String fileName() {
        return null;
    }

    public abstract byte[] getOriginBody();

    public abstract boolean interceptRequestBody();

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public long length() {
        return 0L;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String mimeType() {
        return null;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public void writeTo(OutputStream outputStream) {
    }

    public boolean isBodyEncrypted() {
        return this.mIsBodyEncrypted;
    }

    public String bodyCompressType() {
        return this.mType;
    }

    public String getBodyMd5Stub() {
        return this.mBodyMd5Stub;
    }

    public int getOriginBodySize() {
        return this.mOriginBodySize;
    }

    @Override // com.bytedance.retrofit2.mime.TypedOutput
    public String md5Stub() {
        return this.mBodyMd5Stub;
    }
}
