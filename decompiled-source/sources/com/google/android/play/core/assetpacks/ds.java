package com.google.android.play.core.assetpacks;

import java.util.Arrays;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class ds {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f16711a = new byte[4096];

    /* renamed from: b  reason: collision with root package name */
    private int f16712b;

    /* renamed from: c  reason: collision with root package name */
    private long f16713c;

    /* renamed from: d  reason: collision with root package name */
    private long f16714d;

    /* renamed from: e  reason: collision with root package name */
    private int f16715e;

    /* renamed from: f  reason: collision with root package name */
    private int f16716f;

    /* renamed from: g  reason: collision with root package name */
    private int f16717g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f16718h;

    /* renamed from: i  reason: collision with root package name */
    private String f16719i;

    public ds() {
        d();
    }

    private final int e(int i2, byte[] bArr, int i3, int i4) {
        int i5 = this.f16712b;
        if (i5 < i2) {
            int min = Math.min(i4, i2 - i5);
            System.arraycopy(bArr, i3, this.f16711a, this.f16712b, min);
            int i6 = this.f16712b + min;
            this.f16712b = i6;
            if (i6 < i2) {
                return -1;
            }
            return min;
        }
        return 0;
    }

    public final int a() {
        return this.f16716f;
    }

    public final int b(byte[] bArr, int i2, int i3) {
        int e2 = e(30, bArr, i2, i3);
        if (e2 != -1) {
            if (this.f16713c == -1) {
                long c2 = br.c(this.f16711a, 0);
                this.f16713c = c2;
                if (c2 == 67324752) {
                    this.f16718h = false;
                    this.f16714d = br.c(this.f16711a, 18);
                    this.f16717g = br.a(this.f16711a, 8);
                    this.f16715e = br.a(this.f16711a, 26);
                    int a2 = this.f16715e + 30 + br.a(this.f16711a, 28);
                    this.f16716f = a2;
                    int length = this.f16711a.length;
                    if (length < a2) {
                        do {
                            length += length;
                        } while (length < a2);
                        this.f16711a = Arrays.copyOf(this.f16711a, length);
                    }
                } else {
                    this.f16718h = true;
                }
            }
            int e3 = e(this.f16716f, bArr, i2 + e2, i3 - e2);
            if (e3 == -1) {
                return -1;
            }
            int i4 = e2 + e3;
            if (!this.f16718h && this.f16719i == null) {
                this.f16719i = new String(this.f16711a, 30, this.f16715e);
            }
            return i4;
        }
        return -1;
    }

    public final eu c() {
        int i2 = this.f16712b;
        int i3 = this.f16716f;
        if (i2 < i3) {
            return new bq(this.f16719i, this.f16714d, this.f16717g, true, this.f16718h, Arrays.copyOf(this.f16711a, i2));
        }
        bq bqVar = new bq(this.f16719i, this.f16714d, this.f16717g, false, this.f16718h, Arrays.copyOf(this.f16711a, i3));
        d();
        return bqVar;
    }

    public final void d() {
        this.f16712b = 0;
        this.f16715e = -1;
        this.f16713c = -1L;
        this.f16718h = false;
        this.f16716f = 30;
        this.f16714d = -1L;
        this.f16717g = -1;
        this.f16719i = null;
    }
}
