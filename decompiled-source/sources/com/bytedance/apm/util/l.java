package com.bytedance.apm.util;

import com.facebook.internal.security.CertificateUtil;
import com.meituan.robust.Constants;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: JsonUtils.java */
/* loaded from: classes.dex */
public class l {
    public static JSONObject copyJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        Iterator<String> keys = jSONObject.keys();
        if (keys == null) {
            return null;
        }
        while (keys.hasNext()) {
            linkedList.add(keys.next());
        }
        try {
            return new JSONObject(jSONObject, (String[]) linkedList.toArray(new String[0]));
        } catch (Exception e2) {
            e2.printStackTrace();
            return jSONObject;
        }
    }

    public static JSONObject copyJson2(JSONObject jSONObject) {
        Iterator<String> keys;
        if (jSONObject == null || (keys = jSONObject.keys()) == null) {
            return null;
        }
        JSONObject jSONObject2 = new JSONObject();
        while (keys.hasNext()) {
            String next = keys.next();
            jSONObject2.put(next, jSONObject.opt(next));
        }
        return jSONObject2;
    }

    public static JSONObject copyJson2(JSONObject jSONObject, JSONObject jSONObject2) {
        Iterator<String> keys;
        if (jSONObject == null || jSONObject2 == null || (keys = jSONObject2.keys()) == null) {
            return null;
        }
        while (keys.hasNext()) {
            String next = keys.next();
            if (!jSONObject2.isNull(next)) {
                jSONObject.put(next, jSONObject2.opt(next));
            }
        }
        return jSONObject;
    }

    public static void combineJson(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null || jSONObject2.length() <= 0) {
            return;
        }
        try {
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, jSONObject2.get(next));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean isEmpty(JSONObject jSONObject) {
        return jSONObject == null || jSONObject.length() == 0;
    }

    public static boolean isEmpty(JSONArray jSONArray) {
        return jSONArray == null || jSONArray.length() == 0;
    }

    public static boolean hasData(JSONObject jSONObject) {
        return jSONObject != null && jSONObject.length() > 0;
    }

    public static boolean hasData(JSONArray jSONArray) {
        return jSONArray != null && jSONArray.length() > 0;
    }

    public static int calc(JSONObject jSONObject) {
        try {
            b bVar = new b();
            a.writeTo(jSONObject, bVar);
            return bVar.length;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static int calc(JSONArray jSONArray) {
        if (jSONArray == null) {
            return 0;
        }
        try {
            b bVar = new b();
            a.writeTo(jSONArray, bVar);
            return bVar.length;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* compiled from: JsonUtils.java */
    /* loaded from: classes.dex */
    private static class b extends Writer {
        public int length;

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        private b() {
            this.length = 0;
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i2, int i3) {
            this.length += i3;
        }

        @Override // java.io.Writer
        public void write(int i2) {
            this.length++;
        }

        @Override // java.io.Writer
        public void write(String str) {
            this.length += str.length();
        }

        @Override // java.io.Writer
        public void write(char[] cArr) {
            this.length += cArr.length;
        }

        @Override // java.io.Writer
        public void write(String str, int i2, int i3) {
            this.length += i3;
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(char c2) {
            this.length++;
            return this;
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence) {
            this.length += charSequence.length();
            return this;
        }

        @Override // java.io.Writer, java.lang.Appendable
        public Writer append(CharSequence charSequence, int i2, int i3) {
            this.length += i3 - i2;
            return this;
        }
    }

    /* compiled from: JsonUtils.java */
    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        final Writer f1710a;

        /* renamed from: b  reason: collision with root package name */
        private final List<EnumC0024a> f1711b = new ArrayList();

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: JsonUtils.java */
        /* renamed from: com.bytedance.apm.util.l$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public enum EnumC0024a {
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

        public a(Writer writer) {
            this.f1710a = writer;
        }

        public a array() {
            return a(EnumC0024a.EMPTY_ARRAY, Constants.ARRAY_TYPE);
        }

        public a endArray() {
            return a(EnumC0024a.EMPTY_ARRAY, EnumC0024a.NONEMPTY_ARRAY, "]");
        }

        public a object() {
            return a(EnumC0024a.EMPTY_OBJECT, "{");
        }

        public a endObject() {
            return a(EnumC0024a.EMPTY_OBJECT, EnumC0024a.NONEMPTY_OBJECT, "}");
        }

        a a(EnumC0024a enumC0024a, String str) {
            c();
            this.f1711b.add(enumC0024a);
            this.f1710a.write(str);
            return this;
        }

        a a(EnumC0024a enumC0024a, EnumC0024a enumC0024a2, String str) {
            a();
            List<EnumC0024a> list = this.f1711b;
            list.remove(list.size() - 1);
            this.f1710a.write(str);
            return this;
        }

        private EnumC0024a a() {
            List<EnumC0024a> list = this.f1711b;
            return list.get(list.size() - 1);
        }

        private void a(EnumC0024a enumC0024a) {
            List<EnumC0024a> list = this.f1711b;
            list.set(list.size() - 1, enumC0024a);
        }

        public a value(Object obj) {
            if (obj instanceof JSONArray) {
                a((JSONArray) obj);
                return this;
            } else if (obj instanceof JSONObject) {
                a((JSONObject) obj);
                return this;
            } else {
                c();
                if (obj == null || obj == JSONObject.NULL) {
                    this.f1710a.write("null");
                } else if (obj instanceof Boolean) {
                    this.f1710a.write(String.valueOf(obj));
                } else if (obj instanceof Number) {
                    this.f1710a.write(JSONObject.numberToString((Number) obj));
                } else {
                    a(obj.toString());
                }
                return this;
            }
        }

        public a value(boolean z) {
            c();
            this.f1710a.write(String.valueOf(z));
            return this;
        }

        public a value(double d2) {
            c();
            this.f1710a.write(JSONObject.numberToString(Double.valueOf(d2)));
            return this;
        }

        public a value(long j2) {
            c();
            this.f1710a.write(String.valueOf(j2));
            return this;
        }

        private void a(String str) {
            this.f1710a.write("\"");
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (charAt != '\f') {
                    if (charAt != '\r') {
                        if (charAt == '\"' || charAt == '/' || charAt == '\\') {
                            this.f1710a.write(92);
                            this.f1710a.write(charAt);
                        } else {
                            switch (charAt) {
                                case '\b':
                                    this.f1710a.write("\\b");
                                    continue;
                                case '\t':
                                    this.f1710a.write("\\t");
                                    continue;
                                case '\n':
                                    this.f1710a.write("\\n");
                                    continue;
                                default:
                                    if (charAt <= 31) {
                                        this.f1710a.write(String.format("\\u%04x", Integer.valueOf(charAt)));
                                        continue;
                                    } else {
                                        this.f1710a.write(charAt);
                                        break;
                                    }
                            }
                        }
                    } else {
                        this.f1710a.write("\\r");
                    }
                } else {
                    this.f1710a.write("\\f");
                }
            }
            this.f1710a.write("\"");
        }

        public a key(String str) {
            b();
            a(str);
            return this;
        }

        private void b() {
            EnumC0024a a2 = a();
            if (a2 == EnumC0024a.NONEMPTY_OBJECT) {
                this.f1710a.write(44);
            } else if (a2 != EnumC0024a.EMPTY_OBJECT) {
                throw new JSONException("Nesting problem");
            }
            a(EnumC0024a.DANGLING_KEY);
        }

        private void c() {
            if (this.f1711b.isEmpty()) {
                return;
            }
            EnumC0024a a2 = a();
            if (a2 == EnumC0024a.EMPTY_ARRAY) {
                a(EnumC0024a.NONEMPTY_ARRAY);
            } else if (a2 == EnumC0024a.NONEMPTY_ARRAY) {
                this.f1710a.write(44);
            } else if (a2 == EnumC0024a.DANGLING_KEY) {
                this.f1710a.write(CertificateUtil.DELIMITER);
                a(EnumC0024a.NONEMPTY_OBJECT);
            } else if (a2 != EnumC0024a.NULL) {
                throw new JSONException("Nesting problem");
            }
        }

        public static void writeTo(JSONObject jSONObject, Writer writer) {
            new a(writer).a(jSONObject);
            writer.flush();
        }

        public static void writeTo(JSONArray jSONArray, Writer writer) {
            new a(writer).a(jSONArray);
            writer.flush();
        }

        private void a(JSONObject jSONObject) {
            object();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                key(next).value(jSONObject.get(next));
            }
            endObject();
        }

        private void a(JSONArray jSONArray) {
            array();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                value(jSONArray.get(i2));
            }
            endArray();
        }
    }

    public static JSONObject optJSONObject(JSONObject jSONObject, String str, String str2) {
        JSONObject optJSONObject;
        if (jSONObject == null || (optJSONObject = jSONObject.optJSONObject(str)) == null) {
            return null;
        }
        return optJSONObject.optJSONObject(str2);
    }

    public static JSONObject optJSONObject(JSONObject jSONObject, String str, String str2, String str3) {
        JSONObject optJSONObject = optJSONObject(jSONObject, str, str2);
        if (optJSONObject == null) {
            return null;
        }
        return optJSONObject.optJSONObject(str3);
    }

    public static int optInt(JSONObject jSONObject, String str, String str2, String str3) {
        JSONObject optJSONObject = optJSONObject(jSONObject, str, str2);
        if (optJSONObject == null) {
            return 0;
        }
        return optJSONObject.optInt(str3, 0);
    }

    public static boolean optBoolean(JSONObject jSONObject, String str, String str2, boolean z) {
        JSONObject optJSONObject;
        return (jSONObject == null || (optJSONObject = jSONObject.optJSONObject(str)) == null) ? z : optJSONObject.optBoolean(str2, z);
    }

    public static JSONObject deepCopy(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return new JSONObject(jSONObject.toString());
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    public static JSONObject safeCopyJson2(JSONObject jSONObject) {
        Iterator<String> keys;
        if (jSONObject == null || (keys = jSONObject.keys()) == null) {
            return null;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject2.put(next, jSONObject.opt(next));
            }
            return jSONObject2;
        } catch (Exception unused) {
            return jSONObject;
        }
    }
}
