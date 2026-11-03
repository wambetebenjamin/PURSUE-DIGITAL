package com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher;

import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.a;
import com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.f;
import java.util.List;
import org.json.JSONObject;
/* compiled from: DispatchTypedAction.java */
/* loaded from: classes2.dex */
public class g extends j {

    /* renamed from: i  reason: collision with root package name */
    private static final String f8203i = "g";

    /* renamed from: j  reason: collision with root package name */
    private f f8204j;

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.j
    public a.EnumC0115a a(l lVar, String str, List<String> list) {
        if (Logger.debug()) {
            Logger.d(f8203i, "dispatch action " + this.f8204j.b());
        }
        a.EnumC0115a enumC0115a = a.EnumC0115a.DISPATCH_NONE;
        Uri parse = Uri.parse(str);
        if (parse.getHost() != null && a(parse) && this.f8204j != null) {
            enumC0115a = a.EnumC0115a.DISPATCH_HIT;
            if (this.f8204j.b() == f.a.REQUEST_HEADER_DISPATCH_STRATEGY) {
                this.f8204j.a(lVar);
                list.set(0, str);
                return enumC0115a;
            }
            String a2 = this.f8204j.a(parse);
            if (!str.equals(a2)) {
                list.set(0, a2);
            } else {
                list.set(0, str);
            }
        }
        return enumC0115a;
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.j
    protected void a(String str, boolean z, int i2) {
        this.f8204j.a(str, z, i2);
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.j
    public int e() {
        return this.f8204j.b().ordinal();
    }

    @Override // com.bytedance.frameworks.baselib.network.http.ok3.impl.urldispatcher.j
    protected boolean a(JSONObject jSONObject, List<Boolean> list, String str, long j2) {
        a(jSONObject);
        String optString = jSONObject.optString("service_name");
        if (!TextUtils.isEmpty(optString)) {
            this.f8170g = optString;
        }
        int optInt = jSONObject.optInt("dispatch_strategy", 0);
        JSONObject optJSONObject = jSONObject.optJSONObject("strategy_info");
        if (optInt < 0 || optInt > 6) {
            this.f8204j = null;
            return false;
        }
        f a2 = f.a(f.a.values()[optInt], optJSONObject, str, j2, a());
        this.f8204j = a2;
        list.set(0, Boolean.valueOf(a2.a()));
        return true;
    }
}
