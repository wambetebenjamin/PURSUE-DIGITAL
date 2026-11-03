package com.bytedance.frameworks.baselib.network.http.ok3.impl;

import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
/* compiled from: Ok3TncBridge.java */
/* loaded from: classes2.dex */
public interface e {
    void a(Request request, Exception exc);

    void a(Request request, Response response);

    JSONObject i();
}
