package com.bytedance.ttgame.module.compliance.impl.permission;

import com.bytedance.ttgame.module.compliance.api.permission.IPermissionService;
import com.meituan.robust.Constants;
/* loaded from: classes5.dex */
public class Proxy__PermissionService implements IPermissionService {
    private PermissionService proxy = new PermissionService();

    @Override // com.bytedance.ttgame.module.compliance.api.permission.IPermissionService
    public Boolean isAgreeApplistPermission() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("compliance:impl:permission", "com.bytedance.ttgame.module.compliance.api.permission.IPermissionService", "com.bytedance.ttgame.module.compliance.impl.permission.PermissionService", "isAgreeApplistPermission", new String[0], Constants.LANG_BOOLEAN);
        return this.proxy.isAgreeApplistPermission();
    }

    public IPermissionService getProxy() {
        return this.proxy;
    }
}
