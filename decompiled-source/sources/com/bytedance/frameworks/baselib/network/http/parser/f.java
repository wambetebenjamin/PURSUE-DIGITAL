package com.bytedance.frameworks.baselib.network.http.parser;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import kotlin.text.Typography;
/* compiled from: MimeTypeParameterList.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: b  reason: collision with root package name */
    private static final String f8253b = "()<>@,;:/[]?=\\\"";

    /* renamed from: a  reason: collision with root package name */
    private Hashtable f8254a = new Hashtable();

    public f() {
    }

    public f(String str) {
        a(str);
    }

    protected void a(String str) {
        int length;
        int i2;
        String substring;
        if (str != null && (length = str.length()) > 0) {
            int a2 = a(str, 0);
            while (a2 < length && str.charAt(a2) == ';') {
                int a3 = a(str, a2 + 1);
                if (a3 >= length) {
                    return;
                }
                int i3 = a3;
                while (i3 < length && a(str.charAt(i3))) {
                    i3++;
                }
                String lowerCase = str.substring(a3, i3).toLowerCase(Locale.ENGLISH);
                int a4 = a(str, i3);
                if (a4 >= length || str.charAt(a4) != '=') {
                    throw new g("Couldn't find the '=' that separates a parameter name from its value.");
                }
                int a5 = a(str, a4 + 1);
                if (a5 >= length) {
                    throw new g("Couldn't find a value for parameter named " + lowerCase);
                }
                char charAt = str.charAt(a5);
                if (charAt == '\"') {
                    int i4 = a5 + 1;
                    if (i4 >= length) {
                        throw new g("Encountered unterminated quoted parameter value.");
                    }
                    int i5 = i4;
                    while (i5 < length) {
                        charAt = str.charAt(i5);
                        if (charAt == '\"') {
                            break;
                        }
                        if (charAt == '\\') {
                            i5++;
                        }
                        i5++;
                    }
                    if (charAt != '\"') {
                        throw new g("Encountered unterminated quoted parameter value.");
                    }
                    substring = e(str.substring(i4, i5));
                    i2 = i5 + 1;
                } else if (a(charAt)) {
                    i2 = a5;
                    while (i2 < length && a(str.charAt(i2))) {
                        i2++;
                    }
                    substring = str.substring(a5, i2);
                } else {
                    throw new g("Unexpected character encountered at index " + a5);
                }
                this.f8254a.put(lowerCase, substring);
                a2 = a(str, i2);
            }
            if (a2 < length) {
                throw new g("More characters encountered in input than expected.");
            }
        }
    }

    public int a() {
        return this.f8254a.size();
    }

    public boolean b() {
        return this.f8254a.isEmpty();
    }

    public String b(String str) {
        return (String) this.f8254a.get(str.trim().toLowerCase(Locale.ENGLISH));
    }

    public void a(String str, String str2) {
        this.f8254a.put(str.trim().toLowerCase(Locale.ENGLISH), str2);
    }

    public void c(String str) {
        this.f8254a.remove(str.trim().toLowerCase(Locale.ENGLISH));
    }

    public Enumeration c() {
        return this.f8254a.keys();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.ensureCapacity(this.f8254a.size() * 16);
        Enumeration keys = this.f8254a.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            stringBuffer.append("; ");
            stringBuffer.append(str);
            stringBuffer.append('=');
            stringBuffer.append(d((String) this.f8254a.get(str)));
        }
        return stringBuffer.toString();
    }

    private static boolean a(char c2) {
        return c2 > ' ' && c2 < 127 && f8253b.indexOf(c2) < 0;
    }

    private static int a(String str, int i2) {
        int length = str.length();
        while (i2 < length && Character.isWhitespace(str.charAt(i2))) {
            i2++;
        }
        return i2;
    }

    private static String d(String str) {
        int length = str.length();
        boolean z = false;
        for (int i2 = 0; i2 < length && !z; i2++) {
            z = !a(str.charAt(i2));
        }
        if (z) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.ensureCapacity((int) (length * 1.5d));
            stringBuffer.append(Typography.quote);
            for (int i3 = 0; i3 < length; i3++) {
                char charAt = str.charAt(i3);
                if (charAt == '\\' || charAt == '\"') {
                    stringBuffer.append('\\');
                }
                stringBuffer.append(charAt);
            }
            stringBuffer.append(Typography.quote);
            return stringBuffer.toString();
        }
        return str;
    }

    private static String e(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.ensureCapacity(length);
        boolean z = false;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (!z && charAt != '\\') {
                stringBuffer.append(charAt);
            } else if (z) {
                stringBuffer.append(charAt);
                z = false;
            } else {
                z = true;
            }
        }
        return stringBuffer.toString();
    }
}
