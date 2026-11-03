package com.bytedance.applog.priority;

import com.bytedance.applog.priority.original.o;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class NativeLazyString {
    private final o<String> loader;

    public NativeLazyString(final o<JSONObject> oVar) {
        this.loader = new o() { // from class: com.bytedance.applog.priority.NativeLazyString$$ExternalSyntheticLambda0
            @Override // com.bytedance.applog.priority.original.o
            public final Object load() {
                String jSONObject;
                jSONObject = ((JSONObject) o.this.load()).toString();
                return jSONObject;
            }
        };
    }

    public String getValue() {
        return this.loader.load();
    }
}
