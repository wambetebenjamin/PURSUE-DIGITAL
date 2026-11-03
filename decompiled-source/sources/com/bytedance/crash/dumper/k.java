package com.bytedance.crash.dumper;

import com.bytedance.crash.CrashType;
import com.bytedance.crash.ICommonParams;
import com.bytedance.crash.util.y;
import java.io.File;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: InnerCrashBodyDumper.java */
/* loaded from: classes2.dex */
public class k implements com.bytedance.crash.service.a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6529a = "patch_info";

    /* renamed from: b  reason: collision with root package name */
    public static final String f6530b = "bytest.json";

    /* renamed from: c  reason: collision with root package name */
    private ICommonParams f6531c;

    /* renamed from: d  reason: collision with root package name */
    private final String f6532d = "patch_info.json";

    public k(ICommonParams iCommonParams) {
        this.f6531c = iCommonParams;
    }

    @Override // com.bytedance.crash.service.a
    public void a(File file) {
        try {
            b(file);
            d.a(new File(file, f6530b));
        } catch (Exception unused) {
        }
    }

    private void b(File file) {
        try {
            List<String> patchInfo = this.f6531c.getPatchInfo();
            if (patchInfo == null) {
                return;
            }
            com.bytedance.crash.dumper.tools.f fVar = new com.bytedance.crash.dumper.tools.f(new File(file, "patch_info.json").getAbsolutePath());
            fVar.c();
            boolean z = false;
            for (String str : patchInfo) {
                if (z) {
                    fVar.e();
                }
                fVar.c(str);
                z = true;
            }
            fVar.d();
            fVar.g();
        } catch (Throwable unused) {
        }
    }

    private void a(JSONObject jSONObject, File file) {
        try {
            String d2 = com.bytedance.crash.util.k.d(new File(file, "patch_info.json"));
            if (d2 != null) {
                com.bytedance.crash.util.q.a(jSONObject, f6529a, new JSONArray(d2));
            }
        } catch (Exception e2) {
            y.i(e2);
        }
    }

    @Override // com.bytedance.crash.service.a
    public void a(JSONObject jSONObject, JSONObject jSONObject2, File file) {
        a(jSONObject, file);
        JSONObject a2 = d.a(new File(file, f6530b), jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("header");
        if (optJSONObject == null) {
            d.a(a2, jSONObject2);
        } else {
            d.a(a2, optJSONObject);
        }
    }

    @Override // com.bytedance.crash.service.a
    public JSONObject a(CrashType crashType) {
        if (crashType == CrashType.ENSURE) {
            JSONObject jSONObject = new JSONObject();
            a(jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            d.b(jSONObject2);
            com.bytedance.crash.util.q.a(jSONObject, "filters", jSONObject2);
            return jSONObject;
        }
        return null;
    }

    private void a(JSONObject jSONObject) {
        List<String> patchInfo;
        try {
            ICommonParams iCommonParams = this.f6531c;
            if (iCommonParams != null && (patchInfo = iCommonParams.getPatchInfo()) != null && !patchInfo.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (String str : patchInfo) {
                    jSONArray.put(str);
                }
                jSONObject.put(f6529a, jSONArray);
            }
        } catch (Throwable unused) {
        }
    }
}
