package com.vk.api.sdk.internal;

import android.util.JsonReader;
import android.util.JsonToken;
import java.io.StringReader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: JsonUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lcom/vk/api/sdk/internal/JsonUtils;", "", "()V", "containsElement", "", "jsonString", "", "name", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class JsonUtils {
    public static final JsonUtils INSTANCE = new JsonUtils();

    private JsonUtils() {
    }

    public final boolean containsElement(String jsonString, String name) {
        JsonToken peek;
        Intrinsics.checkParameterIsNotNull(jsonString, "jsonString");
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (jsonString.length() == 0) {
            return false;
        }
        JsonReader jsonReader = new JsonReader(new StringReader(jsonString));
        if (jsonReader.hasNext() && jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
            jsonReader.beginObject();
            while (jsonReader.hasNext() && (peek = jsonReader.peek()) != JsonToken.END_DOCUMENT) {
                if (peek != JsonToken.NAME) {
                    jsonReader.skipValue();
                } else if (Intrinsics.areEqual(name, jsonReader.nextName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
