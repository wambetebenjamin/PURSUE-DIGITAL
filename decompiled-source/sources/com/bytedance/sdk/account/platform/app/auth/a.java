package com.bytedance.sdk.account.platform.app.auth;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import com.bytedance.sdk.account.platform.api.b;
import com.bytedance.sdk.account.platform.base.d;
import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.TokenResponse;
/* compiled from: AppAuthServiceImpl.java */
/* loaded from: classes2.dex */
public class a implements com.bytedance.sdk.account.platform.api.b {

    /* renamed from: a  reason: collision with root package name */
    private static final int f12774a = 100;

    /* renamed from: b  reason: collision with root package name */
    private static final String f12775b = "response null";

    /* renamed from: c  reason: collision with root package name */
    private static final String f12776c = "context null";

    /* renamed from: d  reason: collision with root package name */
    private AuthorizationServiceConfiguration f12777d;

    /* renamed from: e  reason: collision with root package name */
    private String f12778e;

    /* renamed from: f  reason: collision with root package name */
    private Context f12779f;

    public a(Context context, String str, String str2, String str3) {
        try {
            this.f12778e = str3;
            this.f12779f = context;
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !Patterns.WEB_URL.matcher(str).matches() || !Patterns.WEB_URL.matcher(str2).matches()) {
                return;
            }
            this.f12777d = new AuthorizationServiceConfiguration(Uri.parse(str), Uri.parse(str2));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public a(Context context, String str, String str2) {
        try {
            this.f12778e = str2;
            this.f12779f = context;
            if (TextUtils.isEmpty(str) || !Patterns.WEB_URL.matcher(str2).matches()) {
                return;
            }
            AuthorizationServiceConfiguration.fetchFromIssuer(Uri.parse(str), new AuthorizationServiceConfiguration.RetrieveConfigurationCallback() { // from class: com.bytedance.sdk.account.platform.app.auth.a.1
                @Override // net.openid.appauth.AuthorizationServiceConfiguration.RetrieveConfigurationCallback
                public void onFetchConfigurationCompleted(AuthorizationServiceConfiguration authorizationServiceConfiguration, AuthorizationException authorizationException) {
                    if (authorizationException == null) {
                        a.this.f12777d = authorizationServiceConfiguration;
                    } else {
                        authorizationException.printStackTrace();
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* compiled from: AppAuthServiceImpl.java */
    /* renamed from: com.bytedance.sdk.account.platform.app.auth.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static class C0198a implements b.a {

        /* renamed from: a  reason: collision with root package name */
        com.bytedance.sdk.account.platform.base.b f12781a;

        /* renamed from: b  reason: collision with root package name */
        Context f12782b;

        private C0198a(Context context, com.bytedance.sdk.account.platform.base.b bVar) {
            this.f12781a = bVar;
            this.f12782b = context;
        }

        @Override // com.bytedance.sdk.account.platform.base.a
        public void onActivityResult(int i2, int i3, Intent intent) {
            if (i2 != 100 || intent == null) {
                return;
            }
            a(intent);
        }

        private void a(Intent intent) {
            try {
                AuthorizationResponse fromIntent = AuthorizationResponse.fromIntent(intent);
                AuthorizationException fromIntent2 = AuthorizationException.fromIntent(intent);
                final AuthState authState = new AuthState(fromIntent, fromIntent2);
                if (fromIntent != null) {
                    if (this.f12782b == null) {
                        this.f12781a.onError(new d(a.f12776c));
                    } else {
                        new AuthorizationService(this.f12782b).performTokenRequest(fromIntent.createTokenExchangeRequest(), new AuthorizationService.TokenResponseCallback() { // from class: com.bytedance.sdk.account.platform.app.auth.a.a.1
                            @Override // net.openid.appauth.AuthorizationService.TokenResponseCallback
                            public void onTokenRequestCompleted(TokenResponse tokenResponse, AuthorizationException authorizationException) {
                                if (authorizationException != null) {
                                    C0198a.this.f12781a.onError(new d(authorizationException.code, authorizationException.error, authorizationException.errorDescription));
                                } else if (tokenResponse != null) {
                                    authState.update(tokenResponse, authorizationException);
                                    C0198a.this.f12781a.onSuccess(C0198a.this.a(authState));
                                }
                            }
                        });
                    }
                } else if (fromIntent2 != null) {
                    d dVar = new d(fromIntent2.code, fromIntent2.error, fromIntent2.errorDescription);
                    if (fromIntent2.code == AuthorizationException.GeneralErrors.PROGRAM_CANCELED_AUTH_FLOW.code) {
                        dVar.isCancel = true;
                    }
                    this.f12781a.onError(dVar);
                } else {
                    this.f12781a.onError(new d("response null"));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                this.f12781a.onError(new d(e2.getMessage()));
            }
        }

        private void a(Bundle bundle, String str, String str2) {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            bundle.putString(str, str2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Bundle a(AuthState authState) {
            if (authState == null) {
                return null;
            }
            Bundle bundle = new Bundle();
            AuthorizationResponse lastAuthorizationResponse = authState.getLastAuthorizationResponse();
            if (lastAuthorizationResponse != null) {
                a(bundle, "auth_code", lastAuthorizationResponse.authorizationCode);
                a(bundle, "state", lastAuthorizationResponse.state);
            }
            a(bundle, "scope", authState.getScope());
            a(bundle, "id_token", authState.getIdToken());
            a(bundle, "access_token", authState.getAccessToken());
            if (authState.getAccessTokenExpirationTime() != null) {
                bundle.putLong(b.InterfaceC0197b.ACCESS_TOKEN_EXPIRATION_TIME, authState.getAccessTokenExpirationTime().longValue());
            }
            a(bundle, "refresh_token", authState.getRefreshToken());
            return bundle;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0061, code lost:
        if (r8.responseType.equals("id_token") != false) goto L20;
     */
    @Override // com.bytedance.sdk.account.platform.api.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.bytedance.sdk.account.platform.api.b.a authorize(android.app.Activity r7, com.bytedance.sdk.account.platform.base.k r8, com.bytedance.sdk.account.platform.base.b r9) {
        /*
            r6 = this;
            r0 = 0
            if (r7 == 0) goto L12
            android.content.Context r1 = r7.getApplicationContext()
            if (r1 == 0) goto L12
            android.content.Context r1 = r7.getApplicationContext()
            java.lang.String r1 = r1.getPackageName()
            goto L13
        L12:
            r1 = r0
        L13:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L31
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r2 = ":/oauth2callback"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.net.Uri r1 = android.net.Uri.parse(r1)
            goto L32
        L31:
            r1 = r0
        L32:
            net.openid.appauth.AuthorizationServiceConfiguration r2 = r6.f12777d
            if (r2 == 0) goto L92
            java.lang.String r2 = r6.f12778e
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L92
            if (r8 == 0) goto L92
            if (r1 != 0) goto L43
            goto L92
        L43:
            java.lang.String r2 = r8.responseType
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            java.lang.String r3 = "id_token"
            java.lang.String r4 = "code"
            java.lang.String r5 = "token"
            if (r2 != 0) goto L64
            java.lang.String r2 = r8.responseType
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L5b
            r3 = r4
            goto L65
        L5b:
            java.lang.String r2 = r8.responseType
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L64
            goto L65
        L64:
            r3 = r5
        L65:
            net.openid.appauth.AuthorizationRequest$Builder r2 = new net.openid.appauth.AuthorizationRequest$Builder
            net.openid.appauth.AuthorizationServiceConfiguration r4 = r6.f12777d
            java.lang.String r5 = r6.f12778e
            r2.<init>(r4, r5, r3, r1)
            java.util.Set<java.lang.String> r1 = r8.scopes
            r2.setScopes(r1)
            java.lang.String r8 = r8.state
            r2.setState(r8)
            net.openid.appauth.AuthorizationRequest r8 = r2.build()
            net.openid.appauth.AuthorizationService r1 = new net.openid.appauth.AuthorizationService
            r1.<init>(r7)
            android.content.Intent r8 = r1.getAuthorizationRequestIntent(r8)
            r1 = 100
            r7.startActivityForResult(r8, r1)
            com.bytedance.sdk.account.platform.app.auth.a$a r7 = new com.bytedance.sdk.account.platform.app.auth.a$a
            android.content.Context r8 = r6.f12779f
            r7.<init>(r8, r9)
            return r7
        L92:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.account.platform.app.auth.a.authorize(android.app.Activity, com.bytedance.sdk.account.platform.base.k, com.bytedance.sdk.account.platform.base.b):com.bytedance.sdk.account.platform.api.b$a");
    }
}
