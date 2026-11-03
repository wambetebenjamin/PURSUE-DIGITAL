package com.bytedance.ttgame.main.internal;

import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.bytedance.ttgame.main.internal.net.INetworkClient;
import java.util.Map;
@InternalApi
/* loaded from: classes3.dex */
public interface INetService extends IModuleApi {
    public static final int NET_LEVEL1 = 1;
    public static final int NET_LEVEL2 = 2;
    public static final int NET_LEVEL3 = 4;
    public static final int NET_LEVEL4 = 8;
    public static final int NET_LEVEL5 = 16;
    public static final int NET_OSDK = 1000;

    String addCommonParams(String str, boolean z, Map<String, String> map);

    Map<String, String> addCommonParamsWithlevel(String str, boolean z, int i2, Map<String, String> map);

    String createNewUUID();

    String getClientUUID();

    int getNetEffectiveConnectionType();

    boolean isUsingProxy(Context context);

    boolean isVpnOn(Context context);

    INetworkClient newTTLogNetworkClient(Context context);

    void setClientUUID(String str);
}
