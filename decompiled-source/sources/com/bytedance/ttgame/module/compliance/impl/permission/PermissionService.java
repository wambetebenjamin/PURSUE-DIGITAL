package com.bytedance.ttgame.module.compliance.impl.permission;

import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.module.compliance.api.permission.IPermissionService;
import com.meituan.robust.Constants;
/* loaded from: classes5.dex */
public class PermissionService implements IPermissionService {
    public static String APPLSIT = "gsdk_applist";
    public static String KEEPALIVE = "gsdk_keepalive";
    public static String TAG = "PermissionProxy";
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.module.compliance.api.permission.IPermissionService
    public Boolean isAgreeApplistPermission() {
        this.moduleApiMonitor.onApiEnter("compliance:impl:permission", "com.bytedance.ttgame.module.compliance.api.permission.IPermissionService", "com.bytedance.ttgame.module.compliance.impl.permission.PermissionService", "isAgreeApplistPermission", new String[0], Constants.LANG_BOOLEAN);
        return true;
    }
}
