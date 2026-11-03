package com.ttnet.org.chromium.net;

import android.content.Context;
import android.util.Pair;
import com.ttnet.org.chromium.net.BidirectionalStream;
import com.ttnet.org.chromium.net.CronetEngine;
import com.ttnet.org.chromium.net.ExperimentalBidirectionalStream;
import com.ttnet.org.chromium.net.ExperimentalUrlRequest;
import com.ttnet.org.chromium.net.RequestFinishedInfo;
import com.ttnet.org.chromium.net.TTAppInfoProvider;
import com.ttnet.org.chromium.net.TTSamplingSettingProvider;
import com.ttnet.org.chromium.net.TTThreadConfigInfoProvider;
import com.ttnet.org.chromium.net.UrlRequest;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
public abstract class ExperimentalCronetEngine extends CronetEngine {
    public static final int CONNECTION_METRIC_UNKNOWN = -1;
    public static final int EFFECTIVE_CONNECTION_TYPE_2G = 3;
    public static final int EFFECTIVE_CONNECTION_TYPE_3G = 4;
    public static final int EFFECTIVE_CONNECTION_TYPE_4G = 5;
    public static final int EFFECTIVE_CONNECTION_TYPE_OFFLINE = 1;
    public static final int EFFECTIVE_CONNECTION_TYPE_SLOW_2G = 2;
    public static final int EFFECTIVE_CONNECTION_TYPE_UNKNOWN = 0;
    public static final int PACKET_LOSS_PROTOCOL_QUIC = 1;
    public static final int PACKET_LOSS_PROTOCOL_TCP = 0;
    public static final long UNBIND_NETWORK_HANDLE = -1;

    public void addClientOpaqueData(String[] strArr, byte[] bArr, byte[] bArr2, long j2, long j3) {
    }

    public void addRequestFinishedListener(RequestFinishedInfo.Listener listener) {
    }

    public void addRttListener(NetworkQualityRttListener networkQualityRttListener) {
    }

    public void addSamplingSetting(TTSamplingSettingProvider.TTSlaSamplingSetting tTSlaSamplingSetting) {
    }

    public void addThroughputListener(NetworkQualityThroughputListener networkQualityThroughputListener) {
    }

    public void bindBigCore(int i2) {
    }

    public void bindLittleCore(int i2) {
    }

    public void bindToNetwork(long j2) {
    }

    public void clearClientOpaqueData() {
    }

    public void configureNetworkQualityEstimatorForTesting(boolean z, boolean z2, boolean z3) {
    }

    public void enableTTBizHttpDns(boolean z, String str, String str2, String str3, boolean z2, String str4) {
    }

    public double getDownstreamPacketLossRate(int i2) {
        return -1.0d;
    }

    public double getDownstreamPacketLossRateVariance(int i2) {
        return -1.0d;
    }

    public int getDownstreamThroughputKbps() {
        return -1;
    }

    public int getEffectiveConnectionType() {
        return 0;
    }

    public int getEffectiveHttpRtt() {
        return -1;
    }

    public int getEffectiveRxThroughput() {
        return -1;
    }

    public int getEffectiveTransportRtt() {
        return -1;
    }

    public int getHttpRttMs() {
        return -1;
    }

    public int getNetworkQualityLevel() {
        return 0;
    }

    @Override // com.ttnet.org.chromium.net.CronetEngine
    public List<Long> getOpaqueFuncAddress() {
        return null;
    }

    public int getTransportRttMs() {
        return -1;
    }

    public double getUpstreamPacketLossRate(int i2) {
        return -1.0d;
    }

    public double getUpstreamPacketLossRateVariance(int i2) {
        return -1.0d;
    }

    public abstract ExperimentalBidirectionalStream.Builder newBidirectionalStreamBuilder(String str, BidirectionalStream.Callback callback, Executor executor);

    @Override // com.ttnet.org.chromium.net.CronetEngine
    public abstract ExperimentalUrlRequest.Builder newUrlRequestBuilder(String str, UrlRequest.Callback callback, Executor executor);

    public void notifyTNCConfigUpdated(String str, String str2, String str3, String str4, String str5, String str6) {
    }

