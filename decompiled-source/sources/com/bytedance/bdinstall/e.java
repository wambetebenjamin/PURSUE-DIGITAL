package com.bytedance.bdinstall;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Pair;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: Api.java */
/* loaded from: classes.dex */
public class e {
    public static final String A = "resolution";
    public static final String B = "display_density";
    public static final String C = "density_dpi";
    public static final String D = "dpi";
    public static final String E = "carrier";
    public static final String F = "mcc_mnc";
    public static final String G = "release_build";
    public static final String H = "not_request_sender";
    public static final String I = "update_version_code";

    /* renamed from: J  reason: collision with root package name */
    public static final String f3794J = "manifest_version_code";
    public static final String K = "region";
    public static final String L = "tz_name";
    public static final String M = "tz_offset";
    public static final String N = "sim_region";
    public static final String O = "custom";
    public static final String P = "appkey";
    public static final String Q = "rom_version";
    public static final String R = "user_unique_id";
    public static final String S = "app_track";
    public static final String T = "google_aid";
    public static final String U = "app_language";
    public static final String V = "app_region";
    public static final String W = "install_id";
    public static final String X = "device_id";
    public static final String Y = "bd_did";
    public static final String Z = "pico_sn";

    /* renamed from: a  reason: collision with root package name */
    public static final String f3795a = "old_did";
    public static final String aA = "ssmix";
    public static final String aB = "_rticket";
    public static final String aC = "version_name";
    public static final String aD = "device_type";
    public static final String aE = "ac";
    public static final String aF = "iid";
    public static final String aG = "mac_address";
    public static final String aH = "uuid";
    public static final String aI = "aliyun_uuid";
    public static final String aJ = "build_serial";
    public static final String aK = "serial_number";
    public static final String aL = "sim_serial_number";
    public static final String aM = "device_id";
    public static final String aN = "bd_did";
    public static final String aO = "install_id";
    public static final String aP = "device_token";
    public static final String aQ = "clientudid";
    public static final String aR = "openudid";
    public static final String aS = "serial_number";
    public static final String aT = "is_migrate";
    public static final String aU = "old_did";
    public static final String aV = "google_aid";
    public static final String aW = "gaid_limited";
    public static final String aX = "is_activated";
    public static final String aY = "is_first_register_for_app";
    public static final String aZ = "is_first_activate_for_app";
    public static final String aa = "device_category";
    public static final String ab = "cd";
    public static final String ac = "user_agent";
    public static final String ad = "gaid_limited";
    public static final String ae = "device_token";
    public static final String af = "_install_started_v2";
    public static final String ag = "register_time";
    public static final String ah = "magic_tag";
    public static final String ai = "ss_app_log";
    public static final String aj = "ab_version";
    public static final String ak = "ab_client";
    public static final String al = "ab_group";
    public static final String am = "ab_feature";
    public static final String an = "abflag";
    public static final String ao = "header";
    public static final String ap = "new_user_mode";
    public static final String aq = "new_user";
    public static final String ar = "ZQLiNFJR+CWEI4px";
    public static final String as = "clear_key_prefix";
    public static final String at = "app_name";
    public static final String au = "tweaked_channel";
    public static final String av = "app_version_minor";
    public static final String aw = "HttpCode";
    public static final String ax = "req_id";
    public static final String ay = "Set-Cookie";
    public static final String az = "device_platform";

    /* renamed from: b  reason: collision with root package name */
    public static final String f3796b = "openudid";
    private static final String ba = "tt_info";
    private static final String bc = "success";
    private static final String bd = "message";

    /* renamed from: c  reason: collision with root package name */
    public static final String f3797c = "clientudid";

    /* renamed from: d  reason: collision with root package name */
    public static final String f3798d = "ssid";

    /* renamed from: e  reason: collision with root package name */
    public static final String f3799e = "aid";

    /* renamed from: f  reason: collision with root package name */
    public static final String f3800f = "sig_hash";

    /* renamed from: g  reason: collision with root package name */
    public static final String f3801g = "sdk_version";

    /* renamed from: h  reason: collision with root package name */
    public static final String f3802h = "sdk_version_code";

    /* renamed from: i  reason: collision with root package name */
    public static final String f3803i = "package";

