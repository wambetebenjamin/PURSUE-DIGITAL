package com.appsflyer.internal;

import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public final class AFj1wSDK extends FilterInputStream {
    private byte[] AFInAppEventParameterName;
    private long[] AFInAppEventType;
    private final int AFKeystoreWrapper;
    private int AFLogger;

    /* renamed from: d  reason: collision with root package name */
    private int f116d;

    /* renamed from: e  reason: collision with root package name */
    private int f117e;
    private long[] valueOf;
    private short values;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return false;
    }

    public AFj1wSDK(InputStream inputStream, int i2, int i3, short s, int i4, int i5) throws IOException {
        super(new BufferedInputStream(inputStream, 4096));
        this.f117e = Integer.MAX_VALUE;
        int min = Math.min(Math.max((int) s, 4), 8);
        this.AFKeystoreWrapper = min;
        this.AFInAppEventParameterName = new byte[min];
        this.AFInAppEventType = new long[4];
        this.valueOf = new long[4];
        this.f116d = min;
        this.AFLogger = min;
        this.AFInAppEventType = AFj1rSDK.AFInAppEventType(i2 ^ i5, min ^ i5);
        this.valueOf = AFj1rSDK.AFInAppEventType(i3 ^ i5, i4 ^ i5);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        valueOf();
        int i2 = this.f116d;
        if (i2 >= this.AFLogger) {
            return -1;
        }
        byte[] bArr = this.AFInAppEventParameterName;
        this.f116d = i2 + 1;
        return bArr[i2] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = i2 + i3;
        for (int i5 = i2; i5 < i4; i5++) {
            valueOf();
            int i6 = this.f116d;
            if (i6 >= this.AFLogger) {
                if (i5 == i2) {
                    return -1;
                }
                return i3 - (i4 - i5);
            }
            byte[] bArr2 = this.AFInAppEventParameterName;
            this.f116d = i6 + 1;
            bArr[i5] = bArr2[i6];
        }
        return i3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j2) throws IOException {
        long j3 = 0;
        while (j3 < j2 && read() != -1) {
            j3++;
        }
        return j3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() throws IOException {
        valueOf();
        return this.AFLogger - this.f116d;
    }

    private void AFInAppEventParameterName() {
        long[] jArr = this.AFInAppEventType;
        long[] jArr2 = this.valueOf;
        short s = this.values;
        int i2 = (s + 2) % 4;
        int i3 = (s + 3) % 4;
        jArr2[i3] = ((jArr[i3] * 2147483085) + jArr2[i2]) / 2147483647L;
        jArr[i3] = ((jArr[s % 4] * 2147483085) + jArr2[i2]) % 2147483647L;
        for (int i4 = 0; i4 < this.AFKeystoreWrapper; i4++) {
            byte[] bArr = this.AFInAppEventParameterName;
            bArr[i4] = (byte) (bArr[i4] ^ ((this.AFInAppEventType[this.values] >> (i4 << 3)) & 255));
        }
        this.values = (short) ((this.values + 1) % 4);
    }

    private int valueOf() throws IOException {
        int i2;
        if (this.f117e == Integer.MAX_VALUE) {
            this.f117e = ((FilterInputStream) this).in.read();
        }
        if (this.f116d == this.AFKeystoreWrapper) {
            byte[] bArr = this.AFInAppEventParameterName;
            int i3 = this.f117e;
            bArr[0] = (byte) i3;
            if (i3 < 0) {
                throw new IllegalStateException("unexpected block size");
            }
            int i4 = 1;
            do {
                int read = ((FilterInputStream) this).in.read(this.AFInAppEventParameterName, i4, this.AFKeystoreWrapper - i4);
                if (read <= 0) {
                    break;
                }
                i4 += read;
            } while (i4 < this.AFKeystoreWrapper);
            if (i4 < this.AFKeystoreWrapper) {
                throw new IllegalStateException("unexpected block size");
            }
            AFInAppEventParameterName();
            int read2 = ((FilterInputStream) this).in.read();
            this.f117e = read2;
            this.f116d = 0;
            if (read2 < 0) {
                int i5 = this.AFKeystoreWrapper;
                i2 = i5 - (this.AFInAppEventParameterName[i5 - 1] & 255);
            } else {
                i2 = this.AFKeystoreWrapper;
            }
            this.AFLogger = i2;
        }
        return this.AFLogger;
    }
}
