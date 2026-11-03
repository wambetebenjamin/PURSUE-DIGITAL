package com.linecorp.linesdk.a.a;

import android.content.Context;
import android.net.Uri;
import com.linecorp.linesdk.BuildConfig;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.LineProfile;
import gsdk.impl.account.toutiao.f;
import java.util.Collections;
import java.util.HashMap;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private static final com.linecorp.linesdk.a.a.a.c<LineProfile> f17262a = new a();

    /* renamed from: b  reason: collision with root package name */
    private final Uri f17263b;

    /* renamed from: c  reason: collision with root package name */
    private final com.linecorp.linesdk.a.a.a.a f17264c;

    public d(Context context, Uri uri) {
        this(uri, new com.linecorp.linesdk.a.a.a.a(context, BuildConfig.VERSION_NAME));
    }

    private d(Uri uri, com.linecorp.linesdk.a.a.a.a aVar) {
        this.f17263b = uri;
        this.f17264c = aVar;
    }

    public final LineApiResponse<LineProfile> a(com.linecorp.linesdk.a.d dVar) {
        Uri build = this.f17263b.buildUpon().appendPath("profile").build();
        HashMap hashMap = new HashMap(1);
        hashMap.put("Authorization", "Bearer " + dVar.f17271a);
        return this.f17264c.b(build, hashMap, Collections.emptyMap(), f17262a);
    }

    /* loaded from: classes9.dex */
    static class a extends com.linecorp.linesdk.a.a.a<LineProfile> {
        a() {
        }

        @Override // com.linecorp.linesdk.a.a.a
        final /* synthetic */ LineProfile a(JSONObject jSONObject) {
            String optString = jSONObject.optString("pictureUrl", null);
            return new LineProfile(jSONObject.getString(f.v), jSONObject.getString("displayName"), optString == null ? null : Uri.parse(optString), jSONObject.optString("statusMessage", null));
        }
    }
}
