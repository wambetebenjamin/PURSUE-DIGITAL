package com.bytedance.ttgame.module.loccom.api;

import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.loccom.api.callback.LocationCallback;
import com.bytedance.ttgame.sdk.module.location.model.Location;
/* loaded from: classes6.dex */
public interface ILocationCommonService extends IModuleApi {
    void getLocationByIp(Context context, LocationCallback<Location> locationCallback);

    Location getLocationByIpSync(Context context);

    Location getLocationInfo(Context context);
}
