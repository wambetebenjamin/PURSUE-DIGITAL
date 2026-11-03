package com.bytedance.apm.doctor;

import com.bytedance.apm6.util.g;
import com.bytedance.monitor.util.thread.e;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
/* compiled from: DoctorManager.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private final String f1011a;

    /* renamed from: b  reason: collision with root package name */
    private List<a> f1012b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1013c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f1014d;

    /* compiled from: DoctorManager.java */
    /* loaded from: classes.dex */
    public interface a {
        void onDataEvent(int i2, String str, JSONObject jSONObject);

        void onEvent(String str, String str2);
    }

    private c() {
        this.f1011a = "doctor";
        this.f1012b = new ArrayList();
        this.f1014d = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DoctorManager.java */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private static final c f1023a = new c();

        private b() {
        }
    }

    public static c getInstance() {
        return b.f1023a;
    }

    public void registerApmListener(a aVar) {
        if (aVar != null) {
            this.f1012b.add(aVar);
        }
    }

    public void onEvent(final String str, final String str2) {
        if (g.isEmpty(this.f1012b)) {
            return;
        }
        final ArrayList arrayList = new ArrayList(this.f1012b);
        com.bytedance.monitor.util.thread.a.getInstance().post(new e() { // from class: com.bytedance.apm.doctor.c.1
            @Override // com.bytedance.monitor.util.thread.e
            public String getTaskName() {
                return "doctor";
            }

            @Override // com.bytedance.monitor.util.thread.e
            public com.bytedance.monitor.util.thread.b getTaskType() {
                return com.bytedance.monitor.util.thread.b.LIGHT_WEIGHT;
            }

            @Override // java.lang.Runnable
            public void run() {
                for (a aVar : arrayList) {
                    aVar.onEvent(str, str2);
                }
            }
        });
    }

    public void onDataEvent(final String str, final JSONObject jSONObject) {
        if (g.isEmpty(this.f1012b) || jSONObject == null) {
            return;
        }
        final ArrayList arrayList = new ArrayList(this.f1012b);
        com.bytedance.monitor.util.thread.a.getInstance().post(new e() { // from class: com.bytedance.apm.doctor.c.2
            @Override // com.bytedance.monitor.util.thread.e
            public String getTaskName() {
                return "doctor";
            }

            @Override // com.bytedance.monitor.util.thread.e
            public com.bytedance.monitor.util.thread.b getTaskType() {
                return com.bytedance.monitor.util.thread.b.LIGHT_WEIGHT;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(com.bytedance.apm.doctor.a.DATA_DOCTOR);
                    jSONObject2.put(str, System.currentTimeMillis());
                    int optInt = jSONObject2.optInt(com.bytedance.apm.doctor.a.DATA_ID);
                    for (a aVar : arrayList) {
                        aVar.onDataEvent(optInt, str, jSONObject);
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    public boolean isEncrypt() {
        return this.f1013c;
    }

    public void setEncrypt(boolean z) {
        this.f1014d = true;
        this.f1013c = z;
    }

    public boolean isEncryptSet() {
        return this.f1014d;
    }
}
