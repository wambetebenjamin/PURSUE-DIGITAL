package com.bytedance.ttgame.core.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.ProxyInfo;
import android.os.Build;
import com.bytedance.bdinstall.e;
import com.bytedance.ttgame.main.internal.INetService;
import com.bytedance.ttgame.main.internal.net.CronetDependAdapter;
import com.bytedance.ttgame.main.internal.net.INetworkClient;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.meituan.robust.Constants;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: NetService.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\tH\u0016JF\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\t2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\tH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0012\u0010\u0010\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u0013\u001a\u00020\u00072\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\u0019"}, d2 = {"Lcom/bytedance/ttgame/core/net/NetService;", "Lcom/bytedance/ttgame/main/internal/INetService;", "()V", "addCommonParams", "", "url", "isApi", "", "sdkParams", "", "addCommonParamsWithlevel", "level", "", "createNewUUID", "getClientUUID", "getNetEffectiveConnectionType", "isUsingProxy", "context", "Landroid/content/Context;", "isVpnOn", "newTTLogNetworkClient", "Lcom/bytedance/ttgame/main/internal/net/INetworkClient;", "setClientUUID", "", e.aH, "core_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NetService implements INetService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.main.internal.INetService
    public String createNewUUID() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "createNewUUID", new String[0], "java.lang.String");
        String createNewUUID = TTNetUtil.createNewUUID();
        Intrinsics.checkNotNullExpressionValue(createNewUUID, "createNewUUID()");
        return createNewUUID;
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public void setClientUUID(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "setClientUUID", new String[]{"java.lang.String"}, Constants.VOID);
        TTNetUtil.setClientUUID(str);
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public String getClientUUID() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "getClientUUID", new String[0], "java.lang.String");
        return TTNetUtil.getClientUUID();
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public String addCommonParams(String str, boolean z, Map<String, String> map) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "addCommonParams", new String[]{"java.lang.String", "boolean", "java.util.Map"}, "java.lang.String");
        String addCommonParams = TTNetUtil.addCommonParams(str, z, map);
        Intrinsics.checkNotNullExpressionValue(addCommonParams, "addCommonParams(url, isApi, sdkParams)");
        return addCommonParams;
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public Map<String, String> addCommonParamsWithlevel(String str, boolean z, int i2, Map<String, String> map) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "addCommonParamsWithlevel", new String[]{"java.lang.String", "boolean", Constants.INT, "java.util.Map"}, "java.util.Map");
        return TTNetUtil.getCommonParamsByLevel(i2, z, map, str);
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public INetworkClient newTTLogNetworkClient(Context context) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "newTTLogNetworkClient", new String[]{"android.content.Context"}, "com.bytedance.ttgame.main.internal.net.INetworkClient");
        Intrinsics.checkNotNullParameter(context, "context");
        return new GsdkLogNetworkClient(context);
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public int getNetEffectiveConnectionType() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "getNetEffectiveConnectionType", new String[0], Constants.INT);
        return CronetDependAdapter.f14228a.getNetEffectiveConnectionType();
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public boolean isUsingProxy(Context context) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "isUsingProxy", new String[]{"android.content.Context"}, "boolean");
        if (context != null) {
            Object systemService = context.getApplicationContext().getSystemService("connectivity");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
            ProxyInfo proxyInfo = null;
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    proxyInfo = connectivityManager.getDefaultProxy();
                } catch (Exception unused) {
                }
            }
            if (proxyInfo != null) {
                return true;
            }
        }
        return false;
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public boolean isVpnOn(Context context) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "isVpnOn", new String[]{"android.content.Context"}, "boolean");
        if (Build.VERSION.SDK_INT >= 21 && context != null) {
            Object systemService = context.getApplicationContext().getSystemService("connectivity");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
            try {
                Network[] allNetworks = connectivityManager.getAllNetworks();
                Intrinsics.checkNotNullExpressionValue(allNetworks, "connectivityManager.allNetworks");
                int length = allNetworks.length;
                for (int i2 = 0; i2 < length; i2++) {
                    NetworkInfo networkInfo = connectivityManager.getNetworkInfo(allNetworks[i2]);
                    if (networkInfo != null && networkInfo.isConnected()) {
                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(allNetworks[i2]);
                        if (networkCapabilities != null && networkCapabilities.hasTransport(4)) {
                            return true;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return false;
    }
}
