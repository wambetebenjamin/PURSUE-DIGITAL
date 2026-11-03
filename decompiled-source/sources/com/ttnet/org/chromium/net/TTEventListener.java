package com.ttnet.org.chromium.net;

import java.util.List;
import java.util.Map;
/* loaded from: classes9.dex */
public abstract class TTEventListener {
    public abstract Map<String, String> onCallToAddSecurityFactor(String str, Map<String, String> map);

    public abstract void onClientIPChanged(String str);

    public abstract void onColdStartFinish();

    public abstract void onContextInitCompleted(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17);

    public abstract void onCronetBootSucceed();

    public abstract void onDropReasonChanged(boolean z, List<Integer> list);

    public abstract void onEffectiveConnectionTypeChanged(int i2);

    public abstract void onMultiNetworkStateChanged(int i2, int i3);

    public abstract void onNetworkQualityLevelChanged(int i2);

    public abstract void onNetworkQualityRttAndThroughputNotified(int i2, int i3, int i4);

    public abstract void onNetworkQualityTypeChangedV3(int i2);

    public abstract void onPacketLossComputed(int i2, double d2, double d3, double d4, double d5);

    public abstract void onPublicIPsChanged(List<String> list, List<String> list2);

    public abstract void onRTTOrThroughputEstimatesComputed(int i2, int i3, int i4);

    public abstract String onRequestInterceptorToStart(String str, Map<String, String> map, List<String> list);

    public abstract String onResponseInterceptorToStart(String str, Map<String, List<String>> map, int i2);

    public abstract void onServerConfigUpdated(String str, String str2, String str3);

    public abstract void onStoreIdcChanged(String str, String str2, String str3, String str4, String str5, String str6);

    public abstract void onTLBDecompressFail();

    public abstract void onTNCUpdateFailed(String[] strArr, String str);

    public abstract void onTTDnsResolveResult(String str, String str2, int i2, int i3, int i4, List<String> list, String str3);

    public abstract void onTTNetDetectInfoChanged(String str);

    public abstract void onTncRequestSucceeded(boolean z, boolean z2, int i2);

    public abstract void onUserSpecifiedNetworkEnabled(boolean z);

    public abstract void onWiFiToCellStateChanged(int i2, int i3);
}
