package gsdk.impl.main.DEFAULT;

import org.json.JSONObject;
/* compiled from: BridgeUtil.java */
/* loaded from: classes10.dex */
public class i {
    private static com.bytedance.sdk.bridge.model.b a(String str, int i2) {
        if (i2 != -3) {
            if (i2 != -2) {
                if (i2 != -1) {
                    if (i2 != 0) {
                        if (i2 != 1) {
                            return null;
                        }
                        return com.bytedance.sdk.bridge.model.b.Companion.createSuccessResult(new JSONObject(), str);
                    }
                    return com.bytedance.sdk.bridge.model.b.Companion.createErrorResult(str, new JSONObject());
                }
                return com.bytedance.sdk.bridge.model.b.Companion.createNoPrivilegeResult(str, new JSONObject());
            }
            return com.bytedance.sdk.bridge.model.b.Companion.createMethodNotFoundResult(str, new JSONObject());
        }
        return com.bytedance.sdk.bridge.model.b.Companion.createParamsErrorResult(str, new JSONObject());
    }

    public static com.bytedance.sdk.bridge.model.b a(String str) {
        return a(str, 0);
    }

    public static com.bytedance.sdk.bridge.model.b a() {
        return a("error");
    }

    public static com.bytedance.sdk.bridge.model.b a(JSONObject jSONObject) {
        return com.bytedance.sdk.bridge.model.b.Companion.createErrorResult("error", jSONObject);
    }

    public static com.bytedance.sdk.bridge.model.b b() {
        return a("Context is null");
    }

    public static com.bytedance.sdk.bridge.model.b b(String str) {
        return a(str, 1);
    }

    public static com.bytedance.sdk.bridge.model.b c() {
        return b("success");
    }

    public static com.bytedance.sdk.bridge.model.b b(JSONObject jSONObject) {
        return com.bytedance.sdk.bridge.model.b.Companion.createSuccessResult(jSONObject, "success");
    }

    public static com.bytedance.sdk.bridge.model.b c(String str) {
        return a(str, -1);
    }

    public static com.bytedance.sdk.bridge.model.b d() {
        return c("noPrivilege");
    }

    public static com.bytedance.sdk.bridge.model.b d(String str) {
        return a(str, -2);
    }

    public static com.bytedance.sdk.bridge.model.b e(String str) {
        return a(str, -3);
    }
}
