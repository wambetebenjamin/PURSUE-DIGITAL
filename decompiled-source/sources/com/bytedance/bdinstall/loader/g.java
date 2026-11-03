package com.bytedance.bdinstall.loader;

import com.bytedance.bdinstall.am;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: CustomLoader.java */
/* loaded from: classes.dex */
public class g extends c {

    /* renamed from: e  reason: collision with root package name */
    private final am f3886e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(am amVar) {
        super(false, true);
        this.f3886e = amVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.bdinstall.loader.c
    public boolean a(JSONObject jSONObject) {
        Map<String, Object> v = this.f3886e.v();
        if (v != null) {
            try {
                if (v.isEmpty()) {
                    return true;
                }
                for (Map.Entry<String, Object> entry : v.entrySet()) {
                    if (entry != null) {
                        jSONObject.put(entry.getKey(), entry.getValue());
                    }
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return true;
            }
        }
        return true;
    }
}
