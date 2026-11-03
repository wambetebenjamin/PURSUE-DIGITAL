package com.bytedance.bdturing.methods;

import com.bytedance.bdturing.h;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: JsCallParser.java */
/* loaded from: classes.dex */
public class l {
    public static final String GO_TO_CLOSE = "bytedcert.goToClose";
    public static final int MSG_FAILED = 0;
    public static final int MSG_NO_HANDLER = -2;
    public static final int MSG_NO_PERMISSION = -1;
    public static final int MSG_PARAM_ERROR = -3;
    public static final int MSG_SUCCESS = 1;
    public static final String VALUE_CALL = "call";
    public static final String VALUE_CALLBACK = "callback";
    public static final String VERIFY_REPORT = "bytedcert.verifyReport";

    /* renamed from: a  reason: collision with root package name */
    static final String f4276a = "JSSDK";

    /* renamed from: b  reason: collision with root package name */
    static final String f4277b = "func";

    /* renamed from: c  reason: collision with root package name */
    static final String f4278c = "params";

    /* renamed from: d  reason: collision with root package name */
    static final String f4279d = "__msg_type";

    /* renamed from: e  reason: collision with root package name */
    static final String f4280e = "__callback_id";

    /* renamed from: f  reason: collision with root package name */
    static final String f4281f = "code";

    /* renamed from: g  reason: collision with root package name */
    static final String f4282g = "__params";

    /* renamed from: h  reason: collision with root package name */
    static final String f4283h = "data";

    /* renamed from: i  reason: collision with root package name */
    public static final String f4284i = "bytedcert.refreshVerifyView";
    public static final String o = "bytedcert.verifyData";

    /* renamed from: j  reason: collision with root package name */
    String f4285j = null;
    String k = null;
    String l = null;
    String m = null;
    i n;
    int p;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(i iVar, String str) {
        this.n = iVar;
        try {
            a(str);
        } catch (JSONException e2) {
            h.printException(e2);
        }
    }

    private void a(String str) {
        JSONObject jSONObject = new JSONObject(str);
        this.p = jSONObject.getInt(f4276a);
        this.f4285j = jSONObject.getString(f4277b);
        this.k = jSONObject.getString("params");
        this.l = jSONObject.getString(f4279d);
        this.m = jSONObject.getString(f4280e);
    }

    public void response(int i2, JSONObject jSONObject) {
        i iVar = this.n;
        if (iVar == null) {
            return;
        }
        try {
            iVar.callJsCode(parseNativeResponse(i2, VALUE_CALLBACK, jSONObject, this).toString());
        } catch (JSONException unused) {
        }
    }

    public static JSONObject parseNativeResponse(int i2, String str, JSONObject jSONObject, l lVar) {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("code", i2);
        jSONObject2.put(f4277b, lVar.f4285j);
        jSONObject2.put(f4279d, str);
        Object obj = jSONObject;
        if (jSONObject == null) {
            obj = "{}";
        }
        jSONObject2.put("data", obj);
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put(f4282g, jSONObject2);
        jSONObject3.put(f4280e, lVar.m);
        return jSONObject3;
    }

    public static String parseNativeCallJs(int i2, String str, String str2, JSONObject jSONObject, String str3) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code", i2);
            jSONObject2.put(f4277b, str);
            jSONObject2.put(f4279d, str2);
            jSONObject2.put("data", jSONObject);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(f4282g, jSONObject2);
            jSONObject3.put(f4280e, str3);
            return jSONObject3.toString();
        } catch (JSONException e2) {
            h.printException(e2);
            return null;
        }
    }

    public static JSONArray parseGetDataResponse(String str) {
        try {
            return new JSONObject(str).getJSONObject(f4282g).getJSONObject("data").getJSONArray("data");
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
