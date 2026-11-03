package com.bytedance.geckox.statistic.model;

import android.text.TextUtils;
import com.ss.android.token.e;
import java.util.Map;
/* compiled from: CheckUpdateData.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public String f9346a;

    /* renamed from: b  reason: collision with root package name */
    public String f9347b;

    /* renamed from: c  reason: collision with root package name */
    public String f9348c;

    /* renamed from: d  reason: collision with root package name */
    public String f9349d;

    /* renamed from: e  reason: collision with root package name */
    public String f9350e;

    /* renamed from: f  reason: collision with root package name */
    public String f9351f;

    /* renamed from: g  reason: collision with root package name */
    public int f9352g;

    /* renamed from: h  reason: collision with root package name */
    public int f9353h = 1;

    /* renamed from: i  reason: collision with root package name */
    public int f9354i = 0;

    /* renamed from: j  reason: collision with root package name */
    public int f9355j;
    public int k;
    public String l;

    public static String a(Map<String, String> map) {
        if (map != null && !map.isEmpty()) {
            String str = map.get("x-tt-logid");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
            String str2 = map.get("X-Tt-Logid");
            if (!TextUtils.isEmpty(str2)) {
                return str2;
            }
            String str3 = map.get(e.TT_LOGID_KEY);
            if (!TextUtils.isEmpty(str3)) {
                return str3;
            }
        }
        return "";
    }
}
