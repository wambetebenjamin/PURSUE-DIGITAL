package com.bytedance.bdturing.ttnet;

import android.content.Context;
import android.text.TextUtils;
import com.meituan.robust.Constants;
import java.net.URL;
import java.util.Map;
/* compiled from: TrafficColorHelper.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4345a = "x-tt-request-tag";

    public static void a(Context context, String str, Map<String, String> map) {
        try {
            map.put(f4345a, ("n=" + (a(context, str) ? 1 : 0) + Constants.PACKNAME_END) + "t=0");
        } catch (Exception unused) {
        }
    }

    private static boolean a(Context context, String str) {
        URL url = new URL(str);
        if (TextUtils.isEmpty(url.getPath()) || !url.getPath().contains("vc/setting")) {
            return true;
        }
        return true ^ context.getSharedPreferences("bd_turning_settings_init", 0).getBoolean("inited", false);
    }
}
