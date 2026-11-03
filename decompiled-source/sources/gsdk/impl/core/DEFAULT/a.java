package gsdk.impl.core.DEFAULT;

import android.text.TextUtils;
import java.util.HashMap;
/* compiled from: UrlManager.java */
/* loaded from: classes10.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f20014a = "log_url";

    /* renamed from: b  reason: collision with root package name */
    public static final String f20015b = "passport_host_merge";

    /* renamed from: c  reason: collision with root package name */
    public static final String f20016c = "passport_host";

    /* renamed from: d  reason: collision with root package name */
    public static final String f20017d = "passport_host_merge_va";

    /* renamed from: e  reason: collision with root package name */
    public static final String f20018e = "passport_host_va";

    /* renamed from: f  reason: collision with root package name */
    public static final String f20019f = "account_privatization_sandbox";

    /* renamed from: g  reason: collision with root package name */
    public static final String f20020g = "account_privatization";

    /* renamed from: h  reason: collision with root package name */
    private static HashMap<String, Object> f20021h;

    /* renamed from: i  reason: collision with root package name */
    private static HashMap<String, Object> f20022i;

    public static void a(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        f20021h = hashMap;
        f20022i = hashMap2;
    }

    public static HashMap<String, Object> a() {
        return f20021h;
    }

    public static HashMap<String, Object> b() {
        return f20022i;
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        HashMap<String, Object> hashMap = f20021h;
        if (hashMap != null && hashMap.containsKey(str)) {
            String str2 = (String) f20021h.get(str);
            return str2 == null ? "" : str2;
        }
        return (String) f20022i.get(str);
    }
}
