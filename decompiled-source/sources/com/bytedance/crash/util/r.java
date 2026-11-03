package com.bytedance.crash.util;

import com.facebook.internal.security.CertificateUtil;
import com.meituan.robust.Constants;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: JSONWriter.java */
/* loaded from: classes2.dex */
public class r {

    /* renamed from: a  reason: collision with root package name */
    private final Writer f7310a;

    /* renamed from: b  reason: collision with root package name */
    private final List<a> f7311b = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: JSONWriter.java */
    /* loaded from: classes2.dex */
    public enum a {
        EMPTY_ARRAY,
        NONEMPTY_ARRAY,
        EMPTY_OBJECT,
        DANGLING_KEY,
        NONEMPTY_OBJECT,
        NULL
    }

    public String toString() {
        return "";
    }

    private r(Writer writer) {
        this.f7310a = writer;
    }

    private r a() {
        return a(a.EMPTY_ARRAY, Constants.ARRAY_TYPE);
    }

    private r b() {
        return a(a.EMPTY_ARRAY, a.NONEMPTY_ARRAY, "]");
    }

    private r c() {
        return a(a.EMPTY_OBJECT, "{");
    }

    private r d() {
        return a(a.EMPTY_OBJECT, a.NONEMPTY_OBJECT, "}");
    }

    private r a(a aVar, String str) {
        g();
        this.f7311b.add(aVar);
        this.f7310a.write(str);
        return this;
    }

    private r a(a aVar, a aVar2, String str) {
        e();
        List<a> list = this.f7311b;
        list.remove(list.size() - 1);
        this.f7310a.write(str);
        return this;
    }

    private a e() {
        List<a> list = this.f7311b;
        return list.get(list.size() - 1);
    }

    private void a(a aVar) {
        List<a> list = this.f7311b;
        list.set(list.size() - 1, aVar);
    }

    private r a(Object obj) {
        if (obj instanceof JSONArray) {
            a((JSONArray) obj);
            return this;
        } else if (obj instanceof JSONObject) {
            a((JSONObject) obj);
            return this;
        } else {
            g();
            if (obj == null || obj == JSONObject.NULL) {
                this.f7310a.write("null");
            } else if (obj instanceof Boolean) {
                this.f7310a.write(String.valueOf(obj));
            } else if (obj instanceof Number) {
                this.f7310a.write(JSONObject.numberToString((Number) obj));
            } else {
                a(obj.toString());
            }
            return this;
        }
    }

    public r value(boolean z) {
        g();
        this.f7310a.write(String.valueOf(z));
        return this;
    }

    public r value(double d2) {
        g();
        this.f7310a.write(JSONObject.numberToString(Double.valueOf(d2)));
        return this;
    }

    public r value(long j2) {
        g();
        this.f7310a.write(String.valueOf(j2));
        return this;
    }

    private void a(String str) {
        this.f7310a.write("\"");
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt != '\f') {
                if (charAt != '\r') {
                    if (charAt == '\"' || charAt == '/' || charAt == '\\') {
                        this.f7310a.write(92);
                        this.f7310a.write(charAt);
                    } else {
                        switch (charAt) {
                            case '\b':
                                this.f7310a.write("\\b");
                                continue;
                            case '\t':
                                this.f7310a.write("\\t");
                                continue;
                            case '\n':
                                this.f7310a.write("\\n");
                                continue;
                            default:
                                if (charAt <= 31) {
                                    this.f7310a.write(String.format("\\u%04x", Integer.valueOf(charAt)));
                                    continue;
                                } else {
                                    this.f7310a.write(charAt);
                                    break;
                                }
                        }
                    }
                } else {
                    this.f7310a.write("\\r");
                }
            } else {
                this.f7310a.write("\\f");
            }
        }
        this.f7310a.write("\"");
    }

    private r b(String str) {
        f();
        a(str);
        return this;
    }

    private void f() {
        a e2 = e();
        if (e2 == a.NONEMPTY_OBJECT) {
            this.f7310a.write(44);
        } else if (e2 != a.EMPTY_OBJECT) {
            throw new JSONException("Nesting problem");
        }
        a(a.DANGLING_KEY);
    }

    private void g() {
        if (this.f7311b.isEmpty()) {
            return;
        }
        a e2 = e();
        if (e2 == a.EMPTY_ARRAY) {
            a(a.NONEMPTY_ARRAY);
        } else if (e2 == a.NONEMPTY_ARRAY) {
            this.f7310a.write(44);
        } else if (e2 == a.DANGLING_KEY) {
            this.f7310a.write(CertificateUtil.DELIMITER);
            a(a.NONEMPTY_OBJECT);
        } else if (e2 != a.NULL) {
            throw new JSONException("Nesting problem");
        }
    }

    public static void writeTo(JSONObject jSONObject, Writer writer) {
        new r(writer).a(jSONObject);
        writer.flush();
    }

    public static void writeTo(JSONArray jSONArray, Writer writer) {
        new r(writer).a(jSONArray);
        writer.flush();
    }

    private void a(JSONObject jSONObject) {
        c();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            b(next).a(jSONObject.get(next));
        }
        d();
    }

    private void a(JSONArray jSONArray) {
        a();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            a(jSONArray.get(i2));
        }
        b();
    }
}
