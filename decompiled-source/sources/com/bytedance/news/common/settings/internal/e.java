package com.bytedance.news.common.settings.internal;

import com.facebook.internal.ServerProtocol;
import org.json.JSONObject;
/* compiled from: JsonUtil.java */
/* loaded from: classes2.dex */
public class e {
    public static boolean optBoolean(JSONObject jSONObject, String str) {
        return optBoolean(jSONObject, str, false);
    }

    public static boolean optBoolean(JSONObject jSONObject, String str, boolean z) {
        Boolean a2 = a(jSONObject.opt(str));
        return a2 != null ? a2.booleanValue() : z;
    }

    public static boolean optBoolean(com.bytedance.news.common.settings.api.b bVar, String str) {
        return optBoolean(bVar, str, false);
    }

    public static boolean optBoolean(com.bytedance.news.common.settings.api.b bVar, String str, boolean z) {
        Object string;
        try {
            try {
                string = Boolean.valueOf(bVar.getBoolean(str));
            } catch (Exception unused) {
                string = Integer.valueOf(bVar.getInt(str));
            }
        } catch (Exception unused2) {
            string = bVar.getString(str);
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
}
