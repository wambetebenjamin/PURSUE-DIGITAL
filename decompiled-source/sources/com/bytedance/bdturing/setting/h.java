package com.bytedance.bdturing.setting;

import com.bytedance.bdturing.c;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: FreeFlowHost.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002¨\u0006\n"}, d2 = {"Lcom/bytedance/bdturing/setting/FreeFlowHost;", "", "()V", "init", "", RemoteConfigConstants.RequestFieldKey.APP_ID, "", "updateFreeFlowHost", "regionKey", "freeHost", "setting_i18nRelease"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public static final h f4317a = new h();

    private h() {
    }

    public final void a(String str) {
        if (str != null) {
            int hashCode = str.hashCode();
            if (hashCode != 1508632) {
                if (hashCode == 1509441) {
                    if (str.equals("1233")) {
                        com.bytedance.bdturing.h.i("FreeFlowHost", "replace free host");
                        a(c.s, "https://verification-va.tiktokv.com");
                        d.INSTANCE.getSETTINGS().optJSONObject("common").optJSONObject(g.HOST_KEY).put(c.s, "https://vcs-va.tiktokv.com");
                        return;
                    }
                    return;
                } else if (hashCode != 1721912 || !str.equals("8660")) {
                    return;
                }
            } else if (!str.equals("1180")) {
                return;
            }
            com.bytedance.bdturing.h.i("FreeFlowHost", "replace free host");
            a(c.r, "https://verify-sg.tiktokv.com");
            d.INSTANCE.getSETTINGS().optJSONObject("common").optJSONObject(g.HOST_KEY).put(c.r, "https://vcs-sg.tiktokv.com");
        }
    }

    private final void a(String str, String str2) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        JSONObject optJSONObject3 = d.INSTANCE.getSETTINGS().optJSONObject("sms");
        if (optJSONObject3 != null && (optJSONObject2 = optJSONObject3.optJSONObject(g.HOST_KEY)) != null) {
            optJSONObject2.put(str, str2);
        }
        JSONObject optJSONObject4 = d.INSTANCE.getSETTINGS().optJSONObject(g.VERIFY_SERVICE);
        if (optJSONObject4 == null || (optJSONObject = optJSONObject4.optJSONObject(g.HOST_KEY)) == null) {
            return;
        }
        optJSONObject.put(str, str2);
    }
}
