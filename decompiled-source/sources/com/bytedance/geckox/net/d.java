package com.bytedance.geckox.net;

import java.util.Map;
/* compiled from: Response.java */
/* loaded from: classes2.dex */
public class d {
    public final String body;
    public final int code;
    public final Map<String, String> headers;
    public final String msg;

    public d(Map<String, String> map, String str, int i2, String str2) {
        this.headers = map;
        this.body = str;
        this.code = i2;
        this.msg = str2;
    }
}
