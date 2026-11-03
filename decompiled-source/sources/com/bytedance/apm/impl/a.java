package com.bytedance.apm.impl;

import java.util.Map;
/* compiled from: HttpRequest.java */
/* loaded from: classes.dex */
public class a {
    public byte[] data;
    public Map<String, String> headerMap;
    public String url;

    public a(String str, Map<String, String> map) {
        this.url = str;
        this.headerMap = map;
    }

    public a(String str, Map<String, String> map, byte[] bArr) {
        this.url = str;
        this.headerMap = map;
        this.data = bArr;
    }
}
