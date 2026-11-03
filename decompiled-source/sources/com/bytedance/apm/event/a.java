package com.bytedance.apm.event;

import com.bytedance.apm.util.j;
import org.json.JSONObject;
/* compiled from: FpsDropEvent.java */
/* loaded from: classes.dex */
public class a extends com.bytedance.apm6.perf.base.model.b {
    public static final int MIN_DROP_FRAME = 0;

    /* renamed from: j  reason: collision with root package name */
    private static final String f1056j = "fps_drop";
    private static final String k = "scene";
    private static final String l = "total_scroll_time";
    private static final String m = "drop_time_rate";
    private static final String n = "refresh_rate";
    private int o;
    private int[] p;
    private int q;
    private String r;
    private long s;

    @Override // com.bytedance.apm6.perf.base.model.b
    protected String a() {
        return "fps_drop";
    }

    @Override // com.bytedance.apm6.perf.base.model.b
    protected JSONObject getFilters() {
        return null;
    }

    @Override // com.bytedance.apm6.monitor.c
    public boolean isValid() {
        return false;
    }

    public a(int i2, int[] iArr, int i3, String str, long j2) {
        this.o = i2;
        this.p = iArr;
        this.q = i3;
        this.r = str;
        this.s = j2;
    }

    @Override // com.bytedance.apm6.perf.base.model.b
    protected JSONObject b() {
        try {
            JSONObject jSONObject = new JSONObject();
            for (int i2 = 0; i2 <= this.o; i2++) {
                if (this.p[i2] > 0) {
                    jSONObject.put(String.valueOf(i2), this.p[i2]);
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.apm6.perf.base.model.b
    public JSONObject c() {
        try {
            new JSONObject().put("scene", this.r);
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.apm6.perf.base.model.b
    public JSONObject d() {
        try {
            float frameIntervalMillis = j.getFrameIntervalMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("total_scroll_time", this.s);
            jSONObject.put("drop_time_rate", 1.0f - ((this.q * 1.0f) / ((int) (((float) this.s) / frameIntervalMillis))));
            jSONObject.put("refresh_rate", this.o + 1);
        } catch (Throwable unused) {
        }
        return super.d();
    }
}
