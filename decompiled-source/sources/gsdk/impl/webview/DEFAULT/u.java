package gsdk.impl.webview.DEFAULT;

import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: BridgeMonitorUtil.java */
/* loaded from: classes10.dex */
public class u {

    /* renamed from: a  reason: collision with root package name */
    public static final String f21062a = "extra";

    /* renamed from: b  reason: collision with root package name */
    public static final String f21063b = "duration";

    /* renamed from: c  reason: collision with root package name */
    public static final String f21064c = "error_code";

    /* renamed from: d  reason: collision with root package name */
    public static final String f21065d = "error_msg";

    /* renamed from: e  reason: collision with root package name */
    public static final String f21066e = "status";

    /* renamed from: f  reason: collision with root package name */
    public static final String f21067f = "sdk_open_id";

    public static JSONObject a(int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", i2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    public static JSONObject a() {
        return new JSONObject();
    }

    public static JSONObject a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("extra", jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject2;
    }

    public static JSONObject a(int i2, String str, String str2, long j2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error_code", i2);
            jSONObject.put("error_msg", str);
            if (j2 > 0) {
                jSONObject.put("sdk_open_id", j2);
            }
            return jSONObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
