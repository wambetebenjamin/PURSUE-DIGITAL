package com.bytedance.platform.godzilla.sysopt;

import java.util.ArrayList;
import java.util.List;
/* compiled from: SysOptPluginGroup.java */
/* loaded from: classes2.dex */
public class k extends com.bytedance.platform.godzilla.plugin.b {
    @Override // com.bytedance.platform.godzilla.plugin.b, com.bytedance.platform.godzilla.plugin.a
    public String getName() {
        return "SysOptPlugin";
    }

    @Override // com.bytedance.platform.godzilla.plugin.b
    protected List<com.bytedance.platform.godzilla.plugin.a> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new d());
        arrayList.add(new a());
        arrayList.add(new i());
        arrayList.add(new f());
        return arrayList;
    }
}
