package com.bytedance.frameworks.baselib.network.http.impl;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/* compiled from: SerializableHttpCookie.java */
/* loaded from: classes2.dex */
public class h implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7900a = "h";

    /* renamed from: b  reason: collision with root package name */
    private static final long f7901b = 6374381323722046732L;

    /* renamed from: e  reason: collision with root package name */
    private static final char[] f7902e = "0123456789ABCDEF".toCharArray();

    /* renamed from: c  reason: collision with root package name */
    private transient e f7903c;

    /* renamed from: d  reason: collision with root package name */
    private long f7904d = System.currentTimeMillis();

    public h(e eVar) {
        this.f7903c = eVar;
    }

    public boolean a() {
        long e2 = this.f7903c.e();
        return e2 != -1 && (System.currentTimeMillis() - this.f7904d) / 1000 > e2;
    }

    public e b() {
        return this.f7903c;
    }

    public Long c() {
        return Long.valueOf(this.f7904d);
    }

    public boolean equals(Object obj) {
        if (obj instanceof e) {
            return this.f7903c.equals(obj);
        }
        if (obj instanceof h) {
            return this.f7903c.equals(((h) obj).f7903c);
        }
        return false;
    }

    public int hashCode() {
        return this.f7903c.hashCode();
    }

    private boolean e() {
        return this.f7903c.j();
    }

    private void a(boolean z) {
        this.f7903c.c(z);
    }

    public String d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(this);
            return a(byteArrayOutputStream.toByteArray());
        } catch (IOException e2) {
            Log.d(f7900a, "IOException in encodeCookie", e2);
            return null;
        }
    }

    public static h a(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                return (h) new com.bytedance.frameworks.baselib.network.http.io.a(new ByteArrayInputStream(b(str)), h.class).readObject();
            } catch (IOException e2) {
                Log.d(f7900a, "IOException in decodeCookie", e2);
            } catch (ClassNotFoundException e3) {
                Log.d(f7900a, "ClassNotFoundException in decodeCookie", e3);
            }
        }
        return null;
    }

    private void a(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeObject(this.f7903c.f());
        objectOutputStream.writeObject(this.f7903c.k());
        objectOutputStream.writeObject(this.f7903c.a());
        objectOutputStream.writeObject(this.f7903c.b());
        objectOutputStream.writeObject(this.f7903c.d());
        objectOutputStream.writeLong(this.f7903c.e());
        objectOutputStream.writeObject(this.f7903c.g());
        objectOutputStream.writeObject(this.f7903c.h());
        objectOutputStream.writeInt(this.f7903c.l());
        objectOutputStream.writeBoolean(this.f7903c.i());
        objectOutputStream.writeBoolean(this.f7903c.c());
        objectOutputStream.writeBoolean(e());
        objectOutputStream.writeLong(this.f7904d);
    }

    private void a(ObjectInputStream objectInputStream) {
        e eVar = new e((String) objectInputStream.readObject(), (String) objectInputStream.readObject());
        this.f7903c = eVar;
        eVar.b((String) objectInputStream.readObject());
        this.f7903c.c((String) objectInputStream.readObject());
        this.f7903c.d((String) objectInputStream.readObject());
        this.f7903c.a(objectInputStream.readLong());
        this.f7903c.e((String) objectInputStream.readObject());
        this.f7903c.f((String) objectInputStream.readObject());
        this.f7903c.a(objectInputStream.readInt());
        this.f7903c.b(objectInputStream.readBoolean());
        this.f7903c.a(objectInputStream.readBoolean());
        a(objectInputStream.readBoolean());
        this.f7904d = objectInputStream.readLong();
    }

    private String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            int i3 = bArr[i2] & 255;
            int i4 = i2 * 2;
            char[] cArr2 = f7902e;
            cArr[i4] = cArr2[i3 >>> 4];
            cArr[i4 + 1] = cArr2[i3 & 15];
        }
        return new String(cArr);
    }

    private static byte[] b(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i2 = 0; i2 < length; i2 += 2) {
            bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i2 + 1), 16));
        }
        return bArr;
    }

    public static int a(String str, int i2) {
        if (i2 != -1) {
            return i2;
        }
        if ("http".equalsIgnoreCase(str)) {
            return 80;
        }
        return "https".equalsIgnoreCase(str) ? 443 : -1;
    }
}