    /* renamed from: j  reason: collision with root package name */
    public static final String f3804j = "real_package_name";
    public static final String k = "channel";
    public static final String l = "display_name";
    public static final String m = "app_version";
    public static final String n = "version_code";
    public static final String o = "timezone";
    public static final String p = "access";
    public static final String q = "os";
    public static final String r = "sdk_target_version";
    public static final String s = "os_version";
    public static final String t = "os_api";
    public static final String u = "rom";
    public static final String v = "device_model";
    public static final String w = "device_brand";
    public static final String x = "device_manufacturer";
    public static final String y = "cpu_abi";
    public static final String z = "language";
    private static final String bb = "tt_data";
    private static final String[] be = {"aid", "app_version", bb, "device_id", "bd_did"};

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject a(ai aiVar, String str, JSONObject jSONObject, boolean z2, ad adVar, boolean z3) {
        String str2;
        HashMap<String, String> a2 = a(z2, z3);
        try {
            byte[] b2 = b(jSONObject.toString(), z2, adVar);
            if (b2 != null && z2) {
                str = str + "&tt_data=a";
            }
            str2 = aiVar.post(str, b2, a2);
        } catch (Exception e2) {
            u.b("Register#doRegister http error = ", e2);
            e2.printStackTrace();
            str2 = null;
        }
        if (str2 != null) {
            try {
                return new JSONObject(str2);
            } catch (JSONException e3) {
                u.b("Register#doRegister parse json error " + str2 + ", ", e3);
                e3.printStackTrace();
                return null;
            }
        }
        return null;
    }

    private static String a(String str, boolean z2, ad adVar) {
        String[] strArr;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        Uri parse = Uri.parse(str);
        String query = parse.getQuery();
        ArrayList<Pair> arrayList = new ArrayList();
        for (String str2 : be) {
            String queryParameter = parse.getQueryParameter(str2);
            if (!TextUtils.isEmpty(queryParameter)) {
                arrayList.add(new Pair(str2, queryParameter));
            }
        }
        Uri.Builder buildUpon = parse.buildUpon();
        buildUpon.clearQuery();
        for (Pair pair : arrayList) {
            buildUpon.appendQueryParameter((String) pair.first, (String) pair.second);
        }
        buildUpon.appendQueryParameter(ba, new String(Base64.encode(b(query, z2, adVar), 8)));
        return buildUpon.build().toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(ai aiVar, String str, boolean z2, ad adVar, boolean z3) {
        String str2;
        StringBuilder sb = new StringBuilder(str);
        HashMap<String, String> a2 = a(z3);
        JSONObject jSONObject = null;
        try {
            str2 = aiVar.get(a(sb.toString(), z2, adVar), a2);
        } catch (Exception e2) {
            u.b("Register#active http error = ", e2);
            e2.printStackTrace();
            str2 = null;
        }
        if (str2 != null) {
            try {
                jSONObject = new JSONObject(str2);
            } catch (JSONException e3) {
                u.b("Register#active parse json error " + str2 + ", ", e3);
                e3.printStackTrace();
            }
        }
        return jSONObject != null && "success".equals(jSONObject.optString("message", ""));
    }

    private static HashMap<String, String> a(boolean z2) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            a(hashMap, !z2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return hashMap;
    }

    public static void a(HashMap<String, String> hashMap, boolean z2) {
        hashMap.put("x-tt-request-tag", "t=0;n=" + (z2 ? 1 : 0));
    }

    private static byte[] b(String str, boolean z2, ad adVar) {
        byte[] byteArray;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
        GZIPOutputStream gZIPOutputStream = null;
        try {
            try {
                if (z2) {
                    GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(byteArrayOutputStream);
                    try {
                        gZIPOutputStream2.write(str.getBytes("UTF-8"));
                        gZIPOutputStream = gZIPOutputStream2;
                    } catch (Throwable th) {
                        th = th;
                        gZIPOutputStream = gZIPOutputStream2;
                        try {
                            u.a(th);
                            if (gZIPOutputStream != null) {
                                gZIPOutputStream.close();
                            }
                            byteArray = byteArrayOutputStream.toByteArray();
                            return !z2 ? byteArray : byteArray;
                        } catch (Throwable th2) {
                            if (gZIPOutputStream != null) {
                                try {
                                    gZIPOutputStream.close();
                                } catch (IOException e2) {
                                    u.a(e2);
                                }
                            }
                            throw th2;
                        }
                    }
                } else {
                    byteArrayOutputStream.write(str.getBytes("UTF-8"));
                }
            } catch (IOException e3) {
                u.a(e3);
            }
        } catch (Throwable th3) {
            th = th3;
        }
        if (gZIPOutputStream != null) {
            gZIPOutputStream.close();
        }
        byteArray = byteArrayOutputStream.toByteArray();
        if (!z2 && adVar != null) {
            return adVar.a(byteArray, byteArray.length);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(StringBuilder sb, String str, String str2) {
        if (sb == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (sb.toString().indexOf(63) < 0) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        sb.append(str).append("=").append(Uri.encode(str2));
    }

    private static HashMap<String, String> a(boolean z2, boolean z3) {
        HashMap<String, String> hashMap = new HashMap<>(2);
        if (z2) {
            hashMap.put("Content-Type", "application/octet-stream;tt-data=a");
        } else {
            hashMap.put("Content-Type", "application/json; charset=utf-8");
        }
        try {
            a(hashMap, !z3);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return hashMap;
    }
}
