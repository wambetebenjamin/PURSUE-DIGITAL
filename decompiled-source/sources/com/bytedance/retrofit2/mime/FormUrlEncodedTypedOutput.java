package com.bytedance.retrofit2.mime;

import com.bytedance.frameworks.encryptor.EncryptorUtil;
import com.bytedance.retrofit2.mime.TTRequestCompressManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes2.dex */
public final class FormUrlEncodedTypedOutput extends AbsTypedOutput {
    ByteArrayOutputStream content;
    private final boolean enableRecordFields;
    private final Map<String, List<String>> fields;

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput, com.bytedance.retrofit2.mime.TypedOutput
    public String fileName() {
        return null;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput, com.bytedance.retrofit2.mime.TypedOutput
    public String mimeType() {
        return "application/x-www-form-urlencoded; charset=UTF-8";
    }

    public FormUrlEncodedTypedOutput() {
        this.content = new ByteArrayOutputStream();
        this.fields = new HashMap();
        this.enableRecordFields = false;
    }

    public FormUrlEncodedTypedOutput(boolean z) {
        this.content = new ByteArrayOutputStream();
        this.fields = new HashMap();
        this.enableRecordFields = z;
    }

    public void addField(String str, String str2) {
        addField(str, true, str2, true);
    }

    public void addField(String str, boolean z, String str2, boolean z2) {
        Objects.requireNonNull(str, "name");
        Objects.requireNonNull(str2, "value");
        if (this.content.size() > 0) {
            this.content.write(38);
        }
        if (this.enableRecordFields) {
            if (this.fields.containsKey(str)) {
                this.fields.get(str).add(str2);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str2);
                this.fields.put(str, arrayList);
            }
        }
        if (z) {
            try {
                str = URLEncoder.encode(str, "UTF-8");
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }
        if (z2) {
            str2 = URLEncoder.encode(str2, "UTF-8");
        }
        this.content.write(str.getBytes("UTF-8"));
        this.content.write(61);
        this.content.write(str2.getBytes("UTF-8"));
    }

    public Map<String, List<String>> fields() {
        return this.fields;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput, com.bytedance.retrofit2.mime.TypedOutput
    public long length() {
        return this.content.size();
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput, com.bytedance.retrofit2.mime.TypedOutput
    public void writeTo(OutputStream outputStream) {
        outputStream.write(this.content.toByteArray());
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput, com.bytedance.retrofit2.mime.TypedOutput
    public String md5Stub() {
        this.mBodyMd5Stub = DigestUtil.md5Hex(this.content.toByteArray());
        return this.mBodyMd5Stub;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput
    public boolean interceptRequestBody() {
        byte[] encrypt;
        byte[] byteArray = this.content.toByteArray();
        if (byteArray == null || byteArray.length > 102400 || (encrypt = EncryptorUtil.encrypt(byteArray, byteArray.length)) == null) {
            return false;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(encrypt.length);
        this.content = byteArrayOutputStream;
        byteArrayOutputStream.write(encrypt, 0, encrypt.length);
        this.mIsBodyEncrypted = true;
        return true;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput
    public TTRequestCompressManager.CompressData compressRequestBody(String str, String str2, boolean z) {
        byte[] byteArray = this.content.toByteArray();
        if (byteArray == null) {
            return null;
        }
        TTRequestCompressManager.CompressData compressBody = TTRequestCompressManager.compressBody(byteArray, byteArray.length, str, str2, z);
        if (compressBody != null && compressBody.data != null) {
            this.mOriginBodySize = byteArray.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(compressBody.data.length);
            this.content = byteArrayOutputStream;
            byteArrayOutputStream.write(compressBody.data, 0, compressBody.data.length);
            this.mType = compressBody.contentEncoding;
        }
        return compressBody;
    }

    @Override // com.bytedance.retrofit2.mime.AbsTypedOutput
    public byte[] getOriginBody() {
        return TTRequestCompressManager.decompressDataByType(this.content.toByteArray(), this.mType, this.mOriginBodySize);
    }
}
