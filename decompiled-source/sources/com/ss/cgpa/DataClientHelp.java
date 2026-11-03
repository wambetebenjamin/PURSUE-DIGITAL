package com.ss.cgpa;

import android.os.Process;
import android.util.Base64;
import com.ss.cgpa.b;
/* loaded from: classes9.dex */
public class DataClientHelp extends BaseDataHelp {
    private String randomKey;
    private String randomSession;
    private String randomStr;
    private String selfPkgName;
    private String initAesSecretKey = b.a("b7e6e4b8e0e5e4e5e8f0f4f4bcf0b8ba");
    private String initDesSecretKey = b.a("efe8bbe8a9e8e9fa");
    private String debugTag = getClass().getName();
    private b digestUtils = new b();

    public DataClientHelp(String str) {
        this.selfPkgName = str;
        setDesSecretKey(this.initDesSecretKey);
        setAesSecretKey(this.initAesSecretKey);
        this.randomStr = getRandomSha256("s");
        this.randomKey = getRandomSha256("k");
        String randomSha256 = getRandomSha256("s");
        this.randomSession = randomSha256;
        this.randomSession = this.digestUtils.b(randomSha256);
        this.nativeDataHelp.setDataClientHelp(this);
    }

    private String getEncryptRandomKey() {
        return Base64.encodeToString(this.digestUtils.h(this.randomKey.getBytes()), 0);
    }

    private String getEncryptRandomStr() {
        return Base64.encodeToString(this.digestUtils.a(this.randomStr.getBytes(), b.c.AES128, this.randomKey.getBytes()), 0);
    }

    private String getRandomKey() {
        return this.randomKey;
    }

    private String getRandomSession() {
        return this.randomSession;
    }

    private String getRandomStr() {
        return this.randomStr;
    }

    @Override // com.ss.cgpa.BaseDataHelp
    public byte[] decryptMsg(byte[] bArr) {
        return this.digestUtils.b(bArr);
    }

    @Override // com.ss.cgpa.BaseDataHelp
    public byte[] encryptMsg(byte[] bArr) {
        return this.digestUtils.g(bArr);
    }

    protected String getRandomSha256(String str) {
        return this.digestUtils.f((Process.myPid() + "|" + System.currentTimeMillis() + "_" + str).getBytes());
    }

    public String getSelfPkgName() {
        return this.selfPkgName;
    }

    public void setAesSecretKey(String str) {
        this.digestUtils.b(b.c.AES128, str.getBytes());
    }

    @Override // com.ss.cgpa.BaseDataHelp
    protected void setDebugTag(String str) {
        super.setDebugTag(str);
        this.debugTag = str;
    }

    public void setDesSecretKey(String str) {
        this.digestUtils.b(b.d.DES56, str.getBytes());
    }

    public void updateMsgSecretKey(String str) {
        setAesSecretKey(str);
    }
}
