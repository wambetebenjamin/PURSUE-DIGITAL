package com.ttnet.org.chromium.net;

import com.bytedance.apm.perf.traffic.a;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace(a.KEY_TRAFFIC_STATUS_NET)
/* loaded from: classes9.dex */
public final class TTAppSecurityManager {
    private static final String TAG = "TTAppSecurityManager";
    private static volatile TTAppSecurityManager sInstance;
    private ITTSystemApiSandbox mSystemApiSandbox;

    /* loaded from: classes9.dex */
    public interface ITTSystemApiSandbox {
        String getNetworkOperator();

        String getSimOperator();

        int getWifiFrequency();

        int getWifiRssi();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        boolean isTTPRestrictionEnabled();

        boolean isUserPrivacyRestrictionEnabled();
    }

    public static TTAppSecurityManager getInstance() {
        if (sInstance == null) {
            synchronized (TTAppSecurityManager.class) {
                if (sInstance == null) {
                    sInstance = new TTAppSecurityManager();
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isUserPrivacyRestrictionEnabled() {
        return TTAppSecurityManagerJni.get().isUserPrivacyRestrictionEnabled();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isTTPRestrictionEnabled() {
        return TTAppSecurityManagerJni.get().isTTPRestrictionEnabled();
    }

    public void setSystemApiSandbox(ITTSystemApiSandbox iTTSystemApiSandbox) {
        this.mSystemApiSandbox = iTTSystemApiSandbox;
    }

    public int getWifiFrequency() {
        ITTSystemApiSandbox iTTSystemApiSandbox = this.mSystemApiSandbox;
        if (iTTSystemApiSandbox == null) {
            return 0;
        }
        return iTTSystemApiSandbox.getWifiFrequency();
    }

    public int getWifiRssi() {
        ITTSystemApiSandbox iTTSystemApiSandbox = this.mSystemApiSandbox;
        if (iTTSystemApiSandbox == null) {
            return 0;
        }
        return iTTSystemApiSandbox.getWifiRssi();
    }

    public String getNetworkOperator() {
        ITTSystemApiSandbox iTTSystemApiSandbox = this.mSystemApiSandbox;
        return iTTSystemApiSandbox == null ? "" : iTTSystemApiSandbox.getNetworkOperator();
    }

    public String getSimOperator() {
        ITTSystemApiSandbox iTTSystemApiSandbox = this.mSystemApiSandbox;
        return iTTSystemApiSandbox == null ? "" : iTTSystemApiSandbox.getSimOperator();
    }
}
