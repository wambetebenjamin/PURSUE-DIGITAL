package com.bytedance.apm6.fd;

import com.bytedance.apm6.perf.base.e;
import com.bytedance.apm6.util.g;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.List;
import org.json.JSONObject;
/* compiled from: FdEvent.java */
/* loaded from: classes.dex */
public class d extends com.bytedance.apm6.perf.base.model.b {

    /* renamed from: j  reason: collision with root package name */
    private static final String f2187j = "fd";
    private static final String k = "fd_count";
    private static final String l = "fd_detail";
    private int m;
    private List<String> n;
    private boolean o;

    @Override // com.bytedance.apm6.perf.base.model.b
    protected String a() {
        return "fd";
    }

    @Override // com.bytedance.apm6.perf.base.model.b
    protected JSONObject b() {
        return null;
    }

    @Override // com.bytedance.apm6.monitor.c
    public boolean isValid() {
        return true;
    }

    public d(int i2, List<String> list, boolean z) {
        this.m = i2;
        this.n = list;
        this.o = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.apm6.perf.base.model.b
    public JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(k, this.m);
            if (!g.isEmpty(this.n)) {
                jSONObject.put(l, g.listToString(this.n, ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE));
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.apm6.perf.base.model.b
    public JSONObject c() {
        JSONObject c2 = super.c();
        if (c2 == null) {
            try {
                c2 = new JSONObject();
            } catch (Exception unused) {
            }
        }
        c2.put(com.bytedance.apm.constant.c.KEY_IS_FRONT, this.o);
        return c2;
    }

    @Override // com.bytedance.apm6.perf.base.model.b
    protected JSONObject getFilters() {
        return e.getInstance().getPerfFiltersJson();
    }
}