    public void preconnectUrl(String str, int i2, Map<String, String> map) {
    }

    public void registerAppInfoProvider(TTAppInfoProvider tTAppInfoProvider) {
    }

    public void registerAppMonitorProvider(TTMonitorProvider tTMonitorProvider) {
    }

    public void registerEventListener(TTEventListener tTEventListener) {
    }

    public void removeClientOpaqueData(String str) {
    }

    public void removeRequestFinishedListener(RequestFinishedInfo.Listener listener) {
    }

    public void removeRttListener(NetworkQualityRttListener networkQualityRttListener) {
    }

    public void removeThroughputListener(NetworkQualityThroughputListener networkQualityThroughputListener) {
    }

    public void reportNetDiagnosisUserLog(String str) {
    }

    public void resetCoreBind(int i2) {
    }

    public void runInBackGround(boolean z) {
    }

    @Override // com.ttnet.org.chromium.net.CronetEngine
    public void setAlogFuncAddr(long j2) {
    }

    @Override // com.ttnet.org.chromium.net.CronetEngine
    public void setAppStartUpState(int i2) {
    }

    @Override // com.ttnet.org.chromium.net.CronetEngine
    public void setCookieInitCompleted() {
    }

    @Override // com.ttnet.org.chromium.net.CronetEngine
    public void setHostResolverRules(String str) {
    }

    public void setHttpDnsForTesting(boolean z, boolean z2, boolean z3) {
    }

    public void setProxy(String str) {
    }

    public void setRouteSelectionBestHost(String str, String str2) {
    }

    public void setZstdFuncAddr(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9) {
    }

    public void startNetLogToDisk(String str, boolean z, int i2) {
    }

    public void startThrottle(String[] strArr, int i2, long j2) {
    }

    public void stopThrottle(String[] strArr, int i2) {
    }

    public void triggerGetDomain(boolean z) {
    }

    public void tryStartNetDetect(String[] strArr, int i2, int i3) {
    }

    public void ttDnsResolve(String str, int i2, String str2, Map<String, String> map) {
    }

    public Map<String, String> ttUrlDispatch(String str, int i2, boolean z) {
        return null;
    }

    public void updateStoreRegionFromServer(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
    }

