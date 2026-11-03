package org.chromium;

import com.ttnet.org.chromium.net.TTAppSecurityManager;
/* loaded from: classes10.dex */
public class CronetSystemApiSandbox implements TTAppSecurityManager.ITTSystemApiSandbox {
    private static volatile CronetSystemApiSandbox sInstance;

    public static CronetSystemApiSandbox inst() {
        if (sInstance == null) {
            synchronized (CronetSystemApiSandbox.class) {
                if (sInstance == null) {
                    sInstance = new CronetSystemApiSandbox();
                }
            }
        }
        return sInstance;
    }

    private CronetSystemApiSandbox() {
    }

    @Override // com.ttnet.org.chromium.net.TTAppSecurityManager.ITTSystemApiSandbox
    public int getWifiFrequency() {
        return CronetAppProviderManager.inst().getWifiFrequency();
    }

    @Override // com.ttnet.org.chromium.net.TTAppSecurityManager.ITTSystemApiSandbox
    public int getWifiRssi() {
        return CronetAppProviderManager.inst().getWifiRssi();
    }

    @Override // com.ttnet.org.chromium.net.TTAppSecurityManager.ITTSystemApiSandbox
    public String getNetworkOperator() {
        return CronetAppProviderManager.inst().getNetworkOperator();
    }

    @Override // com.ttnet.org.chromium.net.TTAppSecurityManager.ITTSystemApiSandbox
    public String getSimOperator() {
        return CronetAppProviderManager.inst().getSimOperator();
    }
}
