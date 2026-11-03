package com.bytedance.pipo.game.impl.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.bytedance.apm.constant.r;
import com.bytedance.pipo.game.n;
import com.bytedance.sdk.account.platform.base.i;
import gsdk.impl.webview.DEFAULT.bv;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: PipoNetUtils.java */
/* loaded from: classes2.dex */
public class h {
    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.android.vending", 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return i.a.ERROR_CODE_UNKNOW;
        }
    }

    public static com.bytedance.pipo.game.api.j a(Context context, String str, Throwable th) {
        int i2;
        String str2 = str;
        JSONObject jSONObject = new JSONObject();
        if (!TextUtils.isEmpty(str) && str2.contains("?")) {
            str2 = str2.substring(0, str2.indexOf("?"));
        }
        if (th == null) {
            try {
                jSONObject.put("net_exception_type", 0);
                jSONObject.put("net_url", str2);
                jSONObject.put("net_quic_error_code", 0);
                jSONObject.put("net_quic_error_code", 0);
                jSONObject.put("net_vpn_enable", n.c(context));
                jSONObject.put("net_proxy_enable", n.b(context));
                jSONObject.put("net_error_msg", "");
                jSONObject.put("net_conn_type", n.e());
            } catch (Exception unused) {
            }
            return new com.bytedance.pipo.game.api.j(0, "success", 0, "", jSONObject.toString());
        } else if (th instanceof com.bytedance.frameworks.baselib.network.http.exception.d) {
            try {
                jSONObject.put("net_exception_type", 0);
                jSONObject.put("net_url", str2);
                jSONObject.put("net_quic_error_code", 0);
                jSONObject.put("net_quic_error_code", 0);
                jSONObject.put("net_vpn_enable", n.c(context));
                jSONObject.put("net_proxy_enable", n.b(context));
                jSONObject.put("net_error_msg", a(th));
                jSONObject.put("net_conn_type", n.e());
            } catch (Exception unused2) {
            }
            return new com.bytedance.pipo.game.api.j(-393001, "Network error", -20, "NetworkNotAvailabeException", jSONObject.toString());
        } else if (th instanceof com.bytedance.frameworks.baselib.network.http.cronet.impl.b) {
            try {
                jSONObject.put("net_exception_type", 1);
                jSONObject.put("net_error_msg", ((com.bytedance.frameworks.baselib.network.http.cronet.impl.b) th).a());
                JSONObject jSONObject2 = new JSONObject(((com.bytedance.frameworks.baselib.network.http.cronet.impl.b) th).a());
                JSONObject optJSONObject = jSONObject2.optJSONObject(r.STATS_BASE_MODULE);
                if (optJSONObject != null) {
                    JSONArray optJSONArray = optJSONObject.optJSONArray("redirect_info");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        str2 = ((JSONObject) optJSONArray.get(optJSONArray.length() - 1)).optString("url");
                    } else {
                        str2 = optJSONObject.optString("origin_url");
                    }
                    if (bv.f21037a.equals(optJSONObject.optString("status"))) {
                        JSONObject optJSONObject2 = jSONObject2.optJSONObject(com.bytedance.apm.perf.traffic.a.TTNET_RESPONSE);
                        int optInt = optJSONObject2 != null ? optJSONObject2.optInt("code") : 0;
                        i2 = 0;
                        r4 = optInt;
                    } else {
                        int optInt2 = optJSONObject.optInt("net_error");
                        try {
                            JSONObject optJSONObject3 = jSONObject2.optJSONObject("quic");
                            i2 = optJSONObject3 != null ? optJSONObject3.optInt("connection_error") : 0;
                            r4 = optInt2;
                        } catch (Exception unused3) {
                            r4 = optInt2;
                        }
                    }
                } else {
                    i2 = 0;
                }
                jSONObject.put("net_url", str2);
                jSONObject.put("net_error_code", r4);
                jSONObject.put("net_quic_error_code", i2);
                jSONObject.put("net_vpn_enable", n.c(context));
                jSONObject.put("net_proxy_enable", n.b(context));
                jSONObject.put("net_error_msg", a(th));
                jSONObject.put("net_conn_type", n.e());
            } catch (Exception unused4) {
            }
            return new com.bytedance.pipo.game.api.j(-393001, "Network error", r4, "CronetIOException", jSONObject.toString());
        } else if (th instanceof com.bytedance.frameworks.baselib.network.http.exception.c) {
            try {
                jSONObject.put("net_exception_type", 2);
                jSONObject.put("net_url", str2);
                jSONObject.put("net_error_code", ((com.bytedance.frameworks.baselib.network.http.exception.c) th).a());
                jSONObject.put("net_quic_error_code", 0);
                jSONObject.put("net_vpn_enable", n.c(context));
                jSONObject.put("net_proxy_enable", n.b(context));
                jSONObject.put("net_error_msg", a(th));
                jSONObject.put("net_conn_type", n.e());
            } catch (Exception unused5) {
            }
            return new com.bytedance.pipo.game.api.j(-393001, "Network error", -20, "HttpResponseException", jSONObject.toString());
        } else if (th instanceof IOException) {
            try {
                jSONObject.put("net_exception_type", 3);
                jSONObject.put("net_url", str2);
                jSONObject.put("net_error_code", 0);
                jSONObject.put("net_quic_error_code", 0);
                jSONObject.put("net_vpn_enable", n.c(context));
                jSONObject.put("net_proxy_enable", n.b(context));
                jSONObject.put("net_error_msg", a(th));
                jSONObject.put("net_conn_type", n.e());
            } catch (Exception unused6) {
            }
            return new com.bytedance.pipo.game.api.j(-393001, "Network error", -20, "IOException", jSONObject.toString());
        } else {
            try {
                jSONObject.put("net_exception_type", 4);
                jSONObject.put("net_url", str2);
                jSONObject.put("net_error_code", 0);
                jSONObject.put("net_quic_error_code", 0);
                jSONObject.put("net_vpn_enable", n.c(context));
                jSONObject.put("net_proxy_enable", n.b(context));
                jSONObject.put("net_error_msg", a(th));
                jSONObject.put("net_conn_type", n.e());
            } catch (Exception unused7) {
            }
            return new com.bytedance.pipo.game.api.j(-393001, "Network error", -20, "普通异常", jSONObject.toString());
        }
    }

    private static String a(Throwable th) {
        if (th != null) {
            String message = th.getMessage();
            return (TextUtils.isEmpty(message) || message.length() <= 200) ? message : message.substring(0, 200);
        }
        return "";
    }
}
