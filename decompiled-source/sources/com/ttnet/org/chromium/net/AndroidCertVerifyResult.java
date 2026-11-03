package com.ttnet.org.chromium.net;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@JNINamespace("net::android")
/* loaded from: classes9.dex */
public class AndroidCertVerifyResult {
    private final List<X509Certificate> mCertificateChain;
    private final boolean mIsIssuedByKnownRoot;
    private final int mStatus;

    public AndroidCertVerifyResult(int i2, boolean z, List<X509Certificate> list) {
        this.mStatus = i2;
        this.mIsIssuedByKnownRoot = z;
        this.mCertificateChain = new ArrayList(list);
    }

    public AndroidCertVerifyResult(int i2) {
        this.mStatus = i2;
        this.mIsIssuedByKnownRoot = false;
        this.mCertificateChain = Collections.emptyList();
    }

    public int getStatus() {
        return this.mStatus;
    }

    public boolean isIssuedByKnownRoot() {
        return this.mIsIssuedByKnownRoot;
    }

    public byte[][] getCertificateChainEncoded() {
        byte[][] bArr = new byte[this.mCertificateChain.size()];
        for (int i2 = 0; i2 < this.mCertificateChain.size(); i2++) {
            try {
                bArr[i2] = this.mCertificateChain.get(i2).getEncoded();
            } catch (CertificateEncodingException unused) {
                return new byte[0];
            }
        }
        return bArr;
    }
}
