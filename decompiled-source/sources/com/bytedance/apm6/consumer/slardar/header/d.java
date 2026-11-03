package com.bytedance.apm6.consumer.slardar.header;

import android.os.Process;
import android.text.TextUtils;
import com.bytedance.apm6.util.f;
import org.json.JSONObject;
/* compiled from: HeaderUtils.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static long f1814a = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long a(long j2) {
        if (j2 < 0) {
            return 0L;
        }
        return j2 >> 16;
    }

    public static JSONObject serialize(a aVar) {
        if (aVar == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (aVar.getStableExtra() != null) {
                jSONObject = f.copyJson2(jSONObject, aVar.getStableExtra());
            }
            if (aVar.getDynamicExtra() != null) {
                jSONObject = f.copyJson2(jSONObject, aVar.getDynamicExtra());
            }
            jSONObject.put("version_code", aVar.g());
            jSONObject.put("version_name", aVar.h());
            jSONObject.put("manifest_version_code", aVar.f());
            jSONObject.put("update_version_code", aVar.d());
            jSONObject.put("app_version", aVar.e());
            jSONObject.put("os", aVar.j());
            jSONObject.put("device_platform", aVar.k());
            jSONObject.put("os_version", aVar.l());
            jSONObject.put("os_api", aVar.m());
            jSONObject.put("device_model", aVar.n());
            jSONObject.put("device_brand", aVar.o());
            jSONObject.put("device_manufacturer", aVar.p());
            jSONObject.put("process_name", aVar.q());
            jSONObject.put("sid", aVar.r());
            jSONObject.put("rom_version", aVar.s());
            jSONObject.put("package", aVar.t());
            jSONObject.put("monitor_version", aVar.u());
            jSONObject.put("channel", aVar.c());
            jSONObject.put("aid", aVar.a());
            if (!TextUtils.isEmpty(aVar.b())) {
                jSONObject.put("device_id", aVar.b());
            }
            jSONObject.put("uid", aVar.v());
            jSONObject.put("phone_startup_time", aVar.w());
            jSONObject.put("release_build", aVar.i());
            if (aVar.A() != -1) {
                jSONObject.put(com.bytedance.apm6.consumer.slardar.b.KEY_CONFIG_TIME, String.valueOf(aVar.A()));
            }
            if (!TextUtils.isEmpty(aVar.x())) {
                jSONObject.put("verify_info", aVar.x());
            }
            jSONObject.put("current_update_version_code", aVar.z());
            if (aVar.getNtpTime() != -1) {
                jSONObject.put(com.bytedance.apm6.consumer.slardar.b.KEY_NTP_TIME, aVar.getNtpTime());
            }
            if (aVar.getNtpOffset() != -1) {
                jSONObject.put(com.bytedance.apm6.consumer.slardar.b.KEY_NTP_OFFSET, aVar.getNtpOffset());
            }
            if (aVar.y() != null) {
                jSONObject.put("filters", aVar.y());
            }
            return jSONObject;
        } catch (Exception unused) {
            return null;
        }
    }

    public static a deserialize(byte[] bArr) {
        try {
            a aVar = new a();
            JSONObject jSONObject = new JSONObject(new String(bArr));
            aVar.n(f.removeString(jSONObject, "version_code"));
            aVar.o(f.removeString(jSONObject, "version_name"));
            aVar.m(f.removeString(jSONObject, "manifest_version_code"));
            aVar.k(f.removeString(jSONObject, "update_version_code"));
            aVar.l(f.removeString(jSONObject, "app_version"));
            aVar.a(f.removeString(jSONObject, "os"));
            aVar.b(f.removeString(jSONObject, "device_platform"));
            aVar.c(f.removeString(jSONObject, "os_version"));
            aVar.a(f.removeInt(jSONObject, "os_api"));
            aVar.d(f.removeString(jSONObject, "device_model"));
            aVar.e(f.removeString(jSONObject, "device_brand"));
            aVar.f(f.removeString(jSONObject, "device_manufacturer"));
            aVar.g(f.removeString(jSONObject, "process_name"));
            aVar.a(f.removeLong(jSONObject, "sid"));
            aVar.h(f.removeString(jSONObject, "rom_version"));
            aVar.q(f.removeString(jSONObject, "package"));
            aVar.r(f.removeString(jSONObject, "monitor_version"));
            aVar.j(f.removeString(jSONObject, "channel"));
            aVar.b(f.removeInt(jSONObject, "aid"));
            aVar.i(f.removeString(jSONObject, "device_id"));
            aVar.c(f.removeLong(jSONObject, "phone_startup_time"));
            aVar.p(f.removeString(jSONObject, "release_build"));
            aVar.b(f.removeLong(jSONObject, "uid"));
            aVar.s(f.removeString(jSONObject, "verify_info"));
            aVar.t(f.removeString(jSONObject, "current_update_version_code"));
            if (jSONObject.has(com.bytedance.apm6.consumer.slardar.b.KEY_CONFIG_TIME)) {
                aVar.d(f.removeInt(jSONObject, com.bytedance.apm6.consumer.slardar.b.KEY_CONFIG_TIME));
            }
            try {
                aVar.a(new JSONObject((String) jSONObject.remove("filters")));
            } catch (Exception unused) {
            }
            aVar.setStableExtra(jSONObject);
            return aVar;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static long getUniqueHeaderId() {
        if (f1814a == -1) {
            f1814a = (com.bytedance.apm6.foundation.context.a.getStartId() << 16) | Process.myPid();
        }
        return f1814a;
    }
}
