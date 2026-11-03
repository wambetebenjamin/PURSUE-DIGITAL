package com.bytedance.pipo.game.impl.util;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.bytedance.pipo.game.n;
import com.facebook.internal.security.CertificateUtil;
import com.facebook.share.internal.ShareConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: Utils.java */
/* loaded from: classes2.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10552a = "-";

    /* renamed from: b  reason: collision with root package name */
    private static final String f10553b = "iapUtils";

    public static boolean a() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    public static String a(Context context) {
        return context.getPackageName();
    }

    public static String a(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null) {
            try {
                for (String str : map.keySet()) {
                    jSONObject.put(str, map.get(str));
                }
            } catch (JSONException e2) {
                Log.e(f10553b, "", e2);
            }
        }
        return jSONObject.toString();
    }

    public static String a(String str) {
        return Base64.encodeToString(str.getBytes(), 2);
    }

    public static String b(String str) {
        try {
            return new String(Base64.decode(str, 2), "UTF-8");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String b(Map<String, Long> map) {
        StringBuilder sb = new StringBuilder();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append(CertificateUtil.DELIMITER);
                sb.append(entry.getValue());
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static Map<String, Long> c(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(",")) {
                String[] split = str2.split(CertificateUtil.DELIMITER);
                if (split.length == 2) {
                    hashMap.put(split[0], Long.valueOf(split[1]));
                }
            }
        }
        return hashMap;
    }

    public static JSONObject a(com.bytedance.pipo.game.impl.model.h hVar) {
        Map<String, String> b2 = b(hVar);
        b2.put(com.bytedance.pipo.game.impl.net.a.I, hVar.b());
        return new JSONObject(b2);
    }

    public static Map<String, String> b(com.bytedance.pipo.game.impl.model.h hVar) {
        HashMap hashMap = new HashMap();
        hashMap.put("payment_channel", "googleplay");
        if (hVar != null) {
            String a2 = n.a();
            if (!TextUtils.isEmpty(hVar.f())) {
                a2 = hVar.f();
            }
            hashMap.put("app_id", a2);
            hashMap.put("order_id", hVar.g());
            hashMap.put("sdk_open_id", hVar.i());
            hashMap.put("role_id", hVar.d());
            hashMap.put("server_id", hVar.c());
            hashMap.put(com.bytedance.pipo.payment.common.lib.support.impl.g.f10973d, hVar.k());
            hashMap.put("outside_order_id", hVar.n());
            hashMap.put("transaction_identifier", hVar.n());
            hashMap.put(com.bytedance.pipo.payment.common.lib.c.am, hVar.h());
            hashMap.put("actual_amount", hVar.l());
            hashMap.put("actual_currency", hVar.m());
            hashMap.put("purchase_id", hVar.a());
            hashMap.put(ShareConstants.PROMO_CODE, hVar.s() ? "googleplay" : "");
            hashMap.put("region_code", hVar.t());
            hashMap.put("extra", hVar.u());
        }
        return hashMap;
    }

    public static com.bytedance.pipo.game.impl.model.h a(JSONObject jSONObject) {
        com.bytedance.pipo.game.impl.model.h hVar = new com.bytedance.pipo.game.impl.model.h();
        if (jSONObject != null) {
            hVar.e(jSONObject.optString("app_id"));
            hVar.f(jSONObject.optString("order_id"));
            hVar.h(jSONObject.optString("sdk_open_id"));
            hVar.d(jSONObject.optString("role_id"));
            hVar.c(jSONObject.optString("server_id"));
            hVar.j(jSONObject.optString(com.bytedance.pipo.payment.common.lib.support.impl.g.f10973d));
            hVar.g(jSONObject.optString(com.bytedance.pipo.payment.common.lib.c.am));
            hVar.k(jSONObject.optString("actual_amount"));
            hVar.l(jSONObject.optString("actual_currency"));
        }
        return hVar;
    }

    public static com.bytedance.pipo.game.impl.model.h a(com.bytedance.pipo.game.impl.model.d dVar) {
        com.bytedance.pipo.game.api.c b2 = dVar.b();
        com.bytedance.pipo.game.impl.model.h hVar = new com.bytedance.pipo.game.impl.model.h();
        hVar.f(dVar.j()).g(dVar.g()).h(dVar.i()).c(b2.e()).e(b2.f()).d(b2.c()).a(b2.a()).a(b2.i()).c(dVar.B()).n(dVar.C()).o(dVar.D());
        com.bytedance.pipo.game.impl.model.c k = dVar.k();
        if (k != null) {
            String c2 = k.c();
            if (TextUtils.isEmpty(c2) && dVar.B()) {
                c2 = dVar.j();
            }
            if (dVar.B()) {
                hVar.f("");
            }
            hVar.j(k.m()).m(c2).b(k.h());
        } else {
            hVar.j(dVar.d());
            hVar.b(dVar.c());
        }
        com.bytedance.pipo.game.impl.model.b l = dVar.l();
        if (l != null) {
            hVar.k(String.valueOf((int) ((l.c() / 1000000.0d) * 100.0d))).l(l.d());
        } else {
            hVar.k(dVar.e()).l(dVar.f());
        }
        return hVar;
    }
}
