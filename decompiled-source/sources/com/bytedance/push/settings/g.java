package com.bytedance.push.settings;

import com.facebook.internal.ServerProtocol;
import org.json.JSONObject;
/* compiled from: JsonUtil.java */
/* loaded from: classes2.dex */
public class g {
    public static boolean optBoolean(JSONObject jSONObject, String str) {
        return optBoolean(jSONObject, str, false);
    }

    public static boolean optBoolean(JSONObject jSONObject, String str, boolean z) {
        Boolean a2 = a(jSONObject.opt(str));
        return a2 != null ? a2.booleanValue() : z;
    }

    public static boolean optBoolean(k kVar, String str) {
        return optBoolean(kVar, str, false);
    }

    public static boolean optBoolean(k kVar, String str, boolean z) {
        Object string;
        try {
            try {
                string = Boolean.valueOf(kVar.getBoolean(str));
            } catch (Exception unused) {
                string = Integer.valueOf(kVar.getInt(str));
            }
        } catch (Exception unused2) {
            string = kVar.getString(str);
        }
        Boolean a2 = a(string);
        return a2 != null ? a2.booleanValue() : z;
    }

    private static Boolean a(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            if (num.intValue() == 1) {
                return true;
            }
            return num.intValue() == 0 ? false : null;
        } else if (obj instanceof String) {
            String str = (String) obj;
            if (ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(str) || "1".equals(str)) {
                return true;
            }
            return ("false".equalsIgnoreCase(str) || "0".equals(str)) ? false : null;
        } else {
            return null;
        }
    }

    public static String a(JSONObject jSONObject) {
        return jSONObject == null ? "null" : jSONObject.toString();
    }
}
