package com.bytedance.frameworks.baselib.network.http.cronet;

import android.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public interface ICronetAppProvider {
    void a(String str, int i2);

    void doLoadLibrary(String str);

    String getAbClient();

    String getAbFeature();

    String getAbFlag();

    List<String> getAbSdkVersion();

    String getAbVersion();

    String getAbi();

    String getAppId();

    d getAppInfoSubset();

    String getAppInitialRegionInfo();

    String getAppName();

    long getAppStartTime();

    String getBypassBOEJSON();

    String getCarrierRegion();

    String getChannel();

    String getCityName();

    Map<String[], Pair<byte[], byte[]>> getClientOpaqueData();

    String getCronetSoPath();

    String getDPI();

    String getDeviceBrand();

    String getDeviceId();

    String getDeviceModel();

    String getDevicePlatform();

    String getDeviceType();

    String getGetDomainDefaultJSON();

    Map<String, String> getGetDomainDependHostMap();

    String getHttpDnsRequestFlags();

    String getIId();

    String getIsDropFirstTnc();

    String getLanguage();

    boolean getLazyInitCronetIPC();

    String getManifestVersionCode();

    String getNetAccessType();

    String getNetworkOperator();

    String getOSApi();

    String getOSVersion();

    ArrayList<byte[]> getOpaqueData();

    String getOpenUdid();

    boolean getOptInit();

    boolean getOptNqeDefault();

    boolean getOptNqePersist();

    int getProcessFlag();

    Map<String, Pair<Integer, Integer>> getQuicHint();

    String getRegion();

    String getResolution();

    String getRticket();

    String getSdkAppId();

    String getSdkVersion();

    String getSimOperator();

    String getSsmix();

    String getStoreIdc();

    String getStoreIdcRuleJSON();

    String getSysRegion();

    String getTNCRequestFlags();

    Map<String, String> getTNCRequestHeader();

    Map<String, String> getTNCRequestQuery();

    @Deprecated
    String getUUID();

    String getUpdateVersionCode();

    String getUserId();

    String getVersionCode();

    String getVersionName();

    int getWifiFrequency();

    int getWifiRssi();

    boolean isBOEProxyEnabled();

    boolean isEnableBrotli();

    boolean isEnableHttp2();

    boolean isEnableQuic();

    boolean needCustomLoadLibrary();

    Map<String, String> onCallToAddSecurityFactor(String str, Map<String, String> map);

    void onClientIPChanged(String str);

    void onColdStartFinish();

    void onCronetBootSucceed();

    void onDropReasonChanged(boolean z, List<Integer> list);

    void onEffectiveConnectionTypeChanged(int i2);

    void onEffectiveConnectionTypeChangedOnIOThread(int i2);

    void onMultiNetworkStateChanged(int i2, int i3);

    void onNetworkQualityLevelChanged(int i2);

    void onNetworkQualityLevelChangedOnIOThread(int i2);

    void onNetworkQualityRttAndThroughputNotified(int i2, int i3, int i4);

    void onNetworkQualityRttAndThroughputNotifiedOnIOThread(int i2, int i3, int i4);

    void onPacketLossComputed(int i2, double d2, double d3, double d4, double d5);

    void onPacketLossComputedOnIOThread(int i2, double d2, double d3, double d4, double d5);

    void onPublicIPsChanged(List<String> list, List<String> list2);

    void onRTTOrThroughputEstimatesComputed(int i2, int i3, int i4);

    void onRTTOrThroughputEstimatesComputedOnIOThread(int i2, int i3, int i4);

    void onServerConfigABTestChanged(String str);

    void onServerConfigEtagChanged(String str);

    void onServerConfigUpdated(String str);

    void onStoreIdcChanged(String str, String str2, String str3, String str4, String str5, String str6);

    void onTNCUpdateFailed(ArrayList<String> arrayList, String str);

    void onTTDnsResolveResult(String str, String str2, int i2, int i3, int i4, List<String> list, String str3);

    void onTTNetDetectInfoChanged(String str);

    void onTncRequestSucceeded(boolean z, boolean z2, int i2);

    void onUserSpecifiedNetworkEnabled(boolean z);

    void onWiFiToCellStateChanged(int i2, int i3);

    void sendAppMonitorEvent(String str, String str2);
}
