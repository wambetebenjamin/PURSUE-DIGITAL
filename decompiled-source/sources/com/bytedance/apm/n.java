package com.bytedance.apm;

import com.bytedance.compression.zstd.ZstdCompress;
import com.bytedance.compression.zstd.ZstdDictCompress;
import com.bytedance.services.apm.api.p;
import com.bytedance.services.apm.api.q;
import java.util.Arrays;
/* compiled from: ZstdCompressService.java */
/* loaded from: classes.dex */
public class n implements p {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f1221a;

    /* renamed from: b  reason: collision with root package name */
    private q f1222b;

    /* renamed from: c  reason: collision with root package name */
    private int f1223c;

    /* renamed from: d  reason: collision with root package name */
    private ZstdDictCompress f1224d;

    @Override // com.bytedance.services.apm.api.p
    public byte[] a(byte[] bArr, byte[] bArr2, int i2) {
        if (bArr2 == null) {
            return null;
        }
        if (this.f1224d == null || !Arrays.equals(bArr2, this.f1221a) || i2 != this.f1223c) {
            this.f1224d = new ZstdDictCompress(bArr2, i2);
            this.f1221a = bArr2;
            this.f1223c = i2;
        }
        return ZstdCompress.compress(bArr, this.f1224d);
    }

    public byte[] a(byte[] bArr, q qVar, int i2) {
        if (qVar == null) {
            return null;
        }
        if (!qVar.equals(this.f1222b) || i2 != this.f1223c) {
            byte[] bArr2 = qVar.get();
            if (bArr2 == null) {
                return null;
            }
            this.f1224d = new ZstdDictCompress(bArr2, i2);
            this.f1222b = qVar;
            this.f1223c = i2;
        }
        return ZstdCompress.compress(bArr, this.f1224d);
    }

    @Override // com.bytedance.services.apm.api.p
    public byte[] a(byte[] bArr, int i2) {
        return ZstdCompress.compress(bArr, i2);
    }
}
