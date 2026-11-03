package com.bytedance.reader_apk.signaturev1;

import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import java.io.DataInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* compiled from: V1SchemeUtil.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    static final byte[] f12101a = {108, 116, 108, 111, 118, 101, 122, 104};

    public static String readChannel(File file) {
        RandomAccessFile randomAccessFile;
        byte[] bArr;
        RandomAccessFile randomAccessFile2 = null;
        try {
        } catch (Throwable th) {
            th = th;
            randomAccessFile2 = randomAccessFile;
        }
        try {
            try {
                randomAccessFile = new RandomAccessFile(file, DownloadFileUtils.MODE_READ);
                try {
                    long length = randomAccessFile.length();
                    byte[] bArr2 = new byte[f12101a.length];
                    long length2 = length - bArr.length;
                    randomAccessFile.seek(length2);
                    randomAccessFile.readFully(bArr2);
                    if (!a(bArr2)) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        return "";
                    }
                    long j2 = length2 - 2;
                    randomAccessFile.seek(j2);
                    int a2 = a(randomAccessFile);
                    if (a2 <= 0) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        return "";
                    }
                    randomAccessFile.seek(j2 - a2);
                    byte[] bArr3 = new byte[a2];
                    randomAccessFile.readFully(bArr3);
                    String str = new String(bArr3, "UTF-8");
                    try {
                        randomAccessFile.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    return str;
                } catch (FileNotFoundException e5) {
                    e = e5;
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                        return null;
                    }
                    return null;
                } catch (UnsupportedEncodingException e6) {
                    e = e6;
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                        return null;
                    }
                    return null;
                } catch (IOException e7) {
                    e = e7;
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                        return null;
                    }
                    return null;
                } catch (Exception e8) {
                    e = e8;
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                        return null;
                    }
                    return null;
                }
            } catch (IOException e9) {
                e9.printStackTrace();
                return null;
            }
        } catch (FileNotFoundException e10) {
            e = e10;
            randomAccessFile = null;
        } catch (UnsupportedEncodingException e11) {
            e = e11;
            randomAccessFile = null;
        } catch (IOException e12) {
            e = e12;
            randomAccessFile = null;
        } catch (Exception e13) {
            e = e13;
            randomAccessFile = null;
        } catch (Throwable th2) {
            th = th2;
            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e14) {
                    e14.printStackTrace();
                }
            }
            throw th;
        }
    }

    private static short a(DataInput dataInput) {
        byte[] bArr = new byte[2];
        dataInput.readFully(bArr);
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getShort(0);
    }

    private static boolean a(byte[] bArr) {
        if (bArr.length != f12101a.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = f12101a;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }
}
