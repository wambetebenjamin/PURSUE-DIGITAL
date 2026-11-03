package com.twitter.sdk.android.core.internal.network;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
/* loaded from: classes10.dex */
public class GuestAuthNetworkInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        return proceed.code() == 403 ? proceed.newBuilder().code(401).message("Unauthorized").build() : proceed;
    }
}
