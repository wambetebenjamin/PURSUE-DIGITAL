package com.bytedance.frameworks.baselib.network.http.cronet.retrofit;

import android.content.Context;
import com.bytedance.frameworks.baselib.network.http.c;
import com.bytedance.frameworks.baselib.network.http.cronet.b;
import com.bytedance.retrofit2.client.Client;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.client.SsCall;
/* compiled from: SsRetrofitClient.java */
/* loaded from: classes2.dex */
public class a implements Client {

    /* renamed from: a  reason: collision with root package name */
    private Context f7816a;

    @Deprecated
    public a(Context context) {
        this.f7816a = context;
    }

    @Override // com.bytedance.retrofit2.client.Client
    public SsCall newSsCall(Request request) {
        c a2 = b.a(this.f7816a, request.getUrl());
        if (a2 != null) {
            return a2.newSsCall(request);
        }
        return null;
    }
}
