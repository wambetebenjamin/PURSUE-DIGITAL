package com.bytedance.bdinstall.loader;

import android.os.Build;
import android.text.TextUtils;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RomLoader.java */
/* loaded from: classes.dex */
public class v extends c {
    /* JADX INFO: Access modifiers changed from: package-private */
    public v() {
        super(true, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.bdinstall.loader.c
    public boolean a(JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder(16);
        if (com.bytedance.bdinstall.util.p.n()) {
            sb.append("MIUI-");
        } else if (com.bytedance.bdinstall.util.p.g()) {
            sb.append("FLYME-");
        } else {
            String a2 = com.bytedance.bdinstall.util.p.a();
            if (com.bytedance.bdinstall.util.p.a(a2)) {
                sb.append("EMUI-");
                if (!TextUtils.isEmpty(a2)) {
                    sb.append(a2).append("-");
                }
            }
        }
        sb.append(Build.VERSION.INCREMENTAL);
        jSONObject.put(com.bytedance.bdinstall.e.u, sb.toString());
        String b2 = com.bytedance.bdinstall.util.p.b();
        if (TextUtils.isEmpty(b2)) {
            return true;
        }
        jSONObject.put("rom_version", b2);
        return true;
    }
}
