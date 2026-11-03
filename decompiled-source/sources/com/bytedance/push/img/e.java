package com.bytedance.push.img;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
/* compiled from: Utils.java */
/* loaded from: classes2.dex */
class e {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11386a = 12;

    /* renamed from: b  reason: collision with root package name */
    private static final String f11387b = "RIFF";

    /* renamed from: c  reason: collision with root package name */
    private static final String f11388c = "WEBP";

    e() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(InputStream inputStream) {
        byte[] bArr = new byte[12];
        return inputStream.read(bArr, 0, 12) == 12 && f11387b.equals(new String(bArr, 0, 4, "US-ASCII")) && f11388c.equals(new String(bArr, 8, 4, "US-ASCII"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] b(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[4096];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 != read) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
