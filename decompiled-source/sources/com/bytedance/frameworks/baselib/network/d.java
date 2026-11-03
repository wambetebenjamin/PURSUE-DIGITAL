package com.bytedance.frameworks.baselib.network;

import com.bytedance.retrofit2.client.SsCall;
import java.io.InputStream;
/* compiled from: InputStreamWrapWithMetrics.java */
/* loaded from: classes2.dex */
public class d extends com.bytedance.frameworks.baselib.network.http.d {

    /* renamed from: a  reason: collision with root package name */
    private com.bytedance.frameworks.baselib.network.http.a f7511a;

    public d(InputStream inputStream, SsCall ssCall, com.bytedance.frameworks.baselib.network.http.a aVar) {
        super(inputStream, ssCall);
        this.f7511a = aVar;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.d, java.io.InputStream
    public int read(byte[] bArr) {
        if (this.f7511a.Y != null) {
            this.f7511a.Y.readResponseBodyStart();
        }
        try {
            return super.read(bArr);
        } finally {
            if (this.f7511a.Y != null) {
                this.f7511a.Y.readResponseBodyEnd();
            }
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.http.d, java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        if (this.f7511a.Y != null) {
            this.f7511a.Y.readResponseBodyStart();
        }
        try {
            return super.read(bArr, i2, i3);
        } finally {
            if (this.f7511a.Y != null) {
                this.f7511a.Y.readResponseBodyEnd();
            }
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.http.d, java.io.InputStream
    public int read() {
        if (this.f7511a.Y != null) {
            this.f7511a.Y.readResponseBodyStart();
        }
        try {
            return super.read();
        } finally {
            if (this.f7511a.Y != null) {
                this.f7511a.Y.readResponseBodyEnd();
            }
        }
    }
}
