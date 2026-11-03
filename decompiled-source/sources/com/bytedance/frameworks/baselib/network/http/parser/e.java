package com.bytedance.frameworks.baselib.network.http.parser;

import com.bytedance.librarian.c;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Locale;
/* compiled from: MimeType.java */
/* loaded from: classes2.dex */
public class e implements Externalizable {

    /* renamed from: d  reason: collision with root package name */
    private static final String f8249d = "()<>@,;:/[]?=\\\"";

    /* renamed from: a  reason: collision with root package name */
    private String f8250a;

    /* renamed from: b  reason: collision with root package name */
    private String f8251b;

    /* renamed from: c  reason: collision with root package name */
    private f f8252c;

    public e() {
        this.f8250a = "application";
        this.f8251b = "*";
        this.f8252c = new f();
    }

    public e(String str) {
        f(str);
    }

    public e(String str, String str2) {
        if (g(str)) {
            this.f8250a = str.toLowerCase(Locale.ENGLISH);
            if (g(str2)) {
                this.f8251b = str2.toLowerCase(Locale.ENGLISH);
                this.f8252c = new f();
                return;
            }
            throw new g("Sub type is invalid.");
        }
        throw new g("Primary type is invalid.");
    }

    private void f(String str) {
        int indexOf = str.indexOf(47);
        int indexOf2 = str.indexOf(59);
        if (indexOf < 0 && indexOf2 < 0) {
            throw new g("Unable to find a sub type.");
        }
        if (indexOf < 0 && indexOf2 >= 0) {
            throw new g("Unable to find a sub type.");
        }
        if (indexOf >= 0 && indexOf2 < 0) {
            this.f8250a = str.substring(0, indexOf).trim().toLowerCase(Locale.ENGLISH);
            this.f8251b = str.substring(indexOf + 1).trim().toLowerCase(Locale.ENGLISH);
            this.f8252c = new f();
        } else if (indexOf < indexOf2) {
            this.f8250a = str.substring(0, indexOf).trim().toLowerCase(Locale.ENGLISH);
            this.f8251b = str.substring(indexOf + 1, indexOf2).trim().toLowerCase(Locale.ENGLISH);
            this.f8252c = new f(str.substring(indexOf2));
        } else {
            throw new g("Unable to find a sub type.");
        }
        if (!g(this.f8250a)) {
            throw new g("Primary type is invalid.");
        }
        if (!g(this.f8251b)) {
            throw new g("Sub type is invalid.");
        }
    }

    public String a() {
        return this.f8250a;
    }

    public void a(String str) {
        if (!g(this.f8250a)) {
            throw new g("Primary type is invalid.");
        }
        this.f8250a = str.toLowerCase(Locale.ENGLISH);
    }

    public String b() {
        return this.f8251b;
    }

    public void b(String str) {
        if (!g(this.f8251b)) {
            throw new g("Sub type is invalid.");
        }
        this.f8251b = str.toLowerCase(Locale.ENGLISH);
    }

    public f c() {
        return this.f8252c;
    }

    public String c(String str) {
        return this.f8252c.b(str);
    }

    public void a(String str, String str2) {
        this.f8252c.a(str, str2);
    }

    public void d(String str) {
        this.f8252c.c(str);
    }

    public String toString() {
        return d() + this.f8252c.toString();
    }

    public String d() {
        return this.f8250a + c.a.SEPARATOR + this.f8251b;
    }

    public boolean a(e eVar) {
        return this.f8250a.equals(eVar.a()) && (this.f8251b.equals("*") || eVar.b().equals("*") || this.f8251b.equals(eVar.b()));
    }

    public boolean e(String str) {
        return a(new e(str));
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeUTF(toString());
        objectOutput.flush();
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) {
        try {
            f(objectInput.readUTF());
        } catch (g e2) {
            throw new IOException(e2.toString());
        }
    }

    private static boolean a(char c2) {
        return c2 > ' ' && c2 < 127 && f8249d.indexOf(c2) < 0;
    }

    private boolean g(String str) {
        int length = str.length();
        if (length > 0) {
            for (int i2 = 0; i2 < length; i2++) {
                if (!a(str.charAt(i2))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
