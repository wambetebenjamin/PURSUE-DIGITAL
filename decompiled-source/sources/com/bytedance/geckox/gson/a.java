package com.bytedance.geckox.gson;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
/* compiled from: BooleanTypeAdapter.java */
/* loaded from: classes2.dex */
public class a extends TypeAdapter<Boolean> {
    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Boolean bool) {
        if (bool == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(bool.booleanValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BooleanTypeAdapter.java */
    /* renamed from: com.bytedance.geckox.gson.a$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9059a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f9059a = iArr;
            try {
                iArr[JsonToken.BOOLEAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9059a[JsonToken.NULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9059a[JsonToken.NUMBER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Boolean read(JsonReader jsonReader) {
        JsonToken peek = jsonReader.peek();
        int i2 = AnonymousClass1.f9059a[peek.ordinal()];
        if (i2 != 1) {
            if (i2 == 2) {
                jsonReader.nextNull();
                return null;
            } else if (i2 == 3) {
                return Boolean.valueOf(jsonReader.nextInt() != 0);
            } else {
                throw new JsonParseException("Expected BOOLEAN or NUMBER but was " + peek);
            }
        }
        return Boolean.valueOf(jsonReader.nextBoolean());
    }
}
