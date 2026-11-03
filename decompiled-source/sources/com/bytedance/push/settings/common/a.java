package com.bytedance.push.settings.common;

import com.bytedance.common.push.c;
import com.bytedance.push.settings.d;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: NotificationChannelListConverter.java */
/* loaded from: classes2.dex */
public class a extends c implements com.bytedance.push.settings.c<Map<String, com.bytedance.push.settings.notification.a>>, d<Map<String, com.bytedance.push.settings.notification.a>> {
    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public Map<String, com.bytedance.push.settings.notification.a> to(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                com.bytedance.push.settings.notification.a aVar = new com.bytedance.push.settings.notification.a();
                aVar.a((String) jSONObject.get(next));
                hashMap.put(next, aVar);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return hashMap;
    }

    @Override // com.bytedance.push.settings.d
    /* renamed from: a */
    public String from(Map<String, com.bytedance.push.settings.notification.a> map) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, com.bytedance.push.settings.notification.a> entry : map.entrySet()) {
            add(jSONObject, entry.getKey(), entry.getValue().a());
        }
        return jSONObject.toString();
    }

    @Override // com.bytedance.push.settings.c
    /* renamed from: a */
    public Map<String, com.bytedance.push.settings.notification.a> create() {
        return new HashMap();
    }
}
