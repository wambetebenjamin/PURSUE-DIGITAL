package com.bytedance.frameworks.baselib.network.http.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.bytedance.retrofit2.client.Header;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: BoeUtils.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: b  reason: collision with root package name */
    private static final String f8305b = "ttnet_config.json";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8306c = "ttnet_boe.flag";

    /* renamed from: d  reason: collision with root package name */
    private static volatile String f8307d = null;

    /* renamed from: e  reason: collision with root package name */
    private static volatile String f8308e = null;

    /* renamed from: f  reason: collision with root package name */
    private static volatile boolean f8309f = false;

    /* renamed from: g  reason: collision with root package name */
    private static volatile boolean f8310g = false;

    /* renamed from: h  reason: collision with root package name */
    private static volatile List<String> f8311h = new ArrayList();

    /* renamed from: i  reason: collision with root package name */
    private static volatile List<String> f8312i = new ArrayList();

    /* renamed from: j  reason: collision with root package name */
    private static volatile boolean f8313j = false;
    private static volatile List<Pattern> k = new ArrayList();

    /* renamed from: a  reason: collision with root package name */
    private static volatile List<String> f8304a = new CopyOnWriteArrayList();

    public static boolean a(Context context) {
        String a2 = a(context, f8305b);
        if (!TextUtils.isEmpty(a2)) {
            try {
                if (new JSONObject(a2).optBoolean("boe_proxy_enabled", false)) {
                    return true;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    public static void a(JSONObject jSONObject) {
        JSONArray optJSONArray;
        if (!f8309f || jSONObject == null || (optJSONArray = jSONObject.optJSONArray("boe_bypass_host_list")) == null) {
            return;
        }
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            String optString = optJSONArray.optString(i2);
            if (!com.bytedance.common.utility.o.isEmpty(optString)) {
                f8304a.add(optString);
            }
        }
    }

    public static boolean b(Context context) {
        String absolutePath = context.getFilesDir().getAbsolutePath();
        if (TextUtils.isEmpty(absolutePath)) {
            return false;
        }
        try {
            return new File(new StringBuilder().append(absolutePath).append(c.a.SEPARATOR).append(f8306c).toString()).exists();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static String a(Context context, String str) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(str);
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            String str2 = new String(bArr, "UTF-8");
            if (inputStream != null) {
                try {
                    inputStream.close();
                    return str2;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return str2;
                }
            }
            return str2;
        } catch (Throwable unused) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            return "";
        }
    }

    public static String a(Context context, String str, List<Header> list) {
        boolean z = false;
        if (!f8313j && !f8309f) {
            f8309f = a(context) || b(context);
            f8313j = true;
        }
        if (f8309f && (!TextUtils.isEmpty(f8307d) || !TextUtils.isEmpty(f8308e))) {
            Uri parse = Uri.parse(str);
            if (com.bytedance.common.utility.o.isEmpty(parse.getScheme()) || com.bytedance.common.utility.o.isEmpty(parse.getHost()) || com.bytedance.common.utility.o.isEmpty(parse.getPath()) || c(parse.getHost(), parse.getPath())) {
                return str;
            }
            for (Header header : list) {
                if (!com.bytedance.common.utility.o.isEmpty(header.getName()) && !com.bytedance.common.utility.o.isEmpty(header.getValue()) && header.getName().equals("bypass-boe") && header.getValue().equals("1")) {
                    return str;
                }
            }
            String b2 = b(str, parse.getScheme());
            if ((h.a(parse.getHost()) || h.b(parse.getHost()) || h.c(parse.getHost())) ? true : true) {
                return b2;
            }
            if (f8310g) {
                String host = parse.getHost();
                return !host.contains(f8307d) ? b2.replaceFirst(host, host + f8307d) : b2;
            }
            return a(b2, parse.getHost());
        }
        return str;
    }

    public static String d(String str) {
        if (f8309f && f8310g && str != null && str.contains(f8307d)) {
            String scheme = Uri.parse(str).getScheme();
            if ("http".equals(scheme)) {
                str = str.replaceFirst("http", "https");
            } else if ("ws".equals(scheme)) {
                str = str.replaceFirst("ws", "wss");
            }
            return str.replaceFirst(f8307d, "");
        }
        return null;
    }

    private static String a(String str, String str2) {
        String[] split;
        int length;
        if (!TextUtils.isEmpty(f8308e) && (length = (split = str2.split("\\.")).length) >= 2) {
            int i2 = length - 2;
            String str3 = split[i2];
            return str3.contains(f8308e) ? str : a(str, a(str, c.a.DOT, i2), str3, str3 + f8308e);
        }
        return str;
    }

    private static String a(String str, int i2, String str2, String str3) {
        String substring = str.substring(0, i2);
        return substring + str.substring(i2).replaceFirst(str2, str3);
    }

    private static int a(String str, String str2, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            i4 = str.indexOf(str2, i3 == 0 ? 0 : i4 + 1);
            if (i4 == -1) {
                return 0;
            }
            i3++;
        }
        return i4;
    }

    private static String b(String str, String str2) {
        if (f8310g) {
            String str3 = null;
            if (str2.equals("https")) {
                str3 = "http";
            } else if (str2.equals("wss")) {
                str3 = "ws";
            }
            return !com.bytedance.common.utility.o.isEmpty(str3) ? str.replaceFirst(str2, str3) : str;
        }
        return str;
    }

    public static void a(String str) {
        f8307d = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f8310g = true;
    }

    public static void b(String str) {
        f8308e = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f8310g = false;
    }

    public static void a(boolean z) {
        f8309f = z;
    }

    public static void c(String str) {
        if (com.bytedance.common.utility.o.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray optJSONArray = jSONObject.optJSONArray("bypass_boe_host_list");
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    String optString = optJSONArray.optString(i2);
                    if (!com.bytedance.common.utility.o.isEmpty(optString)) {
                        f8311h.add(optString);
                    }
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("bypass_boe_path_list");
            if (optJSONArray2 != null) {
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    String optString2 = optJSONArray2.optString(i3);
                    if (!com.bytedance.common.utility.o.isEmpty(optString2)) {
                        f8312i.add(optString2);
                    }
                }
            }
            JSONArray optJSONArray3 = jSONObject.optJSONArray("bypass_boe_url_list");
            if (optJSONArray3 != null) {
                for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                    String optString3 = optJSONArray3.optString(i4);
                    if (!com.bytedance.common.utility.o.isEmpty(optString3)) {
                        k.add(Pattern.compile(optString3, 2));
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private static boolean c(String str, String str2) {
        if (f8311h.isEmpty() && f8312i.isEmpty() && k.isEmpty() && f8304a.isEmpty()) {
            return false;
        }
        for (String str3 : f8311h) {
            if (p.a(str, str3)) {
                return true;
            }
        }
        for (String str4 : f8304a) {
            if (p.a(str, str4)) {
                return true;
            }
        }
        for (String str5 : f8312i) {
            if (p.a(str2, str5)) {
                return true;
            }
        }
        String str6 = str + str2;
        for (Pattern pattern : k) {
            if (pattern.matcher(str6).matches()) {
                return true;
            }
        }
        return false;
    }

    public static boolean a() {
        return f8309f;
    }
}
