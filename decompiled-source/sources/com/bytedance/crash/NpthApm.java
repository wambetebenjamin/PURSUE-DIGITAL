package com.bytedance.crash;

import com.bytedance.apm.block.l;
import com.bytedance.apm.m;
import com.bytedance.apm.maps.a;
import com.bytedance.apm.maps.b;
import com.bytedance.apm.npth.a;
import com.bytedance.crash.NpthApi;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class NpthApm {
    NpthApm() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        try {
            l.init();
            m.init();
            a.setNpthCallback(new a.c() { // from class: com.bytedance.crash.NpthApm.1
                @Override // com.bytedance.apm.npth.a.c
                public void addTags(Map<String, String> map) {
                    NpthApi.addTags(map);
                }

                @Override // com.bytedance.apm.npth.a.c
                public void addAttachUserData(final a.InterfaceC0018a interfaceC0018a) {
                    NpthApi.addCustomData(new NpthApi.CustomDataCallback() { // from class: com.bytedance.crash.NpthApm.1.1
                        @Override // com.bytedance.crash.NpthApi.CustomDataCallback
                        public Map<String, String> getData() {
                            return interfaceC0018a.getUserData();
                        }
                    });
                }
            });
            com.bytedance.apm.maps.a.SetCallback(new a.InterfaceC0017a() { // from class: com.bytedance.crash.NpthApm.2
                @Override // com.bytedance.apm.maps.a.InterfaceC0017a
                public void addInfo(Map<Object, Object> map) {
                    b.addInfo(map);
                }
            });
            com.bytedance.apm.npth.a.setMonitorListener(new a.b() { // from class: com.bytedance.crash.NpthApm.3
                @Override // com.bytedance.apm.npth.a.b
                public void startMonitor() {
                    NpthApi.startMonitor();
                }
            });
        } catch (Throwable unused) {
        }
    }
}
