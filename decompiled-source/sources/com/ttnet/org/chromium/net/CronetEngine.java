package com.ttnet.org.chromium.net;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.ttnet.org.chromium.net.TTAppInfoProvider;
import com.ttnet.org.chromium.net.TTNetDiagnosisRequest;
import com.ttnet.org.chromium.net.TTSamplingSettingProvider;
import com.ttnet.org.chromium.net.TTThreadConfigInfoProvider;
import com.ttnet.org.chromium.net.TTWebsocketConnection;
import com.ttnet.org.chromium.net.UrlRequest;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
public abstract class CronetEngine {
    private static final String TAG = "CronetEngine";

    public abstract TTMpaService createTTMpaService();

    public abstract URLStreamHandlerFactory createURLStreamHandlerFactory();

    public abstract byte[] getGlobalMetricsDeltas();

    public abstract List<Long> getOpaqueFuncAddress();

    public abstract String getVersionString();

    public abstract TTNetDiagnosisRequest.Builder newNetDiagnosisRequestBuilder(TTNetDiagnosisRequest.Callback callback, Executor executor);

    public abstract UrlRequest.Builder newUrlRequestBuilder(String str, UrlRequest.Callback callback, Executor executor);

    public abstract TTWebsocketConnection.Builder newWebsocketConnectionBuilder(TTWebsocketConnection.Callback callback, Executor executor);

    public abstract URLConnection openConnection(URL url);

    public abstract URLConnection openConnection(URL url, Proxy proxy, boolean z);

    public abstract void setAlogFuncAddr(long j2);

    public abstract void setAppStartUpState(int i2);

    public abstract void setCookieInitCompleted();

    public abstract void setHostResolverRules(String str);

    public abstract void shutdown();

    public abstract void startNetLogToFile(String str, boolean z);

    public abstract void stopNetLog();

    /* loaded from: classes9.dex */
    public static class Builder {
        public static final int HTTP_CACHE_DISABLED = 0;
        public static final int HTTP_CACHE_DISK = 3;
        public static final int HTTP_CACHE_DISK_NO_HTTP = 2;
        public static final int HTTP_CACHE_IN_MEMORY = 1;
        private static final String TAG = "CronetEngine.Builder";
        protected final ICronetEngineBuilder mBuilderDelegate;

        /* loaded from: classes9.dex */
        public static abstract class LibraryLoader {
            public abstract void loadLibrary(String str);
        }

        @Deprecated
        public Builder enableSdch(boolean z) {
            return this;
        }

        public Builder(Context context, boolean z) {
            this(createBuilderDelegate(context, z));
        }

        public Builder(ICronetEngineBuilder iCronetEngineBuilder) {
            this.mBuilderDelegate = iCronetEngineBuilder;
        }

        public String getDefaultUserAgent() {
            return this.mBuilderDelegate.getDefaultUserAgent();
        }

        public Builder setUserAgent(String str) {
            this.mBuilderDelegate.setUserAgent(str);
            return this;
        }

        public Builder setStoragePath(String str) {
            this.mBuilderDelegate.setStoragePath(str);
            return this;
        }

        public Builder setLibraryLoader(LibraryLoader libraryLoader) {
            this.mBuilderDelegate.setLibraryLoader(libraryLoader);
            return this;
        }

        public Builder enableQuic(boolean z) {
            this.mBuilderDelegate.enableQuic(z);
            return this;
        }

        public Builder enableHttp2(boolean z) {
            this.mBuilderDelegate.enableHttp2(z);
            return this;
        }

        public Builder setCronetSoPath(String str) {
            this.mBuilderDelegate.setCronetSoPath(str);
            return this;
        }

        public Builder enableHttpDns(boolean z) {
            this.mBuilderDelegate.enableHttpDns(z);
            return this;
        }

        public Builder listenAppStateIndependently(boolean z) {
            this.mBuilderDelegate.listenAppStateIndependently(z);
            return this;
        }

        public Builder enableVerboseLog() {
            this.mBuilderDelegate.enableVerboseLog();
            return this;
        }

        public Builder setAppInfoProvider(TTAppInfoProvider tTAppInfoProvider) {
            this.mBuilderDelegate.setAppInfoProvider(tTAppInfoProvider);
            return this;
        }

        public Builder setAppMonitorProvider(TTMonitorProvider tTMonitorProvider) {
            this.mBuilderDelegate.setAppMonitorProvider(tTMonitorProvider);
            return this;
        }

        public Builder setEventListener(TTEventListener tTEventListener) {
            this.mBuilderDelegate.setEventListener(tTEventListener);
            return this;
        }

        public Builder setAppSecurityLevel(int i2) {
            this.mBuilderDelegate.setAppSecurityLevel(i2);
            return this;
        }

        public Builder setGetDomainDefaultJSON(String str) {
            this.mBuilderDelegate.setGetDomainDefaultJSON(str);
            return this;
        }

        public Builder setOpaqueData(ArrayList<byte[]> arrayList) {
            this.mBuilderDelegate.setOpaqueData(arrayList);
            return this;
        }

        public Builder setClientOpaqueData(Map<String[], Pair<byte[], byte[]>> map) {
            this.mBuilderDelegate.setClientOpaqueData(map);
            return this;
        }

        public Builder setBypassBOEJSON(String str) {
            this.mBuilderDelegate.setBypassBOEJSON(str);
            return this;
        }

        public Builder setStoreIdcRuleJSON(String str) {
            this.mBuilderDelegate.setStoreIdcRuleJSON(str);
            return this;
        }

        public Builder setProxyConfig(String str) {
            this.mBuilderDelegate.setProxyConfig(str);
            return this;
        }

