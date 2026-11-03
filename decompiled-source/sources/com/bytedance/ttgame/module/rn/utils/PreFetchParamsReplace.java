package com.bytedance.ttgame.module.rn.utils;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class PreFetchParamsReplace {
    private static Map<String, GetParamValueAction> actionMap;

    /* loaded from: classes6.dex */
    interface GetParamValueAction {
        String getValue();
    }

    static {
        HashMap hashMap = new HashMap();
        actionMap = hashMap;
        hashMap.put("\\$\\{role_id\\}", new RoleIdGetAction());
        actionMap.put("\\$\\{server_id\\}", new ServerIdGetAction());
        actionMap.put("\\$\\{sdk_app_id\\}", new SDKAppIdGetAction());
        actionMap.put("\\$\\{device_id\\}", new DeviceIdGetAction());
        actionMap.put("\\$\\{gumiho_version\\}", new GumihoVersionGetAction());
        actionMap.put("\\$\\{access_token\\}", new AccessTokenGetAction());
    }

    public static String replaceParams(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        for (Map.Entry<String, GetParamValueAction> entry : actionMap.entrySet()) {
            String value = entry.getValue().getValue();
            String key = entry.getKey();
            if (TextUtils.isEmpty(value)) {
                value = "";
            }
            str = str.replaceAll(key, value);
        }
        return str;
    }
}
