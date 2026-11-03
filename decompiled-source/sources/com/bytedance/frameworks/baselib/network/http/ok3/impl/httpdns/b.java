package com.bytedance.frameworks.baselib.network.http.ok3.impl.httpdns;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/* compiled from: DnsResult.java */
/* loaded from: classes2.dex */
public class b implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public List<String> f8010a = new ArrayList();

    /* renamed from: b  reason: collision with root package name */
    public List<String> f8011b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    public a f8012c = a.HTTPDNS_CACHE;

    /* compiled from: DnsResult.java */
    /* loaded from: classes2.dex */
    public enum a {
        UNKNOWN,
        HTTPDNS_CACHE,
        HTTPDNS_STALE_CACHE,
        HTTPDNS_REQUEST,
        LOCALDNS_REQUEST,
        LOCALDNS_CACHE,
        HARDCODE_IPS
    }
}
