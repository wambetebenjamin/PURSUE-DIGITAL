package com.bytedance.retrofit2.mime;

import android.text.TextUtils;
import com.bytedance.frameworks.encryptor.EncryptorUtil;
import com.bytedance.retrofit2.mime.TTRequestCompressManager;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes2.dex */
public class TypedByteArray extends AbsTypedOutput implements TypedInput {
    private byte[] bytes;
    private final String fakeFileName;
    private final String mimeType;

    public TypedByteArray(String str, byte[] bArr, String... strArr) {
        String str2 = (strArr == null || strArr.length <= 0) ? null : strArr[0];
        str = str == null ? TextUtils.isEmpty(str2) ? "application/unknown" : "application/octet-stream" : str;
        Objects.requireNonNull(bArr, "bytes null");
        this.mimeType = str;
        this.bytes = bArr;
        this.fakeFileName = str2;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput, com.bytedance.retrofit2.mime.TypedOutput
    public String fileName() {
        if (TextUtils.isEmpty(this.fakeFileName)) {
            return null;
        }
        return this.fakeFileName;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput, com.bytedance.retrofit2.mime.TypedOutput
    public String mimeType() {
        return this.mimeType;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput, com.bytedance.retrofit2.mime.TypedOutput
    public long length() {
        return this.bytes.length;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput, com.bytedance.retrofit2.mime.TypedOutput
    public void writeTo(OutputStream outputStream) {
        outputStream.write(this.bytes);
    }

    @Override // com.bytedance.retrofit2.mime.TypedInput
    public InputStream in() {
        return new ByteArrayInputStream(this.bytes);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TypedByteArray typedByteArray = (TypedByteArray) obj;
        return Arrays.equals(this.bytes, typedByteArray.bytes) && this.mimeType.equals(typedByteArray.mimeType);
    }

    public int hashCode() {
        return (this.mimeType.hashCode() * 31) + Arrays.hashCode(this.bytes);
    }

    public String toString() {
        return "TypedByteArray[length=" + length() + "]";
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput, com.bytedance.retrofit2.mime.TypedOutput
    public String md5Stub() {
        byte[] bArr = this.bytes;
        if (bArr == null) {
            return null;
        }
        this.mBodyMd5Stub = DigestUtil.md5Hex(bArr);
        return this.mBodyMd5Stub;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput
    public boolean interceptRequestBody() {
        byte[] encrypt;
        byte[] bArr = this.bytes;
        if (bArr == null || bArr.length > 102400 || (encrypt = EncryptorUtil.encrypt(bArr, bArr.length)) == null) {
            return false;
        }
        this.bytes = encrypt;
        this.mIsBodyEncrypted = true;
        return true;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput
    public TTRequestCompressManager.CompressData compressRequestBody(String str, String str2, boolean z) {
        byte[] bArr = this.bytes;
        if (bArr == null) {
            return null;
        }
        TTRequestCompressManager.CompressData compressBody = TTRequestCompressManager.compressBody(bArr, bArr.length, str, str2, z);
        if (compressBody != null && compressBody.data != null) {
            this.mOriginBodySize = this.bytes.length;
            this.bytes = compressBody.data;
            this.mType = compressBody.contentEncoding;
        }
        return compressBody;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput
    public byte[] getOriginBody() {
        return TTRequestCompressManager.decompressDataByType(this.bytes, this.mType, this.mOriginBodySize);
    }
}
