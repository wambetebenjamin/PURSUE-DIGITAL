package com.bytedance.retrofit2.cache;

import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.client.Response;
/* loaded from: classes2.dex */
public interface ICacheServer {
    Response getCacheResponse(Request request);

    Response putCacheResponse(Request request, Response response);
}
