package com.google.android.play.core.assetpacks.internal;

import com.vk.api.sdk.exceptions.VKApiCodes;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class am {
    public static long a(an anVar, InputStream inputStream, OutputStream outputStream, long j2) throws IOException {
        byte[] bArr = new byte[16384];
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream, 4096));
        int readInt = dataInputStream.readInt();
        if (readInt != -771763713) {
            throw new al("Unexpected magic=".concat(String.valueOf(String.format("%x", Integer.valueOf(readInt)))));
        }
        int read = dataInputStream.read();
        if (read != 4) {
            throw new al("Unexpected version=" + read);
        }
        long j3 = 0;
        while (true) {
            long j4 = j2 - j3;
            try {
                int read2 = dataInputStream.read();
                if (read2 == -1) {
                    throw new IOException("Patch file overrun");
                }
                if (read2 == 0) {
                    return j3;
                }
                switch (read2) {
                    case 247:
                        read2 = dataInputStream.readUnsignedShort();
                        c(bArr, dataInputStream, outputStream, read2, j4);
                        break;
                    case 248:
                        read2 = dataInputStream.readInt();
                        c(bArr, dataInputStream, outputStream, read2, j4);
                        break;
                    case 249:
                        long readUnsignedShort = dataInputStream.readUnsignedShort();
                        read2 = dataInputStream.read();
                        if (read2 != -1) {
                            b(bArr, anVar, outputStream, readUnsignedShort, read2, j4);
                            break;
                        } else {
                            throw new IOException("Unexpected end of patch");
                        }
                    case 250:
                        read2 = dataInputStream.readUnsignedShort();
                        b(bArr, anVar, outputStream, dataInputStream.readUnsignedShort(), read2, j4);
                        break;
                    case 251:
                        read2 = dataInputStream.readInt();
                        b(bArr, anVar, outputStream, dataInputStream.readUnsignedShort(), read2, j4);
                        break;
                    case 252:
                        long readInt2 = dataInputStream.readInt();
                        read2 = dataInputStream.read();
                        if (read2 != -1) {
                            b(bArr, anVar, outputStream, readInt2, read2, j4);
                            break;
                        } else {
                            throw new IOException("Unexpected end of patch");
                        }
                    case VKApiCodes.CODE_ACCESS_POLLS_WITHOUT_VOTE /* 253 */:
                        read2 = dataInputStream.readUnsignedShort();
                        b(bArr, anVar, outputStream, dataInputStream.readInt(), read2, j4);
                        break;
                    case 254:
                        read2 = dataInputStream.readInt();
                        b(bArr, anVar, outputStream, dataInputStream.readInt(), read2, j4);
                        break;
                    case 255:
                        long readLong = dataInputStream.readLong();
                        read2 = dataInputStream.readInt();
                        b(bArr, anVar, outputStream, readLong, read2, j4);
                        break;
                    default:
                        c(bArr, dataInputStream, outputStream, read2, j4);
                        break;
                }
                j3 += read2;
            } finally {
                outputStream.flush();
            }
        }
    }

    private static void b(byte[] bArr, an anVar, OutputStream outputStream, long j2, int i2, long j3) throws IOException {
        if (i2 < 0) {
            throw new IOException("copyLength negative");
        }
        if (j2 < 0) {
            throw new IOException("inputOffset negative");
        }
        long j4 = i2;
        if (j4 <= j3) {
            try {
                InputStream c2 = new ao(anVar, j2, j4).c();
                while (i2 > 0) {
                    int min = Math.min(i2, 16384);
                    int i3 = 0;
                    while (i3 < min) {
                        int read = c2.read(bArr, i3, min - i3);
                        if (read == -1) {
                            throw new IOException("truncated input stream");
                        }
                        i3 += read;
                    }
                    outputStream.write(bArr, 0, min);
                    i2 -= min;
                }
                c2.close();
                return;
            } catch (EOFException e2) {
                throw new IOException("patch underrun", e2);
            }
        }
        throw new IOException("Output length overrun");
    }

    private static void c(byte[] bArr, DataInputStream dataInputStream, OutputStream outputStream, int i2, long j2) throws IOException {
        if (i2 < 0) {
            throw new IOException("copyLength negative");
        }
        if (i2 > j2) {
            throw new IOException("Output length overrun");
        }
        while (i2 > 0) {
            try {
                int min = Math.min(i2, 16384);
                dataInputStream.readFully(bArr, 0, min);
                outputStream.write(bArr, 0, min);
                i2 -= min;
            } catch (EOFException unused) {
                throw new IOException("patch underrun");
            }
        }
    }
}
