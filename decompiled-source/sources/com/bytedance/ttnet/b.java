package com.bytedance.ttnet;

import android.text.TextUtils;
/* compiled from: AppConsts.java */
/* loaded from: classes8.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15024a = "success";

    /* renamed from: b  reason: collision with root package name */
    public static final String f15025b = "error";

    /* renamed from: c  reason: collision with root package name */
    public static final String f15026c = "message";

    /* renamed from: d  reason: collision with root package name */
    public static final String f15027d = "data";

    /* renamed from: e  reason: collision with root package name */
    public static final int f15028e = 0;

    /* renamed from: f  reason: collision with root package name */
    public static final int f15029f = 1;

    /* renamed from: g  reason: collision with root package name */
    public static final int f15030g = 2;

    public static String a() {
        String hostSuffix = TTNetInit.getTTNetDepend().getHostSuffix();
        if (hostSuffix == null || TextUtils.isEmpty(hostSuffix)) {
            throw new IllegalArgumentException("hostSuffix is not init !!!");
        }
        return hostSuffix;
    }

    public static String b() {
        String cdnHostSuffix = TTNetInit.getTTNetDepend().getCdnHostSuffix();
        if (cdnHostSuffix == null || TextUtils.isEmpty(cdnHostSuffix)) {
            throw new IllegalArgumentException("cdnHostSuffix is not init !!!");
        }
        return cdnHostSuffix;
    }
}
