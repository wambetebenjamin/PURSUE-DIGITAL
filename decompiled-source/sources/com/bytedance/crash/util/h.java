package com.bytedance.crash.util;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
/* compiled from: DigestPrintWriter.java */
/* loaded from: classes2.dex */
public class h extends PrintWriter {

    /* renamed from: a  reason: collision with root package name */
    private MessageDigest f7300a;

    /* renamed from: b  reason: collision with root package name */
    private Charset f7301b;

    /* renamed from: c  reason: collision with root package name */
    private a f7302c;

    /* compiled from: DigestPrintWriter.java */
    /* loaded from: classes2.dex */
    public static class a {
        public boolean shouldDigest(String str) {
            return true;
        }
    }

    public h(Writer writer, MessageDigest messageDigest) {
        super(writer);
        this.f7300a = messageDigest;
        if (messageDigest != null) {
            this.f7301b = Charset.defaultCharset();
        }
    }

    public h(OutputStream outputStream, MessageDigest messageDigest, a aVar) {
        super(outputStream);
        this.f7300a = messageDigest;
        this.f7302c = aVar;
        if (messageDigest != null) {
            this.f7301b = Charset.defaultCharset();
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(char[] cArr, int i2, int i3) {
        super.write(cArr, i2, i3);
        MessageDigest messageDigest = this.f7300a;
        if (messageDigest != null) {
            messageDigest.update(this.f7301b.encode(CharBuffer.wrap(cArr)).array());
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(int i2) {
        super.write(i2);
        MessageDigest messageDigest = this.f7300a;
        if (messageDigest != null) {
            messageDigest.update((byte) i2);
        }
    }

    @Override // java.io.PrintWriter, java.io.Writer
    public void write(String str, int i2, int i3) {
        super.write(str, i2, i3);
        if (this.f7300a != null) {
            a aVar = this.f7302c;
            if (aVar == null || aVar.shouldDigest(str)) {
                this.f7300a.update(this.f7301b.encode(CharBuffer.wrap(str, i2, i3 + i2)).array());
            }
        }
    }

    public static String getMd5(String str) {
        return d.a(str);
    }
}
