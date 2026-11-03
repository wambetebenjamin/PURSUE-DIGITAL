package com.bytedance.frameworks.baselib.network.queryfilter;

import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.b;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
/* compiled from: QueryFilterStateListener.java */
/* loaded from: classes2.dex */
public class e extends b.a {

    /* renamed from: b  reason: collision with root package name */
    private static volatile e f8420b;

    /* renamed from: a  reason: collision with root package name */
    public AtomicBoolean f8421a;

    public static e e() {
        if (f8420b == null) {
            synchronized (e.class) {
                if (f8420b == null) {
                    f8420b = new e(30000);
                }
            }
        }
        return f8420b;
    }

    public e(int i2) {
        super(i2);
        this.f8421a = new AtomicBoolean(false);
    }

    public void a(JSONObject jSONObject) {
        a(jSONObject.optInt("qf_enter_background_time", 30000));
    }

    @Override // com.bytedance.frameworks.baselib.network.b.a
    public void c() {
        this.f8421a.set(false);
        if (Logger.debug()) {
            Logger.d("QueryFilterStateListener", "onEnterToForeground");
        }
    }

    @Override // com.bytedance.frameworks.baselib.network.b.a
    public void d() {
        this.f8421a.set(true);
        if (Logger.debug()) {
            Logger.d("QueryFilterStateListener", "onEnterToBackground");
        }
    }
}
