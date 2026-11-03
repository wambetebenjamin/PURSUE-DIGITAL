package com.bytedance.apm.agent.helper;

import com.bytedance.apm.internal.ApmDelegate;
import org.json.JSONObject;
/* compiled from: PageShowHelper.java */
/* loaded from: classes.dex */
public class d {
    public static void onPageShowHideAction(Object obj, boolean z) {
        String name = obj instanceof String ? (String) obj : obj.getClass().getName();
        JSONObject jSONObject = new JSONObject();
        try {
            if (obj instanceof b) {
                jSONObject.put("label", ((b) obj).getMonitorLabel());
            }
        } catch (Exception unused) {
        }
        String str = z ? "page_show" : "page_hide";
        if (z && ApmDelegate.getInstance().getApmInitConfig().getTraceListener() != null) {
            ApmDelegate.getInstance().getApmInitConfig().getTraceListener().pageShow(name, true);
        }
        com.bytedance.apm.agent.monitor.a.monitorUIAction(str, name, jSONObject);
    }
}
