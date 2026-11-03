package com.bytedance.ttgame.module.loccom.impl;

import android.content.Context;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.module.loccom.api.ILocationCommonService;
import com.bytedance.ttgame.module.loccom.api.callback.LocationCallback;
import com.bytedance.ttgame.sdk.module.location.model.Location;
import com.meituan.robust.Constants;
/* loaded from: classes6.dex */
public class LocationCommonService implements ILocationCommonService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.module.loccom.api.ILocationCommonService
    public void getLocationByIp(Context context, LocationCallback<Location> locationCallback) {
        this.moduleApiMonitor.onApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.module.loccom.api.ILocationCommonService", "com.bytedance.ttgame.module.loccom.impl.LocationCommonService", "getLocationByIp", new String[]{"android.content.Context", "com.bytedance.ttgame.module.loccom.api.callback.LocationCallback"}, Constants.VOID);
        LocationCommon.getLocationByIp(context, locationCallback);
    }

    @Override // com.bytedance.ttgame.module.loccom.api.ILocationCommonService
    public Location getLocationInfo(Context context) {
        this.moduleApiMonitor.onApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.module.loccom.api.ILocationCommonService", "com.bytedance.ttgame.module.loccom.impl.LocationCommonService", "getLocationInfo", new String[]{"android.content.Context"}, "com.bytedance.ttgame.sdk.module.location.model.Location");
        return LocationCommon.getLocationInfoFromSp(context);
    }

    @Override // com.bytedance.ttgame.module.loccom.api.ILocationCommonService
    public Location getLocationByIpSync(Context context) {
        this.moduleApiMonitor.onApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.module.loccom.api.ILocationCommonService", "com.bytedance.ttgame.module.loccom.impl.LocationCommonService", "getLocationByIpSync", new String[]{"android.content.Context"}, "com.bytedance.ttgame.sdk.module.location.model.Location");
        return LocationCommon.getLocationByIp(context);
    }
}
