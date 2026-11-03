package com.bytedance.applog.util;

import java.util.Map;
/* compiled from: BDNetworkTagManagerHelper.java */
/* loaded from: classes.dex */
public class a {
    public static void a(Map<String, String> map, boolean z) {
        map.put("x-tt-request-tag", "t=0;n=" + (z ? 1 : 0));
    }
}
