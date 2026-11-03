package com.bytedance.frameworks.baselib.network.http;

import com.bytedance.common.utility.o;
/* compiled from: BaseHttpClient.java */
/* loaded from: classes2.dex */
public abstract class h implements c {
    public static String c(String str) {
        try {
            return !o.isEmpty(str) ? new com.bytedance.frameworks.baselib.network.http.parser.e(str).d() : "";
        } catch (com.bytedance.frameworks.baselib.network.http.parser.g e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
