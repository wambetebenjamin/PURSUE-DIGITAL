package com.bytedance.common.network;

import com.bytedance.librarian.c;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Locale;
/* compiled from: MimeType.java */
/* loaded from: classes2.dex */
public class c implements Externalizable {

    /* renamed from: d  reason: collision with root package name */
    private static final String f5082d = "()<>@,;:/[]?=\\\"";

    /* renamed from: a  reason: collision with root package name */
    private String f5083a;

    /* renamed from: b  reason: collision with root package name */
    private String f5084b;

    /* renamed from: c  reason: collision with root package name */
    private d f5085c;

    public c() {
        this.f5083a = "application";
        this.f5084b = "*";
        this.f5085c = new d();
    }

    public c(String str) {
        a(str);
    }

    public c(String str, String str2) {
        if (b(str)) {
            this.f5083a = str.toLowerCase(Locale.ENGLISH);
            if (b(str2)) {
                this.f5084b = str2.toLowerCase(Locale.ENGLISH);
                this.f5085c = new d();
                return;
            }
            throw new e("Sub type is invalid.");
        }
        throw new e("Primary type is invalid.");
    }

    private void a(String str) {
        int indexOf = str.indexOf(47);
        int indexOf2 = str.indexOf(59);
        if (indexOf < 0 && indexOf2 < 0) {
            throw new e("Unable to find a sub type.");
        }
        if (indexOf < 0 && indexOf2 >= 0) {
            throw new e("Unable to find a sub type.");
        }
        if (indexOf >= 0 && indexOf2 < 0) {
            this.f5083a = str.substring(0, indexOf).trim().toLowerCase(Locale.ENGLISH);
            this.f5084b = str.substring(indexOf + 1).trim().toLowerCase(Locale.ENGLISH);
            this.f5085c = new d();
        } else if (indexOf < indexOf2) {
            this.f5083a = str.substring(0, indexOf).trim().toLowerCase(Locale.ENGLISH);
            this.f5084b = str.substring(indexOf + 1, indexOf2).trim().toLowerCase(Locale.ENGLISH);
            this.f5085c = new d(str.substring(indexOf2));
        } else {
            throw new e("Unable to find a sub type.");
        }
        if (!b(this.f5083a)) {
            throw new e("Primary type is invalid.");
        }
        if (!b(this.f5084b)) {
            throw new e("Sub type is invalid.");
        }
    }

    public String getPrimaryType() {
        return this.f5083a;
    }

    public void setPrimaryType(String str) {
        if (!b(this.f5083a)) {
            throw new e("Primary type is invalid.");
        }
        this.f5083a = str.toLowerCase(Locale.ENGLISH);
    }

    public String getSubType() {
        return this.f5084b;
    }

    public void setSubType(String str) {
        if (!b(this.f5084b)) {
            throw new e("Sub type is invalid.");
        }
        this.f5084b = str.toLowerCase(Locale.ENGLISH);
    }

    public d getParameters() {
        return this.f5085c;
    }

    public String getParameter(String str) {
        return this.f5085c.get(str);
    }

    public void setParameter(String str, String str2) {
        this.f5085c.set(str, str2);
    }

    public void removeParameter(String str) {
        this.f5085c.remove(str);
    }

    public String toString() {
        return getBaseType() + this.f5085c.toString();
    }

    public String getBaseType() {
        return this.f5083a + c.a.SEPARATOR + this.f5084b;
    }

    public boolean match(c cVar) {
        return this.f5083a.equals(cVar.getPrimaryType()) && (this.f5084b.equals("*") || cVar.getSubType().equals("*") || this.f5084b.equals(cVar.getSubType()));
    }

    public boolean match(String str) {
        return match(new c(str));
    }

    @Override // java.io.Externalizable
    public void writeExternal(ObjectOutput objectOutput) {
        objectOutput.writeUTF(toString());
        objectOutput.flush();
    }

    @Override // java.io.Externalizable
    public void readExternal(ObjectInput objectInput) {
        try {
            a(objectInput.readUTF());
        } catch (e e2) {
            throw new IOException(e2.toString());
        }
    }

    private static boolean a(char c2) {
        return c2 > ' ' && c2 < 127 && f5082d.indexOf(c2) < 0;
    }

    private boolean b(String str) {
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
