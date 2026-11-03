package com.bytedance.crash.applog;

import com.bytedance.crash.NpthCustomHeaderApi;
import com.bytedance.crash.general.HardwareInfo;
import java.util.HashMap;
import java.util.Map;
/* compiled from: NpthApplog.java */
/* loaded from: classes2.dex */
public class c {
    public static void a() {
        NpthCustomHeaderApi.a(new NpthCustomHeaderApi.ICustomHeaderService() { // from class: com.bytedance.crash.applog.c.1
            @Override // com.bytedance.crash.NpthCustomHeaderApi.ICustomHeaderService
            public Map<String, String> a() {
                HashMap hashMap = new HashMap();
                hashMap.put(HardwareInfo.KEY_HW_RAM_SIZE, com.bytedance.crash.general.b.h());
                return hashMap;
            }
        });
        b.a();
    }
}
