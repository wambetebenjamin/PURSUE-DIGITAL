package com.bytedance.bdinstall.storage;

import android.content.Context;
import java.util.Arrays;
/* compiled from: DeviceParamsProvider.java */
/* loaded from: classes.dex */
public class f extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public f(Context context, i iVar) {
        super(context, iVar);
    }

    @Override // com.bytedance.bdinstall.service.a
    public void c() {
        this.f3980d.a(Arrays.asList("openudid", "clientudid", "device_id", "install_id"));
    }
}
