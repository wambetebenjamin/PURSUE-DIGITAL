package com.linecorp.linesdk.a.a;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.linecorp.linesdk.BuildConfig;
import com.linecorp.linesdk.a.e;
import com.linecorp.linesdk.a.f;
import com.linecorp.linesdk.a.g;
import com.twitter.sdk.android.core.internal.oauth.OAuthConstants;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public static final com.linecorp.linesdk.a.a.a.c<f> f17254a = new C0311b((byte) 0);

    /* renamed from: b  reason: collision with root package name */
    public static final com.linecorp.linesdk.a.a.a.c<e> f17255b = new a((byte) 0);

    /* renamed from: c  reason: collision with root package name */
    public static final com.linecorp.linesdk.a.a.a.c<com.linecorp.linesdk.a.b> f17256c = new d((byte) 0);

    /* renamed from: d  reason: collision with root package name */
    public static final com.linecorp.linesdk.a.a.a.c<g> f17257d = new c((byte) 0);

    /* renamed from: e  reason: collision with root package name */
    public static final com.linecorp.linesdk.a.a.a.c<?> f17258e = new com.linecorp.linesdk.a.a.c();

    /* renamed from: f  reason: collision with root package name */
    public final Uri f17259f;

    /* renamed from: g  reason: collision with root package name */
    public final com.linecorp.linesdk.a.a.a.a f17260g;

    public b(Context context, Uri uri) {
        this(uri, new com.linecorp.linesdk.a.a.a.a(context, BuildConfig.VERSION_NAME));
    }

    private b(Uri uri, com.linecorp.linesdk.a.a.a.a aVar) {
        this.f17259f = uri;
        this.f17260g = aVar;
    }

    /* renamed from: com.linecorp.linesdk.a.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    private static class C0311b extends com.linecorp.linesdk.a.a.a<f> {
        private C0311b() {
        }

        /* synthetic */ C0311b(byte b2) {
            this();
        }

        @Override // com.linecorp.linesdk.a.a.a
        final /* synthetic */ f a(JSONObject jSONObject) {
            return new f(jSONObject.getString("otpId"), jSONObject.getString("otp"));
        }
    }

    /* loaded from: classes9.dex */
    private static class a extends com.linecorp.linesdk.a.a.a<e> {
        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }

        @Override // com.linecorp.linesdk.a.a.a
        final /* synthetic */ e a(JSONObject jSONObject) {
            List asList;
            String string = jSONObject.getString("token_type");
            if (!OAuthConstants.AUTHORIZATION_BEARER.equals(string)) {
                throw new JSONException("Illegal token type. token_type=" + string);
            }
            String optString = jSONObject.optString("scope");
            com.linecorp.linesdk.a.d dVar = new com.linecorp.linesdk.a.d(jSONObject.getString("access_token"), jSONObject.getLong("expires_in") * 1000, System.currentTimeMillis(), jSONObject.getString("refresh_token"));
            if (TextUtils.isEmpty(optString)) {
                asList = Collections.emptyList();
            } else {
                asList = Arrays.asList(optString.split(" "));
            }
            return new e(dVar, asList);
        }
    }

    /* loaded from: classes9.dex */
    private static class d extends com.linecorp.linesdk.a.a.a<com.linecorp.linesdk.a.b> {
        private d() {
        }

        /* synthetic */ d(byte b2) {
            this();
        }

        @Override // com.linecorp.linesdk.a.a.a
        final /* synthetic */ com.linecorp.linesdk.a.b a(JSONObject jSONObject) {
            List asList;
            String optString = jSONObject.optString("scope");
            String string = jSONObject.getString("client_id");
            long j2 = jSONObject.getLong("expires_in") * 1000;
            if (TextUtils.isEmpty(optString)) {
                asList = Collections.emptyList();
            } else {
                asList = Arrays.asList(optString.split(" "));
            }
            return new com.linecorp.linesdk.a.b(string, j2, asList);
        }
    }

    /* loaded from: classes9.dex */
    private static class c extends com.linecorp.linesdk.a.a.a<g> {
        private c() {
        }

        /* synthetic */ c(byte b2) {
            this();
        }

        @Override // com.linecorp.linesdk.a.a.a
        final /* synthetic */ g a(JSONObject jSONObject) {
            String string = jSONObject.getString("token_type");
            if (!OAuthConstants.AUTHORIZATION_BEARER.equals(string)) {
                throw new JSONException("Illegal token type. token_type=" + string);
            }
            return new g(jSONObject.getString("access_token"), jSONObject.getLong("expires_in") * 1000, jSONObject.optString("refresh_token"));
        }
    }
}
