package org.chromium;

import android.os.Process;
import com.bytedance.common.utility.concurrent.c;
import com.bytedance.frameworks.baselib.network.e;
import com.bytedance.frameworks.baselib.network.http.j;
import com.bytedance.retrofit2.mime.TTRequestCompressManager;
import com.ttnet.org.chromium.net.TTEventListener;
import com.ttnet.org.chromium.net.impl.TTCronetInitTimingInfo;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class CronetEventListener extends TTEventListener {
    private static volatile CronetEventListener sInstance;
    private boolean isMainProcess = false;
    private boolean enableOptInit = false;

    public static CronetEventListener inst() {
        if (sInstance == null) {
            synchronized (CronetEventListener.class) {
                if (sInstance == null) {
                    sInstance = new CronetEventListener();
                }
            }
        }
        return sInstance;
    }

    private CronetEventListener() {
    }

    public void setIsMainProcess(boolean z) {
        this.isMainProcess = z;
    }

    public void enableOptInit(boolean z) {
        this.enableOptInit = z;
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onServerConfigUpdated(String str, String str2, String str3) {
        if (this.isMainProcess) {
            CronetAppProviderManager.inst().onServerConfigUpdated(str, str2, str3);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onTTNetDetectInfoChanged(String str) {
        if (this.isMainProcess) {
            CronetAppProviderManager.inst().onTTNetDetectInfoChanged(str);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onTTDnsResolveResult(String str, String str2, int i2, int i3, int i4, List<String> list, String str3) {
        CronetAppProviderManager.inst().onTTDnsResolveResult(str, str2, i2, i3, i4, list, str3);
    }

    public void onMappingRequestStatus(String str, int i2) {
        if (this.isMainProcess) {
            CronetAppProviderManager.inst().onMappingRequestStatus(str, i2);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onColdStartFinish() {
        if (this.isMainProcess) {
            CronetAppProviderManager.inst().onColdStartFinish();
        }
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onEffectiveConnectionTypeChanged(int i2) {
        CronetAppProviderManager.inst().onEffectiveConnectionTypeChanged(i2);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onRTTOrThroughputEstimatesComputed(int i2, int i3, int i4) {
        CronetAppProviderManager.inst().onRTTOrThroughputEstimatesComputed(i2, i3, i4);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onPacketLossComputed(int i2, double d2, double d3, double d4, double d5) {
        CronetAppProviderManager.inst().onPacketLossComputed(i2, d2, d3, d4, d5);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onNetworkQualityTypeChangedV3(int i2) {
        CronetAppProviderManager.inst().onNetworkQualityTypeChangedV3(i2);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onClientIPChanged(String str) {
        if (this.isMainProcess) {
            CronetAppProviderManager.inst().onClientIPChanged(str);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onTLBDecompressFail() {
        TTRequestCompressManager.setGlobalForbidenCompress();
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onPublicIPsChanged(List<String> list, List<String> list2) {
        if (this.isMainProcess) {
            CronetAppProviderManager.inst().onPublicIPsChanged(list, list2);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onStoreIdcChanged(String str, String str2, String str3, String str4, String str5, String str6) {
        if (this.isMainProcess) {
            CronetAppProviderManager.inst().onStoreIdcChanged(str, str2, str3, str4, str5, str6);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onDropReasonChanged(boolean z, List<Integer> list) {
        if (this.isMainProcess) {
            CronetAppProviderManager.inst().onDropReasonChanged(z, list);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onTNCUpdateFailed(String[] strArr, String str) {
        if (this.isMainProcess) {
            CronetAppProviderManager.inst().onTNCUpdateFailed(strArr, str);
        }
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public Map<String, String> onCallToAddSecurityFactor(String str, Map<String, String> map) {
        return CronetAppProviderManager.inst().onCallToAddSecurityFactor(str, map);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public String onRequestInterceptorToStart(String str, Map<String, String> map, List<String> list) {
        return j.a(str, map, list);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public String onResponseInterceptorToStart(String str, Map<String, List<String>> map, int i2) {
        return j.a(str, map, i2);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onContextInitCompleted(long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17) {
        if (this.enableOptInit) {
            e.a(true);
        }
        e c2 = e.c();
        c2.f7581f = Process.getThreadPriority(0);
        c2.K = TTCronetInitTimingInfo.sInitThreadStart;
        c2.L = TTCronetInitTimingInfo.sInitThreadEnd;
        c2.M = TTCronetInitTimingInfo.sLoadCronetSoStart;
        c2.N = TTCronetInitTimingInfo.sLoadCronetSoEnd;
        c2.O = TTCronetInitTimingInfo.sNativeInitTimingJson;
        c2.Q = TTCronetInitTimingInfo.sABTest;
        c2.P = System.nanoTime();
        c2.S = j2;
        c2.T = j3;
        c2.U = j4;
        c2.R = j5;
        c2.V = j6;
        c2.W = j7;
        c2.X = j8;
        c2.Y = j9;
        c2.Z = j10;
        c2.aa = j11;
        c2.ab = j12;
        c2.ac = j13;
        c2.ad = j14;
        c2.ae = j15;
        c2.af = j16;
        c2.ag = j17;
        final long appStartTime = CronetAppProviderManager.inst().getAppStartTime();
        if (!e.c().e()) {
            c.getScheduledThreadPool().schedule(new Runnable() { // from class: org.chromium.CronetEventListener.1
                @Override // java.lang.Runnable
                public void run() {
                    JSONObject d2 = e.c().d();
                    JSONObject a2 = e.c().a(appStartTime);
                    CronetAppProviderManager.inst().sendAppMonitorEvent(d2.toString(), e.f7576b);
                    CronetAppProviderManager.inst().sendAppMonitorEvent(a2.toString(), e.f7577c);
                }
            }, 5L, TimeUnit.SECONDS);
            if (this.enableOptInit) {
                return;
            }
            e.a(true);
            return;
        }
        JSONObject d2 = e.c().d();
        JSONObject a2 = e.c().a(appStartTime);
        if (!this.enableOptInit) {
            e.a(true);
        }
        CronetAppProviderManager.inst().sendAppMonitorEvent(d2.toString(), e.f7576b);
        CronetAppProviderManager.inst().sendAppMonitorEvent(a2.toString(), e.f7577c);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onCronetBootSucceed() {
        if (this.isMainProcess) {
            CronetAppProviderManager.inst().onCronetBootSucceed();
        }
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onWiFiToCellStateChanged(int i2, int i3) {
        CronetAppProviderManager.inst().onWiFiToCellStateChanged(i2, i3);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onMultiNetworkStateChanged(int i2, int i3) {
        CronetAppProviderManager.inst().onMultiNetworkStateChanged(i2, i3);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onUserSpecifiedNetworkEnabled(boolean z) {
        CronetAppProviderManager.inst().onUserSpecifiedNetworkEnabled(z);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onNetworkQualityLevelChanged(int i2) {
        CronetAppProviderManager.inst().onNetworkQualityLevelChanged(i2);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onNetworkQualityRttAndThroughputNotified(int i2, int i3, int i4) {
        CronetAppProviderManager.inst().onNetworkQualityRttAndThroughputNotified(i2, i3, i4);
    }

    @Override // com.ttnet.org.chromium.net.TTEventListener
    public void onTncRequestSucceeded(boolean z, boolean z2, int i2) {
        CronetAppProviderManager.inst().onTncRequestSucceeded(z, z2, i2);
    }
}