        public Builder enableBoeProxy(boolean z) {
            this.mBuilderDelegate.enableBoeProxy(z);
            return this;
        }

        public Builder enableTncSecureMode(boolean z) {
            this.mBuilderDelegate.enableTncSecureMode(z);
            return this;
        }

        public Builder setALogFuncAddr(long j2) {
            this.mBuilderDelegate.setALogFuncAddr(j2);
            return this;
        }

        public Builder setIsMainProcess(boolean z) {
            this.mBuilderDelegate.setIsMainProcess(z);
            return this;
        }

        public Builder setLazyInitIPC(boolean z) {
            this.mBuilderDelegate.setLazyInitIPC(z);
            return this;
        }

        public Builder setProcessName(String str) {
            this.mBuilderDelegate.setProcessName(str);
            return this;
        }

        public Builder setTTAppInfoColdStartSubset(TTAppInfoProvider.AppInfo appInfo) {
            this.mBuilderDelegate.setTTAppInfoColdStartSubset(appInfo);
            return this;
        }

        public Builder setOptInit(boolean z) {
            this.mBuilderDelegate.setOptInit(z);
            return this;
        }

        public Builder setOptNqeInit(boolean z, boolean z2) {
            this.mBuilderDelegate.setOptNqeInit(z, z2);
            return this;
        }

        public Builder setTTNetInitStart(long j2) {
            this.mBuilderDelegate.setTTNetInitStart(j2);
            return this;
        }

        public Builder setThreadConfigInfoList(ArrayList<TTThreadConfigInfoProvider.ThreadConfigInfo> arrayList) {
            this.mBuilderDelegate.setThreadConfigInfoList(arrayList);
            return this;
        }

        public Builder setThreadConfigCallbackImpl(TTThreadConfigInfoProvider.Callback callback) {
            this.mBuilderDelegate.setThreadConfigCallbackImpl(callback);
            return this;
        }

        public Builder setSlaSamplingSetting(TTSamplingSettingProvider.TTSlaSamplingSetting tTSlaSamplingSetting) {
            this.mBuilderDelegate.setSlaSamplingSetting(tTSlaSamplingSetting);
            return this;
        }

        public Builder setOecCallbackAddress(long j2) {
            this.mBuilderDelegate.setOecCallbackAddress(j2);
            return this;
        }

        public Builder setStackInfo(boolean z, String str) {
            this.mBuilderDelegate.setStackInfo(z, str);
            return this;
        }

        public Builder enableBrotli(boolean z) {
            this.mBuilderDelegate.enableBrotli(z);
            return this;
        }

        public Builder enableHttpCache(int i2, long j2) {
            this.mBuilderDelegate.enableHttpCache(i2, j2);
            return this;
        }

        public Builder addQuicHint(String str, int i2, int i3) {
            this.mBuilderDelegate.addQuicHint(str, i2, i3);
            return this;
        }

        public Builder addPublicKeyPins(String str, Set<byte[]> set, boolean z, Date date) {
            this.mBuilderDelegate.addPublicKeyPins(str, set, z, date);
            return this;
        }

        public Builder enablePublicKeyPinningBypassForLocalTrustAnchors(boolean z) {
            this.mBuilderDelegate.enablePublicKeyPinningBypassForLocalTrustAnchors(z);
            return this;
        }

        public CronetEngine build() {
            return this.mBuilderDelegate.build();
        }

        private static ICronetEngineBuilder createBuilderDelegate(Context context, boolean z) {
            CronetProvider cronetProvider = getEnabledCronetProviders(context, new ArrayList(CronetProvider.getAllProviders(context))).get(0);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, String.format("Using '%s' provider for creating CronetEngine.Builder.", cronetProvider));
            }
            return cronetProvider.createBuilder(z).mBuilderDelegate;
        }

        static List<CronetProvider> getEnabledCronetProviders(Context context, List<CronetProvider> list) {
            if (list.size() == 0) {
                throw new RuntimeException("Unable to find any Cronet provider. Have you included all necessary jars?");
            }
            Iterator<CronetProvider> it = list.iterator();
            while (it.hasNext()) {
                if (!it.next().isEnabled()) {
                    it.remove();
                }
            }
            if (list.size() == 0) {
                throw new RuntimeException("All available Cronet providers are disabled. A provider should be enabled before it can be used.");
            }
            Collections.sort(list, new Comparator<CronetProvider>() { // from class: com.ttnet.org.chromium.net.CronetEngine.Builder.1
                @Override // java.util.Comparator
                public int compare(CronetProvider cronetProvider, CronetProvider cronetProvider2) {
                    if (CronetProvider.PROVIDER_NAME_FALLBACK.equals(cronetProvider.getName())) {
                        return 1;
                    }
                    if (CronetProvider.PROVIDER_NAME_FALLBACK.equals(cronetProvider2.getName())) {
                        return -1;
                    }
                    return -Builder.compareVersions(cronetProvider.getVersion(), cronetProvider2.getVersion());
                }
            });
            return list;
        }

        static int compareVersions(String str, String str2) {
            if (str == null || str2 == null) {
                throw new IllegalArgumentException("The input values cannot be null");
            }
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            for (int i2 = 0; i2 < split.length && i2 < split2.length; i2++) {
                try {
                    int parseInt = Integer.parseInt(split[i2]);
                    int parseInt2 = Integer.parseInt(split2[i2]);
                    if (parseInt != parseInt2) {
                        return Integer.signum(parseInt - parseInt2);
                    }
                } catch (NumberFormatException e2) {
                    throw new IllegalArgumentException("Unable to convert version segments into integers: " + split[i2] + " & " + split2[i2], e2);
                }
            }
            return Integer.signum(split.length - split2.length);
        }
    }
}
