package com.bytedance.sdk.account.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieSyncManager;
import com.bytedance.librarian.c;
import com.meituan.robust.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: Utils.java */
/* loaded from: classes2.dex */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13095a = "Utils";

    public static String getUrl(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append(str2).append(c.a.SEPARATOR);
        return sb.toString();
    }

    public static String[] a(String str) {
        String str2;
        try {
            str2 = (String) com.bytedance.sdk.account.bpea.a.a(null, com.bytedance.sdk.account.bpea.a.f12309a, com.bytedance.sdk.account.bpea.a.f12311c, str);
        } catch (Throwable th) {
            th.printStackTrace();
            str2 = null;
        }
        String[] split = TextUtils.isEmpty(str2) ? null : str2.split(Constants.PACKNAME_END);
        if (split != null) {
            for (int i2 = 0; i2 < split.length; i2++) {
                String str3 = split[i2];
                if (str3 != null) {
                    split[i2] = str3.trim();
                }
            }
        }
        return split;
    }

    public static String getSpecialCookieInfo(String str, String str2) {
        String[] a2 = a(str);
        if (a2 == null || a2.length == 0) {
            return "";
        }
        for (String str3 : a2) {
            if (!TextUtils.isEmpty(str3)) {
                String[] split = str3.split("=");
                if (split.length >= 2 && split[0] != null && split[0].trim().equalsIgnoreCase(str2)) {
                    return split[1];
                }
            }
        }
        return "";
    }

    public static String b(String str) {
        String[] a2;
        if (TextUtils.isEmpty(str) || (a2 = a(str)) == null || a2.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (String str2 : a2) {
            if (!TextUtils.isEmpty(str2)) {
                String[] split = str2.split("=");
                if (split.length >= 2) {
                    if (i2 > 0) {
                        sb.append(",");
                    }
                    sb.append(split[0].trim());
                    i2++;
                }
            }
        }
        return sb.toString();
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String host = Uri.parse(str).getHost();
        com.ss.android.token.d.a(f13095a, String.format("getAllCookieForDomainKeys, domain for %s is %s", str, host));
        return b(host);
    }

    public static void flushCookie() {
        try {
            CookieSyncManager.getInstance().sync();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null || obj.getClass() != obj2.getClass()) {
            return false;
        }
        if (obj instanceof JSONObject) {
            return a((JSONObject) obj, (JSONObject) obj2);
        }
        return obj.equals(obj2);
    }

    public static boolean a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == jSONObject2) {
            return true;
        }
        if (jSONObject == null || jSONObject2 == null || jSONObject.length() != jSONObject2.length()) {
            return false;
        }
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            Object opt2 = jSONObject2.opt(next);
            if (opt2 != null && opt.getClass() == opt2.getClass()) {
                if (opt instanceof JSONObject) {
                    if (!a((JSONObject) opt, (JSONObject) opt2)) {
                        return false;
                    }
                } else if (!opt.equals(opt2)) {
                }
            }
            return false;
        }
        return true;
    }

    public static JSONArray a(JSONArray jSONArray, int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            jSONArray.remove(i2);
            return jSONArray;
        } else if (i2 < 0 || i2 > jSONArray.length() - 1) {
            throw new IndexOutOfBoundsException();
        } else {
            JSONArray jSONArray2 = new JSONArray();
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                if (i3 != i2) {
                    jSONArray2.put(jSONArray.get(i3));
                }
            }
            return jSONArray2;
        }
    }

    public static boolean a(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse(String.format("smsto:%s", str)));
        intent.putExtra("sms_body", str2);
        if (Build.VERSION.SDK_INT >= 19) {
            String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(context);
            if (!TextUtils.isEmpty(defaultSmsPackage)) {
                intent.setPackage(defaultSmsPackage);
            }
        }
        try {
            context.startActivity(intent);
            return true;
        } catch (Exception e2) {
            Log.d(f13095a, "sendSms: 失败, 无法打开系统发送短信activity, exception=" + Log.getStackTraceString(e2));
            return false;
        }
    }

    public static String a(long j2) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z").format(new Date(j2));
    }
}
