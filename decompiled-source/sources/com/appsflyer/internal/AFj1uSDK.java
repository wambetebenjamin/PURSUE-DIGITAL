package com.appsflyer.internal;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import java.io.BufferedInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public final class AFj1uSDK extends FilterInputStream {
    private byte[] AFInAppEventParameterName;
    private final int AFInAppEventType;
    private AFj1vSDK AFKeystoreWrapper;
    private int[] AFLogger;

    /* renamed from: d  reason: collision with root package name */
    private int f114d;

    /* renamed from: e  reason: collision with root package name */
    private int f115e;
    private int registerClient;
    private int unregisterClient;
    private byte[] valueOf;
    private byte[] values;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final boolean markSupported() {
        return false;
    }

    public AFj1uSDK(InputStream inputStream, int[] iArr, byte[] bArr, int i2, boolean z, int i3) throws IOException {
        super(new BufferedInputStream(inputStream, 4096));
        this.f114d = Integer.MAX_VALUE;
        int min = Math.min(Math.max(i2, 3), 16);
        this.AFInAppEventType = min;
        this.values = new byte[8];
        byte[] bArr2 = new byte[8];
        this.valueOf = bArr2;
        this.AFInAppEventParameterName = new byte[8];
        this.AFLogger = new int[2];
        this.registerClient = 8;
        this.unregisterClient = 8;
        this.f115e = i3;
        if (i3 == 2) {
            System.arraycopy(bArr, 0, bArr2, 0, 8);
        }
        this.AFKeystoreWrapper = new AFj1vSDK(iArr, min, true, false);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        valueOf();
        int i2 = this.registerClient;
        if (i2 >= this.unregisterClient) {
            return -1;
        }
        byte[] bArr = this.values;
        this.registerClient = i2 + 1;
        return bArr[i2] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i2, int i3) throws IOException {
        int i4 = i2 + i3;
        for (int i5 = i2; i5 < i4; i5++) {
            valueOf();
            int i6 = this.registerClient;
            if (i6 >= this.unregisterClient) {
                if (i5 == i2) {
                    return -1;
                }
                return i3 - (i4 - i5);
            }
            byte[] bArr2 = this.values;
            this.registerClient = i6 + 1;
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
        return this.unregisterClient - this.registerClient;
    }

    private void AFKeystoreWrapper() {
        if (this.f115e == 2) {
            byte[] bArr = this.values;
            System.arraycopy(bArr, 0, this.AFInAppEventParameterName, 0, bArr.length);
        }
        byte[] bArr2 = this.values;
        AFj1xSDK.AFInAppEventParameterName(((bArr2[0] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr2[1] << Ascii.DLE) & 16711680) + ((bArr2[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr2[3] & 255), ((-16777216) & (bArr2[4] << Ascii.CAN)) + (16711680 & (bArr2[5] << Ascii.DLE)) + (65280 & (bArr2[6] << 8)) + (bArr2[7] & 255), false, this.AFInAppEventType, this.AFKeystoreWrapper.values, this.AFKeystoreWrapper.AFKeystoreWrapper, this.AFLogger);
        int[] iArr = this.AFLogger;
        int i2 = iArr[0];
        int i3 = iArr[1];
        byte[] bArr3 = this.values;
        bArr3[0] = (byte) (i2 >> 24);
        bArr3[1] = (byte) (i2 >> 16);
        bArr3[2] = (byte) (i2 >> 8);
        bArr3[3] = (byte) i2;
        bArr3[4] = (byte) (i3 >> 24);
        bArr3[5] = (byte) (i3 >> 16);
        bArr3[6] = (byte) (i3 >> 8);
        bArr3[7] = (byte) i3;
        if (this.f115e == 2) {
            for (int i4 = 0; i4 < 8; i4++) {
                byte[] bArr4 = this.values;
                bArr4[i4] = (byte) (bArr4[i4] ^ this.valueOf[i4]);
            }
            byte[] bArr5 = this.AFInAppEventParameterName;
            System.arraycopy(bArr5, 0, this.valueOf, 0, bArr5.length);
        }
    }

    private int valueOf() throws IOException {
        if (this.f114d == Integer.MAX_VALUE) {
            this.f114d = ((FilterInputStream) this).in.read();
        }
        if (this.registerClient == 8) {
            byte[] bArr = this.values;
            int i2 = this.f114d;
            bArr[0] = (byte) i2;
            if (i2 < 0) {
                throw new IllegalStateException("unexpected block size");
            }
            int i3 = 1;
            do {
                int read = ((FilterInputStream) this).in.read(this.values, i3, 8 - i3);
                if (read <= 0) {
                    break;
                }
                i3 += read;
            } while (i3 < 8);
            if (i3 < 8) {
                throw new IllegalStateException("unexpected block size");
            }
            AFKeystoreWrapper();
            int read2 = ((FilterInputStream) this).in.read();
            this.f114d = read2;
            this.registerClient = 0;
            this.unregisterClient = read2 < 0 ? 8 - (this.values[7] & 255) : 8;
        }
        return this.unregisterClient;
    }
}
