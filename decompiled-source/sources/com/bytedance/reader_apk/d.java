package com.bytedance.reader_apk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PangolinSDK.java */
/* loaded from: classes2.dex */
public class d {
    public static final String TAG = "PangolinSDK";

    /* renamed from: a  reason: collision with root package name */
    private static final int f12095a = 1903654776;

    /* renamed from: b  reason: collision with root package name */
    private static final int f12096b = -1721342362;

    /* renamed from: c  reason: collision with root package name */
    private static final int f12097c = 1903654775;

    /* renamed from: d  reason: collision with root package name */
    private static String[] f12098d = new String[3];

    /* renamed from: e  reason: collision with root package name */
    private static volatile boolean f12099e = false;

    public static String getChannel(Context context) {
        if (!f12099e) {
            a(context);
            f12099e = true;
        }
        Map<String, String> a2 = a(getExtra(context));
        return (a2 == null || a2.size() <= 0) ? "" : a2.get("meta_umeng_channel");
    }

    public static String getExtra(Context context) {
        if (!f12099e) {
            a(context);
            f12099e = true;
        }
        if (TextUtils.isEmpty(f12098d[0])) {
            return !TextUtils.isEmpty(f12098d[1]) ? f12098d[1] : "";
        }
        return f12098d[0];
    }

    public static Map<String, String> getTraceInfo(Context context) {
        if (!f12099e) {
            a(context);
            f12099e = true;
        }
        Map<String, String> a2 = a(f12098d[2]);
        return a2 == null ? new HashMap() : a2;
    }

    private static void a(Context context) {
        int[] iArr = {f12096b, f12097c, f12095a};
        String b2 = b(context);
        if (TextUtils.isEmpty(b2)) {
            f12098d = new String[]{"", "", ""};
        }
        File file = new File(b2);
        String[] string = e.getString(file, iArr);
        if (string == null) {
            string = f12098d;
        }
        f12098d = string;
        if (string.length >= 2 && TextUtils.isEmpty(string[0]) && TextUtils.isEmpty(f12098d[1])) {
            String readChannel = com.bytedance.reader_apk.signaturev1.a.readChannel(file);
            String[] strArr = f12098d;
            if (readChannel == null) {
                readChannel = "";
            }
            strArr[0] = readChannel;
        }
        String[] strArr2 = f12098d;
        if (strArr2.length < 3 || TextUtils.isEmpty(strArr2[2])) {
            return;
        }
        int length = f12098d[2].length();
        if (length > 4) {
            String[] strArr3 = f12098d;
            strArr3[2] = strArr3[2].substring(2, length - 2);
            return;
        }
        f12098d[2] = "";
    }

    private static String b(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo == null) {
                return null;
            }
            return applicationInfo.sourceDir;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Map<String, String> a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            HashMap hashMap = new HashMap();
            while (keys.hasNext()) {
                String obj = keys.next().toString();
                hashMap.put(obj, jSONObject.getString(obj));
            }
            return hashMap;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
