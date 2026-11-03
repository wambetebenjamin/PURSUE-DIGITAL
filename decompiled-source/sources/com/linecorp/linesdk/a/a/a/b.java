package com.linecorp.linesdk.a.a.a;

import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public final class b implements c<JSONObject> {

    /* renamed from: a  reason: collision with root package name */
    private final d f17249a = new d();

    @Override // com.linecorp.linesdk.a.a.a.c
    /* renamed from: b */
    public final JSONObject a(InputStream inputStream) {
        try {
            return new JSONObject(this.f17249a.a(inputStream));
        } catch (JSONException e2) {
            throw new IOException(e2);
        }
    }
}
