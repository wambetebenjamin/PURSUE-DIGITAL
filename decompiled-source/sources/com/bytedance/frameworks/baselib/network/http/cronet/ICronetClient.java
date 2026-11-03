package com.bytedance.frameworks.baselib.network.http.cronet;

import android.content.Context;
import com.bytedance.frameworks.baselib.network.http.cronet.e;
import com.bytedance.frameworks.baselib.network.http.cronet.impl.f;
import com.bytedance.frameworks.baselib.network.http.cronet.impl.i;
import com.bytedance.frameworks.baselib.network.sampling.a;
import java.net.HttpURLConnection;
import java.util.Map;
/* loaded from: classes2.dex */
public interface ICronetClient {

    /* loaded from: classes2.dex */
    public interface ICronetBootFailureCheckerProvider {
        boolean isCronetBootFailureExpected();
    }

    /* loaded from: classes2.dex */
    public interface ICronetThreadStackOptFailureCheckerProvider {
        String getThreadStackInitReason();

        boolean isCronetThreadStackOptFailureExpected();
    }

    void addClientOpaqueData(String[] strArr, byte[] bArr, byte[] bArr2, long j2, long j3);

    void bindBigCore(e.c cVar);

    void bindLittleCore(e.c cVar);

    void clearClientOpaqueData();

    void enableTTBizHttpDns(boolean z, String str, String str2, String str3, boolean z2, String str4);

    int getCronetInternalErrorCode(HttpURLConnection httpURLConnection);

    String getCronetVersion();

    int getEffectiveConnectionType();

    int getEffectiveHttpRttMs();

    int getEffectiveRxThroughputKbps();

    int getEffectiveTransportRttMs();

    Map<String, com.bytedance.frameworks.baselib.network.http.cronet.impl.e> getGroupRttEstimates();

    void getMappingRequestState(String str);

    com.bytedance.frameworks.baselib.network.http.cronet.impl.e getNetworkQuality();

    int getNetworkQualityLevel();

    f getPacketLossRateMetrics(int i2);

    boolean isCronetHttpURLConnection(HttpURLConnection httpURLConnection);

    void notifyStoreRegionUpdated(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11);

    void notifyTNCConfigUpdated(String str, String str2, String str3, String str4, String str5, String str6);

    HttpURLConnection openConnection(Context context, String str, boolean z, String str2, com.bytedance.frameworks.baselib.network.http.a aVar, ICronetAppProvider iCronetAppProvider);

    void preconnectUrl(String str, Map<String, String> map);

    void removeClientOpaqueData(String str);

    void reportNetDiagnosisUserLog(String str);

    void resetCoreBind(e.c cVar);

    void runInBackGround(boolean z);

    void setAlogFuncAddr(long j2);

    void setAppStartUpState(int i2);

    void setBestHostWithRouteSelectionName(String str, String str2);

    void setCookieInitCompleted();

    void setCronetBootFailureCheckerProvider(ICronetBootFailureCheckerProvider iCronetBootFailureCheckerProvider);

    void setCronetThreadStackOptFailureCheckerProvider(ICronetThreadStackOptFailureCheckerProvider iCronetThreadStackOptFailureCheckerProvider);

    void setHostResolverRules(String str);

    void setHttpDnsForTesting(boolean z, boolean z2, boolean z3);

    void setOecCallbackAddress(long j2);

    void setProxy(String str);

    void setSlaSamplingSetting(a.h hVar);

    void setZstdFuncAddr(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    void startThrottle(String[] strArr, int i2, long j2);

    void stopThrottle(String[] strArr, int i2);

    void triggerGetDomain(boolean z);

    void tryCreateCronetEngine(Context context, boolean z, boolean z2, boolean z3, boolean z4, String str, boolean z5, ICronetAppProvider iCronetAppProvider);

    void tryStartNetDetect(String[] strArr, int i2, int i3);

    void ttDnsResolve(String str, int i2, String str2, Map<String, String> map);

    i ttUrlDispatch(String str, int i2, boolean z);
}
