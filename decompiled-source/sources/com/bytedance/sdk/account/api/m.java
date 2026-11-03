package com.bytedance.sdk.account.api;

import com.bytedance.sdk.account.api.response.as;
import com.bytedance.sdk.account.api.response.at;
import com.bytedance.sdk.account.api.response.au;
import com.bytedance.sdk.account.api.response.av;
import com.bytedance.sdk.account.api.response.ax;
import com.bytedance.sdk.account.i;
import java.util.Map;
/* compiled from: IBDAccountPlatformAPI.java */
/* loaded from: classes2.dex */
public interface m {
    void a(String str, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.b> aVar);

    void a(String str, String str2, String str3, String str4, String str5, String str6, Map<String, String> map, i<ax> iVar);

    void a(String str, String str2, String str3, String str4, String str5, Map<String, String> map, i<as> iVar);

    void a(String str, String str2, String str3, String str4, boolean z, boolean z2, Map<String, String> map, i<com.bytedance.sdk.account.api.call.h> iVar);

    void a(String str, String str2, String str3, Map<String, String> map, i<ax> iVar);

    void a(String str, String str2, Map<String, String> map, i<av> iVar);

    void a(String str, String str2, String[] strArr, Map<String, String> map, boolean z, i<au> iVar);

    void a(String str, Map<String, String> map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.b> aVar);

    void a(Map<String, String> map, i<at> iVar);

    void authBindLoginWithProfileKey(String str, String str2, String str3, String str4, Map<String, String> map, com.ss.android.account.g gVar);

    void b(String str, String str2, String str3, String str4, boolean z, boolean z2, Map<String, String> map, i<com.bytedance.sdk.account.api.call.h> iVar);

    void b(String str, String str2, String str3, Map<String, String> map, i<au> iVar);

    void bindWithMobile(String str, String str2, String str3, String str4, String str5, String str6, String str7, long j2, String str8, String str9, Map<String, String> map, com.ss.android.account.g gVar);

    void canAwemeQuickLogin(com.bytedance.sdk.account.api.callback.h hVar);

    void getOauthProfile(String str, String str2, String str3, String str4, String str5, String str6, Map<String, String> map, com.bytedance.sdk.account.information.method.oauth_profile.a aVar);

    void getOauthProfileByAccessToken(String str, String str2, String str3, long j2, Map<String, String> map, com.bytedance.sdk.account.information.method.oauth_profile.a aVar);

    void getOauthProfileByAccessToken(String str, String str2, String str3, String str4, long j2, Map<String, String> map, com.bytedance.sdk.account.information.method.oauth_profile.a aVar);

    void getOauthProfileByCode(String str, String str2, String str3, long j2, Map<String, String> map, com.bytedance.sdk.account.information.method.oauth_profile.a aVar);

    void platformAuthToken(String str, String str2, String str3, String str4, String str5, long j2, Map map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.g> aVar);

    void shareLogin(String str, String str2, String str3, Map map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.h> aVar);

    void ssoCheckBindLogin(String str, String str2, String str3, Map<String, String> map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.h> aVar);

    void ssoSwitchBindWithAuthToken(String str, String str2, String str3, long j2, String str4, Map map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.h> aVar);

    void ssoWithAccessTokenBind(String str, String str2, String str3, long j2, Map map, com.ss.android.account.g gVar);

    void ssoWithAccessTokenBind(String str, String str2, String str3, String str4, long j2, Map map, com.ss.android.account.g gVar);

    void ssoWithAccessTokenLogin(String str, String str2, String str3, long j2, Map map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.h> aVar);

    void ssoWithAccessTokenLogin(String str, String str2, String str3, String str4, long j2, Map map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.h> aVar);

    void ssoWithAccessTokenOnlyLogin(String str, String str2, String str3, long j2, Map map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.h> aVar);

    void ssoWithAuthCodeBind(String str, String str2, String str3, long j2, Map map, com.ss.android.account.g gVar);

    void ssoWithAuthCodeBindMobileLogin(String str, String str2, String str3, String str4, boolean z, boolean z2, Map map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.h> aVar);

    void ssoWithAuthCodeLogin(String str, String str2, String str3, long j2, Map map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.h> aVar);

    void ssoWithAuthCodeOnlyLogin(String str, String str2, String str3, long j2, Map map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.h> aVar);

    void ssoWithProfileKeyBind(String str, String str2, String str3, long j2, Map map, com.ss.android.account.g gVar);

    void ssoWithProfileKeyLogin(String str, String str2, String str3, long j2, Map map, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.h> aVar);

    void ssoWithProfileKeyRegister(String str, String str2, String str3, long j2, Map map, com.ss.android.account.g gVar);

    void switchBindWithAccessToken(String str, String str2, String str3, long j2, String str4, Map map, com.ss.android.account.g gVar);

    void switchBindWithAuthCode(String str, String str2, String str3, long j2, String str4, Map map, com.ss.android.account.g gVar);

    void unbindPlatform(String str, int i2, String str2, com.bytedance.sdk.account.api.call.a<com.bytedance.sdk.account.api.call.b> aVar);

    void webAuth(String str, Map map, com.ss.android.account.g gVar);
}
