package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class cn extends OutputStream {

    /* renamed from: a  reason: collision with root package name */
    private final ds f16617a = new ds();

    /* renamed from: b  reason: collision with root package name */
    private final File f16618b;

    /* renamed from: c  reason: collision with root package name */
    private final eo f16619c;

    /* renamed from: d  reason: collision with root package name */
    private long f16620d;

    /* renamed from: e  reason: collision with root package name */
    private long f16621e;

    /* renamed from: f  reason: collision with root package name */
    private FileOutputStream f16622f;

    /* renamed from: g  reason: collision with root package name */
    private eu f16623g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cn(File file, eo eoVar) {
        this.f16618b = file;
        this.f16619c = eoVar;
    }

    @Override // java.io.OutputStream
    public final void write(int i2) throws IOException {
        write(new byte[]{(byte) i2}, 0, 1);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i2, int i3) throws IOException {
        int min;
        while (i3 > 0) {
            if (this.f16620d == 0 && this.f16621e == 0) {
                int b2 = this.f16617a.b(bArr, i2, i3);
                if (b2 == -1) {
                    return;
                }
                i2 += b2;
                i3 -= b2;
                eu c2 = this.f16617a.c();
                this.f16623g = c2;
                if (c2.d()) {
                    this.f16620d = 0L;
                    this.f16619c.l(this.f16623g.f(), 0, this.f16623g.f().length);
                    this.f16621e = this.f16623g.f().length;
                } else if (!this.f16623g.h() || this.f16623g.g()) {
                    byte[] f2 = this.f16623g.f();
                    this.f16619c.l(f2, 0, f2.length);
                    this.f16620d = this.f16623g.b();
                } else {
                    this.f16619c.j(this.f16623g.f());
                    File file = new File(this.f16618b, this.f16623g.c());
                    file.getParentFile().mkdirs();
                    this.f16620d = this.f16623g.b();
                    this.f16622f = new FileOutputStream(file);
                }
            }
            if (!this.f16623g.g()) {
                if (this.f16623g.d()) {
                    this.f16619c.e(this.f16621e, bArr, i2, i3);
                    this.f16621e += i3;
                    min = i3;
                } else if (!this.f16623g.h()) {
                    min = (int) Math.min(i3, this.f16620d);
                    this.f16619c.e((this.f16623g.f().length + this.f16623g.b()) - this.f16620d, bArr, i2, min);
                    this.f16620d -= min;
                } else {
                    min = (int) Math.min(i3, this.f16620d);
                    this.f16622f.write(bArr, i2, min);
                    long j2 = this.f16620d - min;
                    this.f16620d = j2;
                    if (j2 == 0) {
                        this.f16622f.close();
                    }
                }
                i2 += min;
                i3 -= min;
            }
        }
    }
}
