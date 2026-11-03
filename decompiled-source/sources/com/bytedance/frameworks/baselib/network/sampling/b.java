package com.bytedance.frameworks.baselib.network.sampling;

import android.text.TextUtils;
import com.bytedance.apm.constant.p;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.sampling.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: SlaSamplingSettingCreator.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8487a = "SlaSamplingSCreator";

    /* renamed from: b  reason: collision with root package name */
    private static final String f8488b = "api_block_list";

    /* renamed from: c  reason: collision with root package name */
    private static final String f8489c = "enable_base_api_all";

    /* renamed from: d  reason: collision with root package name */
    private static final String f8490d = "enable_api_all_upload";

    /* renamed from: e  reason: collision with root package name */
    private static final String f8491e = "api_allow_list";

    /* renamed from: f  reason: collision with root package name */
    private static final String f8492f = "allow_log_type";

    /* renamed from: g  reason: collision with root package name */
    private static final Set<String> f8493g;

    /* renamed from: h  reason: collision with root package name */
    private static double f8494h;

    static {
        HashSet hashSet = new HashSet();
        f8493g = hashSet;
        f8494h = 1.0E-9d;
        hashSet.add("ttnet_high_lvl_conn");
        hashSet.add("multinetwork");
        hashSet.add("mpa_client");
        hashSet.add("ttnet_feedback_detect");
        hashSet.add("ttnet_exception_detect");
        hashSet.add("ttnet_stress_detect");
        hashSet.add("ttnet_polling_detect");
        hashSet.add("long_cost_net_task");
        hashSet.add("ws_all");
        hashSet.add("frontier_all");
        hashSet.add("frontier_msg");
        hashSet.add("user_msg");
        hashSet.add(com.bytedance.ttnet.cronet.b.f15233a);
        hashSet.add("ttnet_route_select");
        hashSet.add("ws_ping");
        hashSet.add("frontier_online");
        hashSet.add("ttnet_all_log");
        hashSet.add("cookie_mismatch");
        hashSet.add("cronet_native_requestlog");
        hashSet.add("ttnet_debug_trace_info");
        hashSet.add("ttnet_http_dns_fail");
        hashSet.add("ttnet_tnc_update_result");
        hashSet.add("cronet_native_requestlog_empty");
        hashSet.add("frontier_qos");
        hashSet.add("ttnet_net_exp");
        hashSet.add("bad_request_diagnosis");
        hashSet.add("ttnet_raw_detect");
        hashSet.add("gna_raw_detect");
        hashSet.add("ttnet_network_environment");
        hashSet.add("game_network");
        hashSet.add("ttnet_zstd_stream");
        hashSet.add("ttnet_zstd_dict_error");
        hashSet.add("ttnet_load");
        hashSet.add("ttnet_insec_rect");
        hashSet.add("ttquiche_native_request_log");
        hashSet.add("otel_log");
    }

    private static boolean a(JSONObject jSONObject, String str, Collection<String> collection) {
        JSONObject optJSONObject;
        if (jSONObject == null || TextUtils.isEmpty(str) || collection == null || (optJSONObject = jSONObject.optJSONObject(str)) == null) {
            return false;
        }
        Iterator<String> keys = optJSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            if (!TextUtils.isEmpty(next)) {
                collection.add(next);
            }
        }
        return true;
    }

    private static void a(JSONObject jSONObject, String str, List<String> list) {
        JSONArray optJSONArray;
        if (jSONObject == null || TextUtils.isEmpty(str) || list == null || (optJSONArray = jSONObject.optJSONArray(str)) == null) {
            return;
        }
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            String optString = optJSONArray.optString(i2);
            if (!TextUtils.isEmpty(optString)) {
                list.add(optString);
            }
        }
    }

    public static a.h a(JSONObject jSONObject, int i2) {
        a.EnumC0118a enumC0118a;
        a.f fVar;
        a.g gVar;
        try {
            if (i2 <= 0 || jSONObject == null) {
                a d2 = a.d();
                if (i2 <= 0) {
                    enumC0118a = a.EnumC0118a.AID_FETCH_FAILED;
                } else {
                    enumC0118a = a.EnumC0118a.CONFIG_EMPTY;
                }
                d2.a(enumC0118a);
                Logger.e(f8487a, "create slaSamplingSetting failed, " + (jSONObject == null ? "jsonObject is null" : "appId is invalid"));
                return null;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(p.SETTING_NETWORK_IMAGE_MODULES);
            JSONObject optJSONObject2 = jSONObject.optJSONObject("custom_event_settings");
            if (optJSONObject != null) {
                optJSONObject = optJSONObject.optJSONObject(p.SETTING_NETWORK);
            }
            if ((optJSONObject != null && optJSONObject.length() > 0) || (optJSONObject2 != null && optJSONObject2.length() > 0)) {
                if (optJSONObject != null) {
                    ArrayList<String> arrayList = new ArrayList();
                    a(optJSONObject, "api_allow_list", (Collection<String>) arrayList);
                    ArrayList arrayList2 = new ArrayList();
                    for (String str : arrayList) {
                        try {
                            arrayList2.add(new com.bytedance.frameworks.baselib.network.utils.a(str));
                        } catch (Exception unused) {
                            if (Logger.debug()) {
                                Logger.d(f8487a, "api allow list rule is invalid, rule = " + str);
                            }
                        }
                    }
                    ArrayList<String> arrayList3 = new ArrayList();
                    a(optJSONObject, "api_block_list", (List<String>) arrayList3);
                    ArrayList arrayList4 = new ArrayList();
                    for (String str2 : arrayList3) {
                        try {
                            arrayList4.add(new com.bytedance.frameworks.baselib.network.utils.a(str2));
                        } catch (Exception unused2) {
                            if (Logger.debug()) {
                                Logger.d(f8487a, "api block rule is invalid, rule = " + str2);
                            }
                        }
                    }
                    boolean z = true;
                    boolean z2 = Math.abs(optJSONObject.optDouble("enable_base_api_all")) > f8494h;
                    if (optJSONObject.optInt("enable_api_all_upload") != 1) {
                        z = false;
                    }
                    fVar = new a.f(z2, z, arrayList4, arrayList3, arrayList2, arrayList);
                } else {
                    fVar = null;
                }
                HashSet hashSet = new HashSet();
                if (optJSONObject2 == null || !a(optJSONObject2, "allow_log_type", hashSet)) {
                    gVar = null;
                } else {
                    hashSet.retainAll(f8493g);
                    gVar = new a.g(hashSet);
                }
                if (fVar == null && gVar == null) {
                    if (Logger.debug()) {
                        Logger.d(f8487a, "create slaSamplingSetting failed, api and event rule is null");
                    }
                    a.d().a(a.EnumC0118a.NO_MATCHING_CONFIG);
                    return null;
                }
                return new a.h(i2, fVar, gVar);
            }
            a.d().a(a.EnumC0118a.NO_MATCHING_CONFIG);
            return null;
        } catch (Exception e2) {
            a.d().a(a.EnumC0118a.PARSE_CONFIG_HAS_EXCEPTION);
            e2.printStackTrace();
            return null;
        }
    }
}
