package org.chromium;

import android.util.Pair;
import com.bytedance.frameworks.baselib.network.http.cronet.ICronetAppProvider;
import com.bytedance.frameworks.baselib.network.http.cronet.ICronetDepend;
import com.bytedance.frameworks.baselib.network.http.e;
import com.bytedance.frameworks.baselib.network.utils.b;
import com.bytedance.retrofit2.RetrofitMetrics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class CronetAppProviderManager {
    private static final String ADAPTER_CLASS_NAME = "com.bytedance.frameworks.baselib.network.http.cronet.ICronetAppProvider";
    private static volatile CronetAppProviderManager sCronetAppProviderManager;
    private ICronetAppProvider mCronetAppProvider;
    private Object mCronetAppProviderAdapter;
    private ICronetDepend mCronetDepend;

    private CronetAppProviderManager() {
    }

    public static CronetAppProviderManager inst() {
        if (sCronetAppProviderManager == null) {
            synchronized (CronetAppProviderManager.class) {
                if (sCronetAppProviderManager == null) {
                    sCronetAppProviderManager = new CronetAppProviderManager();
                }
            }
        }
        return sCronetAppProviderManager;
    }

    public void setAdapter(Object obj) {
        List allInterfaces;
        if (obj != null) {
            try {
                Class<?> cls = obj.getClass();
                if (cls != null && (allInterfaces = Utils.getAllInterfaces(cls)) != null && !allInterfaces.isEmpty()) {
                    Iterator it = allInterfaces.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        } else if (ADAPTER_CLASS_NAME.equals(((Class) it.next()).getName())) {
                            if (CronetDependManager.inst().loggerDebug()) {
                                CronetDependManager.inst().loggerD("CronetAppProviderManager", "inject CronetAppProviderManager success");
                            }
                            this.mCronetAppProviderAdapter = obj;
                        }
                    }
                }
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        if (obj instanceof ICronetAppProvider) {
            this.mCronetAppProvider = (ICronetAppProvider) obj;
        }
        if (obj instanceof ICronetDepend) {
            this.mCronetDepend = (ICronetDepend) obj;
        }
    }

    public void sendAppMonitorEvent(String str, String str2) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "Send monitor json = " + str + ", log type =" + str2);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.sendAppMonitorEvent(str, str2);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("sendAppMonitorEvent", new Class[]{String.class, String.class}, str, str2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onServerConfigUpdated(String str, String str2, String str3) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onServerConfigUpdated json = " + str);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onServerConfigEtagChanged(str2);
                this.mCronetAppProvider.onServerConfigABTestChanged(str3);
                this.mCronetAppProvider.onServerConfigUpdated(str);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onServerConfigEtagChanged", new Class[]{String.class}, str2);
            b.a(this.mCronetAppProviderAdapter).a("onServerConfigABTestChanged", new Class[]{String.class}, str3);
            b.a(this.mCronetAppProviderAdapter).a("onServerConfigUpdated", new Class[]{String.class}, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onTTNetDetectInfoChanged(String str) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onTTNetDetectInfoChanged info str = " + str);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onTTNetDetectInfoChanged(str);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onTTNetDetectInfoChanged", new Class[]{String.class}, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onTTDnsResolveResult(String str, String str2, int i2, int i3, int i4, List<String> list, String str3) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onTTDnsResolveResult info uuid = " + str + " host: " + str2);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onTTDnsResolveResult(str, str2, i2, i3, i4, list, str3);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onTTDnsResolveResult", new Class[]{String.class, String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, List.class, String.class}, str, str2, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), list, str3);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onMappingRequestStatus(String str, int i2) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onMappingRequestStatus key = " + str + " status = " + i2);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.a(str, i2);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onMappingRequestStatus", new Class[]{String.class, Integer.TYPE}, str, Integer.valueOf(i2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onEffectiveConnectionTypeChanged(int i2) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onEffectiveConnectionTypeChanged type = " + i2);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onEffectiveConnectionTypeChangedOnIOThread(i2);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onEffectiveConnectionTypeChangedOnIOThread", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onRTTOrThroughputEstimatesComputed(int i2, int i3, int i4) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onRTTOrThroughputEstimatesComputed httpRtt = " + i2 + ", transportRttMs = " + i3 + ",downstreamThroughputKbps = " + i4);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onRTTOrThroughputEstimatesComputedOnIOThread(i2, i3, i4);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onRTTOrThroughputEstimatesComputedOnIOThread", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onPacketLossComputed(int i2, double d2, double d3, double d4, double d5) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onPacketLossComputed protocol = " + i2 + ", upstreamLossRate = " + d2 + ", upstreamLossRateVariance = " + d3 + ", downstreamLossRate = " + d4 + ", downstreamLossRateVariance = " + d5);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onPacketLossComputedOnIOThread(i2, d2, d3, d4, d5);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onPacketLossComputedOnIOThread", new Class[]{Integer.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE}, Integer.valueOf(i2), Double.valueOf(d2), Double.valueOf(d3), Double.valueOf(d4), Double.valueOf(d5));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onNetworkQualityTypeChangedV3(int i2) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onNetworkQualityTypeChangedV3, network_quality_type = " + i2);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onEffectiveConnectionTypeChangedOnIOThread(i2);
                b.a("com.bytedance.ttnet.NQEManager", (RetrofitMetrics) null).a("notifyNQTChanged", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onEffectiveConnectionTypeChangedOnIOThread", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
            b.a("com.bytedance.ttnet.NQEManager").a("notifyNQTChanged", new Class[]{Integer.TYPE}, Integer.valueOf(i2));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onColdStartFinish() {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onColdStartFinish ");
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onColdStartFinish();
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onColdStartFinish");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onClientIPChanged(String str) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onClientIPChanged ");
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onClientIPChanged(str);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onClientIPChanged", new Class[]{String.class}, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onPublicIPsChanged(List<String> list, List<String> list2) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onPublicIPsChanged ");
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onPublicIPsChanged(list, list2);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onPublicIPsChanged", new Class[]{List.class, List.class}, list, list2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onStoreIdcChanged(String str, String str2, String str3, String str4, String str5, String str6) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onStoreIdcChanged ");
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onStoreIdcChanged(str, str2, str3, str4, str5, str6);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onStoreIdcChanged", new Class[]{String.class, String.class, String.class, String.class, String.class, String.class}, str, str2, str3, str4, str5, str6);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onDropReasonChanged(boolean z, List<Integer> list) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onDropReasonChanged " + z + ", Drop Reason List " + list);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onDropReasonChanged(z, list);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onDropReasonChanged", new Class[]{Boolean.TYPE, List.class}, Boolean.valueOf(z), list);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onCronetBootSucceed() {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onCronetBootSucceed ");
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onCronetBootSucceed();
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onCronetBootSucceed");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onTNCUpdateFailed(String[] strArr, String str) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onTNCUpdateFailed ");
        }
        if (strArr != null) {
            try {
                if (strArr.length > 0) {
                    ArrayList<String> arrayList = new ArrayList<>();
                    Collections.addAll(arrayList, strArr);
                    if (e.i()) {
                        ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                        if (iCronetAppProvider == null) {
                            return;
                        }
                        iCronetAppProvider.onTNCUpdateFailed(arrayList, str);
                    } else {
                        Object obj = this.mCronetAppProviderAdapter;
                        if (obj == null) {
                            return;
                        }
                        b.a(obj).a("onTNCUpdateFailed", new Class[]{ArrayList.class, String.class}, arrayList, str);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public Map<String, String> onCallToAddSecurityFactor(String str, Map<String, String> map) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onCallToAddSecurityFactor info url = " + str);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.onCallToAddSecurityFactor(str, map);
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (Map) b.a(obj).a("onCallToAddSecurityFactor", new Class[]{String.class, Map.class}, str, map).a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void onWiFiToCellStateChanged(int i2, int i3) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onWiFiToCellStateChanged, prev state = " + i2 + ", curr state = " + i3);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onWiFiToCellStateChanged(i2, i3);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onWiFiToCellStateChanged", new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), Integer.valueOf(i3)).a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onMultiNetworkStateChanged(int i2, int i3) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onMultiNetworkStateChanged, prev state = " + i2 + ", curr state = " + i3);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onMultiNetworkStateChanged(i2, i3);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onMultiNetworkStateChanged", new Class[]{Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), Integer.valueOf(i3)).a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onUserSpecifiedNetworkEnabled(boolean z) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onUserSpecifiedNetworkEnabled, enable = " + z);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onUserSpecifiedNetworkEnabled(z);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onUserSpecifiedNetworkEnabled", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onNetworkQualityLevelChanged(int i2) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onNetworkQualityLevelChanged, nql: " + i2);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onNetworkQualityLevelChangedOnIOThread(i2);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onNetworkQualityLevelChangedOnIOThread", new Class[]{Integer.TYPE}, Integer.valueOf(i2)).a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void onNetworkQualityRttAndThroughputNotified(int i2, int i3, int i4) {
        if (CronetDependManager.inst().loggerDebug()) {
            CronetDependManager.inst().loggerD("CronetAppProviderManager", "onNetworkQualityRttAndThroughputNotified, effectiveHrttMs: " + i2 + ", effectiveTrttMs: " + i3 + ", effectiveRxThroughputKbps: " + i4);
        }
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onNetworkQualityRttAndThroughputNotifiedOnIOThread(i2, i3, i4);
                return;
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return;
            }
            b.a(obj).a("onNetworkQualityRttAndThroughputNotifiedOnIOThread", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE}, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)).a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getUserId() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getUserId();
        }
        return getStringAttribute("getUserId");
    }

    public String getAppId() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getAppId();
        }
        return getStringAttribute("getAppId");
    }

    public String getOSApi() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getOSApi();
        }
        return getStringAttribute("getOSApi");
    }

    public String getDeviceId() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getDeviceId();
        }
        return getStringAttribute("getDeviceId");
    }

    public String getNetAccessType() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getNetAccessType();
        }
        return getStringAttribute("getNetAccessType");
    }

    public String getVersionCode() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getVersionCode();
        }
        return getStringAttribute("getVersionCode");
    }

    public String getDeviceType() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getDeviceType();
        }
        return getStringAttribute("getDeviceType");
    }

    public String getAppName() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getAppName();
        }
        return getStringAttribute("getAppName");
    }

    public String getSdkAppId() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getSdkAppId();
        }
        return getStringAttribute("getSdkAppId");
    }

    public String getSdkVersion() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getSdkVersion();
        }
        return getStringAttribute("getSdkVersion");
    }

    public String getChannel() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getChannel();
        }
        return getStringAttribute("getChannel");
    }

    public String getOSVersion() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getOSVersion();
        }
        return getStringAttribute("getOSVersion");
    }

    public String getAbi() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getAbi();
        }
        return getStringAttribute("getAbi");
    }

    public String getDevicePlatform() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getDevicePlatform();
        }
        return getStringAttribute("getDevicePlatform");
    }

    public String getDeviceBrand() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getDeviceBrand();
        }
        return getStringAttribute("getDeviceBrand");
    }

    public String getDeviceModel() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getDeviceModel();
        }
        return getStringAttribute("getDeviceModel");
    }

    public String getVersionName() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getVersionName();
        }
        return getStringAttribute("getVersionName");
    }

    public String getUpdateVersionCode() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getUpdateVersionCode();
        }
        return getStringAttribute("getUpdateVersionCode");
    }

    public String getManifestVersionCode() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getManifestVersionCode();
        }
        return getStringAttribute("getManifestVersionCode");
    }

    public String getStoreIdc() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getStoreIdc();
        }
        return getStringAttribute("getStoreIdc");
    }

    public String getRegion() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getRegion();
        }
        return getStringAttribute("getRegion");
    }

    public String getSysRegion() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getSysRegion();
        }
        return getStringAttribute("getSysRegion");
    }

    public String getCarrierRegion() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getCarrierRegion();
        }
        return getStringAttribute("getCarrierRegion");
    }

    public String getInitRegion() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getAppInitialRegionInfo();
        }
        return getStringAttribute("getAppInitialRegionInfo");
    }

    public String getTNCRequestFlags() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getTNCRequestFlags();
        }
        return getStringAttribute("getTNCRequestFlags");
    }

    public String getHttpDnsRequestFlags() {
        if (e.i()) {
            ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
            return iCronetAppProvider == null ? "" : iCronetAppProvider.getHttpDnsRequestFlags();
        }
        return getStringAttribute("getHttpDnsRequestFlags");
    }

    private String getStringAttribute(String str) {
        try {
            Object obj = this.mCronetAppProviderAdapter;
            return obj == null ? "" : (String) b.a(obj).a(str).a();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public Map<String, String> getGetDomainDependHostMap() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return Collections.emptyMap();
                }
                return iCronetAppProvider.getGetDomainDependHostMap();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return Collections.emptyMap();
            }
            return (Map) b.a(obj).a("getGetDomainDependHostMap").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return Collections.emptyMap();
        }
    }

    public boolean getOptInit() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return false;
                }
                return iCronetAppProvider.getOptInit();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return false;
            }
            return ((Boolean) b.a(obj).a("getOptInit").a()).booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public long getAppStartTime() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return -1L;
                }
                return iCronetAppProvider.getAppStartTime();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return -1L;
            }
            return ((Long) b.a(obj).a("getAppStartTime").a()).longValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return -1L;
        }
    }

    public String getGetDomainDefaultJSON() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.getGetDomainDefaultJSON();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (String) b.a(obj).a("getGetDomainDefaultJSON").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public ArrayList<byte[]> getOpaqueData() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.getOpaqueData();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (ArrayList) b.a(obj).a("getOpaqueData").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public Map<String[], Pair<byte[], byte[]>> getClientOpaqueData() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.getClientOpaqueData();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (Map) b.a(obj).a("getClientOpaqueData").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public Map<String, String> getTNCRequestHeader() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.getTNCRequestHeader();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (Map) b.a(obj).a("getTNCRequestHeader").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public Map<String, String> getTNCRequestQuery() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.getTNCRequestQuery();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (Map) b.a(obj).a("getTNCRequestQuery").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public boolean isEnableQuic() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return false;
                }
                return iCronetAppProvider.isEnableQuic();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return false;
            }
            return ((Boolean) b.a(obj).a("isEnableQuic").a()).booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean isEnableHttp2() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return false;
                }
                return iCronetAppProvider.isEnableHttp2();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return false;
            }
            return ((Boolean) b.a(obj).a("isEnableHttp2").a()).booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public boolean isEnableBrotli() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return false;
                }
                return iCronetAppProvider.isEnableBrotli();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return false;
            }
            return ((Boolean) b.a(obj).a("isEnableBrotli").a()).booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public Map<String, Pair<Integer, Integer>> getQuicHint() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.getQuicHint();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (Map) b.a(obj).a("getQuicHint").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public boolean isBOEProxyEnabled() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return false;
                }
                return iCronetAppProvider.isBOEProxyEnabled();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return false;
            }
            return ((Boolean) b.a(obj).a("isBOEProxyEnabled").a()).booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public String getBypassBOEJSON() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.getBypassBOEJSON();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (String) b.a(obj).a("getBypassBOEJSON").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public String getStoreIdcRuleJSON() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.getStoreIdcRuleJSON();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (String) b.a(obj).a("getStoreIdcRuleJSON").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public String getIsDropFirstTnc() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.getIsDropFirstTnc();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (String) b.a(obj).a("getIsDropFirstTnc").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public void doLoadLibrary(String str) {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.doLoadLibrary(str);
            } else {
                Object obj = this.mCronetAppProviderAdapter;
                if (obj == null) {
                    return;
                }
                b.a(obj).a("doLoadLibrary", new Class[]{String.class}, str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public boolean needCustomLoadLibrary() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return false;
                }
                return iCronetAppProvider.needCustomLoadLibrary();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return false;
            }
            return ((Boolean) b.a(obj).a("needCustomLoadLibrary").a()).booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public String getCronetSoPath() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.getCronetSoPath();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (String) b.a(obj).a("getCronetSoPath").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public int getProcessFlag() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return 0;
                }
                return iCronetAppProvider.getProcessFlag();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return 0;
            }
            return ((Integer) b.a(obj).a("getProcessFlag").a()).intValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public int getWifiFrequency() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return 0;
                }
                return iCronetAppProvider.getWifiFrequency();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return 0;
            }
            return ((Integer) b.a(obj).a("getWifiFrequency").a()).intValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public int getWifiRssi() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return 0;
                }
                return iCronetAppProvider.getWifiRssi();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return 0;
            }
            return ((Integer) b.a(obj).a("getWifiRssi").a()).intValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return 0;
        }
    }

    public String getNetworkOperator() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                return iCronetAppProvider == null ? "" : iCronetAppProvider.getNetworkOperator();
            }
            Object obj = this.mCronetAppProviderAdapter;
            return obj == null ? "" : (String) b.a(obj).a("getNetworkOperator").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public String getSimOperator() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                return iCronetAppProvider == null ? "" : iCronetAppProvider.getSimOperator();
            }
            Object obj = this.mCronetAppProviderAdapter;
            return obj == null ? "" : (String) b.a(obj).a("getSimOperator").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public List<String> getAbSdkVersion() {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return null;
                }
                return iCronetAppProvider.getAbSdkVersion();
            }
            Object obj = this.mCronetAppProviderAdapter;
            if (obj == null) {
                return null;
            }
            return (List) b.a(obj).a("getAbSdkVersion").a();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public void onTncRequestSucceeded(boolean z, boolean z2, int i2) {
        try {
            if (e.i()) {
                ICronetAppProvider iCronetAppProvider = this.mCronetAppProvider;
                if (iCronetAppProvider == null) {
                    return;
                }
                iCronetAppProvider.onTncRequestSucceeded(z, z2, i2);
            } else {
                Object obj = this.mCronetAppProviderAdapter;
                if (obj == null) {
                    return;
                }
                b.a(obj).a("onTncRequestSucceeded", new Class[]{Boolean.TYPE, Boolean.TYPE, Integer.TYPE}, Boolean.valueOf(z), Boolean.valueOf(z2), Integer.valueOf(i2)).a();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
