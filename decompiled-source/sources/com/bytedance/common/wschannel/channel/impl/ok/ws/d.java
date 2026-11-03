package com.bytedance.common.wschannel.channel.impl.ok.ws;

import okio.Buffer;
import okio.ByteString;
/* compiled from: WebSocketProtocol.java */
/* loaded from: classes2.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    static final String f5539a = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";

    /* renamed from: b  reason: collision with root package name */
    static final int f5540b = 128;

    /* renamed from: c  reason: collision with root package name */
    static final int f5541c = 64;

    /* renamed from: d  reason: collision with root package name */
    static final int f5542d = 32;

    /* renamed from: e  reason: collision with root package name */
    static final int f5543e = 16;

    /* renamed from: f  reason: collision with root package name */
    static final int f5544f = 15;

    /* renamed from: g  reason: collision with root package name */
    static final int f5545g = 8;

    /* renamed from: h  reason: collision with root package name */
    static final int f5546h = 128;

    /* renamed from: i  reason: collision with root package name */
    static final int f5547i = 127;

    /* renamed from: j  reason: collision with root package name */
    static final int f5548j = 0;
    static final int k = 1;
    static final int l = 2;
    static final int m = 8;
    static final int n = 9;
    static final int o = 10;
    static final long p = 125;
    static final long q = 123;
    static final int r = 126;
    static final long s = 65535;
    static final int t = 127;
    static final int u = 1001;
    static final int v = 1005;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Buffer.UnsafeCursor unsafeCursor, byte[] bArr) {
        int length = bArr.length;
        int i2 = 0;
        do {
            byte[] bArr2 = unsafeCursor.data;
            int i3 = unsafeCursor.start;
            int i4 = unsafeCursor.end;
            while (i3 < i4) {
                int i5 = i2 % length;
                bArr2[i3] = (byte) (bArr2[i3] ^ bArr[i5]);
                i3++;
                i2 = i5 + 1;
            }
        } while (unsafeCursor.next() != -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(int i2) {
        if (i2 < 1000 || i2 >= 5000) {
            return "Code must be in range [1000,5000): " + i2;
        }
        if ((i2 < 1004 || i2 > 1006) && (i2 < 1012 || i2 > 2999)) {
            return null;
        }
        return "Code " + i2 + " is reserved and may not be used.";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(int i2) {
        String a2 = a(i2);
        if (a2 != null) {
            throw new IllegalArgumentException(a2);
        }
    }

    public static String a(String str) {
        return ByteString.encodeUtf8(str + f5539a).sha1().base64();
    }

    private d() {
        throw new AssertionError("No instances.");
    }
}
