package com.linecorp.linesdk.a.a;

import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
abstract class a<T> implements com.linecorp.linesdk.a.a.a.c<T> {

    /* renamed from: a  reason: collision with root package name */
    private final com.linecorp.linesdk.a.a.a.b f17243a;

    abstract T a(JSONObject jSONObject);

    /* JADX INFO: Access modifiers changed from: package-private */
    public a() {
        this(new com.linecorp.linesdk.a.a.a.b());
    }

    private a(com.linecorp.linesdk.a.a.a.b bVar) {
        this.f17243a = bVar;
    }

    @Override // com.linecorp.linesdk.a.a.a.c
    public final T a(InputStream inputStream) {
        try {
            return a(this.f17243a.a(inputStream));
        } catch (JSONException e2) {
            throw new IOException(e2);
        }
    }
}
