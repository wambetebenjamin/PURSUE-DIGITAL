package com.appsflyer.internal;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public final class AFj1sSDK extends FilterInputStream {
    private static final short AFKeystoreWrapper = (short) ((Math.sqrt(5.0d) - 1.0d) * Math.pow(2.0d, 15.0d));
    private byte[] AFInAppEventParameterName;
    private int AFInAppEventType;
    private int AFLogger;

    /* renamed from: d  reason: collision with root package name */
    private int f111d;

    /* renamed from: e  reason: collision with root package name */
    private int f112e;
    private int force;

    /* renamed from: i  reason: collision with root package name */
    private int f113i;
    private int registerClient;
    private int unregisterClient;
    private int v;
    private byte[] valueOf;
    private byte[] values;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return false;
    }

    public AFj1sSDK(InputStream inputStream, int[] iArr, int i2, byte[] bArr, int i3, int i4) throws IOException {
        super(new BufferedInputStream(inputStream, 4096));
        this.registerClient = Integer.MAX_VALUE;
        this.valueOf = new byte[8];
        this.values = new byte[8];
        this.AFInAppEventParameterName = new byte[8];
        this.AFInAppEventType = 8;
        this.unregisterClient = 8;
        this.AFLogger = Math.min(Math.max(i3, 5), 16);
        this.f112e = i4;
        if (i4 == 3) {
            System.arraycopy(bArr, 0, this.values, 0, 8);
        }
        long j2 = ((iArr[0] & 4294967295L) << 32) | (4294967295L & iArr[1]);
        if (i2 != 0) {
            int i5 = (int) j2;
            this.f111d = i5;
            this.force = i5 * i2;
            this.f113i = i5 ^ i2;
            this.v = (int) (j2 >> 32);
            return;
        }
        this.f111d = (int) j2;
        long j3 = j2 >> 3;
        short s = AFKeystoreWrapper;
        this.force = (int) ((s * j3) >> 32);
        this.f113i = (int) (j2 >> 32);
        this.v = (int) (j3 + s);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        values();
        int i2 = this.AFInAppEventType;
        if (i2 >= this.unregisterClient) {
            return -1;
        }
        byte[] bArr = this.valueOf;
        this.AFInAppEventType = i2 + 1;
        return bArr[i2] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = i2 + i3;
        for (int i5 = i2; i5 < i4; i5++) {
            values();
            int i6 = this.AFInAppEventType;
            if (i6 >= this.unregisterClient) {
                if (i5 == i2) {
                    return -1;
                }
                return i3 - (i4 - i5);
            }
            byte[] bArr2 = this.valueOf;
            this.AFInAppEventType = i6 + 1;
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
        values();
        return this.unregisterClient - this.AFInAppEventType;
    }

    private void AFInAppEventType() {
        if (this.f112e == 3) {
            byte[] bArr = this.valueOf;
            System.arraycopy(bArr, 0, this.AFInAppEventParameterName, 0, bArr.length);
        }
        byte[] bArr2 = this.valueOf;
        int i2 = ((bArr2[0] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr2[1] << Ascii.DLE) & 16711680) + ((bArr2[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr2[3] & 255);
        int i3 = ((-16777216) & (bArr2[4] << Ascii.CAN)) + (16711680 & (bArr2[5] << Ascii.DLE)) + (65280 & (bArr2[6] << 8)) + (bArr2[7] & 255);
        int i4 = 0;
        while (true) {
            int i5 = this.AFLogger;
            if (i4 >= i5) {
                break;
            }
            short s = AFKeystoreWrapper;
            i3 -= ((((i5 - i4) * s) + i2) ^ ((i2 << 4) + this.f113i)) ^ ((i2 >>> 5) + this.v);
            i2 -= (((i3 << 4) + this.f111d) ^ ((s * (i5 - i4)) + i3)) ^ ((i3 >>> 5) + this.force);
            i4++;
        }
        byte[] bArr3 = this.valueOf;
        bArr3[0] = (byte) (i2 >> 24);
        bArr3[1] = (byte) (i2 >> 16);
        bArr3[2] = (byte) (i2 >> 8);
        bArr3[3] = (byte) i2;
        bArr3[4] = (byte) (i3 >> 24);
        bArr3[5] = (byte) (i3 >> 16);
        bArr3[6] = (byte) (i3 >> 8);
        bArr3[7] = (byte) i3;
        if (this.f112e == 3) {
            for (int i6 = 0; i6 < 8; i6++) {
                byte[] bArr4 = this.valueOf;
                bArr4[i6] = (byte) (bArr4[i6] ^ this.values[i6]);
            }
            byte[] bArr5 = this.AFInAppEventParameterName;
            System.arraycopy(bArr5, 0, this.values, 0, bArr5.length);
        }
    }

    private int values() throws IOException {
        if (this.registerClient == Integer.MAX_VALUE) {
            this.registerClient = ((FilterInputStream) this).in.read();
        }
        if (this.AFInAppEventType == 8) {
            byte[] bArr = this.valueOf;
            int i2 = this.registerClient;
            bArr[0] = (byte) i2;
            if (i2 < 0) {
                throw new IllegalStateException("unexpected block size");
            }
            int i3 = 1;
            do {
                int read = ((FilterInputStream) this).in.read(this.valueOf, i3, 8 - i3);
                if (read <= 0) {
                    break;
                }
                i3 += read;
            } while (i3 < 8);
            if (i3 < 8) {
                throw new IllegalStateException("unexpected block size");
            }
            AFInAppEventType();
            int read2 = ((FilterInputStream) this).in.read();
            this.registerClient = read2;
            this.AFInAppEventType = 0;
            this.unregisterClient = read2 < 0 ? 8 - (this.valueOf[7] & 255) : 8;
        }
        return this.unregisterClient;
    }
}
