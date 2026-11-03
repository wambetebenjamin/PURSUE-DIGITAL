package com.bytedance.common.wschannel.server;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.o;
import com.bytedance.common.wschannel.model.g;
import com.bytedance.common.wschannel.p;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONArray;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WsChannelSaver.java */
/* loaded from: classes2.dex */
public class n implements g {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5997a = "n";

    /* renamed from: b  reason: collision with root package name */
    private final Context f5998b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(Context context) {
        this.f5998b = context;
    }

    @Override // com.bytedance.common.wschannel.server.g
    public Map<Integer, com.bytedance.common.wschannel.app.b> a() {
        String d2;
        JSONArray jSONArray;
        int length;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            d2 = p.a(this.f5998b).d();
            if (Logger.debug()) {
                Logger.d(f5997a, "load from sp wsAppStr: " + d2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!TextUtils.isEmpty(d2) && (length = (jSONArray = new JSONArray(d2)).length()) > 0) {
            ArrayList<Integer> i2 = p.a(this.f5998b).i();
            if (Logger.debug()) {
                Logger.d(f5997a, "load from sp disabledList: " + i2);
            }
            for (int i3 = 0; i3 < length; i3++) {
                com.bytedance.common.wschannel.model.g a2 = new g.a().a(jSONArray.optJSONObject(i3));
                if (a(a2) && (i2 == null || !i2.contains(Integer.valueOf(WsChannelService.getClientKey(a2))))) {
                    linkedHashMap.put(Integer.valueOf(WsChannelService.getClientKey(a2)), a2);
                }
            }
            if (Logger.debug()) {
                Logger.d(f5997a, "loadWsChannels final wsApp: " + linkedHashMap);
            }
            return linkedHashMap;
        }
        return linkedHashMap;
    }

    private boolean a(com.bytedance.common.wschannel.app.b bVar) {
        return bVar != null && bVar.a() > 0 && bVar.b() > 0 && !o.isEmpty(bVar.i()) && bVar.e() > 0 && !bVar.l().isEmpty() && !o.isEmpty(bVar.c()) && bVar.j() > 0 && !o.isEmpty(bVar.d()) && bVar.f() == 0;
    }

    @Override // com.bytedance.common.wschannel.server.g
    public void a(Map<Integer, com.bytedance.common.wschannel.app.b> map) {
        if (map == null) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        synchronized (WsChannelService.class) {
            for (Map.Entry<Integer, com.bytedance.common.wschannel.app.b> entry : map.entrySet()) {
                try {
                    if (a(entry.getValue())) {
                        jSONArray.put(entry.getValue().h());
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        try {
            Logger.d(f5997a, "save to sp : " + jSONArray.toString());
            p.a(this.f5998b).a(jSONArray.toString());
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
