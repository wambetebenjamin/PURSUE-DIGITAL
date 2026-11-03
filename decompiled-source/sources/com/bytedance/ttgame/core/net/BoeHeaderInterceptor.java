package com.bytedance.ttgame.core.net;

import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import java.util.ArrayList;
/* loaded from: classes3.dex */
class BoeHeaderInterceptor implements Interceptor {
    private String boeheader;

    public BoeHeaderInterceptor(String str) {
        this.boeheader = str;
    }

    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public SsResponse intercept(Interceptor.Chain chain) throws Exception {
        Request request = chain.request();
        ArrayList arrayList = new ArrayList(request.getHeaders());
        arrayList.add(new Header("X-TT-ENV", this.boeheader));
        return chain.proceed(request.newBuilder().headers(arrayList).build());
    }
}
