package com.bytedance.pipo.game.impl.event;

import android.text.TextUtils;
import com.android.billingclient.api.Purchase;
import com.bytedance.pipo.game.impl.model.c;
import com.bytedance.pipo.game.impl.model.d;
import com.bytedance.pipo.game.impl.model.h;
import com.bytedance.pipo.game.impl.util.e;
import com.bytedance.sdk.account.platform.base.i;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: PayEventJsonHelper.java */
/* loaded from: classes2.dex */
public class b {
    public static JSONObject a(c cVar) {
        return a(new JSONObject(), cVar);
    }

    public static JSONObject a(JSONObject jSONObject, c cVar) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (cVar != null) {
            e.a(jSONObject, "outside_order_id", cVar.c());
        }
        return jSONObject;
    }

    public static JSONObject a(Purchase purchase) {
        JSONObject jSONObject = new JSONObject();
        if (purchase != null) {
            e.a(jSONObject, com.bytedance.pipo.game.impl.net.a.K, purchase.getOrderId());
            e.a(jSONObject, com.bytedance.pipo.game.impl.net.a.I, purchase.getPurchaseToken());
            e.a(jSONObject, com.bytedance.pipo.game.impl.net.a.af, purchase.getOriginalJson());
            e.a(jSONObject, com.bytedance.pipo.game.impl.net.a.H, purchase.getSignature());
            try {
                e.a(jSONObject, com.bytedance.pipo.game.impl.net.a.G, purchase.getProducts().get(0));
            } catch (Exception unused) {
                e.a(jSONObject, com.bytedance.pipo.game.impl.net.a.G, "unknown");
            }
            e.a(jSONObject, "purchase_state", purchase.getPurchaseState());
        } else {
            e.a(jSONObject, com.bytedance.pipo.game.impl.net.a.K, "unknown");
            e.a(jSONObject, com.bytedance.pipo.game.impl.net.a.I, "unknown");
            e.a(jSONObject, com.bytedance.pipo.game.impl.net.a.af, "unknown");
            e.a(jSONObject, com.bytedance.pipo.game.impl.net.a.H, "unknown");
            e.a(jSONObject, com.bytedance.pipo.game.impl.net.a.G, "unknown");
            e.a(jSONObject, "purchase_state", -1L);
        }
        return jSONObject;
    }

    public static JSONObject a(com.bytedance.pipo.game.api.b bVar) {
        return a(new JSONObject(), bVar);
    }

    public static JSONObject a(JSONObject jSONObject, com.bytedance.pipo.game.api.b bVar) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (bVar != null && bVar.e() == 0) {
            e.a(jSONObject, "result", "1");
            e.a(jSONObject, "error_code", "0");
            e.a(jSONObject, "error_msg", "0");
        } else {
            e.a(jSONObject, "result", "0");
            e.a(jSONObject, "error_code", String.valueOf(bVar == null ? -1 : bVar.d()));
            e.a(jSONObject, "error_msg", bVar == null ? "unknown" : bVar.f());
        }
        return jSONObject;
    }

    public static JSONObject a(d dVar) {
        JSONObject jSONObject = new JSONObject();
        if (dVar != null) {
            e.a(jSONObject, "order_id", dVar.j());
            e.a(jSONObject, "channel_product_id", dVar.g());
            if (dVar.b() != null) {
                e.a(jSONObject, "role_id", dVar.b().c());
                e.a(jSONObject, "server_id", dVar.b().e());
                e.a(jSONObject, "purchase_id", dVar.b().a());
            } else {
                e.a(jSONObject, "role_id", "unknown");
                e.a(jSONObject, "server_id", "unknown");
            }
            c k = dVar.k();
            if (k != null) {
                e.a(jSONObject, "outside_order_id", k.c());
            } else if (!TextUtils.isEmpty(dVar.d())) {
                try {
                    e.a(jSONObject, "outside_order_id", new JSONObject(dVar.d()).optString("orderId"));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return jSONObject;
    }

    public static JSONObject a(h hVar) {
        JSONObject jSONObject = new JSONObject();
        if (hVar != null) {
            e.a(jSONObject, "order_id", hVar.g());
            e.a(jSONObject, "channel_product_id", hVar.h());
            e.a(jSONObject, "role_id", hVar.d());
            e.a(jSONObject, "server_id", hVar.c());
            e.a(jSONObject, "real_pay_currency", hVar.m());
            e.a(jSONObject, "real_pay_amount", hVar.l());
        }
        return jSONObject;
    }

    public static JSONObject a(com.bytedance.pipo.game.impl.model.e eVar) {
        JSONObject jSONObject = new JSONObject();
        if (eVar != null) {
            e.a(jSONObject, "result_code", eVar.a());
            e.a(jSONObject, "result_message", eVar.b());
        } else {
            e.a(jSONObject, "result_code", -1L);
            e.a(jSONObject, "result_message", "unknown");
        }
        return jSONObject;
    }

    public static JSONObject a(com.bytedance.pipo.game.impl.model.e eVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (eVar != null && eVar.a() == 0) {
            e.a(jSONObject, "result", "1");
            e.a(jSONObject, "error_code", "0");
            e.a(jSONObject, "error_msg", "0");
        } else {
            e.a(jSONObject, "result", "0");
            e.a(jSONObject, "error_code", eVar == null ? i.a.ERROR_CODE_UNKNOW : eVar.f10361a + "");
            e.a(jSONObject, "error_msg", eVar == null ? "unknown" : eVar.f10362b);
        }
        return jSONObject;
    }
}
