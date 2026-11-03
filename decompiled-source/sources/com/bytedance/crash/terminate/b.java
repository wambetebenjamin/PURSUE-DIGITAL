package com.bytedance.crash.terminate;

import android.app.ApplicationExitInfo;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import com.bytedance.crash.util.ae;
import com.bytedance.crash.util.q;
import com.facebook.internal.ServerProtocol;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* compiled from: AppExitInfo.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private String f7091a;

    /* renamed from: b  reason: collision with root package name */
    private long f7092b;

    /* renamed from: c  reason: collision with root package name */
    private int f7093c;

    /* renamed from: d  reason: collision with root package name */
    private String f7094d;

    /* renamed from: e  reason: collision with root package name */
    private int f7095e;

    /* renamed from: f  reason: collision with root package name */
    private int f7096f;

    /* renamed from: g  reason: collision with root package name */
    private String f7097g;

    /* renamed from: h  reason: collision with root package name */
    private String f7098h;

    /* renamed from: i  reason: collision with root package name */
    private String f7099i;

    /* renamed from: j  reason: collision with root package name */
    private long f7100j;
    private long k;
    private String l;

    public static b a(ApplicationExitInfo applicationExitInfo) {
        b bVar = new b();
        bVar.f7091a = applicationExitInfo.getProcessName();
        bVar.f7092b = applicationExitInfo.getTimestamp();
        bVar.f7093c = applicationExitInfo.getPid();
        bVar.f7096f = applicationExitInfo.getImportance();
        bVar.f7094d = c.a(applicationExitInfo.getReason());
        bVar.f7095e = applicationExitInfo.getStatus();
        bVar.f7100j = applicationExitInfo.getPss();
        bVar.k = applicationExitInfo.getRss();
        bVar.f7097g = (applicationExitInfo.getPss() / 1024) + "M";
        bVar.f7098h = (applicationExitInfo.getRss() / 1024) + "M";
        bVar.f7099i = applicationExitInfo.getDescription();
        try {
            bVar.l = c.b(((Integer) f.a((Object) applicationExitInfo, "getSubReason", new Object[0])).intValue());
        } catch (Throwable unused) {
            bVar.l = "UNKNOWN";
        }
        return bVar;
    }

    public String a(long j2) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f7094d);
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("pid:");
        sb.append(this.f7093c);
        sb.append(" process:");
        sb.append(this.f7091a);
        sb.append(" alive_time:");
        sb.append(this.f7092b - j2);
        sb.append(" last_alive_time:");
        sb.append(this.f7092b - j2);
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("#exitInfo\n");
        if (Build.VERSION.SDK_INT >= 24) {
            sb.append(new SimpleDateFormat().format(new Date(this.f7092b))).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        sb.append("importance=").append(this.f7096f).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("sub_reason=").append(this.l).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("status=").append(this.f7095e).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("description=").append(this.f7099i).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("pss=").append(this.f7097g).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("reason=").append(this.f7094d).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("process=").append(this.f7091a).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("rss=").append(this.f7098h).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        return sb.toString();
    }

    public JSONObject a(long j2, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("exit_status", String.valueOf(c()));
        hashMap.put("exit_pss", c.a(k()));
        hashMap.put("exit_rss", c.a(l()));
        hashMap.put("has_exit_info", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        hashMap.put(com.bytedance.crash.constants.c.E, a());
        hashMap.put("exit_sub_reason", b());
        HashMap hashMap2 = new HashMap(hashMap);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("event_type", "exception");
            jSONObject.put("crash_time", this.f7092b);
            jSONObject.put("log_type", "PROCESS_DIED");
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("message", "PROCESS_DIED");
            jSONObject.put(com.bytedance.crash.entity.b.CRASH_THREAD_NAME, "UNKNOW");
            jSONObject.put("crash_md5", com.bytedance.crash.util.d.a(str));
            jSONObject.put("process_name", d());
            jSONObject.put("app_start_time", j2);
            jSONObject.put(com.bytedance.apm.perf.traffic.a.KEY_EXCEPTION_TYPE, 1);
            jSONObject.put(com.bytedance.crash.entity.b.PID, this.f7093c);
            jSONObject.put(com.bytedance.crash.event.f.f6711c, "Native");
            jSONObject.put("method", "Native");
            jSONObject.put("is_core", 1);
            jSONObject.put(com.bytedance.crash.event.f.f6713e, -1);
            jSONObject.put(com.bytedance.crash.event.f.f6710b, com.bytedance.crash.event.e.f6696a);
            jSONObject.put("stack", str);
            jSONObject.put(com.bytedance.crash.constants.c.f6392i, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            jSONObject.put("java_data", ae.DIED_PROCESS_STACK);
            b(jSONObject, hashMap);
            a(jSONObject, hashMap2);
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    private void b(JSONObject jSONObject, Map<String, String> map) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject2.put(entry.getKey(), entry.getValue());
                }
            }
            if (q.isEmpty(jSONObject2)) {
                return;
            }
            jSONObject.put("custom", jSONObject2);
        } catch (Throwable unused) {
        }
    }

    public void a(JSONObject jSONObject, Map<String, String> map) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    jSONObject2.put(entry.getKey(), entry.getValue());
                }
            }
            if (q.isEmpty(jSONObject2)) {
                return;
            }
            jSONObject.put("filters", jSONObject2);
        } catch (Throwable unused) {
        }
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject("filters");
            if (optJSONObject == null) {
                optJSONObject = new JSONObject();
                jSONObject.put("filters", optJSONObject);
            }
            optJSONObject.put(str, str2);
        } catch (Throwable unused) {
        }
    }

    public String a() {
        return this.f7094d;
    }

    public String b() {
        return this.l;
    }

    public int c() {
        return this.f7095e;
    }

    public String d() {
        return this.f7091a;
    }

    public long e() {
        return this.f7092b;
    }

    public int f() {
        return this.f7093c;
    }

    public int g() {
        return this.f7096f;
    }

    public String h() {
        return this.f7097g;
    }

    public String i() {
        return this.f7098h;
    }

    public String j() {
        return this.f7099i;
    }

    public long k() {
        return this.f7100j;
    }

    public long l() {
        return this.k;
    }

    public String toString() {
        return "AppExitInfo{mProcessName='" + this.f7091a + "', mTimestamp=" + this.f7092b + ", mPid=" + this.f7093c + ", mReason='" + this.f7094d + "', mStatus=" + this.f7095e + ", mImportance=" + this.f7096f + ", mPss='" + this.f7097g + "', mRss='" + this.f7098h + "', mDescription='" + this.f7099i + "', mRealPss=" + this.f7100j + ", mRealRss=" + this.k + ", subReason='" + this.l + "'}";
    }
}