    /* loaded from: classes9.dex */
    public static class Builder extends CronetEngine.Builder {
        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder enableSdch(boolean z) {
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public /* bridge */ /* synthetic */ CronetEngine.Builder addPublicKeyPins(String str, Set set, boolean z, Date date) {
            return addPublicKeyPins(str, (Set<byte[]>) set, z, date);
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public /* bridge */ /* synthetic */ CronetEngine.Builder setClientOpaqueData(Map map) {
            return setClientOpaqueData((Map<String[], Pair<byte[], byte[]>>) map);
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public /* bridge */ /* synthetic */ CronetEngine.Builder setOpaqueData(ArrayList arrayList) {
            return setOpaqueData((ArrayList<byte[]>) arrayList);
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public /* bridge */ /* synthetic */ CronetEngine.Builder setThreadConfigInfoList(ArrayList arrayList) {
            return setThreadConfigInfoList((ArrayList<TTThreadConfigInfoProvider.ThreadConfigInfo>) arrayList);
        }

        public Builder(Context context, boolean z) {
            super(context, z);
        }

        public Builder(ICronetEngineBuilder iCronetEngineBuilder) {
            super(iCronetEngineBuilder);
        }

        public Builder enableNetworkQualityEstimator(boolean z) {
            this.mBuilderDelegate.enableNetworkQualityEstimator(z);
            return this;
        }

        public Builder setExperimentalOptions(String str) {
            this.mBuilderDelegate.setExperimentalOptions(str);
            return this;
        }

        public Builder setThreadPriority(int i2) {
            this.mBuilderDelegate.setThreadPriority(i2);
            return this;
        }

        public ICronetEngineBuilder getBuilderDelegate() {
            return this.mBuilderDelegate;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setUserAgent(String str) {
            super.setUserAgent(str);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setStoragePath(String str) {
            super.setStoragePath(str);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setLibraryLoader(CronetEngine.Builder.LibraryLoader libraryLoader) {
            super.setLibraryLoader(libraryLoader);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder enableQuic(boolean z) {
            super.enableQuic(z);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder enableHttp2(boolean z) {
            super.enableHttp2(z);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder enableHttpCache(int i2, long j2) {
            super.enableHttpCache(i2, j2);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder enableHttpDns(boolean z) {
            super.enableHttpDns(z);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder listenAppStateIndependently(boolean z) {
            super.listenAppStateIndependently(z);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder enableVerboseLog() {
            super.enableVerboseLog();
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setAppInfoProvider(TTAppInfoProvider tTAppInfoProvider) {
            super.setAppInfoProvider(tTAppInfoProvider);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setAppMonitorProvider(TTMonitorProvider tTMonitorProvider) {
            super.setAppMonitorProvider(tTMonitorProvider);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setEventListener(TTEventListener tTEventListener) {
            super.setEventListener(tTEventListener);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setAppSecurityLevel(int i2) {
            super.setAppSecurityLevel(i2);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setGetDomainDefaultJSON(String str) {
            super.setGetDomainDefaultJSON(str);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setOpaqueData(ArrayList<byte[]> arrayList) {
            super.setOpaqueData(arrayList);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setClientOpaqueData(Map<String[], Pair<byte[], byte[]>> map) {
            super.setClientOpaqueData(map);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setBypassBOEJSON(String str) {
            super.setBypassBOEJSON(str);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setStoreIdcRuleJSON(String str) {
            super.setStoreIdcRuleJSON(str);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setProxyConfig(String str) {
            super.setProxyConfig(str);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder enableBoeProxy(boolean z) {
            super.enableBoeProxy(z);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder enableTncSecureMode(boolean z) {
            super.enableTncSecureMode(z);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setALogFuncAddr(long j2) {
            super.setALogFuncAddr(j2);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setIsMainProcess(boolean z) {
            super.setIsMainProcess(z);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setLazyInitIPC(boolean z) {
            super.setLazyInitIPC(z);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setProcessName(String str) {
            super.setProcessName(str);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setTTAppInfoColdStartSubset(TTAppInfoProvider.AppInfo appInfo) {
            super.setTTAppInfoColdStartSubset(appInfo);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setOptInit(boolean z) {
            super.setOptInit(z);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setOptNqeInit(boolean z, boolean z2) {
            super.setOptNqeInit(z, z2);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setTTNetInitStart(long j2) {
            super.setTTNetInitStart(j2);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setThreadConfigInfoList(ArrayList<TTThreadConfigInfoProvider.ThreadConfigInfo> arrayList) {
            super.setThreadConfigInfoList(arrayList);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setThreadConfigCallbackImpl(TTThreadConfigInfoProvider.Callback callback) {
            super.setThreadConfigCallbackImpl(callback);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setSlaSamplingSetting(TTSamplingSettingProvider.TTSlaSamplingSetting tTSlaSamplingSetting) {
            super.setSlaSamplingSetting(tTSlaSamplingSetting);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setOecCallbackAddress(long j2) {
            super.setOecCallbackAddress(j2);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder setStackInfo(boolean z, String str) {
            super.setStackInfo(z, str);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder addQuicHint(String str, int i2, int i3) {
            super.addQuicHint(str, i2, i3);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder addPublicKeyPins(String str, Set<byte[]> set, boolean z, Date date) {
            super.addPublicKeyPins(str, set, z, date);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public Builder enablePublicKeyPinningBypassForLocalTrustAnchors(boolean z) {
            super.enablePublicKeyPinningBypassForLocalTrustAnchors(z);
            return this;
        }

        @Override // com.ttnet.org.chromium.net.CronetEngine.Builder
        public ExperimentalCronetEngine build() {
            return this.mBuilderDelegate.build();
        }
    }

    public Map<String, int[]> getGroupRttEstimates() {
        return new HashMap();
    }

    public URLConnection openConnection(URL url, Proxy proxy) {
        return url.openConnection(proxy);
    }
}
