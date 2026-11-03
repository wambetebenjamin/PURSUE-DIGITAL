package com.linecorp.linesdk.auth;

import android.content.Context;
import android.content.Intent;
import com.linecorp.linesdk.LineApiError;
import com.linecorp.linesdk.LineApiResponseCode;
import com.linecorp.linesdk.a.c;
import com.linecorp.linesdk.auth.LineAuthenticationConfig;
import com.linecorp.linesdk.auth.internal.LineAuthenticationActivity;
import java.util.Collections;
import java.util.List;
/* loaded from: classes9.dex */
public class LineLoginApi {
    private LineLoginApi() {
    }

    public static Intent getLoginIntent(Context context, String str) {
        return getLoginIntent(context, new LineAuthenticationConfig.Builder(str).build(), Collections.emptyList());
    }

    public static Intent getLoginIntentWithoutLineAppAuth(Context context, String str) {
        return getLoginIntent(context, new LineAuthenticationConfig.Builder(str).disableLineAppAuthentication().build(), Collections.emptyList());
    }

    public static Intent getLoginIntent(Context context, String str, List<String> list) {
        return getLoginIntent(context, new LineAuthenticationConfig.Builder(str).build(), list);
    }

    public static Intent getLoginIntentWithoutLineAppAuth(Context context, String str, List<String> list) {
        return getLoginIntent(context, new LineAuthenticationConfig.Builder(str).disableLineAppAuthentication().build(), list);
    }

    public static Intent getLoginIntent(Context context, LineAuthenticationConfig lineAuthenticationConfig, List<String> list) {
        if (!lineAuthenticationConfig.isEncryptorPreparationDisabled()) {
            c.a(context);
        }
        return LineAuthenticationActivity.a(context, lineAuthenticationConfig, list);
    }

    public static LineLoginResult getLoginResultFromIntent(Intent intent) {
        if (intent == null) {
            return new LineLoginResult(LineApiResponseCode.INTERNAL_ERROR, new LineApiError("Callback intent is null"));
        }
        return LineAuthenticationActivity.a(intent);
    }
}
