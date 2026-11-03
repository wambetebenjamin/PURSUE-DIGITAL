package com.oasis.utils;

import android.os.Bundle;
import com.oasis.Logger.Logger;
import java.util.Iterator;
import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class Utils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17769a = "com.oasis.utils.Utils";

    /* loaded from: classes9.dex */
    public static class JsonStringBuilder {

        /* renamed from: b  reason: collision with root package name */
        private static int f17770b = 512;

        /* renamed from: a  reason: collision with root package name */
        private StringBuilder f17771a;

        public JsonStringBuilder() {
            this(f17770b);
        }

        public JsonStringBuilder(int i2) {
            StringBuilder sb = new StringBuilder(i2);
            this.f17771a = sb;
            sb.append('{');
        }

        private void a(String str) {
            this.f17771a.append(Typography.quote);
            this.f17771a.append(str);
            this.f17771a.append("\":");
        }

        private void b(String str) {
            StringBuilder sb;
            String str2;
            StringBuilder append;
            this.f17771a.append("\"");
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (charAt == '\f') {
                    sb = this.f17771a;
                    str2 = "\\f";
                } else if (charAt != '\r') {
                    if (charAt == '\"' || charAt == '/' || charAt == '\\') {
                        append = this.f17771a.append('\\');
                    } else {
                        switch (charAt) {
                            case '\b':
                                sb = this.f17771a;
                                str2 = "\\b";
                                break;
                            case '\t':
                                sb = this.f17771a;
                                str2 = "\\t";
                                break;
                            case '\n':
                                sb = this.f17771a;
                                str2 = "\\n";
                                break;
                            default:
                                if (charAt <= 31) {
                                    this.f17771a.append(String.format("\\u%04x", Integer.valueOf(charAt)));
                                    break;
                                } else {
                                    append = this.f17771a;
                                    break;
                                }
                        }
                    }
                    append.append(charAt);
                } else {
                    sb = this.f17771a;
                    str2 = "\\r";
                }
                sb.append(str2);
            }
            this.f17771a.append("\",");
        }

        public static int setDefaultCapacity(int i2) {
            f17770b = i2;
            return i2;
        }

        public JsonStringBuilder append(String str, double d2) {
            a(str);
            this.f17771a.append(d2);
            this.f17771a.append(",");
            return this;
        }

        public JsonStringBuilder append(String str, float f2) {
            a(str);
            this.f17771a.append(f2);
            this.f17771a.append(",");
            return this;
        }

        public JsonStringBuilder append(String str, int i2) {
            a(str);
            this.f17771a.append(i2);
            this.f17771a.append(",");
            return this;
        }

        public JsonStringBuilder append(String str, long j2) {
            a(str);
            this.f17771a.append(j2);
            this.f17771a.append(",");
            return this;
        }

        public JsonStringBuilder append(String str, JsonStringBuilder jsonStringBuilder) {
            StringBuilder sb;
            if (jsonStringBuilder == null) {
                return this;
            }
            if (jsonStringBuilder == this) {
                sb = this.f17771a.append("\"").append(str).append("\":").append(jsonStringBuilder.build());
            } else {
                a(str);
                this.f17771a.append(jsonStringBuilder);
                sb = this.f17771a;
            }
            sb.append(",");
            return this;
        }

        public JsonStringBuilder append(String str, String str2) {
            if (str2 == null) {
                return this;
            }
            a(str);
            b(str2);
            return this;
        }

        public JsonStringBuilder append(String str, short s) {
            a(str);
            this.f17771a.append((int) s);
            this.f17771a.append(",");
            return this;
        }

        public JsonStringBuilder append(String str, boolean z) {
            a(str);
            this.f17771a.append(z);
            this.f17771a.append(",");
            return this;
        }

        public String build() {
            if (this.f17771a.length() == 1) {
                return "{}";
            }
            StringBuilder sb = this.f17771a;
            sb.setLength(sb.length() - 1);
            this.f17771a.append('}');
            String sb2 = this.f17771a.toString();
            StringBuilder sb3 = this.f17771a;
            sb3.setLength(sb3.length() - 1);
            this.f17771a.append(',');
            return sb2;
        }

        public int clear() {
            this.f17771a.setLength(1);
            return this.f17771a.capacity();
        }

        public int clearAndRealloc() {
            return clearAndRealloc(f17770b);
        }

        public int clearAndRealloc(int i2) {
            StringBuilder sb = new StringBuilder(i2);
            this.f17771a = sb;
            sb.append('{');
            return this.f17771a.capacity();
        }

        public String toString() {
            return build();
        }
    }

    public static JSONObject bundleToJsonObject(Bundle bundle) {
        Object bundleToJsonObject;
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof Bundle) {
                try {
                    bundleToJsonObject = bundleToJsonObject((Bundle) obj);
                } catch (JSONException unused) {
                }
            } else {
                bundleToJsonObject = JSONObject.wrap(obj);
            }
            jSONObject.put(str, bundleToJsonObject);
        }
        return jSONObject;
    }

    public static Bundle jsonToBundle(String str) {
        try {
            return jsonToBundle(new JSONObject(str));
        } catch (JSONException e2) {
            Logger.e(f17769a, "Error: parse json error: " + e2.toString());
            return null;
        }
    }

    public static Bundle jsonToBundle(JSONObject jSONObject) {
        Object obj;
        String str = f17769a + ".jsonToBundle";
        Bundle bundle = new Bundle();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            try {
                obj = jSONObject.get(next);
            } catch (JSONException unused) {
                obj = null;
            }
            if (obj instanceof Integer) {
                bundle.putInt(next, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                bundle.putLong(next, ((Long) obj).longValue());
            } else if (obj instanceof Float) {
                bundle.putFloat(next, ((Float) obj).floatValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(next, ((Double) obj).doubleValue());
            } else if (obj instanceof String) {
                bundle.putString(next, (String) obj);
            } else if (obj instanceof Boolean) {
                bundle.putBoolean(next, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Byte) {
                bundle.putByte(next, ((Byte) obj).byteValue());
            } else if (obj instanceof Character) {
                bundle.putChar(next, ((Character) obj).charValue());
            } else if (obj instanceof Short) {
                bundle.putShort(next, ((Short) obj).shortValue());
            } else if (obj instanceof int[]) {
                bundle.putIntArray(next, (int[]) obj);
            } else if (obj instanceof long[]) {
                bundle.putLongArray(next, (long[]) obj);
            } else if (obj instanceof float[]) {
                bundle.putFloatArray(next, (float[]) obj);
            } else if (obj instanceof double[]) {
                bundle.putDoubleArray(next, (double[]) obj);
            } else if (obj instanceof String[]) {
                bundle.putStringArray(next, (String[]) obj);
            } else if (obj instanceof boolean[]) {
                bundle.putBooleanArray(next, (boolean[]) obj);
            } else if (obj instanceof byte[]) {
                bundle.putByteArray(next, (byte[]) obj);
            } else if (obj instanceof char[]) {
                bundle.putCharArray(next, (char[]) obj);
            } else if (obj instanceof short[]) {
                bundle.putShortArray(next, (short[]) obj);
            } else if (!(obj instanceof JSONObject)) {
                Logger.e(str, String.format("Error: Unsupported type: %s: (%s)%s", next, obj.getClass().getName(), obj.toString()));
                return null;
            } else {
                bundle.putBundle(next, jsonToBundle((JSONObject) obj));
            }
        }
        return bundle;
    }
}
